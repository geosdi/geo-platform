/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.geosdi.geoplatform.connector.server.request.cookie.GPConnectorCookieSpec;

import static java.lang.Boolean.TRUE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.hc.core5.util.Timeout.of;
import static org.geosdi.geoplatform.connector.server.request.cookie.ConnectorCookieSpec.IGNORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@Immutable
public class BasePooledConnectorConfig implements GPPooledConnectorConfig {

    @Getter
    private final Integer maxTotalConnections;
    @Getter
    private final Integer defaultMaxPerRoute;
    @Getter
    private final Timeout connectionTimeout;
    @Getter
    private final Timeout requestConnectionTimeout;
    @Getter
    private final Timeout responseConnectionTimeout;
    @Getter
    private final TimeValue connectionKeepAlive;
    @Getter
    private final Integer maxRedirect;
    private final Boolean redirectsEnabled;
    @Getter
    private final GPConnectorCookieSpec cookieSpec;

    /**
     * @param theMaxTotalConnections
     * @param theDefaultMaxPerRoute
     * @param theConnectionTimeout
     * @param theRequestConnectionTimeout
     * @param theResponseConnectionTimeout
     * @param theConnectionKeepAlive
     * @param theMaxRedirect
     * @param theRedirectsEnabled
     * @param theCookieSpec
     */
    BasePooledConnectorConfig(Integer theMaxTotalConnections, Integer theDefaultMaxPerRoute, Timeout theConnectionTimeout,
            Timeout theRequestConnectionTimeout, Timeout theResponseConnectionTimeout, TimeValue theConnectionKeepAlive,
            Integer theMaxRedirect, Boolean theRedirectsEnabled, GPConnectorCookieSpec theCookieSpec) {
        this.maxTotalConnections = theMaxTotalConnections;
        this.defaultMaxPerRoute = theDefaultMaxPerRoute;
        this.connectionTimeout = ((theConnectionTimeout == null) ? of(30l, SECONDS) : theConnectionTimeout);
        this.requestConnectionTimeout = ((theRequestConnectionTimeout == null) ? of(30l, SECONDS) : theRequestConnectionTimeout);
        this.responseConnectionTimeout = ((theResponseConnectionTimeout == null) ? of(30l, SECONDS) : theResponseConnectionTimeout);
        this.connectionKeepAlive = ((theConnectionKeepAlive != null) ? theConnectionKeepAlive : TimeValue.of(10l, SECONDS));
        this.maxRedirect = ((theMaxRedirect != null) && (theMaxRedirect <= 50) ? theMaxRedirect : 50);
        this.redirectsEnabled = ((theRedirectsEnabled != null) ? theRedirectsEnabled : TRUE);
        this.cookieSpec = ((theCookieSpec != null) ? theCookieSpec : IGNORE);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isRedirectsEnabled() {
        return this.redirectsEnabled;
    }
}