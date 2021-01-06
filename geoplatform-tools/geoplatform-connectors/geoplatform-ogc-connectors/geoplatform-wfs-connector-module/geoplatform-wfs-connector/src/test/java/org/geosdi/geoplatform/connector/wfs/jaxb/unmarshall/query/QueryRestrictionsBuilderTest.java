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
package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall.query;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler.WFSQueryRestrictionsBuilder.builder;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class QueryRestrictionsBuilderTest {

    private final static Logger logger = LoggerFactory.getLogger(QueryRestrictionsBuilderTest.class);
    //
    private static QueryDTO queryDTOAnd;
    private static QueryDTO queryDTOOr;
    private static QueryDTO queryDTONot;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "unmarshall", "query")
                .collect(joining(separator, "", separator));
        File queryDTOAndFile = new File(basePath.concat("wfsQueryAnd.xml"));
        queryDTOAnd = GPJAXBContextBuilder.newInstance().unmarshal(queryDTOAndFile, QueryDTO.class);
        logger.info("####################QUERY_DTO_AND : {}\n\n", queryDTOAnd);
        File queryDTOOrFile = new File(basePath.concat("wfsQueryOr.xml"));
        queryDTOOr = GPJAXBContextBuilder.newInstance().unmarshal(queryDTOOrFile, QueryDTO.class);
        logger.info("####################QUERY_DTO_OR : {}\n\n", queryDTOOr);
        File queryDTONotFile = new File(basePath.concat("wfsQueryNot.xml"));
        queryDTONot = GPJAXBContextBuilder.newInstance().unmarshal(queryDTONotFile, QueryDTO.class);
        logger.info("####################QUERY_DTO_NOT : {}\n\n", queryDTOOr);
    }

    @Test
    public void a_wfsQueryRestrictionsBuilderAndTest() throws Exception {
        logger.info("##################FILTER_CREATED_AND : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(queryDTOAnd)
                .build());
    }

    @Test
    public void b_wfsQueryRestrictionsBuilderOrTest() throws Exception {
        logger.info("##################FILTER_CREATED_OR : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(queryDTOOr)
                .build());
    }

    @Test
    public void c_wfsQueryRestrictionsBuilderNotTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(queryDTONot)
                .build());
    }

    @Test
    public void d_wfsQueryRestrictionsBuilderNotStringTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_FROM_STRING : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>CONTAINS</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void e_wfsQueryRestrictionsBuilderAndGreatherTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>GREATER</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void f_wfsQueryRestrictionsBuilderAndGreatherOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER_OR_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void g_wfsQueryRestrictionsBuilderOrGreatherTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>GREATER</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void h_wfsQueryRestrictionsBuilderNotGreatherTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_GREATHER : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>GREATER</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void i_wfsQueryRestrictionsBuilderOrGreatherOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_GREATHER_OR_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void l_wfsQueryRestrictionsBuilderNotGreatherOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_GREATHER_OR_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void m_wfsQueryRestrictionsBuilderAndLessTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void n_wfsQueryRestrictionsBuilderAndLessOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS_OR_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void o_wfsQueryRestrictionsBuilderOrLessTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void p_wfsQueryRestrictionsBuilderNotLessTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_LESS : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void q_wfsQueryRestrictionsBuilderOrLessOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_LESS_OR_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void r_wfsQueryRestrictionsBuilderNotLessOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_LESS_OR_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void s_wfsQueryRestrictionsBuilderAndEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>EQUAL</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void t_wfsQueryRestrictionsBuilderOrEqualTest() throws Exception {
        logger.info("##################FILTER_CREATED_EQUAL : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>EQUAL</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void u_wfsQueryRestrictionsBuilderAndContainsTest() throws Exception {
        logger.info("##################FILTER_CREATED_CONTAINS : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>CONTAINS</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void v_wfsQueryRestrictionsBuilderOrContainsTest() throws Exception {
        logger.info("##################FILTER_CREATED_CONTAINS : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>CONTAINS</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void w_wfsQueryRestrictionsBuilderNotContainsTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_CONTAINS : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>CONTAINS</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void x_wfsQueryRestrictionsBuilderAndStartWithTest() throws Exception {
        logger.info("##################FILTER_CREATED_START_WITH : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>STARTS_WITH</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void y_wfsQueryRestrictionsBuilderOrStartWithTest() throws Exception {
        logger.info("##################FILTER_CREATED_START_WITH : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ANY</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>STARTS_WITH</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void z_wfsQueryRestrictionsBuilderNotStartWithTest() throws Exception {
        logger.info("##################FILTER_CREATED_NOT_START_WITH : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>NONE</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>SUB_REGION</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>string</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>STARTS_WITH</operator>\n" +
                                        "            <restriction>Mtn</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class))
                .build());
    }

    @Test
    public void j_wfsQueryRestrictionsBuilderEmptyQueryTest() throws Exception {
        logger.info("##################FILTER_CREATED : {}\n", builder().withFilterType(new FilterType())
                .withQueryDTO(new QueryDTO())
                .build());
    }
}