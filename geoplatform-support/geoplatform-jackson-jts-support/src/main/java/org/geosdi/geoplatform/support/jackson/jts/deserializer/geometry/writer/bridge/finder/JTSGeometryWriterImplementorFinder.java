package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.finder;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.support.jackson.jts.bridge.ImplementorArraySet;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public final class JTSGeometryWriterImplementorFinder {

    private static final ServiceLoader<JTSGeometryWriterImplementor> loader = ServiceLoader.<JTSGeometryWriterImplementor>load(JTSGeometryWriterImplementor.class);

    private JTSGeometryWriterImplementorFinder() {
    }

    private static final synchronized <I extends JTSGeometryWriterImplementor> Set<I> getJTSGeometryWriterImplementors(
            Class<I> type, Boolean all) {

        final Iterator<JTSGeometryWriterImplementor> jtsGeometryWriterImplementors = loader.iterator();

        return new ImplementorArraySet<>(new Iterator<I>() {

            private I next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                }
                synchronized (JTSGeometryWriterImplementorFinder.class) {
                    while (jtsGeometryWriterImplementors.hasNext()) {
                        JTSGeometryWriterImplementor jtsGeometryWriterImplementor = jtsGeometryWriterImplementors.next();
                        if ((type == null) || (type.isInstance(jtsGeometryWriterImplementor))) {
                            if (all || jtsGeometryWriterImplementor.isImplementorValid()) {
                                next = (I) jtsGeometryWriterImplementor;
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

    public static <I extends JTSGeometryWriterImplementor> Set<I> getAllJTSGeometryWriterImplementors() {
        return getJTSGeometryWriterImplementors(null, Boolean.TRUE);
    }

    public static Set<JTSGeometryWriterImplementor> getValidJTSGeometryWriterImplementors() {
        return getJTSGeometryWriterImplementors(JTSGeometryWriterImplementor.class, Boolean.FALSE);
    }

    public static synchronized void reload() {
        loader.reload();
    }
}
