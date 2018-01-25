/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
                .setFrom(this.start).setSize(PAGE_SIZE_LIMIT).setFetchSource(Boolean.FALSE);
        logger.trace("#########################{} ----------> call#Builder : \n{}\n",
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
