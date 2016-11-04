package org.geosdi.geoplatform.support.primitive.string.responsibility;

import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPBaseTemporalImplementorFinder;
import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPTemporalPatternImplementor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.Locale;

import static org.geosdi.geoplatform.support.primitive.string.responsibility.GPPrimitiveParserFromStringHandler.IGPPrimitiveParserFromStringType.GPPrimitiveParserFromStringType.TEMPORAL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class TemporalParserFromStringHandler extends AbstractPrimitiveParserFromStringHandler<DateTime> {

    static {
        temporalPatternImplementor = new GPBaseTemporalImplementorFinder().findTemporalImplementor();
    }

    private static final GPTemporalPatternImplementor temporalPatternImplementor;

    public TemporalParserFromStringHandler() {
        super.setSuccessor(new DefaultParserFromStringHandler());
    }

    /**
     * @param value
     * @return {@link Class<DateTime>}
     * @throws Exception
     */
    @Override
    public Class<DateTime> parseValue(String value) throws Exception {
        return (canParseValue(value) ? getPrimitiveType() : super.forwardParseValue(value));
    }

    /**
     * @return {@link ParserType}
     */
    @Override
    public <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType() {
        return (ParserType) TEMPORAL;
    }

    /**
     * @return {@link Class<Date>}
     */
    @Override
    public Class<DateTime> getPrimitiveType() {
        return DateTime.class;
    }

    /**
     * @param value
     * @return {@link Boolean}
     */
    @Override
    protected Boolean canParseValue(String value) {
        Locale locale = temporalPatternImplementor.getLocale();
        for (String patter : temporalPatternImplementor.getPatterns()) {
            try {
                DateTime dateTime = DateTime.parse(value, DateTimeFormat.forPattern(patter).withLocale(locale));
                logger.debug("##########################PRIMITIVE_PARSER : {} parse value {} as DateTime : {}.\n",
                        getParserType(), value, dateTime);
                return Boolean.TRUE;
            } catch (Exception ex) {
                logger.trace("######################ERROR FOR PARSER : {} , trying to parse Value : {} , " +
                        "with Pattern :{} and Locale : {}", getParserType(), value, patter, locale);
            }
        }
        return Boolean.FALSE;
    }
}
