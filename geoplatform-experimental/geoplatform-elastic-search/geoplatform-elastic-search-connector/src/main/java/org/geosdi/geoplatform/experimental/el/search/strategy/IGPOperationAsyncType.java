package org.geosdi.geoplatform.experimental.el.search.strategy;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPOperationAsyncType {

    /**
     * @return {@link String}
     */
    String getType();

    /**
     *
     */
    enum OperationAsyncType implements IGPOperationAsyncType {

        DELETE("DELETE_ASYNC"),
        UPDATE("UPDATE_ASYNC");

        private final String type;

        /**
         * @param theType
         */
        OperationAsyncType(String theType) {
            this.type = theType;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getType() {
            return this.type;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String toString() {
            return this.type;
        }
    }
}
