package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.WPSVersion.toWPSVersion;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WPSConnectorBuilder extends AbstractConnectorBuilder<WPSConnectorBuilder, GPWPSConnectorStore> {

    protected WPSConnectorBuilder() {
    }

    /**
     * <p>Create a new GeoPlatform WPSConnectorBuilder with which to define a
     * specification for a GPWFSConnector.</p>
     *
     * @return {@link WPSConnectorBuilder}
     */
    public static WPSConnectorBuilder newWPSConnectorBuilder() {
        return new WPSConnectorBuilder();
    }

    @Override
    public GPWPSConnectorStore build() throws Exception {
        checkArgument((this.serverUrl != null), "The Parameter ServerURL must not be null.");
        return new GPWPSConnectorStore(serverUrl, pooledConnectorConfig, securityConnector, toWPSVersion(this.version));
    }
}
