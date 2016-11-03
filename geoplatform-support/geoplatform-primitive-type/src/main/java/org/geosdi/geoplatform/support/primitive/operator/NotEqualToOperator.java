package org.geosdi.geoplatform.support.primitive.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class NotEqualToOperator extends AbstractOperator {

    public NotEqualToOperator() {
        super("!=");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public <Type extends IOperatorType> Type getOperatorType() {
        return (Type) OperatorType.NOT_EQUALS_TO;
    }
}
