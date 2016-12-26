package org.geosdi.geoplatform.support.mail.spring.configuration.freemarker;

import org.springframework.core.io.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPFreeMarkerConfigLocation {

    /**
     * @return {@link Resource}
     */
     Resource getFreeMarkerConfigLocation();
}
