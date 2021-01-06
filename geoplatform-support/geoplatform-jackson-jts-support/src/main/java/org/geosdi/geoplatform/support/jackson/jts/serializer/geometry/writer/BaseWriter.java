/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.fasterxml.jackson.core.JsonGenerator;
import org.geojson.Crs;
import org.geojson.GeoJsonObject;
import org.geojson.jackson.CrsType;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class BaseWriter<JTS extends GPJTSGeometryAdapter, GEOJSON extends GeoJsonObject> implements GeometryWriter<JTS, GEOJSON> {

    protected static final Logger logger = LoggerFactory.getLogger(BaseWriter.class);
    //
    private GeometryWriterImplementorKey key;

    /**
     * @param geometry
     * @param jsonGenerator
     * @throws Exception
     */
    @Override
    public void writeGeometry(@Nonnull(when = NEVER) JTS geometry, @Nonnull(when = NEVER) JsonGenerator jsonGenerator) throws Exception {
        checkArgument(geometry != null, "The Parameter geometry must not be null.");
        checkArgument(jsonGenerator != null, "The Parameter jsonGenerator must not be null.");
        GEOJSON geoJsonGeometry = buildGeoJsonGeometry(geometry);
        geoJsonGeometry.setCrs(writeGeometryCrs(geometry));
        jsonGenerator.writeObject(geoJsonGeometry);
    }

    /**
     * @return {@link org.geosdi.geoplatform.support.bridge.implementor.GPImplementor.GPImplementorKey<Class<?>>}
     */
    @Override
    public final GeometryWriterImplementorKey getKey() {
        return this.key = ((this.key != null) ? this.key : prepareKey());
    }

    /**
     * @return {@link GeometryWriterImplementorKey}
     */
    protected abstract GeometryWriterImplementorKey prepareKey();

    /**
     * @param geometry
     * @return
     */
    protected Crs writeGeometryCrs(JTS geometry) {
        Crs crs = new Crs();
        crs.setType(CrsType.name);
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "EPSG:" + ((geometry.getSRID() != 0) ? geometry.getSRID() : 4326));
        crs.setProperties(properties);
        return crs;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}