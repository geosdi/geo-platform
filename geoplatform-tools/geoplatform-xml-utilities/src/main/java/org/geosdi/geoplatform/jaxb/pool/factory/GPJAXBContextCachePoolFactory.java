package org.geosdi.geoplatform.jaxb.pool.factory;

import com.google.common.base.Preconditions;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.xml.bind.JAXBContext;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextCachePoolFactory extends BaseKeyedPooledObjectFactory<GPJAXBContextCachePool, JAXBContext> {

    /**
     * Create an instance that can be served by the pool.
     *
     * @param key the key used when constructing the object
     * @return an instance that can be served by the pool
     * @throws Exception if there is a problem creating a new instance,
     *                   this will be propagated to the code requesting an object.
     */
    @Override
    public JAXBContext create(GPJAXBContextCachePool key) throws Exception {
        Preconditions.checkNotNull(key, "The GPJAXBContextCache must not be null.");
        return key.getContext();
    }

    /**
     * Wrap the provided instance with an implementation of
     * {@link PooledObject}.
     *
     * @param value the instance to wrap
     * @return The provided instance, wrapped by a {@link PooledObject}
     */
    @Override
    public PooledObject<JAXBContext> wrap(JAXBContext value) {
        return new DefaultPooledObject<>(value);
    }

    /**
     * Destroy an instance no longer needed by the pool.
     * <p>
     * The default implementation is a no-op.
     *
     * @param key the key used when selecting the instance
     * @param p   a {@code PooledObject} wrapping the the instance to be destroyed
     */
    @Override
    public void destroyObject(GPJAXBContextCachePool key, PooledObject<JAXBContext> p) throws Exception {
        key.dispose();
    }
}
