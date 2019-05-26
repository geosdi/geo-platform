package org.geosdi.geoplatform.support.primitive.string.responsibility;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.LONG_ARRAY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class LongArrayParserFromStringHandler extends ArrayPrimitiveParserFromStringHandler<Long> {

    public LongArrayParserFromStringHandler() {
        super.setSuccessor(new DefaultParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        try {
            Long.parseLong(value);
            logger.debug("##########################PRIMITIVE_PARSER : {} parse value as Long : {}\n", getParserType(), value);
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
        return (ParserType) LONG_ARRAY;
    }

    /**
     * @return {@link Class<Long[]>}
     */
    @Override
    public Class<Long[]> getPrimitiveType() {
        return Long[].class;
    }
}