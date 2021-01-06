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
package org.geosdi.geoplatform.connector.wfs.prosit;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Arrays;

import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPWFSGetFeatureLayerTempoTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSGetFeatureLayerTempoTest.class);

    //
    static {
        try {
            serverConnector = newConnector()
                    .withServerUrl(new URL("https://prosit.geosdi.org/geoserver/wfs"))
                    .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                            .withMaxTotalConnections(40)
                            .withDefaultMaxPerRoute(20)
                            .withMaxRedirect(10)
                            .build()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static GPWFSConnectorStore serverConnector;

    @Test
    public void a_searchStartWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.STARTS_WITH, "te");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 7);
    }

    @Test
    public void b_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.ENDS_WITH, "te");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 0);
    }

    @Test
    public void c_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.ENDS_WITH, "sto");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 1);
    }

    @Test
    public void d_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.ENDS_WITH, "sto1");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 1);
    }

    @Test
    public void e_searchLikeLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.LIKE, "testo");
        queryDTO.setQueryRestrictionList(Arrays.asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 7);
    }
}