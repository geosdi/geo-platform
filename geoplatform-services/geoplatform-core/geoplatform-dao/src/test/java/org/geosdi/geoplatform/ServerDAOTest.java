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
package org.geosdi.geoplatform;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import org.geosdi.geoplatform.core.model.GPCababilityType;
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
        GeoPlatformServer server1 = createServer1();
        GeoPlatformServer server2 = createServer2();
        serverDAO.persist(server1, server2);
        logger.debug("\n*** SAVED Server:\n{}\n***", server1);
        logger.debug("\n*** SAVED Server:\n{}\n***", server2);
    }

    private GeoPlatformServer createServer1() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://imaa.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities");
        server.setName("imaa.geosdi.org");
        server.setServerType(GPCababilityType.WMS);
        return server;
    }

    private GeoPlatformServer createServer2() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://maps.telespazio.it/dpc/dpc-wms");
        server.setName("maps.telespazio.it");
        server.setServerType(GPCababilityType.WMS);
        return server;
    }
}
