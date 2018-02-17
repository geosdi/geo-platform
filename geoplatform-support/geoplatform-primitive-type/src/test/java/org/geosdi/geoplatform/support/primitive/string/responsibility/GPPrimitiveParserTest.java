/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.primitive.string.responsibility;

import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager.GPPrimitiveParserHandlerManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPrimitiveParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveParserTest.class);
    //
    private static final IGPPrimitiveParserHandlerManager primitiveParserHandlerManager = new GPPrimitiveParserHandlerManager();

    @Test
    public void parseStringTest() throws Exception {
        logger.info("#######################PARSE_STRING : {}\n",
                primitiveParserHandlerManager.parseValue("This is a Simple Test").getSimpleName());
    }

    @Test
    public void parseIntegerTest() throws Exception {
        logger.info("#######################PARSE_INTEGER : {}\n",
                primitiveParserHandlerManager.parseValue("34").getSimpleName());
    }

    @Test
    public void parseDoubleTest() throws Exception {
        logger.info("#######################PARSE_DOUBLE : {}\n",
                primitiveParserHandlerManager.parseValue("34.89").getSimpleName());
    }

    @Test
    public void parseDateTimeTest() throws Exception {
        logger.info("#######################PARSE_DATE_TIME : {}\n",
                primitiveParserHandlerManager.parseValue("2001-07-04T12:08:56.235-0700").getSimpleName());
    }

    @Test
    public void parseDateTimeAnotherTest() throws Exception {
        logger.info("#######################PARSE_DATE_TIME : {}\n",
                primitiveParserHandlerManager.parseValue("2016-08-28 14:36:15").getSimpleName());
    }

    @Test
    public void parseDateTime1Test() throws Exception {
        logger.info("#######################PARSE_DATE_TIME : {}\n",
                primitiveParserHandlerManager.parseValue("2010-07-05T11:47:14Z").getSimpleName());
    }
}