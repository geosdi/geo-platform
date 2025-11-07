/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.jts;

import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.jts.module.GPJTSModule;
import org.geosdi.geoplatform.support.jackson.property.JacksonSupportConfigFeature;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;

import javax.annotation.Nonnull;
import java.io.StringReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static java.util.List.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAKARTA;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonJTSSupport extends GPJacksonSupport implements IGPJacksonJTSSupport {

    public GPJacksonJTSSupport() {
        this(asList(UNWRAP_ROOT_VALUE_DISABLE,
                FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                WRAP_ROOT_VALUE_DISABLE,
                INDENT_OUTPUT_ENABLE, NON_NULL,
                USE_FAST_DOUBLE_PARSER_ENABLE,
                USE_FAST_BIG_NUMBER_PARSER_ENABLE));
    }

    /**
     * @param features
     */
    protected GPJacksonJTSSupport(@Nonnull(when = NEVER) List<JacksonSupportConfigFeature> features) {
        super(JAKARTA, features, of(new GPJTSModule()));
    }

    /**
     * @param theGeom
     * @return
     * @throws Exception
     */
    @Override
    public GeoJsonObject convertJtsGeometryToGeoJson(@Nonnull(when = NEVER) Geometry theGeom) throws Exception {
        checkArgument(theGeom != null, "The Parameter Geometry must not be null.");
        String geometryGeoJsonString = super.getDefaultMapper().writeValueAsString(theGeom);
        return super.getDefaultMapper().readValue(geometryGeoJsonString, GeoJsonObject.class);
    }

    /**
     * @param theGeoJsonGeometry
     * @return {@link org.locationtech.jts.geom.Geometry}
     * @throws Exception
     */
    @Override
    public Geometry convertGeoJsonGeometryToJts(@Nonnull(when = NEVER) org.geojson.Geometry theGeoJsonGeometry) throws Exception {
        checkArgument(theGeoJsonGeometry != null, "The Parameter GeoJson Geometry must not be null.");
        String geometryGeoJsonString = super.getDefaultMapper().writeValueAsString(theGeoJsonGeometry);
        return super.getDefaultMapper().readValue(geometryGeoJsonString, org.locationtech.jts.geom.Geometry.class);
    }

    /**
     * @param theGeom
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    @Override
    public GeoJsonObject convertVividisolutionGeometryToGeoJson(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.Geometry theGeom) throws Exception {
        checkArgument(theGeom != null, "The Parameter Geometry must not be null.");
        String geometryGeoJsonString = super.getDefaultMapper().writeValueAsString(theGeom);
        return super.getDefaultMapper().readValue(geometryGeoJsonString, GeoJsonObject.class);
    }

    /**
     * @param theGeoJsonGeometry
     * @return {@link Geometry}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.Geometry convertGeoJsonGeometryToVividisolution(@Nonnull(when = NEVER) org.geojson.Geometry theGeoJsonGeometry) throws Exception {
        checkArgument(theGeoJsonGeometry != null, "The Parameter GeoJson Geometry must not be null.");
        String geometryGeoJsonString = super.getDefaultMapper().writeValueAsString(theGeoJsonGeometry);
        return super.getDefaultMapper().readValue(geometryGeoJsonString, com.vividsolutions.jts.geom.Geometry.class);
    }

    /**
     * @param theFeatureCollection
     * @return {@link Geometry}
     * @throws Exception
     */
    @Override
    public GeometryCollection convertGeoJsonFeatureCollectionToJts(@Nonnull(when = NEVER) FeatureCollection theFeatureCollection) throws Exception {
        checkArgument((theFeatureCollection != null) && (!theFeatureCollection.getFeatures().isEmpty()), "The Parameter featureCollection must not be null and must contains features.");
        String geometryAsString = super.getDefaultMapper().writeValueAsString(theFeatureCollection);
        return super.getDefaultMapper().readValue(new StringReader(geometryAsString), GeometryCollection.class);
    }

    /**
     * @param theFeatureCollection
     * @return {@link Geometry}
     * @throws Exception
     */
    @Override
    public com.vividsolutions.jts.geom.GeometryCollection convertGeoJsonFeatureCollectionToVividisolution(@Nonnull(when = NEVER) FeatureCollection theFeatureCollection) throws Exception {
        checkArgument((theFeatureCollection != null) && (!theFeatureCollection.getFeatures().isEmpty()), "The Parameter featureCollection must not be null and must contains features.");
        String geometryAsString = super.getDefaultMapper().writeValueAsString(theFeatureCollection);
        return super.getDefaultMapper().readValue(new StringReader(geometryAsString), com.vividsolutions.jts.geom.GeometryCollection.class);
    }

    /**
     * @return {@link String}
     */
    @Override
    public final String getProviderName() {
        return "GP_JACKSON_JTS_SUPPORT";
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getProviderName();
    }
}