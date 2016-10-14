package org.geosdi.geoplatform.c3p0.config;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPC3P0DefaultConfig {

    String CONNECTION_CUSTOMIZER_CLASS = "org.geosdi.geoplatform.c3p0.customizer";

    /**
     * @return {@link Integer}
     */
    default Integer defaultAcquireIncrement() {
        return new Integer(3);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultAcquireRetryAttempts() {
        return new Integer(30);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultMinPoolSize() {
        return new Integer(3);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultMaxPoolSize() {
        return new Integer(15);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultMaxIdleTime() {
        return new Integer(0);
    }

    /**
     * @return {@link Integer}
     */
    default Integer defaultMaxConnectionAge() {
        return new Integer(0);
    }
}
