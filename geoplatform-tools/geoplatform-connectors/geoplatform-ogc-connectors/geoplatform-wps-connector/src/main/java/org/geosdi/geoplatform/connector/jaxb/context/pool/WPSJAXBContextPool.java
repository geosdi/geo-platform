package org.geosdi.geoplatform.connector.jaxb.context.pool;

import org.geosdi.geoplatform.connector.jaxb.repository.WPSConnectorJAXBContextPool;
import org.geosdi.geoplatform.jaxb.pool.GeoPlatformJAXBContextPool;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSJAXBContextPool extends GeoPlatformJAXBContextPool {

    /**
     * @param contextPath
     * @throws JAXBException
     */
    public WPSJAXBContextPool(String contextPath) throws JAXBException {
        super(contextPath);
    }

    /**
     * @param contextPath
     * @param classLoader
     * @throws JAXBException
     */
    public WPSJAXBContextPool(String contextPath, ClassLoader classLoader) throws JAXBException {
        super(contextPath, classLoader);
    }

    /**
     * @param contextPath
     * @param classLoader
     * @param properties
     * @throws JAXBException
     */
    public WPSJAXBContextPool(String contextPath, ClassLoader classLoader, Map<String, ?> properties) throws JAXBException {
        super(contextPath, classLoader, properties);
    }

    /**
     * @param classToBeBound
     * @throws JAXBException
     */
    public WPSJAXBContextPool(Class... classToBeBound) throws JAXBException {
        super(classToBeBound);
    }

    /**
     * @param theJaxbContext
     */
    public WPSJAXBContextPool(JAXBContext theJaxbContext) {
        super(theJaxbContext);
    }

    public static class WPSJAXBContextPoolKey extends GeoPlatformJAXBContextKey {

        public WPSJAXBContextPoolKey() {
            super(WPSConnectorJAXBContextPool.class);
        }

        @Override
        public boolean isCompatibleValue(Object val) {
            return (val instanceof WPSJAXBContextPool);
        }
    }
}
