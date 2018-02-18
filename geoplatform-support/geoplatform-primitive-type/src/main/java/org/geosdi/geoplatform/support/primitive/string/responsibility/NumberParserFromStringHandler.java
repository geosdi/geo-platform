package org.geosdi.geoplatform.support.primitive.string.responsibility;

import java.text.DecimalFormat;
import java.text.ParseException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.NUMBER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class NumberParserFromStringHandler extends AbstractPrimitiveParserFromStringHandler<Number> {

    private static final DecimalFormat decimalFormat = new DecimalFormat();

    public NumberParserFromStringHandler() {
        super.setSuccessor(new LongParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Class<Number>}
     * @throws Exception
     */
    @Override
    public Class<Number> parseValue(String value) throws Exception {
        return (canParseValue(value) ? getPrimitiveType() : super.forwardParseValue(value));
    }

    /**
     * @return {@link ParserType}
     */
    @Override
    public <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType() {
        return (ParserType) NUMBER;
    }

    /**
     * @return {@link Class<Number>}
     */
    @Override
    public Class<Number> getPrimitiveType() {
        return Number.class;
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        try {
            decimalFormat.parse(value);
            return TRUE;
        } catch (ParseException ex) {
            logger.trace("######################ERROR FOR PARSER : {} , trying to parse Value : {}",
                    getParserType(), value);
            return FALSE;
        }
    }
}
