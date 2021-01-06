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
package org.geosdi.geoplatform.experimental.el.query.rest.delegare;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult;
import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.mediator.GPElasticSearchQueryMediator;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;
import org.geosdi.geoplatform.experimental.el.query.rest.response.GPElasticSearchQueryExecutorStore;
import org.geosdi.geoplatform.experimental.el.query.rest.response.GPElasticSearchQueryStore;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryDelegate<QUERY extends GPElasticSearchQuery,
        QUERY_DAO extends IGPElasticSearchQueryDAO<QUERY>, REQUEST extends GPElasticSearchQueryExecutionRequest>
        implements IGPElasticSearchQueryDelegate<QUERY, QUERY_DAO, REQUEST> {

    @GeoPlatformLog
    protected Logger logger;
    //
    protected QUERY_DAO queryDAO;
    @Resource(name = "elasticSearchQueryMediator")
    protected GPElasticSearchQueryMediator queryMediator;

    /**
     * @param from
     * @param size
     * @return {@link GPElasticSearchQueryStore <QUERY>}
     * @throws Exception
     */
    @Override
    public GPElasticSearchQueryStore<QUERY> findQuery(Integer from, Integer size) throws Exception {
        logger.debug("###########################{}#findGPElasticSearchQuery is executing with parameters from : {}, " +
                "size : {}\n", getClass().getSimpleName(), from, size);
        IPageResult<QUERY> pageResult = this.queryDAO.findQuery(from, size);
        return new GPElasticSearchQueryStore<>(pageResult.getTotal(), pageResult.getResults());
    }

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link GPElasticSearchQueryStore<QUERY>}
     * @throws Exception
     */
    @Override
    public GPElasticSearchQueryStore<QUERY> findQueryByCreationDate(Integer from, Integer size,
            Long fromDate, Long toDate) throws Exception {
        logger.debug("###########################{}#findGPElasticSearchQueryByCreationDate is executing with parameters from : {}, " +
                "size : {}, fromDate : {}, toDate : {}\n", getClass().getSimpleName(), from, size, fromDate, toDate);
        IPageResult<QUERY> pageResult = this.queryDAO.findByQueryCreationDate(new DateTime(fromDate),
                new DateTime(toDate), from, size);
        return new GPElasticSearchQueryStore<>(pageResult.getTotal(), pageResult.getResults());
    }

    /**
     * @param request
     * @return {@link GPElasticSearchQueryStore <QUERY>}
     * @throws Exception
     */
    @Override
    public <R> GPElasticSearchQueryExecutorStore<R> executeGPElasticSearchQuery(REQUEST request)
            throws Exception {
        logger.debug("###########################{}#executeGPElasticSearchQuery is executing with parameters " +
                "request : {}\n", getClass().getSimpleName(), request);
        QUERY gpElasticSearchQuery = this.queryDAO.find(request.getQueryID());
        if (gpElasticSearchQuery == null)
            throw new ResourceNotFoundFault("The ElasticSearchQuery with ID : "
                    + request.getQueryID() + " doesn't exist.");
        IPageResult pageResult = this.queryMediator.executeQueryColleague(gpElasticSearchQuery.getIndexSettings(),
                gpElasticSearchQuery.getQueryTemplate(), request.getQueryTemplateParameters());
        return new GPElasticSearchQueryExecutorStore<>(pageResult.getTotal(), pageResult.getResults());
    }

    /**
     * @param theQueryDAO
     */
    @Autowired
    @Override
    public void setGPElasticSearchQueryDAO(QUERY_DAO theQueryDAO) {
        this.queryDAO = theQueryDAO;
    }
}
