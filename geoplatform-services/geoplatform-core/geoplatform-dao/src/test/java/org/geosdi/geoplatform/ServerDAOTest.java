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

import junit.framework.Assert;

import org.geosdi.geoplatform.core.model.GPCababilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.junit.Test;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class ServerDAOTest extends BaseDAOTest {


    @Test
    public void testServers() {
        Assert.assertNotNull(serverDAO);

        removeAllServers();

        insertServers();

        logger.info("NUMBER OF PERSISTED SERVERS ******************* "
                + serverDAO.findAll().size());
    }

    private void removeAllServers() {
        List<GeoPlatformServer> servers = serverDAO.findAll();
        for (GeoPlatformServer server : servers) {
            logger.info("Delete Server ************************ " + server);
            boolean ret = serverDAO.remove(server);
            Assert.assertTrue("Old Server not removed", ret);
        }

    }

    protected void insertServers() {
        GeoPlatformServer server = createServer1();
        GeoPlatformServer server1 = createServer2();
        serverDAO.persist(server, server1);
        logger.info("Persist Servers: " + server + " - " + server1);
    }

    private GeoPlatformServer createServer1() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://dpc.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities");
        server.setServerType(GPCababilityType.WMS);
        return server;
    }

    private GeoPlatformServer createServer2() {
        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("http://maps.telespazio.it/dpc/dpc-wms");
        server.setServerType(GPCababilityType.WMS);
        return server;
    }
}
