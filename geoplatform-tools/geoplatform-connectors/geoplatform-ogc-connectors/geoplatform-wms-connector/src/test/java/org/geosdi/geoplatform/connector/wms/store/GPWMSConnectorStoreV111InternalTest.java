package org.geosdi.geoplatform.connector.wms.store;

import org.geosdi.geoplatform.connector.server.request.*;
import org.geosdi.geoplatform.connector.server.v111.GPWMSDescribeLayerV111Request;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.geosdi.geoplatform.connector.server.v111.WMSGetCapabilitiesV111Request;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Arrays;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.store.GPWMSConnectorBuilder.WMSConnectorBuilder.wmsConnectorBuilder;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStoreV111InternalTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreV111InternalTest.class);
    //
    private static IGPWMSConnectorStoreV111 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilder()
                .wmsConnectorBuilderV111()
                .withServerUrl(new URL("http://150.145.141.180/geoserver/wms"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(8)
                        .withMaxRedirect(5)
                        .build()).build();
    }

    @Test
    public void a_wmsGetCapabilitiesV111Test() throws Exception {
        WMSGetCapabilitiesV111Request wmsGetCapabilitiesRequest = wmsServerConnector.createGetCapabilitiesRequest();
        logger.debug("###############################WMS_GET_CAPABILITIES_V111_RESPONSE : {}\n", wmsGetCapabilitiesRequest.getResponseAsString());
    }

    @Test
    public void b_wmsDescribeLayerV111Test() throws Exception {
        GPWMSDescribeLayerV111Request wmsDescribeLayerRequest = wmsServerConnector.createDescribeLayerRequest();
        logger.info("##########################WMS_DESCRIBE_LAYER_RESPONSE_V111 : {}\n", wmsDescribeLayerRequest
                .withLayers("sf:sfdem", "sf:roads", "sf:restricted").getResponse());
    }

    @Test
    public void c_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(-130d, 24d, -66d, 50d);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, Arrays.asList("topp:states", "topp:states"),
                "EPSG:4326", "550", "250");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("topp:states", "topp:states")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(2)
                .withInfoFormat(WMSFeatureInfoFormat.GML_AS_STRING).withX(170).withY(160).getResponse());
    }

    @Test
    public void d_wmsGetFeatureInfoV111Test() throws Exception {
        GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(-74.01849076151848, 40.69844752550125, -73.98381516337395, 40.73312312364578);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, Arrays.asList("tiger:tiger_roads"),
                "EPSG:4326", "101", "101");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("tiger:tiger_roads")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(12)
                .withInfoFormat(WMSFeatureInfoFormat.GML).withX(50).withY(50).getResponse());
    }
}