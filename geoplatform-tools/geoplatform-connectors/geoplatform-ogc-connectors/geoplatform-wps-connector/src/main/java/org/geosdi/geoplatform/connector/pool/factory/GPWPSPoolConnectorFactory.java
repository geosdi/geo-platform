package org.geosdi.geoplatform.connector.pool.factory;

import org.geosdi.geoplatform.connector.GPWPSConnectorStore;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorFactory;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.geosdi.geoplatform.connector.WPSVersion.toWPSVersion;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWPSPoolConnectorFactory extends GPPoolConnectorFactory<GPPoolConnectorKey, GPWPSConnectorStore> {

    /**
     * @param key
     * @return {@link GPWPSConnectorStore}
     * @throws Exception
     */
    @Override
    public GPWPSConnectorStore create(GPPoolConnectorKey key) throws Exception {
        checkNotNull(key, "The GPPoolConnectorKey must not be null");
        return new GPWPSConnectorStore(key.getServerUrl(), key.getSecurityConnector(), toWPSVersion(key.getVersion()));
    }
}
