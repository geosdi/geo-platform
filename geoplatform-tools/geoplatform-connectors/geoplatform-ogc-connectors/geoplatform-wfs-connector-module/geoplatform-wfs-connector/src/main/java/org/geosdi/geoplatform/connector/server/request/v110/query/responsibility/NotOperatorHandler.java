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
package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.UnaryLogicOpType;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType.NONE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class NotOperatorHandler extends LogicOperatorHandler {

    protected NotOperatorHandler() {
    }

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    @Override
    public void buildLogicFilterOperator(QueryDTO queryDTO, FilterType filter) throws IllegalStateException {
        if (super.canBuildLogicOperator(queryDTO.getMatchOperator())) {
            this.processQueryRestrictions(filter, queryDTO.getQueryRestrictionList());
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
        logger.debug("##################{} builds : {} " + (elements.size() > 1 ? "elements" : "element") + "\n",
                getFilterName(), elements.size());
        UnaryLogicOpType unaryLogicOpType = ((elements.size() == 1)
                ? createComparisonOps(elements.get(0), filter) : createLogicOps(elements, filter));
        filter.setLogicOps(filterFactory.createNot(unaryLogicOpType));
    }

    /**
     * <p>We have three Operator type see {@link LogicOperatorType}</p>
     *
     * @return the Operator
     */
    @Override
    protected String getOperatorValue() {
        return NONE.name();
    }

    /**
     * @param element
     * @return {@link UnaryLogicOpType}
     */
    protected UnaryLogicOpType createComparisonOps(@Nonnull(when = NEVER) JAXBElement element, @Nonnull(when = NEVER) FilterType filter) {
        checkArgument(element != null, "The Parameter element must not be null.");
        checkArgument(filter != null, "The Parameter filter must not be null.");
        if (filter.isSetSpatialOps()) {
            List<JAXBElement<?>> elements = new ArrayList<>(2);
            elements.add(filter.getSpatialOps());
            filter.setSpatialOps(null);
            elements.add(element);
            UnaryLogicOpType unaryLogicOpType = new UnaryLogicOpType();
            BinaryLogicOpType and = new BinaryLogicOpType();
            and.setComparisonOpsOrSpatialOpsOrLogicOps(elements);
            unaryLogicOpType.setLogicOps(filterFactory.createAnd(and));
            return unaryLogicOpType;
        } else {
            UnaryLogicOpType unaryLogicOpType = new UnaryLogicOpType();
            unaryLogicOpType.setComparisonOps(element);
            return unaryLogicOpType;
        }
    }

    /**
     * @param elements
     * @return {@link UnaryLogicOpType}
     */
    protected UnaryLogicOpType createLogicOps(@Nonnull(when = NEVER) List<JAXBElement<?>> elements, @Nonnull(when = NEVER) FilterType filter) {
        checkArgument(elements != null, "The Parameter elements must not be null.");
        checkArgument(filter != null, "The Parameter filter must not be null.");
        if (filter.isSetSpatialOps()) {
            elements.add(filter.getSpatialOps());
            filter.setSpatialOps(null);
        }
        UnaryLogicOpType unaryLogicOpType = new UnaryLogicOpType();
        BinaryLogicOpType and = new BinaryLogicOpType();
        and.setComparisonOpsOrSpatialOpsOrLogicOps(elements);
        unaryLogicOpType.setLogicOps(filterFactory.createAnd(and));
        return unaryLogicOpType;
    }
}