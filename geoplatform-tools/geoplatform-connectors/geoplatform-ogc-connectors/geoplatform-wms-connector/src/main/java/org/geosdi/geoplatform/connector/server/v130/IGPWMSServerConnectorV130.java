package org.geosdi.geoplatform.connector.server.v130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWMSServerConnectorV130<WMSGetCapabilities> {

    /**
     * @return {@link WMSGetCapabilities}
     */
    WMSGetCapabilities createGetCapabilitiesRequest();

    /**
     * @return {@link GPWMSGetFeatureInfoV130Request<Object>}
     */
    GPWMSGetFeatureInfoV130Request<Object> createGetFeatureInfoRequest();
}