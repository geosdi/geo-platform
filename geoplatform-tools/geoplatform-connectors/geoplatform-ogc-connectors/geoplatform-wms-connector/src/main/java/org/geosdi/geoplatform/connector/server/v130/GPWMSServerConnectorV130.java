package org.geosdi.geoplatform.connector.server.v130;

import org.geosdi.geoplatform.connector.server.GPBaseWMSServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.WMSVersion.V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSServerConnectorV130 extends GPBaseWMSServerConnector<WMSGetCapabilitiesV130Request> implements IGPWMSServerConnectorV130<WMSGetCapabilitiesV130Request> {

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPWMSServerConnectorV130(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector) {
        super(server, pooledConnectorConfig, securityConnector, V130);
    }

    /**
     * @return {@link WMSGetCapabilitiesV130Request}
     */
    @Override
    public WMSGetCapabilitiesV130Request createGetCapabilitiesRequest() {
        return new WMSGetCapabilitiesV130Request(this);
    }
}