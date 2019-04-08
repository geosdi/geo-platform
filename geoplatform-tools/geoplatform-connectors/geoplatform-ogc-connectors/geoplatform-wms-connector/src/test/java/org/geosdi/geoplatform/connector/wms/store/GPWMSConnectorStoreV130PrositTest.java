package org.geosdi.geoplatform.connector.wms.store;

import org.geosdi.geoplatform.connector.server.request.*;
import org.geosdi.geoplatform.connector.server.v130.GPWMSGetFeatureInfoV130Request;
import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Arrays;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.store.GPWMSConnectorBuilder.WMSConnectorBuilder.wmsConnectorBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorStoreV130PrositTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreV130PrositTest.class);
    //
    private static IGPWMSConnectorStoreV130 wmsServerConnector;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnector = wmsConnectorBuilder()
                .wmsConnectorBuilderV130()
                .withServerUrl(new URL("https://prosit.geosdi.org/geoserver/wms"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(20)
                        .withDefaultMaxPerRoute(8)
                        .withMaxRedirect(5)
                        .build()).build();
    }


    @Test
    public void wmsGetFeatureInfoV130Test() throws Exception {
        GPWMSGetFeatureInfoV130Request<Object> wmsGetFeatureInfoRequest = wmsServerConnector.createGetFeatureInfoRequest();
        GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(40.97210836426291, 15.824662897187245, 40.98109151703362, 15.836561468047133);
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, Arrays.asList("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali"),
                "EPSG:4326", "256", "256");
        logger.info("##################################WMS_GET_FEATURE_INFO_V111_RESPONSE : {}\n", wmsGetFeatureInfoRequest.withQueryLayers("admin:admin_shp_vigneti_catastali", "admin:admin_shp_oliveti_catastali")
                .withWMSGetMapRequest(wmsGetMapBaseRequest)
                .withFeatureCount(8)
                .withInfoFormat(WMSFeatureInfoFormat.GML).withX(154).withY(230).getResponse());
    }
}