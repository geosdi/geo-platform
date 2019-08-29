package org.geosdi.geoplatform.connector.geoserver.model.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverMasterPassword.class)
public interface IGPGeoserverMasterPassword extends Serializable {

    /**
     * @return {@link String}
     */
    String getOldMasterPassword();
}