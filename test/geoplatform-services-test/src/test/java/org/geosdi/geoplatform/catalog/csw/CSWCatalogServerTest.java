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

import java.util.List;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for CSW Catalog Servers.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class CSWCatalogServerTest extends CSWCatalogTest {

    @Test
    public void testInsertServer() throws Exception {
        // Insert the server
        GeoPlatformServer server = super.createCSWServer("server_test",
                                                         "http://url.test",
                                                         organizationTest);
        Long serverID = cswService.insertServerCSW(server);

        Assert.assertNotNull(serverID);

        // Retrieve the server
        GeoPlatformServer retrievedServer = cswService.getServerDetailCSW(
                serverID);

        server.setId(serverID);
        this.compareServer(server, retrievedServer);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverID);
        Assert.assertTrue(deleted);
    }

    @Test
    public void testReinsertServerOur() throws Exception {
        // Try to reinsert the server
        Long serverID = cswService.insertServerCSW(serverTestOur);

        Assert.assertNotNull(serverID);
        Assert.assertEquals(serverTestOurID, serverID);
    }

    @Test(expected = IllegalParameterFault.class)
    public void testInsertServerNullURL() throws Exception {
        serverTestOur.setServerUrl(null);

        // Try to insert the incorrect server
        cswService.insertServerCSW(serverTestOur);
    }

    @Test(expected = IllegalParameterFault.class)
    public void testInsertServerNullType() throws Exception {
        serverTestOur.setServerType(null);

        // Try to insert the incorrect server
        cswService.insertServerCSW(serverTestOur);
    }

    @Ignore("Catalog is down")
    @Test
    public void testSaveServer() throws Exception {
        // Save the server
        String serverURL = "http://datigis.comune.fi.it/geonetwork/srv/it/csw";
        ServerCSWDTO serverDTO = cswService.saveServerCSW("Firenze", serverURL,
                                                          organizationNameTest);

        Assert.assertNotNull(serverDTO);

        // Retrieve the server
        ServerCSWDTO retrievedServerDTO = cswService.getShortServerCSW(serverURL);

        this.compareServer(serverDTO, retrievedServerDTO);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverDTO.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testResaveServerOur() throws Exception {
        // Try to resave a server with a
        ServerCSWDTO serverDTO = cswService.saveServerCSW("alias",
                                                          serverTestOur.getServerUrl(),
                                                          organizationNameTest);

        this.compareServer(serverTestOur, serverDTO);
    }

    @Test(expected = IllegalParameterFault.class)
    public void testSaveServerNullURL() throws Exception {
        cswService.saveServerCSW("Must fail", null, organizationNameTest);
    }

    @Test(expected = IllegalParameterFault.class)
    public void testSaveServerMalformedURLException() throws Exception {
        cswService.saveServerCSW("Must fail", "http//url-test.fail",
                                 organizationNameTest);
    }

    @Test(expected = IllegalParameterFault.class)
    @Ignore(value = "Server is DOWN")
    public void testSaveServerCatalogVersionException() throws Exception {
        cswService.saveServerCSW("NSDI",
                                 "http://catalogocentrale.nsdi.it/geonetwork/srv/en/csw",
                                 organizationNameTest); // Version 2.0.1
    }

    @Ignore("Require to add the SNIPC certificate into default keystore")
    @Test
    public void testSecureSaveServerSNIPC() throws Exception {
        // Save the server
        String serverURL = super.snipcProvider.getSnipcUrl();
        ServerCSWDTO serverDTO = cswService.saveServerCSW("SNIPC", serverURL,
                                                          organizationNameTest);

        Assert.assertNotNull(serverDTO);

        // Retrieve the server
        ServerCSWDTO retrievedServerDTO = cswService.getShortServerCSW(serverURL);

        this.compareServer(serverDTO, retrievedServerDTO);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverDTO.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testGetServerDetailById() throws Exception {
        GeoPlatformServer retrievedServer = cswService.getServerDetailCSW(
                serverTestOurID);

        this.compareServer(serverTestOur, retrievedServer);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testGetServerDetailByIdResourceNotFoundFault() throws Exception {
        cswService.getServerDetailCSW(Long.MAX_VALUE);
    }

    @Test
    public void testGetServerDetailByUrl() throws Exception {
        GeoPlatformServer retrievedServer = cswService.getServerDetailCSWByUrl(
                serverTestOur.getServerUrl());

        this.compareServer(serverTestOur, retrievedServer);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testGetServerDetailByUrlResourceNotFoundFault() throws Exception {
        cswService.getServerDetailCSWByUrl("http://not-found.fail");
    }

    @Test
    public void testShortServerByUrl() throws Exception {
        ServerCSWDTO retrievedServerDTO = cswService.getShortServerCSW(
                serverTestOur.getServerUrl());

        this.compareServer(serverTestOur, retrievedServerDTO);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testShortServerByUrlResourceNotFoundFault() throws Exception {
        cswService.getShortServerCSW("http://not-found.fail");
    }

    @Test
    public void testGetAllServers() throws ResourceNotFoundFault {
        List<ServerCSWDTO> servers = cswService.getAllCSWServers(
                organizationNameTest);

        Assert.assertNotNull(servers);
        Assert.assertTrue(servers.size() >= 2);
    }

    @Test
    public void testCSWServersCountNothing() {
        SearchRequest request = new SearchRequest("nothing");
        int count = cswService.getCSWServersCount(request, super.organizationNameTest);

        Assert.assertEquals(0, count);
    }

    @Test
    public void testCSWServersCount() {
        SearchRequest request = new SearchRequest("test"); // wrt title
        int count = cswService.getCSWServersCount(request, super.organizationNameTest);

        Assert.assertEquals(1, count);
    }

    @Test
    public void testCSWServersCountTwo() throws Exception {
        // Insert the server
        GeoPlatformServer server = super.createCSWServer("Mock title",
                                                         "http://url.mock",
                                                         organizationTest);
        server.setAliasName("Alias test");
        Long serverID = cswService.insertServerCSW(server);

        Assert.assertNotNull(serverID);

        SearchRequest request = new SearchRequest("test"); // wrt title and alias
        int count = cswService.getCSWServersCount(request, super.organizationNameTest);

        Assert.assertEquals(2, count);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverID);
        Assert.assertTrue(deleted);
    }

    @Test
    public void testSearchCSWServersNothing() {
        PaginatedSearchRequest request = new PaginatedSearchRequest("nothing", 10, 0);
        List<ServerCSWDTO> search = cswService.searchCSWServers(request, super.organizationNameTest);

        Assert.assertNull(search);
    }

    @Test
    public void testSearchCSWServers() {
        PaginatedSearchRequest request = new PaginatedSearchRequest("test", 10, 0); // wrt title
        List<ServerCSWDTO> search = cswService.searchCSWServers(request, super.organizationNameTest);

        Assert.assertNotNull(search);
        Assert.assertEquals(1, search.size());
    }

    @Test
    public void testSearchCSWServersMore() throws Exception {
        // Insert 27 servers (only 25 for matching wrt alias)
        Long[] serverIDs = new Long[27];
        for (int i = 1; i <= 27; i++) {
            GeoPlatformServer server = super.createCSWServer("Mock title " + i,
                                                             "http://url.mock-" + i,
                                                             organizationTest);
            if (i >= 3) {
                server.setAliasName("Alias test " + i);
            }
            serverIDs[i - 1] = cswService.insertServerCSW(server);

            Assert.assertNotNull(serverIDs[i - 1]);
        }

        // First page
        PaginatedSearchRequest request = new PaginatedSearchRequest("test", 10, 0); // wrt title and alias
        List<ServerCSWDTO> search = cswService.searchCSWServers(request, super.organizationNameTest);

        Assert.assertNotNull(search);
        Assert.assertEquals(10, search.size());

        // Second page
        request = new PaginatedSearchRequest("test", 10, 1); // wrt title and alias
        search = cswService.searchCSWServers(request, super.organizationNameTest);

        Assert.assertNotNull(search);
        Assert.assertEquals(10, search.size());

        // Third page
        request = new PaginatedSearchRequest("test", 10, 2); // wrt title and alias
        search = cswService.searchCSWServers(request, super.organizationNameTest);

        Assert.assertNotNull(search);
        Assert.assertEquals(6, search.size());

        // Delete the servers
        for (Long serverID : serverIDs) {
            boolean deleted = cswService.deleteServerCSW(serverID);
            Assert.assertTrue(deleted);
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
