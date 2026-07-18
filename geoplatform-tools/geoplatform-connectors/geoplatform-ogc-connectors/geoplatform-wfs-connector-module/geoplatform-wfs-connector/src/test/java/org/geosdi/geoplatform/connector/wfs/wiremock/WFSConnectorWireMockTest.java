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
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.geosdi.geoplatform.xml.wfs.v110.WFSCapabilitiesType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.geojson.FeatureCollection;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigInteger;
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
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.GEOJSON;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.INSERT;
import static org.geosdi.geoplatform.jaxb.jakarta.GPJAXBJakartaContextBuilder.jakartaContextBuilder;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.HITS;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

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
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSConnectorWireMockTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSConnectorWireMockTest.class);
    private static final String WFS_PATH = "/geoserver/wfs";
    private static final QName statesName = new QName("http://www.openplans.org/topp", "states", "topp");
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
    public void a_getCapabilitiesIsParsedFromMockedServer() throws Exception {
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
    public void b_describeFeatureTypeIsParsedFromMockedServer() throws Exception {
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
    public void c_getFeatureHitsIsParsedFromMockedServer() throws Exception {
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
    public void d_transactionInsertRoundTripIsParsedFromMockedServer() throws Exception {
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
     * Offline, deterministic conversion of {@code WFSGetFeatureTest#h_statesContainsRestrictionTest} : the same
     * {@code SUB_REGION CONTAINS Mtn} QueryDTO is sent, but instead of hitting a live GeoServer the mock serves
     * a per-query GeoJSON fixture. The stub is keyed on the OGC filter that the connector serializes into the
     * request body (property name + literal), and the test asserts both that the connector built that filter
     * and that it parsed exactly the features the fixture declares.
     */
    @Test
    public void e_getFeatureContainsRestrictionIsParsedFromMockedServer() throws Exception {
        QueryDTO containsMtn = jakartaContextBuilder().unmarshal(new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<QueryDTO>\n"
                        + "    <matchOperator>ALL</matchOperator>\n"
                        + "    <queryRestrictionList>\n"
                        + "        <queryRestriction>\n"
                        + "            <attribute>\n"
                        + "                <maxOccurs>1</maxOccurs>\n"
                        + "                <minOccurs>0</minOccurs>\n"
                        + "                <name>SUB_REGION</name>\n"
                        + "                <nillable>true</nillable>\n"
                        + "                <type>string</type>\n"
                        + "                <value></value>\n"
                        + "            </attribute>\n"
                        + "            <operator>CONTAINS</operator>\n"
                        + "            <restriction>Mtn</restriction>\n"
                        + "        </queryRestriction>\n"
                        + "    </queryRestrictionList>\n"
                        + "</QueryDTO>"), QueryDTO.class);

        // Per-query stub : match the OGC filter this specific query serializes (property name + literal).
        // matchingXPath("//ogc:PropertyIsLike[ogc:PropertyName='SUB_REGION']") would be the stricter alternative.
        stubFor(post(urlPathEqualTo(WFS_PATH))
                .withRequestBody(containing("SUB_REGION"))
                .withRequestBody(containing("Mtn"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                        .withBody(resource("/wiremock/getfeature_contains_mtn.json"))));

        WFSGetFeatureRequest<FeatureCollection> request = this.serverConnector.createGetFeatureRequest();
        request.withTypeName(new QName("http://www.openplans.org/topp", "states", "topp"))
                .withResultType(RESULTS.value())
                .withOutputFormat(GEOJSON)
                .withQueryDTO(containsMtn);
        FeatureCollection response = request.getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@ WireMock GetFeature CONTAINS Mtn features : {}\n", response.getFeatures().size());

        assertNotNull("the GetFeature response must be parsed", response);
        assertEquals("the parsed feature count must match the per-query fixture", 2, response.getFeatures().size());
        // Behavioral check : the connector actually serialized the CONTAINS filter on SUB_REGION.
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH))
                .withRequestBody(containing("SUB_REGION")).withRequestBody(containing("Mtn")));
    }

    /**
     * Conversion of {@code b_statesHitsQueryRestrictions} : a multi-restriction AND query
     * (WORKERS &gt;= 0.25 AND MANUAL &gt;= 0.25 AND SUB_REGION = Mtn), HITS. The stub is keyed on the two
     * numeric property names the filter serializes.
     */
    @Test
    public void f_getFeatureAndRestrictionsIsParsedFromMockedServer() throws Exception {
        QueryDTO andQuery = queryDTO("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<QueryDTO><matchOperator>ALL</matchOperator><queryRestrictionList>"
                + "<queryRestriction><attribute><maxOccurs>1</maxOccurs><minOccurs>0</minOccurs><name>WORKERS</name><nillable>true</nillable><type>double</type><value></value></attribute><operator>GREATER_OR_EQUAL</operator><restriction>0.25</restriction></queryRestriction>"
                + "<queryRestriction><attribute><maxOccurs>1</maxOccurs><minOccurs>0</minOccurs><name>MANUAL</name><nillable>true</nillable><type>double</type><value></value></attribute><operator>GREATER_OR_EQUAL</operator><restriction>0.25</restriction></queryRestriction>"
                + "<queryRestriction><attribute><maxOccurs>1</maxOccurs><minOccurs>0</minOccurs><name>SUB_REGION</name><nillable>true</nillable><type>string</type><value></value></attribute><operator>EQUAL</operator><restriction>Mtn</restriction></queryRestriction>"
                + "</queryRestrictionList></QueryDTO>");
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("WORKERS")).withRequestBody(containing("MANUAL"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml").withBody(hits(8))));

        FeatureCollectionType response = this.serverConnector.<FeatureCollectionType>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(HITS.value()).withQueryDTO(andQuery).getResponse();

        assertEquals(8, response.getNumberOfFeatures().intValue());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("WORKERS")).withRequestBody(containing("MANUAL")));
    }

    /**
     * Conversion of {@code l_statesNotContainsRestrictionTest} : a LIKE restriction on SUB_REGION, HITS.
     */
    @Test
    public void g_getFeatureLikeRestrictionIsParsedFromMockedServer() throws Exception {
        QueryDTO likeQuery = queryDTO(singleRestriction("SUB_REGION", "string", "LIKE", "Mtn", "ALL"));
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("SUB_REGION")).withRequestBody(containing("Mtn"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml").withBody(hits(8))));

        FeatureCollectionType response = this.serverConnector.<FeatureCollectionType>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(HITS.value()).withQueryDTO(likeQuery).getResponse();

        assertEquals(8, response.getNumberOfFeatures().intValue());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("SUB_REGION")).withRequestBody(containing("Mtn")));
    }

    /**
     * Conversion of {@code n_statesGreatherThanRestrictionTest} : GREATER on WORKERS, HITS.
     */
    @Test
    public void h_getFeatureGreaterThanRestrictionIsParsedFromMockedServer() throws Exception {
        QueryDTO greaterQuery = queryDTO(singleRestriction("WORKERS", "double", "GREATER", "6000000", "ALL"));
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("WORKERS")).withRequestBody(containing("6000000"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml").withBody(hits(3))));

        FeatureCollectionType response = this.serverConnector.<FeatureCollectionType>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(HITS.value()).withQueryDTO(greaterQuery).getResponse();

        assertEquals(3, response.getNumberOfFeatures().intValue());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("WORKERS")).withRequestBody(containing("6000000")));
    }

    /**
     * Conversion of {@code p_statesNotGreatherThanRestrictionTest} : the negated (matchOperator NONE) GREATER
     * restriction, HITS. Same property/value as the plain GREATER case but wrapped in a Not.
     */
    @Test
    public void i_getFeatureNotGreaterThanRestrictionIsParsedFromMockedServer() throws Exception {
        QueryDTO notGreaterQuery = queryDTO(singleRestriction("WORKERS", "double", "GREATER", "6000000", "NONE"));
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("WORKERS")).withRequestBody(containing("6000000"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/xml").withBody(hits(46))));

        FeatureCollectionType response = this.serverConnector.<FeatureCollectionType>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(HITS.value()).withQueryDTO(notGreaterQuery).getResponse();

        assertEquals(46, response.getNumberOfFeatures().intValue());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("WORKERS")).withRequestBody(containing("6000000")));
    }

    /**
     * Conversion of {@code f_statesFeatureIDs} : selection by feature IDs, RESULTS/GeoJSON. The stub is keyed
     * on one of the requested feature ids that the connector serializes into the OGC Id filter.
     */
    @Test
    public void l_getFeatureByFeatureIDsIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("states.1")).withRequestBody(containing("states.49"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(geoJson(2))));

        FeatureCollection response = this.serverConnector.<FeatureCollection>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(RESULTS.value()).withOutputFormat(GEOJSON)
                .withFeatureIDs(asList("states.1", "states.49")).getResponse();

        assertEquals(2, response.getFeatures().size());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("states.1")).withRequestBody(containing("states.49")));
    }

    /**
     * Conversion of {@code g_statesBBox} : a BBOX spatial restriction (plus property-name selection),
     * RESULTS/GeoJSON. The stub is keyed on a corner coordinate the envelope serializes.
     */
    @Test
    public void m_getFeatureByBBoxIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("-75.102613"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(geoJson(3))));

        FeatureCollection response = this.serverConnector.<FeatureCollection>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(RESULTS.value()).withOutputFormat(GEOJSON)
                .withPropertyNames(asList("STATE_NAME", "PERSONS"))
                .withBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517)).getResponse();

        assertEquals(3, response.getFeatures().size());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("-75.102613")));
    }

    /**
     * Conversion of {@code e_statesResults} : a capped result set, RESULTS/GeoJSON. The stub is keyed on the
     * {@code maxFeatures} attribute the connector puts on the GetFeature element.
     */
    @Test
    public void n_getFeatureWithMaxFeaturesIsParsedFromMockedServer() throws Exception {
        stubFor(post(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("maxFeatures=\"1\""))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(geoJson(1))));

        FeatureCollection response = this.serverConnector.<FeatureCollection>createGetFeatureRequest()
                .withTypeName(statesName).withResultType(RESULTS.value()).withOutputFormat(GEOJSON)
                .withMaxFeatures(BigInteger.ONE).getResponse();

        assertEquals(1, response.getFeatures().size());
        verify(postRequestedFor(urlPathEqualTo(WFS_PATH)).withRequestBody(containing("maxFeatures=\"1\"")));
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

    /**
     * @param xml the QueryDTO document
     * @return the unmarshalled {@link QueryDTO}
     */
    private static QueryDTO queryDTO(String xml) throws Exception {
        return jakartaContextBuilder().unmarshal(new StringReader(xml), QueryDTO.class);
    }

    /**
     * @return a single-restriction QueryDTO document for the given attribute/operator/value and match operator.
     */
    private static String singleRestriction(String name, String type, String operator, String restriction, String matchOperator) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<QueryDTO><matchOperator>" + matchOperator + "</matchOperator><queryRestrictionList>"
                + "<queryRestriction><attribute><maxOccurs>1</maxOccurs><minOccurs>0</minOccurs><name>" + name
                + "</name><nillable>true</nillable><type>" + type + "</type><value></value></attribute>"
                + "<operator>" + operator + "</operator><restriction>" + restriction + "</restriction>"
                + "</queryRestriction></queryRestrictionList></QueryDTO>";
    }

    /**
     * @param count the value of the {@code numberOfFeatures} attribute
     * @return a minimal WFS 1.1.0 HITS {@code FeatureCollection} response carrying the given count
     */
    private static String hits(int count) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<wfs:FeatureCollection xmlns:wfs=\"http://www.opengis.net/wfs\" numberOfFeatures=\"" + count
                + "\" timeStamp=\"2024-01-01T00:00:00.000Z\"></wfs:FeatureCollection>";
    }

    /**
     * @param count the number of dummy features to emit
     * @return a GeoJSON {@code FeatureCollection} with {@code count} minimal features
     */
    private static String geoJson(int count) {
        StringBuilder builder = new StringBuilder("{\"type\":\"FeatureCollection\",\"totalFeatures\":").append(count).append(",\"features\":[");
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append("{\"type\":\"Feature\",\"id\":\"states.").append(i)
                    .append("\",\"geometry\":null,\"properties\":{\"STATE_NAME\":\"State ").append(i).append("\"}}");
        }
        return builder.append("]}").toString();
    }
}
