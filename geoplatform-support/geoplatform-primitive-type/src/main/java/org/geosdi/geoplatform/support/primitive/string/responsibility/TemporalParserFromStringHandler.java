/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.primitive.string.responsibility;

import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPBaseTemporalImplementorFinder;
import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPTemporalPatternImplementor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.Locale;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
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
        super.setSuccessor(new NumberParserFromStringHandler());
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
                return TRUE;
            } catch (Exception ex) {
                logger.trace("######################ERROR FOR PARSER : {} , trying to parse Value : {} , " +
                        "with Pattern :{} and Locale : {}", getParserType(), value, patter, locale);
            }
        }
        return FALSE;
    }
}