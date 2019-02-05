package org.geosdi.geoplatform.connector.server.store;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.api.GeoPlatformConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSConnector<WMSGetCapabilities> extends GeoPlatformConnector {

    /**
     * @return {@link WMSGetCapabilities}
     */
    WMSGetCapabilities createGetCapabilitiesRequest();

    /**
     * @return {@link Version}
     */
    <Version extends WMSVersion> Version getVersion();
}