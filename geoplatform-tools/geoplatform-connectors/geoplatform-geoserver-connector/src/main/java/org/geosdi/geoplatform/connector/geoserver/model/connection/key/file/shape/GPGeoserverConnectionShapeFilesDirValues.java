package org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.shape;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.GPGeoserverConnectionFileValues;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.GPGeoserverConnectionKeyLevel.*;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverConnectionShapeFilesDirValues implements IGPGeoserverConnectionKey {

    URL(of("url", "URL to a .shp file", user, "URL", TRUE, null)),
    FSTYPE(of("fstype", "Enable using a setting of 'shape’.", advanced, "String", FALSE, "shape")),
    FILETYPE(of("filetype", "Discriminator for directory stores", program, "String", FALSE, "shapefile"));

    private final IGPGeoserverConnectionKey key;

    /**
     * @param theKey
     */
    GPGeoserverConnectionShapeFilesDirValues(@Nonnull(when = NEVER) IGPGeoserverConnectionKey theKey) {
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
        List<IGPGeoserverConnectionKey> shapeFileDirRequiredValues = stream(GPGeoserverConnectionShapeFilesDirValues.values())
                .filter(v -> v.isRequired())
                .collect(toList());
        shapeFileDirRequiredValues.addAll(GPGeoserverConnectionFileValues.requiredValues());
        return shapeFileDirRequiredValues;
    }

    /**
     * @return {@link List<IGPGeoserverConnectionKey>}
     */
    public static List<IGPGeoserverConnectionKey> defaultValues() {
        List<IGPGeoserverConnectionKey> shapeFileDirDefaultValues = stream(GPGeoserverConnectionShapeFilesDirValues.values())
                .filter(v -> v.getDefaultValue() != null)
                .collect(toList());
        shapeFileDirDefaultValues.addAll(GPGeoserverConnectionFileValues.defaultValues());
        return shapeFileDirDefaultValues;
    }
}