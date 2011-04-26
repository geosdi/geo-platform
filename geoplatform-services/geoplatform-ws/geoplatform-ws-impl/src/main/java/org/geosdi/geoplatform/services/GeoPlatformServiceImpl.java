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
package org.geosdi.geoplatform.services;

import java.util.List;
import javax.jws.WebService;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.RequestByUserFolder;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderList;
import org.geosdi.geoplatform.responce.LayerList;
import org.geosdi.geoplatform.responce.ShortServer;
import org.geosdi.geoplatform.responce.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@Transactional
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GeoPlatformService")
public class GeoPlatformServiceImpl implements GeoPlatformService {

    private GPUserDAO userDao;
    private GPServerDAO serverDao;
    private GPFolderDAO folderDao;
    private UserServiceImpl userServiceDelegate;
    private WMSServiceImpl wmsServiceDelegate;
    private FolderServiceImpl folderServiceDelegate;

    public GeoPlatformServiceImpl() {
        userServiceDelegate = new UserServiceImpl();
        folderServiceDelegate = new FolderServiceImpl();
        wmsServiceDelegate = new WMSServiceImpl();
    }

    // ==========================================================================
    // === Users
    // ==========================================================================
    @Override
    public long updateUser(GPUser user) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return userServiceDelegate.updateUser(user);
    }

    @Override
    public UserList searchUsers(PaginatedSearchRequest request) {
        return userServiceDelegate.searchUsers(request);
    }

    @Override
    public long insertUser(GPUser user) {
        return userServiceDelegate.insertUser(user);
    }

    @Override
    public long getUsersCount(SearchRequest request) {
        return userServiceDelegate.getUsersCount(request);
    }

    @Override
    public UserList getUsers() {
        return userServiceDelegate.getUsers();
    }

    @Override
    public GPUser getUser(RequestById request) throws ResourceNotFoundFault {
        return userServiceDelegate.getUser(request);
    }

    @Override
    public GPUser getUserByName(SearchRequest username)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getUserByName(username);
    }

    @Override
    public boolean deleteUser(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return userServiceDelegate.deleteUser(request);
    }

    // ==========================================================================
    // === Folder
    // ==========================================================================
    @Override
    public long insertFolder(GPFolder folder) {
        return this.folderServiceDelegate.insertFolder(folder);
    }

    @Override
    public long updateFolder(GPFolder folder) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return folderServiceDelegate.updateFolder(folder);
    }

    @Override
    public GPFolder getFolder(RequestById request) throws ResourceNotFoundFault {
        return folderServiceDelegate.getFolder(request);
    }

    @Override
    public boolean deleteFolder(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.deleteFolder(request);
    }

    @Override
    public FolderList getFolders() {
        return folderServiceDelegate.getFolders();
    }

    @Override
    public FolderList searchFolders(PaginatedSearchRequest searchRequest) {
        return folderServiceDelegate.searchFolders(searchRequest);
    }

    @Override
    public long getFoldersCount(SearchRequest searchRequest) {
        return folderServiceDelegate.getFoldersCount(searchRequest);
    }

    @Override
    public long getUserFoldersCount(RequestById request) {
        return folderServiceDelegate.getUserFoldersCount(request);
    }

    @Override
    public List<GPFolder> getUserFolders(RequestById request) {
        return folderServiceDelegate.getUserFolders(request);
    }

    @Override
    public FolderList getAllUserFolders(long userId, int num, int page) {
        return folderServiceDelegate.getAllUserFolders(userId, num, page);
    }

    @Override
    public int getAllUserFoldersCount(long userId) {
        return folderServiceDelegate.getAllUserFoldersCount(userId);
    }

    @Override
    public void setFolderShared(RequestById request)
            throws ResourceNotFoundFault {
        folderServiceDelegate.setFolderShared(request);
    }

    @Override
    public boolean setFolderOwner(RequestByUserFolder request)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.setFolderOwner(request, false);
    }

    @Override
    public void forceFolderOwner(RequestByUserFolder request)
            throws ResourceNotFoundFault {
        folderServiceDelegate.setFolderOwner(request, true);

    }

    // ==========================================================================
    // === OWS
    // ==========================================================================
    @Override
    public LayerList getCapabilities(RequestById request)
            throws ResourceNotFoundFault {
        return wmsServiceDelegate.getCapabilities(request);
    }

    @Override
    public ShortServer getServer(String serverUrl) throws ResourceNotFoundFault {
        // TODO Auto-generated method stub

        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);

        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }

        ShortServer shortServer = new ShortServer();
        shortServer.setId(server.getId());

        return shortServer;
    }

    // ==========================================================================
    // === DAOs IoC
    // ==========================================================================
    /**
     * @param userDao
     *            the userDao to set
     */
    @Autowired
    public void setUserDao(GPUserDAO theUserDao) {
        this.userDao = theUserDao;
        this.userServiceDelegate.setUserDao(userDao);
        this.folderServiceDelegate.setUserDao(userDao);
    }

    /**
     * @param serverDao
     *            the serverDao to set
     */
    @Autowired
    public void setServerDao(GPServerDAO serverDao) {
        this.serverDao = serverDao;
        this.wmsServiceDelegate.setServerDao(serverDao);
    }

    /**
     * @param folderDao
     *            the folderDao to set
     */
    @Autowired
    public void setFolderDao(GPFolderDAO theFolderDao) {
        this.folderDao = theFolderDao;
        this.folderServiceDelegate.setFolderDao(folderDao);
    }
}
