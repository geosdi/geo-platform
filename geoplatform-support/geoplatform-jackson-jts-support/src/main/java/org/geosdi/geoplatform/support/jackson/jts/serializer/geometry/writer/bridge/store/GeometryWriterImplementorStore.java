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
package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.store;

import net.jcip.annotations.Immutable;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.jackson.jts.adapter.GPJTSGeometryAdapter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.finder.GeometryWriterImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.StreamSupport.stream;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor.GeometryWriterImplementorKey.forClass;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GeometryWriterImplementorStore implements GPGeometryWriterImplementorStore {

    private static final long serialVersionUID = 1319958383128973272L;
    //
    private static final Logger logger = LoggerFactory.getLogger(GeometryWriterImplementorStore.class);
    private static final GPImplementorFinder<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>> finder = new GeometryWriterImplementorFinder<>();
    private static final Map<Object, GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>> geometryWriterImplementors;

    static {
        geometryWriterImplementors = stream(finder.getValidImplementors().spliterator(), FALSE)
                .filter(Objects::nonNull)
                .collect(toMap((Function<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>, Object>) GPImplementor::getKey, identity()));
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", GeometryWriterImplementorStore.class.getSimpleName(), geometryWriterImplementors.size());
    }

    /**
     * @param key
     * @return {@link GeometryWriterImplementor} Implementor
     * @throws Exception
     */
    @Override
    public GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject> getImplementorByKey(@Nonnull(when = NEVER) GPImplementor.GPImplementorKey key) throws Exception {
        checkArgument(key != null, "The Key must not be null.");
        GeometryWriterImplementor implementor = geometryWriterImplementors.get(key);
        checkArgument(implementor != null, "Implementor not found for Key : " + key);
        return implementor;
    }

    /**
     * @param key
     * @return {@link GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>}
     * @throws Exception
     */
    @Override
    public GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject> getImplementorByKey(@Nonnull(when = NEVER) Class<?> key) throws Exception {
        checkArgument(key != null, "The Key must not be null.");
        return this.getImplementorByKey(forClass(key));
    }

    /**
     * @return {@link Set<GeometryWriterImplementor>}
     */
    @Override
    public Set<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>> getAllImplementors() {
        return unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>}
     */
    @Override
    public Collection<GeometryWriterImplementor<? extends GPJTSGeometryAdapter, ? extends GeoJsonObject>> getValidImplementors() {
        return unmodifiableCollection(geometryWriterImplementors.values());
    }
}