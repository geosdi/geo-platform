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
package org.geosdi.geoplatform;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class ServerDAOTest extends BaseDAOTest {

    @Test
    public void testServers() {
        logger.trace("\n\t@@@ testServers @@@");
        Assert.assertNotNull("serverDAO is NULL", super.serverDAO);

        removeAllServers();

        insertServers();

        logger.info("\n*** Number of Servers into DB: {} ***",
                serverDAO.findAll().size());

        Assert.assertTrue("Error on number of total servers", serverDAO.findAll().size() >= 2);
        Assert.assertTrue("Error on number of WMS servers", serverDAO.findAll(GPCapabilityType.WMS).size() == 2);
//        Assert.assertTrue("Error on number of CSW servers", serverDAO.findAll(GPCapabilityType.CSW).size() >= 1);
    }

    private void removeAllServers() {
        List<GeoPlatformServer> servers = serverDAO.findAll();
        for (GeoPlatformServer server : servers) {
            logger.debug("\n*** Server to REMOVE:\n{}\n***", server);
            boolean ret = serverDAO.remove(server);
            Assert.assertTrue("Old Server NOT removed", ret);
        }
    }

    protected void insertServers() {
        // WMS
        GeoPlatformServer server1WMS = createServer1WMS();
        GeoPlatformServer server2WMS = createServer2WMS();
        serverDAO.persist(server1WMS, server2WMS);
        logger.debug("\n*** SAVED Server:\n{}\n***", server1WMS);
        logger.debug("\n*** SAVED Server:\n{}\n***", server2WMS);
        // CSW
//        GeoPlatformServer server1CSW = createServer1CSW();
//        serverDAO.persist(server1CSW);
//        logger.debug("\n*** SAVED Server:\n{}\n***", server1CSW);
        //
//        this.insertDummyCSWServer();
    }

    private GeoPlatformServer createServer1WMS() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://imaa.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities");
        server.setName("imaa.geosdi.org");
        server.setAliasName("geoSdi on IMAA");
        server.setServerType(GPCapabilityType.WMS);
        return server;
    }

    private GeoPlatformServer createServer2WMS() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://dpc.geosdi.org/geoserver/wms");
        server.setName("dpc.geosdi.org");
        server.setAliasName("DPC on geosdi");
        server.setServerType(GPCapabilityType.WMS);
        return server;
    }

    private GeoPlatformServer createServer3WMS() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("https://earthbuilder.google.com/13496919088645259843-03170733828027579281-4/wms/?request=GetCapabilities");
        server.setName("earthbuilder.google.com");
        server.setAliasName("EARTHBUILDER");
        server.setServerType(GPCapabilityType.WMS);
        return server;
    }

    private GeoPlatformServer createServer1CSW() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://catalog.geosdi.org/geonetwork/srv/en/csw");
        server.setName("csw.geosdi.org");
        server.setAliasName("CSW on geosdi");
        server.setServerType(GPCapabilityType.CSW);
        return server;
    }

    private void insertDummyCSWServer() {
        for (int i = 10; i <= 99; i++) {
            GeoPlatformServer server = new GeoPlatformServer();
            server.setTitle("Title_" + i);
            server.setAliasName("Z_Alias_" + i);
            server.setServerUrl("http://csw-test/" + i);
            server.setServerType(GPCapabilityType.CSW);
            serverDAO.persist(server);
        }
    }
}
