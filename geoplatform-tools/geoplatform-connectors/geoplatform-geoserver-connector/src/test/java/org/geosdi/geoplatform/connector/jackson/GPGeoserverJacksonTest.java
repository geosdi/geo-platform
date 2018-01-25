/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.request.model.about.status.GPGeoserverAboutStatus;
import org.geosdi.geoplatform.connector.geoserver.request.model.about.version.GPGeoserverAboutVersion;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GPGeoserverEmptyLayers;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GPGeoserverLayers;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverEmptyNamespaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverNamespaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.namespace.GPGeoserverSingleNamespace;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverEmptyStyles;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverSingleStyle;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverStyles;
import org.geosdi.geoplatform.connector.geoserver.request.model.workspace.GPGeoserverEmptyWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverJacksonTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);
    private static final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_unmarshallGeoserverAboutVersionTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_VERSION : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"about\":{\"resource\":[{\"@name\":\"GeoServer\",\"Build-Timestamp\"" +
                        ":\"21-Nov-2017 22:02\",\"Version\":\"2.12.1\",\"Git-Revision\":" +
                        "\"5927e49e781ddcdbf9213d32a439418347c17480\"},{\"@name\":\"GeoTools\",\"Build-Timestamp\":" +
                        "\"21-Nov-2017 14:18\",\"Version\":18.1,\"Git-Revision\":" +
                        "\"306cf3bdde1bee0110dc1c3ba77819f1e294a45b\"},{\"@name\":\"GeoWebCache\",\"Version\":\"1.12.1\"," +
                        "\"Git-Revision\":\"1.12.x\\/22d18b47c9e80316d563c28d280602cb3dde624c\"}]}}\n"), GPGeoserverAboutVersion.class));
    }

    @Test
    public void b_unmarshallGeoserverWorkspacesTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WOKSPACES : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"workspaces\":{  \n" +
                        "      \"workspace\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"cite\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/cite.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tiger\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/tiger.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"nurc\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/nurc.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sde\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/sde.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"it.geosolutions\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/it.geosolutions.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"topp\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/topp.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sf\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/sf.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverWorkspaces.class));
    }

    @Test
    public void c_unmarshallGeoserverEmptyWorkspacesTest() throws Exception {
        logger.info("\n{}\n", emptyJacksonSupport.getDefaultMapper().readValue(new StringReader("{  \n" +
                "   \"workspaces\":\"\"\n" +
                "}"), GPGeoserverEmptyWorkspaces.class));
    }

    @Test
    public void d_unmarshallGeoserverAboutStatusTest() throws Exception {
        logger.info("##############################GEOSERVER_ABOUT_STATUS : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"statuss\":{  \n" +
                        "      \"status\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Main\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Main.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"Rendering Engine\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/Rendering+Engine.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"system-properties\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/system-properties.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"system-environment\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/system-environment.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer KML\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+KML.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoWeb Cache\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoWeb+Cache.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI GeoWebCache\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+GeoWebCache.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Security JDBC\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Security+JDBC.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Web Feature Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Web+Feature+Service.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web REST\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+REST.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Web Coverage Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Web+Coverage+Service.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Map Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Map+Service.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Processing Service Core\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Processing+Service+Core.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Security LDAP\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Security+LDAP.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Coverage Service 1.1\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Coverage+Service+1.1.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Demos\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Demos.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Web Map Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Web+Map+Service.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Web Processing Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Web+Processing+Service.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Coverage Service 2.0\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Coverage+Service+2.0.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Security Core\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Security+Core.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Core\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Core.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web UI Security LDAP\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+UI+Security+LDAP.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Security JDBC\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Security+JDBC.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Coverage Service 1.0\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Coverage+Service+1.0.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Feature Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Feature+Service.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"GeoServer Web Coverage Service\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/about\\/status\\/GeoServer+Web+Coverage+Service.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverAboutStatus.class));
    }

    @Test
    public void d_unmarshallGeoserverNamespacesTest() throws Exception {
        logger.info("##########################GEOSERVER_NAMESPACES :  \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"namespaces\":{  \n" +
                        "      \"namespace\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"cite\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/cite.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tiger\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/tiger.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"nurc\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/nurc.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sde\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/sde.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"it.geosolutions\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/it.geosolutions.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"topp\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/topp.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sf\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/sf.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverNamespaces.class));
    }

    @Test
    public void e_unmarshallGeoserverEmptyNamespacesTest() throws Exception {
        logger.info("##########################GEOSERVER_NAMESPACES :  \n{}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"namespaces\":\"\"}"), GPGeoserverEmptyNamespaces.class));
    }

    @Test
    public void f_unmarshallGeoserverNamespaceTest() throws Exception {
        logger.info("##########################GEOSERVER_NAMESPACE : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"namespace\":{\"prefix\":\"nurc\",\"uri\":\"http:\\/\\/www.nurc.nato.int\"}}"),
                        GPGeoserverSingleNamespace.class));
    }

    @Test
    public void g_unmarshallGeoserverLayersTest() throws Exception {
        logger.info("#########################GEOSERVER_LAYERS : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"layers\":{  \n" +
                        "      \"layer\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"giant_polygon\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/giant_polygon.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"poi\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/poi.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"poly_landmarks\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/poly_landmarks.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tiger_roads\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/tiger_roads.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"Arc_Sample\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/Arc_Sample.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"Img_Sample\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/Img_Sample.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"Pk50095\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/Pk50095.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"mosaic\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/mosaic.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"states\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/states.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tasmania_cities\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/tasmania_cities.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tasmania_roads\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/tasmania_roads.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tasmania_state_boundaries\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/tasmania_state_boundaries.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tasmania_water_bodies\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/tasmania_water_bodies.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"archsites\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/archsites.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"bugsites\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/bugsites.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"restricted\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/restricted.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"roads\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/roads.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sfdem\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/sfdem.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"streams\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/layers\\/streams.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverLayers.class));
    }

    @Test
    public void h_unmarshallGeoserverEmptyLayersTest() throws Exception {
        logger.info("#######################GEOSERVER_EMPTY_LAYERS : \n{}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"layers\":\"\"}"), GPGeoserverEmptyLayers.class));
    }

    @Test
    public void i_unmarshallGeoserverStylesTest() throws Exception {
        logger.info("#####################GEOSERVER_STYLES : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"styles\":{  \n" +
                        "      \"style\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"Frank\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/Frank.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"burg\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/burg.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"capitals\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/capitals.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"cite_lakes\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/cite_lakes.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"dem\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/dem.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"generic\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/generic.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"giant_polygon\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/giant_polygon.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"grass\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/grass.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"green\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/green.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"line\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/line.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"poi\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/poi.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"point\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/point.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"poly_landmarks\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/poly_landmarks.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"polygon\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/polygon.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"pophatch\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/pophatch.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"population\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/population.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"rain\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/rain.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"raster\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/raster.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"restricted\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/restricted.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"simple_roads\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/simple_roads.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"simple_streams\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/simple_streams.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tiger_roads\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/tiger_roads.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverStyles.class));
    }

    @Test
    public void l_unmarshallGeoserverEmptyStylesTest() throws Exception {
        logger.info("#######################GEOSERVER_EMPTY_STYLES : \n{}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"styles\":\"\"}"), GPGeoserverEmptyStyles.class));
    }

    @Test
    public void m_unmarshallGeoserverStyleTest() throws Exception {
        logger.info("######################GEOSERVER_STYLE : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"style\":{  \n" +
                        "      \"name\":\"Frank\",\n" +
                        "      \"format\":\"sld\",\n" +
                        "      \"languageVersion\":{  \n" +
                        "         \"version\":\"1.0.0\"\n" +
                        "      },\n" +
                        "      \"filename\":\"Frank.sld\"\n" +
                        "   }\n" +
                        "}"), GPGeoserverSingleStyle.class));
    }

    @Test
    public void n_unmarshallGeoserverRasterLayerTest() throws Exception {
        logger.info("####################GEOSERVER_RASTER_LAYER : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"layer\":{  \n" +
                        "      \"name\":\"Arc_Sample\",\n" +
                        "      \"path\":\"\\/\",\n" +
                        "      \"type\":\"RASTER\",\n" +
                        "      \"defaultStyle\":{  \n" +
                        "         \"name\":\"rain\",\n" +
                        "         \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/rain.json\"\n" +
                        "      },\n" +
                        "      \"styles\":{  \n" +
                        "         \"@class\":\"linked-hash-set\",\n" +
                        "         \"style\":[  \n" +
                        "            {  \n" +
                        "               \"name\":\"raster\",\n" +
                        "               \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/raster.json\"\n" +
                        "            }\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"resource\":{  \n" +
                        "         \"@class\":\"coverage\",\n" +
                        "         \"name\":\"Arc_Sample\",\n" +
                        "         \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/nurc\\/coveragestores\\/arcGridSample\\/coverages\\/Arc_Sample.json\"\n" +
                        "      },\n" +
                        "      \"attribution\":{  \n" +
                        "         \"logoWidth\":0,\n" +
                        "         \"logoHeight\":0\n" +
                        "      }\n" +
                        "   }\n" +
                        "}"), GeoserverLayer.class));
    }

    @Test
    public void o_unmarshallGeoserverVectorLayerTest() throws Exception {
        logger.info("#######################GEOSERVER_VECTOR_LAYER : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"layer\":{  \n" +
                        "      \"name\":\"giant_polygon\",\n" +
                        "      \"path\":\"\\/\",\n" +
                        "      \"type\":\"VECTOR\",\n" +
                        "      \"defaultStyle\":{  \n" +
                        "         \"name\":\"giant_polygon\",\n" +
                        "         \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/styles\\/giant_polygon.json\"\n" +
                        "      },\n" +
                        "      \"resource\":{  \n" +
                        "         \"@class\":\"featureType\",\n" +
                        "         \"name\":\"giant_polygon\",\n" +
                        "         \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/tiger\\/datastores\\/nyc\\/featuretypes\\/giant_polygon.json\"\n" +
                        "      },\n" +
                        "      \"attribution\":{  \n" +
                        "         \"logoWidth\":0,\n" +
                        "         \"logoHeight\":0\n" +
                        "      }\n" +
                        "   }\n" +
                        "}"), GeoserverLayer.class));
    }
}