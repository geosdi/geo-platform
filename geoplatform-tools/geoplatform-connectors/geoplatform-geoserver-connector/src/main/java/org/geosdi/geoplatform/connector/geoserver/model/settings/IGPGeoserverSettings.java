package org.geosdi.geoplatform.connector.geoserver.model.settings;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.settings.contact.IGPGeoserverContactSettings;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverSettings.class)
public interface IGPGeoserverSettings extends Serializable {

    /**
     * @return {@link String}
     */
    String getId();

    /**
     * @return {@link IGPGeoserverContactSettings}
     */
    IGPGeoserverContactSettings getContact();

    /**
     * @return {@link String}
     */
    String getCharset();

    /**
     * @return {@link Integer}
     */
    Integer getNumDecimals();

    /**
     * @return {@link String}
     */
    String getOnlineResource();

    /**
     * @return {@link Boolean}
     */
    boolean isVerbose();

    /**
     * @return {@link Boolean}
     */
    boolean isVerboseExceptions();

    /**
     * @return {@link Boolean}
     */
    boolean isLocalWorkspaceIncludesPrefix();
}