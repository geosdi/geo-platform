package org.geosdi.geoplatform.support.bridge.finder;

import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPImplementorFinder<I extends GPImplementor<?>> {

    /**
     * @return {@link Set<I>}
     */
    Set<I> getAllImplementors();

    /**
     * @return {@link Set<I>}
     */
    Set<I> getValidImplementors();

    void reload();
}
