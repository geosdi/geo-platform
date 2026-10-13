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
package org.geosdi.geoplatform.connector.wfs.prosit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.FeatureCollection;
import org.apache.hc.client5.http.auth.CredentialsStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.geojson.Feature;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.server.request.v110.WFSGetFeatureRequestV110;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.csv.support.model.GPCSVBaseSchema;
import org.geosdi.geoplatform.csv.support.model.IGPCSVBaseSchema;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.locationtech.jts.geom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.File;
import java.math.BigInteger;
import java.net.URI;

import static java.io.File.separator;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.CSV;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.GEOJSON;
import static org.geosdi.geoplatform.gui.shared.wfs.OperatorType.LIKE;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.HITS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPWFSGetFeatureLayerTempoTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSGetFeatureLayerTempoTest.class);
    //
    private static final String PROSIT_WFS_URL = "https://prosit.geosdi.org/geoserver/wfs";
    //
    private GPWFSConnectorStore serverConnector;
    private WFSGetFeatureRequest<Object> getFeatureRequest;

    /**
     * The prosit GeoServer is mocked : the {@link GPWFSConnectorStore} returns a real {@link WFSGetFeatureRequestV110}
     * (so the GetFeature request is really built) built on a mocked {@link GPServerConnector}, and only the server
     * responses are stubbed.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        GPServerConnector mockServer = mock(GPServerConnector.class);
        when(mockServer.getURI()).thenReturn(new URI(PROSIT_WFS_URL));
        when(mockServer.getCredentialsStore()).thenReturn(mock(CredentialsStore.class));
        when(mockServer.getClientConnection()).thenReturn(mock(CloseableHttpClient.class));
        when(mockServer.getPooledConnectorConfig()).thenReturn(pooledConnectorConfigBuilder()
                .withMaxTotalConnections(40)
                .withDefaultMaxPerRoute(20)
                .withMaxRedirect(10)
                .build());
        this.getFeatureRequest = spy(new WFSGetFeatureRequestV110(mockServer));
        this.serverConnector = mock(GPWFSConnectorStore.class);
        doReturn(this.getFeatureRequest).when(this.serverConnector).createGetFeatureRequest();
    }

    @Test
    public void a_searchStartWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(featureCollectionType(7));
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
        String requestAsString = request.showRequestAsString();
        logger.info("######################\n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"results\"", "<ogc:PropertyIsLike", "typeName=\"admin:tempo\"", "<ogc:PropertyName>string</ogc:PropertyName>", "<ogc:Literal>te%</ogc:Literal>");
        FeatureCollectionType response = request.getResponse();
        assertEquals(7, response.getNumberOfFeatures().intValue());
        verify(this.getFeatureRequest).getResponse();
    }

    @Test
    public void b_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(featureCollectionType(0));
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
        String requestAsString = request.showRequestAsString();
        logger.info("######################\n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"results\"", "<ogc:PropertyIsLike", "typeName=\"admin:tempo\"", "<ogc:PropertyName>string</ogc:PropertyName>", "<ogc:Literal>%te</ogc:Literal>");
        FeatureCollectionType response = request.getResponse();
        assertEquals(0, response.getNumberOfFeatures().intValue());
        verify(this.getFeatureRequest).getResponse();
    }

    @Test
    public void c_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(featureCollectionType(1));
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
        String requestAsString = request.showRequestAsString();
        logger.info("######################\n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"results\"", "<ogc:PropertyIsLike", "typeName=\"admin:tempo\"", "<ogc:PropertyName>string</ogc:PropertyName>", "<ogc:Literal>%sto</ogc:Literal>");
        FeatureCollectionType response = request.getResponse();
        assertEquals(1, response.getNumberOfFeatures().intValue());
        verify(this.getFeatureRequest).getResponse();
    }

    @Test
    public void d_searchEndsWithLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(featureCollectionType(1));
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
        String requestAsString = request.showRequestAsString();
        logger.info("######################\n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"results\"", "<ogc:PropertyIsLike", "typeName=\"admin:tempo\"", "<ogc:PropertyName>string</ogc:PropertyName>", "<ogc:Literal>%sto1</ogc:Literal>");
        FeatureCollectionType response = request.getResponse();
        assertEquals(1, response.getNumberOfFeatures().intValue());
        verify(this.getFeatureRequest).getResponse();
    }

    @Test
    public void e_searchLikeLayerTempoTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(featureCollectionType(7));
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
        String requestAsString = request.showRequestAsString();
        logger.info("######################\n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"results\"", "<ogc:PropertyIsLike", "typeName=\"admin:tempo\"", "<ogc:PropertyName>string</ogc:PropertyName>", "<ogc:Literal>%testo%</ogc:Literal>");
        FeatureCollectionType response = request.getResponse();
        assertEquals(7, response.getNumberOfFeatures().intValue());
        verify(this.getFeatureRequest).getResponse();
    }

    @Test
    public void f_adminSHPComCqlFilterTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponseAsString("<wfs:FeatureCollection xmlns:wfs=\"http://www.opengis.net/wfs\" numberOfFeatures=\"4\" timeStamp=\"2026-10-13T08:00:00.000Z\"/>");
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(HITS.value());
        request.setCqlFilter("(COMUNE like 'AVIGLIANO' OR PRO_COM = 77014 OR COMUNE like 'T%')");
        String requestAsString = request.showRequestAsString();
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"hits\"", "typeName=\"admin:admin_shp_comuni\"", "<ogc:Or>", "<ogc:Literal>AVIGLIANO</ogc:Literal>", "<ogc:PropertyIsEqualTo>", "<ogc:Literal>77014</ogc:Literal>", "<ogc:Literal>T%</ogc:Literal>");
        String response = request.getResponseAsString();
        logger.info("#########################################RESPONSE : {}\n", response);
        assertTrue(response.contains("numberOfFeatures=\"4\""));
    }

    @Test
    public void g_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponseAsString("<wfs:FeatureCollection xmlns:wfs=\"http://www.opengis.net/wfs\" numberOfFeatures=\"0\" timeStamp=\"2026-10-13T08:00:00.000Z\"/>");
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        String requestAsString = request.showRequestAsString();
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", requestAsString);
        assertRequestContains(requestAsString, "resultType=\"results\"", "typeName=\"admin:admin_shp_comuni\"", "<ogc:BBOX>", "<gml:lowerCorner>14.403076171875002 38.83542884007305</gml:lowerCorner>", "<gml:upperCorner>19.368896484375004 40.94671366508002</gml:upperCorner>");
        String response = request.getResponseAsString();
        logger.info("#########################################RESPONSE : {}\n", response);
        assertTrue(response.contains("numberOfFeatures=\"0\""));
    }

    @Test
    public void h_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollection> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(geoJsonFeatureCollection("AVIGLIANO", "MATERA"));
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        request.setOutputFormat(GEOJSON);
        request.setMaxFeatures(valueOf(2));
        String requestAsString = request.showRequestAsString();
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", requestAsString);
        assertRequestContains(requestAsString, "outputFormat=\"application/json\"", "maxFeatures=\"2\"", "<ogc:BBOX>", "<gml:lowerCorner>14.403076171875002 38.83542884007305</gml:lowerCorner>", "<gml:upperCorner>19.368896484375004 40.94671366508002</gml:upperCorner>");
        FeatureCollection response = request.getResponse();
        logger.info("#########################################RESPONSE : {}\n", response.getFeatures().size());
        assertEquals(2, response.getFeatures().size());
    }

    @Test
    public void i_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<FeatureCollectionType> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(featureCollectionType(2));
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        request.setMaxFeatures(valueOf(2));
        String requestAsString = request.showRequestAsString();
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", requestAsString);
        assertRequestContains(requestAsString, "outputFormat=\"text/xml; subtype=gml/3.1.1\"", "maxFeatures=\"2\"", "<ogc:BBOX>", "<gml:lowerCorner>14.403076171875002 38.83542884007305</gml:lowerCorner>", "<gml:upperCorner>19.368896484375004 40.94671366508002</gml:upperCorner>");
        FeatureCollectionType response = request.getResponse();
        logger.info("#########################################RESPONSE : {}\n", response.getNumberOfFeatures());
        assertEquals(2, response.getNumberOfFeatures().intValue());
    }

    @Test
    public void l_adminSHPComCqlFilterAndBboxTest() throws Exception {
        WFSGetFeatureRequest<IGPCSVBaseSchema> request = serverConnector.createGetFeatureRequest();
        this.mockGetFeatureResponse(csvSchema("FID", "the_geom", "COD_REG", "PRO_COM", "COMUNE"));
        request.setTypeName(new QName("admin:admin_shp_comuni"));
        request.setResultType(RESULTS.value());
        request.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        request.setOutputFormat(CSV);
        request.setMaxFeatures(valueOf(2));
        String requestAsString = request.showRequestAsString();
        logger.info("#############################REQUEST_AS_STRING : \n{}\n", requestAsString);
        assertRequestContains(requestAsString, "outputFormat=\"csv\"", "maxFeatures=\"2\"", "<ogc:BBOX>", "<gml:lowerCorner>14.403076171875002 38.83542884007305</gml:lowerCorner>", "<gml:upperCorner>19.368896484375004 40.94671366508002</gml:upperCorner>");
        IGPCSVBaseSchema response = request.getResponse();
        logger.info("#########################################RESPONSE : {}\n", response.getHeaders());
        assertEquals(asList("FID", "the_geom", "COD_REG", "PRO_COM", "COMUNE"), response.getHeaders());
    }

    @Test
    public void m_createGeoJsonPolygonTest() throws Exception {
        Polygon polygon = toPolygon(new Envelope(new Coordinate(14.403076171875002, 38.83542884007305), new Coordinate(19.368896484375004, 40.94671366508002)), 4326);
        ObjectMapper mapper = new GPJacksonJTSSupport().getDefaultMapper();
        mapper.writeValue(new File(of(new File(".").getCanonicalPath(), "target", "Polygon.json")
                .collect(joining(separator))), polygon);
    }

    /**
     * @param requestAsString the GetFeature request really built by {@link WFSGetFeatureRequestV110}
     * @param expectedFragments
     */
    private static void assertRequestContains(String requestAsString, String... expectedFragments) {
        for (String expectedFragment : expectedFragments) {
            assertTrue("The GetFeature request must contain : " + expectedFragment, requestAsString.contains(expectedFragment));
        }
    }

    /**
     * @param response the response returned by the mocked server
     * @throws Exception
     */
    private void mockGetFeatureResponse(Object response) throws Exception {
        doReturn(response).when(this.getFeatureRequest).getResponse();
    }

    /**
     * @param response the response returned by the mocked server
     * @throws Exception
     */
    private void mockGetFeatureResponseAsString(String response) throws Exception {
        doReturn(response).when(this.getFeatureRequest).getResponseAsString();
    }

    /**
     * @param numberOfFeatures
     * @return {@link FeatureCollectionType}
     */
    private static FeatureCollectionType featureCollectionType(int numberOfFeatures) {
        FeatureCollectionType featureCollectionType = new FeatureCollectionType();
        featureCollectionType.setNumberOfFeatures(BigInteger.valueOf(numberOfFeatures));
        return featureCollectionType;
    }

    /**
     * @param comuni
     * @return {@link FeatureCollection}
     */
    private static FeatureCollection geoJsonFeatureCollection(String... comuni) {
        FeatureCollection featureCollection = new FeatureCollection();
        for (String comune : comuni) {
            Feature feature = new Feature();
            feature.setId("admin_shp_comuni.".concat(comune));
            feature.setProperty("COMUNE", comune);
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    /**
     * @param headers
     * @return {@link IGPCSVBaseSchema}
     */
    private static IGPCSVBaseSchema csvSchema(String... headers) {
        IGPCSVBaseSchema csvSchema = new GPCSVBaseSchema();
        csvSchema.setHeaders(asList(headers));
        return csvSchema;
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