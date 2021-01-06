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
package org.geosdi.geoplatform.support.primitive.string.responsibility.theories;

import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager;
import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager.GPPrimitiveParserHandlerManager;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GPPrimitiveParserTheoriesHandlerTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveParserTheoriesHandlerTest.class);
    //
    private static final IGPPrimitiveParserHandlerManager primitiveParserHandlerManager = new GPPrimitiveParserHandlerManager();

    @DataPoints
    public static String[] data() {
        return new String[]{"This is a test", "120", "130.89", "1200", "1450", "This is another Test",
                "2001.07.04 AD at 12:08:56 PDT", "Wed, Jul 4, '01", "12:08 PM",
                "12 o'clock PM, Pacific Daylight Time", "0:08 PM, PDT",
                "02001.July.04 AD 12:08 PM", "Wed, 4 Jul 2001 12:08:56 -0700",
                "010704120856-0700", "2001-07-04T12:08:56.235-0700",
                "16/08/1977", "This is an important Date.", "12898.89873482", "21-05-15", "Test1",
                "Test2", "1998"};
    }

    @Theory
    public void parseValueTest(String value) throws Exception {
        logger.info("##################################FOUND Classe : {} , for Value : {}\n",
                primitiveParserHandlerManager.parseValue(value), value);
    }
}
