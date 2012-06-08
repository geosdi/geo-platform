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
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class CSWConnectionTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final static String CSW_HOST = "catalog.geosdi.org";
    private final static String CSW_PATH = "/geonetwork/srv/en/csw";
    //
    private final static String SITDPC_HOST = "snipc.protezionecivile.it";
    private final static String SITDPC_PATH = "geoportal/csw/discovery";

    @Before
    public void setUp() {
//        System.setProperty("javax.net.ssl.trustStore", "\\keystore.jks");
//        System.setProperty("javax.net.debug", "ssl");
    }
//    
//    @After
//    public void tearDown() {
//    }

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
            logger.error("\n@@@@@@@@@@@@@@@@\n{}\n@@@@@@@@@@@@@@@@", ex.getMessage());
        }
    }

    @Test
    public void testGetCapabilitiesRequestHttpsWorkaround() {
        try {
            HttpClient client = new DefaultHttpClient();

            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            qparams.add(new BasicNameValuePair("SERVICE", "CSW"));
            qparams.add(new BasicNameValuePair("REQUEST", "GetCapabilities"));

            URI uri = URIUtils.createURI("https", SITDPC_HOST, 443, SITDPC_PATH,
                    URLEncodedUtils.format(qparams, Consts.UTF_8), null);

            HttpGet get = new HttpGet(uri);

            // Workaround for HTTP authentication via header
            String authStr = "geosdi:0x,frank,0x";
            String authEncoded = Base64.encodeBase64URLSafeString(authStr.getBytes());
            get.setHeader("Authorization", "Basic " + authEncoded);

            HttpResponse response = client.execute(get);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream content = entity.getContent();

                String output = new Scanner(content).useDelimiter("\\A").next();
                logger.info("************************* {}", output);
            }

        } catch (Exception ex) {
            logger.error("\n@@@@@@@@@@@@@@@@\n{}\n@@@@@@@@@@@@@@@@", ex.getMessage());
        }
    }
    /**
     * TODO Use HttpComponets API for authentication.
     */
//    @Test
//    public void testGetCapabilitiesRequestHttps() {
//        try {
//
//            AuthScope authScope = new AuthScope(SITDPC_HOST, 443);
////            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("acaralla", "Passw0rd");
//            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("geosdi", "0x,frank,0x");
//
//            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(authScope, credentials);
//
//            DefaultHttpClient client = new DefaultHttpClient();
//            client.setCredentialsProvider(credentialsProvider);
//
//            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
//            qparams.add(new BasicNameValuePair("SERVICE", "CSW"));
//            qparams.add(new BasicNameValuePair("REQUEST", "GetCapabilities"));
//
//            URI uri = URIUtils.createURI("https", SITDPC_HOST, 443, SITDPC_PATH,
//                    URLEncodedUtils.format(qparams, Consts.UTF_8), null);
//
//            HttpGet get = new HttpGet(uri);
//
//            HttpResponse response = client.execute(get);
//
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                InputStream content = entity.getContent();
//
//                String output = new Scanner(content).useDelimiter("\\A").next();
//                logger.info("************************* {}", output);
//            }
//
//        } catch (Exception ex) {
//            logger.error("\n@@@@@@@@@@@@@@@@\n{}\n@@@@@@@@@@@@@@@@", ex.getMessage());
//        }
//    }
}
