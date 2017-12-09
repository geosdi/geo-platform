/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.DeleteByPageSearch;
import org.geosdi.geoplatform.experimental.el.search.delete.responsibility.IGPDeleteHandlerManager;
import org.geosdi.geoplatform.experimental.el.search.delete.responsibility.IGPDeleteHandlerManager.GPDeleteHandlerManager;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableElasticSearchDAO<D extends Document> extends GPBaseElasticSearchDAO<D> {

    private static final IGPDeleteHandlerManager deleteHandlerManager = new GPDeleteHandlerManager();

    @Override
    public <P extends Page> IPageResult<D> find(P page) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, Stream.of(searchResponse.getHits().getHits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null).collect(toList()));
    }

    /**
     * @param page
     * @return {@link IPageResult<V>}
     * @throws Exception
     */
    @Override
    public <P extends Page, V extends D> IPageResult<V> find(P page, Class<V> subType) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<V>(total, Stream.of(searchResponse.getHits().getHits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null)
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
    public <P extends Page, V extends Document> IPageResult<V> findAndMappingWith(P page, Class<V> classe) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        Preconditions.checkNotNull(classe, "The Parameter classe must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<V>(total, Stream.of(searchResponse.getHits().getHits())
                .map(searchHit -> this.readDocument(searchHit, classe))
                .filter(s -> s != null)
                .collect(toList()));
    }

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @return {@link IPageResult<D>}
     * @throws Exception
     */
    @Override
    public <P extends Page> IPageResult<D> find(P page, String[] includeFields, String[] excludeFields)
            throws Exception {
        checkArgument((page != null), "Page must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        builder.setFetchSource(includeFields, excludeFields);
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, Stream.of(searchResponse.getHits().getHits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null).collect(toList()));
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
    public <P extends Page, V extends Document> IPageResult<V> find(P page, String[] includeFields, String[] excludeFields,
            Class<V> classe) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        builder.setFetchSource(includeFields, excludeFields);
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<V>(total, Stream.of(searchResponse.getHits().getHits())
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
    public <P extends Page> IPageResult<D> find(P page, String includeField, String excludeField)
            throws Exception {
        checkArgument((page != null), "Page must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        builder.setFetchSource(includeField, excludeField);
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, Stream.of(searchResponse.getHits().getHits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null).collect(toList()));
    }

    /**
     * @param page
     * @param aggregationBuilder
     * @return {@link SearchResponse}
     * @throws Exception
     */
    @Override
    public <P extends Page> SearchResponse find(P page, AbstractAggregationBuilder aggregationBuilder)
            throws Exception {
        checkArgument((page != null), "Page must not be null.");
        checkArgument((aggregationBuilder != null), "AggregationBuilder must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()))
                .addAggregation(aggregationBuilder);
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        return searchResponse;
    }

    /**
     * s
     *
     * @param page
     * @throws Exception
     */
    @Override
    public <Result extends DeleteByPage.IDeleteByPageResult, P extends Page> Result deleteByPage(P page) throws Exception {
        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
        return deleteHandlerManager.delete(new DeleteByPageSearch(page), this);
    }

    /**
     * @param page
     * @return {@link CompletableFuture<BulkResponse>}
     * @throws Exception
     */
    @Override
    public <Result extends DeleteByPage.IDeleteByPageResult, P extends Page> CompletableFuture<Result> deleteByPageAsync(P page) throws Exception {
        return CompletableFuture.supplyAsync(() -> {

            try {
                return deleteByPage(page);
            } catch (Exception ex) {
                logger.error("@@@@@@@@@@@@@@@@@@@@@@@@PageableElasticSearchDAO#deleteByPageAsync error : {}\n",
                        ex.getMessage());
                throw new IllegalStateException(ex);
            }
        }, this.elasticSearchExecutor);
    }

    /**
     * @param page
     * @return {@link Long}
     * @throws Exception
     */
    @Override
    public <P extends Page> Long count(P page) throws Exception {
        checkArgument((page != null), "Page must not be null.");
        super.refreshIndex();
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        return searchResponse.getHits().getTotalHits();
    }
}
