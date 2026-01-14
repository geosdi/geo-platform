/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson.wmts;

import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GPGeoserverWMTSStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GPGeoserverWMTSStores;
import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GeoserverWMTSStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GeoserverWMTSStoreBody;
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
public class GeoserverWMTSStoresJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverWMTSStoresJacksonTest.class);

    @Test
    public void a_unmarshallWMTSStoresFromJsonStringTest() throws Exception {
        GPGeoserverWMTSStores wmtsStores = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"wmtsStores\":{\n"
                + "      \"wmtsStore\":[\n"
                + "         {\n"
                + "            \"name\":\"altgs\",\n"
                + "            \"href\":\"http://localhost:8080/geoserver/rest/workspaces/cite/wmtsstores/altgs.json\"\n"
                + "         }\n"
                + "      ]\n"
                + "   }\n"
                + "}"), GPGeoserverWMTSStores.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@WMTS_STORES : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(wmtsStores));
    }

    @Test
    public void b_unmarshallWMTSBaseStoreFromJsonStringTest() throws Exception {
        GPGeoserverStore wmtsBaseStore = emptyJacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"name\":\"altgs\",\n"
                + "   \"href\":\"http://localhost:8080/geoserver/rest/workspaces/cite/wmsstores/altgs.json\"\n"
                + "}"), GPGeoserverStore.class);
        logger.info("##################WMTS_BASE_STORE : \n{}\n", emptyJacksonSupport.getDefaultMapper().writeValueAsString(wmtsBaseStore));
    }

    @Test
    public void c_unmarshallGPWMTSStoreFromXmlStringTest() throws Exception {
        GeoserverWMTSStore wmtsStore = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<wmtsStore>\n"
                        + "    <name>remote_wmts</name>\n"
                        + "    <description>description_test</description>\n"
                        + "    <type>WMTS</type>\n"
                        + "    <enabled>true</enabled>\n"
                        + "    <__default__>false</__default__>\n"
                        + "    <capabilitiesURL>http://demo.geoserver.org/geoserver/gwc/service/wmts?SERVICE=WMTS&amp;VERSION=1.0.0&amp;REQUEST=GetCapabilities</capabilitiesURL>\n"
                        + "    <metadata>\n"
                        + "        <entry key=\"useConnectionPooling\">true</entry>\n"
                        + "    </metadata>\n"
                        + "</wmtsStore>"), GeoserverWMTSStore.class);
        logger.info("@@@@@@@@@@@@@@@@@WMTS_STORE : {}\n", wmtsStore);
    }

    @Test
    public void d_unmarshallGPWMTSStoreFromJsonStringTest() throws Exception {
        GeoserverWMTSStore wmtsStore = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"wmtsStore\" : {\n"
                        + "    \"name\" : \"remote_wmts\",\n"
                        + "    \"description\" : \"description_test\",\n"
                        + "    \"type\" : \"WMTS\",\n"
                        + "    \"enabled\" : true,\n"
                        + "    \"capabilitiesURL\" : \"http://demo.geoserver.org/geoserver/gwc/service/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetCapabilities\",\n"
                        + "    \"user\" : null,\n"
                        + "    \"password\" : null,\n"
                        + "    \"maxConnections\" : null,\n"
                        + "    \"readTimeout\" : null,\n"
                        + "    \"metadata\" : {\n"
                        + "      \"entry\" : [ {\n"
                        + "        \"@key\" : \"useConnectionPooling\",\n"
                        + "        \"$\" : \"true\"\n"
                        + "      } ]\n"
                        + "    },\n"
                        + "    \"workspace\" : null,\n"
                        + "    \"__default__\" : false,\n"
                        + "    \"connectTimeout\" : null\n"
                        + "  }\n"
                        + "}"), GeoserverWMTSStore.class);
        logger.info("@@@@@@@@@@@@@@@@@WMTS_STORE : {}\n", wmtsStore);
    }

    @Test
    public void e_marshallGPWMTSStoreAsXmlStringTest() throws Exception {
        logger.info("##################WMTS_STORE : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(toWMTSStore("http://demo.geoserver.org/geoserver/gwc/service/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetCapabilities")));
    }

    @Test
    public void f_marshallGPWMTSStoreAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@WMTS_STORE : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(toWMTSStore("http://demo.geoserver.org/geoserver/gwc/service/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetCapabilities")));
    }

    /**
     * @return {@link GPGeoserverWMSStoreBody}
     */
    public static GPGeoserverWMTSStoreBody toWMTSStore(@Nonnull(when = NEVER) String theCapabilitiesURL) throws Exception {
        checkArgument((theCapabilitiesURL != null) && !(theCapabilitiesURL.trim().isEmpty()), "The Parameter capabilitiesURL must not be null or an empty string.");
        GPGeoserverWMTSStoreBody wmsStoreBody = new GeoserverWMTSStoreBody();
        wmsStoreBody.setName("remote_wmts");
        wmsStoreBody.setDescription("description_test");
        wmsStoreBody.setType("WMTS");
        wmsStoreBody.setEnabled(TRUE);
        wmsStoreBody.setCapabilitiesURL(theCapabilitiesURL);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("useConnectionPooling", "true");
        wmsStoreBody.setMetadata(metadata);
        return wmsStoreBody;
    }
}