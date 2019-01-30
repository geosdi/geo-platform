package org.geosdi.geoplatform.services.rs.path;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPWFSServiceRSPathConfig {

    public static final String DEFAULT_WFS_RS_SERVICE_PATH = "/";

    /**
     * WFS CAPABILITIES PATH
     */
    public static final String WFS_SERVICE_RS_PATH = "/jsonWFS";
    public static final String DESCRIBE_FEATURE_TYPE_RS_PATH = "/describeFeatureType";
    public static final String GET_GEOJSON_FEATURES_RS_PATH = "/getGeoJsonFeatures";

    private GPWFSServiceRSPathConfig() {
    }
}
