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
package org.geosdi.geoplatform.connector.server.request.v202.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.LiteralType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GetRecordsRequestHandlerFilter {

    final protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory;
    protected org.geosdi.geoplatform.xml.gml.v311.ObjectFactory gmlFactory;
    //
    private GetRecordsRequestHandlerFilter successor;

    public GetRecordsRequestHandlerFilter() {
        filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
        gmlFactory = new org.geosdi.geoplatform.xml.gml.v311.ObjectFactory();
    }

    public void setSuccessor(GetRecordsRequestHandlerFilter theSuccessor) {
        successor = theSuccessor;
    }

    public void forwardGetRecordsRequest(
            CatalogGetRecordsRequest request, FilterType filterType)
            throws IllegalParameterFault {

        this.processGetRecordsRequest(request, filterType);
        if (successor != null) {
            successor.forwardGetRecordsRequest(request, filterType);
        }
    }

    protected abstract void processGetRecordsRequest(
            CatalogGetRecordsRequest request, FilterType filterType)
            throws IllegalParameterFault;

    protected void addFilterConstraint(CatalogGetRecordsRequest request,
            FilterType filterType, List<JAXBElement<?>> filterPredicates) {

        if (request.getConstraintLanguage() != ConstraintLanguage.FILTER) {
            throw new IllegalArgumentException("Constraint Language must be FILTER.");
        }

        // Add all predicats always in OR (even if there is one)
        if (!filterType.isSetLogicOps()) {
            BinaryLogicOpType binary = new BinaryLogicOpType();
            binary.setComparisonOpsOrSpatialOpsOrLogicOps(filterPredicates);

            filterType.setLogicOps(filterFactory.createOr(binary));
        } else {
            BinaryLogicOpType binary = (BinaryLogicOpType) filterType.getLogicOps().getValue();
            List<JAXBElement<?>> orCriteria = binary.getComparisonOpsOrSpatialOpsOrLogicOps();

            orCriteria.addAll(filterPredicates);
        }
    }

    protected BinaryComparisonOpType createBinaryComparisonOpType(String propertyName, String literal) {

        BinaryComparisonOpType binaryComparison = new BinaryComparisonOpType();

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.<Object>asList(propertyName));

        LiteralType literalType = new LiteralType();
        literalType.setContent(Arrays.<Object>asList(literal));

        List<JAXBElement<?>> expressionList = new ArrayList<JAXBElement<?>>(2);
        expressionList.add(filterFactory.createPropertyName(propertyNameType));
        expressionList.add(filterFactory.createLiteral(literalType));
        binaryComparison.setExpression(expressionList);

        return binaryComparison;
    }
}
