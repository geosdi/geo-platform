package org.geosdi.geoplatform.support.primitive.string.responsibility;


import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.INTEGER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class IntegerParserFromStringHandler extends AbstractPrimitiveParserFromStringHandler<Integer> {

    public IntegerParserFromStringHandler() {
        super.setSuccessor(new DoubleParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Class<Integer>}
     * @throws Exception
     */
    @Override
    public Class<Integer> parseValue(String value) throws Exception {
        return (canParseValue(value) ? getPrimitiveType() : super.forwardParseValue(value));
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        try {
            Integer.parseInt(value);
            logger.debug("##########################PRIMITIVE_PARSER : {} parse value as Integer : {}\n",
                    getParserType(), value);
            return Boolean.TRUE;
        } catch (NumberFormatException ex) {
            logger.trace("######################ERROR FOR PARSER : {} , trying to parse Value : {}",
                    getParserType(), value);
            return Boolean.FALSE;
        }
    }

    /**
     * @return {@link ParserType}
     */
    @Override
    public <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType() {
        return (ParserType) INTEGER;
    }

    /**
     * @return {@link Class<Integer>}
     */
    @Override
    public Class<Integer> getPrimitiveType() {
        return Integer.TYPE;
    }
}
