package org.geosdi.geoplatform.wmc.support.v110.jaxb.repository;

import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.xml.wmc.WMCServiceProviderV110;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMCJAXBContextProviderPoolV110 implements GeoPlatformJAXBContextProvider {

    static {
        try {
            jaxbContext = new WMCJAXBContextPoolV110(
                    WMCServiceProviderV110.loadContextPath());
        } catch (JAXBException e) {
            LoggerFactory.getLogger(WMCJAXBContextProviderPoolV110.class).error(
                    "Failed to Initialize JAXBContext for Class "
                            + WMCJAXBContextProviderPoolV110.class.getName()
                            + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMCJAXBContextPoolV110 jaxbContext;
    public static final WMCJAXBContextPoolV110.WMCJAXBContextPoolKeyV110 WMC_CONTEXT_POOL_KEY_V110 = new WMCJAXBContextPoolV110.WMCJAXBContextPoolKeyV110();

    protected WMCJAXBContextProviderPoolV110() {
    }

    @Override
    public WMCJAXBContextPoolV110 getJAXBProvider() {
        return jaxbContext;
    }

    @Override
    public WMCJAXBContextPoolV110.WMCJAXBContextPoolKeyV110 getKeyProvider() {
        return WMC_CONTEXT_POOL_KEY_V110;
    }
}
