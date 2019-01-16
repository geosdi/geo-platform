package org.geosdi.geoplatform.connector.geoserver.model.metadata.link;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverMetadataLink.class)
public interface IGPGeoserverMetadataLink extends Serializable {

    /**
     * @return {@link String}
     */
    String getType();

    /**
     * @return {@link String}
     */
    String getMetadataType();

    /**
     * @return {@link String}
     */
    String getContent();
}