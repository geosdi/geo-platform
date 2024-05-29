/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.apache.hc.core5.util.Timeout;
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v130.GPWMSGetCapabilitiesV130Request;
import org.geosdi.geoplatform.connector.server.v130.GPWMSGetFeatureInfoV130Request;
import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.pool.builder.v130.WMSConnectorBuilderPoolV130.wmsConnectorBuilderPoolV130;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML2_AS_STRING;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML3;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStoreSit2PoolV130Test {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreSit2PoolV130Test.class);
    //
    private static IGPWMSConnectorStoreV130 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilderPoolV130()
                .withServerUrl(new URI("https://sit2.regione.campania.it/geoserver/wms").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withConnectionTimeout(Timeout.of(15L, SECONDS))
                        .withRequestConnectionTimeout(Timeout.of(10L, SECONDS))
                        .withResponseConnectionTimeout(Timeout.of(10L, SECONDS))
                        .withConnectionKeepAlive(Timeout.of(3L, MINUTES))
                        .withDefaultMaxPerRoute(10)
                        .withMaxRedirect(5)
                        .build())
                .build();
    }

    @Ignore(value = "Server is Down")
    @Test
    public void a_wmsGetCapabilitiesV130Test() throws Exception {
        GPWMSGetCapabilitiesV130Request wmsGetCapabilitiesRequest = wmsServerConnector.createGetCapabilitiesRequest();
        logger.info("###############################WMS_GET_CAPABILITIES_V130_RESPONSE : {}\n", wmsGetCapabilitiesRequest.getResponseAsString());
    }

    @Ignore(value = "Server is Down")
    @Test
    public void b_wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(4486229.492994551,443799.21368173225,4517075.4832059685,474645.2038931505);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("RegioneCampania.Cartografia.Tematica:sitdbo_reticolo_idrografico").collect(toSet()),
                "EPSG:3045", "101", "101");
        logger.info("##################################WMS_GET_FEATURE_INFO_V130_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("RegioneCampania.Cartografia.Tematica:sitdbo_reticolo_idrografico")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(50)
                .withInfoFormat(GML2_AS_STRING).withX(50).withY(50).getResponse());
    }

    @Ignore(value = "Server is Down")
    @Test
    public void c_wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(4529593.958572989,424639.1351805072,4560439.948784407,455485.12539192545);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("RegioneCampania.Cartografia.Base:sitdbo_rete_stradale_rc").collect(toSet()),
                "EPSG:3045", "101", "101");
        logger.info("##################################WMS_GET_FEATURE_INFO_V130_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("RegioneCampania.Cartografia.Base:sitdbo_rete_stradale_rc")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(50)
                .withInfoFormat(GML2_AS_STRING).withX(50).withY(50).getResponse());
    }

    @Ignore(value = "Server is Down")
    @Test
    public void d_wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(4486229.492994551,443799.21368173225,4517075.4832059685,474645.2038931505);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("RegioneCampania.Cartografia.Tematica:sitdbo_reticolo_idrografico").collect(toSet()),
                "EPSG:3045", "101", "101");
        logger.info("##################################WMS_GET_FEATURE_INFO_V130_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("RegioneCampania.Cartografia.Tematica:sitdbo_reticolo_idrografico")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(50)
                .withInfoFormat(GML3).withX(50).withY(50).getResponse());
    }

    @Ignore(value = "Server is Down")
    @Test
    public void e_wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(4529593.958572989,424639.1351805072,4560439.948784407,455485.12539192545);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("RegioneCampania.Cartografia.Base:sitdbo_rete_stradale_rc").collect(toSet()),
                "EPSG:3045", "101", "101");
        logger.info("##################################WMS_GET_FEATURE_INFO_V130_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("RegioneCampania.Cartografia.Base:sitdbo_rete_stradale_rc")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(50)
                .withInfoFormat(GML3).withX(50).withY(50).getResponse());
    }
}