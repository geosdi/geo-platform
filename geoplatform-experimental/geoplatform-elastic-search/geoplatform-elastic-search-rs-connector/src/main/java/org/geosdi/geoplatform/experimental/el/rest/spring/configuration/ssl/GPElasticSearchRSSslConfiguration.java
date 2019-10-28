package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRSSslConfiguration extends Serializable {

    /**
     * @return {@link String}
     */
    String getKeyStorePath();

    /**
     * @return {@link String}
     */
    String getKeyStorePassword();

    /**
     * @return {@link Boolean}
     */
    Boolean isSetSecureSocketLayer();
}