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
package org.geosdi.geoplatform.connector.store.wms.store;

import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStore;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStores;
import org.geosdi.geoplatform.connector.geoserver.request.wms.store.GeoserverCreateWMSStoreRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static org.geosdi.geoplatform.connector.jackson.wms.store.GeoserverWMSStoresJacksonTest.toWMSStore;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverWMSStoreConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverWMSStoresRequestTest() throws Exception {
        GPGeoserverWMSStores wmsStores = geoserverConnectorStoreV2_28_x.loadWorkspaceWMSStoresRequest()
                .withWorkspace("sf")
                .getResponse();
        assertNotNull(wmsStores);
        assertTrue(wmsStores.isEmpty());
    }

    @Test
    public void b_createGeoserverWMSStoreRequestTest() throws Exception {
        GeoserverCreateWMSStoreRequest request = geoserverConnectorStoreV2_28_x.createWMSStoreRequest()
                .withWorkspace("sf")
                .withBody(toWMSStore("http://150.145.141.180/geoserver/ows?service=wms&version=1.1.1&request=GetCapabilities"));
        logger.info("######################{}\n", request.getResponse());
    }

    @Test
    public void c_loadGeoserverWMSStoresRequestTest() throws Exception {
        GPGeoserverWMSStores wmsStores = geoserverConnectorStoreV2_28_x.loadWorkspaceWMSStoresRequest()
                .withWorkspace("sf")
                .getResponse();
        assertNotNull(wmsStores);
        assertTrue(wmsStores.getStores().size() == 1);
        assertTrue(wmsStores.getStores().get(0).getName().equals("remote"));
    }

    @Test
    public void d_loadGeoserverWMSStoreRequestTest() throws Exception {
        GPGeoserverWMSStore wmsStore = geoserverConnectorStoreV2_28_x.loadWorkspaceWMSStoreRequest()
                .withWorkspace("sf")
                .withStore("remote").getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@WMS_STORE : {}\n", wmsStore);
    }

    @Test
    public void e_updateGeoserverWMSStoreRequestTest() throws Exception {
        GPGeoserverWMSStore wmsStore = geoserverConnectorStoreV2_28_x.loadWorkspaceWMSStoreRequest()
                .withWorkspace("sf")
                .withStore("remote").getResponse();
        assertNotNull(wmsStore);
        GPGeoserverWMSStoreBody wmsStoreBody = toWMSStore("http://150.145.141.180/geoserver/ows?service=wms&version=1.1.1&request=GetCapabilities");
        wmsStoreBody.setDescription("the_remote_description_test");
        wmsStoreBody.getMetadata().put("useConnectionPooling", FALSE.toString());
        logger.info("{}\n", geoserverConnectorStoreV2_28_x.updateWMSStoreRequest()
                .withWorkspace("sf")
                .withStore("remote")
                .withBody(wmsStoreBody).getResponse());
    }

    @Test
    public void f_deleteGeoserverWMSStoreRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_DELETE_WMS_STORE_RESPONSE : {}\n", geoserverConnectorStoreV2_28_x
                .deleteWMSStoreRequest().withWorkspace("sf").withStore("remote").getResponse());
    }
}