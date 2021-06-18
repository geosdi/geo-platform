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
package org.geosdi.geoplatform.gui.server;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.util.Base64;
import java.util.Enumeration;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPServerProxy extends HttpServlet {

    private static final long serialVersionUID = -8296565872107565884L;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.log("@@@@@@@@@@@@@@@@@@@@@Called " + this.getClass().getSimpleName() + "#doGet.");
        try {
            if ((request.getParameter("targetURL") != null) && (request.getParameter("targetURL") != "")) {
                CloseableHttpClient httpClient =  HttpClients
                        .custom()
                        .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
                        .setConnectionManager(createClientConnectionManager())
                        .setDefaultCredentialsProvider(new BasicCredentialsProvider())
                        .build();
                String targetUrl = request.getParameter("targetURL");
                URIBuilder uriBuilder = new URIBuilder(targetUrl);
                Enumeration<String> parametersName = request.getParameterNames();
                while (parametersName.hasMoreElements()) {
                    String requestParamName = parametersName.nextElement();
                    switch (requestParamName) {
                        case "targetURL":
                        case "v":
                        case "p":
                            break;
                        default:
                            uriBuilder.addParameter(requestParamName, request.getParameter(requestParamName));
                    }
                }
                URI uri = uriBuilder.build();
                this.log("############################URI to call : "+ uri.toString());
                HttpGet httpGet = new HttpGet(uri);
                if(((request.getParameter("v") != null) && (request.getParameter("v") != "")) && ((request.getParameter("p") != null) && (request.getParameter("p") != ""))) {
                    String userName = request.getParameter("v");
                    String password = request.getParameter("p");
                    String userpass = userName + ":" + password;
                    this.log("@@@@@@@@@@@@@@@@@Trying to inject basicAuth with parameter :" + userpass);
                    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
                    httpGet.setHeader(HttpHeaders.AUTHORIZATION, basicAuth);
                }
                CloseableHttpResponse httpClientResponse = httpClient.execute(httpGet);
                this.log("###########################STATUS_CODE :" + httpClientResponse.getStatusLine());
                if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
                    String exceptionMessage = IOUtils
                            .toString(httpClientResponse.getEntity().getContent(), UTF_8);
                    this.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ExecutionError : " + exceptionMessage);
                    response.setStatus(500);
                } else {
                    OutputStream ostream = response.getOutputStream();
                    copy(httpClientResponse.getEntity().getContent(), ostream);
                    this.log("#########################EXECUTE SUCCESS \n\n");
                }
            }
        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.log("@@@@@@@@@@@@@@@@@@@@@Called " + this.getClass().getSimpleName()+ "#doPost.");
        try {
            if ((request.getParameter("targetURL") != null) && (request.getParameter("targetURL") != "")) {
                CloseableHttpClient httpClient = HttpClients
                        .custom()
                        .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
                        .setConnectionManager(createClientConnectionManager())
                        .setDefaultCredentialsProvider(new BasicCredentialsProvider())
                        .build();
                String targetUrl = request.getParameter("targetURL");
                URIBuilder uriBuilder = new URIBuilder(targetUrl);
                Enumeration<String> parametersName = request.getParameterNames();
                while (parametersName.hasMoreElements()) {
                    String requestParamName = parametersName.nextElement();
                    switch (requestParamName) {
                        case "targetURL":
                        case "v":
                        case "p":
                            break;
                        default:
                            uriBuilder.addParameter(requestParamName, request.getParameter(requestParamName));
                    }
                }
                URI uri = uriBuilder.build();
                this.log("############################URI to call : " + uri.toString());
                HttpPost httpPost = new HttpPost(uri);
                if (request.getHeader("Content-Type") != null) {
                    httpPost.setHeader("Content-Type", request.getContentType());
                }
                if(((request.getParameter("v") != null) && (request.getParameter("v") != "")) && ((request.getParameter("p") != null) && (request.getParameter("p") != ""))) {
                    String userName = request.getParameter("v");
                    String password = request.getParameter("p");
                    String userpass = userName + ":" + password;
                    this.log("@@@@@@@@@@@@@@@@@Trying to inject basicAuth with parameter :" + userpass);
                    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
                    httpPost.setHeader(HttpHeaders.AUTHORIZATION, basicAuth);
                }
                int contentLength = request.getContentLength();
                if (contentLength > 0) {
                    httpPost.setEntity(new StringEntity(new BufferedReader(new InputStreamReader(request.getInputStream(), UTF_8))
                            .lines()
                            .collect(joining("\n"))));
                }
                CloseableHttpResponse httpClientResponse = httpClient.execute(httpPost);
                this.log("###########################STATUS_CODE :" + httpClientResponse.getStatusLine().getStatusCode());
                if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
                    String exceptionMessage = IOUtils
                            .toString(httpClientResponse.getEntity().getContent(), UTF_8);
                    this.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ExecutionError :" + exceptionMessage);
                    response.setStatus(500);
                } else {
                    OutputStream ostream = response.getOutputStream();
                    copy(httpClientResponse.getEntity().getContent(), ostream);
                    this.log("#########################EXECUTE SUCCESS");
                }
            }
        } catch (Exception ex) {
            response.setStatus(500);
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.log("@@@@@@@@@@@@@@@@@@Called " + this.getClass().getSimpleName() + "#destroy");
    }

    /**
     * @param istream
     * @param ostream
     * @throws Exception
     */
    private void copy(InputStream istream, OutputStream ostream) throws Exception {
        int bufferSize = 4 * 4 * 1024; // same buffer size as in Jetty utils (2*8192)
        byte[] buffer = new byte[bufferSize];
        int read;
        while ((read = istream.read(buffer)) != -1) {
            ostream.write(buffer, 0, read);
        }
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
}
