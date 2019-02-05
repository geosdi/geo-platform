package org.geosdi.geoplatform.connector.server.store;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPWMSConnectorStore<WMSGetCapabilities, WMSServerConnector extends GPWMSServerConnector<?>>
        extends GPConnectorStore<WMSServerConnector> implements GPWMSConnector<WMSGetCapabilities> {

    /**
     * @param theServer
     */
    protected GPWMSConnectorStore(WMSServerConnector theServer) {
        super(theServer);
    }

    /**
     * @return {@link WMSVersion}
     */
    @Override
    public WMSVersion getVersion() {
        return this.server.getVersion();
    }
}