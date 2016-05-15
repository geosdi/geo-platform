package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.store;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.finder.GeometryWriterImplementorFinder;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.finder.GeometryWriterImplementorFinder.getValidGeometryWriterImplementors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GeometryWriterImplementorStore implements ImplementorStore<GeometryWriterImplementor> {

    private static final long serialVersionUID = 1319958383128973272L;
    //
    private static final Logger logger = LoggerFactory.getLogger(GeometryWriterImplementorStore.class);
    private static final Map<Object, GeometryWriterImplementor> geometryWriterImplementors;

    static {
        geometryWriterImplementors = StreamSupport.stream(getValidGeometryWriterImplementors().spliterator(),
                Boolean.FALSE).collect(Collectors.toMap(GeometryWriterImplementor::getKey, value -> value));

        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", GeometryWriterImplementorStore.class.getSimpleName(),
                geometryWriterImplementors.size());
    }

    /**
     * @param key
     * @return {@link GeometryWriterImplementor} Implementor
     * @throws Exception
     */
    @Override
    public GeometryWriterImplementor getImplementorByKey(Object key) throws Exception {
        if (key == null) {
            throw new IllegalArgumentException("The Key must not be null.");
        }
        GeometryWriterImplementor implementor = geometryWriterImplementors.get(key);
        if (implementor == null) {
            throw new IllegalStateException("Implementor not found for Key : " + key);
        }
        return implementor;
    }

    /**
     * @return {@link Set<GeometryWriterImplementor>}
     */
    @Override
    public Set<GeometryWriterImplementor> getAllImplementors() {
        return Collections.unmodifiableSet(GeometryWriterImplementorFinder.getAllGeometryWriterImplementors());
    }

    /**
     * @return {@link Collection<GeometryWriterImplementor>}
     */
    @Override
    public Collection<GeometryWriterImplementor> getAllValidImplementors() {
        return Collections.unmodifiableCollection(geometryWriterImplementors.values());
    }
}
