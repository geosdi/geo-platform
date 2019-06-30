package org.geosdi.geoplatform.connector.geoserver.model.connection.key;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Getter
@ToString
public class GPGeoserverConnectionKey implements IGPGeoserverConnectionKey {

    private final String connectionKey;
    private final String connectionDescription;
    private final GPGeoserverConnectionKeyLevel level;
    private final String type;
    private final boolean required;
    private final Object defaultValue;

    /**
     * @param theConnectionKey
     * @param connectionDescription
     * @param theLevel
     * @param theType
     * @param theRequired
     * @param theDefaultValue
     */
    GPGeoserverConnectionKey(@Nonnull(when = NEVER) String theConnectionKey, @Nullable String connectionDescription,
            @Nonnull(when = NEVER) GPGeoserverConnectionKeyLevel theLevel, @Nonnull(when = NEVER) String theType,
            boolean theRequired, @Nullable Object theDefaultValue) {
        checkArgument((theConnectionKey != null) && !(theConnectionKey.trim().isEmpty()), "The Parameter connectionKey must not be null or an empty string.");
        checkNotNull(theLevel, "The Parameter level must not be null");
        checkArgument((theType != null) && !(theType.trim().isEmpty()), "The Parameter type must not be null or an empty string.");
        this.connectionKey = theConnectionKey;
        this.connectionDescription = connectionDescription;
        this.level = theLevel;
        this.type = theType;
        this.required = theRequired;
        this.defaultValue = theDefaultValue;
    }
}