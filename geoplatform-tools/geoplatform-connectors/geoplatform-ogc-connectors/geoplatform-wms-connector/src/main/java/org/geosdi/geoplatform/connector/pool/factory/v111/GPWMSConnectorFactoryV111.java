package org.geosdi.geoplatform.connector.pool.factory.v111;

import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.pool.factory.GPWMSConnectorFactory;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.server.v111.WMSConnectorBuilderV111.wmsConnectorBuilderV111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorFactoryV111 extends GPWMSConnectorFactory<IGPWMSConnectorStoreV111> {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSConnectorFactoryV111.class);

    /**
     * @param key
     * @return {@link IGPWMSConnectorStoreV111}
     * @throws Exception
     */
    @Override
    protected IGPWMSConnectorStoreV111 internalCreate(GPPoolConnectorKey key) throws Exception {
        logger.trace("\n\n##############################CALLED : {} with Key : {}\n\n", this.getClass().getSimpleName(), key);
        return wmsConnectorBuilderV111()
                .withServerUrl(key.getServerUrl())
                .withPooledConnectorConfig(key.getPooledConnectorConfig())
                .withClientSecurity(key.getSecurityConnector())
                .build();
    }
}