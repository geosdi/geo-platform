package org.geosdi.geoplatform.support.primitive.string.responsibility;

import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.DOUBLE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class DoubleParserFromStringHandler extends AbstractPrimitiveParserFromStringHandler<Double> {

    public DoubleParserFromStringHandler() {
        super.setSuccessor(new TemporalParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Class<Double>}
     * @throws Exception
     */
    @Override
    public Class<Double> parseValue(String value) throws Exception {
        return (canParseValue(value) ? getPrimitiveType() : super.forwardParseValue(value));
    }

    /**
     * @return {@link ParserType}
     */
    @Override
    public <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType() {
        return (ParserType) DOUBLE;
    }

    /**
     * @return {@link Class<Double>}
     */
    @Override
    public Class<Double> getPrimitiveType() {
        return Double.TYPE;
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        try {
            Double.parseDouble(value);
            logger.debug("##########################PRIMITIVE_PARSER : {} parse value as Double : {}\n",
                    getParserType(), value);
            return Boolean.TRUE;
        } catch (NumberFormatException ex) {
            logger.trace("######################ERROR FOR PARSER : {} , trying to parse Value : {}",
                    getParserType(), value);
            return Boolean.FALSE;
        }
    }
}
