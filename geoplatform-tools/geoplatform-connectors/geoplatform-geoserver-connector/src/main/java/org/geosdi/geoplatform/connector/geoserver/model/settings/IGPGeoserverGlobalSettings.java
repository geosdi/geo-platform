package org.geosdi.geoplatform.connector.geoserver.model.settings;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.settings.coverage.IGPGeoserverCoverageAccessSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.jai.IGPGeoserverJaiSettings;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverGlobalSettings.class)
public interface IGPGeoserverGlobalSettings extends Serializable {

    /**
     * @return {@link IGPGeoserverSettings}
     */
    IGPGeoserverSettings getSettings();

    /**
     * @return {@link IGPGeoserverJaiSettings}
     */
    IGPGeoserverJaiSettings getJai();

    /**
     * @return {@link IGPGeoserverCoverageAccessSettings}
     */
    IGPGeoserverCoverageAccessSettings getCoverageAccess();

    /**
     * @return {@link Integer}
     */
    Integer getUpdateSequence();

    /**
     * @return {@link Integer}
     */
    Integer getFeatureTypeCacheSize();

    /**
     * @return {@link Boolean}
     */
    boolean isGlobalServices();

    /**
     * @return {@link Integer}
     */
    Integer getXmlPostRequestLogBufferSize();
}