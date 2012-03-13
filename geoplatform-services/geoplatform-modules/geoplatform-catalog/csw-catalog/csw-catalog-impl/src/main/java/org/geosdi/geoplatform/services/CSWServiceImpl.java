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
package org.geosdi.geoplatform.services;

import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 */
class CSWServiceImpl {

    final private Logger logger = LoggerFactory.getLogger(CSWServiceImpl.class);
    // DAO
    private GPServerDAO serverDao;

    /**
     * @param serverDao the serverDao to set
     */
    void setServerDao(GPServerDAO serverDao) {
        this.serverDao = serverDao;
    }

    /**
     * @see GeoPlatformCSWService#insertServerCSW(org.geosdi.geoplatform.core.model.GeoPlatformServer)
     */
    Long insertServerCSW(GeoPlatformServer server) {
        /** IMPORTANT TO AVOID EXCEPTION IN DB FOR UNIQUE URL SERVER **/
        GeoPlatformServer serverSearch = serverDao.findByServerUrl(server.getServerUrl());
        if (serverSearch != null) {
            return serverSearch.getId();
        }

        server.setServerType(GPCapabilityType.CSW);

        serverDao.persist(server);
        return server.getId();
    }

    /**
     * @see GeoPlatformCSWService#deleteServerCSW(java.lang.Long) 
     */
    boolean deleteServerCSW(Long serverID) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        if (server.getServerType() != GPCapabilityType.CSW) {
            throw new ResourceNotFoundFault("Server is not a CSW server");
        }

        return serverDao.remove(server);
    }

    /**
     * @see GeoPlatformCSWService#getServerDetailCSW(java.lang.Long) 
     */
    GeoPlatformServer getServerDetailCSW(Long serverID)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        if (server.getServerType() != GPCapabilityType.CSW) {
            throw new ResourceNotFoundFault("Server is not a CSW server");
        }

        return server;
    }

    /**
     * @see GeoPlatformCSWService#getServerDetailCSWByUrl(java.lang.String) 
     */
    GeoPlatformServer getServerDetailCSWByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found by URL");
        }
        if (server.getServerType() != GPCapabilityType.CSW) {
            throw new ResourceNotFoundFault("Server is not a CSW server");
        }

        return server;
    }
}
