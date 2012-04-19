/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.catalog.csw;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.gui.responce.TimeInfo;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.geosdi.geoplatform.services.GeoPlatformCSWService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CSWCatalogTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GeoPlatformCSWService cswService;
    //
    private GeoPlatformServer serverTestOur;
    private Long serverTestOurID;
    private Long serverTestTrevisoID;
    private CatalogFinderBean catalogFinder;

    /**
     * The listener will inject this dependency
     */
    public void setCSWService(GeoPlatformCSWService cswService) {
        this.cswService = cswService;
    }

    @Before
    public void setUp() throws Exception {
        logger.trace("\n\t@@@ {}.setUp @@@", this.getClass().getSimpleName());

        // Insert the servers test
        serverTestOur = this.createCSWServer("CSW Server WS Test",
                "http://150.146.160.152/geonetwork/srv/en/csw");
        serverTestOurID = cswService.insertServerCSW(serverTestOur);
        serverTestOur.setId(serverTestOurID);

        serverTestTrevisoID = cswService.insertServerCSW(this.createCSWServer(
                "Provincia di Treviso",
                "http://ows.provinciatreviso.it/geonetwork/srv/it/csw"));

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

    private GeoPlatformServer createCSWServer(String title, String url) {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerType(GPCapabilityType.CSW);
        server.setTitle(title);
        server.setServerUrl(url);
        return server;
    }

    @After
    public void tearDown() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ {}.tearDown @@@", this.getClass().getSimpleName());

        // Delete the servers test
        cswService.deleteServerCSW(serverTestOurID);
        cswService.deleteServerCSW(serverTestTrevisoID);
    }

    @Test
    public void testInsertServer() throws ResourceNotFoundFault {
        // Insert the server
        GeoPlatformServer server = this.createCSWServer("server_test", "http://url.test");
        Long serverID = cswService.insertServerCSW(server);

        Assert.assertNotNull(serverID);

        // Retrieve the server
        GeoPlatformServer retrievedServer = cswService.getServerDetailCSW(serverID);

        server.setId(serverID);
        this.compareServer(server, retrievedServer);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverID);
        Assert.assertTrue(deleted);
    }

    @Test
    public void testReinsertServerOur() throws ResourceNotFoundFault {
        // Try to reinsert the server
        Long serverID = cswService.insertServerCSW(serverTestOur);

        Assert.assertNotNull(serverID);
        Assert.assertEquals(serverTestOurID, serverID);
    }

    @Test
    public void testSaveServer() throws ResourceNotFoundFault, IllegalParameterFault {
        // Save the server
        String serverURL = "http://datigis.comune.fi.it/geonetwork/srv/it/csw";
        ServerCSWDTO serverDTO = cswService.saveServerCSW("Firenze", serverURL);

        Assert.assertNotNull(serverDTO);

        // Retrieve the server
        ServerCSWDTO retrievedServerDTO = cswService.getShortServerCSW(serverURL);

        this.compareServer(serverDTO, retrievedServerDTO);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverDTO.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testResaveServerOur() throws ResourceNotFoundFault, IllegalParameterFault {
        // Try to resave a server with a
        ServerCSWDTO serverDTO = cswService.saveServerCSW("alias",
                serverTestOur.getServerUrl());

        this.compareServer(serverTestOur, serverDTO);
    }

    @Test(expected = IllegalParameterFault.class)
    public void testSaveServerMalformedURLException() throws IllegalParameterFault {
        cswService.saveServerCSW("Must fail", "http//url-test.fail");
    }

    @Test(expected = IllegalParameterFault.class)
    public void testSaveServerCatalogVersionException() throws IllegalParameterFault {
        cswService.saveServerCSW("NSDI",
                "http://catalogocentrale.nsdi.it/geonetwork/srv/en/csw"); // Version 2.0.1
    }

    @Test
    public void testGetServerDetailById() throws ResourceNotFoundFault {
        GeoPlatformServer retrievedServer = cswService.getServerDetailCSW(serverTestOurID);

        this.compareServer(serverTestOur, retrievedServer);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testGetServerDetailByIdResourceNotFoundFault() throws ResourceNotFoundFault {
        cswService.getServerDetailCSW(Long.MAX_VALUE);
    }

    @Test
    public void testGetServerDetailByUrl() throws ResourceNotFoundFault {
        GeoPlatformServer retrievedServer = cswService.getServerDetailCSWByUrl(serverTestOur.getServerUrl());

        this.compareServer(serverTestOur, retrievedServer);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testGetServerDetailByUrlResourceNotFoundFault() throws ResourceNotFoundFault {
        cswService.getServerDetailCSWByUrl("http://not-found.fail");
    }

    @Test
    public void testShortServerByUrl() throws ResourceNotFoundFault {
        ServerCSWDTO retrievedServerDTO = cswService.getShortServerCSW(serverTestOur.getServerUrl());

        this.compareServer(serverTestOur, retrievedServerDTO);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testShortServerByUrlResourceNotFoundFault() throws ResourceNotFoundFault {
        cswService.getShortServerCSW("http://not-found.fail");
    }

    @Test
    public void testGetAllServers() {
        List<ServerCSWDTO> servers = cswService.getAllCSWServers();

        Assert.assertNotNull(servers);
        Assert.assertTrue(servers.size() >= 2);
    }

    @Test
    public void testCSWServersCountNothing() {
        SearchRequest request = new SearchRequest("nothing");
        int count = cswService.getCSWServersCount(request);

        Assert.assertEquals(0, count);
    }

    @Test
    public void testCSWServersCount() {
        SearchRequest request = new SearchRequest("test"); // wrt title
        int count = cswService.getCSWServersCount(request);

        Assert.assertEquals(1, count);
    }

    @Test
    public void testCSWServersCountTwo() throws ResourceNotFoundFault {
        // Insert the server
        GeoPlatformServer server = this.createCSWServer("Mock title", "http://url.mock");
        server.setAliasName("Alias test");
        Long serverID = cswService.insertServerCSW(server);

        Assert.assertNotNull(serverID);

        SearchRequest request = new SearchRequest("test"); // wrt title and alias
        int count = cswService.getCSWServersCount(request);

        Assert.assertEquals(2, count);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverID);
        Assert.assertTrue(deleted);
    }

    @Test
    public void testSearchCSWServersNothing() {
        PaginatedSearchRequest request = new PaginatedSearchRequest("nothing", 10, 0);
        List<ServerCSWDTO> search = cswService.searchCSWServers(request);

        Assert.assertNull(search);
    }

    @Test
    public void testSearchCSWServers() {
        PaginatedSearchRequest request = new PaginatedSearchRequest("test", 10, 0); // wrt title
        List<ServerCSWDTO> search = cswService.searchCSWServers(request);

        Assert.assertNotNull(search);
        Assert.assertEquals(1, search.size());
    }

    @Test
    public void testSearchCSWServersMore() throws ResourceNotFoundFault {
        // Insert 27 servers (only 25 for matching wrt alias)
        Long[] serverIDs = new Long[27];
        for (int i = 1; i <= 27; i++) {
            GeoPlatformServer server = this.createCSWServer("Mock title " + i,
                    "http://url.mock-" + i);
            if (i >= 3) {
                server.setAliasName("Alias test " + i);
            }
            serverIDs[i - 1] = cswService.insertServerCSW(server);

            Assert.assertNotNull(serverIDs[i - 1]);
        }

        // First page
        PaginatedSearchRequest request = new PaginatedSearchRequest("test", 10, 0); // wrt title and alias
        List<ServerCSWDTO> search = cswService.searchCSWServers(request);

        Assert.assertNotNull(search);
        Assert.assertEquals(10, search.size());

        // Second page
        request = new PaginatedSearchRequest("test", 10, 1); // wrt title and alias
        search = cswService.searchCSWServers(request);

        Assert.assertNotNull(search);
        Assert.assertEquals(10, search.size());

        // Third page
        request = new PaginatedSearchRequest("test", 10, 2); // wrt title and alias
        search = cswService.searchCSWServers(request);

        Assert.assertNotNull(search);
        Assert.assertEquals(6, search.size());

        // Delete the servers
        for (Long serverID : serverIDs) {
            boolean deleted = cswService.deleteServerCSW(serverID);
            Assert.assertTrue(deleted);
        }
    }

    @Test
    public void testGetRecordsOurCount() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.getTextInfo().setText("land");

        Assert.assertEquals(2, cswService.getSummaryRecordsCount(catalogFinder));
    }

    @Test
    public void testGetRecordsOurResult() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.getTextInfo().setText("land");

        List<SummaryRecordDTO> summaryRecords = cswService.searchSummaryRecords(10, 1, catalogFinder);
        this.traceCollection(summaryRecords);
        Assert.assertEquals(2, summaryRecords.size());
    }

    @Test
    public void testGetRecordsTrevisoSearchWMSText() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("wms");

        int num = 10;
        int recordsMatched = cswService.getSummaryRecordsCount(catalogFinder);
        Assert.assertTrue(recordsMatched > 0);
        logger.debug("\n*** Records matched: {} *** Result for page: {} ***",
                recordsMatched, num);

        List<SummaryRecordDTO> summaryRecords;
        int pages = (recordsMatched / num);
        int mod = recordsMatched % num;
        if (mod > 0) {
            pages++;
        }
        logger.debug("\n*** Pages: {} *** Module: {} ***", pages, mod);

        int start;
        for (int i = 1; i < pages; i++) {
            start = (num * (i - 1)) + 1;
            logger.debug("\n*** page: {} *** start: {} ***", i, start);

            summaryRecords = cswService.searchSummaryRecords(num, start, catalogFinder);
            this.traceCollection(summaryRecords);
            Assert.assertEquals(num, summaryRecords.size());
        }

        // Last page
        if (mod > 0) {
            start = (num * (pages - 1)) + 1;
            summaryRecords = cswService.searchSummaryRecords(num, start, catalogFinder);
            this.traceCollection(summaryRecords);
            Assert.assertEquals(mod, summaryRecords.size());
        }
    }

    @Test
    public void testGetRecordsTrevisoCountLimitiTextAny() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");

        Assert.assertEquals(19, cswService.getSummaryRecordsCount(catalogFinder));
    }

    @Test
    public void testGetRecordsTrevisoCountLimitiTextTitle() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");
        catalogFinder.getTextInfo().setSearchTitle(true);
        catalogFinder.getTextInfo().setSearchAbstract(false);
        catalogFinder.getTextInfo().setSearchSubjects(false);

        Assert.assertEquals(6, cswService.getSummaryRecordsCount(catalogFinder));
    }

    @Test
    public void testGetRecordsTrevisoCountLimitiTextAbstract() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");
        catalogFinder.getTextInfo().setSearchTitle(false);
        catalogFinder.getTextInfo().setSearchAbstract(true);
        catalogFinder.getTextInfo().setSearchSubjects(false);

        Assert.assertEquals(10, cswService.getSummaryRecordsCount(catalogFinder));
    }

    @Test
    public void testGetRecordsTrevisoCountLimitiTextSubjects() throws IllegalParameterFault, ResourceNotFoundFault, ServerInternalFault {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");
        catalogFinder.getTextInfo().setSearchTitle(false);
        catalogFinder.getTextInfo().setSearchAbstract(false);
        catalogFinder.getTextInfo().setSearchSubjects(true);

        Assert.assertEquals(0, cswService.getSummaryRecordsCount(catalogFinder));
    }

    // TODO Uncomment (commented because the "The service is not running")
//    @Test
//    public void testGetRecordsFirenzeCountTimeFiltering() throws ResourceNotFoundFault, IllegalParameterFault, ServerInternalFault {
//        // Insert the server
//        GeoPlatformServer server = this.createCSWServer("Geomatys",
//                "http://demo.geomatys.com/mdweb-cnes-labs/WS/csw/default");
//        Long serverID = cswService.insertServerCSW(server);
//
//        Assert.assertNotNull(serverID);
//
//        catalogFinder.setServerID(serverID);
//
//        Assert.assertEquals(241, cswService.getSummaryRecordsCount(catalogFinder));
//
//        Calendar startCalendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
//        Calendar endCalendar = new GregorianCalendar(2012, Calendar.JANUARY, 1);
//        catalogFinder.getTimeInfo().setActive(true);
//        catalogFinder.getTimeInfo().setStartDate(startCalendar.getTime());
//        catalogFinder.getTimeInfo().setEndDate(endCalendar.getTime());
//
//        Assert.assertEquals(76, cswService.getSummaryRecordsCount(catalogFinder));
//
//        // Delete the server
//        boolean deleted = cswService.deleteServerCSW(serverID);
//        Assert.assertTrue(deleted);
//    }

    private void traceCollection(Collection collection) {
        for (Object object : collection) {
            logger.trace("\n*** " + object);
        }
    }

    private void compareServer(GeoPlatformServer expected,
            GeoPlatformServer toTest) {

        Assert.assertNotNull(expected);
        Assert.assertNotNull(toTest);

        Assert.assertTrue(GPCapabilityType.CSW == toTest.getServerType());
        Assert.assertEquals(expected.getServerType(), toTest.getServerType());

        Assert.assertEquals(expected.getId(), toTest.getId());
        Assert.assertEquals(expected.getTitle(), toTest.getTitle());
        Assert.assertEquals(expected.getServerUrl(), toTest.getServerUrl());
    }

    private void compareServer(GeoPlatformServer expected, ServerCSWDTO toTest) {
        Assert.assertNotNull(expected);
        Assert.assertNotNull(toTest);

        Assert.assertTrue(GPCapabilityType.CSW == expected.getServerType());

        Assert.assertEquals(expected.getId(), toTest.getId());
        Assert.assertEquals(expected.getTitle(), toTest.getTitle());
        Assert.assertEquals(expected.getServerUrl(), toTest.getServerUrl());
    }

    private void compareServer(ServerCSWDTO expected, ServerCSWDTO toTest) {
        Assert.assertNotNull(expected);
        Assert.assertNotNull(toTest);

        Assert.assertEquals(expected.getId(), toTest.getId());
        Assert.assertEquals(expected.getTitle(), toTest.getTitle());
        Assert.assertEquals(expected.getServerUrl(), toTest.getServerUrl());
    }
}
