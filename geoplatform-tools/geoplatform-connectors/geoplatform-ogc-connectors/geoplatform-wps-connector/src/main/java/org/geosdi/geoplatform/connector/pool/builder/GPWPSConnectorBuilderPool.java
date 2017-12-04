package org.geosdi.geoplatform.connector.pool.builder;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.connector.GPWPSConnectorStore;
import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.pool.factory.GPWPSPoolConnectorFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWPSConnectorBuilderPool extends AbstractConnectorBuilder<GPWPSConnectorBuilderPool, GPWPSConnectorStore> {

    static {
        wpsConnectorPool = new GenericKeyedObjectPool<>(new GPWPSPoolConnectorFactory(), new GPPoolConnectorConfig());
    }

    private static GenericKeyedObjectPool<GPPoolConnectorKey, GPWPSConnectorStore> wpsConnectorPool;

    protected GPWPSConnectorBuilderPool() {
    }

    /**
     * @return {@link GPWPSConnectorBuilderPool}
     */
    public static GPWPSConnectorBuilderPool wpsConnectorBuilderPool() {
        return new GPWPSConnectorBuilderPool();
    }

    /**
     * @return {@link GPWPSConnectorStore}
     * @throws Exception
     */
    @Override
    public GPWPSConnectorStore build() throws Exception {
        checkNotNull(serverUrl, "WPS Server URL must not be null.");
        GPPoolConnectorKey keyConnector = new GPPoolConnectorKey(serverUrl, pooledConnectorConfig,
                securityConnector, version);
        GPWPSConnectorStore wpsConnectorStore = wpsConnectorPool.borrowObject(keyConnector);
        wpsConnectorPool.returnObject(keyConnector, wpsConnectorStore);
        return wpsConnectorStore;
    }
}
