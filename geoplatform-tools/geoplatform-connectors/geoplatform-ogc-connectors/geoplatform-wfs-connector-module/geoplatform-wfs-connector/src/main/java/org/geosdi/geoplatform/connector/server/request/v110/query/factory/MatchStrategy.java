package org.geosdi.geoplatform.connector.server.request.v110.query.factory;

import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.xml.filter.v110.LiteralType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyIsLikeType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;

import javax.xml.bind.JAXBElement;
import java.util.Arrays;

/**
 * <p>This class is for
 * <ul>
 * <li>{@link org.geosdi.geoplatform.gui.shared.wfs.OperatorType#STARTS_WITH} operator</li>
 * <li>{@link org.geosdi.geoplatform.gui.shared.wfs.OperatorType#CONTAINS} operator</li>
 * <li>{@link org.geosdi.geoplatform.gui.shared.wfs.OperatorType#ENDS_WITH} operator</li>
 * <li>{@link org.geosdi.geoplatform.gui.shared.wfs.OperatorType#LIKE} operator</li>
 * </ul>
 * </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class MatchStrategy implements QueryRestrictionStrategy<PropertyIsLikeType> {

    /**
     * @param queryRestrictionDTO
     * @return {@link JAXBElement <T>}
     */
    @Override
    public JAXBElement<PropertyIsLikeType> create(QueryRestrictionDTO queryRestrictionDTO) {
        PropertyIsLikeType propertyIsLikeType = new PropertyIsLikeType();
        propertyIsLikeType.setWildCard("%");
        propertyIsLikeType.setSingleChar(".");
        propertyIsLikeType.setEscapeChar("\\");

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.<Object>asList(queryRestrictionDTO.getAttribute().getName()));
        propertyIsLikeType.setPropertyName(propertyNameType);

        LiteralType literalType = new LiteralType();
        literalType.setContent(Arrays.<Object>asList(this.matchExpression(queryRestrictionDTO.getRestriction())));
        propertyIsLikeType.setLiteral(literalType);
        return filterFactory.createPropertyIsLike(propertyIsLikeType);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public final Boolean isValidStrategy() {
        return Boolean.TRUE;
    }

    protected abstract Object matchExpression(String content);

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " +
                " isValidStrategy = " + isValidStrategy() +
                " , forOperatorType = " + forOperatorType() +
                " }";
    }
}
