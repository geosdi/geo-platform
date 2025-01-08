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
package org.geosdi.geoplatform.connector.wfs.prosit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.csv.support.model.IGPCSVBaseSchema;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.locationtech.jts.geom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.File;
import java.net.URL;

import static java.io.File.separator;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.CSV;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.GEOJSON;
import static org.geosdi.geoplatform.gui.shared.wfs.OperatorType.LIKE;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.HITS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
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
                            .build())
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static GPWFSConnectorStore serverConnector;

    @Test
    public void a_searchStartWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.STARTS_WITH, "te");
        queryDTO.setQueryRestrictionList(asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 7);
    }

    @Test
    public void b_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.ENDS_WITH, "te");
        queryDTO.setQueryRestrictionList(asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 0);
    }

    @Test
    public void c_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.ENDS_WITH, "sto");
        queryDTO.setQueryRestrictionList(asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 1);
    }

    @Test
    public void d_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, OperatorType.ENDS_WITH, "sto1");
        queryDTO.setQueryRestrictionList(asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 1);
    }

    @Test
    public void e_searchLikeLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setResultType(RESULTS.value());
        request.setTypeName(new QName("admin:tempo"));
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setMatchOperator("ALL");
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setName("string");
        attributeDTO.setType("string");
        QueryRestrictionDTO queryRestrictionDTO = new QueryRestrictionDTO(attributeDTO, LIKE, "testo");
        queryDTO.setQueryRestrictionList(asList(queryRestrictionDTO));
        request.setQueryDTO(queryDTO);
        logger.info("######################\n{}\n", request.showRequestAsString());
        FeatureCollectionType response = request.getResponse();
        assertTrue(response.getNumberOfFeatures().intValue() == 7);
    }

    @Test
    public void f_adminSHPComCqlFilterTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(HITS.value());
        request.setCqlFilter("(COMUNE like 'AVIGLIANO' OR PRO_COM = 77014 OR COMUNE like 'T%')");
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("#########################################RESPONSE : {}\n", request.getResponseAsString());
    }

    @Test
    public void g_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("#########################################RESPONSE : {}\n", request.getResponseAsString());
    }

    @Test
    public void h_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        request.setOutputFormat(GEOJSON);
        request.setMaxFeatures(valueOf(2));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("#########################################RESPONSE : {}\n", request.getResponse().getFeatures().size());
    }

    @Test
    public void i_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        request.setMaxFeatures(valueOf(2));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("#########################################RESPONSE : {}\n", request.getResponse().getNumberOfFeatures());
    }

    @Test
    public void l_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<IGPCSVBaseSchema> request = serverConnector.createGetFeatureRequest();
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        request.setOutputFormat(CSV);
        request.setMaxFeatures(valueOf(2));
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", request.showRequestAsString());
        logger.info("#########################################RESPONSE : {}\n", request.getResponse().getHeaders());
    }

    @Test
    public void m_createGeoJsonPolygonTest() throws Exception {
        Polygon polygon = toPolygon(new Envelope(new Coordinate(14.403076171875002, 38.83542884007305), new Coordinate(19.368896484375004, 40.94671366508002)), 4326);
        ObjectMapper mapper = new GPJacksonJTSSupport().getDefaultMapper();
        mapper.writeValue(new File(of(new File(".").getCanonicalPath(), "target", "Polygon.json")
                .collect(joining(separator))), polygon);
    }

    /**
     * @param env
     * @param srid
     * @return {@link Polygon}
     */
    static Polygon toPolygon(Envelope env, int srid) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        final Coordinate[] coords = new Coordinate[5];
        coords[0] = new Coordinate( env.getMinX(), env.getMinY() );
        coords[1] = new Coordinate( env.getMinX(), env.getMaxY() );
        coords[2] = new Coordinate( env.getMaxX(), env.getMaxY() );
        coords[3] = new Coordinate( env.getMaxX(), env.getMinY() );
        coords[4] = new Coordinate( env.getMinX(), env.getMinY() );
        final LinearRing shell = geometryFactory.createLinearRing( coords );
        final Polygon pg = geometryFactory.createPolygon( shell, null );
        pg.setSRID( srid );
        return pg;
    }
}