package org.geosdi.geoplatform.connector.server.v111;

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
public class GPWMSConnectorStoreV111 extends GPWMSConnectorStore<WMSGetCapabilitiesV111Request, GPWMSGetFeatureInfoV111Request<Object>, GPWMSServerConnectorV111> implements IGPWMSConnectorStoreV111 {

    /**
     * @param server
     * @param pooledConnectorConfig
     * @param securityConnector
     */
    protected GPWMSConnectorStoreV111(@Nonnull(when = NEVER) URL server, @Nullable GPPooledConnectorConfig pooledConnectorConfig,
            @Nullable GPSecurityConnector securityConnector) {
        super(new GPWMSServerConnectorV111(server, pooledConnectorConfig, securityConnector));
    }

    /**
     * @return {@link WMSGetCapabilitiesV111Request}
     */
    @Override
    public WMSGetCapabilitiesV111Request createGetCapabilitiesRequest() {
        return this.server.createGetCapabilitiesRequest();
    }

    /**
     * @return {@link GPWMSGetFeatureInfoV111Request<Object>}
     */
    @Override
    public GPWMSGetFeatureInfoV111Request<Object> createGetFeatureInfoRequest() {
        return this.server.createGetFeatureInfoRequest();
    }

    /**
     * @return {@link GPWMSDescribeLayerV111Request}
     */
    @Override
    public GPWMSDescribeLayerV111Request createDescribeLayerRequest() {
        return this.server.createDescribeLayerRequest();
    }
}