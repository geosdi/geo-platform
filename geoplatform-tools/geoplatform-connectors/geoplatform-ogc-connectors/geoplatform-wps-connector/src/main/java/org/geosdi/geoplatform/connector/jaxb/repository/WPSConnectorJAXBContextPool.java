package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.jaxb.context.pool.WPSJAXBContextPool;
import org.geosdi.geoplatform.connector.jaxb.context.pool.WPSJAXBContextPool.WPSJAXBContextPoolKey;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.xml.wps.v100.context.WPSContextServiceProviderV100.WPS_CONTEXT_SERVICE_PROVIDER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WPSConnectorJAXBContextPool implements GeoPlatformJAXBContextProvider {

    private static final Logger logger = LoggerFactory.getLogger(WPSConnectorJAXBContextPool.class);

    static {
        try {
            jaxbContext = new WPSJAXBContextPool(WPS_CONTEXT_SERVICE_PROVIDER.getContextPath());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class "
                    + WPSConnectorJAXBContextPool.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        }
    }

    //
    private static WPSJAXBContextPool jaxbContext;
    public static final WPSJAXBContextPoolKey WPS_CONTEXT_POOL_KEY = new WPSJAXBContextPoolKey();

    protected WPSConnectorJAXBContextPool() {
    }

    /**
     * @param <P>
     * @return {@link P}
     */
    @Override
    public <P extends GPBaseJAXBContext> P getJAXBProvider() {
        return (P) jaxbContext;
    }

    /**
     * @param <K>
     * @return {@link K}
     */
    @Override
    public <K extends GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey> K getKeyProvider() {
        return (K) WPS_CONTEXT_POOL_KEY;
    }
}
