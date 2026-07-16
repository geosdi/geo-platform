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

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.geosdi.geoplatform.connector.server.exception.GPConnectorHttpStatusException;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.geosdi.geoplatform.connector.GeoserverVersion.V28x;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Offline unit tests for the GeoServer connector's HTTP error/status handling, using a mocked HTTP
 * server (WireMock) instead of a live GeoServer. Covers the behaviors introduced by the error and
 * status-handling hardening: 404 mapped to "does not exist", server errors surfaced (not masked)
 * and URL-encoding of path segments.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorWireMockTest {

    @Rule
    public WireMockRule wireMock = new WireMockRule(options().dynamicPort());

    private GPGeoserverConnectorStore store;

    @Before
    public void setUp() throws Exception {
        this.store = geoserverConnectorBuilder()
                .withServerUrl(new URI("http://localhost:" + wireMock.port() + "/geoserver/rest").toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(10)
                        .withMaxRedirect(5)
                        .build())
                .withVersion(V28x.getVersion())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        this.store.dispose();
    }

    /**
     * A 404 must be interpreted as "the resource does not exist" (exist == false).
     */
    @Test
    public void existReturnsFalseOnNotFound() throws Exception {
        stubFor(get(urlEqualTo("/geoserver/rest/layers/ghost"))
                .willReturn(aResponse().withStatus(404)));
        assertFalse("A 404 must map to exist=false", this.store.loadLayerRequest().withName("ghost").exist());
    }

    /**
     * A 5xx must NOT be masked as "resource missing": it must surface as a typed status exception.
     */
    @Test(expected = GPConnectorHttpStatusException.class)
    public void serverErrorIsNotMaskedAsMissing() throws Exception {
        stubFor(get(urlEqualTo("/geoserver/rest/layers/boom"))
                .willReturn(aResponse().withStatus(500).withBody("Internal Server Error")));
        this.store.loadLayerRequest().withName("boom").exist();
    }

    /**
     * The typed status exception must carry the actual status code and the server's response body,
     * so callers can react to the real error instead of a generic parsing failure.
     */
    @Test
    public void statusExceptionCarriesStatusAndBody() throws Exception {
        stubFor(get(urlEqualTo("/geoserver/rest/layers/boom"))
                .willReturn(aResponse().withStatus(409).withBody("resource conflict")));
        try {
            this.store.loadLayerRequest().withName("boom").exist();
            fail("Expected GPConnectorHttpStatusException");
        } catch (GPConnectorHttpStatusException ex) {
            assertEquals(409, ex.getStatusCode());
            assertTrue("The exception must carry the response body", ex.getResponseBody().contains("resource conflict"));
        }
    }

    /**
     * A 401 must surface as a typed {@link UnauthorizedException}.
     */
    @Test(expected = UnauthorizedException.class)
    public void unauthorizedMapsToUnauthorizedException() throws Exception {
        stubFor(get(urlEqualTo("/geoserver/rest/layers/secret"))
                .willReturn(aResponse().withStatus(401)));
        this.store.loadLayerRequest().withName("secret").exist();
    }

    /**
     * Path segments (resource names) with reserved characters must be percent-encoded on the wire.
     */
    @Test
    public void pathSegmentsAreUrlEncoded() throws Exception {
        stubFor(get(urlEqualTo("/geoserver/rest/layers/my%20layer"))
                .willReturn(aResponse().withStatus(404)));
        this.store.loadLayerRequest().withName("my layer").exist();
        verify(getRequestedFor(urlEqualTo("/geoserver/rest/layers/my%20layer")));
    }
}
