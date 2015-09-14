package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public final class QueryRestrictionsStrategyFinder {

    private static final ServiceLoader<QueryRestrictionStrategy> loader = ServiceLoader.<QueryRestrictionStrategy>load(
            QueryRestrictionStrategy.class);

    private QueryRestrictionsStrategyFinder() {
    }

    private static synchronized <S extends QueryRestrictionStrategy> Set<S> getStrategies(final Class<S> type,
            final boolean all) {
        final Iterator<QueryRestrictionStrategy> strategies = loader.iterator();

        return new QueryRestrictionsSet<>(new Iterator<S>() {

            private S next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                }
                synchronized (QueryRestrictionsStrategyFinder.class) {
                    while (strategies.hasNext()) {
                        final QueryRestrictionStrategy strategy = strategies.next();
                        if (type.isInstance(strategy)) {
                            if (all || strategy.isValidStrategy()) {
                                next = (S) strategy;
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public S next() {
                if (hasNext()) {
                    final S s = next;
                    next = null;
                    return s;
                }
                throw new NoSuchElementException("No more elements");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements " + "from this Iterator");
            }
        });
    }

    public static Set<QueryRestrictionStrategy> getAllStrategies() {
        return getStrategies(QueryRestrictionStrategy.class, true);
    }

    public static Set<QueryRestrictionStrategy> getValidStrategies() {
        return getStrategies(QueryRestrictionStrategy.class, false);
    }

    public static synchronized void reload() {
        loader.reload();
    }
}
