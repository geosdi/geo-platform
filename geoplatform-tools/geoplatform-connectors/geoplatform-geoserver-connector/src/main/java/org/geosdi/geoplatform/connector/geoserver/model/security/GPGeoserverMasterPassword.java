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
@ToString
@XmlAccessorType(FIELD)
public class GPGeoserverMasterPassword implements IGPGeoserverMasterPassword {

    private static final long serialVersionUID = 2149528252950883437L;
    //
    private final String oldMasterPassword;

    /**
     * @param theOldMasterPassword
     */
    @JsonCreator
    public GPGeoserverMasterPassword(@Nonnull(when = NEVER) @JsonProperty(value = "oldMasterPassword") String theOldMasterPassword) {
        checkArgument((theOldMasterPassword != null) && !(theOldMasterPassword.trim().isEmpty()), "The Parameter oldMasterPassword must not be null or an empty value.");
        this.oldMasterPassword = theOldMasterPassword;
    }
}