package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.jaxb.context.pool.WFSJAXBContextPool;
import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.xml.wfs.WFSContextServiceProvider;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WFSConnectorJAXBContextPool
        implements GeoPlatformJAXBContextProvider {

    static {
        try {
            jaxbContext = new WFSJAXBContextPool(
                    WFSContextServiceProvider.loadContextPath());
        } catch (JAXBException e) {
            LoggerFactory.getLogger(WFSConnectorJAXBContextPool.class).error(
                    "Failed to Initialize JAXBContext for Class "
                            + WFSConnectorJAXBContextPool.class.getName()
                            + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WFSJAXBContextPool jaxbContext;
    public static final WFSJAXBContextPool.WFSJAXBContextPoolKey WFS_CONTEXT_POOL_KEY = new WFSJAXBContextPool.WFSJAXBContextPoolKey();

    protected WFSConnectorJAXBContextPool() {
    }

    @Override
    public WFSJAXBContextPool getJAXBProvider() {
        return jaxbContext;
    }

    @Override
    public WFSJAXBContextPool.WFSJAXBContextPoolKey getKeyProvider() {
        return WFSConnectorJAXBContextPool.WFS_CONTEXT_POOL_KEY;
    }
}
