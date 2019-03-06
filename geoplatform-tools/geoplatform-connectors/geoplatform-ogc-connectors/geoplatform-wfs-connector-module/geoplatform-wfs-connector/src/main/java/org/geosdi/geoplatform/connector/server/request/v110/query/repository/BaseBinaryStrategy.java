package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;
import org.geosdi.geoplatform.xml.filter.v110.FunctionType;
import org.geosdi.geoplatform.xml.filter.v110.LiteralType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class BaseBinaryStrategy implements QueryRestrictionStrategy<BinaryComparisonOpType> {

    /**
     * @param queryRestrictionDTO
     * @return {@link BinaryComparisonOpType}
     */
    protected BinaryComparisonOpType createBinaryComparisonTypeForDate(QueryRestrictionDTO queryRestrictionDTO) {
        BinaryComparisonOpType binaryComparisonOpType = new BinaryComparisonOpType();

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.asList(queryRestrictionDTO.getAttribute().getName()));
        FunctionType functionType = new FunctionType();
        functionType.setName("dateParse");
        LiteralType literalType = new LiteralType();
        literalType.setContent(Arrays.asList(queryRestrictionDTO.getRestriction()));
        LiteralType literalTypeFormat = new LiteralType();
        literalTypeFormat.setContent(queryRestrictionDTO.getAttribute().getType().equalsIgnoreCase("date") ?
                Arrays.asList("yyyy-MM-dd") : Arrays.asList("yyyy-MM-dd HH:mm"));
        List<JAXBElement<?>> functionElements = new ArrayList<>(2);
        functionElements.add(filterFactory.createLiteral(literalTypeFormat));
        functionElements.add(filterFactory.createLiteral(literalType));
        functionType.setExpression(functionElements);

        List<JAXBElement<?>> elements = new ArrayList<>(2);
        elements.add(filterFactory.createPropertyName(propertyNameType));
        elements.add(filterFactory.createFunction(functionType));
        binaryComparisonOpType.setExpression(elements);
        return binaryComparisonOpType;
    }

    /**
     * @param queryRestrictionDTO
     * @return {@link BinaryComparisonOpType}
     */
    protected BinaryComparisonOpType createBinaryComparisonType(QueryRestrictionDTO queryRestrictionDTO) {
        BinaryComparisonOpType binaryComparisonOpType = new BinaryComparisonOpType();

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.asList(queryRestrictionDTO.getAttribute().getName()));
        LiteralType literalType = new LiteralType();
        literalType.setContent(Arrays.asList(queryRestrictionDTO.getRestriction()));

        List<JAXBElement<?>> elements = new ArrayList<>(2);
        elements.add(filterFactory.createPropertyName(propertyNameType));
        elements.add(filterFactory.createLiteral(literalType));
        binaryComparisonOpType.setExpression(elements);
        return binaryComparisonOpType;
    }
}