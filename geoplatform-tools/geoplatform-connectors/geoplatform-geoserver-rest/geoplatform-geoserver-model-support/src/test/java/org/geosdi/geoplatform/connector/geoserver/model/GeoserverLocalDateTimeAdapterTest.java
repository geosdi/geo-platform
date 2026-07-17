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

import org.geosdi.geoplatform.connector.geoserver.model.adapter.GPLocalDateTimeAdpater;
import org.joda.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * Offline tests for {@link GPLocalDateTimeAdpater}, the JAXB adapter that (un)marshals the
 * GeoServer {@code yyyy-MM-dd HH:mm:ss.SSS z} timestamps used for {@code dateCreated} /
 * {@code dateModified}. Covers the null/blank branches and the parse of the wire format.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoserverLocalDateTimeAdapterTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverLocalDateTimeAdapterTest.class);
    private static GPLocalDateTimeAdpater adapter;

    @BeforeClass
    public static void setUp() {
        adapter = new GPLocalDateTimeAdpater();
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
    public void c_unmarshalEmptyStringReturnsNullTest() throws Exception {
        assertNull(adapter.unmarshal(""));
    }

    @Test
    public void d_unmarshalBlankStringReturnsNullTest() throws Exception {
        assertNull(adapter.unmarshal("     "));
    }

    @Test
    public void e_unmarshalParsesGeoserverTimestampTest() throws Exception {
        LocalDateTime dateTime = adapter.unmarshal("2021-03-15 10:30:45.123 UTC");
        logger.info("################UNMARSHALLED_DATE_TIME : {}\n", dateTime);
        assertEquals(2021, dateTime.getYear());
        assertEquals(3, dateTime.getMonthOfYear());
        assertEquals(15, dateTime.getDayOfMonth());
        assertEquals(10, dateTime.getHourOfDay());
        assertEquals(30, dateTime.getMinuteOfHour());
        assertEquals(45, dateTime.getSecondOfMinute());
        assertEquals(123, dateTime.getMillisOfSecond());
    }

    @Test
    public void f_marshalEmitsDateTimePortionWithoutZoneTest() throws Exception {
        String marshalled = adapter.marshal(new LocalDateTime(2021, 3, 15, 10, 30, 45, 123));
        logger.info("################MARSHALLED_DATE_TIME : {}\n", marshalled);
        assertEquals("2021-03-15 10:30:45.123", marshalled);
    }

    @Test
    public void g_roundTripPreservesDateTimeTest() throws Exception {
        // The zone is an optional parse-only section, so the marshalled value (which carries no zone,
        // since a LocalDateTime has none) parses straight back to the original value.
        LocalDateTime original = adapter.unmarshal("2019-12-01 23:59:59.999 UTC");
        LocalDateTime reparsed = adapter.unmarshal(adapter.marshal(original));
        assertEquals(original, reparsed);
    }

    @Test
    public void h_unmarshalWithoutZoneTest() throws Exception {
        LocalDateTime dateTime = adapter.unmarshal("2021-03-15 10:30:45.123");
        assertEquals(new LocalDateTime(2021, 3, 15, 10, 30, 45, 123), dateTime);
    }
}