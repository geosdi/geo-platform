package org.geosdi.geoplatform.connector.wms.store;

import org.geosdi.geoplatform.connector.server.v111.GPWMSDescribeLayerV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.geosdi.geoplatform.connector.server.v111.WMSGetCapabilitiesV111Request;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.server.store.GPWMSConnectorBuilder.WMSConnectorBuilder.wmsConnectorBuilder;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSConnectorStoreEsriCatalogoFraneV111Test {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorStoreEsriCatalogoFraneV111Test.class);
    //
    private static IGPWMSConnectorStoreV111 wmsServerConnectorMinisteroAmbiente;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsServerConnectorMinisteroAmbiente = wmsConnectorBuilder()
                .wmsConnectorBuilderV111()
                .withServerUrl(new URL("http://wms.pcn.minambiente.it/ogc?map=/ms_ogc/WMS_v1.3/Vettoriali/Catalogo_Frane.map"))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(40)
                        .withDefaultMaxPerRoute(20)
                        .withMaxRedirect(10)
                        .build()).build();
    }


    @Test
    public void a_wmsGetCapabilitiesV111CatalogoFraneTest() throws Exception {
        WMSGetCapabilitiesV111Request wmsGetCapabilitiesRequest = wmsServerConnectorMinisteroAmbiente.createGetCapabilitiesRequest();
        logger.info("###############################WMS_GET_CAPABILITIES_V111_CATALOGO_FRANE_RESPONSE : {}\n", wmsGetCapabilitiesRequest.getResponseAsString());
    }

    @Test
    public void b_wmsDescribeLayerV111CatalogoFraneTest() throws Exception {
        GPWMSDescribeLayerV111Request wmsDescribeLayerRequest = wmsServerConnectorMinisteroAmbiente.createDescribeLayerRequest();
        logger.info("##########################WMS_DESCRIBE_LAYER_RESPONSE_V111_CATALOGO_FRANE_RESPONSE : {}\n", wmsDescribeLayerRequest
                .withLayers("RN.CATALOGO_FRANE.PIFF", "RN.CATALOGO_FRANE.LINEARI",
                        "RN.CATALOGO_FRANE.DIREZIONI", "RN.CATALOGO_FRANE.AREE.FRANE.DIFFUSE",
                        "RN.CATALOGO_FRANE.DGPV", "RN.CATALOGO_FRANE.POLIGONALI", "RN.CATALOGO_FRANE").getResponse());
    }
}