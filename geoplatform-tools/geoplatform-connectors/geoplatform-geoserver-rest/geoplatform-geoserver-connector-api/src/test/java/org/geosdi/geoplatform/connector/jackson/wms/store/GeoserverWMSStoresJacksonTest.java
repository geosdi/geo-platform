/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson.wms.store;

import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStores;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GeoserverWMSStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GeoserverWMSStoreBody;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.emptyJacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeoserverWMSStoresJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverWMSStoresJacksonTest.class);

    @Test
    public void a_unmarshallWMSStoresFromJsonStringTest() throws Exception {
        GPGeoserverWMSStores wmsStores = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"wmsStores\":{\n"
                + "      \"wmsStore\":[\n"
                + "         {\n"
                + "            \"name\":\"altgs\",\n"
                + "            \"href\":\"http://localhost:8080/geoserver/rest/workspaces/cite/wmsstores/altgs.json\"\n"
                + "         }\n"
                + "      ]\n"
                + "   }\n"
                + "}"), GPGeoserverWMSStores.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@WMS_STORES : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(wmsStores));
    }

    @Test
    public void b_unmarshallWMSBaseStoreFromJsonStringTest() throws Exception {
        GPGeoserverStore wmsBaseStore = emptyJacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"name\":\"altgs\",\n"
                + "   \"href\":\"http://localhost:8080/geoserver/rest/workspaces/cite/wmsstores/altgs.json\"\n"
                + "}"), GPGeoserverStore.class);
        logger.info("##################WMS_BASE_STORE : \n{}\n", emptyJacksonSupport.getDefaultMapper().writeValueAsString(wmsBaseStore));
    }

    @Test
    public void c_unmarshallGPWMSStoreFromXmlStringTest() throws Exception {
        GeoserverWMSStore wmsStore = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<wmsStore>\n"
                        + "<name>remote</name>\n"
                        + "<description>description_test</description>\n"
                        + "<type>WMS</type>\n"
                        + "<enabled>true</enabled>\n"
                        + "<workspace>\n"
                        + "<name>sf</name>\n"
                        + "<atom:link xmlns:atom=\"http://www.w3.org/2005/Atom\" rel=\"alternate\" href=\"http://150.145.141.92/geoserver/rest/workspaces/sf.xml\" type=\"application/xml\"/>\n"
                        + "</workspace>\n"
                        + "<metadata>\n"
                        + "<entry key=\"useConnectionPooling\">false</entry>\n"
                        + "</metadata>\n"
                        + "<__default>false</__default>\n"
                        + "<dateCreated>2022-11-16 06:48:29.586 UTC</dateCreated>\n"
                        + "<dateModified>2022-11-16 06:49:23.494 UTC</dateModified>\n"
                        + "<capabilitiesURL>http://150.145.141.180/geoserver/ows?service=wms&amp;version=1.1.1&amp;request=GetCapabilities</capabilitiesURL>\n"
                        + "<maxConnections>6</maxConnections>\n"
                        + "<readTimeout>60</readTimeout>\n"
                        + "<connectTimeout>30</connectTimeout>\n"
                        + "<wmslayers>\n"
                        + "<atom:link xmlns:atom=\"http://www.w3.org/2005/Atom\" rel=\"alternate\" href=\"http://150.145.141.92/geoserver/rest/workspaces/sf/wmsstores/remote/wmslayers.xml\" type=\"application/xml\"/>\n"
                        + "</wmslayers>\n"
                        + "</wmsStore>"), GeoserverWMSStore.class);
        logger.info("@@@@@@@@@@@@@@@@@WMS_STORE : {}\n", wmsStore);
    }

    @Test
    public void d_unmarshallGPWMSStoreFromJsonStringTest() throws Exception {
        GeoserverWMSStore wmsStore = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "   \"wmsStore\":{\n"
                        + "      \"name\":\"remote\",\n"
                        + "      \"description\":\"description_test\",\n"
                        + "      \"type\":\"WMS\",\n"
                        + "      \"enabled\":true,\n"
                        + "      \"workspace\":{\n"
                        + "         \"name\":\"sf\",\n"
                        + "         \"href\":\"http://150.145.141.92/geoserver/rest/workspaces/sf.json\"\n"
                        + "      },\n"
                        + "      \"metadata\":{\n"
                        + "         \"entry\":{\n"
                        + "            \"@key\":\"useConnectionPooling\",\n"
                        + "            \"$\":\"false\"\n"
                        + "         }\n"
                        + "      },\n"
                        + "      \"_default\":false,\n"
                        + "      \"dateCreated\":\"2022-11-16 06:48:29.586 UTC\",\n"
                        + "      \"dateModified\":\"2022-11-16 06:49:23.494 UTC\",\n"
                        + "      \"capabilitiesURL\":\"http://150.145.141.180/geoserver/ows?service=wms&version=1.1.1&request=GetCapabilities\",\n"
                        + "      \"maxConnections\":6,\n"
                        + "      \"readTimeout\":60,\n"
                        + "      \"connectTimeout\":30,\n"
                        + "      \"wmslayers\":\"http://150.145.141.92/geoserver/rest/workspaces/sf/wmsstores/remote/wmslayers.json\"\n"
                        + "   }\n"
                        + "}"), GeoserverWMSStore.class);
        logger.info("@@@@@@@@@@@@@@@@@WMS_STORE : {}\n", wmsStore);
    }

    @Test
    public void e_marshallGPWMSStoreAsXmlStringTest() throws Exception {
        logger.info("##################WMS_STORE : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(toWMSStore("http://demo.geoserver.org/geoserver/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetCapabilities")));
    }

    @Test
    public void f_marshallGPWMSStoreAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@WMS_STORE : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(toWMSStore("http://demo.geoserver.org/geoserver/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetCapabilities")));
    }

    @Test
    public void g_dateFormatterTest() throws Exception {
       DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS z");
       logger.info("{}\n", LocalDateTime.parse("2022-11-23 06:45:05.6 UTC", dateFormat));
    }

    /**
     * @return {@link GPGeoserverWMSStoreBody}
     */
    public static GPGeoserverWMSStoreBody toWMSStore(@Nonnull(when = NEVER) String theCapabilitiesURL) throws Exception {
        checkArgument((theCapabilitiesURL != null) && !(theCapabilitiesURL.trim().isEmpty()), "The Parameter capabilitiesURL must not be null or an empty string.");
        GeoserverWMSStoreBody wmsStoreBody = new GeoserverWMSStoreBody();
        wmsStoreBody.setName("remote");
        wmsStoreBody.setDescription("description_test");
        wmsStoreBody.setType("WMS");
        wmsStoreBody.setEnabled(TRUE);
        wmsStoreBody.setCapabilitiesURL(theCapabilitiesURL);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("useConnectionPooling", "true");
        wmsStoreBody.setMetadata(metadata);
        return wmsStoreBody;
    }
}