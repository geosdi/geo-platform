package org.geosdi.geoplatform.connector.geoserver.model.connection.key;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverConnectionKey extends Serializable {

    /**
     * @return {@link String}
     */
    String getConnectionKey();

    /**
     * @return {@link String}
     */
    String getConnectionDescription();

    /**
     * @return {@link GPGeoserverConnectionKeyLevel}
     */
    GPGeoserverConnectionKeyLevel getLevel();

    /**
     * @return {@link String}
     */
    String getType();

    /**
     * @return {@link Boolean}
     */
    boolean isRequired();

    /**
     * @return {@link Object}
     */
    Object getDefaultValue();

    /**
     * @param theConnectionKey
     * @param connectionDescription
     * @param theLevel
     * @param theType
     * @param theRequired
     * @param theDefaultValue
     * @return {@link IGPGeoserverConnectionKey}
     */
    static IGPGeoserverConnectionKey of(@Nonnull(when = NEVER) String theConnectionKey, @Nullable String connectionDescription,
            @Nonnull(when = NEVER) GPGeoserverConnectionKeyLevel theLevel, @Nonnull(when = NEVER) String theType,
            boolean theRequired, @Nullable Object theDefaultValue) {
        return new GPGeoserverConnectionKey(theConnectionKey, connectionDescription, theLevel, theType, theRequired, theDefaultValue);
    }
}