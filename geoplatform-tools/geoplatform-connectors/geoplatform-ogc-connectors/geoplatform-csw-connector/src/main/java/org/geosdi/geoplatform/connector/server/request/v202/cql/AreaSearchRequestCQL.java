/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.AreaInfo.AreaSearchType;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class AreaSearchRequestCQL extends GetRecordsRequestHandlerCQL {

    private final static String BOUNDING_BOX = "ows:BoundingBox";
    private final static String CONTAINS = "CONTAINS";
    private final static String EQUALS = "EQUALS";
    private final static String DISJOINT = "DISJOINT";
    private final static String INTERSECTS = "INTERSECTS";

    @Override
    protected void processGetRecordsRequest(CatalogGetRecordsRequest request)
            throws IllegalParameterFault {
        logger.debug("Process...");

        AreaInfo areaInfo = request.getCatalogFinder().getAreaInfo();
        if (areaInfo != null && areaInfo.isActive()) {
            AreaSearchType areaSearchType = areaInfo.getAreaSearchType();
            BBox bBox = areaInfo.getBBox();
            logger.debug("\n+++ Search Type: {} +++", areaSearchType);
            logger.debug("\n+++ {} +++", bBox);

            String areaConstraint = this.createCQLAreaPredicate(areaSearchType, bBox);

            logger.trace("\n+++ Area CQL constraint: \"{}\" +++", areaConstraint);
            super.addCQLConstraint(request, areaConstraint);
        }
    }

    private String createCQLAreaPredicate(AreaSearchType areaSearchType, BBox bBox) {
        StringBuilder constraint = new StringBuilder();

        switch (areaSearchType) {
            case ENCLOSES:
                constraint.append(CONTAINS);
                break;

            case IS:
                constraint.append(EQUALS);
                break;

            case OUTSIDE:
                constraint.append(DISJOINT);
                break;

            case OVERLAP:
                constraint.append(INTERSECTS);
                break;
        }

        constraint.append("(");
        constraint.append(BOUNDING_BOX).append(",");
        constraint.append(bBox.getMinX()).append(",");
        constraint.append(bBox.getMinY()).append(",");
        constraint.append(bBox.getMaxX()).append(",");
        constraint.append(bBox.getMaxY()).append(",");
        constraint.append(")");

        return constraint.toString();
    }
}
