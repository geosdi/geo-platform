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
package org.geosdi.geoplatform.experimental.el.rest.client;

import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base.GPElasticSearchRSBaseConfiguration;
import org.geosdi.geoplatform.threadpool.support.factory.GPDecoratorThreadFactory;
import org.geosdi.geoplatform.threadpool.support.factory.GPDefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.SecureRandom;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Thread.NORM_PRIORITY;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.http.impl.nio.reactor.IOReactorConfig.custom;
import static org.apache.http.nio.conn.NoopIOSessionStrategy.INSTANCE;
import static org.apache.http.nio.conn.ssl.SSLIOSessionStrategy.getDefaultStrategy;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestHighLeveClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRestHighLeveClientConfig.class);
    //
    private final GPElasticSearchRSBaseConfiguration elasticSearchRSBaseConfiguration;

    /**
     * @param theElasticSearchRSBaseConfiguration
     */
    GPElasticSearchRestHighLeveClientConfig(@Qualifier(value = "elasticSearchRSBaseConfiguration") GPElasticSearchRSBaseConfiguration theElasticSearchRSBaseConfiguration) {
        checkArgument(theElasticSearchRSBaseConfiguration != null, "The Parameter elasticSearchRestBaseConfiguration must not be null.");
        this.elasticSearchRSBaseConfiguration = theElasticSearchRSBaseConfiguration;
    }

    /**
     * @return {@link RestHighLevelClient}
     * @throws Exception
     */
    @Bean
    public RestHighLevelClient elasticSearchRestHighLevelClient() throws Exception {
        logger.trace("#####################Trying to build RestHighLevelClient with : {}\n", elasticSearchRSBaseConfiguration);
        RestClientBuilder restClientBuilder = RestClient.builder(elasticSearchRSBaseConfiguration.getHttpHosts());
        restClientBuilder.setRequestConfigCallback((requestConfigBuilder) -> requestConfigBuilder
                .setMaxRedirects(elasticSearchRSBaseConfiguration.getMaxRedirects())
                .setConnectTimeout(elasticSearchRSBaseConfiguration.getConnectionTimeout())
                .setConnectionRequestTimeout(elasticSearchRSBaseConfiguration.getConnectionTimeout())
                .setSocketTimeout(elasticSearchRSBaseConfiguration.getSocketTimeout()));
        restClientBuilder.setHttpClientConfigCallback((httpClientConfigCallback) -> {
            httpClientConfigCallback.setConnectionManager(this.createPoolingHttpClientConnectionManager());
            secure(httpClientConfigCallback);
            return httpClientConfigCallback;
        });
        return new RestHighLevelClient(restClientBuilder);
    }

    /**
     * @return {@link PoolingNHttpClientConnectionManager}
     */
    PoolingNHttpClientConnectionManager createPoolingHttpClientConnectionManager() {
        try {
            PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(new DefaultConnectingIOReactor(custom()
                    .setConnectTimeout(this.elasticSearchRSBaseConfiguration.getConnectionTimeout())
                    .setSoTimeout(this.elasticSearchRSBaseConfiguration.getSocketTimeout())
                    .build(), new GPDecoratorThreadFactory(new GPDefaultThreadFactory("GPElasticSearchRestTask#", FALSE, NORM_PRIORITY))), RegistryBuilder.<SchemeIOSessionStrategy>create()
                    .register("http", INSTANCE)
                    .register("https", getDefaultStrategy())
                    .build());
            cm.setMaxTotal(elasticSearchRSBaseConfiguration.getMaxTotalConnections());
            cm.setDefaultMaxPerRoute(elasticSearchRSBaseConfiguration.getDefaultMaxPerRoute());
            return cm;
        } catch (Exception ex) {
            logger.error("##########################IOReactorException : {}\n", ex.getMessage());
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param httpAsyncClientBuilder
     */
    void secure(HttpAsyncClientBuilder httpAsyncClientBuilder) {
        if (this.elasticSearchRSBaseConfiguration.getAuthConfig().isSetAuth()) {
            logger.debug("#####################Trying to setting Credential Provider with Auth : {}\n", this.elasticSearchRSBaseConfiguration
                    .getAuthConfig().toString());
            httpAsyncClientBuilder.setDefaultCredentialsProvider(elasticSearchRSBaseConfiguration.getAuthConfig()
                    .toCredentialProvider());
        }
        try {
            if (this.elasticSearchRSBaseConfiguration.getSslConfig().isSetSecureSocketLayer()) {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                TrustManagerFactory trustManagerFactory = TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                try (InputStream inputStream = Files.newInputStream(loadTrustStore())) {
                    keyStore.load(inputStream, this.elasticSearchRSBaseConfiguration.getSslConfig().getKeyStorePassword().toCharArray());
                }
                trustManagerFactory.init(keyStore);
                sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                httpAsyncClientBuilder.setSSLContext(sslContext);
            }
        } catch (Exception ex) {
            logger.error("##########################NoSuchAlgorithmException : {}\n", ex.getMessage());
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link FileInputStream}
     * @throws Exception
     */
    Path loadTrustStore() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.elasticSearchRSBaseConfiguration.getSslConfig().getKeyStorePath());
        File targetFile = createTempFile("gp_elasticsearch_rest_trust_store", "ts").toFile();
        copy(inputStream, targetFile.toPath(), REPLACE_EXISTING);
        return targetFile.toPath();
    }
}