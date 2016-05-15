package org.geosdi.geoplatform.support.jackson.jts.bridge.store;

import org.geosdi.geoplatform.support.jackson.jts.bridge.implementor.Implementor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ImplementorStore<I extends Implementor> extends Serializable {

    /**
     * @param key
     * @return {@link I} Implementor
     * @throws Exception
     */
    I getImplementorByKey(Object key) throws Exception;

    /**
     * @return {@link Set<I>}
     */
    Set<I> getAllImplementors();

    /**
     * @return {@link Collection<I>}
     */
    Collection<I> getAllValidImplementors();
}
