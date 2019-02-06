package org.geosdi.geoplatform.connector.pool.builder;

import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.server.store.GPWMSConnector;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPWMSConnectorBuilderPool<Builder extends GPWMSConnectorBuilderPool, Store extends GPWMSConnector>
        extends AbstractConnectorBuilder<Builder, Store> {

    protected GPWMSConnectorBuilderPool() {
    }

    /**
     * @return {@link Store}
     * @throws Exception
     */
    @Override
    public Store build() throws Exception {
        checkNotNull(serverUrl, "WMS Server URL must not be null.");
        checkArgument((this.version != null) && !(this.version.trim().isEmpty()), "The Parameter wmsVersion must not be null or an empty string.");
        return this.internalBuild(new GPPoolConnectorKey(this.serverUrl, this.pooledConnectorConfig, this.securityConnector, this.version));
    }

    /**
     * @param key
     * @return
     * @throws Exception
     */
    protected abstract Store internalBuild(@Nonnull(when = NEVER) GPPoolConnectorKey key) throws Exception;
}
