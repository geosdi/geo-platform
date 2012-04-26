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
package org.geosdi.geoplatform.cswconnector;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.geosdi.geoplatform.connector.jaxb.GPConnectorJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.provider.GeoPlatformJAXBContextRepository;
import org.geosdi.geoplatform.connector.protocol.GeoPlatformHTTP;
import org.geosdi.geoplatform.cswconnector.jaxb.CSWConnectorJAXBContext;
import org.geosdi.geoplatform.xml.csw.CSWServiceEnum;
import org.geosdi.geoplatform.xml.csw.v202.CapabilitiesType;
import org.geosdi.geoplatform.xml.csw.v202.GetCapabilitiesType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class CatalogCapabilitiesV202PostTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final static String CSW_HOST = "150.146.160.152";
    private final static String CSW_PATH = "/geonetwork/srv/en/csw";
    //
    private GPConnectorJAXBContext cswContext = GeoPlatformJAXBContextRepository.getProvider(
            CSWConnectorJAXBContext.CSW_CONTEXT_KEY);

    @Test
    public void testGetCapabilitiesPostRequest() throws JAXBException {
        try {
            HttpParams params = new BasicHttpParams();

            params.setParameter(GeoPlatformHTTP.CONTENT_TYPE_PARAMETER,
                    GeoPlatformHTTP.CONTENT_TYPE_XML);

            HttpClient client = new DefaultHttpClient();

            URI uri = URIUtils.createURI("http", CSW_HOST, -1, CSW_PATH,
                    null, null);

            HttpPost post = new HttpPost(uri);
            post.setParams(params);

            GetCapabilitiesType getCapType = new GetCapabilitiesType(
                    CSWServiceEnum.CSW);

            Marshaller m = cswContext.acquireMarshaller();
            Unmarshaller un = cswContext.acquireUnmarshaller();

            StringWriter w = new StringWriter();

            m.marshal(getCapType, w);

            StringEntity entity = new StringEntity(w.toString(),
                    GeoPlatformHTTP.CONTENT_TYPE_XML, HTTP.UTF_8);

            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                InputStream content = responseEntity.getContent();

                CapabilitiesType cap = ((JAXBElement<CapabilitiesType>) un.unmarshal(
                                        content)).getValue();

                logger.info(
                        "CSW GET_CAPABILITIES VERSION @@@@@@@@@@@@@@@@@@@@@@@ "
                        + cap.getVersion());

                EntityUtils.consume(responseEntity);
            }

        } catch (URISyntaxException ex) {
            logger.error("URISyntaxException @@@@@@@@@@@@@@@@@@@@@ " + ex);
        } catch (IOException io) {
            logger.error("IOException @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + io);
        }
    }
}
