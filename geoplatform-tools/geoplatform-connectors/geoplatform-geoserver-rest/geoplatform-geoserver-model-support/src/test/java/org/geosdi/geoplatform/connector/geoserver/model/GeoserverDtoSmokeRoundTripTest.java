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
package org.geosdi.geoplatform.connector.geoserver.model;

import org.geosdi.geoplatform.connector.geoserver.model.about.manifest.GPGeoserverAboutManifestEntry;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.GPGeoserverNamespaceBody;
import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUser;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspace;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Smoke round-trip tests for a representative sample of {@code @XmlRootElement} DTOs, using the
 * production Jackson mapper unchanged (root wrap/unwrap on, exactly as GeoServer wraps its
 * responses). Acts as an offline safety net against Jackson 3 (de)serialization regressions on the
 * real data objects: it exercises JAXB element/attribute name mapping and the {@code @JsonProperty}
 * conventions without a live server.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverDtoSmokeRoundTripTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverDtoSmokeRoundTripTest.class);
    private static JsonMapper mapper;

    @BeforeClass
    public static void setUp() {
        mapper = new GPJacksonSupport().getDefaultMapper();
    }

    @Test
    public void a_workspaceRoundTripTest() throws Exception {
        String json = mapper.writeValueAsString(new GPGeoserverWorkspace("topp", "http://localhost:8080/geoserver/rest/workspaces/topp.json"));
        logger.info("################WORKSPACE_JSON : {}\n", json);
        GPGeoserverWorkspace read = mapper.readValue(json, GPGeoserverWorkspace.class);
        assertEquals("topp", read.getWorkspaceName());
        assertEquals("http://localhost:8080/geoserver/rest/workspaces/topp.json", read.getValue());
    }

    @Test
    public void b_userRoundTripTest() throws Exception {
        GPGeoserverUser user = new GPGeoserverUser();
        user.setUserName("admin");
        user.setEnabled(true);
        GPGeoserverUser read = mapper.readValue(mapper.writeValueAsString(user), GPGeoserverUser.class);
        assertEquals("admin", read.getUserName());
        assertTrue(read.isEnabled());
    }

    @Test
    public void c_namespaceBodySerializationTest() throws Exception {
        // GPGeoserverNamespaceBody is a write-only request body (only @AllArgsConstructor, so it is
        // never deserialized in production); assert the serialized payload rather than a round-trip.
        String json = mapper.writeValueAsString(new GPGeoserverNamespaceBody("topp", "http://www.openplans.org/topp"));
        logger.info("################NAMESPACE_JSON : {}\n", json);
        assertTrue(json.contains("\"prefix\""));
        assertTrue(json.contains("topp"));
        assertTrue(json.contains("http://www.openplans.org/topp"));
    }

    @Test
    public void d_aboutManifestEntryNamePropertyTest() throws Exception {
        // The name is exposed as the "@name" JSON property (GeoServer convention) via @JsonProperty.
        GPGeoserverAboutManifestEntry entry = new GPGeoserverAboutManifestEntry();
        entry.setName("gs-main");
        String json = mapper.writeValueAsString(entry);
        logger.info("################ABOUT_MANIFEST_JSON : {}\n", json);
        assertTrue(json.contains("\"@name\""));
        GPGeoserverAboutManifestEntry read = mapper.readValue(json, GPGeoserverAboutManifestEntry.class);
        assertEquals("gs-main", read.getName());
    }
}