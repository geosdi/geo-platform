package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.WMSVersion.V111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSConnectorBuilderV111 extends AbstractConnectorBuilder<WMSConnectorBuilderV111, IGPWMSConnectorStoreV111> implements GPWMSConnectorBuilderV111 {

    WMSConnectorBuilderV111() {
        this.withVersion(V111.getVersion());
    }

    /**
     * @return {@link WMSConnectorBuilderV111}
     */
    public static WMSConnectorBuilderV111 wmsConnectorBuilderV111() {
        return new WMSConnectorBuilderV111();
    }

    /**
     * @return {@link GPWMSConnectorStoreV111}
     * @throws Exception
     */
    @Override
    public IGPWMSConnectorStoreV111 build() throws Exception {
        checkArgument(this.serverUrl != null, "The Parameter serverURL for WMSConnectorBuilderV111 must not be null.");
        return new GPWMSConnectorStoreV111(this.serverUrl, this.pooledConnectorConfig, this.securityConnector);
    }
}