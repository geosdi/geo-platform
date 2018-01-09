package org.geosdi.geoplatform.connector.store;

import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.GeoserverVersion.fromString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPGeoserverConnectorStoreBuilder extends AbstractConnectorBuilder<GPGeoserverConnectorStoreBuilder, GPGeoserverConnectorStore> {

    private JacksonSupport jacksonSupport;

    private GPGeoserverConnectorStoreBuilder() {
    }

    /**
     * @return {@link GPGeoserverConnectorStoreBuilder}
     */
    public static GPGeoserverConnectorStoreBuilder geoserverConnectorBuilder() {
        return new GPGeoserverConnectorStoreBuilder();
    }

    /**
     * @param theJacksoSupport
     * @return
     */
    public GPGeoserverConnectorStoreBuilder withJacksoSupport(JacksonSupport theJacksoSupport) {
        this.jacksonSupport = theJacksoSupport;
        return self();
    }

    /**
     * @return {@link GPGeoserverConnectorStore}
     * @throws Exception
     */
    @Override
    public GPGeoserverConnectorStore build() throws Exception {
        checkArgument(this.serverUrl != null, "Server URL must not be null");
        return new GPGeoserverConnectorStore(this.serverUrl, this.pooledConnectorConfig, this.securityConnector,
                ((this.jacksonSupport != null) ? this.jacksonSupport : new GPJacksonSupport()), fromString(this.version));
    }
}