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

import org.geosdi.geoplatform.support.jackson.mapper.SimpleBeanJacksonMapperTest;
import org.geosdi.geoplatform.support.jackson.model.SimpleBean;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class SimpleBeanJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleBeanJacksonMapperTest.class);
    //
    private static final GPJacksonXmlMapper<SimpleBean> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(SimpleBean.class,
            new GPJacksonXmlSupport());
    @Test
    public void a_writeXmTest() throws Exception {
        logger.info("###################################XML_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(SimpleBeanJacksonMapperTest::simpleBean));
    }

    @Test
    public void b_readXmlFromStringTest() throws Exception {
        SimpleBean simpleBean = GP_JACKSON_XML_MAPPER.read("<SimpleBean>\n"
                + "  <args>\n"
                + "    <color>white</color>\n"
                + "    <shape>square</shape>\n"
                + "  </args>\n"
                + "  <headers>\n"
                + "    <Accept>text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</Accept>\n"
                + "    <Upgrade-Insecure-Requests>1</Upgrade-Insecure-Requests>\n"
                + "    <User-Agent>Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:52.0) Gecko/20100101 Firefox/52.0</User-Agent>\n"
                + "    <Host>httpbin.org</Host>\n"
                + "    <Accept-Encoding>gzip, deflate, br</Accept-Encoding>\n"
                + "    <Accept-Language>it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3</Accept-Language>\n"
                + "  </headers>\n"
                + "  <origin>82.61.27.192</origin>\n"
                + "  <url>https://httpbin.org/get?color=red&amp;shape=square</url>\n"
                + "</SimpleBean>");
        assertNotNull(simpleBean);
        logger.info("#############################XML_FROM_STRING : {}\n", simpleBean);
    }

    @Test
    public void c_readXmlFromFileTest() throws Exception {
        SimpleBean simpleBean = GP_JACKSON_XML_MAPPER.read(new File(of("src", "test", "resources", "simple_bean.xml")
                .collect(joining(separator))));
        assertNotNull(simpleBean);
        logger.info("#############################XML_FROM_FILE : {}\n", simpleBean);
    }
}