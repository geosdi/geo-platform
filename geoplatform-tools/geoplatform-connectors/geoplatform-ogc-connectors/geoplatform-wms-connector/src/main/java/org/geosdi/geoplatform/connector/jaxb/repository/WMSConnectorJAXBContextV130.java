package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

import static org.geosdi.geoplatform.connector.WMSVersion.V130;
import static org.geosdi.geoplatform.wms.v130.WMSContextServiceProviderV130.WMS_CONTEXT_SERVICE_PROVIDER_V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WMSConnectorJAXBContextV130 implements GPWMSConnectorJAXBContext {

    private static final Logger logger = LoggerFactory.getLogger(WMSConnectorJAXBContextV130.class);

    static {
        try {
            jaxbContext = new WMSJAXBContext(WMS_CONTEXT_SERVICE_PROVIDER_V130.getContextPath(), V130);
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class " + WMSConnectorJAXBContextV130.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMSJAXBContext jaxbContext;
    public static final WMSJAXBContext.WMSJAXBContextKey WMS_CONTEXT_KEY_V130 = new WMSJAXBContext.WMSJAXBContextKey(WMSConnectorJAXBContextV130.class);

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
    public <K extends GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey> K getKeyProvider() {
        return (K) WMS_CONTEXT_KEY_V130;
    }

    /**
     * @return {@link Version}
     */
    @Override
    public <Version extends WMSVersion> Version getVersion() {
        return (Version) V130;
    }
}