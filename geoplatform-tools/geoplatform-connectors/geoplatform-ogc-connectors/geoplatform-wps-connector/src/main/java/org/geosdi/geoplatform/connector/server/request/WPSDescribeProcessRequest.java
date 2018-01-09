package org.geosdi.geoplatform.connector.server.request;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WPSDescribeProcessRequest<T> extends GPJAXBConnectorRequest<T> {

    /**
     * <p>One or more (mandatory).<br/> One for each desired Process, unordered list</p>
     *
     * @return {@link List<String>}
     */
    List<String> getProcessIdentifiers();

    /**
     * @param theProcessIndetifiers
     */
    void setProcessIdentifiers(List<String> theProcessIndetifiers);

    /**
     * @return {@link Boolean}
     */
    Boolean isSetProcessIdentifiers();
}
