package org.geosdi.geoplatform.cxf.rs.support.geocoding.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeocodingResult extends Serializable {

    /**
     * @return {@link Long}
     */
    Long getTotal();

    /**
     * @return {@link List<IGPGeocodingLocality>}
     */
    List<IGPGeocodingLocality> getLocalities();
}
