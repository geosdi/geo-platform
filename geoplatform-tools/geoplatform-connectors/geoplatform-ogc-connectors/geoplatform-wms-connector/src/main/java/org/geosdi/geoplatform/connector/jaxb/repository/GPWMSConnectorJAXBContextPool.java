package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSConnectorJAXBContextPool extends GeoPlatformJAXBContextProvider {

    /**
     * @param <Version>
     * @return {@link Version}
     */
    <Version extends WMSVersion> Version getVersion();
}