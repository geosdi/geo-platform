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
package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.store;

import net.jcip.annotations.Immutable;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.finder.JTSGeometryWriterImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor.JTSGeometryWriterImplementorKey.forClass;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class JTSGeometryWriterImplementorStore implements GPJTSGeometryWriterImplementorStore {

    private static final long serialVersionUID = 2088420776681905640L;
    //
    private static final Logger logger = LoggerFactory.getLogger(JTSGeometryWriterImplementorStore.class);
    private static final GPImplementorFinder<JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>> finder = new JTSGeometryWriterImplementorFinder<>();
    private static final Map<Object, JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>> jtsGeometryWriterImplementors;

    static {
        jtsGeometryWriterImplementors = finder.getValidImplementors()
                .stream()
                .filter(Objects::nonNull)
                .collect(toMap((Function<JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>, Object>) GPImplementor::getKey, identity()));
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", JTSGeometryWriterImplementorStore.class.getSimpleName(),
                jtsGeometryWriterImplementors.size());
    }

    /**
     * @param key
     * @return {@link GeometryWriterImplementor} Implementor
     * @throws Exception
     */
    @Override
    public JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry> getImplementorByKey(GPImplementor.GPImplementorKey key) throws Exception {
        checkArgument(key != null, "The Key must not be null.");
        JTSGeometryWriterImplementor implementor = jtsGeometryWriterImplementors.get(key);
        checkArgument(implementor != null, "Implementor not found for Key : " + key);
        return implementor;
    }

    /**
     * @param key
     * @return {@link JTSGeometryWriterImplementor<?, ?>}
     * @throws Exception
     */
    @Override
    public JTSGeometryWriterImplementor getImplementorByKey(Class<?> key) throws Exception {
        checkArgument(key != null, "The Key must not be null.");
        return this.getImplementorByKey(forClass(key));
    }

    /**
     * @return {@link Set<GeometryWriterImplementor>}
     */
    @Override
    public Set<JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>> getAllImplementors() {
        return unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<GeometryWriterImplementor>}
     */
    @Override
    public Collection<JTSGeometryWriterImplementor<? extends GeoJsonObject, ? extends Geometry>> getValidImplementors() {
        return unmodifiableCollection(jtsGeometryWriterImplementors.values());
    }
}