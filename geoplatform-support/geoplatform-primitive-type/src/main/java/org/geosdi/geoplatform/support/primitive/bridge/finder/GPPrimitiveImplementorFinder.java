package org.geosdi.geoplatform.support.primitive.bridge.finder;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.support.bridge.GPImplementorArraySet;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPPrimitiveImplementorFinder<I extends PrimitiveImplementor<?>> implements GPImplementorFinder<I> {

    private volatile ServiceLoader<PrimitiveImplementor> loader = ServiceLoader.<PrimitiveImplementor>load(PrimitiveImplementor.class);
    private ReentrantLock lock = new ReentrantLock(Boolean.TRUE);

    /**
     * @return {@link Set<PrimitiveImplementor>}
     */
    @Override
    public Set<I> getAllImplementors() {
        this.lock.lock();
        try {
            return loadPrimitiveImplementors(null, Boolean.TRUE);
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * @return {@link Set< org.geosdi.geoplatform.support.primitive.bridge.implementor.GPPrimitiveImplementor >}
     */
    @Override
    public Set<I> getValidImplementors() {
        this.lock.lock();
        try {
            return loadPrimitiveImplementors(PrimitiveImplementor.class, Boolean.FALSE);
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public void reload() {
        this.loader.reload();
    }

    /**
     * @param type
     * @param all
     * @return {@link Set<I>}
     */
    private Set<I> loadPrimitiveImplementors(Class<PrimitiveImplementor> type, Boolean all) {
        Iterator<PrimitiveImplementor> primitiveImplementors = loader.iterator();

        return new GPImplementorArraySet<I>(new Iterator<I>() {

            private I next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                }
                lock.lock();
                try {
                    while (primitiveImplementors.hasNext()) {
                        PrimitiveImplementor primitiveImplementor = primitiveImplementors.next();
                        if ((type == null) || (type.isInstance(primitiveImplementor))) {
                            if (all || primitiveImplementor.isValid()) {
                                next = (I) primitiveImplementor;
                                return true;
                            }
                        }
                    }
                } finally {
                    lock.unlock();
                }
                return false;
            }

            @Override
            public I next() {
                if (hasNext()) {
                    I p = next;
                    next = null;
                    return p;
                }
                throw new NoSuchElementException("No more elements");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements "
                        + "from this Iterator");
            }

        });
    }
}
