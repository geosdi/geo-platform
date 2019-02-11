package org.geosdi.geoplatform.connector.server.store;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.api.GPConnectorStore;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetFeatureInfoRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPWMSConnectorStore<WMSGetCapabilities extends GPWMSGetCapabilitiesRequest<?>, WMSGetFeatureInfo extends GPWMSGetFeatureInfoRequest, WMSServerConnector extends GPWMSServerConnector<WMSGetCapabilities, WMSGetFeatureInfo>>
        extends GPConnectorStore<WMSServerConnector> implements GPWMSConnector<WMSGetCapabilities, WMSGetFeatureInfo> {

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