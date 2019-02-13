package org.geosdi.geoplatform.connector.bridge.finder;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.support.bridge.GPImplementorArraySet;
import org.geosdi.geoplatform.support.bridge.finder.GPBaseImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Thread.currentThread;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public final class GPWMSGetFeatureInfoReaderFinder<Reader extends GPWMSGetFeatureInfoReader<?>> extends GPBaseImplementorFinder<Reader> {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoReaderFinder.class);
    //
    private volatile ServiceLoader<GPWMSGetFeatureInfoReader> loader = ServiceLoader.load(GPWMSGetFeatureInfoReader.class,
            currentThread().getContextClassLoader());

    /**
     * @return {@link Set<GPWMSGetFeatureInfoReader>}
     */
    @Override
    public Set<Reader> getAllImplementors() {
        return loadImplementors(null, TRUE);
    }

    /**
     * @return {@link Set<GPWMSGetFeatureInfoReader>}
     */
    @Override
    public Set<Reader> getValidImplementors() {
        return loadImplementors(GPWMSGetFeatureInfoReader.class, FALSE);
    }

    @Override
    public void reload() {
        this.loader.reload();
        logger.trace("#################################CALLED RELOAD on : {}\n", loader.toString());
    }

    /**
     * @param type
     * @param all
     * @param <V>
     * @return {@link Set<Reader>}
     */
    @Override
    protected <V extends GPImplementor> Set<Reader> loadImplementors(Class<V> type, Boolean all) {
        Iterator<GPWMSGetFeatureInfoReader> readerImplemetors = loader.iterator();
        return new GPImplementorArraySet<>(new Iterator<Reader>() {

            private Reader next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return TRUE;
                }

                while (readerImplemetors.hasNext()) {
                    GPWMSGetFeatureInfoReader readerImplementor = readerImplemetors.next();
                    if ((type == null) || (type.isInstance(readerImplementor))) {
                        if (all || readerImplementor.isValid()) {
                            next = (Reader) readerImplementor;
                            return TRUE;
                        }
                    }
                }
                return FALSE;
            }

            @Override
            public Reader next() {
                if (hasNext()) {
                    Reader p = next;
                    next = null;
                    return p;
                }
                throw new NoSuchElementException("No more elements");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements from this Iterator");
            }

        });
    }
}