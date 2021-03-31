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
package org.geosdi.geoplatform.core.delegate.impl.server;

import org.geosdi.geoplatform.core.dao.GPOrganizationDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.delegate.api.server.ServerDelegate;
import org.geosdi.geoplatform.core.model.GPAuthServer;
import org.geosdi.geoplatform.core.model.GPCapabilityType;

import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.server.WSSaveServerRequest;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.response.ServerDTOContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Server service delegate.
 *
 * @author Francesco Izzi - geoSDI
 * @email francesco.izzi@geosdi.org
 */
@Component(value = "gpServerDelegate")
public class GPServerDelegate implements ServerDelegate {
    
    private static final Logger logger = LoggerFactory.getLogger(
            GPServerDelegate.class);
    //
    @Autowired
    private GPServerDAO serverDao;
    @Autowired
    private GPOrganizationDAO organizationDao;
    
    @Override
    public Long insertServer(GeoPlatformServer server) {
        /**
         * IMPORTANT TO AVOID EXCEPTION IN DB FOR UNIQUE URL SERVER *
         */
        GeoPlatformServer serverSearch = serverDao.findByServerUrl(
                server.getServerUrl());
        if (serverSearch != null) {
            return serverSearch.getId();
        }
        
        serverDao.persist(server);
        return server.getId();
    }
    
    @Override
    public Long updateServer(GeoPlatformServer server) throws ResourceNotFoundFault, IllegalParameterFault {
        GeoPlatformServer orig = serverDao.find(server.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Server not found", server.getId());
        }
        // Update all properties
        orig.setServerUrl(server.getServerUrl());
        orig.setName(server.getName());
        orig.setTitle(server.getTitle());
        orig.setAbstractServer(server.getAbstractServer());
        orig.setOrganization(server.getOrganization());
        orig.setServerType(server.getServerType());
        orig.setAuthServer(server.getAuthServer());
        orig.setProxy(server.isProxy());
        serverDao.merge(orig);
        return orig.getId();
    }
    
    @Override
    public Boolean deleteServer(Long idServer) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(idServer);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", idServer);
        }
        
        return serverDao.remove(server);
    }
    
    @Override
    public GeoPlatformServer getServerDetail(Long idServer) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(idServer);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", idServer);
        }
        
        return server;
    }
    
    @Override
    public ServerDTO getShortServer(String serverUrl) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }
        
        return new ServerDTO(server);
    }
    
    @Override
    public ServerDTOContainer getAllServers(String organizationName) throws ResourceNotFoundFault {
        GPOrganization organization = organizationDao.findByName(organizationName);
        if (organization == null) {
            throw new ResourceNotFoundFault("Organization with name " + organizationName + "was not found.");
        }
        logger.info("##############################getAllServers");
        List<GeoPlatformServer> found = serverDao.findAll(organization.getId(),
                GPCapabilityType.WMS);
        logger.info("##############################found {}", found);

        return new ServerDTOContainer(convertToServerList(found));
    }
    
    @Override
    public GeoPlatformServer getServerDetailByUrl(String serverUrl) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found by URL");
        }
        
        return server;
    }
    
    @Override
    public ServerDTO saveServer(WSSaveServerRequest saveServerReq) throws IllegalParameterFault {
        if (saveServerReq == null) {
            throw new IllegalParameterFault("The WSSaveServerRequest must "
                    + "not be null.");
        }
        Long id = saveServerReq.getId();
        String serverUrl = saveServerReq.getServerUrl();
        String organization = saveServerReq.getOrganization();
        String aliasServerName = saveServerReq.getAliasServerName();
        
        try {
            URL serverURL = new URL(serverUrl);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: " + e);
            throw new IllegalParameterFault("Malformed URL");
        }
        
        GPOrganization org = organizationDao.findByName(organization);
        if (org == null) {
            throw new IllegalParameterFault("Server to save have an organization that does not exist");
        }
        
        GeoPlatformServer server;
        if (id != null) { // Existent server
            server = serverDao.find(id);
        } else { // New server
            if (this.isURLServerAlreadyExists(serverUrl)) {
                throw new IllegalParameterFault("Duplicated Server URL");
            }
            server = new GeoPlatformServer();
            server.setServerType(GPCapabilityType.WMS);
        }
        server.setAliasName(aliasServerName);
        server.setServerUrl(serverUrl);
        server.setAuthServer(new GPAuthServer(saveServerReq.getUsername(), saveServerReq.getPassword()));
        server.setProxy(saveServerReq.isProxy());
        server.setOrganization(org);
        serverDao.persist(server);
        return new ServerDTO(server);
    }

    /**
     ***************************************************************************
     */
    private boolean isURLServerAlreadyExists(String serverUrl) {
        return serverDao.findByServerUrl(serverUrl) != null;
    }
    
    private List<ServerDTO> convertToServerList(
            List<GeoPlatformServer> serverList) {
        List<ServerDTO> shortServers = new ArrayList<ServerDTO>(
                serverList.size());
        ServerDTO serverDTOIth = null;
        for (GeoPlatformServer server : serverList) {
            serverDTOIth = new ServerDTO(server);
            shortServers.add(serverDTOIth);
        }
        return shortServers;
    }
}
