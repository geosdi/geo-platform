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
package org.geosdi.geoplatform.services;

import it.geosolutions.geonetwork.GNClient;
import org.geosdi.geoplatform.exception.GPCatalogException;
import org.geosdi.geoplatform.exception.GPCatalogLoginException;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO;
import org.geosdi.geoplatform.services.util.GPCatalogClient;
import org.geosdi.geoplatform.services.util.GPCatalogMetadataLoader;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class GPCatalogServiceTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPCatalogClient gpCatalogClient;
    //
    @Autowired
    private GPCatalogFinderService gpCatalogFinderService;
    //
    @Autowired
    private GPCatalogMetadataLoader gpCatalogMetadataLoader;

    @Test
    public void testGPCatalogClientReferences() {
        assertNotNull("gpCatalogClient is null", gpCatalogClient);
        assertNotNull("URL of GeoNetwork is null", gpCatalogClient.getGeoNetworkServiceURL());
        assertNotNull("username of GeoNetwork is null", gpCatalogClient.getGeoNetworkUsername());
        assertNotNull("password of GeoNetwork is null", gpCatalogClient.getGeoNetworkPassword());
        logger.trace("\n*** GeoNetwork URL {} ***", gpCatalogClient.getGeoNetworkServiceURL());
        logger.trace("\n*** GeoNetwork Username {} ***", gpCatalogClient.getGeoNetworkUsername());
        logger.trace("\n*** GeoNetwork Password {} ***", gpCatalogClient.getGeoNetworkPassword());
    }

    @Test
    public void testGPCatalogFinderServiceReferences() {
        assertNotNull("gpCatalogFinderService is null", gpCatalogFinderService);
    }

    @Test
    public void testGPCatalogMetadataLoader() {
        assertNotNull("gpCatalogMetadataLoader is null", gpCatalogMetadataLoader);
    }

    @Ignore
    @Test
    public void testGPCatalogClient() {
        try {
            gpCatalogClient.createClientWithCredentials();
        } catch (GPCatalogLoginException ex) {
            fail(ex.getMessage());
        }
    }

    @Ignore
    @Test
    public void testAnonymousVSPrivateGPCatalogFinderService() {
        try {
            GNClient anonymousClient = gpCatalogClient.createClientWithoutCredentials();
            assertNotNull("GeoNetwork anonymousClient is null", anonymousClient);
            List<GPCatalogMetadataDTO> anonymousCatalogMetadataDTOList = this.gpCatalogFinderService.
                    searchPublicMetadata("Bacini idrografici principali");
//            Assert.assertTrue("1 or more elements retrieved with anonymous search, but 0 expected", anonymousCatalogMetadataDTOList.isEmpty());
            GNClient privateClient = gpCatalogClient.createClientWithCredentials();
            assertNotNull("GeoNetwork privateClient is null", privateClient);

            List<GPCatalogMetadataDTO> privateCatalogMetadataDTOList = this.gpCatalogFinderService.
                    searchPublicMetadata("Bacini idrografici principali");
//            Assert.assertTrue("No elements retrieved with private search", privateCatalogMetadataDTOList.isEmpty());
        } catch (GPCatalogException ex) {
            fail(ex.getMessage());
        }
    }

    @Ignore
    @Test
    public void testExtractDatasFromMetadata() {
        try {
            GNClient client = gpCatalogClient.createClientWithCredentials();
            assertNotNull("GeoNetwork client is null", client);
            List<GPCatalogMetadataDTO> catalogMetadataDTOList = this.gpCatalogFinderService.searchPublicMetadata("strade");
//            Assert.assertTrue("Items not found", catalogMetadataDTOList.isEmpty() == false);

            logger.info("@@@@@@@@@@@@@@@@@@@ Found {} Metadata @@@@@@@@@@@@@@@", catalogMetadataDTOList.size());
        } catch (GPCatalogException ex) {
            ex.printStackTrace();
        }
    }

}
