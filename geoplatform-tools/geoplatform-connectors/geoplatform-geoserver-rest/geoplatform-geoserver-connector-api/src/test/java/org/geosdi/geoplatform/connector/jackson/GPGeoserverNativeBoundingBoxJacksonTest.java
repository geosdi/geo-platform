/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverCRSJacksonTest.toCrs;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverNativeBoundingBoxJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverNativeBoundingBoxJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverNativeBoundingBoxAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSREVER_NATIVE_BOUNDING_BOX : \n{}\n", JACKSON_JAXB_XML_SUPPORT
                .writeAsString(GPGeoserverNativeBoundingBoxJacksonTest::toNativeBoundingBox));
    }

    @Test
    public void b_unmarshallGPGeoserverNativeBoundingBoxFromXmlStringTest() throws Exception {
        logger.info("#################GP_GEOSERVER_NATIVE_BOUNDING_BOX : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<nativeBoundingBox>\n"
                        + "    <maxx>609000.0</maxx>\n"
                        + "    <maxy>4928010.0</maxy>\n"
                        + "    <minx>589980.0</minx>\n"
                        + "    <miny>4913700.0</miny>\n"
                        + "    <crs class=\"projected\">EPSG:26713</crs>\n"
                        + "</nativeBoundingBox>"), GPGeoserverNativeBoundingBox.class));
    }

    @Test
    public void c_marshallGPGeoserverNativeBoundingBoxAsXmlStringTest() throws Exception {
        GPGeoserverNativeBoundingBox nativeBoundingBox = toNativeBoundingBox();
        nativeBoundingBox.setCrs("EPSG:32632");
        logger.info("@@@@@@@@@@@@@@@@GP_GEOSERVER_NATIVE_BOUNDING_BOX : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(nativeBoundingBox));
    }

    @Test
    public void d_unmarshallGPGeoserverNativeBoundingBoxFromXmlStringTest() throws Exception {
        logger.info("################GP_GEOSERVER_NATIVE_BOUNDING_BOX : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<nativeBoundingBox>\n"
                        + "    <maxx>609000.0</maxx>\n"
                        + "    <maxy>4928010.0</maxy>\n"
                        + "    <minx>589980.0</minx>\n"
                        + "    <miny>4913700.0</miny>\n"
                        + "    <crs>EPSG:32632</crs>\n"
                        + "</nativeBoundingBox>"), GPGeoserverNativeBoundingBox.class));
    }

    @Test
    public void e_marshallGPGeoserverNativeBoundingBoxAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSREVER_NATIVE_BOUNDING_BOX : \n{}\n", jacksonSupport.writeAsString(GPGeoserverNativeBoundingBoxJacksonTest::toNativeBoundingBox));
    }

    @Test
    public void f_unmarshallGPGeoserverNativeBoundingBoxFromJsonStringTest() throws Exception {
        logger.info("#################GP_GEOSERVER_NATIVE_BOUNDING_BOX : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"nativeBoundingBox\" : {\n"
                        + "    \"maxx\" : 609000.0,\n"
                        + "    \"maxy\" : 4928010.0,\n"
                        + "    \"minx\" : 589980.0,\n"
                        + "    \"miny\" : 4913700.0,\n"
                        + "    \"crs\" : {\n"
                        + "      \"$\" : \"EPSG:26713\",\n"
                        + "      \"@class\" : \"projected\"\n"
                        + "    }\n"
                        + "  }\n"
                        + "}"), GPGeoserverNativeBoundingBox.class));
    }

    @Test
    public void g_marshallGPGeoserverNativeBoundingBoxAsJsonStringTest() throws Exception {
        GPGeoserverNativeBoundingBox nativeBoundingBox = toNativeBoundingBox();
        nativeBoundingBox.setCrs("EPSG:32632");
        logger.info("@@@@@@@@@@@@@@@@@GP_GEOSERVER_NATIVE_BOUNDING_BOX : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(nativeBoundingBox));
    }

    @Test
    public void h_unmarshallGPGeoserverNativeBoundingBoxFromJsonStringTest() throws Exception {
        logger.info("#################GP_GEOSERVER_NATIVE_BOUNDING_BOX : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"nativeBoundingBox\" : {\n"
                        + "    \"maxx\" : 609000.0,\n"
                        + "    \"maxy\" : 4928010.0,\n"
                        + "    \"minx\" : 589980.0,\n"
                        + "    \"miny\" : 4913700.0,\n"
                        + "    \"crs\" : \"EPSG:32632\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverNativeBoundingBox.class));
    }

    /**
     * @return {@link GPGeoserverNativeBoundingBox}
     */
    public static GPGeoserverNativeBoundingBox toNativeBoundingBox() {
        GPGeoserverNativeBoundingBox nativeBoundingBox = new GPGeoserverNativeBoundingBox();
        nativeBoundingBox.setMinx(589980.0);
        nativeBoundingBox.setMaxx(609000.0);
        nativeBoundingBox.setMiny(4913700.0);
        nativeBoundingBox.setMaxy(4928010.0);
        nativeBoundingBox.setCrs(toCrs());
        return nativeBoundingBox;
    }
}