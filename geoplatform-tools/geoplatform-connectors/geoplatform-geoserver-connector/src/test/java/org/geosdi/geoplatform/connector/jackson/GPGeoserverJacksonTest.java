/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.about.status.GPGeoserverAboutStatus;
import org.geosdi.geoplatform.connector.geoserver.model.about.version.GPGeoserverAboutVersion;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastore;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastores;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.GPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GPGeoserverEmptyLayers;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GPGeoserverLayers;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverEmptyNamespaces;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverNamespaces;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverSingleNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverEmptyStyles;
import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverSingleStyle;
import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverStyles;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverEmptyWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverJacksonTest.class);
    //
    public static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);
    public static final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);
    private static final GPJacksonXmlSupport jacksonXmlSupport = new GPJacksonXmlSupport();

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

    @Test
    public void p_unmarshallGeoserverLoadDatastoresTest() throws Exception {
        logger.info("#######################GEOSERVER_LOAD_DATASTORES_RESPONSE : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"dataStores\":{  \n" +
                        "      \"dataStore\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"states_shapefile\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/topp\\/datastores\\/states_shapefile.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"taz_shapes\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/topp\\/datastores\\/taz_shapes.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverLoadDatastores.class));
    }

    @Test
    public void q_unmarshallGeoserverLoadDatastoreTest() throws Exception {
        logger.info("#######################GEOSERVER_LOAD_DATASTORES_RESPONSE : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"dataStore\":{  \n" +
                        "      \"name\":\"sf\",\n" +
                        "      \"enabled\":true,\n" +
                        "      \"workspace\":{  \n" +
                        "         \"name\":\"sf\",\n" +
                        "         \"href\":\"http://localhost:8080/geoserver/rest/workspaces/sf.json\"\n" +
                        "      },\n" +
                        "      \"connectionParameters\":{  \n" +
                        "         \"entry\":[  \n" +
                        "            {  \n" +
                        "               \"@key\":\"url\",\n" +
                        "               \"$\":\"file:data/sf\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"@key\":\"namespace\",\n" +
                        "               \"$\":\"http://www.openplans.org/spearfish\"\n" +
                        "            }\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"_default\":false,\n" +
                        "      \"featureTypes\":\"http://localhost:8080/geoserver/rest/workspaces/sf/datastores/sf/featuretypes.json\"\n" +
                        "   }\n" +
                        "}"), GPGeoserverLoadDatastore.class));
    }

    @Test
    public void r_unmarshallGeoserverCreateDatastoreTest() throws Exception {
        logger.info("#######################GEOSERVER_CREATE_DATASTORE_RESPONSE : \n{}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"dataStore\":{  \n" +
                        "      \"name\":\"sf\",\n" +
                        "      \"enabled\":true,\n" +
                        "      \"description\":\"description_test\",\n" +
                        "      \"workspace\":{  \n" +
                        "         \"name\":\"sf\",\n" +
                        "         \"href\":\"http://localhost:8080/geoserver/rest/workspaces/sf.json\"\n" +
                        "      },\n" +
                        "      \"connectionParameters\":{  \n" +
                        "         \"entry\":[  \n" +
                        "            {  \n" +
                        "               \"@key\":\"url\",\n" +
                        "               \"$\":\"file:data/sf\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"@key\":\"namespace\",\n" +
                        "               \"$\":\"http://www.openplans.org/spearfish\"\n" +
                        "            }\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"_default\":false,\n" +
                        "      \"featureTypes\":\"http://localhost:8080/geoserver/rest/workspaces/sf/datastores/sf/featuretypes.json\"\n" +
                        "   }\n" +
                        "}"), GPGeoserverCreateDatastoreBody.class));
    }

    @Test
    public void s_unmarshallGeoserverCoverageInfoTest() throws Exception {
        GPGeoserverCoverageInfo coverageInfo = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"coverage\":{  \n" +
                        "      \"abstract\":\"Digital elevation model for the Spearfish region.\\r\\n\\r\\nsfdem is a Tagged Image File Format with Geographic information\",\n" +
                        "      \"defaultInterpolationMethod\":\"nearest neighbor\",\n" +
                        "      \"description\":\"Generated from sfdem\",\n" +
                        "      \"dimensions\":{  \n" +
                        "         \"coverageDimension\":[  \n" +
                        "            {  \n" +
                        "               \"description\":\"GridSampleDimension[-9.999999933815813E36,-9.999999933815813E36]\",\n" +
                        "               \"name\":\"GRAY_INDEX\",\n" +
                        "               \"range\":{  \n" +
                        "                  \"max\":-9.999999933815813e+36,\n" +
                        "                  \"min\":-9.999999933815813e+36\n" +
                        "               }\n" +
                        "            }\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"enabled\":true,\n" +
                        "      \"grid\":{  \n" +
                        "         \"@dimension\":\"2\",\n" +
                        "         \"crs\":\"EPSG:26713\",\n" +
                        "         \"range\":{  \n" +
                        "            \"high\":\"634 477\",\n" +
                        "            \"low\":\"0 0\"\n" +
                        "         },\n" +
                        "         \"transform\":{  \n" +
                        "            \"scaleX\":30,\n" +
                        "            \"scaleY\":-30,\n" +
                        "            \"shearX\":0,\n" +
                        "            \"shearY\":0,\n" +
                        "            \"translateX\":589995,\n" +
                        "            \"translateY\":4927995\n" +
                        "         }\n" +
                        "      },\n" +
                        "      \"interpolationMethods\":{  \n" +
                        "         \"string\":[  \n" +
                        "            \"nearest neighbor\",\n" +
                        "            \"bilinear\",\n" +
                        "            \"bicubic\"\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"keywords\":{  \n" +
                        "         \"string\":[  \n" +
                        "            \"WCS\",\n" +
                        "            \"sfdem\",\n" +
                        "            \"sfdem\"\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"latLonBoundingBox\":{  \n" +
                        "         \"crs\":\"EPSG:4326\",\n" +
                        "         \"maxx\":-103.62940739432703,\n" +
                        "         \"maxy\":44.5016011535299,\n" +
                        "         \"minx\":-103.87108701853181,\n" +
                        "         \"miny\":44.370187074132616\n" +
                        "      },\n" +
                        "      \"metadata\":{  \n" +
                        "         \"entry\":{  \n" +
                        "            \"$\":\"sfdem_sfdem\",\n" +
                        "            \"@key\":\"dirName\"\n" +
                        "         }\n" +
                        "      },\n" +
                        "      \"name\":\"sfdem\",\n" +
                        "      \"namespace\":{  \n" +
                        "         \"href\":\"http://localhost:/geoserver/restng/namespaces/sf.json\",\n" +
                        "         \"name\":\"sf\"\n" +
                        "      },\n" +
                        "      \"nativeBoundingBox\":{  \n" +
                        "         \"crs\":{  \n" +
                        "            \"$\":\"EPSG:26713\",\n" +
                        "            \"@class\":\"projected\"\n" +
                        "         },\n" +
                        "         \"maxx\":609000,\n" +
                        "         \"maxy\":4928010,\n" +
                        "         \"minx\":589980,\n" +
                        "         \"miny\":4913700\n" +
                        "      },\n" +
                        "      \"nativeCRS\":{  \n" +
                        "         \"$\":\"PROJCS[\\\"NAD27 / UTM zone 13N\\\", \\n  GEOGCS[\\\"NAD27\\\", \\n    DATUM[\\\"North American Datum 1927\\\", \\n      SPHEROID[\\\"Clarke 1866\\\", 6378206.4, 294.9786982138982, AUTHORITY[\\\"EPSG\\\",\\\"7008\\\"]], \\n      TOWGS84[2.478, 149.752, 197.726, 0.526, -0.498, 0.501, 0.685], \\n      AUTHORITY[\\\"EPSG\\\",\\\"6267\\\"]], \\n    PRIMEM[\\\"Greenwich\\\", 0.0, AUTHORITY[\\\"EPSG\\\",\\\"8901\\\"]], \\n    UNIT[\\\"degree\\\", 0.017453292519943295], \\n    AXIS[\\\"Geodetic longitude\\\", EAST], \\n    AXIS[\\\"Geodetic latitude\\\", NORTH], \\n    AUTHORITY[\\\"EPSG\\\",\\\"4267\\\"]], \\n  PROJECTION[\\\"Transverse_Mercator\\\"], \\n  PARAMETER[\\\"central_meridian\\\", -105.0], \\n  PARAMETER[\\\"latitude_of_origin\\\", 0.0], \\n  PARAMETER[\\\"scale_factor\\\", 0.9996], \\n  PARAMETER[\\\"false_easting\\\", 500000.0], \\n  PARAMETER[\\\"false_northing\\\", 0.0], \\n  UNIT[\\\"m\\\", 1.0], \\n  AXIS[\\\"Easting\\\", EAST], \\n  AXIS[\\\"Northing\\\", NORTH], \\n  AUTHORITY[\\\"EPSG\\\",\\\"26713\\\"]]\",\n" +
                        "         \"@class\":\"projected\"\n" +
                        "      },\n" +
                        "      \"nativeFormat\":\"GeoTIFF\",\n" +
                        "      \"nativeName\":\"sfdem\",\n" +
                        "      \"requestSRS\":{  \n" +
                        "         \"string\":[  \n" +
                        "            \"EPSG:26713\"\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"responseSRS\":{  \n" +
                        "         \"string\":[  \n" +
                        "            \"EPSG:26713\"\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"srs\":\"EPSG:26713\",\n" +
                        "      \"store\":{  \n" +
                        "         \"@class\":\"coverageStore\",\n" +
                        "         \"href\":\"http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json\",\n" +
                        "         \"name\":\"sf:sfdem\"\n" +
                        "      },\n" +
                        "      \"supportedFormats\":{  \n" +
                        "         \"string\":[  \n" +
                        "            \"ARCGRID\",\n" +
                        "            \"IMAGEMOSAIC\",\n" +
                        "            \"GTOPO30\",\n" +
                        "            \"GEOTIFF\",\n" +
                        "            \"GIF\",\n" +
                        "            \"PNG\",\n" +
                        "            \"JPEG\",\n" +
                        "            \"TIFF\"\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"title\":\"Spearfish elevation\"\n" +
                        "   }\n" +
                        "}"), GPGeoserverCoverageInfo.class);
        logger.info("##########################GEOSERVER_COVERAGE_INFO : {}\n", coverageInfo);
    }

    @Test
    public void t_unmarshallGeoserverFeatureTypeInfoTest() throws Exception {
        GPGeoserverFeatureTypeInfo featureTypeInfo = emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n" +
                        "  \"name\": \"poi\",\n" +
                        "  \"nativeName\": \"poi\",\n" +
                        "  \"namespace\": {\n" +
                        "    \"name\": \"tiger\",\n" +
                        "    \"href\": \"http://localhost:8080/geoserver/rest/namespaces/tiger.json\"\n" +
                        "  },\n" +
                        "  \"title\": \"Manhattan (NY) points of interest\",\n" +
                        "  \"abstract\": \"Points of interest in New York, New York (on Manhattan). One of the attributes contains the name of a file with a picture of the point of interest.\",\n" +
                        "  \"keywords\": {\n" +
                        "    \"string\": [\n" +
                        "      \"poi\",\n" +
                        "      \"Manhattan\",\n" +
                        "      \"DS_poi\",\n" +
                        "      \"points_of_interest\",\n" +
                        "      \"sampleKeyword\\\\@language=ab\\\\;\",\n" +
                        "      \"area of effect\\\\@language=bg\\\\;\\\\@vocabulary=technical\\\\;\",\n" +
                        "      \"Привет\\\\@language=ru\\\\;\\\\@vocabulary=friendly\\\\;\"\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"metadataLinks\": {\n" +
                        "    \"metadataLink\": [\n" +
                        "      {\n" +
                        "        \"type\": \"text/plain\",\n" +
                        "        \"metadataType\": \"FGDC\",\n" +
                        "        \"content\": \"www.google.com\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"dataLinks\": {\n" +
                        "    \"org.geoserver.catalog.impl.DataLinkInfoImpl\": [\n" +
                        "      {\n" +
                        "        \"type\": \"text/plain\",\n" +
                        "        \"content\": \"http://www.google.com\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"nativeCRS\": \"GEOGCS[\\\"WGS 84\\\", \\n  DATUM[\\\"World Geodetic System 1984\\\", \\n    SPHEROID[\\\"WGS 84\\\", 6378137.0, 298.257223563, AUTHORITY[\\\"EPSG\\\",\\\"7030\\\"]], \\n    AUTHORITY[\\\"EPSG\\\",\\\"6326\\\"]], \\n  PRIMEM[\\\"Greenwich\\\", 0.0, AUTHORITY[\\\"EPSG\\\",\\\"8901\\\"]], \\n  UNIT[\\\"degree\\\", 0.017453292519943295], \\n  AXIS[\\\"Geodetic longitude\\\", EAST], \\n  AXIS[\\\"Geodetic latitude\\\", NORTH], \\n  AUTHORITY[\\\"EPSG\\\",\\\"4326\\\"]]\",\n" +
                        "  \"srs\": \"EPSG:4326\",\n" +
                        "  \"nativeBoundingBox\": {\n" +
                        "    \"minx\": -74.0118315772888,\n" +
                        "    \"maxx\": -74.00153046439813,\n" +
                        "    \"miny\": 40.70754683896324,\n" +
                        "    \"maxy\": 40.719885123828675,\n" +
                        "    \"crs\": \"EPSG:4326\"\n" +
                        "  },\n" +
                        "  \"latLonBoundingBox\": {\n" +
                        "    \"minx\": -74.0118315772888,\n" +
                        "    \"maxx\": -74.00857344353275,\n" +
                        "    \"miny\": 40.70754683896324,\n" +
                        "    \"maxy\": 40.711945649065406,\n" +
                        "    \"crs\": \"EPSG:4326\"\n" +
                        "  },\n" +
                        "  \"projectionPolicy\": \"REPROJECT_TO_DECLARED\",\n" +
                        "  \"enabled\": true,\n" +
                        "  \"metadata\": {\n" +
                        "    \"entry\": [\n" +
                        "      {\n" +
                        "        \"@key\": \"kml.regionateStrategy\",\n" +
                        "        \"$\": \"external-sorting\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@key\": \"kml.regionateFeatureLimit\",\n" +
                        "        \"$\": \"15\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@key\": \"cacheAgeMax\",\n" +
                        "        \"$\": \"3000\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@key\": \"cachingEnabled\",\n" +
                        "        \"$\": \"true\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@key\": \"kml.regionateAttribute\",\n" +
                        "        \"$\": \"NAME\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@key\": \"indexingEnabled\",\n" +
                        "        \"$\": \"false\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"@key\": \"dirName\",\n" +
                        "        \"$\": \"DS_poi_poi\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"store\": {\n" +
                        "    \"@class\": \"dataStore\",\n" +
                        "    \"name\": \"tiger:nyc\",\n" +
                        "    \"href\": \"http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.json\"\n" +
                        "  },\n" +
                        "  \"cqlFilter\": \"INCLUDE\",\n" +
                        "  \"maxFeatures\": 100,\n" +
                        "  \"numDecimals\": 6,\n" +
                        "  \"responseSRS\": {\n" +
                        "    \"string\": [\n" +
                        "      4326\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"overridingServiceSRS\": true,\n" +
                        "  \"skipNumberMatched\": true,\n" +
                        "  \"circularArcPresent\": true,\n" +
                        "  \"linearizationTolerance\": 10,\n" +
                        "  \"attributes\": {\n" +
                        "    \"attribute\": [\n" +
                        "      {\n" +
                        "        \"name\": \"the_geom\",\n" +
                        "        \"minOccurs\": 0,\n" +
                        "        \"maxOccurs\": 1,\n" +
                        "        \"nillable\": true,\n" +
                        "        \"binding\": \"org.locationtech.jts.geom.Point\"\n" +
                        "      },\n" +
                        "      {},\n" +
                        "      {},\n" +
                        "      {}\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"), GPGeoserverFeatureTypeInfo.class);
        logger.info("##########################GEOSERVER_FEATURE_TYPE_INFO : {}\n", featureTypeInfo);
        emptyJacksonSupport.getDefaultMapper().writeValue(new File("./target/FeatureType.json"), featureTypeInfo);
    }

    @Test
    public void u_unmarshallerGeoserverFeatureTypeInfoTest() throws Exception {
        GPGeoserverFeatureTypeInfo featureTypeInfo = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"featureType\":{  \n" +
                        "      \"name\":\"admin_shp_com2016_wgs84_g\",\n" +
                        "      \"nativeName\":\"admin_shp_com2016_wgs84_g\",\n" +
                        "      \"namespace\":{  \n" +
                        "         \"name\":\"topp\",\n" +
                        "         \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/namespaces\\/topp.json\"\n" +
                        "      },\n" +
                        "      \"title\":\"admin_shp_com2016_wgs84_g\",\n" +
                        "      \"keywords\":{  \n" +
                        "         \"string\":[  \n" +
                        "            \"features\",\n" +
                        "            \"admin_shp_com2016_wgs84_g\"\n" +
                        "         ]\n" +
                        "      },\n" +
                        "      \"nativeCRS\":{  \n" +
                        "         \"@class\":\"projected\",\n" +
                        "         \"$\":\"PROJCS[\\\"WGS 84 \\/ UTM zone 32N\\\", \\n  GEOGCS[\\\"WGS 84\\\", \\n    DATUM[\\\"World Geodetic System 1984\\\", \\n      SPHEROID[\\\"WGS 84\\\", 6378137.0, 298.257223563, AUTHORITY[\\\"EPSG\\\",\\\"7030\\\"]], \\n      AUTHORITY[\\\"EPSG\\\",\\\"6326\\\"]], \\n    PRIMEM[\\\"Greenwich\\\", 0.0, AUTHORITY[\\\"EPSG\\\",\\\"8901\\\"]], \\n    UNIT[\\\"degree\\\", 0.017453292519943295], \\n    AXIS[\\\"Geodetic longitude\\\", EAST], \\n    AXIS[\\\"Geodetic latitude\\\", NORTH], \\n    AUTHORITY[\\\"EPSG\\\",\\\"4326\\\"]], \\n  PROJECTION[\\\"Transverse_Mercator\\\"], \\n  PARAMETER[\\\"central_meridian\\\", 9.0], \\n  PARAMETER[\\\"latitude_of_origin\\\", 0.0], \\n  PARAMETER[\\\"scale_factor\\\", 0.9996], \\n  PARAMETER[\\\"false_easting\\\", 500000.0], \\n  PARAMETER[\\\"false_northing\\\", 0.0], \\n  UNIT[\\\"m\\\", 1.0], \\n  AXIS[\\\"Easting\\\", EAST], \\n  AXIS[\\\"Northing\\\", NORTH], \\n  AUTHORITY[\\\"EPSG\\\",\\\"32632\\\"]]\"\n" +
                        "      },\n" +
                        "      \"srs\":\"EPSG:32632\",\n" +
                        "      \"nativeBoundingBox\":{  \n" +
                        "         \"minx\":308285.5625,\n" +
                        "         \"maxx\":1317009.875,\n" +
                        "         \"miny\":3927413.75,\n" +
                        "         \"maxy\":5226724.5,\n" +
                        "         \"crs\":{  \n" +
                        "            \"@class\":\"projected\",\n" +
                        "            \"$\":\"EPSG:32632\"\n" +
                        "         }\n" +
                        "      },\n" +
                        "      \"latLonBoundingBox\":{  \n" +
                        "         \"minx\":6.470380147450785,\n" +
                        "         \"maxx\":19.68919647853578,\n" +
                        "         \"miny\":35.157041522374975,\n" +
                        "         \"maxy\":47.193590190960194,\n" +
                        "         \"crs\":\"EPSG:4326\"\n" +
                        "      },\n" +
                        "      \"projectionPolicy\":\"FORCE_DECLARED\",\n" +
                        "      \"enabled\":true,\n" +
                        "      \"store\":{  \n" +
                        "         \"@class\":\"dataStore\",\n" +
                        "         \"name\":\"topp:com\",\n" +
                        "         \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/topp\\/datastores\\/com.json\"\n" +
                        "      },\n" +
                        "      \"serviceConfiguration\":false,\n" +
                        "      \"maxFeatures\":0,\n" +
                        "      \"numDecimals\":0,\n" +
                        "      \"padWithZeros\":false,\n" +
                        "      \"forcedDecimal\":false,\n" +
                        "      \"overridingServiceSRS\":false,\n" +
                        "      \"skipNumberMatched\":false,\n" +
                        "      \"circularArcPresent\":false,\n" +
                        "      \"attributes\":{  \n" +
                        "         \"attribute\":[  \n" +
                        "            {  \n" +
                        "               \"name\":\"the_geom\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"org.locationtech.jts.geom.MultiPolygon\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"COD_REG\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Integer\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"COD_CM\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Integer\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"COD_PRO\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Integer\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"PRO_COM\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Long\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"COMUNE\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.String\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"NOME_TED\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.String\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"FLAG_CM\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Integer\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"SHAPE_Leng\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Double\"\n" +
                        "            },\n" +
                        "            {  \n" +
                        "               \"name\":\"SHAPE_Area\",\n" +
                        "               \"minOccurs\":0,\n" +
                        "               \"maxOccurs\":1,\n" +
                        "               \"nillable\":true,\n" +
                        "               \"binding\":\"java.lang.Double\"\n" +
                        "            }\n" +
                        "         ]\n" +
                        "      }\n" +
                        "   }\n" +
                        "}"), GPGeoserverFeatureTypeInfo.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_FEATURE_TYPE_INFO : {}\n", featureTypeInfo);
        jacksonSupport.getDefaultMapper().writeValue(new File("./target/FeatureType.json"), featureTypeInfo);
        Writer writer = new StringWriter();
        jacksonXmlSupport.getDefaultMapper().writeValue(writer, featureTypeInfo);
        logger.info("\n{}\n", writer);
    }
}