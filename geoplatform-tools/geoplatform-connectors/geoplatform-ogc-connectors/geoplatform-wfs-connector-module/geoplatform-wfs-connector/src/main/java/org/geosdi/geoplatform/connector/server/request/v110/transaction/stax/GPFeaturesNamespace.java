package org.geosdi.geoplatform.connector.server.request.v110.transaction.stax;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPFeaturesNamespace extends Serializable {

    /**
     * @return {@link String}
     */
    String NAMESPACE();

    /**
     * @return {@link String}
     */
    String PREFIX();
}