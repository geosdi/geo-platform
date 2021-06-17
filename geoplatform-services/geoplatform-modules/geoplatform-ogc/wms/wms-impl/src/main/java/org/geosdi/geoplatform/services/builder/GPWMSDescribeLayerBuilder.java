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

import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.services.response.GPLayerTypeResponse;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.net.URLDecoder.decode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSDescribeLayerBuilder {

    /**
     * @param theServerURL
     * @return {@link GPWMSDescribeLayerBuilder}
     */
    GPWMSDescribeLayerBuilder withServerURL(@Nonnull(when = NEVER) String theServerURL);

    /**
     * @param theLayerName
     * @return {@link GPWMSDescribeLayerBuilder}
     */
    GPWMSDescribeLayerBuilder withLayerName(@Nonnull(when = NEVER) String theLayerName);

    /**
     * @param theWMSVersion
     * @return {@link GPWMSDescribeLayerBuilder}
     */
    GPWMSDescribeLayerBuilder withWMSVersion(@Nullable WMSVersion theWMSVersion);

    /**
     * @return {@link GPLayerTypeResponse}
     * @throws Exception
     */
    GPLayerTypeResponse build() throws Exception;

    class WMSDescribeLayerBuilder implements GPWMSDescribeLayerBuilder {

        private static final Logger logger = LoggerFactory.getLogger(WMSDescribeLayerBuilder.class);
        //
        private String serverURL;
        private String layerName;
        private WMSVersion wmsVersion;

        private WMSDescribeLayerBuilder() {
            System.setProperty("jsse.enableSNIExtension", "false");
            System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        }

        /**
         * @return {@link GPWMSDescribeLayerBuilder}
         */
        public static GPWMSDescribeLayerBuilder wmsDescribeLayerBuilder() {
            return new WMSDescribeLayerBuilder();
        }

        /**
         * @param theServerURL
         * @return {@link GPWMSDescribeLayerBuilder}
         */
        @Override
        public GPWMSDescribeLayerBuilder withServerURL(@Nonnull(when = NEVER) String theServerURL) {
            this.serverURL = theServerURL;
            return self();
        }

        /**
         * @param theLayerName
         * @return {@link GPWMSDescribeLayerBuilder}
         */
        @Override
        public GPWMSDescribeLayerBuilder withLayerName(@Nonnull(when = NEVER) String theLayerName) {
            this.layerName = theLayerName;
            return self();
        }

        /**
         * @param theWMSVersion
         * @return {@link GPWMSDescribeLayerBuilder}
         */
        @Override
        public GPWMSDescribeLayerBuilder withWMSVersion(@Nullable WMSVersion theWMSVersion) {
            this.wmsVersion = theWMSVersion;
            return self();
        }

        /**
         * @return {@link GPLayerTypeResponse}
         * @throws Exception
         */
        @Override
        public GPLayerTypeResponse build() throws Exception {
            InputStream inputStream = this.internalBuild();
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", FALSE);
            spf.setFeature("http://xml.org/sax/features/validation", FALSE);
            XMLReader xmlReader = spf.newSAXParser().getXMLReader();
            InputSource inputSource = new InputSource(new InputStreamReader(inputStream));
            SAXSource source = new SAXSource(xmlReader, inputSource);
            JAXBContext jaxbContext = JAXBContext.newInstance(WMSDescribeLayerResponse.class);
            WMSDescribeLayerResponse describeLayerResponse = (WMSDescribeLayerResponse) jaxbContext.createUnmarshaller().unmarshal(source);
            return new GPLayerTypeResponse(describeLayerResponse);
        }

        /**
         * @return {@link InputStream}
         * @throws Exception
         */
        protected InputStream internalBuild() throws Exception {
            checkArgument((serverURL != null) && !(serverURL.trim().isEmpty()), "The Parameter serverURL must not be null or an empty string.");
            checkArgument((layerName != null) && !(layerName.trim().isEmpty()), "The Parameter layerName must not be null or an empty string.");
            logger.debug("###########################TRYING TO RETRIEVE LAYER_TYPE with serverURL : {} - layerName : {}\n", serverURL, layerName);
            int index = serverURL.indexOf("?");
            String version = "&version=".concat((this.wmsVersion != null) ? this.wmsVersion.getVersion() : "1.1.1");
            String decribeLayerUrl = decode(serverURL.concat(index != -1 ? "&service=WMS&request=DescribeLayer" + version + "&layers=" : "?service=WMS&request=DescribeLayer" + version + "&layers=").concat(layerName), UTF_8.name());
            logger.debug("#########################DESCRIBE_LAYER_URL : {}\n", decribeLayerUrl);
            CloseableHttpClient httpClient = HttpClients.custom().build();
            HttpGet httpGet = new HttpGet(decribeLayerUrl);
            CloseableHttpResponse httpClientResponse = httpClient.execute(httpGet);
            logger.trace("###########################STATUS_CODE : {}\n\n", httpClientResponse.getCode());
            if (httpClientResponse.getCode() != 200) {
                String exceptionMessage = IOUtils.toString(httpClientResponse.getEntity().getContent(), UTF_8);
                logger.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@DescribeLayer Execution Error : {}\n", exceptionMessage);
                throw new IllegalStateException(exceptionMessage);
            }
            return httpClientResponse.getEntity().getContent();
        }

        /**
         * @return {@link GPWMSDescribeLayerBuilder}
         */
        protected final GPWMSDescribeLayerBuilder self() {
            return this;
        }
    }
}