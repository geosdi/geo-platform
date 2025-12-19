/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.page.scroll;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.geosdi.geoplatform.experimental.el.api.function.GPElasticSearchCheck;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.dao.scroll.GPScrollElasticSearchConfig;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.page.PageableElasticSearchRestDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Single.fromCallable;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.search.sort.SortOrder.ASC;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableElasticSearchRestScrollDAO<D extends Document> extends PageableElasticSearchRestDAO<D> implements GPPageableElasticSearchRestScrollDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected PageableElasticSearchRestScrollDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param theScrollConfig
     * @param <P>
     * @param <R>
     * @param <E>
     * @throws Exception
     */
    @Override
    public <P extends Page, R extends Object, E extends Exception> void findWithScroll(@Nonnull(when = NEVER) GPScrollElasticSearchConfig<P, D, R, E, GPElasticSearchCheck<R, List<D>, E>> theScrollConfig) throws Exception {
        checkArgument((theScrollConfig != null), "The Parameter scrollConfig must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = theScrollConfig.toPage()
                .buildPage(new SearchSourceBuilder())
                .size(theScrollConfig.toSize())
                .sort("_doc", ASC);
        searchRequest.source(searchSourceBuilder);
        Scroll scroll = new Scroll(theScrollConfig.toTimeValue());
        searchRequest.scroll(scroll);
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        while ((searchHits != null) && (searchHits.length > 0)) {
            logger.trace("######################NUMBER_OF_ELEMENTS : {}\n", searchHits.length);
            theScrollConfig.toCheckFunction().apply(of(searchResponse.getHits().getHits())
                    .filter(Objects::nonNull)
                    .map(this::readDocument)
                    .filter(Objects::nonNull)
                    .collect(toList()));
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = this.elasticSearchRestHighLevelClient.scroll(scrollRequest, DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }
        // Clear the scroll context
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        this.elasticSearchRestHighLevelClient.clearScroll(clearScrollRequest, DEFAULT);
        theScrollConfig.toScroolElasticSearchCallback().doOnCompleteScrool();
    }

    /**
     * @param theScrollConfig
     * @param <P>
     * @param <R>
     * @param <E>
     * @return {@link Disposable}
     * @throws Exception
     */
    @Override
    public <P extends Page, R, E extends Exception> Disposable findWithRXScrollAsync(@Nonnull(when = NEVER) GPScrollElasticSearchConfig<P, D, R, E, GPElasticSearchCheck<R, List<D>, E>> theScrollConfig) throws Exception {
        checkArgument((theScrollConfig != null), "The Parameter scrollConfig must not be null.");
        AtomicReference<String> scrollId = new AtomicReference<>();
        return this.prepareRXSearchRequestWithScroll(theScrollConfig)
                .flatMapObservable(searchRequest -> this.performRXSearchWithScroll(searchRequest, theScrollConfig, scrollId))
                .subscribeOn(io())
                .observeOn(io())
                .doOnComplete(() -> {
                    ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
                    clearScrollRequest.addScrollId(scrollId.get());
                    elasticSearchRestHighLevelClient.clearScroll(clearScrollRequest, DEFAULT);
                })
                .doOnComplete(theScrollConfig.toScroolElasticSearchCallback()::doOnCompleteScrool)
                .subscribe(theScrollConfig.toCheckFunction()::apply, Throwable::printStackTrace);
    }

    /**
     * @param theScrollConfig
     * @return {@link Single<SearchRequest>}
     */
    protected <P extends Page, R, E extends Exception> Single<SearchRequest> prepareRXSearchRequestWithScroll(@Nonnull(when = NEVER) GPScrollElasticSearchConfig<P, D, R, E, GPElasticSearchCheck<R, List<D>, E>> theScrollConfig) {
        checkArgument(theScrollConfig != null, "The Parameter scrollConfig must not be null");
        return fromCallable(() -> {
            SearchRequest searchRequest = this.prepareSearchRequest();
            SearchSourceBuilder searchSourceBuilder = theScrollConfig.toPage()
                    .buildPage(new SearchSourceBuilder())
                    .size(theScrollConfig.toSize())
                    .sort("_doc", ASC);
            searchRequest.source(searchSourceBuilder);
            Scroll scroll = new Scroll(theScrollConfig.toTimeValue());
            searchRequest.scroll(scroll);
            return searchRequest;
        });
    }

    /**
     * @param searchRequest
     * @param theScrollConfig
     * @param scrollId
     * @return {@link Observable<List<D>>}
     */
    protected <P extends Page, R, E extends Exception> Observable<List<D>> performRXSearchWithScroll(@Nonnull(when = NEVER) SearchRequest searchRequest, @Nonnull(when = NEVER) GPScrollElasticSearchConfig<P, D, R, E, GPElasticSearchCheck<R, List<D>, E>> theScrollConfig, AtomicReference<String> scrollId) {
        checkArgument(searchRequest != null, "The Parameter searchRequest must not be null");
        checkArgument(theScrollConfig != null, "The Parameter scrollConfig must not be null");
        checkArgument(scrollId != null, "The Parameter scrollId must not be null");
        return Observable.create(emitter -> {
            try {
                SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
                scrollId.set(searchResponse.getScrollId());
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                while ((searchHits != null) && (searchHits.length > 0)) {
                    List<D> documents = of(searchHits)
                            .filter(Objects::nonNull)
                            .map(this::readDocument)
                            .filter(Objects::nonNull)
                            .collect(toList());

                    emitter.onNext(documents);

                    SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId.get());
                    scrollRequest.scroll(new Scroll(theScrollConfig.toTimeValue()));
                    searchResponse = this.elasticSearchRestHighLevelClient.scroll(scrollRequest, DEFAULT);
                    scrollId.set(searchResponse.getScrollId());
                    searchHits = searchResponse.getHits().getHits();
                }
                emitter.onComplete();
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        });
    }
}