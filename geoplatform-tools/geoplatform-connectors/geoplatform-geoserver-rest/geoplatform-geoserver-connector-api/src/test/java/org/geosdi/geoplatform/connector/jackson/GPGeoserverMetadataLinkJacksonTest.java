/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.metadata.link.GPGeoserverMetadataLink;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.link.GPGeoserverMetadataLinks;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverMetadataLinkJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverMetadataLinkJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverMetadataLinksAsXmlStringTest() throws Exception {
        GPGeoserverMetadataLinks metadataLinks = toMetadataLinks(5);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_LINKS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(metadataLinks));
    }

    @Test
    public void b_unmarshallGPGeoserverMetadataLinksFromXmlStringTest() throws Exception {
        GPGeoserverMetadataLinks metadataLinks = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<metadataLinks>\n"
                        + "    <metadataLink>\n"
                        + "        <type>TYPE#0</type>\n"
                        + "        <metadataType>METADATA_TYPE#0</metadataType>\n"
                        + "        <content>CONTENT#0</content>\n"
                        + "    </metadataLink>\n"
                        + "    <metadataLink>\n"
                        + "        <type>TYPE#1</type>\n"
                        + "        <metadataType>METADATA_TYPE#1</metadataType>\n"
                        + "        <content>CONTENT#1</content>\n"
                        + "    </metadataLink>\n"
                        + "    <metadataLink>\n"
                        + "        <type>TYPE#2</type>\n"
                        + "        <metadataType>METADATA_TYPE#2</metadataType>\n"
                        + "        <content>CONTENT#2</content>\n"
                        + "    </metadataLink>\n"
                        + "    <metadataLink>\n"
                        + "        <type>TYPE#3</type>\n"
                        + "        <metadataType>METADATA_TYPE#3</metadataType>\n"
                        + "        <content>CONTENT#3</content>\n"
                        + "    </metadataLink>\n"
                        + "    <metadataLink>\n"
                        + "        <type>TYPE#4</type>\n"
                        + "        <metadataType>METADATA_TYPE#4</metadataType>\n"
                        + "        <content>CONTENT#4</content>\n"
                        + "    </metadataLink>\n"
                        + "</metadataLinks>"), GPGeoserverMetadataLinks.class);
        logger.info("#####################GP_GEOSERVER_METADATA_LINKS : {}\n", metadataLinks);
    }

    @Test
    public void c_marshallGPGeoserverMetadataLinksAsJsonStringTest() throws Exception {
        GPGeoserverMetadataLinks metadataLinks = toMetadataLinks(10);
        logger.info("@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_LINKS : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(metadataLinks));
    }

    @Test
    public void d_unmarshallGPGeoserverMetadataLinksFromJsonStringTest() throws Exception {
        GPGeoserverMetadataLinks metadataLinks = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"metadataLinks\" : {\n"
                + "    \"metadataLink\" : [ {\n"
                + "      \"type\" : \"TYPE#0\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#0\",\n"
                + "      \"content\" : \"CONTENT#0\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#1\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#1\",\n"
                + "      \"content\" : \"CONTENT#1\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#2\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#2\",\n"
                + "      \"content\" : \"CONTENT#2\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#3\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#3\",\n"
                + "      \"content\" : \"CONTENT#3\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#4\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#4\",\n"
                + "      \"content\" : \"CONTENT#4\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#5\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#5\",\n"
                + "      \"content\" : \"CONTENT#5\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#6\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#6\",\n"
                + "      \"content\" : \"CONTENT#6\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#7\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#7\",\n"
                + "      \"content\" : \"CONTENT#7\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#8\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#8\",\n"
                + "      \"content\" : \"CONTENT#8\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#9\",\n"
                + "      \"metadataType\" : \"METADATA_TYPE#9\",\n"
                + "      \"content\" : \"CONTENT#9\"\n"
                + "    } ]\n"
                + "  }\n"
                + "}"), GPGeoserverMetadataLinks.class);
        logger.info("#######################GP_GEOSERVER_METADATA_LINKS : {}\n", metadataLinks);
    }

    @Test
    public void e_marshallGPGeoserverMetadataLinkAsXmlStringTest() throws Exception {
        GPGeoserverMetadataLink metadataLink = toMetadataLink(0);
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_LINK : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(metadataLink));
    }

    @Test
    public void f_unmarshallGPGeoserverMetadataLinkFromXmlStringTest() throws Exception {
        GPGeoserverMetadataLink metadataLink = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<metadataLink>\n"
                        + "    <type>TYPE#0</type>\n"
                        + "    <metadataType>METADATA_TYPE#0</metadataType>\n"
                        + "    <content>CONTENT#0</content>\n"
                        + "</metadataLink>"), GPGeoserverMetadataLink.class);
        logger.info("####################GP_GEOSERVER_METADATA_LINK : {}\n", metadataLink);
    }

    @Test
    public void g_marshallGPGeoserverMetadataLinkAsJsonStringTest() throws Exception {
        GPGeoserverMetadataLink metadataLink = toMetadataLink(0);
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_LINK : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(metadataLink));
    }

    @Test
    public void h_unmarshallGPGeoserverMetadataLinkFromJsonStringTest() throws Exception {
        GPGeoserverMetadataLink metadataLink = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"metadataLink\" : {\n"
                        + "    \"type\" : \"TYPE#0\",\n"
                        + "    \"metadataType\" : \"METADATA_TYPE#0\",\n"
                        + "    \"content\" : \"CONTENT#0\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverMetadataLink.class);
        logger.info("####################GP_GEOSERVER_METADATA_LINK : {}\n", metadataLink);
    }

    /**
     * @param number
     * @return {@link GPGeoserverMetadataLinks}
     */
    public static GPGeoserverMetadataLinks toMetadataLinks(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPGeoserverMetadataLinks metadataLinks = new GPGeoserverMetadataLinks();
        metadataLinks.setMetadataLinks(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPGeoserverMetadataLinkJacksonTest::toMetadataLink)
                .collect(toList()));
        return metadataLinks;
    }

    /**
     * @param value
     * @return {@link GPGeoserverMetadataLink}
     */
    public static GPGeoserverMetadataLink toMetadataLink(Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        GPGeoserverMetadataLink metadataLink = new GPGeoserverMetadataLink();
        metadataLink.setMetadataType("METADATA_TYPE#" + value);
        metadataLink.setType("TYPE#" + value);
        metadataLink.setContent("CONTENT#" + value);
        return metadataLink;
    }
}