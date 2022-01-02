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
package org.geosdi.geoplatform.connector.jackson;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.GPFeatureTypeAttribute;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.GPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttribute;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttributes;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPFeatureTypeAttributesJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPFeatureTypeAttributesJacksonTest.class);

    @Test
    public void a_marshallGPFeatureTypeAttributesAsXmlStringTest() throws Exception {
        GPFeatureTypeAttributes featureTypeAttributes = toFeatureTypeAttributes(10);
        logger.info("###################GP_FEATURE_TYPE_ATTRIBUTES : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(featureTypeAttributes));
    }

    @Test
    public void b_unmarshallGPFeatureTypeAttributesFromXmlStringTest() throws Exception {
        GPFeatureTypeAttributes featureTypeAttributes = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<attributes>\n"
                        + "   <attribute>\n"
                        + "      <name>the_geom</name>\n"
                        + "      <minOccurs>0</minOccurs>\n"
                        + "      <maxOccurs>1</maxOccurs>\n"
                        + "      <nillable>true</nillable>\n"
                        + "      <binding>org.locationtech.jts.geom.Point</binding>\n"
                        + "   </attribute>\n"
                        + "   <attribute>\n"
                        + "      <name>NAME</name>\n"
                        + "      <minOccurs>0</minOccurs>\n"
                        + "      <maxOccurs>1</maxOccurs>\n"
                        + "      <nillable>true</nillable>\n"
                        + "      <binding>java.lang.String</binding>\n"
                        + "      <length>6</length>\n"
                        + "   </attribute>\n"
                        + "   <attribute>\n"
                        + "      <name>THUMBNAIL</name>\n"
                        + "      <minOccurs>0</minOccurs>\n"
                        + "      <maxOccurs>1</maxOccurs>\n"
                        + "      <nillable>true</nillable>\n"
                        + "      <binding>java.lang.String</binding>\n"
                        + "      <length>20</length>\n"
                        + "   </attribute>\n"
                        + "   <attribute>\n"
                        + "      <name>MAINPAGE</name>\n"
                        + "      <minOccurs>0</minOccurs>\n"
                        + "      <maxOccurs>1</maxOccurs>\n"
                        + "      <nillable>true</nillable>\n"
                        + "      <binding>java.lang.String</binding>\n"
                        + "      <length>19</length>\n"
                        + "   </attribute>\n"
                        + "</attributes>"), GPFeatureTypeAttributes.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GP_FEATURE_TYPE_ATTRIBUTES : {}\n", featureTypeAttributes);
    }

    @Test
    public void c_marshallGPFeatureTypeAttributesAsJsonStringTest() throws Exception {
        GPFeatureTypeAttributes featureTypeAttributes = toFeatureTypeAttributes(23);
        logger.info("#####################GP_FEATURE_TYPE_ATTRIBUTES : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(featureTypeAttributes));
    }

    @Test
    public void d_unmarshallGPFeatureTypeAttributesFromJsonStringTest() throws Exception {
        GPFeatureTypeAttributes featureTypeAttributes = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"attributes\" : {\n"
                        + "    \"attribute\" : [ {\n"
                        + "      \"name\" : \"NAME#0\",\n"
                        + "      \"minOccurs\" : 0,\n"
                        + "      \"maxOccurs\" : 0,\n"
                        + "      \"nillable\" : true,\n"
                        + "      \"binding\" : \"BINDING#0\"\n"
                        + "    }, {\n"
                        + "      \"name\" : \"NAME#1\",\n"
                        + "      \"minOccurs\" : 1,\n"
                        + "      \"maxOccurs\" : 1,\n"
                        + "      \"nillable\" : false,\n"
                        + "      \"binding\" : \"BINDING#1\"\n"
                        + "    }, {\n"
                        + "      \"name\" : \"NAME#2\",\n"
                        + "      \"minOccurs\" : 2,\n"
                        + "      \"maxOccurs\" : 2,\n"
                        + "      \"nillable\" : true,\n"
                        + "      \"binding\" : \"BINDING#2\"\n"
                        + "    } ]\n"
                        + "  }\n"
                        + "}"), GPFeatureTypeAttributes.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_FEATURE_TYPE_ATTRIBUTES : {}\n", featureTypeAttributes);
    }

    @Test
    public void e_marshallGPFeatureTypeAttributeAsXmlStringTest() throws Exception {
        GPFeatureTypeAttribute featureTypeAttribute = toFeatureTypeAttribute(0);
        logger.info("###############GP_FEATURE_TYPE_ATTRIBUTE : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(featureTypeAttribute));
    }

    @Test
    public void f_unmarshallGPFeatureTypeAttributeFromXmlStringTest() throws Exception {
        GPFeatureTypeAttribute featureTypeAttribute = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<attribute>\n"
                        + "    <name>NAME#0</name>\n"
                        + "    <minOccurs>0</minOccurs>\n"
                        + "    <maxOccurs>0</maxOccurs>\n"
                        + "    <nillable>true</nillable>\n"
                        + "    <binding>BINDING#0</binding>\n"
                        + "</attribute>"), GPFeatureTypeAttribute.class);
        logger.info("@@@@@@@@@@@@@@GP_FEATURE_TYPE_ATTRIBUTE : {}\n", featureTypeAttribute);
    }

    @Test
    public void g_marshallGPFeatureAttributeAsJsonStringTest() throws Exception {
        GPFeatureTypeAttribute featureTypeAttribute = toFeatureTypeAttribute(0);
        logger.info("##############GP_FEATURE_TYPE_ATTRIBUTE : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(featureTypeAttribute));
    }

    @Test
    public void h_unmarshallGPFeatureTypeAttribteFromJsonStringTest() throws Exception {
        GPFeatureTypeAttribute featureTypeAttribute = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"attribute\" : {\n"
                + "    \"name\" : \"NAME#0\",\n"
                + "    \"minOccurs\" : 0,\n"
                + "    \"maxOccurs\" : 0,\n"
                + "    \"nillable\" : true,\n"
                + "    \"binding\" : \"BINDING#0\"\n"
                + "  }\n"
                + "}"), GPFeatureTypeAttribute.class);
        logger.info("@@@@@@@@@@@@@@GP_FEATURE_TYPE_ATTRIBUTE : \n{}\n", featureTypeAttribute);
    }

    @Test
    public void i_marshallGPFeatureTypeAttributesJacksonAsXmlStringTest() throws Exception {
        GPFeatureTypeAttributes featureTypeAttributes = toFeatureTypeAttributes(9);
        GPFeatureTypeAttributesJackson featureTypeAttributesJackson = new GPFeatureTypeAttributesJackson();
        featureTypeAttributesJackson.setAttributes(featureTypeAttributes);
        logger.info("##############GP_FEATURE_TYPE_ATTRIBUTES_JACKSON : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(featureTypeAttributesJackson));
    }

    @Test
    public void l_unmarshallGPFeatureTypeAttributesJacksonFromXmlStringTest() throws Exception {
        GPFeatureTypeAttributesJackson featureTypeAttributesJackson = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<FeatureTypesJackson>\n"
                        + "    <attributes>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#0</name>\n"
                        + "            <minOccurs>0</minOccurs>\n"
                        + "            <maxOccurs>0</maxOccurs>\n"
                        + "            <nillable>true</nillable>\n"
                        + "            <binding>BINDING#0</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#1</name>\n"
                        + "            <minOccurs>1</minOccurs>\n"
                        + "            <maxOccurs>1</maxOccurs>\n"
                        + "            <nillable>false</nillable>\n"
                        + "            <binding>BINDING#1</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#2</name>\n"
                        + "            <minOccurs>2</minOccurs>\n"
                        + "            <maxOccurs>2</maxOccurs>\n"
                        + "            <nillable>true</nillable>\n"
                        + "            <binding>BINDING#2</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#3</name>\n"
                        + "            <minOccurs>3</minOccurs>\n"
                        + "            <maxOccurs>3</maxOccurs>\n"
                        + "            <nillable>false</nillable>\n"
                        + "            <binding>BINDING#3</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#4</name>\n"
                        + "            <minOccurs>4</minOccurs>\n"
                        + "            <maxOccurs>4</maxOccurs>\n"
                        + "            <nillable>true</nillable>\n"
                        + "            <binding>BINDING#4</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#5</name>\n"
                        + "            <minOccurs>5</minOccurs>\n"
                        + "            <maxOccurs>5</maxOccurs>\n"
                        + "            <nillable>false</nillable>\n"
                        + "            <binding>BINDING#5</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#6</name>\n"
                        + "            <minOccurs>6</minOccurs>\n"
                        + "            <maxOccurs>6</maxOccurs>\n"
                        + "            <nillable>true</nillable>\n"
                        + "            <binding>BINDING#6</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#7</name>\n"
                        + "            <minOccurs>7</minOccurs>\n"
                        + "            <maxOccurs>7</maxOccurs>\n"
                        + "            <nillable>false</nillable>\n"
                        + "            <binding>BINDING#7</binding>\n"
                        + "        </attribute>\n"
                        + "        <attribute>\n"
                        + "            <name>NAME#8</name>\n"
                        + "            <minOccurs>8</minOccurs>\n"
                        + "            <maxOccurs>8</maxOccurs>\n"
                        + "            <nillable>true</nillable>\n"
                        + "            <binding>BINDING#8</binding>\n"
                        + "        </attribute>\n"
                        + "    </attributes>\n"
                        + "</FeatureTypesJackson>"), GPFeatureTypeAttributesJackson.class);
        logger.info("##############GP_FEATURE_TYPE_ATTRIBUTES_JACKSON : \n{}\n", featureTypeAttributesJackson);
    }

    @Test
    public void m_marshallGPFeatureTypeAttributesJacksonAsJsonStringTest() throws Exception {
        IGPFeatureTypeAttributes featureTypeAttributes = toFeatureTypeAttributes(9);
        GPFeatureTypeAttributesJackson featureTypeAttributesJackson = new GPFeatureTypeAttributesJackson();
        featureTypeAttributesJackson.setAttributes(featureTypeAttributes);
        logger.info("##############GP_FEATURE_TYPE_ATTRIBUTES_JACKSON : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(featureTypeAttributesJackson));
    }

    @Test
    public void n_unmarshallGPFeatureTypeAttributesJacksonFromJsonStringTest() throws Exception {
        GPFeatureTypeAttributesJackson featureTypeAttributesJackson = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"FeatureTypesJackson\" : {\n"
                + "    \"attributes\" : {\n"
                + "      \"attribute\" : [ {\n"
                + "        \"name\" : \"NAME#0\",\n"
                + "        \"minOccurs\" : 0,\n"
                + "        \"maxOccurs\" : 0,\n"
                + "        \"nillable\" : true,\n"
                + "        \"binding\" : \"BINDING#0\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#1\",\n"
                + "        \"minOccurs\" : 1,\n"
                + "        \"maxOccurs\" : 1,\n"
                + "        \"nillable\" : false,\n"
                + "        \"binding\" : \"BINDING#1\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#2\",\n"
                + "        \"minOccurs\" : 2,\n"
                + "        \"maxOccurs\" : 2,\n"
                + "        \"nillable\" : true,\n"
                + "        \"binding\" : \"BINDING#2\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#3\",\n"
                + "        \"minOccurs\" : 3,\n"
                + "        \"maxOccurs\" : 3,\n"
                + "        \"nillable\" : false,\n"
                + "        \"binding\" : \"BINDING#3\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#4\",\n"
                + "        \"minOccurs\" : 4,\n"
                + "        \"maxOccurs\" : 4,\n"
                + "        \"nillable\" : true,\n"
                + "        \"binding\" : \"BINDING#4\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#5\",\n"
                + "        \"minOccurs\" : 5,\n"
                + "        \"maxOccurs\" : 5,\n"
                + "        \"nillable\" : false,\n"
                + "        \"binding\" : \"BINDING#5\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#6\",\n"
                + "        \"minOccurs\" : 6,\n"
                + "        \"maxOccurs\" : 6,\n"
                + "        \"nillable\" : true,\n"
                + "        \"binding\" : \"BINDING#6\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#7\",\n"
                + "        \"minOccurs\" : 7,\n"
                + "        \"maxOccurs\" : 7,\n"
                + "        \"nillable\" : false,\n"
                + "        \"binding\" : \"BINDING#7\"\n"
                + "      }, {\n"
                + "        \"name\" : \"NAME#8\",\n"
                + "        \"minOccurs\" : 8,\n"
                + "        \"maxOccurs\" : 8,\n"
                + "        \"nillable\" : true,\n"
                + "        \"binding\" : \"BINDING#8\"\n"
                + "      } ]\n"
                + "    }\n"
                + "  }\n"
                + "}"), GPFeatureTypeAttributesJackson.class);
        logger.info("##############GP_FEATURE_TYPE_ATTRIBUTES_JACKSON : \n{}\n", featureTypeAttributesJackson);
    }

    @Test
    public void o_marshallGPFeatureTypeAttributesJacksonWithNullPropertiesTest() throws Exception {
        GPFeatureTypeAttributesJackson featureTypeAttributesJackson = new GPFeatureTypeAttributesJackson();
        IGPFeatureTypeAttributes featureTypeAttributes = new GPFeatureTypeAttributes();
        featureTypeAttributesJackson.setAttributes(featureTypeAttributes);
        logger.info("##############GP_FEATURE_TYPE_ATTRIBUTES_JACKSON : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(featureTypeAttributesJackson));
    }

    @Test
    public void p_unmarshallGPFeatureTypeAttributesJacksonWithNullPropertiesTest() throws Exception {
        GPFeatureTypeAttributesJackson featureTypeAttributesJackson = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"FeatureTypesJackson\" : {\n"
                        + "    \"attributes\" : {\n"
                        + "      \"attribute\" : [ ]\n"
                        + "    }\n"
                        + "  }\n"
                        + "}"), GPFeatureTypeAttributesJackson.class);
        logger.info("{}\n", featureTypeAttributesJackson);
    }

    @Test
    public void q_marshallGPFeatureTypeAttributesAsJsonStringTest() throws Exception {
        IGPFeatureTypeAttribute featureTypeAttribute = new GPFeatureTypeAttribute();
        featureTypeAttribute.setName("the_geom");
        featureTypeAttribute.setBinding("org.locationtech.jts.geom.Point");
        featureTypeAttribute.setNillable(TRUE);
        featureTypeAttribute.setMinOccurs(0);
        IGPFeatureTypeAttributes featureTypeAttributes = new GPFeatureTypeAttributes();
        featureTypeAttributes.setValues(asList(featureTypeAttribute));
        logger.info("\n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(featureTypeAttributes));
    }

    /**
     * @param number
     * @return {@link GPFeatureTypeAttributes}
     */
    public static GPFeatureTypeAttributes toFeatureTypeAttributes(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPFeatureTypeAttributes featureTypeAttributes = new GPFeatureTypeAttributes();
        featureTypeAttributes.setValues(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPFeatureTypeAttributesJacksonTest::toFeatureTypeAttribute)
                .collect(toList()));
        return featureTypeAttributes;
    }

    /**
     * @param value
     * @return {@link GPFeatureTypeAttribute}
     */
    public static GPFeatureTypeAttribute toFeatureTypeAttribute(Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        GPFeatureTypeAttribute featureTypeAttribute = new GPFeatureTypeAttribute();
        featureTypeAttribute.setBinding("BINDING#" + value);
        featureTypeAttribute.setMaxOccurs(value);
        featureTypeAttribute.setMinOccurs(value);
        featureTypeAttribute.setName("NAME#" + value);
        featureTypeAttribute.setNillable((value % 2 == 0) ? TRUE : FALSE);
        return featureTypeAttribute;
    }

    @Getter
    @Setter
    @ToString
    @XmlRootElement(name = "FeatureTypesJackson")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class GPFeatureTypeAttributesJackson implements Serializable {

        private static final long serialVersionUID = 1860551785898895275L;
        //
        @XmlElement(type = GPFeatureTypeAttributes.class)
        private IGPFeatureTypeAttributes attributes;
    }
}