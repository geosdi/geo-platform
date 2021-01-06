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
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.AreaInfo.AreaSearchType;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.filter.v110.BinarySpatialOpType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;
import org.geosdi.geoplatform.xml.filter.v110.UnaryLogicOpType;
import org.geosdi.geoplatform.xml.gml.v311.DirectPositionType;
import org.geosdi.geoplatform.xml.gml.v311.EnvelopeType;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GetRecordsHandlerType.SPATIAL;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class AreaSearchRequestFilter extends GetRecordsRequestHandlerFilter {

    private final static String BOUNDING_BOX = "ows:BoundingBox";

    /**
     * @param theRequest
     * @param theFilterType
     * @param theFilterPredicates
     * @throws IllegalParameterFault
     */
    @Override
    protected void processGetRecordsRequest(CatalogGetRecordsRequest theRequest, FilterType theFilterType, List<JAXBElement<?>> theFilterPredicates) throws IllegalParameterFault {
        logger.debug("#####################Called {}#processGetRecordsRequest", this);
        AreaInfo areaInfo = theRequest.getCatalogFinder().getAreaInfo();
        if (areaInfo != null && areaInfo.isActive()) {
            AreaSearchType areaSearchType = areaInfo.getAreaSearchType();
            BBox bbox = areaInfo.getBBox();
            logger.debug("############################ Search Type: {}.\n", areaSearchType);
            logger.debug("############################ Bbox : {}.\n", bbox);
            List<JAXBElement<?>> areaPredicate = this.createFilterAreaPredicate(areaSearchType, bbox);
            logger.trace("\n############################ Time filter: \"{}\"\n.", areaPredicate);
            theFilterPredicates.addAll(areaPredicate);
        }
    }

    /**
     * @return {@link GPGetRecordsHandlerType}
     */
    @Override
    public GPGetRecordsHandlerType getType() {
        return SPATIAL;
    }

    /**
     * @param areaSearchType
     * @param bBox
     * @return {@link List<JAXBElement<?>}
     */
    private List<JAXBElement<?>> createFilterAreaPredicate(AreaSearchType areaSearchType, BBox bBox) {
        List<JAXBElement<?>> areaPredicate = new ArrayList(2);
        BinarySpatialOpType binarySpatial = new BinarySpatialOpType();
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.<Object>asList(BOUNDING_BOX));
        binarySpatial.setPropertyName(propertyNameType);
        EnvelopeType envelope = this.createEnvelope(bBox);
        binarySpatial.setEnvelope(gmlFactory.createEnvelope(envelope));
        switch (areaSearchType) {
            case ENCLOSES:
                areaPredicate.add(filterFactory.createContains(binarySpatial));
                break;
            case IS:
                areaPredicate.add(filterFactory.createEquals(binarySpatial));
                break;
            case OUTSIDE:
                // Workaround for GeoNetwork bug: DISJOINT = NOT(INTERSECTS)
                UnaryLogicOpType unary = new UnaryLogicOpType();
                unary.setSpatialOps(filterFactory.createIntersects(binarySpatial));
                areaPredicate.add(filterFactory.createNot(unary));
                // TODO Use DISJOINT spatial operator
//                areaPredicate.add(filterFactory.createDisjoint(binarySpatial));
                break;
            case OVERLAP:
                areaPredicate.add(filterFactory.createIntersects(binarySpatial));
                break;
        }
        return areaPredicate;
    }

    /**
     * @param bbox
     * @return {@link EnvelopeType}
     */
    private EnvelopeType createEnvelope(BBox bbox) {
        EnvelopeType envelope = new EnvelopeType();
        DirectPositionType lower = new DirectPositionType();
        lower.setValue(Arrays.asList(bbox.getMinX(), bbox.getMinY()));
        envelope.setLowerCorner(lower);
        DirectPositionType upper = new DirectPositionType();
        upper.setValue(Arrays.asList(bbox.getMaxX(), bbox.getMaxY()));
        envelope.setUpperCorner(upper);
        return envelope;
    }
}