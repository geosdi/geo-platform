package org.geosdi.geoplatform.rs.support.delegate.search;

import org.geosdi.geoplatform.rs.support.request.GPPageableRequest;
import org.geosdi.geoplatform.rs.support.response.GPStore;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableSearchDelegate<V> {

    /**
     * @param request
     * @param <Store>
     * @return {@link Store}
     * @throws Exception
     */
    <Store extends GPStore<V>> Store search(@Nonnull(when = NEVER) GPPageableRequest request) throws Exception;
}