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
package org.geosdi.geoplatform.services;

import javax.jws.WebService;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPStyleDAO;
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
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.collection.StyleList;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.collection.FolderList;
import org.geosdi.geoplatform.responce.collection.LayerList;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.geosdi.geoplatform.responce.collection.UserList;
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

    // DAO
    private GPUserDAO userDao;
    private GPServerDAO serverDao;
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
    private GPStyleDAO styleDao;
    // Delegate
    private UserServiceImpl userServiceDelegate;
    private WMSServiceImpl wmsServiceDelegate;
    private FolderServiceImpl folderServiceDelegate;
    private LayerSericeImpl layerServiceDelegate;

    public GeoPlatformServiceImpl() {
        userServiceDelegate = new UserServiceImpl();
        folderServiceDelegate = new FolderServiceImpl();
        wmsServiceDelegate = new WMSServiceImpl();
        layerServiceDelegate = new LayerSericeImpl();
    }

    //<editor-fold defaultstate="collapsed" desc="DAOs IoC">
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

    /**
     * @param layerDao
     *            the layerDao to set
     */
    @Autowired
    public void setLayerDao(GPLayerDAO theLayerDao) {
        this.layerDao = theLayerDao;
        this.folderServiceDelegate.setLayerDao(layerDao);
        this.layerServiceDelegate.setLayerDao(layerDao);
    }

    /**
     * @param styleDao
     *            the styleDao to set
     */
    @Autowired
    public void setStyleDao(GPStyleDAO theStyleDao) {
        this.styleDao = theStyleDao;
        this.layerServiceDelegate.setStyleDao(styleDao);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User">
    // ==========================================================================
    // === User
    // ==========================================================================
    @Override
    public long insertUser(GPUser user) {
        return userServiceDelegate.insertUser(user);
    }

    @Override
    public long updateUser(GPUser user) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return userServiceDelegate.updateUser(user);
    }

    @Override
    public boolean deleteUser(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return userServiceDelegate.deleteUser(request);
    }

    @Override
    public UserDTO getShortUser(RequestById request) throws ResourceNotFoundFault {
        return userServiceDelegate.getShortUser(request);
    }

    @Override
    public GPUser getUserDetail(RequestById request) throws ResourceNotFoundFault {
        return userServiceDelegate.getUserDetail(request);
    }

    @Override
    public UserDTO getShortUserByName(SearchRequest username)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getShortUserByName(username);
    }

    @Override
    public GPUser getUserDetailByName(SearchRequest username)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getUserDetailByName(username);
    }

    @Override
    public UserList searchUsers(PaginatedSearchRequest request) {
        return userServiceDelegate.searchUsers(request);
    }

    @Override
    public UserList getUsers() {
        return userServiceDelegate.getUsers();
    }

    @Override
    public long getUsersCount(SearchRequest request) {
        return userServiceDelegate.getUsersCount(request);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
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
    public boolean deleteFolder(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.deleteFolder(request);
    }

    @Override
    public FolderDTO getShortFolder(RequestById request) throws ResourceNotFoundFault {
        return folderServiceDelegate.getShortFolder(request);
    }

    @Override
    public GPFolder getFolderDetail(RequestById request) throws ResourceNotFoundFault {
        return folderServiceDelegate.getFolderDetail(request);
    }

    @Override
    public FolderList searchFolders(PaginatedSearchRequest searchRequest) {
        return folderServiceDelegate.searchFolders(searchRequest);
    }

    @Override
    public FolderList getFolders() {
        return folderServiceDelegate.getFolders();
    }

    @Override
    public long getFoldersCount(SearchRequest searchRequest) {
        return folderServiceDelegate.getFoldersCount(searchRequest);
    }

    @Override
    public FolderList getChildrenFolders(long folderId, int num, int page) {
        return folderServiceDelegate.getChildrenFolders(folderId, num, page);
    }

    @Override
    public FolderList getChildrenFoldersByFolderId(long folderId) {
        return folderServiceDelegate.getChildrenFolders(folderId);
    }

    @Override
    public TreeFolderElements getChildrenElements(long folderId) {
        return folderServiceDelegate.getChildrenElements(folderId);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / User">
    // ==========================================================================
    // === Folder / User
    // ==========================================================================
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

    @Override
    public FolderList getUserFoldersByRequest(RequestById request) {
        return folderServiceDelegate.getUserFoldersByRequest(request);
    }

    @Override
    public FolderList getUserFoldersByUserId(long userId) {
        return folderServiceDelegate.getUserFoldersByUserId(userId);
    }

    @Override
    public FolderList getAllUserFolders(long userId, int num, int page) {
        return folderServiceDelegate.getAllUserFolders(userId, num, page);
    }

    @Override
    public FolderList getAllUserFoldersByUserId(long userId) {
        return folderServiceDelegate.getAllUserFoldersByUserId(userId);
    }

    @Override
    public long getUserFoldersCount(RequestById request) {
        return folderServiceDelegate.getUserFoldersCount(request);
    }

    @Override
    public int getAllUserFoldersCount(long userId) {
        return folderServiceDelegate.getAllUserFoldersCount(userId);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Layer / Style">
    // ==========================================================================
    // === Layer / Style
    // ==========================================================================
    @Override
    public StyleList getLayerStayls(long layerId) {
        return layerServiceDelegate.getLayerStyles(layerId);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OWS">
    // ==========================================================================
    // === OWS
    // ==========================================================================
    @Override
    public LayerList getCapabilities(RequestById request)
            throws ResourceNotFoundFault {
        return wmsServiceDelegate.getCapabilities(request);
    }

    @Override
    public ServerDTO getServer(String serverUrl) throws ResourceNotFoundFault {
        // TODO Auto-generated method stub

        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);

        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }

        ServerDTO serverDTO = new ServerDTO();
        serverDTO.setId(server.getId());

        return serverDTO;
    }
    //</editor-fold>
}
