package org.geosdi.geoplatform.connector.wms.store;

import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;
import org.geosdi.geoplatform.connector.server.v130.WMSGetCapabilitiesV130Request;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.pool.builder.v130.GPWMSConnectorBuilderPoolV130.wmsConnectorBuilderPoolV130;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStorePoolV130Test {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStorePoolV130Test.class);
    //
    private static IGPWMSConnectorStoreV130 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilderPoolV130()
                .withServerUrl(new URL("http://150.145.141.180/geoserver/wms"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(150)
                        .withDefaultMaxPerRoute(80)
                        .withMaxRedirect(20)
                        .build()).build();
    }

    @Test
    public void a_wmsGetCapabilitiesV130Test() throws Exception {
        WMSGetCapabilitiesV130Request wmsGetCapabilitiesRequest = wmsServerConnector.createGetCapabilitiesRequest();
        logger.info("###############################WMS_GET_CAPABILITIES_V130_RESPONSE : {}\n", wmsGetCapabilitiesRequest.getResponse());
    }
}