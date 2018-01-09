package org.geosdi.geoplatform.connector.geoserver.request.model.about.version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverVersionResource.class)
public interface IGPGeoserverVersionResource extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getBuildTimestamp();

    /**
     * @return {@link String}
     */
    String getVersion();

    /**
     * @return {@link String}
     */
    String getGitRevision();
}
