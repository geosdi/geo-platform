package org.geosdi.geoplatform.connector.geoserver.request.model.about.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverAboutStatus.class)
public interface IGPGeoserverAboutStatus extends Serializable {

    /**
     * @return {@link List<IGPGeoserverAboutStatusEntry>}
     */
    List<IGPGeoserverAboutStatusEntry> getEntries();
}
