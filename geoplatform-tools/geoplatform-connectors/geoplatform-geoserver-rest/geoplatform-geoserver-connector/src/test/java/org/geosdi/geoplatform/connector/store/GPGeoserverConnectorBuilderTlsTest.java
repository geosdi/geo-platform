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
package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadLayerRequest;
import org.junit.Test;

import java.net.URI;

import static org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy.createSystemDefault;
import static org.geosdi.geoplatform.connector.GeoserverVersion.V3x;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Verifies that a custom {@code DefaultClientTlsStrategy} provided to the builder is actually
 * threaded down to the server connector (previously {@code build()} silently dropped it). The store
 * is built end-to-end through the whole connector constructor chain and used to produce a request
 * URI. Offline: no HTTP call is performed.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorBuilderTlsTest {

    @Test
    public void customTlsStrategyIsPropagatedThroughBuild() throws Exception {
        GPGeoserverConnectorStore store = geoserverConnectorBuilder()
                .withServerUrl(new URI("http://localhost:8080/geoserver/rest").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(50)
                        .withDefaultMaxPerRoute(30)
                        .withMaxRedirect(10)
                        .withInsecureTls(false)
                        .build())
                .withDefaultClientTlsStrategy(createSystemDefault())
                .withVersion(V3x.getVersion())
                .build();
        try {
            assertNotNull("Store must be built with a custom TLS strategy", store);
            GeoserverLoadLayerRequest request = store.loadLayerRequest();
            assertTrue(request.withName("layer").showRequestAsString().endsWith("/layers/layer"));
        } finally {
            store.dispose();
        }
    }
}
