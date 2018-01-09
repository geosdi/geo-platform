package org.geosdi.geoplatform.connector.pool.key;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Getter
@ToString(callSuper = true)
public class GPPoolGeoserverConnectorKey extends GPPoolConnectorKey {

    private final JacksonSupport jacksonSupport;

    /**
     * @param serverUrl
     * @param pooledConnectorConfig
     * @param securityConnector
     * @param version
     * @param jacksonSupport
     */
    public GPPoolGeoserverConnectorKey(URL serverUrl, GPPooledConnectorConfig pooledConnectorConfig,
            GPSecurityConnector securityConnector, String version, JacksonSupport jacksonSupport) {
        super(serverUrl, pooledConnectorConfig, securityConnector, version);
        this.jacksonSupport = jacksonSupport;
    }
}