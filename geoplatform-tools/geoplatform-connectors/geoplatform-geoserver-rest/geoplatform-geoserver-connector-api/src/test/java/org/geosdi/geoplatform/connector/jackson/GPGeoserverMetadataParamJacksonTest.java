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

import lombok.*;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.GPGeoserverMetadataParam;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.adapter.GPGeoserverMetadataMapAdapter;
import org.geosdi.geoplatform.support.jackson.mapper.xml.GPBaseJacksonXmlMapper;
import org.geosdi.geoplatform.support.jackson.mapper.xml.GPJacksonXmlMapper;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.GPJacksonJAXBXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverMetadataParamJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverMetadataParamJacksonTest.class);
    //
    private static final GPJacksonXmlMapper<GPGeoserverMetadataParam> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(GPGeoserverMetadataParam.class,
            new GPJacksonJAXBXmlSupport());

    @Test
    public void a_marshalMetadataParamAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_PARAM_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(GPGeoserverMetadataParamJacksonTest::toMetadataParam));
    }

    @Test
    public void b_marshallGPGeoserverMetadataParamAsFileTest() throws Exception {
        GP_JACKSON_XML_MAPPER.write(new File(of(".", "target", "GPGeoserverMetadataParam.xml")
                .collect(joining(separator))), GPGeoserverMetadataParamJacksonTest::toMetadataParam);
    }

    @Test
    public void c_marshallGPGeoserverMetadataTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("key_test", "value_test");
        values.put("key_1_test", "value_1_test");
        GPGeoserverMetadata metadata = new GPGeoserverMetadata(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_AS_STRING : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().writeValueAsString(metadata));
    }

    @Test
    public void d_unmarshallGPGeoserverMetadataFromStringTest() throws Exception {
        GPGeoserverMetadata metadata = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<GPGeoserverMetadata>\n"
                + "    <metadata>\n"
                + "        <entry key=\"key_test\">value_test</entry>\n"
                + "        <entry key=\"key_1_test\">value_1_test</entry>\n"
                + "    </metadata>\n"
                + "</GPGeoserverMetadata>"), GPGeoserverMetadata.class);
        logger.info("####################GP_GEOSERVER_METADATA : {}\n", metadata);
    }

    @Test
    public void e_unmarshallGPGeoserverMetadataFromJsonStringTest() throws Exception {
        GPGeoserverMetadata metadata = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"GPGeoserverMetadata\" : {\n"
                + "    \"metadata\" : {\n"
                + "      \"entry\" : [ {\n"
                + "        \"@key\" : \"key_test\",\n"
                + "        \"$\" : \"value_test\"\n"
                + "      }, {\n"
                + "        \"@key\" : \"key_1_test\",\n"
                + "        \"$\" : \"value_1_test\"\n"
                + "      } ]\n"
                + "    }\n"
                + "  }\n"
                + "}"), GPGeoserverMetadata.class );
        logger.info("####################GP_GEOSERVER_METADATA : {}\n", metadata);
    }

    @Test
    public void f_marshalGPGeoserverMetadataTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("kml.regionateStrategy", "external-sorting");
        values.put("kml.regionateFeatureLimit", "15");
        values.put("cacheAgeMax", "3000");
        values.put("cachingEnabled", "true");
        values.put("kml.regionateAttribute", "NAME");
        values.put("indexingEnabled", "false");
        values.put("dirName", "DS_poi_poi");
        GPGeoserverMetadata metadata = new GPGeoserverMetadata(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_AS_STRING : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(metadata));
    }

    @Test
    public void g_unmarshalGPGeoserverMetadataFromJsonStringTest() throws Exception {
        GPGeoserverMetadata metadata = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"GPGeoserverMetadata\" : {\n"
                + "    \"metadata\" : {\n"
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
                + "}"), GPGeoserverMetadata.class);
        logger.info("{}\n", metadata);
    }

    @Test
    public void h_marshalGPGeoserverMetadataTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("dirName", "sfdem_sfdem");
        GPGeoserverMetadata metadata = new GPGeoserverMetadata(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_AS_STRING : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(metadata));
    }

    @Test
    public void i_marshalWMSStoreMetadataParamAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_WMS_STORE_METADATA_PARAM_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(GPGeoserverMetadataParamJacksonTest::toWMSStoreMetadataParam));
    }

    @Test
    public void l_marshallGPWMSStoreMetadataParamAsFileTest() throws Exception {
        GP_JACKSON_XML_MAPPER.write(new File(of(".", "target", "GPWMSStoreMetadataParam.xml")
                .collect(joining(separator))), GPGeoserverMetadataParamJacksonTest::toWMSStoreMetadataParam);
    }

    @Test
    public void m_marshallGPWMSStoreMetadataTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("key_test", "value_test");
        values.put("key_1_test", "value_1_test");
        values.put("key_2_test", "value_2_test");
        GPWMSStoreMetadata metadata = new GPWMSStoreMetadata(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_WMS_STORE_METADATA_AS_STRING : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().writeValueAsString(metadata));
    }

    @Test
    public void n_unmarshallGPWMSStoreMetadataFromStringTest() throws Exception {
        GPWMSStoreMetadata metadata = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<GPWMSStoreMetadata>\n"
                        + "    <metadata>\n"
                        + "        <entry key=\"key_test\">value_test</entry>\n"
                        + "        <entry key=\"key_2_test\">value_2_test</entry>\n"
                        + "        <entry key=\"key_1_test\">value_1_test</entry>\n"
                        + "    </metadata>\n"
                        + "</GPWMSStoreMetadata>"), GPWMSStoreMetadata.class);
        logger.info("####################GP_WMS_STORE_METADATA : {}\n", metadata);
    }

    @Test
    public void o_marshalWMSStoreMetadataParamAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_WMS_STORE_METADATA_PARAM_AS_STRING : \n{}\n", jacksonSupport
                .writeAsString(GPGeoserverMetadataParamJacksonTest::toWMSStoreMetadataParam));
    }

    @Test
    public void p_unmarshalWMSStoreMetadataParamFromJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_WMS_STORE_METADATA_PARAM_AS_STRING : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"entry\" : {\n"
                        + "    \"@key\" : \"key_test\",\n"
                        + "    \"$\" : \"value_test\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverMetadataParam.class));
    }

    @Test
    public void q_marshalGPWMSStoreMetadataAsJsonStringTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("kml.regionateStrategy", "external-sorting");
        values.put("kml.regionateFeatureLimit", "15");
        values.put("cacheAgeMax", "3000");
        values.put("cachingEnabled", "true");
        values.put("kml.regionateAttribute", "NAME");
        values.put("indexingEnabled", "false");
        values.put("dirName", "DS_poi_poi");
        GPWMSStoreMetadata metadata = new GPWMSStoreMetadata(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_WMS_STORE_METADATA_AS_STRING : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(metadata));
    }

    @Test
    public void r_unmarshalGPWMSStorerMetadataFromJsonStringTest() throws Exception {
        GPWMSStoreMetadata metadata = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"GPWMSStoreMetadata\" : {\n"
                + "    \"metadata\" : {\n"
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
                + "}"), GPWMSStoreMetadata.class);
        logger.info("{}\n", metadata);
    }

    @Test
    public void s_marshalGPWMSStoreMetadataTest() throws Exception {
        Map<String, String> values = newHashMap();
        values.put("dirName", "sfdem_sfdem");
        GPWMSStoreMetadata metadata = new GPWMSStoreMetadata(values);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_WMS_STORE_METADATA_AS_STRING : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(metadata));
    }

    /**
     * @return {@link GPGeoserverMetadataParam}
     */
    public static GPGeoserverMetadataParam toMetadataParam() {
        return new GPGeoserverMetadataParam("key_test", "value_test");
    }

    /**
     * @return {@link GPGeoserverMetadataParam}
     */
    public static GPGeoserverMetadataParam toWMSStoreMetadataParam() {
        return new GPGeoserverMetadataParam("key_test", "value_test");
    }

    /**
     * @return {@link Map<String, String>}
     */
    public static Map<String, String> toMapParams() {
        Map<String, String> values = newHashMap();
        values.put("kml.regionateStrategy", "external-sorting");
        values.put("kml.regionateFeatureLimit", "15");
        values.put("cacheAgeMax", "3000");
        values.put("cachingEnabled", "true");
        values.put("kml.regionateAttribute", "NAME");
        values.put("indexingEnabled", "false");
        values.put("dirName", "DS_poi_poi");
        return values;
    }

    @XmlRootElement(name = "GPGeoserverMetadata")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class GPGeoserverMetadata implements Serializable {

        private static final long serialVersionUID = 6064901869538161620L;
        //
        @XmlJavaTypeAdapter(value = GPGeoserverMetadataMapAdapter.class)
        private Map<String, String> metadata;
    }

    @XmlRootElement(name = "GPWMSStoreMetadata")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class GPWMSStoreMetadata implements Serializable {

        private static final long serialVersionUID = -2247798402522860160L;
        //
        @XmlJavaTypeAdapter(value = GPGeoserverMetadataMapAdapter.class)
        private Map<String, String> metadata;
    }
}