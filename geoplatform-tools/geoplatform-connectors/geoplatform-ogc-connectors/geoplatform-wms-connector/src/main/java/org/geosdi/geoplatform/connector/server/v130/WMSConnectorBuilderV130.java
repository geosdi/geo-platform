package org.geosdi.geoplatform.connector.server.v130;

import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.WMSVersion.V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSConnectorBuilderV130 extends AbstractConnectorBuilder<WMSConnectorBuilderV130, IGPWMSConnectorStoreV130> implements GPWMSConnectorBuilderV130 {

    WMSConnectorBuilderV130() {
        this.withVersion(V130.getVersion());
    }

    /**
     * @return {@link WMSConnectorBuilderV130}
     */
    public static WMSConnectorBuilderV130 wmsConnectorBuilderV130() {
        return new WMSConnectorBuilderV130();
    }

    /**
     * @return {@link GPWMSConnectorStoreV130}
     * @throws Exception
     */
    @Override
    public IGPWMSConnectorStoreV130 build() throws Exception {
        checkArgument(this.serverUrl != null, "The Parameter serverURL for WMSConnectorBuilderV130 must not be null.");
        return new GPWMSConnectorStoreV130(this.serverUrl, this.pooledConnectorConfig, this.securityConnector);
    }
}