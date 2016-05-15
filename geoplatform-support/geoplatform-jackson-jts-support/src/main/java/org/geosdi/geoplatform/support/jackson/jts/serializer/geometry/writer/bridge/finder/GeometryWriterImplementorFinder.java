package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.finder;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.support.jackson.jts.bridge.ImplementorArraySet;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public final class GeometryWriterImplementorFinder {

    private static final ServiceLoader<GeometryWriterImplementor> loader = ServiceLoader.<GeometryWriterImplementor>load(GeometryWriterImplementor.class);

    private GeometryWriterImplementorFinder() {
    }

    private static final synchronized <I extends GeometryWriterImplementor> Set<I> getGeometryWriterImplementors(
            Class<I> type, Boolean all) {

        final Iterator<GeometryWriterImplementor> geometryWriterImplementors = loader.iterator();

        return new ImplementorArraySet<>(new Iterator<I>() {

            private I next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                }
                synchronized (GeometryWriterImplementorFinder.class) {
                    while (geometryWriterImplementors.hasNext()) {
                        GeometryWriterImplementor geometryWriterImplementor = geometryWriterImplementors.next();
                        if ((type == null) || (type.isInstance(geometryWriterImplementor))) {
                            if (all || geometryWriterImplementor.isImplementorValid()) {
                                next = (I) geometryWriterImplementor;
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public I next() {
                if (hasNext()) {
                    final I s = next;
                    next = null;
                    return s;
                }
                throw new NoSuchElementException("No more elements");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "Cannot remove elements "
                                + "from this Iterator");
            }

        });
    }

    public static <I extends GeometryWriterImplementor> Set<I> getAllGeometryWriterImplementors() {
        return getGeometryWriterImplementors(null, Boolean.TRUE);
    }

    public static Set<GeometryWriterImplementor> getValidGeometryWriterImplementors() {
        return getGeometryWriterImplementors(GeometryWriterImplementor.class, Boolean.FALSE);
    }

    public static synchronized void reload() {
        loader.reload();
    }
}
