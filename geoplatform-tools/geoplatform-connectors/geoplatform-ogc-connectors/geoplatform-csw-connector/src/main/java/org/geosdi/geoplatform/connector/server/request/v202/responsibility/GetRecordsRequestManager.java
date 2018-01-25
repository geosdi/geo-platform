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
package org.geosdi.geoplatform.connector.server.request.v202.responsibility;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.cql.AreaSearchRequestCQL;
import org.geosdi.geoplatform.connector.server.request.v202.cql.GetRecordsRequestHandlerCQL;
import org.geosdi.geoplatform.connector.server.request.v202.cql.TextSearchRequestCQL;
import org.geosdi.geoplatform.connector.server.request.v202.cql.TimeSearchRequestCQL;
import org.geosdi.geoplatform.connector.server.request.v202.filter.AreaSearchRequestFilter;
import org.geosdi.geoplatform.connector.server.request.v202.filter.GetRecordsRequestHandlerFilter;
import org.geosdi.geoplatform.connector.server.request.v202.filter.TextSearchRequestFilter;
import org.geosdi.geoplatform.connector.server.request.v202.filter.TimeSearchRequestFilter;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GetRecordsRequestManager {

    private GetRecordsRequestHandlerFilter textSearchRequestFilter; // The first ring of the chain Filter
    private GetRecordsRequestHandlerCQL textSearchRequestCQL; // The first ring of the chain CQL

    public GetRecordsRequestManager() {
        this.createChainFilter();
        this.createChainCQL();
    }

    private void createChainFilter() {
        textSearchRequestFilter = new TextSearchRequestFilter();
        GetRecordsRequestHandlerFilter areaSearchRequest = new AreaSearchRequestFilter();
        GetRecordsRequestHandlerFilter timeSearchRequest = new TimeSearchRequestFilter();

        textSearchRequestFilter.setSuccessor(areaSearchRequest);
        areaSearchRequest.setSuccessor(timeSearchRequest);
    }

    private void createChainCQL() {
        textSearchRequestCQL = new TextSearchRequestCQL();
        GetRecordsRequestHandlerCQL areaSearchRequest = new AreaSearchRequestCQL();
        GetRecordsRequestHandlerCQL timeSearchRequest = new TimeSearchRequestCQL();

        textSearchRequestCQL.setSuccessor(areaSearchRequest);
        areaSearchRequest.setSuccessor(timeSearchRequest);
    }

    public void filterGetRecordsRequest(CatalogGetRecordsRequest request, FilterType filterType)
            throws IllegalParameterFault {
        assert (request != null);
        assert (filterType != null);

        // Filter request iff there is a catalog finder setted
        if (request.getCatalogFinder() == null) {
            return;
        }


        if (request.getCatalogFinder() != null) {
            switch (request.getConstraintLanguage()) {
                case FILTER:
                    textSearchRequestFilter.forwardGetRecordsRequest(request, filterType);
                    break;
                case CQL_TEXT:
                    textSearchRequestCQL.forwardGetRecordsRequest(request);
                    break;
                default:
            }
        }
    }
}
