/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.about.version.GPGeoserverVersionResource;
import org.geosdi.geoplatform.connector.geoserver.model.about.version.IGPGeoserverVersionResource;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverVersionResourceJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverVersionResourceJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverVersionResourceAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_VERSION_RESOURCE : \n{}\n", JACKSON_JAXB_XML_SUPPORT
                .writeAsString(GPGeoserverVersionResourceJacksonTest::toVersionResource));
    }

    @Test
    public void b_unmarshallGPGeoserverVersionResourceFromXmlStringTest() throws Exception {
        GPGeoserverVersionResource versionResource = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<resource name=\"GeoTools\">\n"
                        + "    <Build-Timestamp>28-Mar-2017 15:44</Build-Timestamp>\n"
                        + "    <Version>18-SNAPSHOT</Version>\n"
                        + "    <Git-Revision>a96cee38ad6c8e87cb8ccdf75e1fb837bff35ef0</Git-Revision>\n"
                        + "</resource>"), GPGeoserverVersionResource.class);
        logger.info("####################GP_GEOSERVER_VERSION_RESOURCE : {}\n", versionResource);
    }

    @Test
    public void c_marshallGPGeoserverVersionResourceAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_VERSION_RESOURCE : \n{}\n", jacksonSupport
                .writeAsString(GPGeoserverVersionResourceJacksonTest::toVersionResource));
    }

    @Test
    public void d_unmarshallGPGeoserverVersionResourceFromJsonStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_VERSION_RESOURCE : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"resource\" : {\n"
                        + "    \"@name\" : \"GeoTools\",\n"
                        + "    \"Build-Timestamp\" : \"28-Mar-2017 15:44\",\n"
                        + "    \"Version\" : \"18-SNAPSHOT\",\n"
                        + "    \"Git-Revision\" : \"a96cee38ad6c8e87cb8ccdf75e1fb837bff35ef0\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverVersionResource.class));
    }

    /**
     * @return {@link GPGeoserverVersionResource}
     */
    public static GPGeoserverVersionResource toVersionResource() {
        GPGeoserverVersionResource versionResource = new GPGeoserverVersionResource();
        versionResource.setName("GeoTools");
        versionResource.setBuildTimestamp("28-Mar-2017 15:44");
        versionResource.setVersion("18-SNAPSHOT");
        versionResource.setGitRevision("a96cee38ad6c8e87cb8ccdf75e1fb837bff35ef0");
        return versionResource;
    }

    /**
     * @param number
     * @return {@link List<IGPGeoserverVersionResource>}
     */
    public static List<IGPGeoserverVersionResource> toVersionResources(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        return iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPGeoserverVersionResourceJacksonTest::toVersionResource)
                .collect(toList());
    }

    /**
     * @param value
     * @return {@link IGPGeoserverVersionResource}
     */
    public static IGPGeoserverVersionResource toVersionResource(Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        GPGeoserverVersionResource versionResource = new GPGeoserverVersionResource();
        versionResource.setName("NAME#" + value);
        versionResource.setBuildTimestamp("BUILD_TIMESTAMP#" + value);
        versionResource.setVersion("VERSION#" + value);
        versionResource.setGitRevision("GIT_REVISION#" + value);
        return versionResource;
    }
}