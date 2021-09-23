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
package org.geosdi.geoplatform.connector.store.style;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GeoserverStyleRequest;
import org.geosdi.geoplatform.connector.geoserver.request.styles.GeoserverWorkspaceStylesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.GeoserverLoadWorkspacesRequest;
import org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV219xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Objects;

import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverStyleBodyJacksonTest.toGPGeoserverStyleBody;
import static org.junit.Assert.assertTrue;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverStyleConnectorV219xTest extends GPBaseGeoserverConnectorStoreV219xTest {

    private static final GeoserverLoadWorkspacesRequest workspacesRequest = geoserverConnectorStoreV2_19_x.loadWorkspacesRequest();
    private static final GeoserverWorkspaceStylesRequest gpGeoserverWorkspaceStylesRequest = geoserverConnectorStoreV2_19_x.loadWorkspaceStyles();

    @Test
    public void a_stylesGeoserverConnectorTest() throws Exception {
        logger.info("####################WORKSPACES_GEOSERVER_CONNECTOR_RESPONSE : \n{}\n", workspacesRequest.getResponse());
        fromIterable(workspacesRequest.getResponse().getWorkspaces())
                .filter(Objects::nonNull)
                .map(IGPGeoserverWorkspace::getWorkspaceName)
                .doOnComplete(() -> logger.debug("################### workspaces processed."))
                .subscribe(this::toWorkspaceStyles, Throwable::printStackTrace);
    }

    @Test
    public void b_geoserverStyleRequestTest() throws Exception {
        GeoserverStyleRequest geoserverStyleRequest = geoserverConnectorStoreV2_19_x.loadStyleRequest();
        logger.info("#####################GEOSERVER_STYLE_RESPONSE_AS_STRING : {}\n", geoserverStyleRequest
                .withStyleName("pophatch")
                .getResponseAsString());
        logger.info("#####################GEOSERVER_STYLE_RESPONSE_AS_STRING : {}\n", geoserverStyleRequest
                .withStyleName("population")
                .getResponseAsString());
    }

    @Test
    public void c_geoserverStyleSLDV100RequestTest() throws Exception {
        GeoserverStyleSLDV100Request geoserverStyleSLDV100Request = geoserverConnectorStoreV2_19_x.loadStyleSLDV100Request();
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("pophatch")
                .getResponseAsString());
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("population")
                .getResponseAsString());
    }

    @Test
    public void d_geoserverCreateStyleRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GEOSERVER_CREATE_STYLE_RESPONSE : {}\n", geoserverConnectorStoreV2_19_x.createStyleRequest()
                .withStyleBody(toGPGeoserverStyleBody("roads_style_new", "roads33.sld"))
                .getResponse());
    }

    @Test
    public void e_geoserverDeleteStyleRequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_19_x.deleteStyleRequest()
                .withStyle("roads_style_new")
                .getResponse() == TRUE);
    }

    /**
     * @param workspaceName
     * @throws Exception
     */
    private void toWorkspaceStyles(String workspaceName) throws Exception {
        logger.info("####################WORKSPACE_NAME : \n{}\n", workspaceName);
        logger.info("####################STYLE_RESPONSE : \n{}\n", gpGeoserverWorkspaceStylesRequest.withWorkspaceName(workspaceName).getResponse());
    }
}