/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geojson.GeoJsonObject;
import org.geojson.GeometryCollection;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.IGPJTSDeserializer.JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryCollectionWriter extends JTSBaseWriter<GeometryCollection, org.locationtech.jts.geom.GeometryCollection> {

    /**
     * @return {@link Class<GeometryCollection>}
     */
    @Override
    public Class<GeometryCollection> getKey() {
        return GeometryCollection.class;
    }

    /**
     * @param geometryCollection
     * @return {@link org.locationtech.jts.geom.GeometryCollection}
     * @throws Exception
     */
    @Override
    public org.locationtech.jts.geom.GeometryCollection buildJTSGeometry(GeometryCollection geometryCollection)
            throws Exception {
        logger.trace(":::::::::::::::{} is creating JTS GeometryCollection for GeoJson GeometryCollection : {}\n",
                this, geometryCollection);
        List<Geometry> geometries = new ArrayList<>(geometryCollection.getGeometries().size());
        for (GeoJsonObject geoJsonObject : geometryCollection.getGeometries()) {
            JTSGeometryWriterImplementor jtsGeometryWriterImplementor = JTS_GEOMETRY_WRITER_IMPLEMENTOR_STORE.getImplementorByKey(geoJsonObject.getClass());
            geometries.add(jtsGeometryWriterImplementor.buildJTSGeometry(geoJsonObject));
        }
        return GEOMETRY_FACTORY.createGeometryCollection(geometries.stream().toArray(size -> new Geometry[size]));
    }

    /**
     * <p>
     * Specify if {@link org.geosdi.geoplatform.support.jackson.jts.bridge.implementor.Implementor} is valid or not
     * </p>
     *
     * @return {@link Boolean}
     */
    @Override
    public Boolean isImplementorValid() {
        return TRUE;
    }
}
