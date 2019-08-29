package org.geosdi.geoplatform.connector.geoserver.model.settings.contact;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverContactSettings.class)
public interface IGPGeoserverContactSettings extends Serializable {

    /**
     * @return {@link String}
     */
    String getAddressCity();

    /**
     * @return {@link String}
     */
    String getAddressCountry();

    /**
     * @return {@link String}
     */
    String getAddressType();

    /**
     * @return {@link String}
     */
    String getContactEmail();

    /**
     * @return {@link String}
     */
    String getContactOrganization();

    /**
     * @return {@link String}
     */
    String getContactPerson();

    /**
     * @return {@link String}
     */
    String getContactPosition();
}