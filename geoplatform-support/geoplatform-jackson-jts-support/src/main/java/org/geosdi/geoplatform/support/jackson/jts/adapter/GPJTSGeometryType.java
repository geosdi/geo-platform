package org.geosdi.geoplatform.support.jackson.jts.adapter;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJTSGeometryType extends Serializable {

    /**
     * @return {@link String}
     */
    String getType();
}