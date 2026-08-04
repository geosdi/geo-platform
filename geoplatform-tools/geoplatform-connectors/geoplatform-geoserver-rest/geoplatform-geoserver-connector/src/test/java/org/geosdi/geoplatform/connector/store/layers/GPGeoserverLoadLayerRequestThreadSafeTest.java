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
package org.geosdi.geoplatform.connector.store.layers;

import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadLayerRequest;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.geosdi.geoplatform.connector.GeoserverVersion.V3x;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;
import static org.junit.Assert.assertTrue;

/**
 * Verifies that a single, shared {@link GeoserverLoadLayerRequest} instance is thread-safe: the
 * per-call builder parameter ({@code name}) is isolated per thread (via {@link ThreadLocal}), so
 * concurrent callers never observe each other's state. This documents and locks the "a request is
 * safe to reuse/share across threads" contract.
 * <p>
 * Offline test: {@code showRequestAsString()} only builds the target URI (delegates to
 * {@code createUriPath()}); no HTTP call is performed, so no live GeoServer is required.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverLoadLayerRequestThreadSafeTest {

    private static GPGeoserverConnectorStore store;

    @BeforeClass
    public static void beforeClass() throws Exception {
        store = geoserverConnectorBuilder()
                .withServerUrl(new URI("http://localhost:8080/geoserver/rest").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(50)
                        .withDefaultMaxPerRoute(30)
                        .withMaxRedirect(10)
                        .build())
                .withVersion(V3x.getVersion())
                .build();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        store.dispose();
    }

    /**
     * Reusing the same instance sequentially must always reflect the latest configured name.
     */
    @Test
    public void reuseSameInstanceReflectsLatestName() throws Exception {
        GeoserverLoadLayerRequest request = store.loadLayerRequest();
        assertTrue(request.withName("alpha").showRequestAsString().endsWith("/layers/alpha"));
        assertTrue(request.withName("beta").showRequestAsString().endsWith("/layers/beta"));
    }

    /**
     * Names with reserved characters must be URL-encoded in the resulting URI.
     */
    @Test
    public void nameIsUrlEncoded() throws Exception {
        GeoserverLoadLayerRequest request = store.loadLayerRequest();
        assertTrue(request.withName("my layer").showRequestAsString().endsWith("/layers/my%20layer"));
    }

    /**
     * A single shared instance hammered by many threads, each with its own name, must never leak
     * one thread's name into another thread's resolved URI.
     */
    @Test
    public void sharedInstanceIsThreadSafe() throws Exception {
        final GeoserverLoadLayerRequest shared = store.loadLayerRequest();
        final int threads = 16;
        final int iterations = 500;
        ExecutorService pool = Executors.newFixedThreadPool(8);
        try {
            List<Callable<Boolean>> tasks = new ArrayList<>();
            for (int t = 0; t < threads; t++) {
                final String name = "layer_" + t;
                final String expectedSuffix = "/layers/" + name;
                tasks.add(() -> {
                    for (int i = 0; i < iterations; i++) {
                        if (!shared.withName(name).showRequestAsString().endsWith(expectedSuffix)) {
                            return Boolean.FALSE;
                        }
                    }
                    return Boolean.TRUE;
                });
            }
            List<Future<Boolean>> results = pool.invokeAll(tasks);
            for (Future<Boolean> result : results) {
                assertTrue("Shared request leaked builder state across threads", result.get());
            }
        } finally {
            pool.shutdownNow();
        }
    }
}
