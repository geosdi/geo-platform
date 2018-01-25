/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.finder.JTSGeometryWriterImplementorFinder.getAllJTSGeometryWriterImplementors;
import static org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.finder.JTSGeometryWriterImplementorFinder.getValidJTSGeometryWriterImplementors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class JTSGeometryWriterImplementorStore implements ImplementorStore<JTSGeometryWriterImplementor> {

    private static final long serialVersionUID = 2088420776681905640L;
    //
    private static final Logger logger = LoggerFactory.getLogger(JTSGeometryWriterImplementorStore.class);
    private static final Map<Object, JTSGeometryWriterImplementor> jtsGeometryWriterImplementors;

    static {
        jtsGeometryWriterImplementors = StreamSupport.stream(getValidJTSGeometryWriterImplementors().spliterator(),
                Boolean.FALSE).collect(Collectors.toMap(JTSGeometryWriterImplementor::getKey, value -> value));

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", JTSGeometryWriterImplementorStore.class.getSimpleName(),
                jtsGeometryWriterImplementors.size());
    }

    /**
     * @param key
     * @return {@link GeometryWriterImplementor} Implementor
     * @throws Exception
     */
    @Override
    public JTSGeometryWriterImplementor getImplementorByKey(Object key) throws Exception {
        if (key == null) {
            throw new IllegalArgumentException("The Key must not be null.");
        }
        JTSGeometryWriterImplementor implementor = jtsGeometryWriterImplementors.get(key);
        if (implementor == null) {
            throw new IllegalStateException("Implementor not found for Key : " + key);
        }
        return implementor;
    }

    /**
     * @return {@link Set<GeometryWriterImplementor>}
     */
    @Override
    public Set<JTSGeometryWriterImplementor> getAllImplementors() {
        return Collections.unmodifiableSet(getAllJTSGeometryWriterImplementors());
    }

    /**
     * @return {@link Collection<GeometryWriterImplementor>}
     */
    @Override
    public Collection<JTSGeometryWriterImplementor> getAllValidImplementors() {
        return Collections.unmodifiableCollection(jtsGeometryWriterImplementors.values());
    }
}
