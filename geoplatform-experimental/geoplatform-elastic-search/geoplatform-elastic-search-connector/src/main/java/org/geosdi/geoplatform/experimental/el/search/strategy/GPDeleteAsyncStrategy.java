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
package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.strategy.task.GPElasticSearchDeleteTask;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType.DELETE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Component(value = "deleteAsyncOperation")
class GPDeleteAsyncStrategy extends IGPOperationAsyncStrategy.AbstractOperationAsyncStrategy {


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
                .setFrom(page.getFrom()).setSize(page.getSize()).setFetchSource(Boolean.FALSE);
        logger.trace("#########################{} ----------> internalOperation#Builder : \n{}\n",
                this, builder.toString());
        SearchResponse searchResponse = builder.execute().actionGet();
        if (searchResponse.status() != RestStatus.OK) {
            throw new IllegalStateException("Problem in Search : " + searchResponse.status());
        }
        BulkRequestBuilder bulkRequest = searchDAO.client().prepareBulk();
        Arrays.stream(searchResponse.getHits().getHits())
                .map(searchHit -> new DeleteRequest(searchDAO.getIndexName(), searchDAO.getIndexType(),
                        searchHit.getId()))
                .filter(deleteRequest -> deleteRequest != null)
                .forEach(deleteRequest -> bulkRequest.add(deleteRequest));
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
        List<GPElasticSearchDeleteTask> tasks = IntStream
                .iterate(0, n -> n + 1).limit(numberOfTasks).boxed()
                .map(n -> new GPElasticSearchDeleteTask(searchDAO, page, n)).collect(Collectors.toList());
        List<Future<BulkResponse>> futureBulks = searchDAO.executor().invokeAll(tasks);
        Integer elementsDeleted = 0;
        Long took = 0l;
        for (Future<BulkResponse> futureBulk : futureBulks) {
            BulkResponse bulkResponse = futureBulk.get();
            elementsDeleted += bulkResponse.getItems().length;
            took += bulkResponse.getTook().getMillis();
        }
        return (Result) new OperationByPage.OperationByPageResult(elementsDeleted, new TimeValue(took, TimeUnit.MILLISECONDS));
    }

    @Override
    public IGPOperationAsyncType.OperationAsyncType getStrateyType() {
        return DELETE;
    }
}
