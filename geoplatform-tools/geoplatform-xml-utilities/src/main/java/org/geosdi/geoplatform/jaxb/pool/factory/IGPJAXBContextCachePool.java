package org.geosdi.geoplatform.jaxb.pool.factory;

import javax.xml.bind.JAXBContext;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJAXBContextCachePool {

    /**
     * @return {@link JAXBContext}
     */
    JAXBContext getContext() throws Exception;

    /**
     * @return {@link Class}
     */
    Class getType();

    void dispose();
}
