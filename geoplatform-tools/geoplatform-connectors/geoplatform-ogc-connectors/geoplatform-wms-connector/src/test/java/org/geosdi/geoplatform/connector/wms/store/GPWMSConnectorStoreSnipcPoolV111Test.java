/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v111.GPWMSDescribeLayerV111Request;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetCapabilitiesV111Request;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.pool.builder.v111.WMSConnectorBuilderPoolV111.wmsConnectorBuilderPoolV111;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML2_AS_STRING;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStoreSnipcPoolV111Test {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreSnipcPoolV111Test.class);
    //
    private static IGPWMSConnectorStoreV111 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilderPoolV111()
                .withServerUrl(new URL("https://servizi.protezionecivile.it/geoserver/wms"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(150)
                        .withDefaultMaxPerRoute(80)
                        .withMaxRedirect(20)
                        .build()).build();
    }

    @Ignore
    @Test
    public void a_wmsGetCapabilitiesV111Test() throws Exception {
        GPWMSGetCapabilitiesV111Request wmsGetCapabilitiesRequest = wmsServerConnector.createGetCapabilitiesRequest();
        logger.info("###############################WMS_GET_CAPABILITIES_V111_RESPONSE : {}\n", wmsGetCapabilitiesRequest.getResponseAsString());
    }

    @Ignore
    @Test
    public void b_wmsDescribeLayerV11Test() throws Exception {
        GPWMSDescribeLayerV111Request wmsDescribeLayerRequest = wmsServerConnector.createDescribeLayerRequest();
        logger.info("##########################WMS_DESCRIBE_LAYER_RESPONSE_V111 : {}\n", wmsDescribeLayerRequest
                .withLayers("istat:ISTAT_2011_ace_propers").getResponse());
    }

    @Ignore
    @Test
    public void c_wmsDescribeLayerV11Test() throws Exception {
        GPWMSDescribeLayerV111Request wmsDescribeLayerRequest = wmsServerConnector.createDescribeLayerRequest();
        logger.info("##########################WMS_DESCRIBE_LAYER_RESPONSE_V111 : {}\n", wmsDescribeLayerRequest
                .withLayers("PNSRS:IT_sedi_ASL_2020").getResponse());
    }

    @Test
    public void d_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(13.815994262695314, 40.72332345541451, 15.088348388671877, 40.99389273551914);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("VULCANICO:CF_aree_attesa").collect(toSet()),
                "EPSG:4326", "1853", "521");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("VULCANICO:CF_aree_attesa")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(2)
                .withInfoFormat(GML2_AS_STRING).withX(487).withY(305).getResponse());
    }

    @Test
    public void e_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(13.815994262695314, 40.72332345541451, 15.088348388671877, 40.99389273551914);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("VULCANICO:VES_aree_attesa").collect(toSet()),
                "EPSG:4326", "1853", "521");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("VULCANICO:VES_aree_attesa")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(2)
                .withInfoFormat(GML2_AS_STRING).withX(487).withY(305).getResponse());
    }

    @Test(expected = IllegalArgumentException.class)
    public void f_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(13.815994262695314, 40.72332345541451, 15.088348388671877, 40.99389273551914);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("VULCANICO:CF_zona rossa_maplite").collect(toSet()),
                "EPSG:4326", "1853", "521");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("VULCANICO:CF_zona rossa_maplite")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(2)
                .withInfoFormat(GML2_AS_STRING).withX(487).withY(305).getResponse());
    }
}