package org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.finder;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterValue;
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
public final class BaseParameterValueFinder extends GPBaseImplementorFinder<BaseParameterValue<? extends Object>> {

    private static final Logger logger = LoggerFactory.getLogger(BaseParameterValueFinder.class);
    //
    private volatile ServiceLoader<BaseParameterValue> loader = ServiceLoader.load(BaseParameterValue.class, currentThread().getContextClassLoader());

    /**
     * @return {@link Set<BaseParameterValue>}
     */
    @Override
    public Set<BaseParameterValue<? extends Object>> getAllImplementors() {
        return loadImplementors(null, TRUE);
    }

    /**
     * @return {@link Set<BaseParameterValue>}
     */
    @Override
    public Set<BaseParameterValue<? extends Object>> getValidImplementors() {
        return loadImplementors(BaseParameterValue.class, FALSE);
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
     * @return {@link Set<BaseParameterValue>}
     */
    @Override
    protected <V extends GPImplementor> Set<BaseParameterValue<? extends Object>> loadImplementors(Class<V> type, Boolean all) {
        Iterator<BaseParameterValue> baseParemeterValueImplementors = loader.iterator();

        return new GPImplementorArraySet<>(new Iterator<BaseParameterValue<? extends Object>>() {

            private BaseParameterValue next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return TRUE;
                }

                while (baseParemeterValueImplementors.hasNext()) {
                    BaseParameterValue implementor = baseParemeterValueImplementors.next();
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
            public BaseParameterValue next() {
                if (hasNext()) {
                    BaseParameterValue p = next;
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