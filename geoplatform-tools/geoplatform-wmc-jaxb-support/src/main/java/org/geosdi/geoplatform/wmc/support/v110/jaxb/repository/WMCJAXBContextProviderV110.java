package org.geosdi.geoplatform.wmc.support.v110.jaxb.repository;

import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.WMCJAXBContextV110;
import org.geosdi.geoplatform.xml.wmc.WMCServiceProviderV110;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMCJAXBContextProviderV110 implements GeoPlatformJAXBContextProvider {

    static {
        try {
            jaxbContext = new WMCJAXBContextV110(
                    WMCServiceProviderV110.loadContextPath());
        } catch (JAXBException e) {
            LoggerFactory.getLogger(WMCJAXBContextProviderV110.class).error(
                    "Failed to Initialize JAXBContext for Class "
                            + WMCJAXBContextProviderV110.class.getName()
                            + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMCJAXBContextV110 jaxbContext;
    public static final WMCJAXBContextV110.WMCJAXBContextKeyV110 WMC_CONTEXT_KEY_V110 = new WMCJAXBContextV110.WMCJAXBContextKeyV110();

    protected WMCJAXBContextProviderV110() {
    }

    @Override
    public WMCJAXBContextV110 getJAXBProvider() {
        return jaxbContext;
    }

    @Override
    public WMCJAXBContextV110.WMCJAXBContextKeyV110 getKeyProvider() {
        return WMC_CONTEXT_KEY_V110;
    }
}
