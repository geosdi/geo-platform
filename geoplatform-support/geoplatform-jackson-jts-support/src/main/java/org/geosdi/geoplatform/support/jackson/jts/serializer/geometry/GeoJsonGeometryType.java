package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoJsonGeometryType {

    /**
     * @return {@link String}
     */
    default String getType() {
        return "type";
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    String getGeometryType();

    /**
     * @return {@link String}
     */
    default String getGeometryMember() {
        return "coordinates";
    }

    /**
     *
     */
    enum GeoJsonGeometryEnumType implements GeoJsonGeometryType {
        POINT("Point"),
        LINE_STRING("LineString"),
        POLYGON("Polygon"),
        MULTI_POINT("MultiPoint"),
        MULTI_LINE_STRING("MultiLineString"),
        MULTI_POLYGON("MultiPolygon"),
        GEOMETRY_COLLECTION("GeometryCollection") {
            /**
             * @return {@link String}
             */
            @Override
            public String getGeometryMember() {
                return "geometries";
            }
        };

        private final String value;

        GeoJsonGeometryEnumType(String theValue) {
            this.value = theValue;
        }
        
        /**
         * @return {@link GeoJsonGeometryType}
         */
        @Override
        public String getGeometryType() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
