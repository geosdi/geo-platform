package org.geosdi.geoplatform.support.primitive.string.responsibility;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.INTEGER_ARRAY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class IntegerArrayParserFromStringHandler extends ArrayPrimitiveParserFromStringHandler<Integer> {

    public IntegerArrayParserFromStringHandler() {
        super.setSuccessor(new DoubleArrayParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        try {
            Integer.parseInt(value);
            logger.debug("##########################PRIMITIVE_PARSER : {} parse value as Integer : {}\n", getParserType(), value);
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
        return (ParserType) INTEGER_ARRAY;
    }

    /**
     * @return {@link Class<Integer[]>}
     */
    @Override
    public Class<Integer[]> getPrimitiveType() {
        return Integer[].class;
    }
}