package org.geosdi.geoplatform.connector.server;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetFeatureInfoRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSServerConnector<WMSGetCapabilities extends GPWMSGetCapabilitiesRequest<?>, WMSGetFeatureInfo extends GPWMSGetFeatureInfoRequest> extends GPServerConnector {

    /**
     * @return {@link WMSGetCapabilities}
     */
    WMSGetCapabilities createGetCapabilitiesRequest();

    /**
     * @return {@link WMSGetFeatureInfo}
     */
    WMSGetFeatureInfo createGetFeatureInfoRequest();

    /**
     * @return {@link WMSVersion}
     */
    @Override
    WMSVersion getVersion();
}