package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.geosdi.geoplatform.experimental.el.search.delete.responsibility.AbstractParallelDeleteHandler.PAGE_SIZE_LIMIT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPElasticSearchDeleteTask implements Callable<BulkResponse> {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchDeleteTask.class);
    //
    private final ElasticSearchDAO searchDAO;
    private final DeleteByPage page;
    private final int start;
    private final int taskNumber;

    public GPElasticSearchDeleteTask(ElasticSearchDAO theSearchDAO, DeleteByPage thePage,
            int taskNumber) {
        Preconditions.checkNotNull(theSearchDAO, "Parameter SearchDAO must not be null.");
        Preconditions.checkNotNull(thePage, "Parameter Page must not be null.");
        this.searchDAO = theSearchDAO;
        this.page = thePage;
        this.taskNumber = taskNumber;
        this.start = (this.page.getFrom() + (this.taskNumber * PAGE_SIZE_LIMIT));
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public BulkResponse call() throws Exception {
        logger.debug("########################Execution of {}\n", this);
        SearchRequestBuilder builder = page.buildPage(searchDAO.client()
                .prepareSearch(searchDAO.getIndexName()).setTypes(searchDAO.getIndexType()))
                .setFrom(this.start).setSize(PAGE_SIZE_LIMIT).setNoFields();
        logger.trace("#########################{} ----------> call#Builder : \n{}\n",
                this, builder.toString());
        SearchResponse searchResponse = builder.execute().actionGet();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        BulkRequestBuilder bulkRequest = searchDAO.client().prepareBulk();
        Arrays.stream(searchResponse.getHits().hits())
                .map(searchHit -> new DeleteRequest(searchDAO.getIndexName(), searchDAO.getIndexType(),
                        searchHit.getId()))
                .filter(deleteRequest -> deleteRequest != null)
                .forEach(deleteRequest -> bulkRequest.add(deleteRequest));
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return bulkResponse;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + " #taskNumber : " + this.taskNumber
                + ", @start : " + this.start
                + "}";
    }
}
