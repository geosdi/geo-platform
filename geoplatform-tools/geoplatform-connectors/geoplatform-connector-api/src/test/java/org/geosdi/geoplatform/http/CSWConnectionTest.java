/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.http;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.geosdi.geoplatform.configurator.crypt.GPPooledPBEStringEncryptorDecorator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml"})
public class CSWConnectionTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final static String CSW_HOST = "catalog.geosdi.org";
    private final static String CSW_PATH = "/geonetwork/srv/en/csw";
    //
    private final static String SITDPC_HOST = "snipc.protezionecivile.it"; // TODO parse URL
    private final static String SITDPC_PATH = "geoportal/csw/discovery"; // TODO parse URL
    //
    private @Value("${snipc_catalog_url}")
    String snipcUrl; // TODO parse URL
    private @Value("${snipc_catalog_username}")
    String snipcUsername;
    private @Value("${snipc_catalog_password}")
    String snipcPassword;
    //
    @Autowired
    private GPPooledPBEStringEncryptorDecorator encryptor;

    @Test
    public void testGetCapabilitiesRequest() {
        try {
            HttpClient client = new DefaultHttpClient();

            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            qparams.add(new BasicNameValuePair("SERVICE", "CSW"));
            qparams.add(new BasicNameValuePair("REQUEST", "GetCapabilities"));

            URI uri = URIUtils.createURI("http", CSW_HOST, -1, CSW_PATH,
                    URLEncodedUtils.format(qparams, "UTF-8"), null);

            HttpGet get = new HttpGet(uri);

            HttpResponse response = client.execute(get);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream content = entity.getContent();

                String output = new Scanner(content).useDelimiter("\\A").next();
                logger.info("************************* {}", output);
            }

        } catch (Exception ex) {
            logger.error("\n@@@@@@@@@@@@@@@@\n{}\n@@@@@@@@@@@@@@@@",
                    ex.getMessage());
        }
    }

    /**
     * HOWTO Have a GetCapabilities response.
     * <p/>
     * <u>1) Download the certificate authority:</u>
     * from <tt>https://snipc.protezionecivile.it/</tt> link
     * obtain <tt>snipc.protezionecivile.it.cer</tt> CA certificate file
     * <br/>
     * <u>2) Import the CA into Java Trusted Certs (default keystore):</u>
     * the default keystore is the file <tt>cacerts</tt> locate to
     * <tt>%JAVA_HOME%[/jre]/lib/security</tt> folder;
     * copy the certificate file into this folder and execute the command
     * <code>keytool -import -trustcacerts -alias SNIPC
     * -file snipc.protezionecivile.it.cer -keystore cacerts</code>
     * (the default keystore password is <tt>changeit</tt>)
     * <p/>
     * For list teh Trusted CA Certs execute the command
     * <code>keytool -list -v -keystore cacerts</code>
     */
    @Test
    public void testGetCapabilitiesRequestHttps() {
        try {
            HttpHost targetHost = new HttpHost(SITDPC_HOST, 443, "https"); // TODO parse URL

            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                    new UsernamePasswordCredentials(encryptor.decrypt(snipcUsername), encryptor.decrypt(snipcPassword)));

            DefaultHttpClient client = new DefaultHttpClient();
            client.setCredentialsProvider(credentialsProvider);

            // Create AuthCache instance with BASIC scheme object and add it to the local
            AuthCache authCache = new BasicAuthCache();
            authCache.put(targetHost, new BasicScheme());

            // Add AuthCache to the execution context
            HttpContext localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

            // Create URI
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            qparams.add(new BasicNameValuePair("SERVICE", "CSW"));
            qparams.add(new BasicNameValuePair("REQUEST", "GetCapabilities"));

            URI uri = URIUtils.createURI("https", targetHost.getHostName(),
                    targetHost.getPort(),
                    SITDPC_PATH, URLEncodedUtils.format(qparams, Consts.UTF_8), // TODO parse URL
                    null);

            HttpGet get = new HttpGet(uri);

            // HTTP GET
            HttpResponse response = client.execute(targetHost, get, localcontext);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream content = entity.getContent();

                String output = new Scanner(content).useDelimiter("\\A").next();
                logger.info("************************* {}", output);
            }

        } catch (Exception ex) {
            logger.error("\n@@@@@@@@@@@@@@@@\n{}\n@@@@@@@@@@@@@@@@",
                    ex.getMessage());
        }
    }
}
