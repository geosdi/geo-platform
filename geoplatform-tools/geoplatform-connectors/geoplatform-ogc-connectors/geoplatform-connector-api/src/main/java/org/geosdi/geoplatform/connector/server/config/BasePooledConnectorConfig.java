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

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.server.request.cookie.GPConnectorCookieSpec;

import static org.geosdi.geoplatform.connector.server.request.cookie.ConnectorCookieSpec.IGNORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class BasePooledConnectorConfig implements GPPooledConnectorConfig {

    private final Integer maxTotalConnections;
    private final Integer defaultMaxPerRoute;
    private final Integer connectionTimeout;
    private final Integer requestConnectionTimeout;
    private final Integer responseConnectionTimeout;
    private final Integer connectionKeepAlive;
    private final Integer maxRedirect;
    private final boolean redirectsEnabled;
    private final GPConnectorCookieSpec cookieSpec;

    /**
     * @param theMaxTotalConnections
     * @param theDefaultMaxPerRoute
     * @param theConnectionTimeout
     * @param theMaxRedirect
     * @param theRequestConnectionTimeout
     * @param theResponseConnectionTimeout
     * @param theConnectionKeepAlive
     * @param theRedirectsEnabled
     * @param theCookieSpec
     */
    protected BasePooledConnectorConfig(Integer theMaxTotalConnections, Integer theDefaultMaxPerRoute,
            Integer theConnectionTimeout, Integer theMaxRedirect, Integer theRequestConnectionTimeout,
            Integer theResponseConnectionTimeout, Integer theConnectionKeepAlive, boolean theRedirectsEnabled,
            GPConnectorCookieSpec theCookieSpec) {
        this.maxTotalConnections = theMaxTotalConnections;
        this.defaultMaxPerRoute = theDefaultMaxPerRoute;
        this.connectionTimeout = ((theConnectionTimeout == null) ? 5 : (theConnectionTimeout < 0) ? (-1 * theConnectionTimeout) : theConnectionTimeout) * 1000;
        this.requestConnectionTimeout = ((theRequestConnectionTimeout == null) ? 5 : (theRequestConnectionTimeout < 0) ? (-1 * theRequestConnectionTimeout) : theRequestConnectionTimeout) * 1000;
        this.responseConnectionTimeout = ((theResponseConnectionTimeout == null) ? 5 : (theResponseConnectionTimeout < 0) ? (-1 * theResponseConnectionTimeout) : theResponseConnectionTimeout) * 1000;
        this.connectionKeepAlive = ((theConnectionKeepAlive == null) ? 5 : (theConnectionKeepAlive < 0) ? (-1 * theConnectionKeepAlive) : theConnectionKeepAlive) * 1000;
        this.maxRedirect = theMaxRedirect;
        this.redirectsEnabled = theRedirectsEnabled;
        this.cookieSpec = ((theCookieSpec != null) ? theCookieSpec : IGNORE);
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

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getRequestConnectionTimeout() {
        return this.requestConnectionTimeout;
    }

    /**
     * @return {@link  Integer}
     */
    @Override
    public Integer getResponseConnectionTimeout() {
        return this.responseConnectionTimeout;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getConnectionKeepAlive() {
        return this.connectionKeepAlive;
    }

    /**
     * @return {@link  Boolean}
     */
    @Override
    public boolean isRedirectsEnabled() {
        return this.redirectsEnabled;
    }

    /**
     * @return {@link GPConnectorCookieSpec}
     */
    @Override
    public GPConnectorCookieSpec getCookieSpec() {
        return this.cookieSpec;
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