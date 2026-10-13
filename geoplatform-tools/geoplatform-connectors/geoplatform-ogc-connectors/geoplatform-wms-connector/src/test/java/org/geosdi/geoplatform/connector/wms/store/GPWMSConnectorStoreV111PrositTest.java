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
package org.geosdi.geoplatform.connector.wms.store;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v111.GPWMSDescribeLayerV111Request;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetCapabilitiesV111Request;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.wms.v111.LayerDescription;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Class.forName;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML2;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML3;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import static org.mockito.Mockito.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStoreV111PrositTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreV111PrositTest.class);
    //
    private static final GPJacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);
    //
    private IGPWMSConnectorStoreV111 wmsServerConnector;

    @Before
    public void setUp() throws Exception {
        this.wmsServerConnector = mock(IGPWMSConnectorStoreV111.class);
    }

    @Test
    public void a_wmsGetCapabilitiesV111Test() throws Exception {
        // WMSGetCapabilitiesV111Request is package private, so it is mocked by name.
        GPWMSGetCapabilitiesV111Request mockRequest = (GPWMSGetCapabilitiesV111Request) mock(forName("org.geosdi.geoplatform.connector.server.v111.WMSGetCapabilitiesV111Request"));
        when(mockRequest.getResponseAsString()).thenReturn("<WMT_MS_Capabilities version=\"1.1.1\"/>");
        doReturn(mockRequest).when(this.wmsServerConnector).createGetCapabilitiesRequest();
        GPWMSGetCapabilitiesV111Request wmsGetCapabilitiesRequest = wmsServerConnector.createGetCapabilitiesRequest();
        String response = wmsGetCapabilitiesRequest.getResponseAsString();
        logger.debug("###############################WMS_GET_CAPABILITIES_V111_RESPONSE : {}\n", response);
        assertEquals("<WMT_MS_Capabilities version=\"1.1.1\"/>", response);
        verify(this.wmsServerConnector).createGetCapabilitiesRequest();
    }

    @Test
    public void b_wmsDescribeLayerV111Test() throws Exception {
        WMSDescribeLayerResponse describeLayerResponse = new WMSDescribeLayerResponse();
        describeLayerResponse.setVersion("1.1.1");
        describeLayerResponse.setLayerDescription(of("admin:admin_shp_oliveti_catastali", "admin:admin_shp_vigneti_catastali", "sf:restricted")
                .map(this::toLayerDescription)
                .collect(toList()));
        GPWMSDescribeLayerV111Request mockRequest = mock(GPWMSDescribeLayerV111Request.class, RETURNS_SELF);
        doReturn(describeLayerResponse).when(mockRequest).getResponse();
        when(this.wmsServerConnector.createDescribeLayerRequest()).thenReturn(mockRequest);
        GPWMSDescribeLayerV111Request wmsDescribeLayerRequest = wmsServerConnector.createDescribeLayerRequest();
        WMSDescribeLayerResponse response = wmsDescribeLayerRequest
                .withLayers("admin:admin_shp_oliveti_catastali", "admin:admin_shp_vigneti_catastali", "sf:restricted").getResponse();
        logger.info("##########################WMS_DESCRIBE_LAYER_RESPONSE_V111 : {}\n", response);
        assertSame(describeLayerResponse, response);
        assertEquals(3, response.getLayerDescription().size());
        verify(mockRequest).withLayers("admin:admin_shp_oliveti_catastali", "admin:admin_shp_vigneti_catastali", "sf:restricted");
    }

    @Test
    public void c_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("admin_shp_vigneti_catastali", "admin_shp_oliveti_catastali"));
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(15.824662897187245, 40.97210836426291, 15.836561468047133, 40.98109151703362);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali").collect(toSet()),
                "EPSG:4326", "256", "256");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML2).withX(154).withY(230).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(response));
        assertEquals(2, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML2, 154, 230, "admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali");
    }

    @Test(expected = IllegalStateException.class)
    public void d_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = mock(GPWMSGetFeatureInfoV111Request.class, RETURNS_SELF);
        doThrow(new IllegalStateException(new UnauthorizedException())).when(mockRequest).getResponse();
        when(this.wmsServerConnector.createGetFeatureInfoRequest()).thenReturn(mockRequest);
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(-100.45903622648385, 29.975499908170573, -100.45893252351615, 29.975589739698282);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("topp:states").collect(toSet()),
                "EPSG:4326", "256", "256");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("topp:states")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML2).withX(61).withY(209).getResponse());
    }

    @Test
    public void e_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("tempo"));
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(16.085612734672655, 40.485177116824524, 16.091518441322226, 40.489668693209886);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:tempo").collect(toSet()),
                "EPSG:4326", "101", "101");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:tempo")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML2).withX(50).withY(50).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", response);
        assertEquals(1, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML2, 50, 50, "admin:tempo");
    }

    @Test
    public void f_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("admin_shp_vigneti_catastali"));
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(15.686073303222658, 40.95721522257321, 15.765595436096193, 40.985891519320774);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:admin_shp_vigneti_catastali").collect(toSet()),
                "EPSG:4326", "1853", "885");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML2).withX(966).withY(481).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", response);
        assertEquals(1, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML2, 966, 481, "admin:admin_shp_vigneti_catastali");
    }

    @Test
    public void g_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("admin_shp_vigneti_catastali", "admin_shp_oliveti_catastali"));
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(15.824662897187245, 40.97210836426291, 15.836561468047133, 40.98109151703362);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali").collect(toSet()),
                "EPSG:4326", "256", "256");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML3).withX(154).withY(230).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(response));
        assertEquals(2, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML3, 154, 230, "admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali");
    }

    @Test
    public void h_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("tempo"));
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(16.085612734672655, 40.485177116824524, 16.091518441322226, 40.489668693209886);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:tempo").collect(toSet()),
                "EPSG:4326", "101", "101");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:tempo")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML3).withX(50).withY(50).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", response);
        assertEquals(1, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML3, 50, 50, "admin:tempo");
    }

    @Test
    public void i_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("admin_shp_vigneti_catastali"));
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(15.686073303222658, 40.95721522257321, 15.765595436096193, 40.985891519320774);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:admin_shp_vigneti_catastali").collect(toSet()),
                "EPSG:4326", "1853", "885");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML3).withX(966).withY(481).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", response);
        assertEquals(1, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML3, 966, 481, "admin:admin_shp_vigneti_catastali");
    }

    /**
     * @param response
     * @return {@link GPWMSGetFeatureInfoV111Request<Object>}
     * @throws Exception
     */
    private GPWMSGetFeatureInfoV111Request<Object> mockGetFeatureInfoRequest(Object response) throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> mockRequest = mock(GPWMSGetFeatureInfoV111Request.class, RETURNS_SELF);
        doReturn(response).when(mockRequest).getResponse();
        when(this.wmsServerConnector.createGetFeatureInfoRequest()).thenReturn(mockRequest);
        return mockRequest;
    }

    /**
     * @param mockRequest
     * @param wmsGetMapBaseRequest
     * @param infoFormat
     * @param x
     * @param y
     * @param queryLayers
     * @throws Exception
     */
    private void verifyGetFeatureInfoRequest(GPWMSGetFeatureInfoV111Request<Object> mockRequest, GPWMSGetMapBaseRequest wmsGetMapBaseRequest,
            WMSFeatureInfoFormat infoFormat, Integer x, Integer y, String... queryLayers) throws Exception {
        verify(this.wmsServerConnector).createGetFeatureInfoRequest();
        verify(mockRequest).withQueryLayers(queryLayers);
        verify(mockRequest).withWMSGetMapRequest(wmsGetMapBaseRequest);
        verify(mockRequest).withFeatureCount(8);
        verify(mockRequest).withInfoFormat(infoFormat);
        verify(mockRequest).withX(x);
        verify(mockRequest).withY(y);
        verify(mockRequest).getResponse();
    }

    /**
     * @param layerName
     * @return {@link LayerDescription}
     */
    private LayerDescription toLayerDescription(String layerName) {
        LayerDescription layerDescription = new LayerDescription();
        layerDescription.setName(layerName);
        layerDescription.setOwsType("WFS");
        layerDescription.setOwsURL("https://prosit.geosdi.org/geoserver/wfs?");
        return layerDescription;
    }

    /**
     * @param featureTypes
     * @return {@link FeatureCollection}
     */
    private static FeatureCollection toFeatureCollection(String... featureTypes) {
        FeatureCollection featureCollection = new FeatureCollection();
        for (String featureType : featureTypes) {
            Feature feature = new Feature();
            feature.setId(featureType.concat(".1"));
            feature.setProperty("featureType", featureType);
            featureCollection.add(feature);
        }
        return featureCollection;
    }
}
