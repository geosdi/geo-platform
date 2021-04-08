/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.services.builder;

import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.ssl.SSLContextBuilder;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geotools.data.ows.HTTPClient;
import org.geotools.data.ows.HTTPResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import static java.lang.Boolean.TRUE;
import static java.lang.Long.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.apache.http.client.protocol.HttpClientContext.create;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoSDIHttpClient5 implements HTTPClient {

    private static final Logger logger = LoggerFactory.getLogger(GeoSDIHttpClient5.class);
    //
    private final CloseableHttpClient httpClient;
    private String user;
    private String password;
    private boolean tryGzip = true;
    private final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    private List<WMSHeaderParam> headers;

    GeoSDIHttpClient5() {
        System.setProperty("jsse.enableSNIExtension", "false");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        this.httpClient = HttpClients
                .custom()
                .setConnectionManagerShared(TRUE)
                .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
                .setRequestExecutor(new HttpRequestExecutor())
                .setConnectionManager(createClientConnectionManager())
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(3 * 1000)
                        .setConnectionRequestTimeout(3 * 1000)
                        .setMaxRedirects(3)
                        .build())
                .setDefaultCredentialsProvider(this.credentialsProvider)
                .build();
    }

    /**
     * Executes an HTTP POST request against the provided URL, sending the contents of {@code
     * postContent} as the POST method body and setting the Content-Type request header to {@code
     * postContentType} if given, and returns the server response.
     *
     * <p>If an HTTP authentication {@link #getUser() user} and {@link #getPassword() password} is
     * set, the appropriate authentication HTTP header will be sent with the request.
     *
     * <p>If a {@link #getConnectTimeout() connection timeout} is set, the http connection will be
     * set to respect that timeout.
     *
     * <p>If a {@link #getReadTimeout() read timeout} is set, the http connection will be set to
     * respect it.
     *
     * @param url the URL against which to execute the POST request
     * @param postContent an input stream with the contents of the POST body
     * @param postContentType the MIME type of the contents sent as the request POST body, can be
     * {@code null}
     * @return the {@link HTTPResponse} encapsulating the response to the HTTP POST request
     */
    @Override
    public HTTPResponse post(URL url, InputStream postContent, String postContentType) throws IOException {
        HttpPost httpPost = new HttpPost(url.toExternalForm());
        logger.info("Inject OpenAM Cookie");
        if ((this.headers != null) && !(this.headers.isEmpty())) {
            List<String> values = this.headers.stream()
                    .map(value -> String.join("=", value.getHeaderKey(), value.getHeaderValue())).collect(toList());
            httpPost.setHeader("Cookie", String.join(";", values));
        }

        HttpEntity requestEntity = new InputStreamEntity(postContent, ContentType.create(postContentType));
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse response = null;
        if(((this.user != null) && !(this.user.trim().isEmpty())) && ((this.password != null) && !(this.password.trim().isEmpty()))) {
            try {
                URI uri = url.toURI();
                HttpClientContext localContext = create();
                AuthScope authScope = new AuthScope(uri.getHost(), uri.getPort());
                this.credentialsProvider.setCredentials(authScope, new UsernamePasswordCredentials(this.user, this.password));
                HttpHost targetHost = new HttpHost(uri.getHost(), this.retrieveNoSetPort(uri), uri.getScheme());
                AuthCache authCache = new BasicAuthCache();
                authCache.put(targetHost, new BasicScheme());
                localContext.setAuthCache(authCache);
                response = this.httpClient.execute(targetHost, httpPost, localContext);
            } catch (URISyntaxException ex) {
                throw new IOException("URISyntaxException error : " + ex.getMessage() + " for URL " + url.toExternalForm());
            }
        } else {
            response = this.httpClient.execute(httpPost);
        }
        int responseCode = response.getStatusLine().getStatusCode();
        if (200 != responseCode) {
            response.close();
            throw new IOException("Server returned HTTP error code " + responseCode + " for URL " + url.toExternalForm());
        } else {
            return new GeoSDIHttpClient5.HttpMethodResponse(response);
        }
    }

    /**
     * Executes an HTTP GET request against the provided URL and returns the server response.
     *
     * <p>If an HTTP authentication {@link #getUser() user} and {@link #getPassword() password} is
     * set, the appropriate authentication HTTP header will be sent with the request.
     *
     * <p>If a {@link #getConnectTimeout() connection timeout} is set, the http connection will be
     * set to respect that timeout.
     *
     * <p>If a {@link #getReadTimeout() read timeout} is set, the http connection will be set to
     * respect it.
     *
     * @param url the URL to retrieve
     * @return an {@link HTTPResponse} encapsulating the response to the HTTP GET request
     */
    @Override
    public HTTPResponse get(URL url) throws IOException {
        logger.info("Inject OpenAM Cookie Method [GET]");
        HttpGet httpGet = new HttpGet(url.toExternalForm());
        if (this.tryGzip) {
            httpGet.setHeader("Accept-Encoding", "gzip");
        }
        logger.info("HEADERS : " + this.headers);
        if ((this.headers != null) && !(this.headers.isEmpty())) {
            for (WMSHeaderParam wmsHeaderParam : this.headers) {
                httpGet.setHeader(wmsHeaderParam.getHeaderKey(), wmsHeaderParam.getHeaderValue());
            }
        }

        CloseableHttpResponse response = null;
        if(((this.user != null) && !(this.user.trim().isEmpty())) && ((this.password != null) && !(this.password.trim().isEmpty()))) {
            logger.trace("############################Using BasicAuth with user : {} - password : {}\n", this.user, this.password);
            try {
                URI uri = url.toURI();
                HttpClientContext localContext = create();
                AuthScope authScope = new AuthScope(uri.getHost(), uri.getPort());
                this.credentialsProvider.setCredentials(authScope, new UsernamePasswordCredentials(this.user, this.password));
                HttpHost targetHost = new HttpHost(uri.getHost(), this.retrieveNoSetPort(uri), uri.getScheme());
                AuthCache authCache = new BasicAuthCache();
                authCache.put(targetHost, new BasicScheme());
                localContext.setAuthCache(authCache);
                response = this.httpClient.execute(targetHost, httpGet, localContext);
            } catch (URISyntaxException ex) {
                throw new IOException("URISyntaxException error : " + ex.getMessage() + " for URL " + url.toExternalForm());
            }
        } else {
            response = this.httpClient.execute(httpGet);
        }

        int responseCode = response.getStatusLine().getStatusCode();
        if (200 != responseCode) {
            response.close();
            throw new IOException("Server returned HTTP error code " + responseCode + " for URL " + url.toExternalForm());
        } else {
            return new GeoSDIHttpClient5.HttpMethodResponse(response);
        }
    }

    /**
     * @return the HTTP BASIC Authentication user name, or {@code null} if not set
     */
    @Override
    public String getUser() {
        return this.user;
    }

    /**
     * @param theUser the HTTP BASIC Authentication user name
     */
    @Override
    public void setUser(String theUser) {
        this.user = theUser;
    }

    /**
     * @return the HTTP BASIC Authentication password, or {@code null} if not set
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @param thePassword the HTTP BASIC Authentication password
     */
    @Override
    public void setPassword(String thePassword) {
        this.password = thePassword;
    }

    /**
     * @return the tcp/ip connect timeout in seconds
     */
    @Override
    public int getConnectTimeout() {
        TimeUnit timeUnit = MINUTES;
        return valueOf(timeUnit.convert(3l, SECONDS)).intValue();
    }

    /**
     * @param connectTimeout tcp/ip connect timeout in seconds
     */
    @Override
    public void setConnectTimeout(int connectTimeout) {

    }

    /**
     * @return the socket read timeout in seconds
     */
    @Override
    public int getReadTimeout() {
        TimeUnit timeUnit = MINUTES;
        return valueOf(timeUnit.convert(3l, SECONDS)).intValue();
    }

    /**
     * @param readTimeout socket read timeout in seconds
     */
    @Override
    public void setReadTimeout(int readTimeout) {
    }

    /**
     * @param tryGZIP
     */
    @Override
    public void setTryGzip(boolean tryGZIP) {
        this.tryGzip = tryGZIP;
    }

    /**
     * @return whether gzip content encoding will be attempted; defaults to {@code false}
     */
    @Override
    public boolean isTryGzip() {
        return this.tryGzip;
    }

    /**
     * @return {@link List<WMSHeaderParam>}
     */
    public List<WMSHeaderParam> getHeaders() {
        return headers;
    }

    /**
     * @param headers
     */
    public void setHeaders(List<WMSHeaderParam> headers) {
        this.headers = ((headers != null) ? headers : Lists.newArrayList());
    }

    /**
     * @return {@link HttpClientConnectionManager}
     */
    HttpClientConnectionManager createClientConnectionManager() {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, (chain, authType) -> true);
            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSF).build());
            cm.setMaxTotal(10);
            cm.setDefaultMaxPerRoute(3);
            return cm;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    /**
     * If the URI don't have e port, retrieve the standard port wrt scheme
     * ["http" or "https"].
     */
    private int retrieveNoSetPort(URI uri) {
        int port = uri.getPort();
        if (port > 0) {
            return port;
        }
        String scheme = uri.getScheme();
        switch (scheme) {
            case "https":
                port = 443;
                break;
            case "http":
                port = 80;
                break;
            default:
                throw new IllegalStateException("Scheme doesn't recognize");
        }
        return port;
    }

    private static class HttpMethodResponse implements HTTPResponse {

        private CloseableHttpResponse response;
        private InputStream responseBodyAsStream;

        /**
         * @param response
         */
        HttpMethodResponse(CloseableHttpResponse response) {
            this.response = response;
        }

        public void dispose() {
            if (this.responseBodyAsStream != null) {
                try {
                    this.responseBodyAsStream.close();
                } catch (IOException var2) {
                }
            }

            if (this.response != null) {
                try {
                    this.response.close();
                } catch (Exception ex) {
                }
                this.response = null;
            }
        }

        public String getContentType() {
            return this.getResponseHeader("Content-Type");
        }

        public String getResponseHeader(String headerName) {
            try {
                Header responseHeader = this.response.getFirstHeader(headerName);
                return responseHeader == null ? null : responseHeader.getValue();
            } catch (Exception ex) {
                return null;
            }
        }

        public InputStream getResponseStream() throws IOException {
            if (this.responseBodyAsStream == null) {
                this.responseBodyAsStream = this.response.getEntity().getContent();
                try {
                    Header header = this.response.getFirstHeader("Content-Encoding");
                    if (header != null && "gzip".equals(header.getValue())) {
                        this.responseBodyAsStream = new GZIPInputStream(this.responseBodyAsStream);
                    }
                } catch (Exception ex) {

                }
            }

            return this.responseBodyAsStream;
        }

        /**
         * @return {@link String}
         */
        public String getResponseCharset() {
            Charset charset = UTF_8;
            String contentTypeHeader = this.getContentType();
            if (contentTypeHeader != null) {
                try {
                    ContentType contentType = ContentType.parse(contentTypeHeader);
                    Charset contentTypeCharset = contentType.getCharset();
                    return (contentTypeCharset != null) ? contentTypeCharset.name() : charset.name();
                } catch (UnsupportedCharsetException e) {
                    // ignore, use default charset UTF8
                }
            }
            return charset.name();
        }
    }
}