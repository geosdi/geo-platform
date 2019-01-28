package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

import static org.geosdi.geoplatform.connector.WMSVersion.V111;
import static org.geosdi.geoplatform.wms.v111.WMSContextServiceProviderV111.WMS_CONTEXT_SERVICE_PROVIDER_V110;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WMSConnectorJAXBContextV111 implements GPWMSConnectorJAXBContext {

    private static final Logger logger = LoggerFactory.getLogger(WMSConnectorJAXBContextV111.class);

    static {
        try {
            jaxbContext = new WMSJAXBContext(V111, WMS_CONTEXT_SERVICE_PROVIDER_V110.getContextClasses());
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class " + WMSConnectorJAXBContextV111.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMSJAXBContext jaxbContext;
    public static final WMSJAXBContext.WMSJAXBContextKey WMS_CONTEXT_KEY_V111 = new WMSJAXBContext.WMSJAXBContextKey(WMSConnectorJAXBContextV111.class);

    protected WMSConnectorJAXBContextV111() {
    }

    /**
     * @return {@link P}
     */
    @Override
    public <P extends GPBaseJAXBContext> P getJAXBProvider() {
        return (P) jaxbContext;
    }

    /**
     * @return {@link K}
     */
    @Override
    public <K extends GeoPlatformJAXBContextKey> K getKeyProvider() {
        return (K) WMS_CONTEXT_KEY_V111;
    }

    /**
     * @return {@link Version}
     */
    @Override
    public <Version extends WMSVersion> Version getVersion() {
        return (Version) V111;
    }
}