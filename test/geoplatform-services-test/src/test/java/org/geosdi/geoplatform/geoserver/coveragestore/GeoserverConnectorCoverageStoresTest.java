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

import it.geosolutions.geoserver.rest.HTTPUtils;
import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder;
import it.geosolutions.geoserver.rest.encoder.coverage.GSCoverageEncoder;
import org.geosdi.geoplatform.connector.geoserver.coveragestores.GPCoverateStoreExtension;
import org.geosdi.geoplatform.connector.geoserver.coveragestores.GPParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.coveragestores.GPUploadMethod;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.lang.Boolean.TRUE;
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
                this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("burg").withStore("test").exsist());
    }

    @Ignore(value = "Store store_vito may be not present")
    @Test
    public void b_deleteCoverageStore() throws Exception {
        Assert.assertTrue("####################", this.geoserverConnectorStore.loadCoverageStoreRequest().withWorkspace("sf").withStore("store_vito").exsist());
        this.geoserverConnectorStore.deleteCoverageStoreRequest().withCoverageStore("store_vito").withWorkspace("sf").withRecurse(TRUE).getResponse();
        Assert.assertFalse("####################", this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("sf").withStoreName("store_vito").withQuietNotFound(TRUE).exsist());
    }

    @Test
    public void c_exsistCoverage() throws  Exception {
        RESTCoverage restCoverage = this.restReader.getCoverage("nurc", "mosaic", "mosaic");
        logger.info("###################{}\n", restCoverage.getTitle());

        Boolean exsist = this.geoserverConnectorStore.loadWorkspaceStoreCoverageRequest().withCoverage("mosaic")
                .withWorkspace("nurc").withStore("mosaic").exsist();
        logger.info("###################{}\n", exsist);
    }

    @Test()
    public void d_exsistCoverageUrl() throws Exception {
        logger.info("########################EXSIST : {}\n", this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaic.json").exsist());
        logger.info("########################EXSIST : {}\n", this.geoserverConnectorStore.loadCoverageInfoWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/nurc/coveragestores/mosaic/coverages/mosaicww.json").exsist());
    }

    @Ignore(value = "Store layer_vito may be not present")
    @Test
    public void e_unpublishCoverage() throws Exception {
                this.geoserverConnectorStore.deleteCoverageInCoverageStore().withCoverage("layer_vito")
                        .withCoverageStore("mosaic").withWorkspace("nurc").getResponse();
    }

   // @Ignore(value = "Store layer_vito may be not present")
    @Test
    public void f_updateCoverage() throws Exception {
//        File file = new File(of("src", "test", "resources", "VMI_20210923T1020Z.tif")
//                .collect(joining(separator)));
        File file = new File("/Users/vitosalvia/workspace/geo-platform/test/geoplatform-services-test/src/test/resources/VMI_20210923T1020Z.tif");
        Assert.assertTrue("#################FILE_EXSIST", file.exists());
        logger.info("@@@@@@@@@@ {}\n", file.getAbsolutePath());
        logger.info("###############{}\n", this.geoserverConnectorStore.updateCoverageStoreWithStoreName()
                .withWorkspace("sf")
                .withCoverageName("coverage_name")
                .withStore("store_vito")
                .withFileName("layer_vito")
                .withConfigure(GPParameterConfigure.FIRST)
                .withMethod(GPUploadMethod.FILE)
                .withFormat(GPCoverateStoreExtension.GEOTIFF)
                .withFile(file).getResponse());

        // http://150.145.141.180/geoserver/rest/workspaces/sf/coveragestores/store_vito/file.geotiff?configure=first
        // http://150.145.141.180/geoserver/rest/workspaces/sf/coveragestores/store_vito/file.geotiff?configure=first

        //logger.info("############{}\n", this.restPublisher.publishGeoTIFF("sf", "store_vito", file));

    }

    @Test
    public void g_createCoverage() throws Exception {
//        logger.info("#############{}\n", this.geoserverConnectorStore.createCoverageStoreWithStoreName()
//                .withStore("store_vito")
//                .withWorkspace("sf")
//                .withMethod()

        GSCoverageEncoder re = new GSCoverageEncoder();
        re.setName("layer_vito");
        re.setTitle("layer_vito");
        re.setSRS("EPSG:4326");
        re.setProjectionPolicy(GSResourceEncoder.ProjectionPolicy.FORCE_DECLARED);

            StringBuilder sbUrl = (new StringBuilder("http://150.145.141.180/geoserver")).append("/rest/workspaces/").append("sf").append("/").append("coveragestores").append("/").append("store_vito").append("/").append("coverages.json");
            String resourceName = re.getName();
            if (resourceName == null) {
                throw new IllegalArgumentException("Unable to configure a coverage using unnamed coverage encoder");
            } else {
                String xmlBody = re.toString();
                logger.info("################BODY: {}\n", xmlBody);
                String sendResult = HTTPUtils.postXml(sbUrl.toString(), xmlBody, "admin", "geoservertest");
                logger.info("#############SEND {}\n" + sendResult);
            }
        }
}