package org.geosdi.geoplatform.connector.pool.factory;

import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorFactory;
import org.geosdi.geoplatform.connector.pool.key.GPPoolGeoserverConnectorKey;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.geosdi.geoplatform.connector.GeoserverVersion.fromString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorFactory extends GPPoolConnectorFactory<GPPoolGeoserverConnectorKey, GPGeoserverConnectorStore> {

    /**
     * @param key
     * @return {@link GPGeoserverConnectorStore}
     * @throws Exception
     */
    @Override
    public GPGeoserverConnectorStore create(GPPoolGeoserverConnectorKey key) throws Exception {
        checkNotNull(key, "The GPPoolGeoserverConnectorKey must not be null");
        return new GPGeoserverConnectorStore(key.getServerUrl(), key.getPooledConnectorConfig(), key.getSecurityConnector(),
                key.getJacksonSupport(), fromString(key.getVersion()));
    }
}
