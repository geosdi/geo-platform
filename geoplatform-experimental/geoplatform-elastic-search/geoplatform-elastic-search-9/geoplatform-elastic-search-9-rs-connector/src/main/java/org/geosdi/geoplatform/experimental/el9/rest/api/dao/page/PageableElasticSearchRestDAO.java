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
package org.geosdi.geoplatform.experimental.el9.rest.api.dao.page;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.dao.store.PageStore;
import org.geosdi.geoplatform.experimental.el9.dao.PageResult;
import org.geosdi.geoplatform.experimental.el9.rest.api.dao.index.ElasticSearchRestIndexDAO;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPPageableElasticSearchRestDAO}.
 *
 * @param <D> il tipo dei documenti
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableElasticSearchRestDAO<D extends Document> extends ElasticSearchRestIndexDAO<D> implements GPPageableElasticSearchRestDAO<D> {

    /**
     * @param theEntityClass    la classe dei documenti
     * @param theJacksonSupport il mapper dei documenti; null vuol dire quello di default
     */
    protected PageableElasticSearchRestDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param page la pagina
     * @param <P>  il tipo della pagina
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page) throws Exception {
        return this.search(page, null, null, this::readHit);
    }

    /**
     * @param page         la pagina
     * @param thePredicate il filtro sui documenti della pagina
     * @param <P>          il tipo della pagina
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) PredicateCondition<D> thePredicate) throws Exception {
        checkArgument(thePredicate != null, "Il parametro predicate non deve essere null.");
        IPageResult<D> result = this.find(page);
        return new PageResult<>(result.getTotal(), result.getResults().stream().filter(thePredicate).toList());
    }

    /**
     * @param page    la pagina
     * @param subType il sottotipo da tenere
     * @param <P>     il tipo della pagina
     * @param <V>     il sottotipo
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page, V extends D> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> subType) throws Exception {
        checkArgument(subType != null, "Il parametro subType non deve essere null.");
        IPageResult<D> result = this.find(page);
        return new PageResult<>(result.getTotal(), result.getResults().stream().filter(subType::isInstance).map(subType::cast).toList());
    }

    /**
     * @param page   la pagina
     * @param classe la classe in cui leggere i documenti
     * @param <P>    il tipo della pagina
     * @param <V>    il tipo letto
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument(classe != null, "Il parametro classe non deve essere null.");
        return this.search(page, null, null, hit -> this.readDocument(hit.id(), hit.source(), classe));
    }

    /**
     * @param page          la pagina
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @param <P>           il tipo della pagina
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields, @Nullable String[] excludeFields) throws Exception {
        return this.search(page, includeFields, excludeFields, this::readHit);
    }

    /**
     * @param page          la pagina
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @param classe        la classe in cui leggere i documenti
     * @param <P>           il tipo della pagina
     * @param <V>           il tipo letto
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page, V extends Document> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields,
            @Nullable String[] excludeFields, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument(classe != null, "Il parametro classe non deve essere null.");
        return this.search(page, includeFields, excludeFields, hit -> this.readDocument(hit.id(), hit.source(), classe));
    }

    /**
     * @param page          la pagina
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @param classe        la classe in cui leggere i sorgenti
     * @param <P>           il tipo della pagina
     * @param <V>           il tipo letto
     * @return {@link PageStore}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page, V> PageStore<V> findAndMappingWithClass(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields,
            @Nullable String[] excludeFields, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument(classe != null, "Il parametro classe non deve essere null.");
        IPageResult<V> result = this.search(page, includeFields, excludeFields, hit -> this.read(hit.source(), classe));
        return new PageStore<>(result.getTotal(), result.getResults());
    }

    /**
     * @param page         la pagina
     * @param includeField il campo da restituire; null vuol dire tutti
     * @param excludeField il campo da togliere; null vuol dire nessuno
     * @param <P>          il tipo della pagina
     * @return {@link IPageResult}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nullable String includeField, @Nullable String excludeField) throws Exception {
        return this.find(page, ((includeField != null) ? new String[]{includeField} : null), ((excludeField != null) ? new String[]{excludeField} : null));
    }

    /**
     * @param page         la pagina
     * @param aggregations le aggregazioni, per nome
     * @param <P>          il tipo della pagina
     * @return {@link SearchResponse}
     * @throws Exception se la ricerca fallisce
     */
    @Override
    public <P extends Page> SearchResponse<JsonData> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Map<String, Aggregation> aggregations) throws Exception {
        checkArgument(page != null, "Il parametro page non deve essere null.");
        checkArgument(aggregations != null, "Il parametro aggregations non deve essere null.");
        SearchRequest.Builder builder = page.buildPage(this.prepareSearchRequest()).aggregations(aggregations);
        return this.elasticSearchClient.search(builder.build(), JsonData.class);
    }

    /**
     * @param page la pagina
     * @param <P>  il tipo della pagina
     * @return {@link Long}
     * @throws Exception se il conteggio fallisce
     */
    @Override
    public <P extends Page> Long count(@Nonnull(when = NEVER) P page) throws Exception {
        checkArgument(page != null, "Il parametro page non deve essere null.");
        Query query = page.toQuery();
        return this.elasticSearchClient.count(c -> {
            c.index(this.getIndexName());
            if (query != null) {
                c.query(query);
            }
            return c;
        }).count();
    }

    /**
     * @param page          la pagina
     * @param includeFields i campi da restituire; null vuol dire tutti
     * @param excludeFields i campi da togliere; null vuol dire nessuno
     * @param theReader     la lettura di ogni risultato
     * @param <P>           il tipo della pagina
     * @param <V>           il tipo letto
     * @return {@link IPageResult} i risultati letti, senza quelli non leggibili, e il totale
     * @throws Exception se la ricerca fallisce
     */
    protected <P extends Page, V> IPageResult<V> search(@Nonnull(when = NEVER) P page, @Nullable String[] includeFields,
            @Nullable String[] excludeFields, @Nonnull(when = NEVER) Function<Hit<JsonData>, V> theReader) throws Exception {
        checkArgument(page != null, "Il parametro page non deve essere null.");
        checkArgument(theReader != null, "Il parametro reader non deve essere null.");
        SearchRequest.Builder builder = page.buildPage(this.prepareSearchRequest());
        if ((includeFields != null) || (excludeFields != null)) {
            builder.source(s -> s.filter(f -> {
                if (includeFields != null) {
                    f.includes(asList(includeFields));
                }
                if (excludeFields != null) {
                    f.excludes(asList(excludeFields));
                }
                return f;
            }));
        }
        return this.search(builder.build(), theReader);
    }

    /**
     * @param theRequest la ricerca
     * @param theReader  la lettura di ogni risultato
     * @param <V>        il tipo letto
     * @return {@link IPageResult} i risultati letti, senza quelli non leggibili, e il totale
     * @throws Exception se la ricerca fallisce
     */
    protected <V> IPageResult<V> search(@Nonnull(when = NEVER) SearchRequest theRequest, @Nonnull(when = NEVER) Function<Hit<JsonData>, V> theReader) throws Exception {
        checkArgument(theRequest != null, "Il parametro request non deve essere null.");
        SearchResponse<JsonData> response = this.elasticSearchClient.search(theRequest, JsonData.class);
        Long total = ((response.hits().total() != null) ? response.hits().total().value() : Long.valueOf(response.hits().hits().size()));
        return new PageResult<>(total, this.readHits(response.hits().hits(), theReader));
    }

    /**
     * @param theHits   i risultati
     * @param theReader la lettura di ogni risultato
     * @param <V>       il tipo letto
     * @return {@link List} i risultati letti, senza quelli non leggibili
     */
    protected <V> List<V> readHits(@Nonnull(when = NEVER) List<Hit<JsonData>> theHits, @Nonnull(when = NEVER) Function<Hit<JsonData>, V> theReader) {
        return theHits.stream()
                .filter(Objects::nonNull)
                .map(theReader)
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * @param theHit il risultato
     * @return {@link D} il documento, null se non si legge
     */
    protected D readHit(@Nonnull(when = NEVER) Hit<JsonData> theHit) {
        return this.readDocument(theHit.id(), theHit.source());
    }
}
