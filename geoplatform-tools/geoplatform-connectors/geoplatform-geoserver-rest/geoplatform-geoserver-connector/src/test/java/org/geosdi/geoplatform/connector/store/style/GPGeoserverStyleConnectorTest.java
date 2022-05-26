/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.util.Objects;

import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverStyleBodyJacksonTest.toGPGeoserverStyleBody;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverUpdateStyleBodyJacksonTest.toGPGeoserverUpdateStyleBody;
import static org.junit.Assert.assertTrue;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverStyleConnectorTest extends GPBaseGeoserverConnectorStoreTest {

    private static final GeoserverLoadWorkspacesRequest workspacesRequest = geoserverConnectorStoreV2_21_x.loadWorkspacesRequest();
    private static final GeoserverWorkspaceStylesRequest gpGeoserverWorkspaceStylesRequest = geoserverConnectorStoreV2_21_x.loadWorkspaceStyles();

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
        GeoserverStyleRequest geoserverStyleRequest = geoserverConnectorStoreV2_21_x.loadStyleRequest();
        logger.info("#####################GEOSERVER_STYLE_RESPONSE_AS_STRING : {}\n", geoserverStyleRequest
                .withStyleName("tiger_roads")
                .getResponseAsString());
        logger.info("#####################GEOSERVER_STYLE_RESPONSE_AS_STRING : {}\n", geoserverStyleRequest
                .withStyleName("simple_streams")
                .getResponseAsString());
    }

    @Test
    public void c_geoserverStyleSLDV100RequestTest() throws Exception {
        GeoserverStyleSLDV100Request geoserverStyleSLDV100Request = geoserverConnectorStoreV2_21_x.loadStyleSLDV100Request();
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("tiger_roads")
                .getResponseAsString());
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("simple_streams")
                .getResponseAsString());
    }

    @Test
    public void d_geoserverCreateStyleRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GEOSERVER_CREATE_STYLE_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.createStyleRequest()
                .withStyleBody(toGPGeoserverStyleBody("roads_style_new", "roads33.sld"))
                .getResponse());
    }

    @Test
    public void e_geoserverUpdateStyleRequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_21_x.updateStyleRequest()
                .withStyleName("roads_style_new")
                .withStyleBody(toGPGeoserverUpdateStyleBody())
                .getResponse());
        assertTrue(geoserverConnectorStoreV2_21_x.loadStyleRequest()
                .withStyleName("STYLE_TEST")
                .exist());
    }

    @Test
    public void f_geoserverDeleteStyleRequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_21_x.deleteStyleRequest()
                .withStyle("STYLE_TEST")
                .getResponse() == TRUE);
    }

    @Test
    public void g_geoserverCreateStyleSLDV100RequestTest() throws Exception {
        StyledLayerDescriptor styledLayerDescriptor = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "StyledLayerDescriptor-DefaultLine")
                .collect(joining(separator, "", ".xml"))), StyledLayerDescriptor.class);
        logger.info("#################GEOSERVER_CREATE_STYLE_SLD_V100_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.createStyleSLDV100Request()
                .withStyleName("style_sld_v100")
                .withStyleBody(styledLayerDescriptor)
                .getResponse());
    }

    @Test
    public void h_geoserverDeleteStyleSLDV100RequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_21_x.deleteStyleRequest()
                .withStyle("style_sld_v100")
                .getResponse() == TRUE);
        GeoserverStyleSLDV100Request geoserverStyleSLDV100Request = geoserverConnectorStoreV2_21_x.loadStyleSLDV100Request();
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("style_sld_v100")
                .exist());
    }

    @Test
    public void i_geoserverCreateWorkspaceStyleRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GEOSERVER_CREATE_WORKSPACE_STYLE_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.createWorkspaceStyleRequest()
                .withWorkspace("sf")
                .withStyleBody(toGPGeoserverStyleBody("roads_style_new_sf", "roads33_sf.sld"))
                .getResponse());
    }

    @Test
    public void l_geoserverDeleteWorkspaceStyleRequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_21_x.deleteWorkspaceStyleRequest()
                .withWorkspace("sf")
                .withStyle("roads_style_new_sf")
                .getResponse() == TRUE);
    }

    @Test
    public void m_geoserverCreateStyleRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GEOSERVER_CREATE_STYLE_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.createStyleRequest()
                .withStyleBody(toGPGeoserverStyleBody("roads_style_new_test", "roads33_24.sld"))
                .getResponse());
    }

    @Test
    public void n_geoserverUpdateStyleSLDV100RequestTest() throws Exception {
        StyledLayerDescriptor styledLayerDescriptor = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper().readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "StyledLayerDescriptor-DefaultLine")
                .collect(joining(separator, "", ".xml"))), StyledLayerDescriptor.class);
        assertTrue(geoserverConnectorStoreV2_21_x.updateStyleSLDV100Request()
                .withStyleName("roads_style_new_test")
                .withStyleBody(styledLayerDescriptor)
                .getResponse());
        GeoserverStyleRequest styleRequest = geoserverConnectorStoreV2_21_x.loadStyleRequest()
                .withStyleName("roads_style_new_test");
        assertTrue(styleRequest.exist());
        logger.info("######################GEOSERVER_LOAD_STYLE_RESPONSE : {}\n", styleRequest.getResponse());
    }

    @Test
    public void o_geoserverDeleteStyleSLDV100RequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_21_x.deleteStyleRequest()
                .withStyle("roads_style_new_test")
                .getResponse() == TRUE);
        GeoserverStyleSLDV100Request geoserverStyleSLDV100Request = geoserverConnectorStoreV2_21_x.loadStyleSLDV100Request();
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("roads_style_new_test")
                .exist());
    }

    @Test
    public void p_geoserverLoadLayerStylesRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GEOSERVER_LAYER_STYLES_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.loadLayerStylesRequest()
                .withLayerName("topp:states").getResponse());
    }

    @Test
    public void q_geoserverCreateStyleRequestTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GEOSERVER_CREATE_STYLE_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.createStyleRequest()
                .withStyleBody(toGPGeoserverStyleBody("polygon_style_new_test", "polygon_24.sld"))
                .getResponse());
    }

    @Test
    public void r_geoserverAddStyleToLayerRequestTest() throws Exception {
        logger.info("###################GEOSERVER_ADD_STYLE_TO_LAYER_RESPONSE : {}\n", geoserverConnectorStoreV2_21_x.addStyleToLayerRequest()
                .withLayer("topp:states")
                .withStyleBody(toGPGeoserverStyleBody("polygon_style_new_test", "polygon_24.sld")).getResponseAsString());
    }

    @Test
    public void s_geoserverDeleteStyleSLDV100RequestTest() throws Exception {
        assertTrue(geoserverConnectorStoreV2_21_x.deleteStyleRequest()
                .withStyle("polygon_style_new_test")
                .getResponse() == TRUE);
        GeoserverStyleSLDV100Request geoserverStyleSLDV100Request = geoserverConnectorStoreV2_21_x.loadStyleSLDV100Request();
        logger.info("#####################GEOSERVER_STYLE_SLD_V100_RESPONSE_AS_STRING : {}\n", geoserverStyleSLDV100Request
                .withStyleName("polygon_style_new_test")
                .exist());
    }

    /**
     * @param workspaceName
     * @throws Exception
     */
    private void toWorkspaceStyles(String workspaceName) throws Exception {
        logger.info("####################WORKSPACE_NAME : {}\n", workspaceName);
        logger.info("####################STYLE_RESPONSE : {}\n", gpGeoserverWorkspaceStylesRequest.withWorkspaceName(workspaceName).getResponse());
    }
}