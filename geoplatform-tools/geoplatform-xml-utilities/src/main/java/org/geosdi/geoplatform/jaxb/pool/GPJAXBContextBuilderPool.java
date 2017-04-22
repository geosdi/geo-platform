package org.geosdi.geoplatform.jaxb.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.jaxb.AbstractJAXBContextBuilder;
import org.geosdi.geoplatform.jaxb.pool.config.GPJAXBContextBuilderPoolConfig;
import org.geosdi.geoplatform.jaxb.pool.factory.GPJAXBContextCachePool;
import org.geosdi.geoplatform.jaxb.pool.factory.GPJAXBContextCachePoolFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * <p>Pooled class for {@link org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder}</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPJAXBContextBuilderPool extends AbstractJAXBContextBuilder {

    static {
        jaxbContextBuilderPool = new GenericKeyedObjectPool<>(new GPJAXBContextCachePoolFactory(),
                new GPJAXBContextBuilderPoolConfig());
    }

    private static volatile GenericKeyedObjectPool<GPJAXBContextCachePool, JAXBContext> jaxbContextBuilderPool;

    private GPJAXBContextBuilderPool() {
    }

    /**
     * @return {@link GPJAXBContextBuilderPool}
     */
    public static GPJAXBContextBuilderPool jaxbContextBuilderPool() {
        return new GPJAXBContextBuilderPool();
    }

    /**
     * @param type
     * @return {@link JAXBContext}
     * @throws JAXBException
     */
    @Override
    protected <T> JAXBContext getContext(Class<T> type) throws JAXBException {
        try {
            GPJAXBContextCachePool key = new GPJAXBContextCachePool(type);
            JAXBContext jaxbContext = jaxbContextBuilderPool.borrowObject(key);
            jaxbContextBuilderPool.returnObject(key, jaxbContext);
            return jaxbContext;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex.getMessage());
        }
    }
}
