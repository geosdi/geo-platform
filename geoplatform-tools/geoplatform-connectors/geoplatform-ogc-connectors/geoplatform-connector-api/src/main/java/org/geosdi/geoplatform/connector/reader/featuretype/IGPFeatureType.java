package org.geosdi.geoplatform.connector.reader.featuretype;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPFeatureType extends Serializable {

    /**
     * @return {@link String}
     */
    String getPrefix();

    /**
     * @return {@link String}
     */
    String getName();
}