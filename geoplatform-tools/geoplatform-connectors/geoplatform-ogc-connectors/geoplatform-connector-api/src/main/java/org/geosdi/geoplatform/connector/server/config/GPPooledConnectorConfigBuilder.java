/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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