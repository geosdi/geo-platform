/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store.https;

import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutStatusRequest;
import org.geosdi.geoplatform.connector.geoserver.request.about.GPGeoserverAboutVersionRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GPGeoserverLoadWorkspacesRequest;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPHttpsGeoserverConnectorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPHttpsGeoserverConnectorStoreTest.class);
    //
    private static final String geoserverURL = "https://vvf-toscana.geosdi.org/geoserver/rest";
    private static GPGeoserverConnectorStore httpsGeoserverConnectorStore;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        httpsGeoserverConnectorStore = geoserverConnectorBuilder()
                .withServerUrl(new URL(geoserverURL))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(40)
                        .withDefaultMaxPerRoute(10)
                        .withMaxRedirect(2)
                        .build())
                .build();
    }

    @Test(expected = UnauthorizedException.class)
    public void a_aboutHttpsVersionGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutVersionRequest aboutRequest = httpsGeoserverConnectorStore.createAboutVersionRequest();
        logger.info("#####################ABOUT_VERSION_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutRequest.getResponseAsString());
    }

    @Test(expected = UnauthorizedException.class)
    public void b_aboutHttpsStatusGeoserverConnectorTest() throws Exception {
        GPGeoserverAboutStatusRequest aboutStatusRequest = httpsGeoserverConnectorStore.createAboutStatusRequest();
        logger.info("#####################ABOUT_STATUS_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", aboutStatusRequest.getResponseAsString());
    }

    @Test(expected = UnauthorizedException.class)
    public void c_workspacesHttpsGeoserverConnectorTest() throws Exception {
        GPGeoserverLoadWorkspacesRequest workspacesRequest = httpsGeoserverConnectorStore.loadWorkspacesRequest();
        logger.info("####################WORKSPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", workspacesRequest.getResponse());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.clearProperty("jsse.enableSNIExtension");
        httpsGeoserverConnectorStore.dispose();
    }
}