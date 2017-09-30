package org.geosdi.geoplatform.connector.server.config;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPooledConnectorConfig {

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
    Integer getConnectionTimeout();

    /**
     * @return {@link Integer}
     */
    Integer getMaxRedirect();
}