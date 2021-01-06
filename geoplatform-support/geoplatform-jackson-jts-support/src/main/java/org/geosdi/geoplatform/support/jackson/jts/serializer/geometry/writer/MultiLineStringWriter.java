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

import org.geosdi.geoplatform.support.jackson.jts.adapter.JTSMultiLinestringAdapter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.GeoJsonGeometryType.GeoJsonGeometryEnumType.MULTI_LINE_STRING;
import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor.GeometryWriterImplementorKey.forClass;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiLineStringWriter extends BaseWriter<JTSMultiLinestringAdapter, org.geojson.MultiLineString> {

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
     * @param geometryAdapter
     * @return {@link org.geojson.MultiLineString}
     * @throws Exception
     */
    @Override
    public org.geojson.MultiLineString buildGeoJsonGeometry(@Nonnull(when = NEVER) JTSMultiLinestringAdapter geometryAdapter) throws Exception {
        checkArgument(geometryAdapter != null, "The Parameter geometryAdapter must not be null.");
        logger.trace(":::::::::::::::::::Called {}#buildGeoJsonGeometry for JTS_GEOMETRY : {}\n", super.toString(), geometryAdapter);
        org.geojson.MultiLineString multiLineString = new org.geojson.MultiLineString();
        multiLineString.setCoordinates(COORDINATE_WRITER.buildMultiLineStringCoordinate(geometryAdapter));
        return multiLineString;
    }

    /**
     * @return {@link GeoJsonGeometryType}
     */
    @Override
    public GeoJsonGeometryType getGeometryType() {
        return MULTI_LINE_STRING;
    }

    /**
     * @return {@link GeometryWriterImplementorKey}
     */
    @Override
    protected GeometryWriterImplementorKey prepareKey() {
        return forClass(JTSMultiLinestringAdapter.class);
    }
}