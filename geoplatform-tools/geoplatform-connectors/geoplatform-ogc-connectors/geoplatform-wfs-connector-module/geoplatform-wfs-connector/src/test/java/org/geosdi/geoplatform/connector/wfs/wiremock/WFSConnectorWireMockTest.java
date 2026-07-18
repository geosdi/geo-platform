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
package org.geosdi.geoplatform.connector.wfs.wiremock;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.connector.server.request.WFSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.geosdi.geoplatform.xml.wfs.v110.WFSCapabilitiesType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.google.common.base.Charsets.UTF_8;
import static java.util.Arrays.asList;
import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.INSERT;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.HITS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Fully offline, deterministic tests for the WFS connector's request/response round-trip, decoupled from any
 * real GeoServer through WireMock. A mock HTTP server serves canned WFS responses (the {@code GetCapabilities}
 * and {@code DescribeFeatureType} fixtures under {@code src/test/resources}, and an inline {@code HITS}
 * FeatureCollection), and the connector is pointed at it. Each test asserts that the connector both builds the
 * expected request (verified against the mock) and parses the canned response correctly. The three WFS POST
 * operations share the same endpoint, so stubs are distinguished by request-body content.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSConnectorWireMockTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSConnectorWireMockTest.class);
    private static final String WFS_PATH = "/geoserver/wfs";
    private static final String HITS_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<wfs:FeatureCollection xmlns:wfs=\"http://www.opengis.net/wfs\" numberOfFeatures=\"49\" "
            + "timeStamp=\"2024-01-01T00:00:00.000Z\"></wfs:FeatureCollection>";
    private static final String TRANSACTION_RESPONSE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<wfs:TransactionResponse xmlns:wfs=\"http://www.opengis.net/wfs\" xmlns:ogc=\"http://www.opengis.net/ogc\" version=\"1.1.0\">\n"
            + "  <wfs:TransactionSummary>\n"
            + "    <wfs:totalInserted>1</wfs:totalInserted>\n"
            + "    <wfs:totalUpdated>0</wfs:totalUpdated>\n"
            + "    <wfs:totalDeleted>0</wfs:totalDeleted>\n"
            + "  </wfs:TransactionSummary>\n"
            + "</wfs:TransactionResponse>";

    @Rule
    public WireMockRule wireMock = new WireMockRule(options().dynamicPort());

    private GPWFSConnectorStore serverConnector;

    @Before
    public void setUp() throws Exception {
        this.serverConnector = newConnector()
                .withServerUrl(new URI("http://localhost:" + wireMock.port() + WFS_PATH).toURL())
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(8)
                        .withMaxRedirect(5)
                        .build())
                .build();
    }

    @Test
    public void getCapabilitiesIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("GetCapabilities"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml")
                        .withBody(resource("/wfsGetCapabilitiesv110.xml"))));

        WFSGetCapabilitiesRequest<WFSCapabilitiesType> request = this.serverConnector.createGetCapabilitiesRequest();
        WFSCapabilitiesType capabilities = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@ WireMock GetCapabilities version : {}\n", capabilities.getVersion());

        assertNotNull("the GetCapabilities response must be parsed", capabilities);
        assertEquals("1.1.0", capabilities.getVersion());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("GetCapabilities")));
    }

    @Test
    public void describeFeatureTypeIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("DescribeFeatureType"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml")
                        .withBody(resource("/wfsDescribeFeaturev110.xml"))));

        WFSDescribeFeatureTypeRequest<Schema> request = this.serverConnector.createDescribeFeatureTypeRequest();
        request.withTypeName(asList(new QName("http://www.openplans.org/spearfish", "firesat", "topp")));
        Schema schema = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@ WireMock DescribeFeatureType targetNamespace : {}\n", schema.getTargetNamespace());

        assertNotNull("the DescribeFeatureType schema must be parsed", schema);
        assertEquals("http://www.openplans.org/spearfish", schema.getTargetNamespace());
        assertFalse("the schema must declare at least one type/element", schema.getSimpleTypeOrComplexTypeOrGroup().isEmpty());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("DescribeFeatureType")));
    }

    @Test
    public void getFeatureHitsIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("GetFeature"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml")
                        .withBody(HITS_RESPONSE)));

        WFSGetFeatureRequest<FeatureCollectionType> request = this.serverConnector.createGetFeatureRequest();
        request.withTypeName(new QName("http://www.openplans.org/topp", "states", "topp"))
                .withResultType(HITS.value());
        FeatureCollectionType response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@ WireMock GetFeature HITS numberOfFeatures : {}\n", response.getNumberOfFeatures());

        assertNotNull("the GetFeature response must be parsed", response);
        assertEquals("the parsed hit count must match the mocked response", 49, response.getNumberOfFeatures().intValue());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("GetFeature")));
    }

    @Test
    public void transactionInsertRoundTripIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("Transaction"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml")
                        .withBody(TRANSACTION_RESPONSE)));

        AttributeDTO attribute = new AttributeDTO();
        attribute.setName("TYPE");
        attribute.setValue("mocked road");
        WFSTransactionRequest<TransactionResponseType> request = this.serverConnector.createTransactionRequest();
        request.withOperation(INSERT)
                .withTypeName(new QName("http://www.openplans.org/topp", "tasmania_roads", "topp"))
                .withAttributes(asList(attribute));
        TransactionResponseType response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@ WireMock Transaction totalInserted : {}\n",
                response.getTransactionSummary().getTotalInserted());

        assertNotNull("the Transaction response must be parsed", response);
        assertEquals("the parsed transaction summary must match the mocked response", 1,
                response.getTransactionSummary().getTotalInserted().intValue());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("Insert")));
    }

    /**
     * @param name the classpath resource name (absolute, e.g. {@code /wfsGetCapabilitiesv110.xml})
     * @return the resource content as a UTF-8 String
     */
    private static String resource(String name) throws Exception {
        try (InputStream is = WFSConnectorWireMockTest.class.getResourceAsStream(name)) {
            assertNotNull("missing test fixture : " + name, is);
            return new String(is.readAllBytes(), UTF_8);
        }
    }
}
