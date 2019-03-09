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