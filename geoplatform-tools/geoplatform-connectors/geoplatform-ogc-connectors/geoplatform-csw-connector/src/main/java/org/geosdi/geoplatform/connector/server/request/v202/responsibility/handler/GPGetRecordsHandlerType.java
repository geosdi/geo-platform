package org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGetRecordsHandlerType extends Serializable {

    /**
     * @return {@link String}
     */
    String getHandlerType();
}