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

import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionParam;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.GPGeoserverMetadataParam;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Offline round-trip tests for the GeoServer {@code {"@key": ..., "$": ...}} entry
 * convention, produced by {@code GPGenericEntryTypeSerializer} and consumed by the
 * {@code GPGenericEntryTypeDeserializer} subclasses. This format is used by GeoServer
 * for connection parameters and metadata entries and is completely covered here without
 * a live server, guarding it against Jackson (de)serialization regressions.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverGenericEntryTypeSerializerTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverGenericEntryTypeSerializerTest.class);
    private static JsonMapper mapper;

    @BeforeClass
    public static void setUp() {
        // Same mapper used in production, but with root wrap/unwrap disabled: these entries are
        // always serialized nested inside a parent container (List<...> entry), never as a root
        // value, so root wrapping never applies to them and would otherwise mask the entry format.
        mapper = new GPJacksonSupport().getDefaultMapper().rebuild()
                .disable(SerializationFeature.WRAP_ROOT_VALUE)
                .disable(DeserializationFeature.UNWRAP_ROOT_VALUE)
                .build();
    }

    @Test
    public void a_connectionParamSerializeToKeyDollarFormatTest() throws Exception {
        String json = mapper.writeValueAsString(new GPGeoserverConnectionParam("host", "localhost"));
        logger.info("################CONNECTION_PARAM_JSON : {}\n", json);
        JsonNode node = mapper.readTree(json);
        assertEquals("localhost", node.get("$").asString());
        assertEquals("host", node.get("@key").asString());
    }

    @Test
    public void b_connectionParamRoundTripTest() throws Exception {
        String json = mapper.writeValueAsString(new GPGeoserverConnectionParam("port", "5432"));
        GPGeoserverConnectionParam read = mapper.readValue(json, GPGeoserverConnectionParam.class);
        assertEquals("port", read.getKey());
        assertEquals("5432", read.getValue());
    }

    @Test
    public void c_connectionParamDeserializeFromGeoserverJsonTest() throws Exception {
        GPGeoserverConnectionParam read = mapper.readValue("{\"@key\":\"user\",\"$\":\"admin\"}", GPGeoserverConnectionParam.class);
        assertEquals("user", read.getKey());
        assertEquals("admin", read.getValue());
    }

    @Test
    public void d_metadataParamSerializeToKeyDollarFormatTest() throws Exception {
        String json = mapper.writeValueAsString(new GPGeoserverMetadataParam("cachingEnabled", "true"));
        logger.info("################METADATA_PARAM_JSON : {}\n", json);
        JsonNode node = mapper.readTree(json);
        assertEquals("cachingEnabled", node.get("@key").asString());
        assertEquals("true", node.get("$").asString());
    }

    @Test
    public void e_metadataParamRoundTripTest() throws Exception {
        String json = mapper.writeValueAsString(new GPGeoserverMetadataParam("dirName", "wms_stores"));
        GPGeoserverMetadataParam read = mapper.readValue(json, GPGeoserverMetadataParam.class);
        assertEquals("dirName", read.getKey());
        assertEquals("wms_stores", read.getValue());
    }

    @Test
    public void f_metadataParamNullValueSerializesAsLiteralNullTest() throws Exception {
        // GPGeoserverMetadataParam.getValue() maps a null value to the literal string "null".
        String json = mapper.writeValueAsString(new GPGeoserverMetadataParam("emptyKey", null));
        logger.info("################METADATA_PARAM_NULL_JSON : {}\n", json);
        JsonNode node = mapper.readTree(json);
        assertEquals("emptyKey", node.get("@key").asString());
        assertEquals("null", node.get("$").asString());
    }

    @Test
    public void g_connectionParamDeserializeIgnoresUnknownPropertiesTest() throws Exception {
        // The deserializer only reacts to "@key" and "$"; any extra property must be skipped.
        GPGeoserverConnectionParam read = mapper.readValue("{\"@key\":\"dbtype\",\"$\":\"postgis\",\"ignored\":\"x\"}", GPGeoserverConnectionParam.class);
        assertEquals("dbtype", read.getKey());
        assertEquals("postgis", read.getValue());
        assertTrue(read.getValue().equals("postgis"));
    }
}