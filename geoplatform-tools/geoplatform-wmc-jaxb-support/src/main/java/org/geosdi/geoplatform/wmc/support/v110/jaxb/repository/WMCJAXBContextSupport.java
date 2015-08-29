package org.geosdi.geoplatform.wmc.support.v110.jaxb.repository;

import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WMCJAXBContextSupport {

    static {
        INSTANCE = new WMCJAXBContextRepository();
    }

    //
    private static final WMCJAXBContextRepository INSTANCE;

    private WMCJAXBContextSupport() {
    }

    public static void registerProvider(WMCJAXBContextRepository.GeoPlatformJAXBContextKey key,
            Object provider) {
        INSTANCE.registerProvider(key, provider);
    }

    public static <P extends GPBaseJAXBContext> P getProvider(WMCJAXBContextRepository.GeoPlatformJAXBContextKey key) {
        return INSTANCE.getProvider(key);
    }
}
