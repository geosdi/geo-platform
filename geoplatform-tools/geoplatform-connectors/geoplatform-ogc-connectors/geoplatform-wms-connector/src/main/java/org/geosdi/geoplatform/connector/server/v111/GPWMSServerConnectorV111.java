package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.server.GPBaseWMSServerConnector;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URL;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.WMSVersion.V111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSServerConnectorV111 extends GPBaseWMSServerConnector<WMSGetCapabilitiesV111Request> implements IGPWMSServerConnectorV111<WMSGetCapabilitiesV111Request> {

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPWMSServerConnectorV111(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector) {
        super(server, pooledConnectorConfig, securityConnector, V111);
    }

    /**
     * @return {@link WMSGetCapabilitiesV111Request}
     */
    @Override
    public WMSGetCapabilitiesV111Request createGetCapabilitiesRequest() {
        return new WMSGetCapabilitiesV111Request(this);
    }

    /**
     * @return {@link GPWMSDescribeLayerV111Request}
     */
    @Override
    public GPWMSDescribeLayerV111Request createDescribeLayerRequest() {
        return new WMSDescribeLayerV111Request(this);
    }
}