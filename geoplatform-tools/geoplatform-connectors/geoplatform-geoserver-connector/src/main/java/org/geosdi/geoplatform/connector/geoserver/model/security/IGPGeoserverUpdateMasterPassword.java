package org.geosdi.geoplatform.connector.geoserver.model.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverUpdateMasterPassword.class)
public interface IGPGeoserverUpdateMasterPassword extends IGPGeoserverMasterPassword {

    /**
     * @return {@link String}
     */
    String getNewMasterPassword();
}