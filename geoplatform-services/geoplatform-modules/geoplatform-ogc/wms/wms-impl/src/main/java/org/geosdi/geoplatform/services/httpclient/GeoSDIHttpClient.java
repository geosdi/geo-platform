package org.geosdi.geoplatform.services.httpclient;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.geotools.data.ows.HTTPResponse;
import org.geotools.data.ows.MultithreadedHttpClient;
import org.geotools.util.logging.Logging;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private Map<String, String> headers;

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

        LOGGER.info("HEADERS : " + getHeaders());
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            getMethod.setRequestHeader(entry.getKey(), entry.getValue());
        }

        int responseCode = this.executeMethod(getMethod);
        if (200 != responseCode) {
            getMethod.releaseConnection();
            throw new IOException("Server returned HTTP error code " + responseCode + " for URL " + url.toExternalForm());
        } else {
            return new GeoSDIHttpClient.HttpMethodResponse(getMethod);
        }
    }

    public HTTPResponse post(URL url, InputStream postContent, String postContentType) throws IOException {
        PostMethod postMethod = new PostMethod(url.toExternalForm());
        System.out.println("Inject OpenAM Cookie");
        List<String> values = this.headers.entrySet().stream()
                .map(entry -> String.join("=", entry.getKey(), entry.getValue()))
                .collect(toList());
        postMethod.setRequestHeader("Cookie", String.join(";", values));

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

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}

