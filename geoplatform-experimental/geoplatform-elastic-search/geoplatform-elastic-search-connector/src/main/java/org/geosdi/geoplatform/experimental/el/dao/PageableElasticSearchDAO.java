package org.geosdi.geoplatform.experimental.el.dao;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;

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
     * <p>@Todo Try to improve this code with Pagination Concept</p>
     *
     * @param page
     * @throws Exception
     */
    @Override
    public <P extends DeleteByPage> BulkResponse deleteByPage(P page) throws Exception {
        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
                .prepareSearch(getIndexName()).setTypes(getIndexType()));
        logger.trace("#########################deleteByPage#Builder : \n{}\n", builder.toString());
        SearchResponse searchResponse = builder.setSize(500)
                .setScroll(new TimeValue(10000)).setNoFields().execute().actionGet();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        BulkRequestBuilder bulkRequest = this.elastichSearchClient.prepareBulk();
        while (true) {
            Stream.of(searchResponse.getHits().hits())
                    .map(searchHit -> new DeleteRequest(getIndexName(), getIndexType(), searchHit.getId()))
                    .filter(deleteRequest -> deleteRequest != null)
                    .forEach(deleteRequest -> bulkRequest.add(deleteRequest));
            searchResponse = this.elastichSearchClient.prepareSearchScroll(searchResponse.getScrollId())
                    .setScroll(new TimeValue(10000)).execute().actionGet();
            if (searchResponse.getHits().hits().length == 0) {
                break;
            }
        }
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return bulkResponse;
    }

    /**
     * @param page
     * @return {@link CompletableFuture<BulkResponse>}
     * @throws Exception
     */
    @Override
    public <P extends DeleteByPage> CompletableFuture<BulkResponse> deleteByPageAsync(P page) throws Exception {
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

//    /**
//     * @param page
//     * @return {@link BulkResponse}
//     * @throws Exception
//     */
//    @Override
//    public <P extends Page> BulkResponse deleteWithBulkByPage(P page) throws Exception {
//        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
//        Long count = super.count(page.buildPage(this.elastichSearchClient
//                .prepareSearch(getIndexName()).setTypes(getIndexType())));
//        SearchRequestBuilder builder = page.buildPage(this.elastichSearchClient
//                .prepareSearch(getIndexName())
//                .setTypes(getIndexType()).setFrom(0).setSize(count.intValue()).setNoFields());
//        logger.trace("#########################deleteByPage#Builder : \n{}\n", builder.toString());
//        SearchResponse searchResponse = builder.execute().actionGet();
//        if (searchResponse.status() != RestStatus.OK) {
//            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
//        }
//        BulkRequestBuilder bulkRequest = this.elastichSearchClient.prepareBulk();
//        Stream.of(searchResponse.getHits().hits())
//                .map(searchHit -> new DeleteRequest(getIndexName(), getIndexType(), searchHit.getId()))
//                .filter(deleteRequest -> deleteRequest != null)
//                .forEach(deleteRequest -> bulkRequest.add(deleteRequest));
//        BulkResponse bulkResponse = bulkRequest.get();
//        if (bulkResponse.hasFailures()) {
//            throw new IllegalStateException(bulkResponse.buildFailureMessage());
//        }
//        return bulkResponse;
//    }
}
