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

import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.geosdi.geoplatform.connector.server.request.cookie.GPConnectorCookieSpec;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;

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
    GPPooledConnectorConfigBuilder withConnectionTimeout(Timeout theConnectionTimeout);

    /**
     * @param theRequestConnectionTimeout
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withRequestConnectionTimeout(Timeout theRequestConnectionTimeout);

    /**
     * @param theResponseConnectionTimeout
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withResponseConnectionTimeout(Timeout theResponseConnectionTimeout);

    /**
     * @param theConnectionKeepAlive
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withConnectionKeepAlive(TimeValue theConnectionKeepAlive);

    /**
     * @param theMaxRedirect
     * @return {@link GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withMaxRedirect(Integer theMaxRedirect);

    /**
     * @param theRedirectsEnabled
     * @return {@link  GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withRedirectsEnabled(boolean theRedirectsEnabled);

    /**
     * @param theCookieSpec
     * @return {@link  GPPooledConnectorConfigBuilder}
     */
    GPPooledConnectorConfigBuilder withCookieSpec(GPConnectorCookieSpec theCookieSpec);

    /**
     * @return {@link GPPooledConnectorConfig}
     */
    GPPooledConnectorConfig build();

    class PooledConnectorConfigBuilder implements GPPooledConnectorConfigBuilder {

        private Integer maxTotalConnections;
        private Integer defaultMaxPerRoute;
        private Timeout connectionTimeout;
        private Timeout requestConnectionTimeout;
        private Timeout responseConnectionTimeout;
        private TimeValue connectionKeepAlive;
        private Integer maxRedirect;
        private boolean redirectsEnabled = TRUE;
        private GPConnectorCookieSpec cookieSpec;

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
        public GPPooledConnectorConfigBuilder withConnectionTimeout(Timeout theConnectionTimeout) {
            this.connectionTimeout = theConnectionTimeout;
            return self();
        }

        /**
         * @param theRequestConnectionTimeout
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withRequestConnectionTimeout(Timeout theRequestConnectionTimeout) {
            this.requestConnectionTimeout = theRequestConnectionTimeout;
            return self();
        }

        /**
         * @param theResponseConnectionTimeout
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withResponseConnectionTimeout(Timeout theResponseConnectionTimeout) {
            this.responseConnectionTimeout = theResponseConnectionTimeout;
            return self();
        }

        /**
         * @param theConnectionKeepAlive
         * @return {@link GPPooledConnectorConfig}
         */
        @Override
        public GPPooledConnectorConfigBuilder withConnectionKeepAlive(TimeValue theConnectionKeepAlive) {
            this.connectionKeepAlive = theConnectionKeepAlive;
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
         * @param theRedirectsEnabled
         * @return {@link  GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withRedirectsEnabled(boolean theRedirectsEnabled) {
            this.redirectsEnabled = theRedirectsEnabled;
            return self();
        }

        /**
         * @param theCookieSpec
         * @return {@link  GPPooledConnectorConfigBuilder}
         */
        @Override
        public GPPooledConnectorConfigBuilder withCookieSpec(GPConnectorCookieSpec theCookieSpec) {
            this.cookieSpec = theCookieSpec;
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
            return new BasePooledConnectorConfig(this.maxTotalConnections, this.defaultMaxPerRoute, this.connectionTimeout,
                    this.requestConnectionTimeout, this.responseConnectionTimeout, this.connectionKeepAlive,
                    this.maxRedirect, this.redirectsEnabled, this.cookieSpec);
        }

        /**
         * @return {@link GPPooledConnectorConfigBuilder}
         */
        protected GPPooledConnectorConfigBuilder self() {
            return this;
        }
    }
}