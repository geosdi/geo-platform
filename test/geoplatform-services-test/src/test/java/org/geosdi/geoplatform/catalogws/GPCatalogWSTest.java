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
package org.geosdi.geoplatform.catalogws;

import java.util.List;
import org.geosdi.geoplatform.catalogws.beans.GPCatalogEndPointImpl;
import org.geosdi.geoplatform.connectors.ws.geonetwork.GPGeonetworkClientTestConnector;
import org.geosdi.geoplatform.exception.GPCatalogException;
import org.geosdi.geoplatform.responce.GPCatalogMetadataDTO;
import org.geosdi.geoplatform.services.GPCatalogFinderService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath*:applicationContext.xml"})
@ActiveProfiles(profiles = {"dev"})
public class GPCatalogWSTest {

    static final Logger logger = LoggerFactory.getLogger(GPCatalogWSTest.class);
    //
    @Autowired
    private GPGeonetworkClientTestConnector gpCatalogWSClient;
    //
    @Autowired
    private GPCatalogEndPointImpl gpCatalogEndPointImpl;
    private GPCatalogFinderService catalogClient;

    @Before
    public void setUp() {
        this.catalogClient = this.gpCatalogWSClient.getEndpointService();
        this.gpCatalogEndPointImpl.startServer();
    }

    @After
    public void tearDown() throws Exception {
        this.gpCatalogEndPointImpl.stopServer();
    }

    @Test
    @Ignore(value = "Timeout Error")
    public void searchMetadata() {
        try {
            List<GPCatalogMetadataDTO> metadataList = this.catalogClient.searchPublicMetadata(
                    "strade");
            Assert.assertFalse("Items not found", metadataList.isEmpty());


            logger.info("FOUND {} ELEMENTS @@@@@@@@@@@@", metadataList.size());
        } catch (GPCatalogException ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
