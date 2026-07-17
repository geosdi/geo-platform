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

import org.geosdi.geoplatform.connector.geoserver.model.styles.adapter.LocalDateTimeTypeAdapter;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Offline tests for {@link LocalDateTimeTypeAdapter} (styles module), the JAXB adapter that
 * (un)marshals {@code java.time.LocalDateTime} using the GeoServer {@code yyyy-MM-dd HH:mm:ss.SSS z}
 * format. Covers the null/blank/invalid branches (unmarshal is lenient and returns null on failure)
 * and the marshal/round-trip on the happy path.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverStylesLocalDateTimeAdapterTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverStylesLocalDateTimeAdapterTest.class);
    private static LocalDateTimeTypeAdapter adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new LocalDateTimeTypeAdapter();
    }

    @Test
    public void a_marshalNullReturnsNullTest() throws Exception {
        assertNull(adapter.marshal(null));
    }

    @Test
    public void b_unmarshalNullReturnsNullTest() throws Exception {
        assertNull(adapter.unmarshal(null));
    }

    @Test
    public void c_unmarshalBlankStringReturnsNullTest() throws Exception {
        assertNull(adapter.unmarshal("     "));
    }

    @Test
    public void d_unmarshalInvalidStringReturnsNullTest() throws Exception {
        // The adapter swallows parse errors and returns null instead of propagating them.
        assertNull(adapter.unmarshal("not-a-date"));
    }

    @Test
    public void e_marshalProducesGeoserverUtcFormatTest() throws Exception {
        // The adapter marshals using the named UTC zone, so the "z" token renders as "UTC",
        // matching the zone name GeoServer emits.
        String marshalled = adapter.marshal(LocalDateTime.of(2021, 3, 15, 10, 30, 45, 123_000_000));
        logger.info("################STYLES_MARSHALLED_DATE_TIME : {}\n", marshalled);
        assertEquals("2021-03-15 10:30:45.123 UTC", marshalled);
    }

    @Test
    public void f_roundTripPreservesDateTimeTest() throws Exception {
        LocalDateTime original = LocalDateTime.of(2019, 12, 1, 23, 59, 59, 999_000_000);
        LocalDateTime reparsed = adapter.unmarshal(adapter.marshal(original));
        logger.info("################STYLES_ROUND_TRIP : {} -> {}\n", original, reparsed);
        assertEquals(original, reparsed);
    }

    @Test
    public void g_unmarshalParsesGeoserverUtcTimestampTest() throws Exception {
        // GeoServer sends the zone as a name ("UTC"); the adapter must accept it, not silently drop it.
        LocalDateTime dateTime = adapter.unmarshal("2021-03-15 10:30:45.123 UTC");
        logger.info("################STYLES_UNMARSHALLED_UTC : {}\n", dateTime);
        assertEquals(LocalDateTime.of(2021, 3, 15, 10, 30, 45, 123_000_000), dateTime);
    }
}
