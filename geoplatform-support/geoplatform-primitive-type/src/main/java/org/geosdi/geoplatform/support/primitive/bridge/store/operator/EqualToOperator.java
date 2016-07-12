package org.geosdi.geoplatform.support.primitive.bridge.store.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EqualToOperator extends AbstractOperator {

    public EqualToOperator() {
        super("=");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public OperatorType getOperatorType() {
        return OperatorType.EQUALS_TO;
    }
}
