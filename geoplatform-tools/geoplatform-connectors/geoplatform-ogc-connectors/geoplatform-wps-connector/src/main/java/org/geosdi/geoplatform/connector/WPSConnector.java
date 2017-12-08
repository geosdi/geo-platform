package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.server.request.WPSDescribeProcessRequest;
import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WPSConnector {

    /**
     * @param <T>
     * @return {@link WPSGetCapabilitiesRequest<T>}
     */
    <T> WPSGetCapabilitiesRequest<T> createGetCapabilitiesRequest();

    /**
     * @param <T>
     * @return {@link WPSDescribeProcessRequest<T>}
     */
    <T> WPSDescribeProcessRequest<T> createDescribeProcessRequest();

    /**
     * @return {@link WPSVersion}
     */
    WPSVersion getVersion();
}
