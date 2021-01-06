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
package org.geosdi.geoplatform.connector.server.request.v202.cql;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GPGetRecordsHandlerType;
import org.geosdi.geoplatform.gui.responce.TimeInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GetRecordsHandlerType.TIME;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TimeSearchRequestCQL extends GetRecordsRequestHandlerCQL {

    private final static String TEMP_BEGIN = "TempExtent_begin";
    private final static String TEMP_END = "TempExtent_end";
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * @param request
     * @throws Exception
     */
    @Override
    protected void processGetRecordsRequest(CatalogGetRecordsRequest request) throws Exception {
        checkArgument(request != null, "The Parameter request must not be null.");
        logger.debug("##########################Called {}#processGetRecordsRequest\n", this);
        TimeInfo timeInfo = request.getCatalogFinder().getTimeInfo();
        if (timeInfo != null && timeInfo.isActive()) {
            Date startDate = timeInfo.getStartDate();
            Date endDate = timeInfo.getEndDate();
            logger.debug("##################### From: {} - To: {}\n", startDate, endDate);
            String timeConstraint = this.createCQLTimePredicate(startDate, endDate);
            logger.trace("##################### Time CQL constraint: \"{}\"\n", timeConstraint);
            super.addCQLConstraint(request, timeConstraint);
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
     * Create a string like this:
     * <p>
     * TempExtent_begin AFTER 2006-11-30T01:30:00Z AND TempExtent_end BEFORE
     * 2006-12-31T01:30:00Z
     */
    private String createCQLTimePredicate(Date startDate, Date endDate) {
        StringBuilder str = new StringBuilder();
        str.append(TEMP_BEGIN).append(" AFTER ").append(formatter.format(startDate));
        str.append(" AND ");
        str.append(TEMP_END).append(" BEFORE ").append(formatter.format(endDate));
        return str.toString();
    }
}