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
package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.IDateFormatStrategyFinder.DateFormatStrategyPatter;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;
import org.geosdi.geoplatform.xml.filter.v110.FunctionType;
import org.geosdi.geoplatform.xml.filter.v110.LiteralType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class BaseBinaryStrategy implements QueryRestrictionStrategy<BinaryComparisonOpType> {

    private static final IDateFormatStrategyFinder dateFormatStrategyFinder = new DateFormatStrategyPatter();

    protected BaseBinaryStrategy() {
    }

    /**
     * @param queryRestrictionDTO
     * @return {@link BinaryComparisonOpType}
     */
    protected BinaryComparisonOpType createBinaryComparisonTypeForDate(QueryRestrictionDTO queryRestrictionDTO) {
        return new BinaryComparisonOpType() {
            {
                PropertyNameType propertyNameType = new PropertyNameType();
                propertyNameType.setContent(asList(queryRestrictionDTO.getAttribute().getName()));
                FunctionType functionType = new FunctionType();
                functionType.setName("dateParse");
                LiteralType literalType = new LiteralType();
                literalType.setContent(asList(queryRestrictionDTO.getRestriction()));
                LiteralType literalTypeFormat = new LiteralType();
                literalTypeFormat.setContent(dateFormatStrategyFinder.findDateFormat(queryRestrictionDTO.getAttribute()));
                List<JAXBElement<?>> functionElements = new ArrayList<>(2);
                functionElements.add(filterFactory.createLiteral(literalTypeFormat));
                functionElements.add(filterFactory.createLiteral(literalType));
                functionType.setExpression(functionElements);

                List<JAXBElement<?>> elements = new ArrayList<>(2);
                elements.add(filterFactory.createPropertyName(propertyNameType));
                elements.add(filterFactory.createFunction(functionType));
                super.setExpression(elements);
            }
        };
    }

    /**
     * @param queryRestrictionDTO
     * @return {@link BinaryComparisonOpType}
     */
    protected BinaryComparisonOpType createBinaryComparisonType(QueryRestrictionDTO queryRestrictionDTO) {
        return new BinaryComparisonOpType() {
            {
                PropertyNameType propertyNameType = new PropertyNameType();
                propertyNameType.setContent(asList(queryRestrictionDTO.getAttribute().getName()));
                LiteralType literalType = new LiteralType();
                literalType.setContent(asList(queryRestrictionDTO.getRestriction()));

                List<JAXBElement<?>> elements = new ArrayList<>(2);
                elements.add(filterFactory.createPropertyName(propertyNameType));
                elements.add(filterFactory.createLiteral(literalType));
                super.setExpression(elements);
            }
        };
    }
}