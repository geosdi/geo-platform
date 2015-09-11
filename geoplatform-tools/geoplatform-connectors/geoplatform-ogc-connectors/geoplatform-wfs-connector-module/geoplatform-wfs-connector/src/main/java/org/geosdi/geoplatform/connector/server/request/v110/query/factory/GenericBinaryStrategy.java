package org.geosdi.geoplatform.connector.server.request.v110.query.factory;

import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;
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
public abstract class GenericBinaryStrategy implements QueryRestrictionStrategy<BinaryComparisonOpType> {

    /**
     * @param queryRestrictionDTO
     * @return {@link JAXBElement<BinaryComparisonOpType>}
     */
    @Override
    public JAXBElement<BinaryComparisonOpType> create(QueryRestrictionDTO queryRestrictionDTO) {
        BinaryComparisonOpType binaryComparisonOpType = new BinaryComparisonOpType();

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.<Object>asList(queryRestrictionDTO.getAttribute().getName()));
        LiteralType literalType = new LiteralType();
        literalType.setContent(Arrays.<Object>asList(queryRestrictionDTO.getRestriction()));

        List<JAXBElement<?>> elements = new ArrayList<>(2);
        elements.add(filterFactory.createPropertyName(propertyNameType));
        elements.add(filterFactory.createLiteral(literalType));
        binaryComparisonOpType.setExpression(elements);

        return this.create(binaryComparisonOpType);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public final Boolean isValidStrategy() {
        return Boolean.TRUE;
    }

    protected abstract JAXBElement<BinaryComparisonOpType> create(BinaryComparisonOpType binaryComparisonOpType);

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                " isValidStrategy = " + isValidStrategy() +
                " , forOperatorType = " + forOperatorType() +
                " }";
    }
}
