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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.layergroups.publishables.GPGeoserverLayerGroupPublishables;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.publishables.GPGeoserverLayerPublished;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.publishables.GPGeoserverLayerTypePublished;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.emptyJacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverLayerGroupsJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverLayerGroupsJacksonTest.class);

    @Test
    public void a_unmarshallGeoserverLayerGroupsFromJsonStringTest() throws Exception {
        IGPGeoserverLayerGroups layerGroups = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"layerGroups\":{\n"
                + "      \"layerGroup\":[\n"
                + "         {\n"
                + "            \"name\":\"spearfish\",\n"
                + "            \"href\":\"http:\\/\\/localhost:8080\\/geoserver\\/rest\\/layergroups\\/spearfish.json\"\n"
                + "         },\n"
                + "         {\n"
                + "            \"name\":\"tasmania\",\n"
                + "            \"href\":\"http:\\/\\/localhost:8080\\/geoserver\\/rest\\/layergroups\\/tasmania.json\"\n"
                + "         },\n"
                + "         {\n"
                + "            \"name\":\"tiger-ny\",\n"
                + "            \"href\":\"http:\\/\\/localhost:8080\\/geoserver\\/rest\\/layergroups\\/tiger-ny.json\"\n"
                + "         }\n"
                + "      ]\n"
                + "   }\n"
                + "}"), IGPGeoserverLayerGroups.class);
        logger.info("##################GEOSERVER_LAYER_GROUPS_FROM_JSON_STRING : {}\n", layerGroups);
    }

    @Test
    public void b_unmarshallGeoserverLayerGroupsFromXmlStringTest() throws Exception {
        GPGeoserverLayerGroups layerGroups = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<layerGroups>\n"
                + "    <layerGroup>\n"
                + "        <name>spearfish</name>\n"
                + "        <href>http://localhost:8080/geoserver/rest/layergroups/spearfish.json</href>\n"
                + "    </layerGroup>\n"
                + "    <layerGroup>\n"
                + "        <name>tasmania</name>\n"
                + "        <href>http://localhost:8080/geoserver/rest/layergroups/tasmania.json</href>\n"
                + "    </layerGroup>\n"
                + "    <layerGroup>\n"
                + "        <name>tiger-ny</name>\n"
                + "        <href>http://localhost:8080/geoserver/rest/layergroups/tiger-ny.json</href>\n"
                + "    </layerGroup>\n"
                + "</layerGroups>"), GPGeoserverLayerGroups.class);
        logger.info("@@@@@@@@@@@@@@@@GEOSERVER_LAYER_GROUP_FROM_XML_STRING : {}\n", layerGroups);
    }

    @Test
    public void c_unmarshallGeoserverLayerGroupBodyFromXmlStringTest() throws Exception {
        GPGeoserverLayerGroupBody layerGroupBody = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<layerGroup>\n"
                        + "   <name>string</name>\n"
                        + "   <mode>SINGLE</mode>\n"
                        + "   <title>string</title>\n"
                        + "   <abstractTxt>string</abstractTxt>\n"
                        + "   <workspace>\n"
                        + "      <name>string</name>\n"
                        + "   </workspace>\n"
                        + "   <layers>\n"
                        + "      <layer>\n"
                        + "         <name>string</name>\n"
                        + "         <link>string</link>\n"
                        + "      </layer>\n"
                        + "   </layers>\n"
                        + "   <styles>\n"
                        + "      <style>\n"
                        + "         <name>string</name>\n"
                        + "         <link>string</link>\n"
                        + "      </style>\n"
                        + "   </styles>\n"
                        + "   <metadataLinks>\n"
                        + "      <type>string</type>\n"
                        + "      <metadataType>string</metadataType>\n"
                        + "      <content>string</content>\n"
                        + "   </metadataLinks>\n"
                        + "   <bounds>\n"
                        + "      <minx>0</minx>\n"
                        + "      <maxx>0</maxx>\n"
                        + "      <miny>0</miny>\n"
                        + "      <maxy>0</maxy>\n"
                        + "      <crs>string</crs>\n"
                        + "   </bounds>\n"
                        + "   <keywords>\n"
                        + "      <keyword>string</keyword>\n"
                        + "   </keywords>\n"
                        + "</layerGroup>"), GPGeoserverLayerGroupBody.class);
        logger.info("###################GEOSERVER_CREATE_LAYER_GROUP_BODY : {}\n", layerGroupBody);
    }

    @Test
    public void d_unmarshallGeoserverLayerGroupBodyFromJsonStringTest() throws Exception {
        IGPGeoserverLayerGroupBody layerGroupBody = emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "   \"name\":\"string\",\n"
                        + "   \"mode\":\"SINGLE\",\n"
                        + "   \"title\":\"string\",\n"
                        + "   \"abstractTxt\":\"string\",\n"
                        + "   \"workspace\":{\n"
                        + "      \"name\":\"string\"\n"
                        + "   },\n"
                        + "   \"layers\":{\n"
                        + "      \"published\":[\n"
                        + "         {\n"
                        + "            \"name\":\"string\",\n"
                        + "            \"link\":\"string\"\n"
                        + "         }\n"
                        + "      ]\n"
                        + "   },\n"
                        + "   \"styles\":{\n"
                        + "      \"style\":[\n"
                        + "         {\n"
                        + "            \"name\":\"string\",\n"
                        + "            \"link\":\"string\"\n"
                        + "         }\n"
                        + "      ]\n"
                        + "   },\n"
                        + "   \"metadataLinks\":[\n"
                        + "      {\n"
                        + "         \"type\":\"string\",\n"
                        + "         \"metadataType\":\"string\",\n"
                        + "         \"content\":\"string\"\n"
                        + "      }\n"
                        + "   ],\n"
                        + "   \"bounds\":{\n"
                        + "      \"minx\":0,\n"
                        + "      \"maxx\":0,\n"
                        + "      \"miny\":0,\n"
                        + "      \"maxy\":0,\n"
                        + "      \"crs\":\"string\"\n"
                        + "   },\n"
                        + "   \"keywords\":{\n"
                        + "      \"keyword\":[\n"
                        + "         \"string\"\n"
                        + "      ]\n"
                        + "   }\n"
                        + "}"), IGPGeoserverLayerGroupBody.class);
        logger.info("{}\n", layerGroupBody);
    }

    @Test
    public void e_marshallGeoserverLayerGroupPublishablesXmlTest() throws Exception {
        GPGeoserverLayerGroupPublishables layerGroupPublishables = new GPGeoserverLayerGroupPublishables();
        layerGroupPublishables.setLayers(iterate(0, n -> n + 1)
                .limit(10)
                .map(this::build)
                .collect(toList()));
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@\n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().writeValueAsString(layerGroupPublishables));
    }

    @Test
    public void f_unmarshallGeoserverLayerGroupPublishablesFromXmlStringTest() throws Exception {
        GPGeoserverLayerGroupPublishables layerGroupPublishables = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<publishables>\n"
                        + "    <published>\n"
                        + "        <name>LAYER_NAME#0</name>\n"
                        + "        <link>LINK#0</link>\n"
                        + "    </published>\n"
                        + "    <published xsi:type=\"type\" type=\"layer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                        + "        <name>LAYER_NAME#1</name>\n"
                        + "        <link>LINK#1</link>\n"
                        + "    </published>\n"
                        + "    <published>\n"
                        + "        <name>LAYER_NAME#2</name>\n"
                        + "        <link>LINK#2</link>\n"
                        + "    </published>\n"
                        + "    <published xsi:type=\"type\" type=\"layer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                        + "        <name>LAYER_NAME#3</name>\n"
                        + "        <link>LINK#3</link>\n"
                        + "    </published>\n"
                        + "    <published>\n"
                        + "        <name>LAYER_NAME#4</name>\n"
                        + "        <link>LINK#4</link>\n"
                        + "    </published>\n"
                        + "    <published xsi:type=\"type\" type=\"layer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                        + "        <name>LAYER_NAME#5</name>\n"
                        + "        <link>LINK#5</link>\n"
                        + "    </published>\n"
                        + "    <published>\n"
                        + "        <name>LAYER_NAME#6</name>\n"
                        + "        <link>LINK#6</link>\n"
                        + "    </published>\n"
                        + "    <published xsi:type=\"type\" type=\"layer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                        + "        <name>LAYER_NAME#7</name>\n"
                        + "        <link>LINK#7</link>\n"
                        + "    </published>\n"
                        + "    <published>\n"
                        + "        <name>LAYER_NAME#8</name>\n"
                        + "        <link>LINK#8</link>\n"
                        + "    </published>\n"
                        + "    <published xsi:type=\"type\" type=\"layer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
                        + "        <name>LAYER_NAME#9</name>\n"
                        + "        <link>LINK#9</link>\n"
                        + "    </published>\n"
                        + "</publishables>"), GPGeoserverLayerGroupPublishables.class);
        logger.info("{}\n", layerGroupPublishables);
    }

    @Test
    public void g_marshallGeoserverLayerGroupPublishablesJsonTest() throws Exception {
        GPGeoserverLayerGroupPublishables layerGroupPublishables = new GPGeoserverLayerGroupPublishables();
        layerGroupPublishables.setLayers(iterate(0, n -> n + 1)
                .limit(10)
                .map(this::build)
                .collect(toList()));
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@\n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(layerGroupPublishables));
    }

    @Test
    public void h_unmarshallGeoserverLayerGroupPublishablesFromJsonStringTest() throws Exception {
        GPGeoserverLayerGroupPublishables layerGroupPublishables = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"publishables\" : {\n"
                        + "    \"published\" : [ {\n"
                        + "      \"link\" : \"LINK#0\",\n"
                        + "      \"name\" : \"LAYER_NAME#0\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#1\",\n"
                        + "      \"name\" : \"LAYER_NAME#1\",\n"
                        + "      \"@type\" : \"layer\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#2\",\n"
                        + "      \"name\" : \"LAYER_NAME#2\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#3\",\n"
                        + "      \"name\" : \"LAYER_NAME#3\",\n"
                        + "      \"@type\" : \"layer\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#4\",\n"
                        + "      \"name\" : \"LAYER_NAME#4\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#5\",\n"
                        + "      \"name\" : \"LAYER_NAME#5\",\n"
                        + "      \"@type\" : \"layer\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#6\",\n"
                        + "      \"name\" : \"LAYER_NAME#6\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#7\",\n"
                        + "      \"name\" : \"LAYER_NAME#7\",\n"
                        + "      \"@type\" : \"layer\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#8\",\n"
                        + "      \"name\" : \"LAYER_NAME#8\"\n"
                        + "    }, {\n"
                        + "      \"link\" : \"LINK#9\",\n"
                        + "      \"name\" : \"LAYER_NAME#9\",\n"
                        + "      \"@type\" : \"layer\"\n"
                        + "    } ]\n"
                        + "  }\n"
                        + "}"), GPGeoserverLayerGroupPublishables.class);
        logger.info("#######################{}\n", layerGroupPublishables);
    }

    @Test
    public void i_unmarshallGeoserverLoadLayerGroupFromJsonStringTest() throws Exception {
        GeoserverLoadLayerGroup loadLayerGroup = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\"layerGroup\": { \n"
                + "  \"name\":\"spearfish\",\n"
                + "  \"mode\":\"SINGLE\",\n"
                + "  \"title\":\"Spearfish\",\n"
                + "  \"abstractTxt\":\"Spearfish City in Lawrence County, South Dakota\",\n"
                + "  \"publishables\":{\"published\":[\n"
                + "      {\"@type\":\"layer\",\n"
                + "      \"name\":\"sfdem\",\n"
                + "      \"href\":\"http://localhost:8080/geoserver/rest/workspaces/sf/layers/sfdem.json\"},\n"
                + "      {\"@type\":\"layer\",\n"
                + "      \"name\":\"streams\",\n"
                + "      \"href\":\"http://localhost:8080/geoserver/rest/workspaces/sf/layers/streams.json\"}]},\n"
                + "  \"styles\": {\"style\":[\n"
                + "      {\"name\":\"dem\",\n"
                + "      \"href\":\"http://localhost:8080/geoserver/rest/styles/dem.json\"}\n"
                + "      ]},\n"
                + "  \"bounds\": { \"minx\":589425.9342365642,\n"
                + "              \"maxx\":609518.6719560538,\n"
                + "              \"miny\":4913959.224611808,\n"
                + "              \"maxy\":4928082.949945881,\n"
                + "              \"crs\":{\"@class\":\"projected\",\"$\":\"EPSG:26713\"}},\n"
                + "  \"metadata\":{\"entry\":{\"@key\":\"rawStyleList\",\"$\":\"\"}}\n"
                + "  }\n"
                + "}"), GeoserverLoadLayerGroup.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_LOAD_LAYER_GROUP : {}\n", loadLayerGroup);
    }

    @Test
    public void l_unmarshallGeoserverLoadLayerGroupFromJsonStringTest() throws Exception {
        GeoserverLoadLayerGroup loadLayerGroup = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"layerGroup\":{\n"
                + "      \"name\":\"spearfish\",\n"
                + "      \"mode\":\"SINGLE\",\n"
                + "      \"publishables\":{\n"
                + "         \"published\":[\n"
                + "            {\n"
                + "               \"@type\":\"layer\",\n"
                + "               \"name\":\"sf:sfdem\",\n"
                + "               \"href\":\"http:\\/\\/150.145.141.180\\/geoserver\\/rest\\/workspaces\\/sf\\/layers\\/sfdem.json\"\n"
                + "            },\n"
                + "            {\n"
                + "               \"@type\":\"layer\",\n"
                + "               \"name\":\"sf:streams\",\n"
                + "               \"href\":\"http:\\/\\/150.145.141.180\\/geoserver\\/rest\\/workspaces\\/sf\\/layers\\/streams.json\"\n"
                + "            },\n"
                + "            {\n"
                + "               \"@type\":\"layer\",\n"
                + "               \"name\":\"sf:roads\",\n"
                + "               \"href\":\"http:\\/\\/150.145.141.180\\/geoserver\\/rest\\/workspaces\\/sf\\/layers\\/roads.json\"\n"
                + "            },\n"
                + "            {\n"
                + "               \"@type\":\"layer\",\n"
                + "               \"name\":\"sf:restricted\",\n"
                + "               \"href\":\"http:\\/\\/150.145.141.180\\/geoserver\\/rest\\/workspaces\\/sf\\/layers\\/restricted.json\"\n"
                + "            },\n"
                + "            {\n"
                + "               \"@type\":\"layer\",\n"
                + "               \"name\":\"sf:archsites\",\n"
                + "               \"href\":\"http:\\/\\/150.145.141.180\\/geoserver\\/rest\\/workspaces\\/sf\\/layers\\/archsites.json\"\n"
                + "            },\n"
                + "            {\n"
                + "               \"@type\":\"layer\",\n"
                + "               \"name\":\"sf:bugsites\",\n"
                + "               \"href\":\"http:\\/\\/150.145.141.180\\/geoserver\\/rest\\/workspaces\\/sf\\/layers\\/bugsites.json\"\n"
                + "            }\n"
                + "         ]\n"
                + "      },\n"
                + "      \"styles\":{\n"
                + "         \"style\":[\n"
                + "            \"null\",\n"
                + "            \"null\",\n"
                + "            \"null\",\n"
                + "            \"null\",\n"
                + "            \"null\",\n"
                + "            \"null\"\n"
                + "         ]\n"
                + "      },\n"
                + "      \"bounds\":{\n"
                + "         \"minx\":589425.9342365642,\n"
                + "         \"maxx\":609518.6719560538,\n"
                + "         \"miny\":4913959.224611808,\n"
                + "         \"maxy\":4928082.949945881,\n"
                + "         \"crs\":{\n"
                + "            \"@class\":\"projected\",\n"
                + "            \"$\":\"EPSG:26713\"\n"
                + "         }\n"
                + "      },\n"
                + "      \"metadata\":{\n"
                + "         \"entry\":{\n"
                + "            \"@key\":\"rawStyleList\",\n"
                + "            \"$\":\"\"\n"
                + "         }\n"
                + "      }\n"
                + "   }\n"
                + "}"), GeoserverLoadLayerGroup.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_LOAD_LAYER_GROUP : {}\n", loadLayerGroup);
    }

    /**
     * @param i
     * @return {@link List<GPGeoserverLayerPublished>}
     */
    GPGeoserverLayerPublished build(int i) {
        return i % 2 == 0 ? new GPGeoserverLayerPublished() {
            {
                super.setLayerName("LAYER_NAME#" + i);
                super.setLink("LINK#" + i);
            }
        } : new GPGeoserverLayerTypePublished() {
            {
                super.setType("layer");
                super.setLayerName("LAYER_NAME#" + i);
                super.setLink("LINK#" + i);
            }
        };
    }
}