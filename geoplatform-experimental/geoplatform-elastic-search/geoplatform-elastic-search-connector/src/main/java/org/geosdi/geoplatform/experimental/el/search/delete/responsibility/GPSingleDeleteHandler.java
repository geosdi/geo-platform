/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
                .setFrom(page.getFrom()).setSize(page.getSize()).setFetchSource(Boolean.FALSE);
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
