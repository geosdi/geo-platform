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

import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionMapParamType;
import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionParam;
import org.geosdi.geoplatform.connector.geoserver.model.connection.adapter.GPGeoserverConnectionMapParamAdapter;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Offline tests for {@link GPGeoserverConnectionMapParamAdapter}, the JAXB adapter that converts
 * between a {@code Map<String,String>} and the {@code entry}-list wire type used for datastore
 * connection parameters.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverConnectionMapParamAdapterTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverConnectionMapParamAdapterTest.class);
    private static GPGeoserverConnectionMapParamAdapter adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new GPGeoserverConnectionMapParamAdapter();
    }

    @Test
    public void a_marshalMapToEntryTypeTest() throws Exception {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("host", "localhost");
        map.put("port", "5432");
        GPGeoserverConnectionMapParamType mapType = adapter.marshal(map);
        List<GPGeoserverConnectionParam> entries = mapType.getEntry();
        logger.info("################CONNECTION_MAP_ENTRIES : {}\n", entries);
        assertEquals(2, entries.size());
        assertEquals("host", entries.get(0).getKey());
        assertEquals("localhost", entries.get(0).getValue());
        assertEquals("port", entries.get(1).getKey());
        assertEquals("5432", entries.get(1).getValue());
    }

    @Test
    public void b_roundTripPreservesEntriesTest() throws Exception {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("dbtype", "postgis");
        map.put("schema", "public");
        Map<String, String> read = adapter.unmarshal(adapter.marshal(map));
        assertEquals(map, read);
    }

    @Test
    public void c_marshalNullMapProducesEmptyEntriesTest() throws Exception {
        // A null map is tolerated and yields an empty entry list rather than throwing.
        GPGeoserverConnectionMapParamType mapType = adapter.marshal(null);
        assertTrue(mapType.getEntry().isEmpty());
    }

    @Test
    public void d_unmarshalNullTypeThrowsTest() throws Exception {
        try {
            adapter.unmarshal(null);
            fail("Expected unmarshal(null) to be rejected");
        } catch (IllegalArgumentException expected) {
            logger.info("################CONNECTION_MAP_UNMARSHAL_NULL : {}\n", expected.getMessage());
        }
    }
}