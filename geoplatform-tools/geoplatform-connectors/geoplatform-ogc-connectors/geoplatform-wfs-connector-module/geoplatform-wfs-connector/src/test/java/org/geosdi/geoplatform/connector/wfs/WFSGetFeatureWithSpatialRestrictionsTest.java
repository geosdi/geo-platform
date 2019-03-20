package org.geosdi.geoplatform.connector.wfs;

import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSGetFeatureWithSpatialRestrictionsTest extends WFSTestConfigurator {

    @Test
    public void a_stateQueryRestrictionBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setPropertyNames(Arrays.asList(new String[]{"WORKERS", "MANUAL", "SUB_REGION"}));
        request.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        request.setSRS("EPSG:4326");
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        Assert.assertTrue(response.getNumberOfFeatures().intValue() == 4);
        logger.info("#############################a_stateQueryBboxTest#ResponseAsString {}\n", request.formatResponseAsString(2));
    }

    @Test
    public void b_stateQueryRestrictionsWithBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setPropertyNames(Arrays.asList(new String[]{"WORKERS", "MANUAL", "SUB_REGION"}));
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
                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                        "            <restriction>2248972.0</restriction>\n" +
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
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        request.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        request.setSRS("EPSG:4326");
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        Assert.assertTrue(response.getNumberOfFeatures().intValue() == 3);
        logger.info("#############################a_stateQueryBboxTest#ResponseAsString {}\n", request.formatResponseAsString(2));
    }

    @Test
    public void c_stateQueryRestrictionsNotInBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setPropertyNames(Arrays.asList(new String[]{"WORKERS", "MANUAL", "SUB_REGION"}));
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
                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                        "            <restriction>2248972.0</restriction>\n" +
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
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        request.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        request.setSRS("EPSG:4326");
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        Assert.assertTrue(response.getNumberOfFeatures().intValue() == 46);
        logger.info("#############################c_stateQueryRestrictionsNotInBboxTest#ResponseAsString {}\n", request.formatResponseAsString(2));
    }

    @Test
    public void d_stateQueryRestrictionNotTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setPropertyNames(Arrays.asList(new String[]{"WORKERS", "MANUAL", "SUB_REGION"}));
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
                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                        "            <restriction>1248972.0</restriction>\n" +
                        "        </queryRestriction>\n" +
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        Assert.assertTrue(response.getNumberOfFeatures().intValue() == 23);
        logger.info("#############################d_stateQueryRestrictionsNotTest#ResponseAsString {}\n", request.formatResponseAsString(2));
    }

    @Test
    public void e_stateQueryRestrictionNotInBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(statesName);
        request.setPropertyNames(Arrays.asList(new String[]{"WORKERS", "MANUAL", "SUB_REGION"}));
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
                        "            <operator>GREATER_OR_EQUAL</operator>\n" +
                        "            <restriction>1248972.0</restriction>\n" +
                        "        </queryRestriction>\n" +
                        "    </queryRestrictionList>\n" +
                        "</QueryDTO>"), QueryDTO.class));
        request.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        request.setSRS("EPSG:4326");
        logger.info("######################\n{}\n", request.showRequestAsString());
        logger.info("#############################e_stateQueryRestrictionNotInBboxTest#ResponseAsString {}\n", request.formatResponseAsString(2));
    }
}