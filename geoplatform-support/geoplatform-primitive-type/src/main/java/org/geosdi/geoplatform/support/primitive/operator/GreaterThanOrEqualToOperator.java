package org.geosdi.geoplatform.support.primitive.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GreaterThanOrEqualToOperator extends AbstractOperator {

    public GreaterThanOrEqualToOperator() {
        super(">=");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public <Type extends IOperatorType> Type getOperatorType() {
        return (Type) OperatorType.GREATER_THAN_OR_EQUAL_TO;
    }
}
