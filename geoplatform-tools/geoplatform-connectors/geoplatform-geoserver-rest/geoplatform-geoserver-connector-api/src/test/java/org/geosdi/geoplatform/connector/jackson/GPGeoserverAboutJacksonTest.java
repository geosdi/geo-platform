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

import org.geosdi.geoplatform.connector.geoserver.model.about.manifest.GPGeoserverAboutManifest;
import org.geosdi.geoplatform.connector.geoserver.model.about.manifest.GPGeoserverAboutManifestEntry;
import org.geosdi.geoplatform.connector.geoserver.model.about.version.GPGeoserverAboutVersion;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonXmlSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverAboutJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverAboutJacksonTest.class);

    @Test
    public void a_unmarshallGeoserverAboutVersionTest() throws Exception {
        GPGeoserverAboutVersion geoserverAboutVersion = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"about\":{\"resource\":[{\"@name\":\"GeoServer\",\"Build-Timestamp\"" +
                        ":\"21-Nov-2017 22:02\",\"Version\":\"2.12.1\",\"Git-Revision\":" +
                        "\"5927e49e781ddcdbf9213d32a439418347c17480\"},{\"@name\":\"GeoTools\",\"Build-Timestamp\":" +
                        "\"21-Nov-2017 14:18\",\"Version\":18.1,\"Git-Revision\":" +
                        "\"306cf3bdde1bee0110dc1c3ba77819f1e294a45b\"},{\"@name\":\"GeoWebCache\",\"Version\":\"1.12.1\"," +
                        "\"Git-Revision\":\"1.12.x\\/22d18b47c9e80316d563c28d280602cb3dde624c\"}]}}\n"), GPGeoserverAboutVersion.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_VERSION : {}\n", geoserverAboutVersion);
        StringWriter writer = new StringWriter();
        jacksonXmlSupport.getDefaultMapper().writeValue(writer, geoserverAboutVersion);
        logger.info("#########################GEOSERVER_ABOUT_VERSION_XML : \n{}\n", writer);
    }

    @Test
    public void b_unmarshallGeoserverAboutManifestTest() throws Exception {
        GPGeoserverAboutManifest geoserverAboutManifest = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath() , "src", "test", "resources",
                        "GeoserverAboutManifest.json")
                        .collect(joining(separator))), GPGeoserverAboutManifest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_MANIFEST : {}", geoserverAboutManifest);
        StringWriter writer = new StringWriter();
        jacksonXmlSupport.getDefaultMapper().writeValue(writer, geoserverAboutManifest);
        logger.info("#########################GEOSERVER_ABOUT_MANIFEST_XML : \n{}\n", writer);
    }

    @Test
    public void c_unmarshallGeoserverAboutManifestTest() throws Exception {
        GPGeoserverAboutManifest geoserverAboutManifest = jacksonXmlSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath() , "src", "test", "resources",
                        "GeoserverAboutManifest.xml")
                        .collect(joining(separator))), GPGeoserverAboutManifest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_MANIFEST : {}", geoserverAboutManifest);
    }

    @Test
    public void d_unmarshallGeoserverAboutManifestEntryTest() throws Exception {
        GPGeoserverAboutManifestEntry geoserverAboutManifest = jacksonXmlSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath() , "src", "test", "resources",
                        "GeoserverAboutManifestEntry.xml")
                        .collect(joining(separator))), GPGeoserverAboutManifestEntry.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_MANIFEST_ENTRY : {}", geoserverAboutManifest);
    }
}