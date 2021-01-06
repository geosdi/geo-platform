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
package org.geosdi.geoplatform.connector.wfs.jaxb.marshall;

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import static java.lang.Boolean.FALSE;
import static org.geosdi.geoplatform.gui.shared.wfs.OperatorType.CONTAINS;
import static org.geosdi.geoplatform.gui.shared.wfs.OperatorType.EQUAL;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionMarshallTest {

    private final static Logger logger = LoggerFactory.getLogger(QueryRestrictionMarshallTest.class);
    //
    private static final GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static final GPJacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE)
            .configure(NON_NULL);

    @Test
    public void a_marshallQueryDTOWithGeometryTest() throws Exception {
        QueryDTO queryDTO = createQueryDTOWithGeometryAttribute();
        StringWriter stringWriter = new StringWriter();
        jaxbContextBuilder.marshal(queryDTO, stringWriter);
        logger.info("#########################QUERY_DTO_AS_STRING : \n{}\n", stringWriter);
    }

    @Test
    public void b_unmarshallQueryDTOWithGeometryTest() throws Exception {
        QueryDTO queryDTO = jaxbContextBuilder.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<QueryDTO>\n" +
                "    <matchOperator>NONE</matchOperator>\n" +
                "    <queryRestrictionList>\n" +
                "        <queryRestriction>\n" +
                "            <geometryAttribute>\n" +
                "                <type>GEOMETRY</type>\n" +
                "                <name>the_geom</name>\n" +
                "                <maxOccurs>1</maxOccurs>\n" +
                "                <minOccurs>1</minOccurs>\n" +
                "                <nillable>false</nillable>\n" +
                "                <srid>4326</srid>\n" +
                "            </geometryAttribute>\n" +
                "            <operator>CONTAINS</operator>\n" +
                "            <restriction>VALUE_TEST</restriction>\n" +
                "        </queryRestriction>\n" +
                "    </queryRestrictionList>\n" +
                "</QueryDTO>"), QueryDTO.class);
        logger.info("#######################QUERY_DTO : {}\n", queryDTO);
    }

    @Test
    public void c_marshallQueryDTOWithAttributeTest() throws Exception {
        QueryDTO queryDTO = createQueryDTOWithAttribute();
        StringWriter stringWriter = new StringWriter();
        jaxbContextBuilder.marshal(queryDTO, stringWriter);
        logger.info("#########################QUERY_DTO_AS_STRING : \n{}\n", stringWriter);
    }

    @Test
    public void d_unmarshallQueryDTOWithAttributeTest() throws Exception {
        QueryDTO queryDTO = jaxbContextBuilder.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<QueryDTO>\n" +
                "    <matchOperator>NONE</matchOperator>\n" +
                "    <queryRestrictionList>\n" +
                "        <queryRestriction>\n" +
                "            <attribute>\n" +
                "                <type>string</type>\n" +
                "                <name>WORKERS</name>\n" +
                "                <maxOccurs>1</maxOccurs>\n" +
                "                <minOccurs>1</minOccurs>\n" +
                "                <nillable>false</nillable>\n" +
                "            </attribute>\n" +
                "            <operator>EQUAL</operator>\n" +
                "            <restriction>WORKERS_TEST</restriction>\n" +
                "        </queryRestriction>\n" +
                "    </queryRestrictionList>\n" +
                "</QueryDTO>"), QueryDTO.class);
        logger.info("#######################QUERY_DTO : {}\n", queryDTO);
    }

    @Test
    public void e_marshallQueryDTOWithGeometryAsJsonTest() throws Exception {
        QueryDTO queryDTO = createQueryDTOWithGeometryAttribute();
        logger.info("########################QUERY_DTO_AS_JSON : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(queryDTO));
    }

    @Test
    public void f_unmarshallQueryDTOWithGeometryAsJsonTest() throws Exception {
        QueryDTO queryDTO = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n" +
                        "  \"matchOperator\" : \"NONE\",\n" +
                        "  \"queryRestriction\" : [ {\n" +
                        "    \"attribute\" : {\n" +
                        "      \"geometryAttribute\" : {\n" +
                        "        \"type\" : \"GEOMETRY\",\n" +
                        "        \"name\" : \"the_geom\",\n" +
                        "        \"maxOccurs\" : 1,\n" +
                        "        \"minOccurs\" : 1,\n" +
                        "        \"nillable\" : false,\n" +
                        "        \"srid\" : 4326\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"operator\" : \"CONTAINS\",\n" +
                        "    \"restriction\" : \"VALUE_TEST\"\n" +
                        "  } ]\n" +
                        "}"), QueryDTO.class);
        logger.info("#########################QUERY_DTO : {}\n", queryDTO);
    }

    @Test
    public void g_marshallQueryDTOWithAttributeAsJsonTest() throws Exception {
        QueryDTO queryDTO = createQueryDTOWithAttribute();
        logger.info("########################QUERY_DTO_AS_JSON : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(queryDTO));
    }

    @Test
    public void h_unmarshallQueryDTOWithAttributeAsJsonTest() throws Exception {
        QueryDTO queryDTO = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n" +
                        "  \"matchOperator\" : \"NONE\",\n" +
                        "  \"queryRestriction\" : [ {\n" +
                        "    \"attribute\" : {\n" +
                        "      \"attribute\" : {\n" +
                        "        \"type\" : \"string\",\n" +
                        "        \"name\" : \"WORKERS\",\n" +
                        "        \"maxOccurs\" : 1,\n" +
                        "        \"minOccurs\" : 1,\n" +
                        "        \"nillable\" : false\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"operator\" : \"EQUAL\",\n" +
                        "    \"restriction\" : \"WORKERS_TEST\"\n" +
                        "  } ]\n" +
                        "}"), QueryDTO.class);
        logger.info("#########################QUERY_DTO : {}\n", queryDTO);
    }

    /**
     * @return {@link QueryDTO}
     */
    public static QueryDTO createQueryDTOWithGeometryAttribute() {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("NONE");
        GeometryAttributeDTO geometryAttributeDTO = new GeometryAttributeDTO();
        geometryAttributeDTO.setSrid(4326);
        geometryAttributeDTO.setMaxOccurs(1);
        geometryAttributeDTO.setMinOccurs(1);
        geometryAttributeDTO.setName("the_geom");
        geometryAttributeDTO.setType("GEOMETRY");
        geometryAttributeDTO.setNillable(FALSE);
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(geometryAttributeDTO, CONTAINS,
                "VALUE_TEST");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        return queryDTO;
    }

    /**
     * @return {@link QueryDTO}
     */
    public static QueryDTO createQueryDTOWithAttribute() {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("NONE");
        AttributeDTO geometryAttributeDTO = new AttributeDTO();
        geometryAttributeDTO.setMaxOccurs(1);
        geometryAttributeDTO.setMinOccurs(1);
        geometryAttributeDTO.setName("WORKERS");
        geometryAttributeDTO.setType("string");
        geometryAttributeDTO.setNillable(FALSE);
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(geometryAttributeDTO, EQUAL, "WORKERS_TEST");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        return queryDTO;
    }
}