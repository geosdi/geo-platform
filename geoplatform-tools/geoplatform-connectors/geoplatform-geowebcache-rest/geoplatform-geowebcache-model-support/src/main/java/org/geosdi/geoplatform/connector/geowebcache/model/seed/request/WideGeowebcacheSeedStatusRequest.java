package org.geosdi.geoplatform.connector.geowebcache.model.seed.request;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WideGeowebcacheSeedStatusRequest extends Serializable {

    /**
     * @return {@link String}
     */
    String getLayerName();
}