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

import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager.GPPrimitiveParserHandlerManager;
import org.joda.time.DateTime;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPPrimitiveParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveParserTest.class);
    //
    private static final IGPPrimitiveParserHandlerManager primitiveParserHandlerManager = new GPPrimitiveParserHandlerManager();

    @Test
    public void a_parseStringTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("This is a Simple Test").getSimpleName();
        logger.info("#######################PARSE_STRING : {}\n", className);
        assertTrue(className.equalsIgnoreCase(String.class.getSimpleName()));
    }

    @Test
    public void b_parseIntegerTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("34").getSimpleName();
        logger.info("#######################PARSE_INTEGER : {}\n", className);
        assertTrue(className.equalsIgnoreCase(Integer.class.getSimpleName()));
    }

    @Test
    public void c_parseDoubleTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("34.89").getSimpleName();
        logger.info("#######################PARSE_DOUBLE : {}\n", className);
        assertTrue(className.equalsIgnoreCase(Double.class.getSimpleName()));
    }

    @Test
    public void d_parseDateTimeTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("2001-07-04T12:08:56.235-0700").getSimpleName();
        logger.info("#######################PARSE_DATE_TIME : {}\n", className);
        assertTrue(className.equalsIgnoreCase(DateTime.class.getSimpleName()));
    }

    @Test
    public void e_parseDateTimeAnotherTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("2016-08-28 14:36:15").getSimpleName();
        logger.info("#######################PARSE_DATE_TIME : {}\n", className);
        assertTrue(className.equalsIgnoreCase(DateTime.class.getSimpleName()));
    }

    @Test
    public void f_parseDateTime1Test() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("2010-07-05T11:47:14Z").getSimpleName();
        logger.info("#######################PARSE_DATE_TIME : {}\n", className);
        assertTrue(className.equalsIgnoreCase(DateTime.class.getSimpleName()));
    }

    @Test
    public void g_parseNumberTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("1,900,900.23").getSimpleName();
        logger.info("#######################PARSE_NUMBER : {}\n", className);
        assertTrue(className.equalsIgnoreCase(Number.class.getSimpleName()));
    }

    @Test
    public void h_parseNumberTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("1.900.900,23").getSimpleName();
        logger.info("#######################PARSE_NUMBER : {}\n", className);
        assertTrue(className.equalsIgnoreCase(Number.class.getSimpleName()));
    }

    @Test
    public void i_parseValueTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("44.524768336 11.4832955249").getSimpleName();
        logger.info("###########################PARSE_VALUE : {}\n", className);
        assertTrue(className.equalsIgnoreCase(Double[].class.getSimpleName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void l_parseValueTest() throws Exception {
        logger.info("###########################PARSE_VALUE : {}\n", primitiveParserHandlerManager.parseValue(null));
    }

    @Test
    public void m_parseValueTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("2019-05-21T08:13:33.250000").getSimpleName();
        logger.info("###########################PARSE_VALUE : {}\n", className);
        assertTrue(className.equalsIgnoreCase(DateTime.class.getSimpleName()));
    }

    @Test
    public void n_parseValueTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("44 11").getSimpleName();
        logger.info("###########################PARSE_VALUE : {}\n", className);
        assertTrue(className.equalsIgnoreCase(Integer[].class.getSimpleName()));
    }

    @Test
    public void o_parseValueTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("2977").getSimpleName();
        logger.info("###########################PARSE_VALUE : {}\n", className);
    }

    @Test
    public void p_parseValueTest() throws Exception {
        String className = primitiveParserHandlerManager.parseValue("0").getSimpleName();
        logger.info("###########################PARSE_VALUE : {}\n", className);
    }
}