package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.context.pool.WMSJAXBContextPool;
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
public final class WMSConnectorJAXBContextPoolV111 implements GPWMSConnectorJAXBContextPool {

    private static final Logger logger = LoggerFactory.getLogger(WMSConnectorJAXBContextPoolV111.class);

    static {
        try {
            jaxbContext = new WMSJAXBContextPool(WMS_CONTEXT_SERVICE_PROVIDER_V110.getContextPath(), V111);
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class " + WMSConnectorJAXBContextPoolV111.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMSJAXBContextPool jaxbContext;
    public static final WMSJAXBContext.WMSJAXBContextKey WMS_CONTEXT_POOL_KEY_V111 = new WMSJAXBContext.WMSJAXBContextKey(WMSConnectorJAXBContextPoolV111.class);

    protected WMSConnectorJAXBContextPoolV111() {
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
        return (K) WMSConnectorJAXBContextPoolV111.WMS_CONTEXT_POOL_KEY_V111;
    }

    /**
     * @return {@link Version}
     */
    @Override
    public <Version extends WMSVersion> Version getVersion() {
        return (Version) V111;
    }
}