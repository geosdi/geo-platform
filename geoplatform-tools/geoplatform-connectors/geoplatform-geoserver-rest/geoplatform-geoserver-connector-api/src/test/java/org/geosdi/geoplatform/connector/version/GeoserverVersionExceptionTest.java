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
package org.geosdi.geoplatform.connector.version;

import org.apache.hc.core5.net.URIBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;
import static org.geosdi.geoplatform.connector.GeoserverVersion.toVersionExceptionMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GeoserverVersionExceptionTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverVersionExceptionTest.class);

    @Test
    public void a_printGeoserverVersionExceptionMessageTest() {
        logger.info("########################GP_GEOSERVER_CONNECTOR_EXCEPTION_MESSAGE : {}\n", toVersionExceptionMessage());
    }

    @Test
    public void b_simpleTest() throws Exception {
        String baseURI = "http://150.145.141.180/geoserver/rest";
        String styleName = "pippo";
        String recurse = "true";
        String purge = "false";
        logger.info("{}\n", new URIBuilder((baseURI.endsWith("/") ? baseURI.concat("styles/").concat(styleName) : baseURI.concat("/styles/").concat(styleName)))
                .addParameter("recurse", recurse)
                .addParameter("purge", purge)
                .build().toString());
    }

    @Test
    public void c_localDateTimeTest() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.parse("2021-09-29 13:45:10.979 UTC", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z"));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(localDateTime.toInstant(UTC), UTC);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z");
        String p = dtf.format(zonedDateTime);
        logger.info("{}\n", p);
        logger.info("###############{}\n", LocalDateTime.parse(p, dtf));
    }
}