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
package org.geosdi.geoplatform.connector.store.wmts.store;

import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GPGeoserverWMTSStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GPGeoserverWMTSStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.wmts.GPGeoserverWMTSStores;
import org.geosdi.geoplatform.connector.geoserver.request.wmts.store.GeoserverCreateWMTSStoreRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV223xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.geosdi.geoplatform.connector.jackson.wmts.GeoserverWMTSStoresJacksonTest.toWMTSStore;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverWMTSStoreConnectorStoreV223XTest extends GPBaseGeoserverConnectorStoreV223xTest {

    @Test
    public void a_loadGeoserverWMTSStoresRequestTest() throws Exception {
        GPGeoserverWMTSStores wmtsStores = geoserverConnectorStoreV2_23_x.loadWorkspaceWMTSStoresRequest()
                .withWorkspace("sf")
                .getResponse();
        assertTrue(wmtsStores.isEmpty());
    }

    @Test
    public void b_createGeoserverWMTSStoreRequestTest() throws Exception {
        GeoserverCreateWMTSStoreRequest request = geoserverConnectorStoreV2_23_x.createWMTSStoreRequest()
                .withWorkspace("sf")
                .withBody(toWMTSStore("http://150.145.141.92/geoserver/gwc/service/wmts?REQUEST=GetCapabilities"));
        logger.info("######################{}\n", request.getResponse());
    }

    @Test
    public void c_loadGeoserverWMTSStoresRequestTest() throws Exception {
        GPGeoserverWMTSStores wmsStores = geoserverConnectorStoreV2_23_x.loadWorkspaceWMTSStoresRequest()
                .withWorkspace("sf")
                .getResponse();
        assertTrue(wmsStores.getStores().size() == 1);
        assertTrue(wmsStores.getStores().get(0).getName().equals("remote_wmts"));
    }

    @Test
    public void d_loadGeoserverWMTSStoreRequestTest() throws Exception {
        GPGeoserverWMTSStore wmtsStore = geoserverConnectorStoreV2_23_x.loadWorkspaceWMTSStoreRequest()
                .withWorkspace("sf")
                .withStore("remote_wmts").getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@WMTS_STORE : {}\n", wmtsStore);
    }

    @Test
    public void e_updateGeoserverWMTSStoreRequestTest() throws Exception {
        GPGeoserverWMTSStore wmtsStore = geoserverConnectorStoreV2_23_x.loadWorkspaceWMTSStoreRequest()
                .withWorkspace("sf")
                .withStore("remote_wmts").getResponse();
        assertNotNull(wmtsStore);
        GPGeoserverWMTSStoreBody wmtsStoreBody = toWMTSStore("http://150.145.141.92/geoserver/gwc/service/wmts?REQUEST=GetCapabilities");
        wmtsStoreBody.setDescription("the_remote_wmts_description_test");
        wmtsStoreBody.getMetadata().put("useConnectionPooling", FALSE.toString());
        logger.info("{}\n", geoserverConnectorStoreV2_23_x.updateWMTSStoreRequest()
                .withWorkspace("sf")
                .withStore("remote_wmts")
                .withBody(wmtsStoreBody).getResponse());
    }

    @Test
    public void f_deleteGeoserverWMTSStoreRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_DELETE_WMTS_STORE_RESPONSE : {}\n", geoserverConnectorStoreV2_23_x
                .deleteWMTSStoreRequest().withWorkspace("sf").withStore("remote_wmts").getResponse());
    }
}