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
package org.geosdi.geoplatform.connector.wfs.bridge.store;

import org.geosdi.geoplatform.connector.bridge.implementor.GPWFSGetFeatureReader;
import org.geosdi.geoplatform.connector.bridge.store.GPWFSGetFeatureReaderStore;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.*;
import static org.geosdi.geoplatform.connector.wfs.bridge.implementor.GPWFSGetFeatureMockReader.MOCK_OUTUPUT_FORMAT;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWFSGetFeatureReaderStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSGetFeatureReaderStoreTest.class);
    //
    private static final GPWFSGetFeatureReaderStore store = new GPWFSGetFeatureReaderStore();

    @Test
    public void a_loadWFSGetFeatureGml311ReaderTest() throws Exception {
        GPWFSGetFeatureReader<?> gml311GetFeatureReader = store.getImplementorByKey(GML_311);
        assertNotNull(gml311GetFeatureReader);
        logger.info("################################GML_311_READER : {}\n", gml311GetFeatureReader);
    }

    @Test
    public void b_loadWFSGetFeatureGeojsonReaderTest() throws Exception {
        GPWFSGetFeatureReader<?> geoJsonGetFeatureReader = store.getImplementorByKey(GEOJSON);
        assertNotNull(geoJsonGetFeatureReader);
        logger.info("################################GEOJSON_READER : {}\n", geoJsonGetFeatureReader);
    }

    @Test
    public void c_loadWFSGetFeatureCsvReaderTest() throws Exception {
        GPWFSGetFeatureReader<?> csvGetFeatureReader = store.getImplementorByKey(CSV);
        assertNotNull(csvGetFeatureReader);
        logger.info("################################CSV_READER : {}\n", csvGetFeatureReader);
    }

    @Test
    public void d_loadWFSGetFeatureMockReaderTest() throws Exception {
        GPWFSGetFeatureReader<?> mockGetFeatureReader = store.getImplementorByKey(MOCK_OUTUPUT_FORMAT);
        assertNotNull(mockGetFeatureReader);
        logger.info("################################MOCK_READER : {}\n", mockGetFeatureReader);
    }
}