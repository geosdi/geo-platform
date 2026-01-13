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
package org.geosdi.geoplatform.connector.wfs;

import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.csv.support.model.IGPCSVBaseSchema;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;

import static java.math.BigInteger.ONE;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.CSV;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.GEOJSON;
import static org.geosdi.geoplatform.jaxb.jakarta.GPJAXBJakartaContextBuilder.jakartaContextBuilder;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.HITS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSGetFeatureTest extends WFSTestConfigurator {

    private static QueryDTO queryDTOAnd;

    @BeforeClass
    public static void beforeClass() throws Exception {
        queryDTOAnd = jakartaContextBuilder().unmarshal(new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + "<QueryDTO>\n" + "    <matchOperator>ALL</matchOperator>\n" + "    <queryRestrictionList>\n" + "        <queryRestriction>\n" + "            <attribute>\n" + "                <maxOccurs>1</maxOccurs>\n" + "                <minOccurs>0</minOccurs>\n" + "                <name>WORKERS</name>\n" + "                <nillable>true</nillable>\n" + "                <type>double</type>\n" + "                <value></value>\n" + "            </attribute>\n" + "            <operator>GREATER_OR_EQUAL</operator>\n" + "            <restriction>0.25</restriction>\n" + "        </queryRestriction>\n" + "        <queryRestriction>\n" + "            <attribute>\n" + "                <maxOccurs>1</maxOccurs>\n" + "                <minOccurs>0</minOccurs>\n" +
                        "                <name>MANUAL</name>\n" +
                        "                <nillable>true</nillable>\n" +
                        "                <type>double</type>\n" +
                        "                <value></value>\n" +
                        "            </attribute>\n" +
                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                        "            <restriction>0.25</restriction>\n" +
                        "        </queryRestriction>\n" +
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
                        "</QueryDTO>"), QueryDTO.class);
        logger.info("####################QUERY_DTO_AND : {}\n\n", queryDTOAnd);
    }

    @Test
    public void a_statesHits() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(HITS.value());
        request.setTypeName(statesName);
        FeatureCollectionType response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@ STATES Features Found : " + "@@@@@@@@@@@@@@@@@ {}\n\n", response.getNumberOfFeatures());
    }

    @Test
    public void b_statesHitsQueryRestrictions() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(HITS.value());
        request.setTypeName(statesName);
        request.setQueryDTO(queryDTOAnd);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#############################statesHitsQueryRestrictions#Features {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void c_secureStatesHits() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setResultType(HITS.value());
        request.setTypeName(statesName);
        FeatureCollectionType response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@ SECURE STATES Features Found : " + "@@@@@@@@@@@@@@@@@ {}\n\n", response.getNumberOfFeatures());
    }

    @Test
    public void d_secureStatesHitsQueryRestrictions() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setResultType(HITS.value());
        request.setTypeName(statesName);
        request.setQueryDTO(queryDTOAnd);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("###############################secureStatesHitsQueryRestrictions#Features {}\n", request.getResponseAsString());
    }

    @Test
    public void e_statesResults() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setOutputFormat(GEOJSON);
        request.setMaxFeatures(ONE);
        FeatureCollection response = request.getResponse();
        logger.info("xxxxxxxxxxx {}", response.getFeatures().size());
    }

    @Test
    public void f_statesFeatureIDs() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setOutputFormat(GEOJSON);
        request.setFeatureIDs(Arrays.asList("states.1", "states.49"));
        logger.info("RESPONSE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", request.getResponseAsString());
        FeatureCollection response = request.getResponse();
        logger.info("#############################statesFeatureIDs#Features : {}\n", response.getFeatures().size());
    }

    @Test
    public void g_statesBBox() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setOutputFormat(GEOJSON);
        request.setPropertyNames(Arrays.asList(new String[]{"STATE_NAME", "PERSONS"}));
        request.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        request.setSRS("EPSG:4326");
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : \n{}\n", request.getResponseAsString());
        FeatureCollection response = request.getResponse();
        logger.info("##################################statesBBox#Features : {}\n", response.getFeatures().size());
    }

    @Test
    public void h_statesContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>ALL</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>SUB_REGION</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>string</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>CONTAINS</operator>\n"
                                + "            <restriction>Mtn</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));

        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##############################statesContainsRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

//    @Ignore(value = "Server Problem")
    @Test
    public void i_statesSecureContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>ALL</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>SUB_REGION</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>string</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>CONTAINS</operator>\n"
                                + "            <restriction>Mtn</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("###################################statesSecureContainsRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void l_statesNotContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>ALL</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>SUB_REGION</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>string</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>LIKE</operator>\n"
                                + "            <restriction>Mtn</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##################################statesNotContainsRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Ignore(value = "Server Problem")
    @Test
    public void m_statesSecureNotContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>NONE</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>SUB_REGION</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>string</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>CONTAINS</operator>\n"
                                + "            <restriction>Mtn</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#####################################statesSecureNotContainsRestrictionTest#Features {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void n_statesGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>ALL</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>WORKERS</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>double</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>GREATER</operator>\n"
                                + "            <restriction>6000000</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#########################################statesGreatherThanRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Ignore(value = "Server Problem")
    @Test
    public void o_statesSecureGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>ALL</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>WORKERS</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>double</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>GREATER</operator>\n"
                                + "            <restriction>6000000</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#############################statesSecureGreatherThanRestrictionTest#Features : {}\n",
                response.getNumberOfFeatures().intValue());
    }

    @Test
    public void p_statesNotGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(HITS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>NONE</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>WORKERS</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>double</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>GREATER</operator>\n"
                                + "            <restriction>6000000</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##################################statesNotGreatherThanRestrictionTest#Features {}\n",
                response.getNumberOfFeatures().intValue());
    }

    @Ignore(value = "Server Problem")
    @Test
    public void q_statesSecureNotGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(RESULTS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>NONE</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>WORKERS</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>double</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>GREATER</operator>\n"
                                + "            <restriction>6000000</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        request.setOutputFormat(GEOJSON);
        FeatureCollection response = request.getResponse();
        logger.info("########################################statesSecureNotGreatherThanRestrictionTest#Features : {}\n", response.getFeatures().size());
    }

    @Ignore(value = "Server Problem")
    @Test
    public void r_statesSecureNotGreatherThanRestrictionAsGeoJsonTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(RESULTS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>NONE</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>WORKERS</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>double</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>GREATER</operator>\n"
                                + "            <restriction>6000000</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        request.setOutputFormat(GEOJSON);
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollection response = request.getResponse();
        logger.info("########################################statesSecureNotGreatherThanRestrictionTest#Features : {}\n", response.getFeatures().size());
    }

    @Test
    public void s_statesSecureNotGreatherThanRestrictionAsCsvTest() throws Exception {
        WFSGetFeatureRequest<IGPCSVBaseSchema> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(RESULTS.value());
        request.setQueryDTO(jakartaContextBuilder().unmarshal(new StringReader(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                + "<QueryDTO>\n"
                                + "    <matchOperator>NONE</matchOperator>\n"
                                + "    <queryRestrictionList>\n"
                                + "        <queryRestriction>\n"
                                + "            <attribute>\n"
                                + "                <maxOccurs>1</maxOccurs>\n"
                                + "                <minOccurs>0</minOccurs>\n"
                                + "                <name>WORKERS</name>\n"
                                + "                <nillable>true</nillable>\n"
                                + "                <type>double</type>\n"
                                + "                <value></value>\n"
                                + "            </attribute>\n"
                                + "            <operator>GREATER</operator>\n"
                                + "            <restriction>6000000</restriction>\n"
                                + "        </queryRestriction>\n"
                                + "    </queryRestrictionList>\n"
                                + "</QueryDTO>"), QueryDTO.class));
        request.setOutputFormat(CSV);
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        IGPCSVBaseSchema response = request.getResponse();
        logger.info("########################################statesSecureNotGreatherThanRestrictionTest#Features : {}\n", response.getRowAttributes().size());
    }
}