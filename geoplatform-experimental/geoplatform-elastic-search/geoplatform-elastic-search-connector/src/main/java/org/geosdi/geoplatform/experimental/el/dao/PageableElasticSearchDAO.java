package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableElasticSearchDAO<D extends Document> extends GPBaseElasticSearchDAO<D> {

    @Override
    public <P extends Page> IPageResult<D> find(P page) throws Exception {
        Preconditions.checkArgument((page != null), "Page must not be null.");
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################Builder : {}\n\n", builder.toString());
        SearchResponse searchResponse = builder.get();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        Long total = searchResponse.getHits().getTotalHits();
        logger.debug("###################TOTAL HITS FOUND : {} .\n\n", total);
        return new PageResult<D>(total, Stream.of(searchResponse.getHits().hits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null).collect(Collectors.toList()));
    }

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    @Override
    public <P extends Page> IPageResult<D> find(P page, String[] includeFields, String[] excludeFields)
            throws Exception {
        Preconditions.checkArgument((page != null), "Page must not be null.");
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
        return new PageResult<D>(total, Stream.of(searchResponse.getHits().hits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null).collect(Collectors.toList()));
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
        Preconditions.checkArgument((page != null), "Page must not be null.");
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
        return new PageResult<D>(total, Stream.of(searchResponse.getHits().hits())
                .map(searchHit -> this.readDocument(searchHit))
                .filter(s -> s != null).collect(Collectors.toList()));
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
        Preconditions.checkArgument((page != null), "Page must not be null.");
        Preconditions.checkArgument((aggregationBuilder != null), "AggregationBuilder must not be null.");
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
     * @param page
     * @throws Exception
     */
    @Override
    public <P extends Page> Boolean deleteByPage(P page) throws Exception {
        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################deleteByPage#Builder : \n{}\n", builder.toString());
        SearchResponse searchResponse = builder.setSize((page.getSize() > 0) ? page.getSize() : 50)
                .setScroll(new TimeValue(60000)).setNoFields().execute().actionGet();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        while (true) {
            Stream.of(searchResponse.getHits().hits()).forEach(searchHit -> {
                this.elastichSearchClient.delete(new DeleteRequest(getIndexName(), getIndexType(),
                        searchHit.getId())).actionGet();
            });
            searchResponse = this.elastichSearchClient.prepareSearchScroll(searchResponse.getScrollId())
                    .setScroll(new TimeValue(60000)).execute().actionGet();
            if (searchResponse.getHits().getHits().length == 0) {
                break;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * @param page
     * @return {@link CompletableFuture<Boolean>}
     * @throws Exception
     */
    @Override
    public <P extends Page> CompletableFuture<Boolean> deleteByPageAsync(P page) throws Exception {
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
}
