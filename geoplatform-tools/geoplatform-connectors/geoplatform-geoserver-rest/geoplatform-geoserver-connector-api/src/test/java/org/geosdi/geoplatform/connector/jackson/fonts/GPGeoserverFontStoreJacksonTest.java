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
package org.geosdi.geoplatform.connector.jackson.fonts;

import org.geosdi.geoplatform.connector.geoserver.model.fonts.GPGeoserverFontStore;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverFontStoreJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverFontStoreJacksonTest.class);

    @Test
    public void a_unmarshallGPGeoserverFontStoreFromXmlFileTest() throws Exception {
        GPGeoserverFontStore fontStore = jacksonXmlSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GPGeoserverFontStore.xml")
                        .collect(joining(separator))), GPGeoserverFontStore.class);
        logger.info("######################GP_GEOSERVER_FONT_STORE_FROM_XML_FILE : {}\n", fontStore);
    }

    @Test
    public void b_unmarshallGPGeoserverFontStoreFromJsonFileTest() throws Exception {
        GPGeoserverFontStore fontStore = emptyJacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GPGeoserverFontStore.json")
                        .collect(joining(separator))), GPGeoserverFontStore.class);
        logger.info("######################GP_GEOSERVER_FONT_STORE_FROM_JSON_FILE : {}\n", fontStore);
    }

    @Test
    public void c_marshallGPGeoserverFontStoreAsJsonStringTest() throws Exception {
        GPGeoserverFontStore fontStore = emptyJacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GPGeoserverFontStore.json")
                        .collect(joining(separator))), GPGeoserverFontStore.class);
        logger.info("######################GP_GEOSERVER_FONT_STORE_AS_JSON_STRING : \n{}\n", emptyJacksonSupport.getDefaultMapper().writeValueAsString(fontStore));
    }

    @Test
    public void d_marshallGPGeoserverFontStoreAsXmlStringTest() throws Exception {
        GPGeoserverFontStore fontStore = jacksonXmlSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GPGeoserverFontStore.xml").collect(
                        joining(separator))), GPGeoserverFontStore.class);
        logger.info("######################GP_GEOSERVER_FONT_STORE_AS_JSON_STRING : \n{}\n", jacksonXmlSupport.getDefaultMapper().writeValueAsString(fontStore));
    }
}