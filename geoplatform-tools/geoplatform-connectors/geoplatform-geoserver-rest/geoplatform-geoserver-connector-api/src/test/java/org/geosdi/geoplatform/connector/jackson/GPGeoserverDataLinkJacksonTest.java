/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.link.GPGeoserverDataLink;
import org.geosdi.geoplatform.connector.geoserver.model.link.GPGeoserverDataLinks;
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
public class GPGeoserverDataLinkJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverDataLinkJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverDataLinksAsXmlStringTest() throws Exception {
        GPGeoserverDataLinks dataLinks = toDataLinks(5);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_DATA_LINKS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(dataLinks));
    }

    @Test
    public void b_unmarshallGPGeoserverDataLinksFromXmlStringTest() throws Exception {
        GPGeoserverDataLinks dataLinks = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<dataLinks>\n"
                        + "    <org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "        <type>TYPE#0</type>\n"
                        + "        <content>CONTENT#0</content>\n"
                        + "    </org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "    <org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "        <type>TYPE#1</type>\n"
                        + "        <content>CONTENT#1</content>\n"
                        + "    </org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "    <org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "        <type>TYPE#2</type>\n"
                        + "        <content>CONTENT#2</content>\n"
                        + "    </org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "    <org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "        <type>TYPE#3</type>\n"
                        + "        <content>CONTENT#3</content>\n"
                        + "    </org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "    <org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "        <type>TYPE#4</type>\n"
                        + "        <content>CONTENT#4</content>\n"
                        + "    </org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "</dataLinks>"), GPGeoserverDataLinks.class);
        logger.info("###################GP_GEOSERVER_DATA_LINKS : {}\n", dataLinks);
    }

    @Test
    public void c_marshallGPGeoserverDataLinksAsJsonStringTest() throws Exception {
        GPGeoserverDataLinks dataLinks = toDataLinks(10);
        logger.info("@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_DATA_LINKS : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(dataLinks));
    }

    @Test
    public void d_unmarshallGPGeoserverDataLinksFromJsonStringTest() throws Exception {
        GPGeoserverDataLinks dataLinks = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"dataLinks\" : {\n"
                + "    \"org.geoserver.catalog.impl.DataLinkInfoImpl\" : [ {\n"
                + "      \"type\" : \"TYPE#0\",\n"
                + "      \"content\" : \"CONTENT#0\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#1\",\n"
                + "      \"content\" : \"CONTENT#1\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#2\",\n"
                + "      \"content\" : \"CONTENT#2\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#3\",\n"
                + "      \"content\" : \"CONTENT#3\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#4\",\n"
                + "      \"content\" : \"CONTENT#4\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#5\",\n"
                + "      \"content\" : \"CONTENT#5\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#6\",\n"
                + "      \"content\" : \"CONTENT#6\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#7\",\n"
                + "      \"content\" : \"CONTENT#7\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#8\",\n"
                + "      \"content\" : \"CONTENT#8\"\n"
                + "    }, {\n"
                + "      \"type\" : \"TYPE#9\",\n"
                + "      \"content\" : \"CONTENT#9\"\n"
                + "    } ]\n"
                + "  }\n"
                + "}"), GPGeoserverDataLinks.class);
        logger.info("###################GP_GEOSERVER_DATA_LINKS : {}\n", dataLinks);
    }

    @Test
    public void e_marshallGPGeoserverDataLinkAsXmlStringTest() throws Exception {
        GPGeoserverDataLink dataLink = toDataLink(0);
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_DATA_LINK : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(dataLink));
    }

    @Test
    public void f_unmarshallGPGeoserverDataLinkFromXmlStringTest() throws Exception {
        GPGeoserverDataLink dataLink = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<org.geoserver.catalog.impl.DataLinkInfoImpl>\n"
                        + "    <type>TYPE#0</type>\n"
                        + "    <content>CONTENT#0</content>\n"
                        + "</org.geoserver.catalog.impl.DataLinkInfoImpl>"), GPGeoserverDataLink.class);
        logger.info("####################GP_GEOSERVER_DATA_LINK : {}\n", dataLink);
    }

    @Test
    public void g_marshallGPGeoserverDataLinkAsJsonStringTest() throws Exception {
        GPGeoserverDataLink dataLink = toDataLink(0);
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_DATA_LINK : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(dataLink));
    }

    @Test
    public void h_unmarshallGPGeoserverDataLinkFromJsonStringTest() throws Exception {
        GPGeoserverDataLink dataLink = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"org.geoserver.catalog.impl.DataLinkInfoImpl\" : {\n"
                + "    \"type\" : \"TYPE#0\",\n"
                + "    \"content\" : \"CONTENT#0\"\n"
                + "  }\n"
                + "}"), GPGeoserverDataLink.class);
        logger.info("####################GP_GEOSERVER_DATA_LINK : {}\n", dataLink);
    }

    /**
     * @param number
     * @return {@link GPGeoserverDataLinks}
     */
    public static GPGeoserverDataLinks toDataLinks(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPGeoserverDataLinks dataLinks = new GPGeoserverDataLinks();
        dataLinks.setDataLinks(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPGeoserverDataLinkJacksonTest::toDataLink)
                .collect(toList()));
        return dataLinks;
    }

    /**
     * @param value
     * @return {@link GPGeoserverDataLink}
     */
    public static GPGeoserverDataLink toDataLink(Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        GPGeoserverDataLink metadataLink = new GPGeoserverDataLink();
        metadataLink.setType("TYPE#" + value);
        metadataLink.setContent("CONTENT#" + value);
        return metadataLink;
    }
}