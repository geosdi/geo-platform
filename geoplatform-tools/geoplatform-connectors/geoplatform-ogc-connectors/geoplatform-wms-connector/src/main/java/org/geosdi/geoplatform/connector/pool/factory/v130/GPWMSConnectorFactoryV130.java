package org.geosdi.geoplatform.connector.pool.factory.v130;

import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.pool.factory.GPWMSConnectorFactory;
import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;

import static org.geosdi.geoplatform.connector.server.v130.WMSConnectorBuilderV130.wmsConnectorBuilderV130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorFactoryV130 extends GPWMSConnectorFactory<IGPWMSConnectorStoreV130> {

    /**
     * @param key
     * @return {@link IGPWMSConnectorStoreV130}
     * @throws Exception
     */
    @Override
    protected IGPWMSConnectorStoreV130 internalCreate(GPPoolConnectorKey key) throws Exception {
        return wmsConnectorBuilderV130()
                .withServerUrl(key.getServerUrl())
                .withPooledConnectorConfig(key.getPooledConnectorConfig())
                .withClientSecurity(key.getSecurityConnector())
                .build();
    }
}