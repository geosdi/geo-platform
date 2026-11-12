/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.page.scroll;

import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import co.elastic.clients.json.JsonData;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el9.dao.scroll.GPScrollElasticSearchConfig;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.page.PageableElasticSearchRestDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Duration;
import java.util.List;

import static co.elastic.clients.elasticsearch._types.SortOrder.Asc;
import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPPageableElasticSearchRestScrollDAO}.
 * <p>Dalla pagina si prende solo la query: lo scroll ordina per {@code _doc}, il modo più veloce, e non accetta
 * {@code from}.</p>
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableElasticSearchRestScrollDAO<D extends Document> extends PageableElasticSearchRestDAO<D> implements GPPageableElasticSearchRestScrollDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected PageableElasticSearchRestScrollDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param theScrollConfig la configurazione dello scroll
     * @param <P>             il tipo della pagina
     * @param <R>             il tipo restituito dalla funzione
     * @param <E>             l'eccezione della funzione
     * @throws Exception se una richiesta o la funzione falliscono
     */
    @Override
    public <P extends Page, R, E extends Exception> void findWithScroll(@Nonnull(when = NEVER) GPScrollElasticSearchConfig<P, D, R, E, GPElasticSearchCheck<R, List<D>, E>> theScrollConfig) throws Exception {
        checkArgument(theScrollConfig != null, "Il parametro scrollConfig non deve essere null.");
        String scrollId = null;
        try {
            ResponseBody<JsonData> response = this.elasticSearchClient.search(this.createScrollRequest(theScrollConfig), JsonData.class);
            scrollId = response.scrollId();
            while (!response.hits().hits().isEmpty()) {
                theScrollConfig.toCheckFunction().apply(this.readHits(response.hits().hits(), this::readHit));
                String currentScrollId = scrollId;
                response = this.elasticSearchClient.scroll(s -> s.scrollId(currentScrollId).scroll(toTime(theScrollConfig.toKeepAlive())), JsonData.class);
                scrollId = response.scrollId();
            }
        } finally {
            this.clearScroll(scrollId);
        }
        theScrollConfig.toScroolElasticSearchCallback().doOnCompleteScrool();
    }

    /**
     * @param theScrollConfig la configurazione dello scroll
     * @param <P>             il tipo della pagina
     * @param <R>             il tipo restituito dalla funzione
     * @param <E>             l'eccezione della funzione
     * @return {@link Disposable}
     * @throws Exception se la lettura non parte
     */
    @Override
    public <P extends Page, R, E extends Exception> Disposable findWithRXScrollAsync(@Nonnull(when = NEVER) GPScrollElasticSearchConfig<P, D, R, E, GPElasticSearchCheck<R, List<D>, E>> theScrollConfig) throws Exception {
        checkArgument(theScrollConfig != null, "Il parametro scrollConfig non deve essere null.");
        return Observable.<List<D>>create(emitter -> {
                    String scrollId = null;
                    try {
                        ResponseBody<JsonData> response = this.elasticSearchClient.search(this.createScrollRequest(theScrollConfig), JsonData.class);
                        scrollId = response.scrollId();
                        while (!(emitter.isDisposed()) && !(response.hits().hits().isEmpty())) {
                            emitter.onNext(this.readHits(response.hits().hits(), this::readHit));
                            String currentScrollId = scrollId;
                            response = this.elasticSearchClient.scroll(s -> s.scrollId(currentScrollId).scroll(toTime(theScrollConfig.toKeepAlive())), JsonData.class);
                            scrollId = response.scrollId();
                        }
                        emitter.onComplete();
                    } catch (Exception ex) {
                        emitter.tryOnError(ex);
                    } finally {
                        this.clearScroll(scrollId);
                    }
                })
                .subscribeOn(io())
                .observeOn(io())
                .doOnComplete(theScrollConfig.toScroolElasticSearchCallback()::doOnCompleteScrool)
                .subscribe(theScrollConfig.toCheckFunction()::apply,
                        ex -> logger.error("@@@@@@@@@@@@@@@@@@@@@@{} - scroll interrotto da un errore", getClass().getSimpleName(), ex));
    }

    /**
     * @param theScrollConfig la configurazione dello scroll
     * @return {@link SearchRequest}
     */
    protected SearchRequest createScrollRequest(@Nonnull(when = NEVER) GPScrollElasticSearchConfig<?, D, ?, ?, ?> theScrollConfig) {
        Query query = theScrollConfig.toPage().toQuery();
        SearchRequest.Builder builder = this.prepareSearchRequest()
                .scroll(toTime(theScrollConfig.toKeepAlive()))
                .size(theScrollConfig.toSize())
                .sort(s -> s.field(f -> f.field("_doc").order(Asc)));
        if (query != null) {
            builder.query(query);
        }
        return builder.build();
    }

    /**
     * Chiude il contesto dello scroll; un errore qui non deve coprire quello della lettura.
     *
     * @param theScrollId l'id dello scroll, null se la prima ricerca è fallita
     */
    protected void clearScroll(@Nullable String theScrollId) {
        if (theScrollId == null) {
            return;
        }
        try {
            this.elasticSearchClient.clearScroll(c -> c.scrollId(theScrollId));
        } catch (Exception ex) {
            logger.warn("#####################{} - scroll non chiuso: {}", getClass().getSimpleName(), ex.toString());
        }
    }

    /**
     * @param theDuration la durata
     * @return {@link Time} la durata in millisecondi, nel formato di ElasticSearch
     */
    protected static Time toTime(@Nonnull(when = NEVER) Duration theDuration) {
        checkArgument(theDuration != null, "Il parametro duration non deve essere null.");
        return Time.of(t -> t.time(theDuration.toMillis() + "ms"));
    }
}
