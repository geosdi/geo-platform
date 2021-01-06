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
package org.geosdi.geoplatform.connector.server;

import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfig;
import org.geosdi.geoplatform.connector.server.handler.ConnectorHttpRequestRetryHandler;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.support.httpclient.proxy.HttpClientProxyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.http.client.config.RequestConfig.custom;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public abstract class GPAbstractServerConnector implements GPServerConnector {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final URL url;
    private final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    private final GPSecurityConnector securityConnector;
    private final GPPooledConnectorConfig pooledConnectorConfig;
    private final HttpClientProxyConfiguration proxyConfiguration;
    private volatile CloseableHttpClient httpClient;

    /**
     * @param theUrl
     * @param theSecurityConnector
     */
    protected GPAbstractServerConnector(URL theUrl, GPSecurityConnector theSecurityConnector) {
        this(theUrl, theSecurityConnector, DEFAULT_POOLED, null);
    }

    /**
     * @param theUrl
     * @param theSecurityConnector
     * @param theProxyConfiguration
     */
    protected GPAbstractServerConnector(URL theUrl, GPSecurityConnector theSecurityConnector,
            HttpClientProxyConfiguration theProxyConfiguration) {
        this(theUrl, theSecurityConnector, DEFAULT_POOLED, theProxyConfiguration);
    }

    /**
     * @param theUrl
     * @param thePooledConnectorConfig
     */
    protected GPAbstractServerConnector(URL theUrl, GPPooledConnectorConfig thePooledConnectorConfig) {
        this(theUrl, null, thePooledConnectorConfig, null);
    }

    /**
     * @param theUrl
     * @param theSecurityConnector
     * @param thePooledConnectorConfig
     */
    protected GPAbstractServerConnector(URL theUrl, GPSecurityConnector theSecurityConnector,
            GPPooledConnectorConfig thePooledConnectorConfig) {
        this(theUrl, theSecurityConnector, thePooledConnectorConfig, null);
    }

    /**
     * @param theUrl
     * @param theSecurityConnector
     * @param thePooledConnectorConfig
     * @param theProxyConfiguration
     */
    protected GPAbstractServerConnector(URL theUrl, GPSecurityConnector theSecurityConnector,
            GPPooledConnectorConfig thePooledConnectorConfig, HttpClientProxyConfiguration theProxyConfiguration) {
        this.url = theUrl;
        this.securityConnector = theSecurityConnector;
        this.pooledConnectorConfig = ((thePooledConnectorConfig != null)
                ? thePooledConnectorConfig : DEFAULT_POOLED);
        this.proxyConfiguration = theProxyConfiguration;
    }

    /**
     * @return {@link URL}
     */
    @Override
    public URL getURL() {
        return this.url;
    }

    /**
     * @return {@link GPSecurityConnector}
     */
    @Override
    public GPSecurityConnector getSecurityConnector() {
        return this.securityConnector;
    }

    /**
     * @return {@link URI}
     */
    @Override
    public URI getURI() {
        try {
            return this.url.toURI();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            logger.error("URISyntaxException @@@@@@@@@@@@@@@ {}", ex.getMessage());
        }
        return null;
    }

    /**
     * @return {@link CloseableHttpClient}
     */
    @Override
    public CloseableHttpClient getClientConnection() {
        return httpClient = (httpClient != null)
                ? httpClient : proxyConfiguration != null
                ? proxyConfiguration.isUseProxy()
                ? configureProxy() : createDefaultHttpClient()
                : createDefaultHttpClient();
    }

    @Override
    public void dispose() throws Exception {
        this.httpClient.close();
    }

    @Override
    public CredentialsProvider getCredentialsProvider() {
        return this.credentialsProvider;
    }

    /**
     * <p>
     * Analyzes the url of the server by eliminating everything that comes after
     * the ? character </p>
     *
     * @param urlServer
     * @return String
     */
    protected static URL analyzesServerURL(String urlServer) {
        checkArgument((urlServer != null) && !(urlServer.trim().isEmpty()), "The Parameter urlServer must not be null or an empty string.");
        try {
            int index = urlServer.indexOf("?");
            return ((index != -1) ? new URL(urlServer.substring(0, index)) : new URL(urlServer));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link CloseableHttpClient}
     */
    protected CloseableHttpClient configureProxy() {
        logger.debug("SetUp Proxy Configuration @@@@@@@@@@@@@@@@ {}\n", proxyConfiguration);
        String host = this.url.getHost();
        logger.debug("\n\n HOST TO MATCH ######################### {}\n\n", host);

        if (this.proxyConfiguration.matchServerURL(host)) {
            logger.debug("@@@@@@@@@@@ Skipping Proxy Configuration for Server : {}\n", host);
            return createDefaultHttpClient();
        } else {
            logger.debug("@@@@@@@@@@@@@@@ Setting UP Proxy Configuration for Server : {}\n", host);
            /**
             * TODO HERE THE CODE FOR PROXY AUTHENTICATION *
             */
            HttpHost proxy = new HttpHost(proxyConfiguration.getProxyUrl(), proxyConfiguration.getProxyPort());
            return HttpClients
                    .custom()
                    .setConnectionManager(createClientConnectionManager())
                    .setProxy(proxy)
                    .build();
        }
    }

    /**
     * @return {@link CloseableHttpClient}
     */
    protected CloseableHttpClient createDefaultHttpClient() {
        return HttpClients
                .custom()
                .setConnectionManager(createClientConnectionManager())
                .setDefaultRequestConfig(custom()
                        .setConnectTimeout(this.pooledConnectorConfig.getConnectionTimeout())
                        .setConnectionRequestTimeout(this.pooledConnectorConfig.getConnectionTimeout())
                        .setSocketTimeout(this.pooledConnectorConfig.getConnectionTimeout())
                        .setMaxRedirects(this.pooledConnectorConfig.getMaxRedirect())
                        .build())
                .setDefaultCredentialsProvider(credentialsProvider)
                .setRetryHandler(new ConnectorHttpRequestRetryHandler(5))
                .build();
    }

    /**
     * @return {@link HttpClientConnectionManager}
     */
    protected HttpClientConnectionManager createClientConnectionManager() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build());
        cm.setMaxTotal(this.pooledConnectorConfig.getMaxTotalConnections());
        cm.setDefaultMaxPerRoute(this.pooledConnectorConfig.getDefaultMaxPerRoute());
        return cm;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPAbstractServerConnector other = (GPAbstractServerConnector) obj;
        return !(this.url != other.url && (this.url == null || !this.url.equals(other.url)));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.url != null ? this.url.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {" + "Server Url = " + url
                + ", securityConnector = " + securityConnector + '}';
    }
}