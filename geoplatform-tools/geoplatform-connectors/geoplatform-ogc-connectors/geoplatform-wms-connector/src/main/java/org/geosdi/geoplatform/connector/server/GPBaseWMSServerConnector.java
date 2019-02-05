package org.geosdi.geoplatform.connector.server;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseWMSServerConnector<WMSGetCapabilities extends GPWMSGetCapabilitiesRequest<?>> extends GPAbstractServerConnector implements GPWMSServerConnector<WMSGetCapabilities> {

    private final WMSVersion version;

    /**
     * @param urlServer
     * @param version
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nonnull(when = NEVER) WMSVersion version) {
        this(urlServer, null, version);
    }

    /**
     * @param urlServer
     * @param securityConnector
     * @param version
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion version) {
        this(analyzesServerURL(urlServer), securityConnector, version);
    }

    /**
     * @param urlServer
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) String urlServer, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion version) {
        this(analyzesServerURL(urlServer), pooledConnectorConfig, securityConnector, version);
    }

    /**
     * @param server
     * @param securityConnector
     * @param theVersion
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion theVersion) {
        this(server, null, securityConnector, theVersion);
    }

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param theVersion
     */
    protected GPBaseWMSServerConnector(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, @Nonnull(when = NEVER) WMSVersion theVersion) {
        super(server, securityConnector, pooledConnectorConfig);
        checkArgument(theVersion != null, "The Parameter version must not be null.");
        this.version = theVersion;
    }

    /**
     * @return {@link WMSVersion}
     */
    @Override
    public WMSVersion getVersion() {
        return this.version;
    }
}