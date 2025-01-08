/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.gml.GMLEntry;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.gml.GMLEntryContainer;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static java.util.Arrays.asList;
import static org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.gml.GMLVersion.*;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GMLValueJacksonTest.toGMLValue;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GMLEntryContainerJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLEntryContainerJacksonTest.class);

    @Test
    public void a_marshallGMLEntryContainerAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GML_ENTRY_CONTAINER : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GMLEntryContainerJacksonTest::toGMLEntryContainer));
    }

    @Test
    public void b_unmarshallGMLEntryContainerFromXmlStringTest() throws Exception {
        logger.info("#####################GML_ENTRY_CONTAINER : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<gml>\n"
                        + "    <entry>\n"
                        + "        <version>V_10</version>\n"
                        + "        <gml>\n"
                        + "            <srsNameStyle>NORMAL</srsNameStyle>\n"
                        + "            <srsNameStyle>URN2</srsNameStyle>\n"
                        + "            <srsNameStyle>XML</srsNameStyle>\n"
                        + "            <overrideGMLAttributes>true</overrideGMLAttributes>\n"
                        + "        </gml>\n"
                        + "    </entry>\n"
                        + "    <entry>\n"
                        + "        <version>V_11</version>\n"
                        + "        <gml>\n"
                        + "            <srsNameStyle>NORMAL</srsNameStyle>\n"
                        + "            <srsNameStyle>URN2</srsNameStyle>\n"
                        + "            <srsNameStyle>XML</srsNameStyle>\n"
                        + "            <overrideGMLAttributes>true</overrideGMLAttributes>\n"
                        + "        </gml>\n"
                        + "    </entry>\n"
                        + "    <entry>\n"
                        + "        <version>V_20</version>\n"
                        + "        <gml>\n"
                        + "            <srsNameStyle>NORMAL</srsNameStyle>\n"
                        + "            <srsNameStyle>URN2</srsNameStyle>\n"
                        + "            <srsNameStyle>XML</srsNameStyle>\n"
                        + "            <overrideGMLAttributes>true</overrideGMLAttributes>\n"
                        + "        </gml>\n"
                        + "    </entry>\n"
                        + "</gml>"), GMLEntryContainer.class));
    }

    @Test
    public void c_marshallGMLEntryContainerAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GML_ENTRY_CONTAINER : \n{}\n", jacksonSupport.writeAsString(GMLEntryContainerJacksonTest::toGMLEntryContainer));
    }

    @Test
    public void d_unmarshallGMLEntryContainerFromJsonStringTest() throws Exception {
        logger.info("######################GML_ENTRY_CONTAINER : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"gml\" : {\n"
                        + "    \"entry\" : [ {\n"
                        + "      \"version\" : \"V_10\",\n"
                        + "      \"gml\" : {\n"
                        + "        \"srsNameStyle\" : [ \"NORMAL\", \"URN2\", \"XML\" ],\n"
                        + "        \"overrideGMLAttributes\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"version\" : \"V_11\",\n"
                        + "      \"gml\" : {\n"
                        + "        \"srsNameStyle\" : [ \"NORMAL\", \"URN2\", \"XML\" ],\n"
                        + "        \"overrideGMLAttributes\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"version\" : \"V_20\",\n"
                        + "      \"gml\" : {\n"
                        + "        \"srsNameStyle\" : [ \"NORMAL\", \"URN2\", \"XML\" ],\n"
                        + "        \"overrideGMLAttributes\" : true\n"
                        + "      }\n"
                        + "    } ]\n"
                        + "  }\n"
                        + "}"), GMLEntryContainer.class));
    }

    /**
     * @return {@link GMLEntryContainer}
     */
    public static GMLEntryContainer toGMLEntryContainer() {
        GMLEntryContainer container = new GMLEntryContainer();
        GMLEntry gmlEntry = new GMLEntry();
        gmlEntry.setVersion(V_10);
        gmlEntry.setGmlValue(toGMLValue());
        GMLEntry gmlEntry1 = new GMLEntry();
        gmlEntry1.setVersion(V_11);
        gmlEntry1.setGmlValue(toGMLValue());
        GMLEntry gmlEntry2 = new GMLEntry();
        gmlEntry2.setVersion(V_20);
        gmlEntry2.setGmlValue(toGMLValue());
        container.setEntries(asList(gmlEntry, gmlEntry1, gmlEntry2));
        return container;
    }
}