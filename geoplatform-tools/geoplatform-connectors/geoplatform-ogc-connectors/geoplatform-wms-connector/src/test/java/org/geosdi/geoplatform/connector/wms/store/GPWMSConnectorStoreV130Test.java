package org.geosdi.geoplatform.connector.wms.store;

import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;
import org.geosdi.geoplatform.connector.server.v130.WMSGetCapabilitiesV130Request;
import org.geosdi.geoplatform.wms.v130.WMSCapabilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.store.GPWMSConnectorBuilder.WMSConnectorBuilder.wmsConnectorBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorStoreV130Test {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreV130Test.class);
    //
    private static IGPWMSConnectorStoreV130 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilder()
                .wmsConnectorBuilderV130()
                .withServerUrl(new URL("http://rsdi.regione.basilicata.it:80/rbgeoserver2016/wms"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(150)
                        .withDefaultMaxPerRoute(80)
                        .withMaxRedirect(20)
                        .build()).build();
    }

    @Test
    public void a_wmsGetCapabilitiesV130Test() throws Exception {
        WMSGetCapabilitiesV130Request wmsGetCapabilitiesRequest = wmsServerConnector.createGetCapabilitiesRequest();
        WMSCapabilities wmsCapabilities = wmsGetCapabilitiesRequest.getResponse();
        Assert.assertTrue(wmsCapabilities.getVersion().equalsIgnoreCase("1.3.0"));
        logger.info("###############################WMS_GET_CAPABILITIES_V130_RESPONSE : {}\n", wmsGetCapabilitiesRequest.getResponse());
    }
}