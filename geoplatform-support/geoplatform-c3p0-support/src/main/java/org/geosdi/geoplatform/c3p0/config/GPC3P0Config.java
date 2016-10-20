package org.geosdi.geoplatform.c3p0.config;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPC3P0Config extends GPC3P0DefaultConfig {

    /**
     * @return {@link Integer}
     */
    Integer getAcquireIncrement();

    /**
     * @return {@link Integer}
     */
    Integer getAcquireRetryAttempts();

    /**
     * @return {@link Integer}
     */
    Integer getMinPoolSize();

    /**
     * @return {@link Integer}
     */
    Integer getMaxPoolSize();

    /**
     * @return {@link Integer}
     */
    Integer getMaxIdleTime();

    /**
     * @return {@link Integer}
     */
    Integer getMaxConnectionAge();

    /**
     * @return {@link String}
     */
    String getConnectionCustomizerClassName();
}
