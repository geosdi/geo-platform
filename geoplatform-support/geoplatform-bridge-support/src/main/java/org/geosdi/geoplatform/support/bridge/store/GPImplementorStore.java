package org.geosdi.geoplatform.support.bridge.store;

import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPImplementorStore<I extends GPImplementor<?>> extends Serializable {

    /**
     * @param key
     * @return {@link I}
     * @throws Exception
     */
    I getImplementorByKey(GPImplementor.GPImplementorKey key) throws Exception;

    /**
     * @return {@link Set<I>}
     */
    Set<I> getAllImplementors();

    /**
     * @return {@link Collection<I>}
     */
    Collection<I> getValidImplementors();
}
