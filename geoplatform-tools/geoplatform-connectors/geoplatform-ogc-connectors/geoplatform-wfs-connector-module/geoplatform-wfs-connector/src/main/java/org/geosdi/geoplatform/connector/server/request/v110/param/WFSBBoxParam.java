/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110.param;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.filter.v110.BBOXType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;
import org.geosdi.geoplatform.xml.gml.v311.DirectPositionType;
import org.geosdi.geoplatform.xml.gml.v311.EnvelopeType;
import org.geosdi.geoplatform.xml.wfs.v110.QueryType;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.v110.param.WFSFeatureIDsParam.filterFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class WFSBBoxParam extends WFSBaseGetFeatureRequestParam {

    private static final org.geosdi.geoplatform.xml.gml.v311.ObjectFactory gmlFactory = new org.geosdi.geoplatform.xml.gml.v311.ObjectFactory();

    WFSBBoxParam() {
    }

    /**
     * @param theRequest
     * @param theQueryType
     * @throws Exception
     */
    @Override
    protected void internalApply(WFSGetFeatureRequest theRequest, QueryType theQueryType) throws Exception {
        logger.debug("###################Executing {}#internalApply.", this.toParamName());
        FilterType filter = theQueryType.getFilter();
        if (filter == null) {
            filter = new FilterType();
            theQueryType.setFilter(filter);
        }
        JAXBElement<BBOXType> bboxType = this.createAreaOperator(theRequest);
        if (theRequest.isSetQueryDTO()) {
            logger.trace("########################{}, QueryDTOs are present so will be setted a SpatialOps.", this.toParamName());
            filter.setSpatialOps(bboxType);
        } else {
            logger.trace("########################{}, QueryDTOs are not present so i will analize the Cql Filters Presence.", this.toParamName());
            List<JAXBElement<?>> cqlFilterElements = Lists.newArrayList();
            if (filter.isSetComparisonOps()) {
                cqlFilterElements.add(filter.getComparisonOps());
            }
            if (filter.isSetLogicOps()) {
                cqlFilterElements.add(filter.getLogicOps());
            }
            if (cqlFilterElements.isEmpty()) {
                logger.trace("########################{}, cql Filters are not present so will be setted a SpatialOps.", this.toParamName());
                filter.setSpatialOps(bboxType);
            } else {
                logger.trace("########################{}, cql Filters are present so will be created an BinaryLogicOpType AND.", this.toParamName());
                BinaryLogicOpType and = new BinaryLogicOpType();
                cqlFilterElements.add(bboxType);
                and.setComparisonOpsOrSpatialOpsOrLogicOps(cqlFilterElements);
                filter.setLogicOps(filterFactory.createAnd(and));
            }
        }
    }

    /**
     * @param theRequest
     * @return {@link Boolean}
     */
    @Override
    protected boolean canBeApplyParam(@Nonnull(when = NEVER) WFSGetFeatureRequest theRequest) {
        checkArgument(theRequest != null, "The Parameter WFSGetFeatureRequest must not be null.");
        return theRequest.isSetBBox();
    }

    /**
     * @param theRequest
     * @return {@link JAXBElement<BBOXType>}
     */
    JAXBElement<BBOXType> createAreaOperator(WFSGetFeatureRequest theRequest) {
        BBox bbox = theRequest.getBBox();
        logger.debug("#######################BBOX : {}\n.", bbox);
        BBOXType bBoxType = new BBOXType();
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(asList(theRequest.getGeometryName()));
        bBoxType.setPropertyName(propertyNameType);
        EnvelopeType envelope = this.createEnvelope(bbox);
        if (theRequest.isSetSRS()) {
            envelope.setSrsName(theRequest.getSRS());
        }
        bBoxType.setEnvelope(gmlFactory.createEnvelope(envelope));
        return filterFactory.createBBOX(bBoxType);
    }

    /**
     * @param bbox
     * @return {@link EnvelopeType}
     */
    EnvelopeType createEnvelope(BBox bbox) {
        EnvelopeType envelope = new EnvelopeType();
        DirectPositionType lower = new DirectPositionType();
        lower.setValue(asList(bbox.getMinX(), bbox.getMinY()));
        envelope.setLowerCorner(lower);
        DirectPositionType upper = new DirectPositionType();
        upper.setValue(asList(bbox.getMaxX(), bbox.getMaxY()));
        envelope.setUpperCorner(upper);
        return envelope;
    }
}