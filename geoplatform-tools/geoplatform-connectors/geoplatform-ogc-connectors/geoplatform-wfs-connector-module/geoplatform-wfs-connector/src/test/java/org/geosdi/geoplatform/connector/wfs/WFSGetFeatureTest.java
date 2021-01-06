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
package org.geosdi.geoplatform.connector.wfs;

import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.FeatureArrayPropertyType;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringReader;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSGetFeatureTest extends WFSTestConfigurator {

    private static QueryDTO queryDTOAnd;

    @BeforeClass
    public static void beforeClass() throws Exception {
        queryDTOAnd = GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "        <queryRestriction>\n" +
                        "            <attribute>\n" +
                        "                <maxOccurs>1</maxOccurs>\n" +
                        "                <minOccurs>0</minOccurs>\n" +
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
    public void statesHits() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.HITS.value());
        request.setTypeName(statesName);
        FeatureCollectionType response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@ STATES Features Found : " + "@@@@@@@@@@@@@@@@@ {}\n\n", response.getNumberOfFeatures());
    }

    @Test
    public void statesHitsQueryRestrictions() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.HITS.value());
        request.setTypeName(statesName);
        request.setQueryDTO(queryDTOAnd);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#############################statesHitsQueryRestrictions#Features {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void secureStatesHits() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.HITS.value());
        request.setTypeName(statesName);
        FeatureCollectionType response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@ SECURE STATES Features Found : " + "@@@@@@@@@@@@@@@@@ {}\n\n", response.getNumberOfFeatures());
    }

    @Test
    public void secureStatesHitsQueryRestrictions() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.HITS.value());
        request.setTypeName(statesName);
        request.setQueryDTO(queryDTOAnd);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("###############################secureStatesHitsQueryRestrictions#Features {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesResults() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setMaxFeatures(BigInteger.ONE);
        logger.info("RESPONSE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", request.getResponseAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("xxxxxxxxxxx {}", response.getNumberOfFeatures());
        logger.info("xxxxxxxxxxx {}", response.getTimeStamp());
        FeatureArrayPropertyType featureMembers = response.getFeatureMembers();
        logger.info("----------- {}", featureMembers.isSetFeature());
        logger.info("----------- {}", featureMembers.getFeature());
        logger.info("----------- {}", featureMembers.getFeature().size());
        logger.info("+++++++++++ {}", response.getFeatureMember());
        logger.info("+++++++++++ {}\n\n", response.getFeatureMember().size());
    }

    @Test
    public void statesFeatureIDs() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setFeatureIDs(Arrays.asList("states.1", "states.49"));
        logger.info("RESPONSE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", request.getResponseAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#############################statesFeatureIDs#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesBBox() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setPropertyNames(Arrays.asList(new String[]{"STATE_NAME", "PERSONS"}));
        request.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        request.setSRS("EPSG:4326");
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : \n{}\n", request.getResponseAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##################################statesBBox#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "</QueryDTO>"), QueryDTO.class));

        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##############################statesContainsRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesSecureContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("###################################statesSecureContainsRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesNotContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "</QueryDTO>"), QueryDTO.class));

        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##################################statesNotContainsRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesSecureNotContainsRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#####################################statesSecureNotContainsRestrictionTest#Features {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "            <restriction>6000000</restriction>\n" +
                        "        </queryRestriction>\n" +
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#########################################statesGreatherThanRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesSecureGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "            <restriction>6000000</restriction>\n" +
                        "        </queryRestriction>\n" +
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("#############################statesSecureGreatherThanRestrictionTest#Features : {}\n",
                response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesNotGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "            <restriction>6000000</restriction>\n" +
                        "        </queryRestriction>\n" +
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("##################################statesNotGreatherThanRestrictionTest#Features {}\n",
                response.getNumberOfFeatures().intValue());
    }

    @Test
    public void statesSecureNotGreatherThanRestrictionTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = secureServerConnector.createGetFeatureRequest();
        request.setTypeName(statesName);
        request.setResultType(ResultTypeType.HITS.value());
        request.setQueryDTO(GPJAXBContextBuilder.newInstance()
                .unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                        "            <restriction>6000000</restriction>\n" +
                        "        </queryRestriction>\n" +
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("########################################statesSecureNotGreatherThanRestrictionTest#Features : {}\n", response.getNumberOfFeatures().intValue());
    }
}