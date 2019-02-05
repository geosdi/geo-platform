package org.geosdi.geoplatform.connector.server;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSServerConnector<WMSGetCapabilities extends GPWMSGetCapabilitiesRequest<?>> extends GPServerConnector {

    /**
     * @return {@link WMSGetCapabilities}
     */
    WMSGetCapabilities createGetCapabilitiesRequest();

    /**
     * @return {@link WMSVersion}
     */
    @Override
    WMSVersion getVersion();
}