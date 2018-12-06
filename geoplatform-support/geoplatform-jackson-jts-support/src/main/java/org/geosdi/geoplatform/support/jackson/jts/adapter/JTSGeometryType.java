package org.geosdi.geoplatform.support.jackson.jts.adapter;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.Arrays;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum JTSGeometryType implements GPJTSGeometryType {

    POINT("Point"),
    LINESTRING("LineString"),
    LINEARRING("LinearRing"),
    POLYGON("Polygon"),
    GEOMETRYCOLLECTION("GeometryCollection"),
    MULTIPOINT("MultiPoint"),
    MULTILINESTRING("MultiLineString"),
    MULTIPOLYGON("MultiPolygon");

    private final String type;

    JTSGeometryType(String theType) {
        this.type = theType;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * @param theType
     * @return {@link JTSGeometryType}
     */
    public static JTSGeometryType forType(@Nonnull(when = When.NEVER) String theType) {
        checkArgument((theType != null) && !(theType.trim().isEmpty()),
                "The Parameter theType must not be null or an Empty String.");
        Optional<JTSGeometryType> optional = Arrays.stream(JTSGeometryType.values())
                .filter(v -> v.getType().equalsIgnoreCase(theType))
                .findFirst();
        return ((optional != null) && !(optional.equals(Optional.empty())) ? optional.get() : null);
    }
}