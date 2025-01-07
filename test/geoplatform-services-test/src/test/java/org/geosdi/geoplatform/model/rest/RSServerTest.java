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
package org.geosdi.geoplatform.model.rest;

import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.server.WSSaveServerRequest;
import org.geosdi.geoplatform.response.ServerDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.Collection;

import static org.geosdi.geoplatform.core.model.GPCapabilityType.WMS;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class RSServerTest extends BasicRestServiceTest {

    @Override
    public void setUp() throws Exception {
        // Insert Organization
        super.setUpOrganization();
        // Insert Servers
        idServerTest = this.createAndInsertServer(serverUrlTest, WMS, organizationTest);
        idServerGeoSDI = this.createAndInsertServer(serverUrlGeoSDI, WMS, organizationTest);
    }

    @Test
    public void a_testUpdateServerRest() throws IllegalParameterFault, ResourceNotFoundFault {
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
        assertNotNull(serverModified);
        assertEquals(serverTest.getServerUrl(), serverModified.getServerUrl());
    }

    @Test
    public void b_testGetServerRest() throws ResourceNotFoundFault {
        // Get Server from Id
        GeoPlatformServer gpServer = gpWSClient.getServerDetail(idServerTest);
        logger.debug("\n*** gpServer:\n{}\n***", gpServer);
        assertNotNull(gpServer);
        assertEquals("Id Server NOT match", idServerTest, gpServer.getId().longValue());
        assertEquals("URL Server NOT match", serverUrlTest, gpServer.getServerUrl());
        // Get Server from serverUrl
        ServerDTO serverDTO = gpWSClient.getShortServer(serverUrlTest);
        logger.debug("\n*** serverDTO:\n{}\n***", serverDTO);
        assertNotNull(serverDTO);
        assertEquals("Id Server NOT match", idServerTest, serverDTO.getId().longValue());
        assertEquals("URL Server NOT match", serverUrlTest, serverDTO.getServerUrl());
    }

    @Test
    public void c_testGetAllServerRest() throws ResourceNotFoundFault {
        // Number of Servers
        Collection<ServerDTO> servers = gpWSClient.getAllServers(organizationNameRSTest).getServers();
        assertNotNull(servers);
        int totalServers = servers.size();
        assertTrue("Number of Servers stored into database", totalServers >= 1); // SetUp() added 1 server
        // Insert new Server
        long idNewServer = this.createAndInsertServer("map_rest.testGetAllServer.com", WMS, organizationTest);
        // Assert of number of Servers
        assertEquals("Total numebr of Servers is wrong after inserted new Server", gpWSClient.getAllServers(organizationNameRSTest).getServers().size(), totalServers + 1);
        // Delete new Server
        this.deleteServer(idNewServer);
        // Assert of number of Servers
        assertEquals("Total numebr of Servers is wrong after deleted new Server", gpWSClient.getAllServers(organizationNameRSTest).getServers().size(), totalServers);
    }

    @Test
    public void d_testSaveServerRest() throws ResourceNotFoundFault, IllegalParameterFault {
        logger.trace("\n@@@ testSaveServer @@@");
        // Server is into DB
        ServerDTO serverGeoSDI = gpWSClient.getShortServer(serverUrlGeoSDI);
        assertNotNull(serverGeoSDI);
        ServerDTO serverDTO = gpWSClient.saveServer(new WSSaveServerRequest(serverGeoSDI.getId(), "geoSDI", serverUrlGeoSDI, organizationTest.getName()));
        assertNotNull("ServerDTO geoSDI is NULL", serverDTO);
        assertEquals("ServerDTO geoSDI alias is wrong", "geoSDI", serverDTO.getAlias());
        // Server is NOT into DB
        String serverUrlEx = "http://iws.erdas.com/ecwp/ecw_wms.dll?request=GetCapabilities";
        serverDTO = gpWSClient.saveServer(new WSSaveServerRequest(null, "Erdas", serverUrlEx, organizationTest.getName()));
        assertNotNull("ServerDTO EX is NULL", serverDTO);
        // Check if the server was insert
        GeoPlatformServer serverEx = gpWSClient.getServerDetailByUrl(serverUrlEx);
        assertNotNull("Server Ex is NULL for URL", serverEx);
        assertEquals("Server Ex URL is NOT correct", serverUrlEx, serverEx.getServerUrl());
        assertEquals("Server Ex ID is NOT correct", serverDTO.getId(), serverEx.getId());
        // Delete server
        gpWSClient.deleteServer(serverEx.getId());
    }
}