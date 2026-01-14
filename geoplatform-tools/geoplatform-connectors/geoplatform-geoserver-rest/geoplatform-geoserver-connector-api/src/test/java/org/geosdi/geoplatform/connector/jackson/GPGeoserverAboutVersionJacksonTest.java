/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.about.version.GPGeoserverAboutVersion;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverVersionResourceJacksonTest.toVersionResources;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverAboutVersionJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverAboutVersionJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverAboutVersionAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@GP_GEOSERVER_ABOUT_VERSION : \n{}\n", JACKSON_JAXB_XML_SUPPORT
                .writeAsString(GPGeoserverAboutVersionJacksonTest::toAboutVersion));
    }

    @Test
    public void b_unmarshallGPGeoserverAboutVersionFromXmlStringTest() throws Exception {
        GPGeoserverAboutVersion aboutVersion = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<about>\n"
                        + "    <resource name=\"NAME#0\">\n"
                        + "        <Build-Timestamp>BUILD_TIMESTAMP#0</Build-Timestamp>\n"
                        + "        <Version>VERSION#0</Version>\n"
                        + "        <Git-Revision>GIT_REVISION#0</Git-Revision>\n"
                        + "    </resource>\n"
                        + "    <resource name=\"NAME#1\">\n"
                        + "        <Build-Timestamp>BUILD_TIMESTAMP#1</Build-Timestamp>\n"
                        + "        <Version>VERSION#1</Version>\n"
                        + "        <Git-Revision>GIT_REVISION#1</Git-Revision>\n"
                        + "    </resource>\n"
                        + "    <resource name=\"NAME#2\">\n"
                        + "        <Build-Timestamp>BUILD_TIMESTAMP#2</Build-Timestamp>\n"
                        + "        <Version>VERSION#2</Version>\n"
                        + "        <Git-Revision>GIT_REVISION#2</Git-Revision>\n"
                        + "    </resource>\n"
                        + "    <resource name=\"NAME#3\">\n"
                        + "        <Build-Timestamp>BUILD_TIMESTAMP#3</Build-Timestamp>\n"
                        + "        <Version>VERSION#3</Version>\n"
                        + "        <Git-Revision>GIT_REVISION#3</Git-Revision>\n"
                        + "    </resource>\n"
                        + "    <resource name=\"NAME#4\">\n"
                        + "        <Build-Timestamp>BUILD_TIMESTAMP#4</Build-Timestamp>\n"
                        + "        <Version>VERSION#4</Version>\n"
                        + "        <Git-Revision>GIT_REVISION#4</Git-Revision>\n"
                        + "    </resource>\n"
                        + "</about>"), GPGeoserverAboutVersion.class);
        logger.info("####################GP_GEOSERVER_ABOUT_VERSION : {}\n", aboutVersion);
    }

    @Test
    public void c_marshallGPGeoserverAboutVersionAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@GP_GEOSERVER_ABOUT_VERSION : \n{}\n", jacksonSupport.writeAsString(GPGeoserverAboutVersionJacksonTest::toAboutVersion));
    }

    @Test
    public void d_unmarshallGPGeoserverAboutVersionFromJsonStringTest() throws Exception {
        logger.info("###############GP_GEOSERVER_ABOUT_VERSION : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"about\" : {\n"
                        + "    \"resource\" : [ {\n"
                        + "      \"@name\" : \"NAME#0\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#0\",\n"
                        + "      \"Version\" : \"VERSION#0\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#0\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#1\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#1\",\n"
                        + "      \"Version\" : \"VERSION#1\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#1\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#2\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#2\",\n"
                        + "      \"Version\" : \"VERSION#2\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#2\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#3\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#3\",\n"
                        + "      \"Version\" : \"VERSION#3\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#3\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#4\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#4\",\n"
                        + "      \"Version\" : \"VERSION#4\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#4\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#5\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#5\",\n"
                        + "      \"Version\" : \"VERSION#5\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#5\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#6\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#6\",\n"
                        + "      \"Version\" : \"VERSION#6\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#6\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#7\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#7\",\n"
                        + "      \"Version\" : \"VERSION#7\",\n" +
                        "" + "      \"Git-Revision\" : \"GIT_REVISION#7\"\n"
                        + "    }, {\n"
                        + "      \"@name\" : \"NAME#8\",\n"
                        + "      \"Build-Timestamp\" : \"BUILD_TIMESTAMP#8\",\n"
                        + "      \"Version\" : \"VERSION#8\",\n"
                        + "      \"Git-Revision\" : \"GIT_REVISION#8\"\n"
                        + "    } ]\n"
                        + "  }\n"
                        + "}"), GPGeoserverAboutVersion.class));
    }

    /**
     * @return {@link GPGeoserverAboutVersion}
     */
    public static GPGeoserverAboutVersion toAboutVersion() {
        GPGeoserverAboutVersion aboutVersion = new GPGeoserverAboutVersion();
        aboutVersion.setResources(toVersionResources(30));
        return aboutVersion;
    }

    /**
     * @return {@link GPGeoserverAboutVersion}
     */
    public static GPGeoserverAboutVersion toAboutVersion(int resources) {
        GPGeoserverAboutVersion aboutVersion = new GPGeoserverAboutVersion();
        aboutVersion.setResources(toVersionResources(resources));
        return aboutVersion;
    }
}