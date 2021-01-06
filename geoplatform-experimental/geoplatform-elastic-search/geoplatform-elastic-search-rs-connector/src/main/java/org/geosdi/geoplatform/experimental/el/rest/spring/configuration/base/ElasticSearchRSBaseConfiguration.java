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
package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.apache.http.HttpHost;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth.GPElasticSearchRSAuthConfiguration;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Long.valueOf;
import static java.lang.Math.abs;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@ToString(of = {"httpHosts", "connectionTimeout", "socketTimeout", "maxTotalConnections", "defaultMaxPerRoute", "maxRedirects", "authConfig", "sslConfig"})
@RequiredArgsConstructor
class ElasticSearchRSBaseConfiguration implements GPElasticSearchRSBaseConfiguration {

    private static final long serialVersionUID = 1493541089790319103L;
    //
    @NonNull
    private String httpHost;
    @NonNull
    private Integer connectionTimeout;
    @NonNull
    private Integer socketTimeout;
    @NonNull
    private Integer maxTotalConnections;
    @NonNull
    private Integer defaultMaxPerRoute;
    @NonNull
    private Integer maxRedirects;
    @NonNull
    private GPElasticSearchRSAuthConfiguration authConfig;
    @NonNull
    private GPElasticSearchRSSslConfiguration sslConfig;
    private HttpHost[] httpHosts;

    /**
     * @return {@link HttpHost[]}
     */
    @Override
    public HttpHost[] getHttpHosts() {
        return this.httpHosts = ((this.httpHosts != null) ? this.httpHosts :
                of(this.httpHost.split(";")).filter(value -> (value != null) && !(value.trim().isEmpty()))
                        .map(value -> (value.endsWith("/") ? value.substring(0, value.lastIndexOf("/")) : value))
                        .map(HttpHost::create).toArray(HttpHost[]::new));
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getConnectionTimeout() {
        return this.connectionTimeout = ((this.connectionTimeout == null) ? this.defaultConnectionTimeout() :
                this.connectionTimeout < 0 ? valueOf(ofSeconds(abs(this.connectionTimeout)).toMillis()).intValue() :
                        valueOf(ofSeconds(this.connectionTimeout).toMillis()).intValue());
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getSocketTimeout() {
        return this.socketTimeout = ((this.socketTimeout == null) ? this.defaultSocketTimeout() :
                this.socketTimeout < 0 ? valueOf(ofSeconds(abs(this.socketTimeout)).toMillis()).intValue() :
                        valueOf(ofSeconds(this.socketTimeout).toMillis()).intValue());
    }

    /**
     * @return {@link GPElasticSearchRSAuthConfiguration}
     */
    @Override
    public GPElasticSearchRSAuthConfiguration getAuthConfig() {
        return this.authConfig;
    }

    /**
     * @return {@link GPElasticSearchRSSslConfiguration}
     */
    @Override
    public GPElasticSearchRSSslConfiguration getSslConfig() {
        return this.sslConfig;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getMaxTotalConnections() {
        return this.maxTotalConnections = (((this.maxTotalConnections != null) && (abs(this.maxTotalConnections) > 0)) ?
                abs(this.maxTotalConnections) : this.defaultMaxTotalConnections());
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getDefaultMaxPerRoute() {
        return this.defaultMaxPerRoute = (((this.defaultMaxPerRoute != null) && (abs(this.defaultMaxPerRoute) > 0)) ?
                abs(this.defaultMaxPerRoute) : this.defaultMaxPerRoute());
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getMaxRedirects() {
        return this.maxRedirects = (((this.maxRedirects != null) && (abs(this.maxRedirects) > 0)) ?
                abs(this.maxRedirects) : this.defaultMaxRedirects());
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link org.springframework.beans.factory.BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument((this.httpHost != null) && !(this.httpHost.trim().isEmpty()), "The Parameter httpHost must not be null or an empty string.");
        checkArgument(this.authConfig != null, "The Parameter authConfig must not be null.");
        checkArgument(this.sslConfig != null, "The Parameter sslConfig must not be null.");
        if (this.sslConfig.isSetSecureSocketLayer()) {
            checkArgument(((this.sslConfig.getKeyStorePassword() != null) && !(this.sslConfig.getKeyStorePassword().trim().isEmpty())), "The KeyStorePassword Parameter must not be null or an empty value.");
        }
    }
}