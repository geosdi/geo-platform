package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext.WPSJAXBContextKey;
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
public final class WPSConnectorJAXBContext implements GeoPlatformJAXBContextProvider {

    private static final Logger logger = LoggerFactory.getLogger(WPSConnectorJAXBContext.class);

    static {
        try {
            jaxbContext = new WPSJAXBContext(WPS_CONTEXT_SERVICE_PROVIDER.getContextPath());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class "
                    + WPSConnectorJAXBContext.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        }
    }

    //
    private static WPSJAXBContext jaxbContext;
    public static final WPSJAXBContextKey WPS_CONTEXT_KEY = new WPSJAXBContextKey();

    protected WPSConnectorJAXBContext() {
    }

    @Override
    public <P extends GPBaseJAXBContext> P getJAXBProvider() {
        return (P) jaxbContext;
    }

    @Override
    public <K extends GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey> K getKeyProvider() {
        return (K) WPS_CONTEXT_KEY;
    }
}
