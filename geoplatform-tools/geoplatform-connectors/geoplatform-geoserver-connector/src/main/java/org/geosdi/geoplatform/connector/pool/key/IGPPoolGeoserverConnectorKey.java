package org.geosdi.geoplatform.connector.pool.key;

import org.geosdi.geoplatform.connector.api.pool.IGPPoolConnectorKey;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPPoolGeoserverConnectorKey extends IGPPoolConnectorKey {

    /**
     * @return {@link JacksonSupport}
     */
    JacksonSupport getJacksonSupport();

    /**
     * @param serverUrl
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     * @param jacksonSupport
     * @return {@link IGPPoolGeoserverConnectorKey}
     */
    static IGPPoolGeoserverConnectorKey of(@Nonnull(when = NEVER) URL serverUrl, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector, String version, JacksonSupport jacksonSupport) {
        return new GPPoolGeoserverConnectorKey(serverUrl, pooledConnectorConfig, securityConnector, version, jacksonSupport);
    }
}