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
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v130.GPWMSGetFeatureInfoV130Request;
import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML2;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML3;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import static org.mockito.Mockito.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStoreV130PrositTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreV130PrositTest.class);
    //
    private IGPWMSConnectorStoreV130 wmsServerConnector;

    @Before
    public void setUp() throws Exception {
        this.wmsServerConnector = mock(IGPWMSConnectorStoreV130.class);
    }

    @Test
    public void a_wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("admin_shp_vigneti_catastali", "admin_shp_oliveti_catastali"));
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(40.97210836426291, 15.824662897187245, 40.98109151703362, 15.836561468047133);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali").collect(toSet()),
                "EPSG:4326", "256", "256");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML2).withX(154).withY(230).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V130_RESPONSE : {}\n", response);
        assertEquals(2, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML2, 154, 230, "admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali");
    }

    @Test
    public void b_wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> mockRequest = this.mockGetFeatureInfoRequest(toFeatureCollection("admin_shp_vigneti_catastali", "admin_shp_oliveti_catastali"));
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(40.97210836426291, 15.824662897187245, 40.98109151703362, 15.836561468047133);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali").collect(toSet()),
                "EPSG:4326", "256", "256");
        Object response = wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(GML3).withX(154).withY(230).getResponse();
        logger.info("##################################WMS_GET_FEATURE_INFO_V130_RESPONSE : {}\n", response);
        assertEquals(2, ((FeatureCollection) response).getFeatures().size());
        this.verifyGetFeatureInfoRequest(mockRequest, wmsGetMapBaseRequest, GML3, 154, 230, "admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali");
    }

    /**
     * @param response
     * @return {@link GPWMSGetFeatureInfoV130Request<Object>}
     * @throws Exception
     */
    private GPWMSGetFeatureInfoV130Request<Object> mockGetFeatureInfoRequest(Object response) throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> mockRequest = mock(GPWMSGetFeatureInfoV130Request.class, RETURNS_SELF);
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
    private void verifyGetFeatureInfoRequest(GPWMSGetFeatureInfoV130Request<Object> mockRequest, GPWMSGetMapBaseRequest wmsGetMapBaseRequest,
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
