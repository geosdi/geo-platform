/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBElement;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType.ALL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class AndOperatorHandler extends LogicOperatorHandler {

    AndOperatorHandler() {
        super.setSuccessor(new NotOperatorHandler());
    }

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    @Override
    public void buildLogicFilterOperator(@Nonnull(when = NEVER) QueryDTO queryDTO, @Nullable FilterType filter) throws IllegalStateException {
        checkArgument(queryDTO != null, "The Parameter queryDTO must not be null.");
        if (super.canBuildLogicOperator(queryDTO.getMatchOperator())) {
            processQueryRestrictions(filter, queryDTO.getQueryRestrictionList());
        } else {
            super.forwardBuildLogicFilterOperator(queryDTO, filter);
        }
    }

    /**
     * @param filter
     * @param queryRestrictionDTOs
     */
    @Override
    protected void processQueryRestrictions(FilterType filter, List<QueryRestrictionDTO> queryRestrictionDTOs) {
        logger.debug("################### {} Processing............\n", getFilterName());
        List<JAXBElement<?>> elements = super.buildJAXBElementList(queryRestrictionDTOs);
        logger.debug("##################{} builds : {} ".concat((elements.size() > 1 ? "elements" : "element.\n")),
                getFilterName(), elements.size());
        List<JAXBElement<?>> cqlFilterElements = Lists.newArrayList();
        if (filter.isSetComparisonOps()) {
            cqlFilterElements.add(filter.getComparisonOps());
        }
        if (filter.isSetLogicOps()) {
            cqlFilterElements.add(filter.getLogicOps());
        }
        if (elements.size() == 1) {
            if (filter.isSetSpatialOps()) {
                elements.add(filter.getSpatialOps());
                filter.setSpatialOps(null);
                BinaryLogicOpType and = new BinaryLogicOpType();
                if (cqlFilterElements.isEmpty()) {
                    and.setComparisonOpsOrSpatialOpsOrLogicOps(elements);
                } else {
                    cqlFilterElements.addAll(elements);
                    and.setComparisonOpsOrSpatialOpsOrLogicOps(cqlFilterElements);
                }
                filter.setLogicOps(filterFactory.createAnd(and));
            } else {
                if (cqlFilterElements.isEmpty()) {
                    JAXBElement<ComparisonOpsType> opsTypeJAXBElement = (JAXBElement<ComparisonOpsType>) elements.get(0);
                    filter.setComparisonOps(opsTypeJAXBElement);
                } else {
                    BinaryLogicOpType and = new BinaryLogicOpType();
                    cqlFilterElements.add(elements.get(0));
                    and.setComparisonOpsOrSpatialOpsOrLogicOps(cqlFilterElements);
                    filter.setLogicOps(filterFactory.createAnd(and));
                }
            }
        } else if (elements.size() > 1) {
            if (filter.isSetSpatialOps()) {
                elements.add(filter.getSpatialOps());
                filter.setSpatialOps(null);
            }
            BinaryLogicOpType and = new BinaryLogicOpType();
            if (cqlFilterElements.isEmpty()) {
                and.setComparisonOpsOrSpatialOpsOrLogicOps(elements);
            } else {
                cqlFilterElements.addAll(elements);
                and.setComparisonOpsOrSpatialOpsOrLogicOps(cqlFilterElements);
            }
            filter.setLogicOps(filterFactory.createAnd(and));
        }
    }

    /**
     * <p>We have three Operator type see {@link OperatorType}</p>
     *
     * @return the Operator
     */
    @Override
    protected String getOperatorValue() {
        return ALL.name();
    }
}