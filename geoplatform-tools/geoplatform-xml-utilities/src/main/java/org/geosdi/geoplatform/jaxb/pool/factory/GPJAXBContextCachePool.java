package org.geosdi.geoplatform.jaxb.pool.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import java.util.Objects;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextCachePool implements IGPJAXBContextCachePool {

    private static final Logger logger = LoggerFactory.getLogger(GPJAXBContextCachePool.class);
    //
    private final Class type;
    private JAXBContext context;

    public GPJAXBContextCachePool(Class theType) {
        this.type = theType;
    }

    /**
     * @return {@link JAXBContext}
     */
    public JAXBContext getContext() throws Exception {
        return (this.context = (this.context != null) ? this.context : JAXBContext.newInstance(this.type));
    }

    /**
     * @return {@link Class}
     */
    public Class getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GPJAXBContextCachePool)) return false;
        GPJAXBContextCachePool that = (GPJAXBContextCachePool) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void dispose() {
        logger.debug("#########################CALLED {}#dispose for ----------> class : {}\n",
                this, this.type);
    }
}
