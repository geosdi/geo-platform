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
package org.geosdi.geoplatform.connector.store.logging;

import org.geosdi.geoplatform.connector.geoserver.request.logging.GeoserverLoadLoggingRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverLoggingJacksonTest.toGeoserverLogging;
import static org.junit.Assert.assertFalse;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverLoggingConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverLoggingRequestTest() throws Exception {
        GeoserverLoadLoggingRequest loadLoggingRequest = geoserverConnectorStoreV2_27_x.loadLoggingRequest();
        logger.info("####################GEOSERVER_LOAD_LOGGING_RESPONSE : {}\n", loadLoggingRequest.getResponse());
    }

    @Test
    public void b_updateGeoserverLoggingRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_LOGGING_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x
                .updateLoggingRequest()
                .withBody(toGeoserverLogging())
                .getResponse());
    }

    @Test
    public void c_loadGeoserverLoggingRequestTest() throws Exception {
        GeoserverLoadLoggingRequest loadLoggingRequest = geoserverConnectorStoreV2_27_x.loadLoggingRequest();
        assertFalse(loadLoggingRequest.getResponse().isStdOutLogging());
    }

    @Test
    public void c_updateGeoserverLoggingRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_LOGGING_RESPONSE : {}\n", geoserverConnectorStoreV2_27_x
                .updateLoggingRequest()
                .withBody(toGeoserverLogging(TRUE))
                .getResponse());
    }
}