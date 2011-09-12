/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.IOAuth2Service;
import org.geosdi.geoplatform.gui.utility.GoogleLoginEnum;
import org.springframework.stereotype.Service;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 */
@Service("oauth2Service")
public class OAuth2Service implements IOAuth2Service {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void googleUserLogin(String token, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(GoogleLoginEnum.GOOGLE_TOKEN.toString(), token);

//        System.out.println("### Token dalla sessione : " + session.getAttribute(GoogleLoginEnum.GOOGLE_TOKEN.toString()));
////        try {
////
////
//////        URL url = new URL("https://earthbuilder.google.com/13496919088645259843-03170733828027579281-4/wms/?request=GetCapabilities&access_token=" + token);
////
////        String url = "https://earthbuilder.google.com/13496919088645259843-03170733828027579281-4/wms/?request=GetCapabilities";
////
////        
////            HttpTransport transport = new NetHttpTransport();
////            HttpRequestFactory rf = transport.createRequestFactory();
////
////            // Make an authenticated request
////            GenericUrl shortenEndpoint = new GenericUrl("https://earthbuilder.google.com/13496919088645259843-03170733828027579281-4/wms/?request=GetCapabilities&access_token=" + token);
////            String requestBody =
////                    "{\"longUrl\":\"http://farm6.static.flickr.com/5281/5686001474_e06f1587ff_o.jpg\"}";
////            HttpRequest request = rf.buildGetRequest(shortenEndpoint);
////            request.headers.contentType = "application/json";
////            HttpResponse shortUrl = request.execute();
////            BufferedReader output = new BufferedReader(new InputStreamReader(shortUrl.getContent()));
////            System.out.println("Shorten Response: ");
////            for (String line = output.readLine(); line != null; line = output.readLine()) {
////                System.out.println(line);
////            }
////        } catch (IOException ioe) {
////            try {
////                System.out.println("Error 1: " + ((HttpResponseException) ioe).response.parseAsString());
////            } catch (IOException ioee) {
////                System.out.println("Error 2: " + ioee.toString());
////            }
////        }
    }
}
