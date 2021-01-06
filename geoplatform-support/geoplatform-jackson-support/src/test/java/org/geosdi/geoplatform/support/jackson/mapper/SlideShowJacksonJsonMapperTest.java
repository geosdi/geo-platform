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
package org.geosdi.geoplatform.support.jackson.mapper;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.mapper.xml.SlideShowJacksonXmlMapperTest;
import org.geosdi.geoplatform.support.jackson.model.SlideShow;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class SlideShowJacksonJsonMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(SlideShowJacksonJsonMapperTest.class);
    //
    private static final GPJacksonMapper<SlideShow> GP_JACKSON_MAPPER = new GPBaseJacksonMapper<>(SlideShow.class,
            new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                    ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE, INDENT_OUTPUT_ENABLE)
                    .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
                    .configure(NON_NULL));

    @Test
    public void a_writeSlideShowAsJsonStringTest() throws Exception {
        logger.info("\n{}\n", GP_JACKSON_MAPPER.writeAsString(SlideShowJacksonXmlMapperTest::slideShow));
    }

    @Test
    public void b_readSlideShowFromJsonStringTest() throws Exception {
        SlideShow slideShow = GP_JACKSON_MAPPER.read("{\n"
                + "  \"slide\" : [ {\n"
                + "    \"type\" : \"ALL\",\n"
                + "    \"title\" : \"Wake up to WonderWidgets!\"\n"
                + "  }, {\n" + "    \"item\" : [ \"pippo\", \"pluto\" ],\n"
                + "    \"type\" : \"ALL\",\n"
                + "    \"title\" : \"Overview\"\n"
                + "  } ],\n"
                + "  \"title\" : \"TITLE_TEST\",\n"
                + "  \"date\" : \"DATE_TEST\",\n"
                + "  \"author\" : \"Test\"\n"
                + "}");
        assertNotNull(slideShow);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@SLIDE_SHOW_FROM_XML_STRING : {}\n", slideShow);
    }

    @Test
    public void c_writeSlideShowJsonAsFileTest() throws Exception {
        GP_JACKSON_MAPPER.write(new File("./target/SlideShow.json"), SlideShowJacksonXmlMapperTest::slideShow);
    }

    @Test
    public void d_readSlideShowJsonFromFileTest() throws Exception {
        logger.info("########################SLIDE_SHOW_FROM_FILE : {}\n", GP_JACKSON_MAPPER.read(new File(of("src", "test",
                "resources", "SlideShow.json").collect(joining(separator)))));
    }
}