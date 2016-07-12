package org.geosdi.geoplatform.support.primitive.bridge.store.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LessThanOrEqualToOperator extends AbstractOperator {

    public LessThanOrEqualToOperator() {
        super("<=");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public <Type extends IOperatorType> Type getOperatorType() {
        return (Type) OperatorType.LESS_THAN_OR_EQUAL_TO;
    }
}
