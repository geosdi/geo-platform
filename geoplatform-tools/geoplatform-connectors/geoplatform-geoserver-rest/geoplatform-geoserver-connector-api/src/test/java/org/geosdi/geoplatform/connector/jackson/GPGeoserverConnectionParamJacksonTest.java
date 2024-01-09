/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import lombok.*;
import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionParam;
import org.geosdi.geoplatform.connector.geoserver.model.connection.adapter.GPGeoserverConnectionMapParamAdapter;
import org.geosdi.geoplatform.support.jackson.mapper.xml.GPBaseJacksonXmlMapper;
import org.geosdi.geoplatform.support.jackson.mapper.xml.GPJacksonXmlMapper;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.GPJacksonJAXBXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverConnectionParamJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectionParamJacksonTest.class);
    //
    private static final GPJacksonXmlMapper<GPGeoserverConnectionParam> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(GPGeoserverConnectionParam.class,
            new GPJacksonJAXBXmlSupport());

    @Test
    public void a_marshalConnecctionParamAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_PARAM_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(GPGeoserverConnectionParamJacksonTest::toConnectionParam));
    }

    @Test
    public void b_marshallGPGeoserverConnectionParamAsFileTest() throws Exception {
        GP_JACKSON_XML_MAPPER.write(new File(of(".", "target", "GPGeoserverConnectionParam.xml")
                .collect(joining(separator))), GPGeoserverConnectionParamJacksonTest::toConnectionParam);
    }

    @Test
    public void c_marshallGPGeoserverConnectionAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CONNECTION_AS_STRING : \n{}\n", JACKSON_JAXB_XML_SUPPORT
                .writeAsString(GPGeoserverConnectionParamJacksonTest::toGPGeoserverConnetion));
    }

    @Test
    public void d_unmarshallGPGeoserverConnectionFromXmlStringTest() throws Exception {
        GPGeoserverConnection connection = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<GPGeoserverConnection>\n"
                        + "    <connectionParams>\n"
                        + "        <entry key=\"key_connection_1_test\">value_connection_1_test</entry>\n"
                        + "        <entry key=\"key_connection_test\">value_connection_test</entry>\n"
                        + "    </connectionParams>\n"
                        + "</GPGeoserverConnection>"), GPGeoserverConnection.class);
        logger.info("##################GP_GEOSERVER_CONNECTION : {}\n", connection);
    }

    @Test
    public void e_marshallGPGeoserverConnectionAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CONNECTION_AS_STRING : \n{}\n", jacksonSupport
                .writeAsString(GPGeoserverConnectionParamJacksonTest::toGPGeoserverConnetion));
    }

    @Test
    public void f_unmarshallGPGeoserverConnectionFromJsonStringTest() throws Exception {
        GPGeoserverConnection connection = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"GPGeoserverConnection\" : {\n"
                + "    \"connectionParams\" : {\n"
                + "      \"entry\" : [ {\n"
                + "        \"@key\" : \"key_connection_1_test\",\n"
                + "        \"$\" : \"value_connection_1_test\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"key_connection_test\",\n"
                + "        \"$\" : \"value_connection_test\"\n"
                + "      } ]\n"
                + "    }\n"
                + "  }\n"
                + "}"), GPGeoserverConnection.class);
        logger.info("##################GP_GEOSERVER_CONNECTION : {}\n", connection);
    }

    @Test
    public void g_marshallGPGeoserverConnectionAsXmlStringTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("kml.regionateStrategy", "external-sorting");
        values.put("kml.regionateFeatureLimit", "15");
        values.put("cacheAgeMax", "3000");
        values.put("cachingEnabled", "true");
        values.put("kml.regionateAttribute", "NAME");
        values.put("indexingEnabled", "false");
        values.put("dirName", "DS_poi_poi");
        GPGeoserverConnection connection = new GPGeoserverConnection(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CONNECTION : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(connection));
    }

    @Test
    public void h_unmarshallGPGeoserverConnectionFromXmlStringTest() throws Exception {
        GPGeoserverConnection connection = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<GPGeoserverConnection>\n"
                        + "    <connectionParams>\n"
                        + "        <entry key=\"kml.regionateStrategy\">external-sorting</entry>\n"
                        + "        <entry key=\"kml.regionateFeatureLimit\">15</entry>\n"
                        + "        <entry key=\"cacheAgeMax\">3000</entry>\n"
                        + "        <entry key=\"cachingEnabled\">true</entry>\n"
                        + "        <entry key=\"kml.regionateAttribute\">NAME</entry>\n"
                        + "        <entry key=\"indexingEnabled\">false</entry>\n"
                        + "        <entry key=\"dirName\">DS_poi_poi</entry>\n"
                        + "    </connectionParams>\n"
                        + "</GPGeoserverConnection>"), GPGeoserverConnection.class);
        logger.info("##################GP_GEOSERVER_CONNECTION : {}\n", connection);
    }

    @Test
    public void i_marshallGPGeoserverConnectionAsJsonStringTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("kml.regionateStrategy", "external-sorting");
        values.put("kml.regionateFeatureLimit", "15");
        values.put("cacheAgeMax", "3000");
        values.put("cachingEnabled", "true");
        values.put("kml.regionateAttribute", "NAME");
        values.put("indexingEnabled", "false");
        values.put("dirName", "DS_poi_poi");
        GPGeoserverConnection connection = new GPGeoserverConnection(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CONNECTION : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(connection));
    }

    @Test
    public void l_unmarshallGPGeoserverConnectionFromJsonStringTest() throws Exception {
        GPGeoserverConnection connection = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"GPGeoserverConnection\" : {\n"
                + "    \"connectionParams\" : {\n"
                + "      \"entry\" : [ {\n"
                + "        \"@key\" : \"kml.regionateStrategy\",\n"
                + "        \"$\" : \"external-sorting\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"kml.regionateFeatureLimit\",\n"
                + "        \"$\" : \"15\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"cacheAgeMax\",\n"
                + "        \"$\" : \"3000\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"cachingEnabled\",\n"
                + "        \"$\" : \"true\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"kml.regionateAttribute\",\n"
                + "        \"$\" : \"NAME\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"indexingEnabled\",\n"
                + "        \"$\" : \"false\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"dirName\",\n"
                + "        \"$\" : \"DS_poi_poi\"\n"
                + "      } ]\n"
                + "    }\n"
                + "  }\n"
                + "}"), GPGeoserverConnection.class);
        logger.info("##################GP_GEOSERVER_CONNECTION : {}\n", connection);
    }

    /**
     * @return {@link GPGeoserverConnectionParam}
     */
    public static GPGeoserverConnectionParam toConnectionParam() {
        return new GPGeoserverConnectionParam("key_test", "value_test");
    }

    /**
     * @return {@link GPGeoserverConnection}
     */
    public static GPGeoserverConnection toGPGeoserverConnetion() {
        Map<String, String> values = newHashMap();
        values.put("key_connection_test", "value_connection_test");
        values.put("key_connection_1_test", "value_connection_1_test");
        return new GPGeoserverConnection(values);
    }

    @XmlRootElement(name = "GPGeoserverConnection")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class GPGeoserverConnection implements Serializable {

        private static final long serialVersionUID = -2496499771545499367L;
        //
        @XmlJavaTypeAdapter(value = GPGeoserverConnectionMapParamAdapter.class)
        private Map<String, String> connectionParams;
    }
}