package org.geosdi.geoplatform.connector.geoserver.model.connection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import javax.xml.bind.annotation.XmlAccessorType;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(FIELD)
public class GPGeoserverConnectionParam implements IGPGeoserverConnectionParam {

    private static final long serialVersionUID = 7701839272292492501L;
    //
    private final String key;
    private final String value;

    /**
     * @param theKey
     * @param theValue
     */
    @JsonCreator
    public GPGeoserverConnectionParam(@Nonnull(when = When.NEVER) @JsonProperty(value = "@key") String theKey,
            @Nonnull(when = When.NEVER) @JsonProperty(value = "$") String theValue) {
        checkArgument((theKey != null) && !(theKey.trim().isEmpty()), "The Parameter key must not be null or an empty String.");
        checkArgument((theValue != null) && !(theValue.trim().isEmpty()), "The Parameter value must not be null or an empty String.");
        this.key = theKey;
        this.value = theValue;
    }
}