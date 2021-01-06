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
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.filter.v110.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.xml.csw.ConstraintLanguage.FILTER;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GetRecordsRequestHandlerFilter implements GPGetRecordsRequestHandlerFilter {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
    protected static final org.geosdi.geoplatform.xml.gml.v311.ObjectFactory gmlFactory = new org.geosdi.geoplatform.xml.gml.v311.ObjectFactory();
    //
    private GetRecordsRequestHandlerFilter successor;

    GetRecordsRequestHandlerFilter() {
    }

    /**
     * @param theRequest
     * @param theFilterType
     * @param theFilterPredicates
     * @throws IllegalParameterFault
     */
    public void forwardGetRecordsRequest(@Nonnull(when = NEVER) CatalogGetRecordsRequest theRequest, @Nonnull(when = NEVER) FilterType theFilterType, @Nonnull(when = NEVER) List<JAXBElement<?>> theFilterPredicates) throws IllegalParameterFault {
        checkArgument(theRequest != null, "The Parameter request must not be null.");
        checkArgument(theFilterType != null, "The Parameter filterType must not be null.");
        checkArgument(theFilterPredicates != null);
        this.processGetRecordsRequest(theRequest, theFilterType, theFilterPredicates);
        if (successor != null) {
            successor.forwardGetRecordsRequest(theRequest, theFilterType, theFilterPredicates);
        } else if (theFilterPredicates.size() > 0) {
            this.addFilterConstraint(theRequest, theFilterType, theFilterPredicates);
        }
    }

    /**
     *
     * @param theSuccessor
     */
    public void setSuccessor(GetRecordsRequestHandlerFilter theSuccessor) {
        successor = theSuccessor;
    }

    protected abstract void processGetRecordsRequest(CatalogGetRecordsRequest theRequest, FilterType theFilterType, List<JAXBElement<?>> theFilterPredicates) throws IllegalParameterFault;

    /**
     * @param propertyName
     * @param literal
     * @return {@link BinaryComparisonOpType}
     */
    protected BinaryComparisonOpType createBinaryComparisonOpType(String propertyName, String literal) {
        BinaryComparisonOpType binaryComparison = new BinaryComparisonOpType();
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(asList(propertyName));
        LiteralType literalType = new LiteralType();
        literalType.setContent(asList(literal));
        List<JAXBElement<?>> expressionList = new ArrayList(2);
        expressionList.add(filterFactory.createPropertyName(propertyNameType));
        expressionList.add(filterFactory.createLiteral(literalType));
        binaryComparison.setExpression(expressionList);
        return binaryComparison;
    }

    /**
     * @param request
     * @param filterType
     * @param filterPredicates
     */
    private void addFilterConstraint(CatalogGetRecordsRequest request, FilterType filterType, List<JAXBElement<?>> filterPredicates) {
        checkArgument(request.getConstraintLanguage() == FILTER, "Constraint Language must be FILTER.");
        if (filterPredicates.size() == 1) {
            Object value = filterPredicates.get(0).getValue();
            if(value instanceof ComparisonOpsType) {
                filterType.setComparisonOps((JAXBElement<ComparisonOpsType>) filterPredicates.get(0));
            } else if(value instanceof LogicOpsType) {
                filterType.setLogicOps((JAXBElement<LogicOpsType>) filterPredicates.get(0));
            } else if(value instanceof SpatialOpsType) {
                filterType.setSpatialOps((JAXBElement<SpatialOpsType>) filterPredicates.get(0));
            }
        } else {
            BinaryLogicOpType binary = new BinaryLogicOpType();
            binary.setComparisonOpsOrSpatialOpsOrLogicOps(filterPredicates);
            filterType.setLogicOps(filterFactory.createOr(binary));
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ \n" +
                "handlerType = " + this.getType() +
                "\n}";
    }
}