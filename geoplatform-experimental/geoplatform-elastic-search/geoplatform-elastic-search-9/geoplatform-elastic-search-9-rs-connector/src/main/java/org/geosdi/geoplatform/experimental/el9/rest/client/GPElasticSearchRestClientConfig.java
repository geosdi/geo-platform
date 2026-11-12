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
package org.geosdi.geoplatform.experimental.el9.rest.client;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.json.jackson.Jackson3JsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest5_client.Rest5ClientTransport;
import co.elastic.clients.transport.rest5_client.low_level.Rest5Client;
import co.elastic.clients.transport.rest5_client.low_level.Rest5ClientBuilder;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;
import org.geosdi.geoplatform.experimental.el9.rest.spring.configuration.base.GPElasticSearchRSBaseConfiguration;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.threadpool.support.factory.GPDecoratorThreadFactory;
import org.geosdi.geoplatform.threadpool.support.factory.GPDefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.SecureRandom;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Thread.NORM_PRIORITY;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.FAIL_ON_NULL_FOR_PRIMITIVES_DISABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.FAIL_ON_UNKNOW_PROPERTIES_DISABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.UNWRAP_ROOT_VALUE_DISABLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.WRAP_ROOT_VALUE_DISABLE;

/**
 * Configurazione Spring dei client di ElasticSearch 9: client HTTP Rest5, trasporto, client sincrono e asincrono.
 * <p>I client si chiudono con il contesto di Spring: i DAO li usano ma non li chiudono, perché sono condivisi.</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRestClientConfig.class);
    //
    private final GPElasticSearchRSBaseConfiguration elasticSearchRSBaseConfiguration;

    /**
     * @param theElasticSearchRSBaseConfiguration la configurazione della connessione
     */
    GPElasticSearchRestClientConfig(@Nonnull(when = NEVER) @Qualifier(value = "elasticSearchRSBaseConfiguration") GPElasticSearchRSBaseConfiguration theElasticSearchRSBaseConfiguration) {
        checkArgument(theElasticSearchRSBaseConfiguration != null, "Il parametro elasticSearchRSBaseConfiguration non deve essere null.");
        this.elasticSearchRSBaseConfiguration = theElasticSearchRSBaseConfiguration;
    }

    /**
     * Il mapper che il client usa per i suoi tipi e per i valori in {@code JsonData}. I documenti invece li scrivono
     * e li leggono i mapper dei DAO, con la loro configurazione di geoplatform-jackson-support: per questo qui non
     * c'è il wrap del nome radice.
     *
     * @return {@link JsonpMapper}
     */
    @Bean
    public JsonpMapper elasticSearchJsonpMapper() {
        return new Jackson3JsonpMapper(new GPJacksonSupport(FAIL_ON_UNKNOW_PROPERTIES_DISABLE, WRAP_ROOT_VALUE_DISABLE,
                UNWRAP_ROOT_VALUE_DISABLE, FAIL_ON_NULL_FOR_PRIMITIVES_DISABLE).getDefaultMapper());
    }

    /**
     * @return {@link Rest5Client} il client HTTP verso i nodi di ElasticSearch
     * @throws Exception se il truststore non si carica
     */
    @Bean
    public Rest5Client elasticSearchRestClient() throws Exception {
        logger.debug("#####################Creo il Rest5Client con: {}\n", this.elasticSearchRSBaseConfiguration);
        Timeout connectionTimeout = Timeout.of(this.elasticSearchRSBaseConfiguration.getConnectionTimeout(), MILLISECONDS);
        Timeout socketTimeout = Timeout.of(this.elasticSearchRSBaseConfiguration.getSocketTimeout(), MILLISECONDS);
        Rest5ClientBuilder builder = Rest5Client.builder(this.elasticSearchRSBaseConfiguration.getHttpHosts())
                .setRequestConfigCallback(requestConfig -> requestConfig
                        .setConnectionRequestTimeout(connectionTimeout)
                        .setResponseTimeout(socketTimeout)
                        .setMaxRedirects(this.elasticSearchRSBaseConfiguration.getMaxRedirects()))
                .setConnectionConfigCallback(connectionConfig -> connectionConfig
                        .setConnectTimeout(connectionTimeout)
                        .setSocketTimeout(socketTimeout))
                .setConnectionManagerCallback(connectionManager -> connectionManager
                        .setMaxConnTotal(this.elasticSearchRSBaseConfiguration.getMaxTotalConnections())
                        .setMaxConnPerRoute(this.elasticSearchRSBaseConfiguration.getDefaultMaxPerRoute()))
                .setHttpClientConfigCallback(httpClient -> httpClient
                        .setIOReactorConfig(IOReactorConfig.custom().setSoTimeout(socketTimeout).build())
                        .setThreadFactory(new GPDecoratorThreadFactory(new GPDefaultThreadFactory("GPElasticSearchRestTask#", FALSE, NORM_PRIORITY))));
        Header authorization = this.elasticSearchRSBaseConfiguration.getAuthConfig().toAuthorizationHeader();
        if (authorization != null) {
            logger.debug("#####################Autenticazione Basic con l'utente {}\n", this.elasticSearchRSBaseConfiguration.getAuthConfig().getUserName());
            builder.setDefaultHeaders(new Header[]{authorization});
        }
        GPElasticSearchRSSslConfiguration sslConfig = this.elasticSearchRSBaseConfiguration.getSslConfig();
        if (sslConfig.isSetSecureSocketLayer()) {
            builder.setSSLContext(this.createSSLContext(sslConfig));
        }
        return builder.build();
    }

    /**
     * @param theElasticSearchRestClient il client HTTP
     * @param theElasticSearchJsonpMapper il mapper del client
     * @return {@link ElasticsearchTransport}
     */
    @Bean
    public ElasticsearchTransport elasticSearchTransport(@Qualifier(value = "elasticSearchRestClient") Rest5Client theElasticSearchRestClient,
            @Qualifier(value = "elasticSearchJsonpMapper") JsonpMapper theElasticSearchJsonpMapper) {
        return new Rest5ClientTransport(theElasticSearchRestClient, theElasticSearchJsonpMapper);
    }

    /**
     * @param theElasticSearchTransport il trasporto
     * @return {@link ElasticsearchClient}
     */
    @Bean
    public ElasticsearchClient elasticSearchClient(@Qualifier(value = "elasticSearchTransport") ElasticsearchTransport theElasticSearchTransport) {
        return new ElasticsearchClient(theElasticSearchTransport);
    }

    /**
     * @param theElasticSearchTransport il trasporto, lo stesso del client sincrono
     * @return {@link ElasticsearchAsyncClient}
     */
    @Bean
    public ElasticsearchAsyncClient elasticSearchAsyncClient(@Qualifier(value = "elasticSearchTransport") ElasticsearchTransport theElasticSearchTransport) {
        return new ElasticsearchAsyncClient(theElasticSearchTransport);
    }

    /**
     * Il truststore si cerca prima nel classpath, come nel connettore per ElasticSearch 7, e poi sul disco: così si
     * può usare anche il certificato della CA generato da ElasticSearch.
     *
     * @param theSslConfig la configurazione SSL
     * @return {@link SSLContext}
     * @throws Exception se il truststore non si trova o non si apre
     */
    SSLContext createSSLContext(@Nonnull(when = NEVER) GPElasticSearchRSSslConfiguration theSslConfig) throws Exception {
        checkArgument(theSslConfig != null, "Il parametro sslConfig non deve essere null.");
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (InputStream inputStream = this.openTrustStore(theSslConfig.getKeyStorePath())) {
            trustStore.load(inputStream, theSslConfig.getKeyStorePassword().toCharArray());
        }
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
        return sslContext;
    }

    /**
     * @param theKeyStorePath il percorso nel classpath o sul disco
     * @return {@link InputStream}
     * @throws Exception se il truststore non si trova
     */
    InputStream openTrustStore(@Nonnull(when = NEVER) String theKeyStorePath) throws Exception {
        checkArgument(theKeyStorePath != null, "Il parametro keyStorePath non deve essere null.");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(theKeyStorePath);
        if (inputStream != null) {
            return inputStream;
        }
        Path path = Path.of(theKeyStorePath);
        checkArgument(Files.isReadable(path), "Il truststore " + theKeyStorePath + " non è né nel classpath né sul disco.");
        return Files.newInputStream(path);
    }
}
