package org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.finder;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterValue;
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
public final class JTSParameterValueFinder extends GPBaseImplementorFinder<JTSParameterValue<? extends Object>> {

    private static final Logger logger = LoggerFactory.getLogger(JTSParameterValueFinder.class);
    //
    private volatile ServiceLoader<JTSParameterValue> loader = ServiceLoader.load(JTSParameterValue.class, currentThread().getContextClassLoader());

    /**
     * @return {@link Set<JTSParameterValue>}
     */
    @Override
    public Set<JTSParameterValue<? extends Object>> getAllImplementors() {
        return loadImplementors(null, TRUE);
    }

    /**
     * @return {@link Set<JTSParameterValue>}
     */
    @Override
    public Set<JTSParameterValue<? extends Object>> getValidImplementors() {
        return loadImplementors(JTSParameterValue.class, FALSE);
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
     * @return {@link Set<JTSParameterValue>}
     */
    @Override
    protected <V extends GPImplementor> Set<JTSParameterValue<? extends Object>> loadImplementors(Class<V> type, Boolean all) {
        Iterator<JTSParameterValue> jtsParemeterValueImplementors = loader.iterator();

        return new GPImplementorArraySet<>(new Iterator<JTSParameterValue<? extends Object>>() {

            private JTSParameterValue next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return TRUE;
                }

                while (jtsParemeterValueImplementors.hasNext()) {
                    JTSParameterValue implementor = jtsParemeterValueImplementors.next();
                    if ((type == null) || (type.isInstance(implementor))) {
                        if (all || implementor.isValid()) {
                            next = implementor;
                            return TRUE;
                        }
                    }
                }
                return FALSE;
            }

            @Override
            public JTSParameterValue next() {
                if (hasNext()) {
                    JTSParameterValue p = next;
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