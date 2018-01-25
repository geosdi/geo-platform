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
            took += bulkResponse.getTook().getMillis();
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
