/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.rest.api.dao.page;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.condition.PredicateCondition;
import org.geosdi.geoplatform.experimental.el.dao.PageResult;
import org.geosdi.geoplatform.experimental.el.rest.api.dao.index.ElasticSearchRestIndexDAO;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.rest.RestStatus.OK;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableElasticSearchRestDAO<D extends Document> extends ElasticSearchRestIndexDAO<D> implements GPPageableElasticSearchRestDAO<D> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    protected PageableElasticSearchRestDAO(@Nonnull(when = NEVER) Class<D> theEntityClass, @Nullable GPJacksonSupport theJacksonSupport) {
        super(theEntityClass, theJacksonSupport);
    }

    /**
     * @param page
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        searchRequest.source(searchSourceBuilder);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@SEARCH_SOURCE_BUILDER : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .collect(toList()));
    }

    /**
     * @param page
     * @param thePredicate
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) PredicateCondition<D> thePredicate) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        checkArgument((thePredicate != null), "Predicate must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        searchRequest.source(searchSourceBuilder);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@SEARCH_SOURCE_BUILDER : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .filter(thePredicate)
                .collect(toList()));
    }

    /**
     * @param page
     * @param subType
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    public <P extends Page, V extends D> IPageResult<V> find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> subType) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        checkArgument(subType != null, "The Parameter subType must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        searchRequest.source(searchSourceBuilder);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@SEARCH_SOURCE_BUILDER : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<V>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .filter(subType::isInstance)
                .map(s -> (V) s)
                .collect(toList()));
    }

    /**
     * @param page
     * @param classe
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    public <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        checkArgument(classe != null, "The Parameter subType must not be null.");
        checkNotNull(classe, "The Parameter classe must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        searchRequest.source(searchSourceBuilder);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@SEARCH_SOURCE_BUILDER : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<V>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(searchHit -> this.readDocument(searchHit, classe))
                .filter(Objects::nonNull)
                .collect(toList()));
    }

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, String[] includeFields, String[] excludeFields) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder()).fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);
        logger.trace("#########################Builder : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .collect(toList()));
    }

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @param classe
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    public <P extends Page, V extends Document> IPageResult<V> find(@Nonnull(when = NEVER) P page, String[] includeFields,
            String[] excludeFields, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        checkArgument(classe != null, "The Parameter subType must not be null.");
        checkNotNull(classe, "The Parameter classe must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);
        logger.trace("#########################Builder : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<V>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(searchHit -> this.readDocument(searchHit, classe))
                .filter(s -> s != null)
                .collect(toList()));
    }

    /**
     * @param page
     * @param includeField
     * @param excludeField
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    @Override
    public <P extends Page> IPageResult<D> find(@Nonnull(when = NEVER) P page, String includeField, String excludeField) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        searchSourceBuilder.fetchSource(includeField, excludeField);
        searchRequest.source(searchSourceBuilder);
        logger.trace("#########################Builder : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits().value;
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, of(searchResponse.getHits().getHits())
                .filter(Objects::nonNull)
                .map(this::readDocument)
                .filter(Objects::nonNull)
                .collect(toList()));
    }

    /**
     * @param page
     * @param aggregationBuilder
     * @return {@link SearchResponse}
     * @throws Exception
     */
    @Override
    public <P extends Page> SearchResponse find(@Nonnull(when = NEVER) P page, @Nonnull(when = NEVER) AbstractAggregationBuilder aggregationBuilder) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        checkArgument((aggregationBuilder != null), "AggregationBuilder must not be null.");
        SearchRequest searchRequest = this.prepareSearchRequest();
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder()).aggregation(aggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        logger.trace("#########################Builder : {}\n\n", searchSourceBuilder.toString());
        SearchResponse searchResponse = this.elasticSearchRestHighLevelClient.search(searchRequest, DEFAULT);
        if (searchResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        return searchResponse;
    }

    /**
     * @param page
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public <P extends Page> Long count(@Nonnull(when = NEVER) P page) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        CountRequest countRequest = new CountRequest(this.getIndexName());
        SearchSourceBuilder searchSourceBuilder = page.buildPage(new SearchSourceBuilder());
        countRequest.query(searchSourceBuilder.query());
        logger.trace("#########################Builder : {}\n\n", searchSourceBuilder.toString());
        CountResponse countResponse = this.elasticSearchRestHighLevelClient.count(countRequest, DEFAULT);
        if (countResponse.status() != OK) {
            throw new IllegalStateException("Problem in Search : " + countResponse.status());
        }
        return countResponse.getCount();
    }
}