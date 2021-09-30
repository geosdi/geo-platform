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
package org.geosdi.geoplatform.geoserver.coveragestore;

import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.file.GPGeoserverCoverageStoreFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.layers.raster.GeoserverRasterLayer;
import org.geosdi.geoplatform.connector.geoserver.model.projection.GPProjectionPolicy;
import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverStyle;
import org.geosdi.geoplatform.connector.geoserver.model.update.GPParameterUpdate;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverLoadCoverageStoreRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorCoverageStoresTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorCoverageStoresTest.class);
    //
    public static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_ENABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_exsistCoverageStores() throws Exception {
        Assert.assertTrue("####################",  restReader.existsCoveragestore("burg", "test", TRUE) ==
                this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("burg").withStore("test").exist());
    }

    @Ignore(value = "Store store_vito may be not present")
    @Test
    public void b_deleteCoverageStore() throws Exception {
        Assert.assertTrue("####################", this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("sf").withStore("store_vito").exist());
        this.geoserverConnectorStore.deleteCoverageStoreRequest().withCoverageStore("store_vito").withWorkspace("sf").withRecurse(TRUE).getResponse();
        Assert.assertFalse("####################", this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("sf").withStoreName("store_vito").withQuietNotFound(TRUE).exist());
    }

    @Test
    public void c_exsistCoverage() throws  Exception {
        RESTCoverage restCoverage = this.restReader.getCoverage("nurc", "mosaic", "mosaic");
        logger.info("###################{}\n", restCoverage.getTitle());

        Boolean exsist = this.geoserverConnectorStore.loadWorkspaceStoreCoverageRequest().withCoverage("mosaic")
                .withWorkspace("nurc").withStore("mosaic").exist();
        logger.info("###################{}\n", exsist);
    }

    @Test()
    public void d_exsistCoverageUrl() throws Exception {
        logger.info("########################EXSIST : {}\n", this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaic.json").exist());
        logger.info("########################EXSIST : {}\n", this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaicww.json").exist());
    }

    @Ignore(value = "Store layer_vito may be not present")
    @Test
    public void e_unpublishCoverage() throws Exception {
                this.geoserverConnectorStore.deleteCoverageInCoverageStore().withCoverage("layer_vito")
                        .withCoverageStore("mosaic").withWorkspace("nurc").getResponse();
    }

    @Ignore()
    @Test
    public void f_updateCoverage() throws Exception {
        File file = new File(of("src", "test", "resources", "VMI_20210923T1020Z.tif").collect(joining(separator)));
        Assert.assertTrue("#################FILE_EXSIST", file.exists());
        logger.info("###############{}\n", this.geoserverConnectorStore.updateCoverageStoreWithStoreName()
                .withWorkspace("sf")
                .withCoverageName("layer_vito")
                .withStore("store_vito")
                .withUpdate(GPParameterUpdate.OVERWRITE)
                .withConfigure(GPGeoserverParameterConfigure.FIRST)
                .withMethod(GPGeoserverUploadMethod.FILE)
                .withFormat(GPGeoserverCoverageStoreFileExtension.GEOTIFF)
                .withFile(file).getResponse());
        //logger.info("############{}\n", this.restPublisher.publishGeoTIFF("sf", "store_vito", file));
    }

    @Ignore
    @Test
    public void g_createCoverage() throws Exception {
        GPGeoserverCoverageInfo theGPGeoserverCoverageInfo = new GPGeoserverCoverageInfo();
        theGPGeoserverCoverageInfo.setName("layer_vito");
        theGPGeoserverCoverageInfo.setTitle("layer_vito2");
        theGPGeoserverCoverageInfo.setSrs("EPSG:4326");
        theGPGeoserverCoverageInfo.setEnabled(TRUE);
        theGPGeoserverCoverageInfo.setPolicy(GPProjectionPolicy.FORCE_DECLARED);
        logger.info("#############{}\n", this.geoserverConnectorStore.updateCoverageRequest()
                .withWorkspace("sf")
                .withCoverageStore("store_vito")
                .withCoverageBody(theGPGeoserverCoverageInfo).getResponseAsString());
        }

    @Ignore
    @Test
    public void h_updateLayer() throws Exception {
        GeoserverRasterLayer geoserverRasterLayer = new GeoserverRasterLayer();
        GPGeoserverStyle gpGeoserverStyle = new GPGeoserverStyle();
        gpGeoserverStyle.setName("burg");
        geoserverRasterLayer.setDefaultStyle(gpGeoserverStyle);
        logger.info("###############{}\n", this.geoserverConnectorStore.updateLayerRequest()
                .withWorkspaceName("sf")
                .withLayerName("layer_vito")
                .withLayerBody(geoserverRasterLayer)
                .getResponse());
    }

   @Ignore
    @Test
    public void i_createCoverage() throws Exception {
        File file = new File(of("src", "test", "resources", "VMI_20210923T1020Z.tif").collect(joining(separator)));
        Assert.assertTrue("#################FILE_EXSIST", file.exists());
        GPGeoserverCoverageInfo theGPGeoserverCoverageInfo = new GPGeoserverCoverageInfo();
        theGPGeoserverCoverageInfo.setName("layer_vito");
        theGPGeoserverCoverageInfo.setTitle("layer_vito");
        theGPGeoserverCoverageInfo.setEnabled(TRUE);
        theGPGeoserverCoverageInfo.setPolicy(GPProjectionPolicy.FORCE_DECLARED);
        theGPGeoserverCoverageInfo.setSrs("EPSG:4326");
        this.geoserverConnectorStore.updateCoverageStoreWithStoreName().withWorkspace("sf").withStore("store_vito")
                .withFormat(GPGeoserverCoverageStoreFileExtension.GEOTIFF).withFile(file)
                .withMethod(GPGeoserverUploadMethod.FILE)
                .withConfigure(GPGeoserverParameterConfigure.FIRST)
                .withCoverageName("layer_vito")
                .withUpdate(GPParameterUpdate.OVERWRITE)
                .getResponse();
        GeoserverLoadCoverageStoreRequest geoserverLoadCoverageStoreRequest = this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("sf").withStore("store_vito");
        if (!geoserverLoadCoverageStoreRequest.exist()) {
            logger.error("");
        } else if (!this.geoserverConnectorStore.updateCoverageRequest()
                .withWorkspace("sf")
                .withCoverageStore("store_vito")
                .withCoverageBody(theGPGeoserverCoverageInfo).getResponse()) {
            logger.error("Unable to create a coverage for the store:" + "layer_vito");
        } else {
            GeoserverRasterLayer geoserverRasterLayer = new GeoserverRasterLayer();
            GPGeoserverStyle gpGeoserverStyle = new GPGeoserverStyle();
            gpGeoserverStyle.setName("burg");
            geoserverRasterLayer.setDefaultStyle(gpGeoserverStyle);
            logger.info("##############{}\n", this.geoserverConnectorStore.updateLayerRequest().withWorkspaceName("sf").withLayerName("layer_vito").withLayerBody(geoserverRasterLayer).getResponse());
        }
    }
}