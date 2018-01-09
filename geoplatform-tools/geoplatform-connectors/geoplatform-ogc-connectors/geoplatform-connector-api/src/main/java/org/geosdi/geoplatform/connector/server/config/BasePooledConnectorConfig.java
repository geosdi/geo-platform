package org.geosdi.geoplatform.connector.server.config;

import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class BasePooledConnectorConfig implements GPPooledConnectorConfig {

    private final Integer maxTotalConnections;
    private final Integer defaultMaxPerRoute;
    private final Integer connectionTimeout;
    private final Integer maxRedirect;

    /**
     * @param theMaxTotalConnections
     * @param theDefaultMaxPerRoute
     * @param theConnectionTimeout
     * @param theMaxRedirect
     */
    protected BasePooledConnectorConfig(Integer theMaxTotalConnections, Integer theDefaultMaxPerRoute,
            Integer theConnectionTimeout, Integer theMaxRedirect) {
        this.maxTotalConnections = theMaxTotalConnections;
        this.defaultMaxPerRoute = theDefaultMaxPerRoute;
        this.connectionTimeout = ((theConnectionTimeout == null) ? 5 : (theConnectionTimeout < 0)
                ? (-1 * theConnectionTimeout) : theConnectionTimeout) * 1000;
        this.maxRedirect = theMaxRedirect;
    }

    /**
     * @return the maxTotalConnections
     */
    @Override
    public Integer getMaxTotalConnections() {
        return maxTotalConnections;
    }

    /**
     * @return the defaultMaxPerRoute
     */
    @Override
    public Integer getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getConnectionTimeout() {
        return this.connectionTimeout;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getMaxRedirect() {
        return this.maxRedirect;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "maxTotalConnections = " + maxTotalConnections +
                ", defaultMaxPerRoute = " + defaultMaxPerRoute +
                ", connectionTimeout = " + connectionTimeout +
                ", maxRedirect = " + maxRedirect +
                '}';
    }
}
