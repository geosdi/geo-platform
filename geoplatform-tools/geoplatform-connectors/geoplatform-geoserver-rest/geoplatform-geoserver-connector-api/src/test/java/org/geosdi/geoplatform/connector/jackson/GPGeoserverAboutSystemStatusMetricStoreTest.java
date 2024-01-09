/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.about.system.GPGeoserverAboutSystemStatusMetricStore;
import org.geosdi.geoplatform.connector.geoserver.model.about.system.GPGeoserverAboutSystemStatusMetricValue;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
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
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAXB;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverAboutSystemStatusMetricStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverAboutSystemStatusMetricStoreTest.class);
    //
    public static final JacksonSupport jacksonSupport = new GPJacksonSupport(JAXB, UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);
    private static final GPJacksonXmlSupport jacksonXmlSupport = new GPJacksonXmlSupport(JAXB);

    @Test
    public void a_unmarshallGeoserverAboutSystemStatusMetricStoreTest() throws Exception {
        GPGeoserverAboutSystemStatusMetricStore geoserverAboutSystemStatusStore = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath() , "src", "test", "resources",
                        "GPGeoserverAboutSystemStatusMetricStore.json")
                        .collect(joining(separator))), GPGeoserverAboutSystemStatusMetricStore.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_SYSTEM_STATUS_STORE : {}", geoserverAboutSystemStatusStore);
    }

    @Test
    public void b_unmarshallGeoserverAboutSystemStatusMetricStoreTest() throws Exception {
        GPGeoserverAboutSystemStatusMetricStore geoserverAboutSystemStatusStore = jacksonXmlSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath() , "src", "test", "resources",
                        "GPGeoserverAboutSystemStatusMetricStore.xml")
                        .collect(joining(separator))), GPGeoserverAboutSystemStatusMetricStore.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_SYSTEM_STATUS_STORE : {}", geoserverAboutSystemStatusStore);
    }

    @Test
    public void c_unmarshallGeoserverAboutSystemStatusMetricTest() throws Exception {
        GPGeoserverAboutSystemStatusMetricValue geoserverAboutSystemStatusMetricValue = jacksonXmlSupport.getDefaultMapper()
                .readValue(new StringReader("<metric>\n"
                        + "        <available>false</available>\n"
                        + "        <description>Uptime</description>\n"
                        + "        <name>SYSTEM_UPTIME</name>\n"
                        + "        <unit>sec</unit>\n"
                        + "        <category>SYSTEM</category>\n"
                        + "        <identifier>SYSTEM_UPTIME</identifier>\n"
                        + "        <priority>2</priority>\n"
                        + "        <value>NOT AVAILABLE</value>\n"
                        + "    </metric>"), GPGeoserverAboutSystemStatusMetricValue.class);
        logger.info("#########################GP_GEOSERVER_ABOUT_SYSTEM_METRIC_VALUE : {}\n", geoserverAboutSystemStatusMetricValue);
    }

    @Test
    public void d_unmarshallGeoserverAboutSystemStatusMetricTest() throws Exception {
        GPGeoserverAboutSystemStatusMetricValue geoserverAboutSystemStatusMetricValue = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"metric\" : {\n"
                        + "    \"available\" : false,\n"
                        + "    \"category\" : \"SYSTEM\",\n"
                        + "    \"description\" : \"Operating system\",\n"
                        + "    \"identifier\" : \"OPERATING_SYSTEM\",\n"
                        + "    \"name\" : \"OPERATING_SYSTEM\",\n"
                        + "    \"priority\" : 1,\n"
                        + "    \"unit\" : \"\",\n"
                        + "    \"value\" : \"NOT AVAILABLE\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverAboutSystemStatusMetricValue.class);
        logger.info("#########################GP_GEOSERVER_ABOUT_SYSTEM_METRIC_VALUE : {}\n", geoserverAboutSystemStatusMetricValue);
    }
}