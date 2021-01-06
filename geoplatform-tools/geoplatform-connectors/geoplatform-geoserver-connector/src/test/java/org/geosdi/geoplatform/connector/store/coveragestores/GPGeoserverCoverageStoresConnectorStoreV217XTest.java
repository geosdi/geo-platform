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
package org.geosdi.geoplatform.connector.store.coveragestores;

import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverLoadCoverageStoreRequest;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverLoadCoverageStoresRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV217xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverCoverageStoresConnectorStoreV217XTest extends GPBaseGeoserverConnectorStoreV217xTest {

    @Test
    public void a_loadCoverageStoresConnectorTest() throws Exception {
        GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest = geoserverConnectorStoreV2_17_x.loadCoverageStoresRequest();
        loadCoverageStoresRequest.withWorkspace("nurc");
        logger.info("#############################LOAD_COVERAGE_STORES_RESPONSE : {}\n", loadCoverageStoresRequest.getResponse());
    }

    @Test
    public void b_loadEmptyCoverageStoresConnectorTest() throws Exception {
        GeoserverLoadCoverageStoresRequest loadCoverageStoresRequest = geoserverConnectorStoreV2_17_x.loadCoverageStoresRequest();
        loadCoverageStoresRequest.withWorkspace("topp");
        logger.info("############################LOAD_EMPTY_COVERAGE_STORES_RESPONSE : {}\n", loadCoverageStoresRequest.getResponse());
    }

    @Test
    public void c_loadArcGridSampleCoverageStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStoreV2_17_x.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("arcGridSample");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

    @Test
    public void d_loadImgSample2CoverageStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStoreV2_17_x.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("img_sample2");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

    @Test
    public void e_loadMosaicCoverageStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStoreV2_17_x.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("mosaic");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }

    @Test
    public void f_loadWorldImageSampleStoreConnectorTest() throws Exception {
        GeoserverLoadCoverageStoreRequest loadCoverageStoreRequest = geoserverConnectorStoreV2_17_x.loadCoverageStoreRequest();
        loadCoverageStoreRequest.withWorkspace("nurc").withStore("worldImageSample");
        logger.info("###############################LOAD_COVERAGE_RESPONSE : {}\n", loadCoverageStoreRequest.getResponse());
    }
}