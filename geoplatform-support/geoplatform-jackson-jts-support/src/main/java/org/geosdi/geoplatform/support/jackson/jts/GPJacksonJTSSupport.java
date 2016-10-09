package org.geosdi.geoplatform.support.jackson.jts;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.Geometry;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.jts.module.GPJTSModule;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonJTSSupport extends GPJacksonSupport implements IGPJacksonJTSSupport {

    public GPJacksonJTSSupport() {
        this(UNWRAP_ROOT_VALUE_DISABLE,
                FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                WRAP_ROOT_VALUE_DISABLE,
                INDENT_OUTPUT_ENABLE, NON_NULL);
    }

    public GPJacksonJTSSupport(JacksonSupportConfigFeature... features) {
        super(features);
        super.registerModule(new GPJTSModule());
    }

    /**
     * @param theGeom
     * @return
     * @throws Exception
     */
    @Override
    public org.geojson.Geometry convertJtsGeometryToGeoJson(Geometry theGeom) throws Exception {
        Preconditions.checkArgument(theGeom != null, "The Parameter Geometry must not be null.");
        String geometryGeoJsonString = super.getDefaultMapper().writeValueAsString(theGeom);
        return super.getDefaultMapper().readValue(geometryGeoJsonString, org.geojson.Geometry.class);
    }

    /**
     * @param theGeoJsonGeometry
     * @return {@link com.vividsolutions.jts.geom.Geometry}
     * @throws Exception
     */
    @Override
    public Geometry convertGeoJsonGeometryToJts(org.geojson.Geometry theGeoJsonGeometry)
            throws Exception {
        Preconditions.checkArgument(theGeoJsonGeometry != null, "The Parameter GeoJson Geometry must not be null.");
        String geometryGeoJsonString = super.getDefaultMapper().writeValueAsString(theGeoJsonGeometry);
        return super.getDefaultMapper().readValue(geometryGeoJsonString, com.vividsolutions.jts.geom.Geometry.class);
    }

    /**
     * @return {@link String}
     */
    @Override
    public final String getProviderName() {
        return "GP_JACKSON_JTS_SUPPORT";
    }

    @Override
    public String toString() {
        return getProviderName();
    }
}
