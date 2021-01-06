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
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.junit.FixMethodOrder;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.io.StringReader;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
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
        assertTrue(response.getNumberOfFeatures().intValue() == 4);
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
        assertTrue(response.getNumberOfFeatures().intValue() == 3);
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
        assertTrue(response.getNumberOfFeatures().intValue() == 46);
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
        assertTrue(response.getNumberOfFeatures().intValue() == 23);
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

    @Test
    public void f_queryWithBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(new QName("topp:admin_shp_com2016_wgs84_g"));
        request.setBBox(new BBox(16.13123416900635, 40.83818500873241, 16.138143539428714, 40.84040902994519));
        request.setSRS("EPSG:4326");
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        logger.info("########################FEATURES : {}\n", response.getNumberOfFeatures());
    }
}