package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.server.store.GPWMSConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWMSConnectorStoreV111 extends GPWMSConnector<WMSGetCapabilitiesV111Request> {

    /**
     * @return {@link GPWMSDescribeLayerV111Request}
     */
    GPWMSDescribeLayerV111Request createDescribeLayerRequest();
}