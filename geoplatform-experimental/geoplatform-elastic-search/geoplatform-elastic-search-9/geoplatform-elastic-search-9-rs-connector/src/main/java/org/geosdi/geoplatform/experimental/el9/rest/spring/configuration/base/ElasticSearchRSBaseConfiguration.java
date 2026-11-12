/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el9.rest.spring.configuration.base;

import lombok.ToString;
import org.apache.hc.core5.http.HttpHost;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;
import org.geosdi.geoplatform.experimental.el9.rest.spring.configuration.auth.GPElasticSearchRSAuthConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.abs;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * Implementazione di {@link GPElasticSearchRSBaseConfiguration}, con le stesse regole del connettore per
 * ElasticSearch 7: più nodi separati da {@code ;}, tempi in secondi (anche negativi, presi in valore assoluto),
 * valori del pool presi in valore assoluto.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString(of = {"httpHost", "connectionTimeout", "socketTimeout", "maxTotalConnections", "defaultMaxPerRoute", "maxRedirects", "authConfig", "sslConfig"})
class ElasticSearchRSBaseConfiguration implements GPElasticSearchRSBaseConfiguration {

    private static final long serialVersionUID = 6390718239432145377L;
    //
    private final String httpHost;
    private final Integer connectionTimeout;
    private final Integer socketTimeout;
    private final Integer maxTotalConnections;
    private final Integer defaultMaxPerRoute;
    private final Integer maxRedirects;
    private final GPElasticSearchRSAuthConfiguration authConfig;
    private final GPElasticSearchRSSslConfiguration sslConfig;

    /**
     * @param theHttpHost            i nodi, separati da {@code ;}, per esempio {@code http://localhost:9200}
     * @param theConnectionTimeout   secondi per aprire una connessione; null vuol dire 2
     * @param theSocketTimeout       secondi di attesa della risposta; null vuol dire 30
     * @param theMaxTotalConnections connessioni in tutto; null o zero vuol dire 400
     * @param theDefaultMaxPerRoute  connessioni per nodo; null o zero vuol dire 60
     * @param theMaxRedirects        redirect seguiti; null o zero vuol dire 5
     * @param theAuthConfig          le credenziali
     * @param theSslConfig           la configurazione SSL
     */
    ElasticSearchRSBaseConfiguration(@Nullable String theHttpHost, @Nullable Integer theConnectionTimeout, @Nullable Integer theSocketTimeout,
            @Nullable Integer theMaxTotalConnections, @Nullable Integer theDefaultMaxPerRoute, @Nullable Integer theMaxRedirects,
            @Nonnull(when = NEVER) GPElasticSearchRSAuthConfiguration theAuthConfig, @Nonnull(when = NEVER) GPElasticSearchRSSslConfiguration theSslConfig) {
        this.httpHost = theHttpHost;
        this.connectionTimeout = theConnectionTimeout;
        this.socketTimeout = theSocketTimeout;
        this.maxTotalConnections = theMaxTotalConnections;
        this.defaultMaxPerRoute = theDefaultMaxPerRoute;
        this.maxRedirects = theMaxRedirects;
        this.authConfig = theAuthConfig;
        this.sslConfig = theSslConfig;
    }

    /**
     * @return {@link HttpHost}
     */
    @Override
    public HttpHost[] getHttpHosts() {
        return of(this.httpHost.split(";"))
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(value -> !(value.isEmpty()))
                .map(value -> (value.endsWith("/") ? value.substring(0, value.length() - 1) : value))
                .map(ElasticSearchRSBaseConfiguration::toHttpHost)
                .toArray(HttpHost[]::new);
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getConnectionTimeout() {
        return ((this.connectionTimeout == null) ? this.defaultConnectionTimeout() : toMillis(this.connectionTimeout));
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getSocketTimeout() {
        return ((this.socketTimeout == null) ? this.defaultSocketTimeout() : toMillis(this.socketTimeout));
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
        return (((this.maxTotalConnections != null) && (this.maxTotalConnections != 0)) ? abs(this.maxTotalConnections) : this.defaultMaxTotalConnections());
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getDefaultMaxPerRoute() {
        return (((this.defaultMaxPerRoute != null) && (this.defaultMaxPerRoute != 0)) ? abs(this.defaultMaxPerRoute) : this.defaultMaxPerRoute());
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getMaxRedirects() {
        return (((this.maxRedirects != null) && (this.maxRedirects != 0)) ? abs(this.maxRedirects) : this.defaultMaxRedirects());
    }

    /**
     * Controlla la configurazione alla creazione del bean.
     *
     * @throws Exception se mancano i nodi o, con l'SSL, la password del truststore
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument((this.httpHost != null) && !(this.httpHost.trim().isEmpty()), "La proprietà gp.elasticsearch.rest.hosts non deve essere vuota.");
        checkArgument(this.authConfig != null, "Il parametro authConfig non deve essere null.");
        checkArgument(this.sslConfig != null, "Il parametro sslConfig non deve essere null.");
        if (this.sslConfig.isSetSecureSocketLayer()) {
            checkArgument((this.sslConfig.getKeyStorePassword() != null) && !(this.sslConfig.getKeyStorePassword().trim().isEmpty()),
                    "Con gp.elasticsearch.rest.key_store serve anche gp.elasticsearch.rest.key_store.password.");
        }
        checkArgument(this.getHttpHosts().length > 0, "La proprietà gp.elasticsearch.rest.hosts non contiene nodi.");
    }

    /**
     * @param theSeconds i secondi, anche negativi
     * @return {@link Integer} i millisecondi del valore assoluto
     */
    private static Integer toMillis(int theSeconds) {
        return Long.valueOf(ofSeconds(abs(theSeconds)).toMillis()).intValue();
    }

    /**
     * @param theValue il nodo, per esempio {@code https://host:9200}
     * @return {@link HttpHost}
     */
    private static HttpHost toHttpHost(@Nonnull(when = NEVER) String theValue) {
        checkArgument(theValue != null, "Il parametro value non deve essere null.");
        try {
            return HttpHost.create(theValue);
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Nodo di ElasticSearch non valido: " + theValue, ex);
        }
    }
}
