package org.geosdi.geoplatform.connector.geoserver.request.model.about.version;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverAboutVersion extends Serializable {

    /**
     * @return {@link List< IGPGeoserverVersionResource >}
     */
    List<IGPGeoserverVersionResource> getResources();
}