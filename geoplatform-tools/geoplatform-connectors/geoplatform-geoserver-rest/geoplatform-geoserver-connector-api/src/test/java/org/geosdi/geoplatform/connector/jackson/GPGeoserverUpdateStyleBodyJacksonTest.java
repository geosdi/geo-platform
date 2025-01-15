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

import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverUpdateStyleBody;
import org.geosdi.geoplatform.connector.geoserver.model.styles.GPStyleVersion;
import org.geosdi.geoplatform.connector.geoserver.model.styles.IGPGeoserverUpdateStyleBody;
import org.geosdi.geoplatform.connector.geoserver.model.styles.IGPStyleVersion;
import org.geosdi.geoplatform.connector.geoserver.model.styles.legend.GPGeoserverStyleLegend;
import org.geosdi.geoplatform.connector.geoserver.model.styles.legend.IGPGeoserverStyleLegend;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverUpdateStyleBodyJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverUpdateStyleBodyJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverUpdateStyleBodyAsJsonStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_UPDATE_STYLE_BODY_AS_JSON_STRING : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(toGPGeoserverUpdateStyleBody()));
    }

    @Test
    public void b_marshallGPGeoserverUpdateStyleBodyAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_UPDATE_STYLE_BODY_AS_XML_STRING : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(toGPGeoserverUpdateStyleBody()));
    }

    @Test
    public void c_unmarshallGPGeoserverUpdateStyleBodyRequestFromJsonStringTest() throws Exception {
        logger.info("###################GP_GEOSERVER_UPDATE_STYLE_BODY_FROM_JSON_STRING : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"style\" : {\n"
                        + "    \"name\" : \"STYLE_TEST\",\n"
                        + "    \"filename\" : \"FILENAME_TEST\",\n"
                        + "    \"languageVersion\" : {\n"
                        + "      \"version\" : \"1.0.0\"\n"
                        + "    },\n"
                        + "    \"legend\" : {\n"
                        + "      \"width\" : 32,\n"
                        + "      \"height\" : 32,\n"
                        + "      \"format\" : \"image/png; charset=UTF-8\",\n"
                        + "      \"onLineResource\" : \"legend.png\"\n"
                        + "    }\n"
                        + "  }\n"
                        + "}"), IGPGeoserverUpdateStyleBody.class));
    }

    @Test
    public void d_unmarshallGPGeoserverUpdateStyleBodyFromXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@GP_GEOSERVER_UPDATE_STYLE_BODY_FROM_XML_STRING : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<style>\n"
                        + "    <name>STYLE_TEST</name>\n"
                        + "    <filename>FILENAME_TEST</filename>\n"
                        + "    <languageVersion>\n"
                        + "        <version>1.0.0</version>\n"
                        + "    </languageVersion>\n"
                        + "    <legend>\n"
                        + "        <format>image/png; charset=UTF-8</format>\n"
                        + "        <height>32</height>\n"
                        + "        <onLineResource>legend.png</onLineResource>\n"
                        + "        <width>32</width>\n"
                        + "    </legend>\n"
                        + "</style>"), GPGeoserverUpdateStyleBody.class));
    }

    /**
     * @return {@link IGPGeoserverUpdateStyleBody}
     */
    public static IGPGeoserverUpdateStyleBody toGPGeoserverUpdateStyleBody() {
        IGPGeoserverUpdateStyleBody geoserverUpdateStyleBody = new GPGeoserverUpdateStyleBody();
        geoserverUpdateStyleBody.setStyleName("STYLE_TEST");
        geoserverUpdateStyleBody.setLanguageVersion(toGPGeoserverStyleVersion());
        geoserverUpdateStyleBody.setFileName("FILENAME_TEST");
        geoserverUpdateStyleBody.setLegend(toGPGeoserverStyleLegend());
        return geoserverUpdateStyleBody;
    }

    /**
     * @return {@link IGPGeoserverUpdateStyleBody}
     */
    public static IGPGeoserverUpdateStyleBody toGPGeoserverUpdateStyleBody(String theFileName) {
        IGPGeoserverUpdateStyleBody geoserverUpdateStyleBody = new GPGeoserverUpdateStyleBody();
        geoserverUpdateStyleBody.setStyleName("STYLE_TEST");
        geoserverUpdateStyleBody.setLanguageVersion(toGPGeoserverStyleVersion());
        geoserverUpdateStyleBody.setFileName(theFileName);
        geoserverUpdateStyleBody.setLegend(toGPGeoserverStyleLegend());
        return geoserverUpdateStyleBody;
    }

    /**
     * @return {@link IGPStyleVersion}
     */
    static IGPStyleVersion toGPGeoserverStyleVersion() {
        IGPStyleVersion languageVersion = new GPStyleVersion();
        languageVersion.setVersion("1.0.0");
        return languageVersion;
    }

    /**
     * @return {@link IGPGeoserverStyleLegend}
     */
    static IGPGeoserverStyleLegend toGPGeoserverStyleLegend() {
        IGPGeoserverStyleLegend legend = new GPGeoserverStyleLegend();
        legend.setWidth(32);
        legend.setHeight(32);
        legend.setFormat("image/png; charset=UTF-8");
        legend.setOnLineResource("legend.png");
        return legend;
    }
}