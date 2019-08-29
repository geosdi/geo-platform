package org.geosdi.geoplatform.connector.geoserver.model.settings.contact;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "contact")
public class GPGeoserverContactSettings implements IGPGeoserverContactSettings {

    private static final long serialVersionUID = -2811734176391291035L;
    //
    private final String addressCity;
    private final String addressCountry;
    private final String addressType;
    private final String contactEmail;
    private final String contactOrganization;
    private final String contactPerson;
    private final String contactPosition;

    /**
     * @param theAddressCity
     * @param theAddressCountry
     * @param theAddressType
     * @param theContactEmail
     * @param theContactOrganization
     * @param theContactPerson
     * @param theContactPosition
     */
    @JsonCreator
    public GPGeoserverContactSettings(@JsonProperty(value = "addressCity") String theAddressCity, @JsonProperty(value = "addressCountry") String theAddressCountry,
            @JsonProperty(value = "addressType") String theAddressType, @JsonProperty(value = "contactEmail") String theContactEmail,
            @JsonProperty(value = "contactOrganization") String theContactOrganization, @JsonProperty(value = "contactPerson") String theContactPerson,
            @JsonProperty(value = "contactPosition") String theContactPosition) {
        this.addressCity = theAddressCity;
        this.addressCountry = theAddressCountry;
        this.addressType = theAddressType;
        this.contactEmail = theContactEmail;
        this.contactOrganization = theContactOrganization;
        this.contactPerson = theContactPerson;
        this.contactPosition = theContactPosition;
    }
}