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
package org.geosdi.geoplatform.connector.server.request.v202.filter;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GPGetRecordsHandlerType;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.responce.TimeInfo;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.xml.bind.JAXBElement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GetRecordsHandlerType.TIME;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TimeSearchRequestFilter extends GetRecordsRequestHandlerFilter {

    private final static String TEMP_BEGIN = "TempExtent_begin";
    private final static String TEMP_END = "TempExtent_end";
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * @param theRequest
     * @param theFilterType
     * @param theFilterPredicates
     * @throws IllegalParameterFault
     */
    @Override
    protected void processGetRecordsRequest(CatalogGetRecordsRequest theRequest, FilterType theFilterType, List<JAXBElement<?>> theFilterPredicates) throws IllegalParameterFault {
        logger.debug("##############################Called {}#processGetRecordsRequest.\n", this);
        TimeInfo timeInfo = theRequest.getCatalogFinder().getTimeInfo();
        if (timeInfo != null && timeInfo.isActive()) {
            Date startDate = timeInfo.getStartDate();
            Date endDate = timeInfo.getEndDate();
            logger.debug("\n######################### From: {} - To: {}.\n", startDate, endDate);
            List<JAXBElement<?>> timePredicate = this.createFilterTimePredicate(startDate, endDate);
            logger.trace("\n######################### Time filter: \"{}\".\n", timePredicate);
            theFilterPredicates.addAll(timePredicate);
        }
    }

    /**
     * @return {@link GPGetRecordsHandlerType}
     */
    @Override
    public GPGetRecordsHandlerType getType() {
        return TIME;
    }

    /**
     * @param startDate
     * @param endDate
     * @return {@link List<JAXBElement<?>}
     */
    private List<JAXBElement<?>> createFilterTimePredicate(Date startDate, Date endDate) {
        BinaryComparisonOpType begin = this.createBinaryComparisonOpType(TEMP_BEGIN, formatter.format(startDate));
        BinaryComparisonOpType end = this.createBinaryComparisonOpType(TEMP_END, formatter.format(endDate));
        List<JAXBElement<?>> timePredicate = new ArrayList(2);
        timePredicate.add(filterFactory.createPropertyIsGreaterThanOrEqualTo(begin));
        timePredicate.add(filterFactory.createPropertyIsLessThanOrEqualTo(end));
        return timePredicate;
    }
}