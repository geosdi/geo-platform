package org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.geopkg;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.GPGeoserverConnectionDatabaseValues;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.program;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.user;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverConnectionGeoPackageValues implements IGPGeoserverConnectionKey {

    DATABASE(of("database", "Database", user, "File", TRUE, null)),
    DBTYPE(of("dbtype", "Type", program, "String", TRUE, "geopkg")),
    USER(of("user", "User name to login as", user, "String", FALSE, null));

    private final IGPGeoserverConnectionKey key;

    /**
     * @param theKey
     */
    GPGeoserverConnectionGeoPackageValues(@Nonnull(when = NEVER) IGPGeoserverConnectionKey theKey) {
        checkArgument(theKey != null, "The Parameter key must not be null.");
        this.key = theKey;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getConnectionKey() {
        return this.key.getConnectionKey();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getConnectionDescription() {
        return this.key.getConnectionDescription();
    }

    /**
     * @return {@link GPGeoserverConnectionKeyLevel}
     */
    @Override
    public GPGeoserverConnectionKeyLevel getLevel() {
        return this.key.getLevel();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getType() {
        return this.key.getType();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isRequired() {
        return this.key.isRequired();
    }

    /**
     * @return {@link Object}
     */
    @Override
    public Object getDefaultValue() {
        return this.key.getDefaultValue();
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> requiredValues() {
        List<IGPGeoserverConnectionKey> geoPackageRequiredValues = stream(GPGeoserverConnectionGeoPackageValues.values())
                .filter(IGPGeoserverConnectionKey::isRequired)
                .collect(toList());
        geoPackageRequiredValues.addAll(GPGeoserverConnectionDatabaseValues.requiredValues());
        return geoPackageRequiredValues;
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> defaultValues() {
        List<IGPGeoserverConnectionKey> geoPackageDefaultValues = stream(GPGeoserverConnectionGeoPackageValues.values())
                .filter(v -> v.getDefaultValue() != null)
                .collect(toList());
        geoPackageDefaultValues.addAll(GPGeoserverConnectionDatabaseValues.defaultValues());
        return geoPackageDefaultValues;
    }
}