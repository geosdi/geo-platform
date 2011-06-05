//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform;

import java.text.ParseException;
import java.util.Collection;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPCababilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;


import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.collection.LayerList;
import org.junit.Test;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
//@TestExecutionListeners(value = {WSListenerServices.class})
public class CXFServiceTest extends ServiceTest {

//    private GeoPlatformWSClientEncrypted gpWSClientEncrypted;
    private final String serverUrlTest = "http://map.serverNameTest.org";
    private long idServerTest = -1;
    // Server geoSDI
    private final String serverUrlGeoSDI = "http://dpc.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities";
    private final int numLayersGeoSDI = 251;    

    @Override
    public void setUp() throws Exception {
        // Insert Server
        idServerTest = this.createAndInsertServer(serverUrlTest, GPCababilityType.WMS);
    }

    @Override
    public void tearDown() {
        // Delete Server
        this.deleteServer(idServerTest);
    }

    @Test
    public void testUpdateServer() {
        final String serverUrlUpdated = serverUrlTest.replaceAll("org", "com");
        try {
            // Retrieve Server
            GeoPlatformServer serverTest = geoPlatformService.getServerDetail(idServerTest);
            logger.debug("\n*** serverTest:\n{}\n***", serverTest);
            // Update Server
            serverTest.setServerUrl(serverUrlUpdated);
            geoPlatformService.updateServer(serverTest);

            // Retrieve Server modified
            GeoPlatformServer serverModified = geoPlatformService.getServerDetail(idServerTest);
            logger.debug("\n*** serverModified:\n{}\n***", serverModified);
            // Assert on Server modified
            Assert.assertNotNull(serverModified);
            Assert.assertEquals(serverTest.getServerUrl(), serverModified.getServerUrl());
        } catch (IllegalParameterFault ex) {
            Assert.fail("Server has an Illegal Parameter");
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Server \"" + usernameTest + "\" not found");
        }
    }

    @Test
    public void testGetServer() {
        // Get Server from Id
        try {
            // Get GeoPlatformServer from id
            GeoPlatformServer gpServer = geoPlatformService.getServerDetail(idServerTest);
            logger.debug("\n*** gpServer:\n{}\n***", gpServer);
            Assert.assertNotNull(gpServer);
            Assert.assertEquals("Id Server NOT match", idServerTest, gpServer.getId());
            Assert.assertEquals("URL Server NOT match", serverUrlTest, gpServer.getServerUrl());
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Not found Server with Id: \"" + idServerTest + "\"");
        }

        // Get Server from serverUrl
        try {
            // Get ServerDTO from serverUrl
            ServerDTO serverDTO = geoPlatformService.getShortServer(serverUrlTest);
            logger.debug("\n*** serverDTO:\n{}\n***", serverDTO);
            Assert.assertNotNull(serverDTO);
            Assert.assertEquals("Id Server NOT match", idServerTest, serverDTO.getId());
            Assert.assertEquals("URL Server NOT match", serverUrlTest, serverDTO.getServerUrl());
        } catch (ResourceNotFoundFault ex) {
            Assert.fail("Not found Server with serverUrl: \"" + idServerTest + "\"");
        }
    }

    @Test
    public void testGetAllServer() {
        // Number of Servers
        Collection<ServerDTO> servers = geoPlatformService.getAllServers();
        Assert.assertNotNull(servers);
        int totalServers = servers.size();
        Assert.assertTrue("Number of Servers stored into database",
                totalServers >= 1); // SetUp() added 1 server

        // Insert new Server
        long idNewServer = this.createAndInsertServer("map.testGetAllServer.com", GPCababilityType.WMS);

        // Assert of number of Servers
        Assert.assertEquals("Total numebr of Servers is wrong after inserted new Server",
                geoPlatformService.getAllServers().size(), totalServers + 1);

        // Delete new Server
        this.deleteServer(idNewServer);

        // Assert of number of Servers
        Assert.assertEquals("Total numebr of Servers is wrong after deleted new Server",
                geoPlatformService.getAllServers().size(), totalServers);
    }

    @Test
    public void testGetCapabilities() throws ParseException,
            ResourceNotFoundFault {
        ServerDTO serverDTO = geoPlatformService.getShortServer(serverUrlGeoSDI);

        Assert.assertNotNull(serverDTO);

        LayerList layers = geoPlatformService.getCapabilities(new RequestById(serverDTO.getId()));
        logger.debug("\n*** NUMBER OF LAYERS FOR DPC {} ***", layers.getList().size());
    }

    @Test
    public void testSaveServer() throws ResourceNotFoundFault, IllegalParameterFault {
        logger.trace("\n@@@ testSaveServer @@@");
        // Server is into DB
        LayerList layers = geoPlatformService.saveServer(serverUrlGeoSDI);
        Assert.assertNotNull("LayerList is NULL for server URL:\n" + serverUrlGeoSDI, layers);

        Collection<ShortLayerDTO> layersList = layers.getList();
        Assert.assertNotNull("List of LayerList is NULL for server URL:\n" + serverUrlTest, layersList);
        Assert.assertEquals("LayerList must have " + numLayersGeoSDI + " layers", layersList.size(), numLayersGeoSDI);

        // Server is not into DB
        String serverUrlEx = "http://iws.erdas.com/ecwp/ecw_wms.dll?request=GetCapabilities";
        layers = geoPlatformService.saveServer(serverUrlEx);
        Assert.assertNotNull("LayerList is NULL for server URL:\n" + serverUrlEx, layers);

        layersList = layers.getList();
        Assert.assertNotNull("List of LayerList is NULL for server URL:\n" + serverUrlEx, layersList);
        // Check if the server was insert
        GeoPlatformServer serverEx = geoPlatformService.getServerDetailByUrl(serverUrlEx);
        Assert.assertNotNull("Server is NULL for URL:\n" + serverEx);
        Assert.assertEquals("Server should have the URL:\n" + serverUrlEx,
                serverEx.getServerUrl(), serverUrlEx);
        // Delete server
        geoPlatformService.deleteServer(serverEx.getId());
    }

    /**
     * Service methods
     * 
     */
    // Create and insert (with assert) a Server
    private long createAndInsertServer(String serverUrl, GPCababilityType serverType) {
        GeoPlatformServer server = this.createServer(serverUrl, serverType);
        logger.debug("\n*** GeoPlatformServer to INSERT:\n{}\n***", server);
        long idServer = geoPlatformService.insertServer(server);
        logger.debug("\n*** Id ASSIGNED at the Server in the DB: {} ***", idServer);
        Assert.assertTrue("Id ASSIGNED at the Server in the DB", idServer > 0);
        return idServer;
    }

    private GeoPlatformServer createServer(String serverUrl, GPCababilityType serverType) {
        // Create field's value from Regex on Server URL
        String serverName = serverUrl.replaceAll("http://(dpc|map|www)\\.([^\\.]+)\\.(org|it|com)", "$1.$2.$3");
        logger.debug("\n*** serverName: {} ***", serverName);
        String labelServer = serverName.replaceAll("(dpc|map|www)\\.([^\\.]+)\\.(org|it|com)", "$2");
        logger.debug("\n*** labelServer: {} ***", labelServer);
        // Create Server
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl(serverUrl);
        server.setName(serverName);
        server.setTitle(labelServer);
        server.setAbstractServer("Abstract of " + labelServer);
        server.setContactPerson("Contact Person of " + labelServer);
        server.setContactOrganization("Contact Organization of " + labelServer);
        server.setServerType(serverType);
        return server;
    }

    // Delete (with assert) a Server
    private void deleteServer(long idServer) {
        try {
            boolean check = geoPlatformService.deleteServer(idServer);
            Assert.assertTrue("Server with id = " + idServer + " has not been eliminated", check);
        } catch (Exception e) {
            Assert.fail("Error while deleting Server with Id: " + idServer);
        }
    }
}
