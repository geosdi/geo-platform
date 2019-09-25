/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import org.geojson.GeometryCollection;
import org.locationtech.jts.geom.Geometry;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.JTSFeatureCollectionWriter.GEOJSON_TO_JTS_GEOMETRY_FUNCTION;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor.JTSGeometryWriterImplementorKey.forClass;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryCollectionWriter extends JTSBaseWriter<GeometryCollection, org.locationtech.jts.geom.GeometryCollection> {

    /**
     * @param geometryCollection
     * @return {@link org.locationtech.jts.geom.GeometryCollection}
     * @throws Exception
     */
    @Override
    public org.locationtech.jts.geom.GeometryCollection buildJTSGeometry(@Nonnull(when = NEVER) GeometryCollection geometryCollection) throws Exception {
        checkArgument(geometryCollection != null, "The Parameter geometryCollection must not be null.");
        logger.trace(":::::::::::::::{} is creating JTS GeometryCollection for GeoJson GeometryCollection : {}\n", this, geometryCollection);
        return GEOMETRY_FACTORY.createGeometryCollection(geometryCollection.getGeometries().stream()
                .filter(Objects::nonNull)
                .map(GEOJSON_TO_JTS_GEOMETRY_FUNCTION)
                .toArray(Geometry[]::new));
    }

    /**
     * <p>
     * Specify if {@link org.geosdi.geoplatform.support.bridge.implementor.GPImplementor} is valid or not
     * </p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValid() {
        return TRUE;
    }

    /**
     * @return {@link JTSGeometryWriterImplementorKey}
     */
    @Override
    protected JTSGeometryWriterImplementorKey prepareKey() {
        return forClass(GeometryCollection.class);
    }
}