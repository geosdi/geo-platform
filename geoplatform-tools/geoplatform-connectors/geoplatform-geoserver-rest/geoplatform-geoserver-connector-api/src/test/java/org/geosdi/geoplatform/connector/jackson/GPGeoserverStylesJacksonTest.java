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

import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverStyles;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverStylesJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverStylesJacksonTest.class);

    @Test
    public void a_unmarshallGPGeoserverStylesFromXmlStringTest() throws Exception {
        logger.info("#################GP_GEOSERVER_STYLES : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<styles>\n"
                        + "  <style>\n"
                        + "    <name>pophatch</name>\n"
                        + "    <atom:link xmlns:atom=\"http://www.w3.org/2005/Atom\" " +
                        "rel=\"alternate\" href=\"http://localhost:8080/geoserver/rest/layers/topp:states/styles/pophatch.xml\"" +
                        " type=\"application/xml\"/>\n"
                        + "  </style>\n"
                        + "  <style>\n"
                        + "    <name>polygon</name>\n"
                        + "    <atom:link xmlns:atom=\"http://www.w3.org/2005/Atom\" rel=\"alternate\"" +
                        " href=\"http://localhost:8080/geoserver/rest/layers/topp:states/styles/polygon.xml\" " +
                        "type=\"application/xml\"/>\n"
                        + "  </style>\n"
                        + "</styles>"), GPGeoserverStyles.class));
    }
}