package org.geosdi.geoplatform.support.primitive.bridge.store.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EndsWithOperator extends AbstractOperator {

    public EndsWithOperator() {
        super("ends_with");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public <Type extends IOperatorType> Type getOperatorType() {
        return (Type) OperatorType.ENDS_WITH;
    }
}
