package org.geosdi.geoplatform.connector.server.request;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPTransactionIdGen extends Serializable {

    /**
     * @return {@link String}
     */
    String value();
}