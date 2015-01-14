/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.modelws;

import java.util.Collection;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 *
 */
public class CXFServiceTest extends ServiceTest {

//    private GeoPlatformWSClientEncrypted gpWSClientEncrypted;
    // Servers
    private final String serverUrlTest = "http://map.serverNameTest.foo";
    private long idServerTest = -1;
    private final String serverUrlGeoSDI = "http://imaa.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities";
    private long idServerGeoSDI = -1;
//    private final String serverUrlTelespazio = "http://maps.telespazio.it/dpc/dpc-wms?service=wms&version=1.1.1&request=GetCapabilities";
//    private long idServerTelespazio = -1;
//    private GPOrganization organizationTest;
//    private long idOrganizationTest;

    @Override
    public void setUp() throws Exception {
        // Insert Organization
        super.setUpOrganization();

        // Insert Servers
        idServerTest = this.createAndInsertServer(serverUrlTest, GPCapabilityType.WMS, organizationTest);
        idServerGeoSDI = this.createAndInsertServer(serverUrlGeoSDI, GPCapabilityType.WMS, organizationTest);
    }

    @Test
    public void testUpdateServer()
            throws IllegalParameterFault, ResourceNotFoundFault {
        final String serverUrlUpdated = serverUrlTest.replaceAll("org", "com");
        // Retrieve Server
        GeoPlatformServer serverTest = gpWSClient.getServerDetail(idServerTest);
        logger.debug("\n*** serverTest:\n{}\n***", serverTest);
        // Update Server
        serverTest.setServerUrl(serverUrlUpdated);
        gpWSClient.updateServer(serverTest);

        // Retrieve Server modified
        GeoPlatformServer serverModified = gpWSClient.getServerDetail(idServerTest);
        logger.debug("\n*** serverModified:\n{}\n***", serverModified);
        // Assert on Server modified
        Assert.assertNotNull(serverModified);
        Assert.assertEquals(serverTest.getServerUrl(), serverModified.getServerUrl());
    }

    @Test
    public void testGetServer() throws ResourceNotFoundFault {
        // Get Server from Id
        GeoPlatformServer gpServer = gpWSClient.getServerDetail(idServerTest);
        logger.debug("\n*** gpServer:\n{}\n***", gpServer);
        Assert.assertNotNull(gpServer);
        Assert.assertEquals("Id Server NOT match", idServerTest, gpServer.getId().longValue());
        Assert.assertEquals("URL Server NOT match", serverUrlTest, gpServer.getServerUrl());

        // Get Server from serverUrl
        ServerDTO serverDTO = gpWSClient.getShortServer(serverUrlTest);
        logger.debug("\n*** serverDTO:\n{}\n***", serverDTO);
        Assert.assertNotNull(serverDTO);
        Assert.assertEquals("Id Server NOT match", idServerTest, serverDTO.getId().longValue());
        Assert.assertEquals("URL Server NOT match", serverUrlTest, serverDTO.getServerUrl());
    }

    @Test
    public void testGetAllServer() throws ResourceNotFoundFault {
        // Number of Servers
        Collection<ServerDTO> servers = gpWSClient.getAllServers(organizationNameTest);
        Assert.assertNotNull(servers);
        int totalServers = servers.size();
        Assert.assertTrue("Number of Servers stored into database",
                totalServers >= 1); // SetUp() added 1 server

        // Insert new Server
        long idNewServer = this.createAndInsertServer("map.testGetAllServer.com", GPCapabilityType.WMS, organizationTest);

        // Assert of number of Servers
        Assert.assertEquals("Total numebr of Servers is wrong after inserted new Server",
                gpWSClient.getAllServers(organizationNameTest).size(), totalServers + 1);

        // Delete new Server
        this.deleteServer(idNewServer);

        // Assert of number of Servers
        Assert.assertEquals("Total numebr of Servers is wrong after deleted new Server",
                gpWSClient.getAllServers(organizationNameTest).size(), totalServers);
    }


    @Test
    public void testSaveServer() throws ResourceNotFoundFault, IllegalParameterFault {
        logger.trace("\n@@@ testSaveServer @@@");
        // Server is into DB
        ServerDTO serverGeoSDI = gpWSClient.getShortServer(serverUrlGeoSDI);
        Assert.assertNotNull(serverGeoSDI);
        ServerDTO serverDTO = gpWSClient.saveServer(serverGeoSDI.getId(), "geoSDI", serverUrlGeoSDI, organizationTest.getName());
        Assert.assertNotNull("ServerDTO geoSDI is NULL", serverDTO);
        Assert.assertEquals("ServerDTO geoSDI alias is wrong", serverDTO.getAlias(), "geoSDI");

        // Server is NOT into DB
        String serverUrlEx = "http://iws.erdas.com/ecwp/ecw_wms.dll?request=GetCapabilities";
        serverDTO = gpWSClient.saveServer(null, "Erdas", serverUrlEx, organizationTest.getName());
        Assert.assertNotNull("ServerDTO EX is NULL", serverDTO);

        // Check if the server was insert
        GeoPlatformServer serverEx = gpWSClient.getServerDetailByUrl(serverUrlEx);
        Assert.assertNotNull("Server Ex is NULL for URL", serverEx);
        Assert.assertEquals("Server Ex URL is NOT correct", serverUrlEx, serverEx.getServerUrl());
        Assert.assertEquals("Server Ex ID is NOT correct", serverDTO.getId(), serverEx.getId());
        // Delete server
        gpWSClient.deleteServer(serverEx.getId());
    }

    /**
     * Create and insert (with assert) a Server.
     */
    private long createAndInsertServer(String serverUrl, GPCapabilityType serverType, GPOrganization organization) {
        GeoPlatformServer server = this.createServer(serverUrl, serverType, organization);
        logger.debug("\n*** GeoPlatformServer to INSERT:\n{}\n***", server);
        long idServer = gpWSClient.insertServer(server);
        logger.debug("\n*** Id ASSIGNED at the Server in the DB: {} ***", idServer);
        Assert.assertTrue("Id ASSIGNED at the Server in the DB", idServer > 0);
        return idServer;
    }

    private GeoPlatformServer createServer(String serverUrl, GPCapabilityType serverType, GPOrganization organization) {
        // Create field's value from Regex on Server URL
        String serverName = serverUrl.replaceAll("http://(\\w+)\\.([^\\.]+)\\.(\\w+)", "$1.$2.$3");
        logger.trace("\n*** serverName: {} ***", serverName);
        String labelServer = serverName.replaceAll("(\\w+)\\.([^\\.]+)\\.(\\w+)", "$2");
        logger.trace("\n*** labelServer: {} ***", labelServer);
        // Create Server
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl(serverUrl);
        server.setName(serverName);
        server.setTitle(labelServer);
        server.setAbstractServer("Abstract of " + labelServer);
        server.setServerType(serverType);
        server.setOrganization(organization);
        return server;
    }

    /**
     * Delete (with assert) a Server.
     */
    private void deleteServer(long idServer) {
        try {
            boolean check = gpWSClient.deleteServer(idServer);
            Assert.assertTrue("Server with id = " + idServer + " has not been eliminated", check);
        } catch (Exception e) {
            Assert.fail("Error while deleting Server with Id: " + idServer);
        }
    }
}
