package org.geosdi.geoplatform.connector.geoserver.model.connection.key.file;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.GPGeoserverConnectionDatabaseValues;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.nio.charset.Charset.forName;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.advanced;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverConnectionFileValues implements IGPGeoserverConnectionKey {

    CACHE_ADN_REUSE_MEMORY_MAPS(of("cache and reuse memory maps", "Only memory map a file one, then cache and reuse the map", advanced, "Boolean", FALSE, TRUE)),
    NAMESPACE(of("namespace", "URI to a the namespace", advanced, "URI", FALSE, null)),
    CHARSET(of("charset", "Character used to decode strings from the DBF file", advanced, "Charset", FALSE, forName("ISO-8859-1"))),
    CREATE_SPATIAL_INDEX(of("create spatial index", "enable/disable the automatic creation of spatial index", advanced, "Boolean", FALSE, TRUE)),
    FSTYPE(of("fstype", "Enable using a setting of 'shape’.", advanced, "String", FALSE, "shape")),
    ENABLE_SPATIAL_INDEX(of("enable spatial index", "enable/disable the use of spatial index for local shapefiles", advanced, "Boolean", FALSE, TRUE)),
    MEMORY_MAPPED_BUFFER(of("memory mapped buffer", "enable/disable the use of memory-mapped io", advanced, "Boolean", FALSE, FALSE));

    private final IGPGeoserverConnectionKey key;

    /**
     * @param theKey
     */
    GPGeoserverConnectionFileValues(@Nonnull(when = NEVER) IGPGeoserverConnectionKey theKey) {
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
        return stream(GPGeoserverConnectionDatabaseValues.values())
                .filter(IGPGeoserverConnectionKey::isRequired)
                .collect(toList());
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> defaultValues() {
        return stream(GPGeoserverConnectionDatabaseValues.values())
                .filter(v -> v.getDefaultValue() != null)
                .collect(toList());
    }
}