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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.model.PlaneForSale;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class PlaneForSaleJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(PlaneForSaleJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<PlaneForSale> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(PlaneForSale.class,
            new GPJacksonXmlSupport());

    @Test
    public void a_readPlaneForSaleFromXmlStringTest() throws Exception {
        logger.info("######################PLANE_FOR_SALE_FROM_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<?xml version = \"1.0\" encoding = \"utf-8\"?>\n"
                + "<planes_for_sale>\n"
                + "    <ad>\n"
                + "        <year>1977</year>\n"
                + "        <make>&amp;c</make>\n"
                + "        <model>Skyhawk</model>\n"
                + "        <color>Light blue and white</color>\n"
                + "        <description>New paint, nearly new interior,\n"
                + "            685 hours SMOH, full IFR King avionics\n"
                + "        </description>\n"
                + "        <price>23.495</price>\n"
                + "        <seller phone=\"555-222-3333\">Skyway Aircraft</seller>\n"
                + "        <location>\n"
                + "            <city>Rapid City,</city>\n"
                + "            <state>South Dakota</state>\n"
                + "        </location>\n"
                + "    </ad>\n"
                + "    <ad>\n"
                + "        <year>1965</year>\n"
                + "        <make>&amp;p</make>\n"
                + "        <model>Cherokee</model>\n"
                + "        <color>Gold</color>\n"
                + "        <description>240 hours SMOH, dual NAVCOMs, DME,\n"
                + "            new Cleveland brakes, great shape\n"
                + "        </description>\n"
                + "        <seller phone=\"555-333-2222\"\n"
                + "                email=\"jseller@www.axl.com\">\n"
                + "            John Seller\n"
                + "        </seller>\n"
                + "        <location>\n"
                + "            <city>St. Joseph,</city>\n"
                + "            <state>Missouri</state>\n"
                + "        </location>\n"
                + "    </ad>\n"
                + "</planes_for_sale>")));
    }

    @Test
    public void b_readPlaneForSaleFromFileTest() throws Exception {
        logger.info("########################PLANE_FOR_SALE_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "plane_for_sale.xml").collect(joining(separator)))));
    }
}