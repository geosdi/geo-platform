package org.geosdi.geoplatform.connector.pool.builder;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.pool.factory.GPGeoserverConnectorFactory;
import org.geosdi.geoplatform.connector.pool.key.GPPoolGeoserverConnectorKey;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorBuilderPool extends AbstractConnectorBuilder<GPGeoserverConnectorBuilderPool, GPGeoserverConnectorStore> {

    static {
        geoserverConnectorPool = new GenericKeyedObjectPool<>(new GPGeoserverConnectorFactory(), new GPPoolConnectorConfig());
    }

    private static final GenericKeyedObjectPool<GPPoolGeoserverConnectorKey, GPGeoserverConnectorStore> geoserverConnectorPool;
    //
    private JacksonSupport jacksonSupport;

    private GPGeoserverConnectorBuilderPool() {
    }

    /**
     * @return {@link GPGeoserverConnectorBuilderPool}
     */
    public static GPGeoserverConnectorBuilderPool builderPool() {
        return new GPGeoserverConnectorBuilderPool();
    }

    /**
     * @param theJacksonSupport
     * @return {@link GPGeoserverConnectorBuilderPool}
     */
    public GPGeoserverConnectorBuilderPool withJacksonSupport(JacksonSupport theJacksonSupport) {
        this.jacksonSupport = theJacksonSupport;
        return self();
    }

    /**
     * @return {@link GPGeoserverConnectorStore}
     * @throws Exception
     */
    @Override
    public GPGeoserverConnectorStore build() throws Exception {
        checkNotNull(serverUrl, "Geoserver Connector Server URL must not be null.");
        GPPoolGeoserverConnectorKey key = new GPPoolGeoserverConnectorKey(serverUrl, pooledConnectorConfig,
                securityConnector, version, ((this.jacksonSupport != null) ? this.jacksonSupport : new GPJacksonSupport()));
        GPGeoserverConnectorStore geoserverConnectorStore = geoserverConnectorPool.borrowObject(key);
        return geoserverConnectorStore;
    }
}