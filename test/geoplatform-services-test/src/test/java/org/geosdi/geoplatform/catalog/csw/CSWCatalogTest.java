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
package org.geosdi.geoplatform.catalog.csw;

import org.geosdi.geoplatform.connector.security.GeosdiCatalogBeanProvider;
import org.geosdi.geoplatform.connector.security.SnipcCatalogBeanProvider;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.gui.responce.TimeInfo;
import org.geosdi.geoplatform.services.GeoPlatformCSWService;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath*:applicationContext.xml"})
@TestExecutionListeners(value = {CSWListenerServices.class})
@ActiveProfiles(profiles = {"dev"})
public abstract class CSWCatalogTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected GeoPlatformCSWService cswService;
    protected GeoPlatformService gpWSClient;
    protected SnipcCatalogBeanProvider snipcProvider;
    protected GeosdiCatalogBeanProvider geosdiProvider;
    // Organization
    protected GPOrganization organizationTest;
    protected String organizationNameTest = "geoSDI_ws_test";
    //
    protected GeoPlatformServer serverTestOur;
    protected Long serverTestOurID;
    protected Long serverTestTrevisoID;
    protected CatalogFinderBean catalogFinder;

    /**
     * The listener will inject this dependencies.
     */
    public void setCSWService(GeoPlatformCSWService cswService) {
        this.cswService = cswService;
    }

    public void setGeoplatformService(GeoPlatformService gpWSClient) {
        this.gpWSClient = gpWSClient;
    }

    public void setSnipcProvider(SnipcCatalogBeanProvider snipcProvider) {
        this.snipcProvider = snipcProvider;
    }

    public void setGeosdiProvider(GeosdiCatalogBeanProvider geosdiProvider) {
        this.geosdiProvider = geosdiProvider;
    }

    @Before
    public void setUp() throws Exception {
        logger.trace("\n\t@@@ {}.setUp @@@", this.getClass().getSimpleName());

        organizationTest = new GPOrganization(organizationNameTest);
        organizationTest.setId(gpWSClient.insertOrganization(organizationTest));

        // Insert the servers test
        serverTestOur = this.createCSWServer("CSW Server WS Test",
                geosdiProvider.getGeosdiUrl(), organizationTest);
        serverTestOurID = cswService.insertServerCSW(serverTestOur);
        serverTestOur.setId(serverTestOurID);

        serverTestTrevisoID = cswService.insertServerCSW(
                this.createCSWServer("Provincia di Treviso",
                "http://ows.provinciatreviso.it/geonetwork/srv/it/csw",
                organizationTest));

        // Create the CSW search parameters
        catalogFinder = new CatalogFinderBean();
        catalogFinder.setServerID(serverTestOurID);
        TextInfo searchInfo = new TextInfo();
        searchInfo.setText(null);
        searchInfo.setSearchTitle(true);
        searchInfo.setSearchAbstract(true);
        searchInfo.setSearchSubjects(true);
        catalogFinder.setTextInfo(searchInfo);
        catalogFinder.setAreaInfo(new AreaInfo());
        catalogFinder.setTimeInfo(new TimeInfo());
    }

    @After
    public void tearDown() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ {}.tearDown @@@", this.getClass().getSimpleName());

        gpWSClient.deleteOrganization(organizationTest.getId());
    }

    protected GeoPlatformServer createCSWServer(String title, String url,
            GPOrganization organization) {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerType(GPCapabilityType.CSW);
        server.setTitle(title);
        server.setServerUrl(url);
        server.setOrganization(organization);
        return server;
    }
}
