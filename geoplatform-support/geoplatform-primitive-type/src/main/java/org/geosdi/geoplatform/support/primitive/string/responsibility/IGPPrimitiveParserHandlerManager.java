package org.geosdi.geoplatform.support.primitive.string.responsibility;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPPrimitiveParserHandlerManager {

    /**
     * @param value
     * @return {@link Class}
     * @throws Exception
     */
    Class parseValue(String value) throws Exception;

    class GPPrimitiveParserHandlerManager implements IGPPrimitiveParserHandlerManager {

        private static final GPPrimitiveParserFromStringHandler primitiveParserFromStringHandler = new IntegerParserFromStringHandler();

        /**
         * @param value
         * @return {@link Class}
         * @throws Exception
         */
        @Override
        public Class parseValue(String value) throws Exception {
            return primitiveParserFromStringHandler.parseValue(value);
        }
    }
}
