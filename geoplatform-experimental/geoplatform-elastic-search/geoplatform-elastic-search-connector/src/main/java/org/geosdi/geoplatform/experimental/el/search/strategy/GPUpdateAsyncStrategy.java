package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.strategy.function.GPUpdateRequestFunction;
import org.geosdi.geoplatform.experimental.el.search.strategy.task.GPElasticSearchUpdateTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Component(value = "updateAsyncOperation")
class GPUpdateAsyncStrategy extends IGPOperationAsyncStrategy.AbstractOperationAsyncStrategy {

    /**
     * @param <Result>
     * @param page
     * @param
     * @return {@link OperationByPage.IOperationByPageResult}
     */
    @Override
    public <Result extends OperationByPage.IOperationByPageResult, Page extends OperationByPage> Result singleOperation(Page page, ElasticSearchDAO searchDAO) throws Exception {
        SearchRequestBuilder builder = page.buildPage(searchDAO.client()
                .prepareSearch(searchDAO.getIndexName()).setTypes(searchDAO.getIndexType()))
                .setFrom(page.getFrom()).setSize(page.getSize()).setFetchSource(Boolean.TRUE);
        logger.trace("#########################{} ----------> internalOperation#Builder : \n{}\n",
                this, builder.toString());
        SearchResponse searchResponse = builder.execute().actionGet();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }

        BulkRequestBuilder bulkRequest = searchDAO.client().prepareBulk();
        stream(searchResponse.getHits().getHits()).map(searchHit -> searchDAO.readDocument(searchHit))
                .map(page::update)
                .map(new GPUpdateRequestFunction(searchDAO))
                .filter(updateRequest -> updateRequest != null)
                .forEach(updateRequest -> bulkRequest.add(updateRequest));
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            throw new IllegalStateException(bulkResponse.buildFailureMessage());
        }
        return (Result) new OperationByPage.OperationByPageResult(bulkResponse.getItems().length, bulkResponse.getTook());
    }


    /**
     * @param <Result>
     * @param page
     * @param
     * @return {@link OperationByPage.IOperationByPageResult}
     */
    @Override
    public <Result extends OperationByPage.IOperationByPageResult, Page extends OperationByPage> Result parallerOperation(Page page, ElasticSearchDAO searchDAO) throws Exception {
        Integer numberOfTasks = super.calculateNumberOfTasks(page.getSize());
        logger.debug("###########################{} ---------> calculate {} tasks", this, numberOfTasks);
        List<GPElasticSearchUpdateTask> tasks = IntStream
                .iterate(0, n -> n + 1).limit(numberOfTasks).boxed()
                .map(n -> new GPElasticSearchUpdateTask(searchDAO, page, n)).collect(Collectors.toList());
        List<Future<BulkResponse>> futureBulks = searchDAO.executor().invokeAll(tasks);
        Integer elementsUpdated = 0;
        Long took = 0l;
        for (Future<BulkResponse> futureBulk : futureBulks) {
            BulkResponse bulkResponse = futureBulk.get();
            elementsUpdated += bulkResponse.getItems().length;
            took += bulkResponse.getTook().getMillis();
        }
        return (Result) new OperationByPage.OperationByPageResult(elementsUpdated, new TimeValue(took, TimeUnit.MILLISECONDS));
    }

    @Override
    public IGPOperationAsyncType.OperationAsyncType getStrateyType() {
        return IGPOperationAsyncType.OperationAsyncType.UPDATE;
    }
}