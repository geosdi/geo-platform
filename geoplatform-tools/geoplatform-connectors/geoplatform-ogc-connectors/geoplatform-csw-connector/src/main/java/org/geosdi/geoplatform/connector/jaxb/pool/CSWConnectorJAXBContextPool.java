package org.geosdi.geoplatform.connector.jaxb.pool;

import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.xml.csw.CSWContextServiceProvider;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class CSWConnectorJAXBContextPool implements GeoPlatformJAXBContextProvider {

    static {
        try {
            jaxbContext = new CSWJAXBContextPool(
                    CSWContextServiceProvider.loadContextPath());
        } catch (JAXBException e) {
            LoggerFactory.getLogger(CSWConnectorJAXBContextPool.class).error(
                    "Failed to Initialize JAXBContext for Class "
                            + CSWConnectorJAXBContextPool.class.getName()
                            + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static CSWJAXBContextPool jaxbContext;
    public static final CSWJAXBContextPool.CSWJAXBContextPoolKey CSW_CONTEXT_POOL_KEY = new CSWJAXBContextPool.CSWJAXBContextPoolKey();

    protected CSWConnectorJAXBContextPool() {
    }

    @Override
    public CSWJAXBContextPool getJAXBProvider() {
        return jaxbContext;
    }

    @Override
    public CSWJAXBContextPool.CSWJAXBContextPoolKey getKeyProvider() {
        return CSWConnectorJAXBContextPool.CSW_CONTEXT_POOL_KEY;
    }
}
