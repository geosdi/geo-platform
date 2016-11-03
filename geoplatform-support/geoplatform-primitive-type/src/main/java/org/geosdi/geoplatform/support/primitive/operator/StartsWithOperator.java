package org.geosdi.geoplatform.support.primitive.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class StartsWithOperator extends AbstractOperator {

    public StartsWithOperator() {
        super("starts_with");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public <Type extends IOperatorType> Type getOperatorType() {
        return (Type) OperatorType.STARTS_WITH;
    }
}
