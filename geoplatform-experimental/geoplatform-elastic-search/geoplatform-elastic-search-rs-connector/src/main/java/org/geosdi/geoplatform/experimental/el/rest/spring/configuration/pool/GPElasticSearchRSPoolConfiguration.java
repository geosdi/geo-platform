package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.pool;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRSPoolConfiguration extends InitializingBean, Serializable {

    /**
     * @return {@link Integer}
     */
    Integer getMaxTotalConnections();

    /**
     * @return {@link Integer}
     */
    Integer getDefaultMaxPerRoute();

    /**
     * @return {@link Integer}
     */
    Integer getMaxRedirects();

    /**
     * @return {@link Integer}
     */
    default Integer defaultMaxTotalConnections() {
        return Integer.valueOf(400);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultMaxPerRoute() {
        return Integer.valueOf(60);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultMaxRedirects() {
        return Integer.valueOf(5);
    }
}