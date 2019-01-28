package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPCoverageStoreType implements IGPCoverageStoreType {

    ARCGRID("ArcGrid"),
    GEOPACKAGE("GeoPackage"),
    GEOTIFF("GeoTIFF"),
    IMAGEMOSAIC("ImageMosaic"),
    WORKDIMAGE("WorldImage");

    private final String type;

    /**
     * @param theType
     */
    GPCoverageStoreType(String theType) {
        this.type = theType;
    }

    /**
     * @return {@link String}
     */
    @JsonValue
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    /**
     * @param type
     * @return {@link GPCoverageStoreType}
     */
    @JsonCreator
    public static GPCoverageStoreType forType(String type) {
        Optional<GPCoverageStoreType> optional = stream(GPCoverageStoreType.values())
                .filter(v -> ((type != null) && !(type.trim().isEmpty()))
                        ? v.getType().equalsIgnoreCase(type) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}