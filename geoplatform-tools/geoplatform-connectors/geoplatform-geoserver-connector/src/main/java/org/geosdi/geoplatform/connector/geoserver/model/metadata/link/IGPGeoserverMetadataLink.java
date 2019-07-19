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
     * @param theType
     */
    void setType(String theType);

    /**
     * @return {@link String}
     */
    String getMetadataType();

    /**
     * @param theMetadataType
     */
    void setMetadataType(String theMetadataType);

    /**
     * @return {@link String}
     */
    String getContent();

    /**
     * @param theContent
     */
    void setContent(String theContent);
}