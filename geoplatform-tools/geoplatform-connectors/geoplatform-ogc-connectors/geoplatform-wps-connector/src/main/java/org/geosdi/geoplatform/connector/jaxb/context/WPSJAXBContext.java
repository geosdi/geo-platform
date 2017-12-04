package org.geosdi.geoplatform.connector.jaxb.context;

import org.geosdi.geoplatform.connector.jaxb.repository.WPSConnectorJAXBContext;
import org.geosdi.geoplatform.jaxb.GeoPlatformJAXBContext;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSJAXBContext extends GeoPlatformJAXBContext {

    /**
     * @param contextPath
     * @throws JAXBException
     */
    public WPSJAXBContext(String contextPath) throws JAXBException {
        super(contextPath);
    }

    /**
     * @param contextPath
     * @param classLoader
     * @throws JAXBException
     */
    public WPSJAXBContext(String contextPath, ClassLoader classLoader) throws JAXBException {
        super(contextPath, classLoader);
    }

    /**
     * @param contextPath
     * @param classLoader
     * @param properties
     * @throws JAXBException
     */
    public WPSJAXBContext(String contextPath, ClassLoader classLoader, Map<String, ?> properties) throws JAXBException {
        super(contextPath, classLoader, properties);
    }

    /**
     * @param classToBeBound
     * @throws JAXBException
     */
    public WPSJAXBContext(Class... classToBeBound) throws JAXBException {
        super(classToBeBound);
    }

    /**
     * @param theJaxbContext
     */
    public WPSJAXBContext(JAXBContext theJaxbContext) {
        super(theJaxbContext);
    }

    /**
     * @return {@link Marshaller}
     * @throws Exception
     */
    @Override
    public Marshaller acquireMarshaller() throws Exception {
        return super.createMarshaller();
    }

    /**
     * @return {@link Unmarshaller}
     * @throws Exception
     */
    @Override
    public Unmarshaller acquireUnmarshaller() throws Exception {
        return super.createUnmarshaller();
    }

    public static class WPSJAXBContextKey extends GeoPlatformJAXBContextKey {

        public WPSJAXBContextKey() {
            super(WPSConnectorJAXBContext.class);
        }

        @Override
        public boolean isCompatibleValue(Object o) {
            return o instanceof WPSJAXBContext;
        }
    }
}
