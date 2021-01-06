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
package org.geosdi.geoplatform.support.jackson.reader;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.model.SimpleBean;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.stream.Stream;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class SimpleBeanJacksonReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleBeanJacksonReaderTest.class);
    //
    private static final GPJacksonReaderSupport<SimpleBean> JACKSON_READER_SUPPORT = new GPBaseJacksonReaderSupport<>(new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE, INDENT_OUTPUT_ENABLE)
            .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
            .configure(GPJsonIncludeFeature.NON_NULL), SimpleBean.class);

    @Test
    public void a_readJsonFromURLTest() throws Exception {
        SimpleBean simpleBean = JACKSON_READER_SUPPORT.read(new URL("https://httpbin.org/get?color=red&shape=square"));
        logger.info("#######################HEADERS_SIZE : {}", simpleBean.getHeaders().size());
        assertNotNull(simpleBean);
        assertTrue(simpleBean.getArguments().size() == 2);
        assertNotNull(simpleBean.getOrigin());
        assertNotNull(simpleBean.getUrl());
    }

    @Test
    public void b_readJsonFromStringTest() throws Exception {
        SimpleBean simpleBean = JACKSON_READER_SUPPORT.read("{\n" +
                "  \"args\": {\n" +
                "    \"color\": \"red\", \n" +
                "    \"shape\": \"square\"\n" +
                "  }, \n" +
                "  \"headers\": {\n" +
                "    \"Accept\": \"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\", \n" +
                "    \"Accept-Encoding\": \"gzip, deflate, br\", \n" +
                "    \"Accept-Language\": \"it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3\", \n" +
                "    \"Host\": \"httpbin.org\", \n" +
                "    \"Upgrade-Insecure-Requests\": \"1\", \n" +
                "    \"User-Agent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:52.0) Gecko/20100101 Firefox/52.0\"\n" +
                "  }, \n" +
                "  \"origin\": \"82.61.27.192\", \n" +
                "  \"url\": \"https://httpbin.org/get?color=red&shape=square\"\n" +
                "}");
        assertNotNull(simpleBean);
    }

    @Test
    public void c_readJsonFromFileTest() throws Exception {
        SimpleBean simpleBean = JACKSON_READER_SUPPORT.read(new File(Stream
                .of(new File(".").getAbsolutePath(), "src", "test", "resources", "simple_bean.json")
                .collect(joining(separator))));
        assertNotNull(simpleBean);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@SIMPLE_BEAN from File : {}\n", simpleBean);
    }
}