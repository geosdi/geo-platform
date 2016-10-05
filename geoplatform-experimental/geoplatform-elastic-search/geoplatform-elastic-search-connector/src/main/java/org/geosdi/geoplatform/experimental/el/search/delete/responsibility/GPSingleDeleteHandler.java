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
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.DeleteByPageResult;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.IDeleteByPageResult;

import java.util.Arrays;

import static org.geosdi.geoplatform.experimental.el.search.delete.responsibility.AbstractParallelDeleteHandler.PAGE_SIZE_LIMIT;
import static org.geosdi.geoplatform.experimental.el.search.delete.responsibility.GPElasticSearchDeleteHandler.GPElasticSearchDeleteHandlerType.NOT_PARALLEL_DELETE_TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPSingleDeleteHandler extends GPAbstractDeleteHandler<ElasticSearchDAO> {

    public GPSingleDeleteHandler() {
        super.setSuccessor(new GPParallelDeleteHandler());
    }

    /**
     * @param page
     * @param searchDAO
     * @return {@link Result}
     * @throws Exception
     */
    @Override
    public <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result delete(Page page,
            ElasticSearchDAO searchDAO) throws Exception {
        return (canDelete(page) ? internalDelete(page, searchDAO) : super.forwardDelete(page, searchDAO));
    }

    /**
     * @param page
     * @param searchDAO
     * @return {@link Result}
     * @throws Exception
     */
    @Override
    protected <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result internalDelete(Page page,
            ElasticSearchDAO searchDAO) throws Exception {
        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
        Preconditions.checkNotNull(searchDAO, "Parameter SearchDAO must not be null.");
        SearchRequestBuilder builder = page.buildPage(searchDAO.client()
                .prepareSearch(searchDAO.getIndexName()).setTypes(searchDAO.getIndexType()))
                .setFrom(page.getFrom()).setSize(page.getSize()).setNoFields();
        logger.trace("#########################{} ----------> internalDelete#Builder : \n{}\n",
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
        return (Result) new DeleteByPageResult(bulkResponse.getItems().length, bulkResponse.getTook());
    }

    /**
     * @param page
     * @return {@link Boolean}
     */
    @Override
    protected <Page extends DeleteByPage> Boolean canDelete(Page page) {
        return ((page.getSize() != null) && ((page.getSize() > 0) && (page.getSize() <= PAGE_SIZE_LIMIT)));
    }

    /**
     * @return {@link TYPE}
     */
    @Override
    public <TYPE extends IGPElasticSearchDeleteHandlerType> TYPE getDeleteType() {
        return (TYPE) NOT_PARALLEL_DELETE_TYPE;
    }
}
