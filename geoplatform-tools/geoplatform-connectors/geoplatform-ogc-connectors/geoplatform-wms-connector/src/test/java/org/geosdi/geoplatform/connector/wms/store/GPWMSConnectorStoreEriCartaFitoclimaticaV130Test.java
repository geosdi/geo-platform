package org.geosdi.geoplatform.connector.wms.store;

import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;
import org.geosdi.geoplatform.connector.server.v130.WMSGetCapabilitiesV130Request;
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
public class GPWMSConnectorStoreEriCartaFitoclimaticaV130Test {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreEriCartaFitoclimaticaV130Test.class);
    //
    private static IGPWMSConnectorStoreV130 wmsServerConnectorMinisteroAmbiente;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnectorMinisteroAmbiente = wmsConnectorBuilder()
                .wmsConnectorBuilderV130()
                .withServerUrl(new URL("http://wms.pcn.minambiente.it/ogc?map=/ms_ogc/WMS_v1.3/Vettoriali/Carta_fitoclimatica.map"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(40)
                        .withDefaultMaxPerRoute(20)
                        .withMaxRedirect(10)
                        .build()).build();
    }


    @Test
    public void wmsGetCapabilitiesV130CartaFitoclimaticaTest() throws Exception {
        WMSGetCapabilitiesV130Request wmsGetCapabilitiesRequest = wmsServerConnectorMinisteroAmbiente.createGetCapabilitiesRequest();
        logger.info("###############################WMS_GET_CAPABILITIES_V130_CARTA_FITOCLIMATICA_RESPONSE : \n{}\n", wmsGetCapabilitiesRequest.getResponseAsString());
    }
}