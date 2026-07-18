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
package org.geosdi.geoplatform.connector.wms.request;

import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML2;
import static org.geosdi.geoplatform.connector.server.store.GPWMSConnectorBuilder.WMSConnectorBuilder.wmsConnectorBuilder;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Verifies the opt-in {@code clearState()} lifecycle method on the WMS {@code GetFeatureInfo} request : after
 * configuring the request through the fluent {@code withXxx(...)} mutators, an explicit {@code clearState()}
 * releases the per-thread {@code ThreadLocal} configuration, so a shared request instance does not retain
 * per-thread values on pooled threads. {@code clearState()} is never invoked automatically, so it is
 * exercised here directly. The test is deterministic and performs no network call.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGetFeatureInfoRequestClearStateTest {

    private static IGPWMSConnectorStoreV111 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilder()
                .wmsConnectorBuilderV111()
                .withServerUrl(new URI("http://localhost:8080/geoserver/wms").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(8)
                        .withMaxRedirect(5)
                        .build())
                .build();
    }

    @Test
    public void getFeatureInfoStateIsClearedByClearState() throws Exception {
        GPWMSBoundingBox boundingBox = new WMSBoundingBox(-130d, 24d, -66d, 50d);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(boundingBox,
                of("topp:states").collect(toList()), "EPSG:4326", "550", "250");
        GPWMSGetFeatureInfoV111Request<Object> request = wmsServerConnector.createGetFeatureInfoRequest();
        String uriPath = request
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withQueryLayers("topp:states")
                .withInfoFormat(GML2)
                .withFeatureCount(5)
                .withX(100)
                .withY(200)
                .showRequestAsString();
        assertTrue("the configured request must carry its query layers", uriPath.contains("QUERY_LAYERS=topp:states"));

        request.clearState();

        // With the per-thread state released, building the URI fails because the mandatory parameters are gone.
        assertThrows(IllegalArgumentException.class, () -> request.showRequestAsString());
    }
}
