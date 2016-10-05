package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.IDeleteByPageResult;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.geosdi.geoplatform.experimental.el.search.delete.responsibility.GPElasticSearchDeleteHandler.GPElasticSearchDeleteHandlerType.PARALLEL_DELETE_TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPParallelDeleteHandler extends AbstractParallelDeleteHandler {

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
        Integer numberOfTasks = super.calculateNumberOfTasks(page.getSize());
        logger.debug("###########################{} ---------> calculate {} tasks", this, numberOfTasks);
        List<GPElasticSearchDeleteTask> tasks = IntStream
                .iterate(0, n -> n + 1).limit(numberOfTasks).boxed()
                .map(n -> new GPElasticSearchDeleteTask(searchDAO, page, n)).collect(Collectors.toList());
        List<Future<BulkResponse>> futureBulks = searchDAO.executor().invokeAll(tasks);
        Integer elementsDeleted = 0;
        Long took = 0l;
        for (Future<BulkResponse> futureBulk : futureBulks) {
            BulkResponse bulkResponse = futureBulk.get();
            elementsDeleted += bulkResponse.getItems().length;
            took += bulkResponse.getTookInMillis();
        }
        return (Result) new DeleteByPage.DeleteByPageResult(elementsDeleted, new TimeValue(took, TimeUnit.MILLISECONDS));
    }

    /**
     * @param page
     * @return {@link Boolean}
     */
    @Override
    protected <Page extends DeleteByPage> Boolean canDelete(Page page) {
        return ((page != null) && (page.getSize() > PAGE_SIZE_LIMIT));
    }

    /**
     * @return {@link TYPE}
     */
    @Override
    public <TYPE extends IGPElasticSearchDeleteHandlerType> TYPE getDeleteType() {
        return (TYPE) PARALLEL_DELETE_TYPE;
    }
}
