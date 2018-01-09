package org.geosdi.geoplatform.connector.server.config;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPooledConnectorConfigBuilder {

    /**
     * @param theMaxTotalConnections
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withMaxTotalConnections(Integer theMaxTotalConnections);

    /**
     * @param theDefaultMaxPerRoute
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withDefaultMaxPerRoute(Integer theDefaultMaxPerRoute);

    /**
     * @param theConnectionTimeout
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withConnectionTimeout(Integer theConnectionTimeout);

    /**
     * @param theMaxRedirect
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withMaxRedirect(Integer theMaxRedirect);

    /**
     * @return {@link GPPooledConnectorConfig}
     */
    GPPooledConnectorConfig build();

    class PooledConnectorConfigBuilder implements GPPooledConnectorConfigBuilder {

        private Integer maxTotalConnections;
        private Integer defaultMaxPerRoute;
        private Integer connectionTimeout;
        private Integer maxRedirect;

        private PooledConnectorConfigBuilder() {
        }

        /**
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        public static GPPooledConnectorConfigBuilder pooledConnectorConfigBuilder() {
            return new PooledConnectorConfigBuilder();
        }

        /**
         * @param theMaxTotalConnections
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withMaxTotalConnections(Integer theMaxTotalConnections) {
            this.maxTotalConnections = theMaxTotalConnections;
            return self();
        }

        /**
         * @param theDefaultMaxPerRoute
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withDefaultMaxPerRoute(Integer theDefaultMaxPerRoute) {
            this.defaultMaxPerRoute = theDefaultMaxPerRoute;
            return self();
        }

        @Override
        public GPPooledConnectorConfigBuilder withConnectionTimeout(Integer theConnectionTimeout) {
            this.connectionTimeout = theConnectionTimeout;
            return self();
        }

        /**
         * @param theMaxRedirect
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withMaxRedirect(Integer theMaxRedirect) {
            this.maxRedirect = theMaxRedirect;
            return self();
        }

        /**
         * @return {@link GPPooledConnectorConfig}
         */
        @Override
        public GPPooledConnectorConfig build() {
            checkArgument((maxTotalConnections > 0), "The Parameter MaxTotalConnections must be greater than zero.");
            checkArgument((defaultMaxPerRoute > 0), "The Parameter MaxTotalPerRoute must be greater than zero.");
            checkArgument(maxRedirect > 0, "The Parameter MaxRedirect must be greater than 0.");
            return new BasePooledConnectorConfig(this.maxTotalConnections, this.defaultMaxPerRoute,
                    this.connectionTimeout, this.maxRedirect);
        }

        /**
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        protected GPPooledConnectorConfigBuilder self() {
            return this;
        }
    }
}