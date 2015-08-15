package org.geosdi.geoplatform.connector.jaxb.pool;

import org.geosdi.geoplatform.jaxb.pool.GeoPlatformJAXBContextPool;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSJAXBContextPool extends GeoPlatformJAXBContextPool {

    public WFSJAXBContextPool(String contextPath) throws JAXBException {
        super(contextPath);
    }

    public WFSJAXBContextPool(String contextPath, ClassLoader classLoader) throws JAXBException {
        super(contextPath, classLoader);
    }

    public WFSJAXBContextPool(String contextPath, ClassLoader classLoader, Map<String, ?> properties)
            throws JAXBException {
        super(contextPath, classLoader, properties);
    }

    public WFSJAXBContextPool(Class... classToBeBound) throws JAXBException {
        super(classToBeBound);
    }

    public WFSJAXBContextPool(JAXBContext theJaxbContext) {
        super(theJaxbContext);
    }

    public static class WFSJAXBContextPoolKey
            extends GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey {

        public WFSJAXBContextPoolKey() {
            super(WFSConnectorJAXBContextPool.class);
        }

        @Override
        public boolean isCompatibleValue(Object val) {
            return (val instanceof WFSJAXBContextPool);
        }
    }
}
