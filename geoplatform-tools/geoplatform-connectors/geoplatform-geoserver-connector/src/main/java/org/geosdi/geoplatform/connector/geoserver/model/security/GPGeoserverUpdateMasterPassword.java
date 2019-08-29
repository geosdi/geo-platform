package org.geosdi.geoplatform.connector.geoserver.model.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessorType;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
@XmlAccessorType(FIELD)
public class GPGeoserverUpdateMasterPassword extends GPGeoserverMasterPassword implements IGPGeoserverUpdateMasterPassword {

    private static final long serialVersionUID = -7136725038820303244L;
    //
    private final String newMasterPassword;

    /**
     * @param theOldMasterPassword
     * @param theNewMasterPassword
     */
    @JsonCreator
    public GPGeoserverUpdateMasterPassword(@Nonnull(when = NEVER) @JsonProperty(value = "oldMasterPassword") String theOldMasterPassword,
            @Nonnull(when = NEVER) @JsonProperty(value = "newMasterPassword") String theNewMasterPassword) {
        super(theOldMasterPassword);
        checkArgument((theNewMasterPassword != null) && !(theNewMasterPassword.trim().isEmpty()), "The Parameter newMasterPassword must not be null or an empty string.");
        this.newMasterPassword = theNewMasterPassword;
    }
}