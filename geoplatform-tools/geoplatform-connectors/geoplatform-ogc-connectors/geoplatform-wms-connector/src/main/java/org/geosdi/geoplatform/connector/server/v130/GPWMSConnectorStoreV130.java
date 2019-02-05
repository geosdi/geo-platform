package org.geosdi.geoplatform.connector.server.v130;

import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.connector.server.store.GPWMSConnectorStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorStoreV130 extends GPWMSConnectorStore<WMSGetCapabilitiesV130Request, GPWMSServerConnectorV130> implements IGPWMSConnectorStoreV130 {

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPWMSConnectorStoreV130(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector) {
        super(new GPWMSServerConnectorV130(server, pooledConnectorConfig, securityConnector));
    }

    /**
     * @return {@link WMSGetCapabilitiesV130Request}
     */
    @Override
    public WMSGetCapabilitiesV130Request createGetCapabilitiesRequest() {
        return this.server.createGetCapabilitiesRequest();
    }
}