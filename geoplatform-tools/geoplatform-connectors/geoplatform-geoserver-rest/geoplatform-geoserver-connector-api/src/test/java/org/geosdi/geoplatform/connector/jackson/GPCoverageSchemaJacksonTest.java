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

import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.structured.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPCoverageSchemaJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPCoverageSchemaJacksonTest.class);

    @Test
    public void a_marshallGPCoverageGranulesSchemaAsXmlStringTest() throws Exception {
        GPStructuredCoverageSchema coverageGranulesSchema = toCoverageGranulesSchema(10);
        logger.info("###################GP_COVERAGE_GRANULES_SCHEMA : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(coverageGranulesSchema));
    }

    @Test
    public void b_unmarshallGPCoverageGranulesSchemaFromXmlFileTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRANULES_SCHEMA : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources",
                                "GPCoverageGranulesSchema.xml").collect(joining(separator))), GPStructuredCoverageSchema.class));
    }

    @Test
    public void c_marshallGPCoverageGranulesSchemaAsJsonStringTest() throws Exception {
        GPStructuredCoverageSchema coverageGranulesSchema = toCoverageGranulesSchema(10);
        logger.info("###################GP_COVERAGE_GRANULES_SCHEMA : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(coverageGranulesSchema));
    }

    @Test
    public void d_unmarshallGPCoverageGranulesSchemaFromJsonFileTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRANULES_SCHEMA : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources",
                        "GPCoverageGranulesSchema.json").collect(joining(separator))), GPStructuredCoverageSchema.class));
    }

    @Test
    public void e_marshallGPCoverageGranuleAttributesAsXmlStringTest() throws Exception {
        IGPStructuredCoverageAttributes coverageGranuleAttributes = toCoverageGranuleAttributes(10);
        logger.info("###################GP_COVERAGE_GRANULE_ATTRIBUTES : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(coverageGranuleAttributes));
    }

    @Test
    public void f_unmarshallGPCoverageGranuleAttributesFromXmlFileTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRANULE_ATTRIBUTES : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources",
                        "GPCoverageGranuleAttributes.xml").collect(joining(separator))), GPStructuredCoverageAttributes.class));
    }

    @Test
    public void g_marshallGPCoverageGranuleAttributesAsJsonStringTest() throws Exception {
        IGPStructuredCoverageAttributes coverageGranuleAttributes = toCoverageGranuleAttributes(10);
        logger.info("###################GP_COVERAGE_GRANULE_ATTRIBUTES : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(coverageGranuleAttributes));
    }

    @Test
    public void h_unmarshallGPCoverageGranuleAttributesFromJsonFileTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRANULE_ATTRIBUTES : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources",
                        "GPCoverageGranuleAttributes.json").collect(joining(separator))), GPStructuredCoverageAttributes.class));
    }

    @Test
    public void i_marshallGPCoverageGranuleAttributeAsXmlStringTest() throws Exception {
        IGPStructuredCoverageAttribute coverageGranuleAttribute = toCoverageGranuleAttribute(1);
        logger.info("###################GP_COVERAGE_GRANULE_ATTRIBUTE : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(coverageGranuleAttribute));
    }

    @Test
    public void l_unmarshallGPCoverageGranuleAttributeFromXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRANULE_ATTRIBUTE : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Attribute>\n"
                        + "    <name>NAME#1</name>\n"
                        + "    <minOccurs>1</minOccurs>\n"
                        + "    <maxOccurs>1</maxOccurs>\n"
                        + "    <nillable>false</nillable>\n"
                        + "    <binding>BINDING#1</binding>\n"
                        + "</Attribute>"), GPStructuredCoverageAttribute.class));
    }

    @Test
    public void m_marshallGPCoverageGranuleAttributeAsJsonStringTest() throws Exception {
        IGPStructuredCoverageAttribute coverageGranuleAttribute = toCoverageGranuleAttribute(1);
        logger.info("###################GP_COVERAGE_GRANULE_ATTRIBUTE : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(coverageGranuleAttribute));
    }

    @Test
    public void n_unmarshallGPCoverageGranuleAttributeFromJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRANULE_ATTRIBUTE : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"Attribute\" : {\n"
                        + "    \"name\" : \"NAME#1\",\n"
                        + "    \"minOccurs\" : 1,\n"
                        + "    \"maxOccurs\" : 1,\n"
                        + "    \"nillable\" : false,\n"
                        + "    \"binding\" : \"BINDING#1\"\n"
                        + "  }\n"
                        + "}"), GPStructuredCoverageAttribute.class));
    }

    /**
     * @param number
     * @return {@link GPStructuredCoverageSchema}
     */
    public static GPStructuredCoverageSchema toCoverageGranulesSchema(int number) {
        GPStructuredCoverageSchema coverageGranulesSchema = new GPStructuredCoverageSchema();
        coverageGranulesSchema.setAttributes(toCoverageGranuleAttributes(number));
        coverageGranulesSchema.setHref("http://localhost:8080/geoserver/rest/workspaces/wcs/coveragestores/watertemp/coverages/watertemp/index/granules.xml");
        return coverageGranulesSchema;
    }

    /**
     * @param number
     * @return {@link GPStructuredCoverageAttributes}
     */
    public static GPStructuredCoverageAttributes toCoverageGranuleAttributes(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPStructuredCoverageAttributes coverageGranuleAttributes = new GPStructuredCoverageAttributes();
        coverageGranuleAttributes.setValues(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPCoverageSchemaJacksonTest::toCoverageGranuleAttribute)
                .collect(toList()));
        return coverageGranuleAttributes;
    }

    /**
     * @param value
     * @return {@link GPStructuredCoverageAttribute}
     */
    public static GPStructuredCoverageAttribute toCoverageGranuleAttribute(@Nonnull(when = NEVER) Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        GPStructuredCoverageAttribute coverageGranuleAttribute = new GPStructuredCoverageAttribute();
        coverageGranuleAttribute.setBinding("BINDING#" + value);
        coverageGranuleAttribute.setMaxOccurs(value);
        coverageGranuleAttribute.setMinOccurs(value);
        coverageGranuleAttribute.setName("NAME#" + value);
        coverageGranuleAttribute.setNillable((value % 2 == 0) ? TRUE : FALSE);
        return coverageGranuleAttribute;
    }
}