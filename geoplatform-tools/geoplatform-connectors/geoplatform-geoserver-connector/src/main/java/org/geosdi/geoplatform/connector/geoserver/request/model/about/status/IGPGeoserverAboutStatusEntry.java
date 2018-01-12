package org.geosdi.geoplatform.connector.geoserver.request.model.about.status;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverAboutStatusEntry.class)
public interface IGPGeoserverAboutStatusEntry extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getHref();
}