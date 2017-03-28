package org.geosdi.geoplatform.support.bridge.finder;

import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseImplementorFinder<I extends GPImplementor<?>> implements GPImplementorFinder<I> {

    /**
     * @param type
     * @param all
     * @param <V>
     * @return {@link Set<I>}
     */
    protected abstract <V extends GPImplementor> Set<I> loadImplementors(Class<V> type, Boolean all);
}
