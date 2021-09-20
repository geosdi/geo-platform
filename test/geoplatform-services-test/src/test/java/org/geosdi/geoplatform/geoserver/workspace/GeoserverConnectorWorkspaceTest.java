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
package org.geosdi.geoplatform.geoserver.workspace;

import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoverageWithUrlRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorWorkspaceTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorWorkspaceTest.class);

    /*
    url: "http://${geoserver_url}/geoserver/rest/workspaces/${workspace_name}/coveragestores/${store_name}/coverages/${layer_name}.json
     */
    @Test()
    public void a_readCoverageInfo() throws Exception {
        GeoserverLoadCoverageWithUrlRequest gpGeoserverLoadCoverageWithUrlRequest = this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaic.json");
        GPGeoserverCoverageInfo gpGeoserverFeatureTypeInfo = gpGeoserverLoadCoverageWithUrlRequest.getResponse();
        logger.info("########################BBOX {}\n", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox());
        logger.info("########################BBOX {}\n", gpGeoserverFeatureTypeInfo.getLatLonBoundingBox().getCrs());
    }

    @Test
    public void b_existWorkspace() throws Exception {
        Assert.assertTrue("####################", this.restReader.existsWorkspace("tiger", TRUE) ==
                this.geoserverConnectorStore.loadWorkspaceRequest().withWorkspaceName("tiger").withQuietOnNotFound(TRUE).exsist());
        Assert.assertTrue("####################", this.restReader.existsWorkspace("tigeraa", TRUE) ==
                this.geoserverConnectorStore.loadWorkspaceRequest().withWorkspaceName("tigeraa").withQuietOnNotFound(TRUE).exsist());
    }

    @Test
    public void c_exsistCoverage() throws  Exception {
        RESTCoverage restCoverage = this.restReader.getCoverage("tiger", "nyc", "poi");
        logger.info("###################{}\n", restCoverage);

        Boolean exsist = this.geoserverConnectorStore.loadWorkspaceStoreCoverageRequest().withCoverage("poi")
                .withWorkspace("tiger").withStore("nyc").exsist();
        logger.info("###################{}\n", exsist);
    }

    @Test()
    public void d_exsistCoverageUrl() throws Exception {
        logger.info("########################EXSIST {}\n", this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaic.json").exsist());
        logger.info("########################EXSIST {}\n", this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaicww.json").exsist());
    }

    @Test
    public void e_getWorkspaceNames() throws Exception {
        List<String> workspaces = restReader.getWorkspaceNames();
        logger.info("###################WORKSPACE_NAME: {}\n", workspaces);
        logger.info("###################WORKSPACE_NAME: {}\n", this.geoserverConnectorStore.loadWorkspacesRequest().getWorkpacesNames());
    }

}