package org.geosdi.geoplatform.support.primitive.operator;

import java.util.Collection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPOperator {

    /**
     * @return {@link String}
     */
    String getSymbol();

    /**
     * @param <Type>
     * @return {@link OperatorType}
     */
    <Type extends IOperatorType> Type getOperatorType();

    /**
     *
     */
    @FunctionalInterface
    interface GPOperatorLoader {

        /**
         * @return {@link Collection<GPOperator>}
         */
        Collection<GPOperator> load();
    }

    /**
     *
     */
    interface IOperatorType {

        /**
         * @return {@link String}
         */
        String getType();
    }

    /**
     *
     */
    enum OperatorType implements IOperatorType {

        EQUALS_TO("Equals To"),
        NOT_EQUALS_TO("Not Equals To"),
        GREATER_THAN("Greater Than"),
        GREATER_THAN_OR_EQUAL_TO("Greater Than Or Equal To"),
        LESS_THAN("Less Than"),
        LESS_THAN_OR_EQUAL_TO("Less Than Or Equal To"),
        CONTAINS("Contains"),
        ENDS_WITH("Ends With"),
        STARTS_WITH("Starts With");

        private final String type;

        OperatorType(String theType) {
            this.type = theType;
        }

        /**
         * @return {@link String}
         */
        public String getType() {
            return this.type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }
}
