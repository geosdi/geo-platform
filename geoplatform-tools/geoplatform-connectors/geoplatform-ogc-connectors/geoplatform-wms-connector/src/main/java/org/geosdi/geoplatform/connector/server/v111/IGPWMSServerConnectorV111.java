package org.geosdi.geoplatform.connector.server.v111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWMSServerConnectorV111<WMSGetCapabilities> {

    /**
     * @return {@link WMSGetCapabilities}
     */
    WMSGetCapabilities createGetCapabilitiesRequest();

    /**
     * @return {@link GPWMSDescribeLayerV111Request}
     */
    GPWMSDescribeLayerV111Request createDescribeLayerRequest();
}