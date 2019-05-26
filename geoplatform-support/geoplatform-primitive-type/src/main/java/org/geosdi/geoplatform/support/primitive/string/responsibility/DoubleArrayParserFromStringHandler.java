package org.geosdi.geoplatform.support.primitive.string.responsibility;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.DOUBLE_ARRAY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class DoubleArrayParserFromStringHandler extends ArrayPrimitiveParserFromStringHandler<Double> {

    public DoubleArrayParserFromStringHandler() {
        super.setSuccessor(new LongArrayParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        try {
            Double.parseDouble(value);
            logger.debug("##########################PRIMITIVE_PARSER : {} parse value as Double : {}\n", getParserType(), value);
            return TRUE;
        } catch (NumberFormatException ex) {
            logger.trace("######################ERROR FOR PARSER : {} , trying to parse Value : {}", getParserType(), value);
            return FALSE;
        }
    }

    /**
     * @return {@link ParserType}
     */
    @Override
    public <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType() {
        return (ParserType) DOUBLE_ARRAY;
    }

    /**
     * @return {@link Class<Double[]>}
     */
    @Override
    public Class<Double[]> getPrimitiveType() {
        return Double[].class;
    }
}