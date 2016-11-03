package org.geosdi.geoplatform.support.primitive.bridge.store;

import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface PrimitiveImplementorStore<I extends GPImplementor<?>> extends GPImplementorStore<I> {

    /**
     * @param classe
     * @return {@link Boolean}
     */
    Boolean isPrimitiveOrWrapper(Class<?> classe) throws Exception;

    /**
     * @param classe
     * @return {@link I}
     */
    I getPrimitiveImplementorForClass(Class<?> classe) throws Exception;
}
