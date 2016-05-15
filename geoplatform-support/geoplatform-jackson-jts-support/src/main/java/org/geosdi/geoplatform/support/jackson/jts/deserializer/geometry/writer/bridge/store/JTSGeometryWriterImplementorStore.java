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
