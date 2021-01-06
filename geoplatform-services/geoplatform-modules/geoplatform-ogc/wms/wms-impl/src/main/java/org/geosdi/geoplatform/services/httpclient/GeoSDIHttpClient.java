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
package org.geosdi.geoplatform.services.httpclient;

import com.google.common.collect.Lists;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geotools.data.ows.HTTPResponse;
import org.geotools.data.ows.MultithreadedHttpClient;
import org.geotools.util.logging.Logging;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import static java.util.stream.Collectors.toList;

public class GeoSDIHttpClient extends MultithreadedHttpClient {
    private static final Logger LOGGER = Logging.getLogger(GeoSDIHttpClient.class);
    private MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
    private HttpClient client;
    private String user;
    private String password;
    private boolean tryGzip = true;
    private HostConfiguration hostConfigNoProxy;
    private Set<String> nonProxyHosts = new HashSet();

    private List<WMSHeaderParam> headers;

    public GeoSDIHttpClient() {

        System.setProperty("jsse.enableSNIExtension", "false");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setSoTimeout(30000);
        params.setConnectionTimeout(30000);
        params.setMaxTotalConnections(6);
        params.setDefaultMaxConnectionsPerHost(6);
        this.connectionManager.setParams(params);

        this.client = this.createHttpClient();

        //this.applySystemProxySettings();
    }

    HttpClient createHttpClient() {
        return new HttpClient(this.connectionManager);
    }


    public HTTPResponse get(URL url) throws IOException {
        LOGGER.info("Inject OpenAM Cookie Method [GET]");

        GetMethod getMethod = new GetMethod(url.toExternalForm());
        getMethod.setDoAuthentication(this.user != null && this.password != null);
        if (this.tryGzip) {
            getMethod.setRequestHeader("Accept-Encoding", "gzip");
        }

        LOGGER.info("HEADERS : " + this.headers);
        if (this.headers != null)
            for (WMSHeaderParam wmsHeaderParam : this.headers) {
                getMethod.setRequestHeader(wmsHeaderParam.getHeaderKey(), wmsHeaderParam.getHeaderValue());
            }

        int responseCode = this.executeMethod(getMethod);
        if (200 != responseCode) {
            getMethod.releaseConnection();
            throw new IOException("Server returned HTTP error code " + responseCode + " for URL " + url.toExternalForm());
        } else {
            return new GeoSDIHttpClient.HttpMethodResponse(getMethod);
        }
    }

    /**
     * @param url
     * @param postContent
     * @param postContentType
     * @return {@link HTTPResponse}
     * @throws IOException
     */
    public HTTPResponse post(URL url, InputStream postContent, String postContentType) throws IOException {
        PostMethod postMethod = new PostMethod(url.toExternalForm());
        LOGGER.info("Inject OpenAM Cookie");
        if (this.headers != null) {
            List<String> values = this.headers.stream()
                    .map(value -> String.join("=", value.getHeaderKey(), value.getHeaderValue()))
                    .collect(toList());
            postMethod.setRequestHeader("Cookie", String.join(";", values));
        }

        RequestEntity requestEntity = new InputStreamRequestEntity(postContent);
        postMethod.setRequestEntity(requestEntity);
        int responseCode = this.executeMethod(postMethod);
        if (200 != responseCode) {
            postMethod.releaseConnection();
            throw new IOException("Server returned HTTP error code " + responseCode + " for URL " + url.toExternalForm());
        } else {
            return new GeoSDIHttpClient.HttpMethodResponse(postMethod);
        }
    }
    
    private int executeMethod(HttpMethod method) throws IOException, HttpException {
        String host = method.getURI().getHost();
        if (host != null && this.nonProxyHosts.contains(host.toLowerCase())) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine("Bypassing proxy config due to nonProxyHosts for " + method.getURI().toString());
            }

            return this.client.executeMethod(this.hostConfigNoProxy, method);
        } else {
            return this.client.executeMethod(method);
        }
    }

    private static class HttpMethodResponse implements HTTPResponse {
        private HttpMethod methodResponse;
        private InputStream responseBodyAsStream;

        public HttpMethodResponse(HttpMethod methodResponse) {
            this.methodResponse = methodResponse;
        }

        public void dispose() {
            if (this.responseBodyAsStream != null) {
                try {
                    this.responseBodyAsStream.close();
                } catch (IOException var2) {
                    ;
                }
            }

            if (this.methodResponse != null) {
                this.methodResponse.releaseConnection();
                this.methodResponse = null;
            }

        }

        public String getContentType() {
            return this.getResponseHeader("Content-Type");
        }

        public String getResponseHeader(String headerName) {
            Header responseHeader = this.methodResponse.getResponseHeader(headerName);
            return responseHeader == null ? null : responseHeader.getValue();
        }

        public InputStream getResponseStream() throws IOException {
            if (this.responseBodyAsStream == null) {
                this.responseBodyAsStream = this.methodResponse.getResponseBodyAsStream();
                Header header = this.methodResponse.getResponseHeader("Content-Encoding");
                if (header != null && "gzip".equals(header.getValue())) {
                    this.responseBodyAsStream = new GZIPInputStream(this.responseBodyAsStream);
                }
            }

            return this.responseBodyAsStream;
        }

        public String getResponseCharset() {
            String responseCharSet = null;
            if (this.methodResponse instanceof HttpMethodBase) {
                responseCharSet = ((HttpMethodBase) this.methodResponse).getResponseCharSet();
            }

            return responseCharSet;
        }
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
}