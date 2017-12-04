package org.geosdi.geoplatform.connector.server;

import org.geosdi.geoplatform.connector.WPSVersion;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWPSConnector extends GPServerConnector {

    /**
     * @return {@link WPSGetCapabilitiesRequest}
     */
    WPSGetCapabilitiesRequest createGetCapabilitiesRequest();

    /**
     * @return {@link WPSVersion}
     */
    WPSVersion getVersion();
}
