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
package org.geosdi.geoplatform.experimental.el.query.rest.service;

import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.delegare.IGPElasticSearchQueryDelegate;
import org.geosdi.geoplatform.experimental.el.query.rest.request.GPElasticSearchQueryExecutionRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPElasticSearchQueryService<QUERY extends GPElasticSearchQuery,
        QUERY_DAO extends IGPElasticSearchQueryDAO<QUERY>, REQUEST extends GPElasticSearchQueryExecutionRequest,
        QUERY_DELEGATE extends IGPElasticSearchQueryDelegate<QUERY, QUERY_DAO, REQUEST>>
        implements GPElasticSearchQueryServiceSupport<QUERY, QUERY_DAO, QUERY_DELEGATE, REQUEST> {

    protected QUERY_DELEGATE queryDelegate;

    /**
     * @param from
     * @param size
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response findGPElasticSearchQuery(Integer from, Integer size) throws Exception {
        return Response.ok(this.queryDelegate.findQuery(from, size)).build();
    }

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response findGPElasticSearchQueryByCreationDate(Integer from, Integer size, Long fromDate, Long toDate)
            throws Exception {
        return Response.ok(this.queryDelegate.findQueryByCreationDate(from, size, fromDate, toDate)).build();
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    public abstract Response executeGPElasticSearchQuery(REQUEST request) throws Exception;

    /**
     * @param theQueryDelegate
     */
    @Autowired
    @Override
    public void setQueryDelegate(QUERY_DELEGATE theQueryDelegate) {
        this.queryDelegate = theQueryDelegate;
    }
}
