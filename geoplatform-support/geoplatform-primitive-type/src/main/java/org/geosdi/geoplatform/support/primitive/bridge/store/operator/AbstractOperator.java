package org.geosdi.geoplatform.support.primitive.bridge.store.operator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractOperator implements GPOperator {

    private final String symbol;

    public AbstractOperator(String theSymbol) {
        this.symbol = theSymbol;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " symbol = '" + symbol + '\'' +
                " type = '" + getOperatorType() + '\'' +
                '}';
    }
}
