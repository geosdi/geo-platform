package org.geosdi.geoplatform.connector.pool.factory;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorFactory;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.server.store.GPWMSConnector;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPWMSConnectorFactory<WMSConnectorStore extends GPWMSConnector> extends GPPoolConnectorFactory<GPPoolConnectorKey, WMSConnectorStore> {

    /**
     * @param key
     * @return {@link WMSConnectorStore}
     * @throws Exception
     */
    @Override
    public WMSConnectorStore create(GPPoolConnectorKey key) throws Exception {
        checkNotNull(key, "The GPPoolConnectorKey must not be null");
        WMSVersion wmsVersion = WMSVersion.forValue(key.getVersion());
        checkNotNull(wmsVersion, "The WMSVersion must not be null");
        return this.internalCreate(key);
    }

    /**
     * @param key
     * @return {@link WMSConnectorStore}
     * @throws Exception
     */
    protected abstract WMSConnectorStore internalCreate(GPPoolConnectorKey key) throws Exception;
}