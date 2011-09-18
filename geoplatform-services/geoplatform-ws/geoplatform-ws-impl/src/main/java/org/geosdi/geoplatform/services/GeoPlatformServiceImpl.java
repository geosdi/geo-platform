//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import java.util.List;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.cxf.binding.soap.SoapFault;
import org.geosdi.geoplatform.core.acl.dao.AclClassDAO;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.acl.dao.AclObjectIdentityDAO;
import org.geosdi.geoplatform.core.acl.dao.AclSidDAO;
import org.geosdi.geoplatform.core.acl.dao.GuiComponentDAO;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPStyleDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.RequestByUserFolder;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.StyleDTO;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@Transactional // Give atomicity on WS methods
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GeoPlatformService")
public class GeoPlatformServiceImpl implements GeoPlatformService {

    // DAO
    private GPUserDAO userDao;
    private GPServerDAO serverDao;
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
    private GPStyleDAO styleDao;
    private GPAuthorityDAO authorityDao;
    // ACL
    private AclClassDAO classDao;
    private AclSidDAO sidDao;
    private AclObjectIdentityDAO objectIdentityDao;
    private AclEntryDAO entryDao;
    private GuiComponentDAO guiComponentDao;
    // Delegate
    private UserServiceImpl userServiceDelegate;
    private WMSServiceImpl wmsServiceDelegate;
    private FolderServiceImpl folderServiceDelegate;
    private LayerServiceImpl layerServiceDelegate;
    private AclServiceImpl aclServiceDelegate;

    public GeoPlatformServiceImpl() {
        userServiceDelegate = new UserServiceImpl();
        folderServiceDelegate = new FolderServiceImpl();
        wmsServiceDelegate = new WMSServiceImpl();
        layerServiceDelegate = new LayerServiceImpl();
        aclServiceDelegate = new AclServiceImpl();
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
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
        this.userServiceDelegate.setUserDao(userDao);
        this.folderServiceDelegate.setUserDao(userDao);
        this.layerServiceDelegate.setUserDao(userDao);
        this.aclServiceDelegate.setUserDao(userDao);
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
    public void setFolderDao(GPFolderDAO folderDao) {
        this.folderDao = folderDao;
        this.folderServiceDelegate.setFolderDao(folderDao);
        this.layerServiceDelegate.setFolderDao(folderDao);
    }

    /**
     * @param layerDao
     *            the layerDao to set
     */
    @Autowired
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
        this.folderServiceDelegate.setLayerDao(layerDao);
        this.layerServiceDelegate.setLayerDao(layerDao);
    }

    /**
     * @param styleDao
     *            the styleDao to set
     */
    @Autowired
    public void setStyleDao(GPStyleDAO styleDao) {
        this.styleDao = styleDao;
        this.layerServiceDelegate.setStyleDao(styleDao);
    }

    /**
     * @param authorityDao
     *          the authorityDao to set
     */
    public void setAuthorityDao(GPAuthorityDAO authorityDao) {
        this.authorityDao = authorityDao;
        this.userServiceDelegate.setAuthorityDao(authorityDao);
        this.aclServiceDelegate.setAuthorityDao(authorityDao);
    }

    /**
     * @param classDao
     *          the classDao to set
     */
    @Autowired
    public void setClassDao(AclClassDAO classDao) {
        this.classDao = classDao;
        this.aclServiceDelegate.setClassDao(classDao);
    }

    /**
     * @param sidDao
     *          the sidDao to set
     */
    @Autowired
    public void setSidDao(AclSidDAO sidDao) {
        this.sidDao = sidDao;
        this.aclServiceDelegate.setSidDao(sidDao);
    }

    /**
     * @param objectIdentityDao
     *          the objectIdentityDao to set
     */
    @Autowired
    public void setObjectIdentityDao(AclObjectIdentityDAO objectIdentityDao) {
        this.objectIdentityDao = objectIdentityDao;
        this.aclServiceDelegate.setObjectIdentityDao(objectIdentityDao);
    }

    /**
     * @param entryDao
     *          the entryDao to set
     */
    @Autowired
    public void setEntryDao(AclEntryDAO entryDao) {
        this.entryDao = entryDao;
        this.aclServiceDelegate.setEntryDao(entryDao);
    }

    /**
     * @param guiComponentDao
     *          the guiComponentDao to set
     */
    @Autowired
    public void setGuiComponentDao(GuiComponentDAO guiComponentDao) {
        this.guiComponentDao = guiComponentDao;
        this.aclServiceDelegate.setGuiComponentDao(guiComponentDao);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User">
    // ==========================================================================
    // === User
    // ==========================================================================
    @Override
    public long insertUser(GPUser user) throws IllegalParameterFault {
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
    public List<UserDTO> searchUsers(PaginatedSearchRequest request) {
        return userServiceDelegate.searchUsers(request);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userServiceDelegate.getUsers();
    }

    @Override
    public long getUsersCount(SearchRequest request) {
        return userServiceDelegate.getUsersCount(request);
    }

    @Override
    public GPUser getUserDetailByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundFault, SOAPFaultException {
        GPUser user = null;
        try {
            user = userServiceDelegate.getUserDetailByUsernameAndPassword(username);
            if (user == null) {
                throw new ResourceNotFoundFault("User with specified username was not found");
            }
            System.out.println("### user: " + user.toString());
            if (!user.verify(password)) {
                throw new SoapFault("Specified password was incorrect", new QName("password"));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new SoapFault(e.getMessage(), null);
        }
        return user;
    }

    @Override
    public List<String> getUserAuthorities(long userId)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getUserAuthorities(userId);
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
    public long saveAddedFolderAndTreeModifications(GPFolder folder, GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        return folderServiceDelegate.saveAddedFolderAndTreeModifications(folder, descendantsMapData);
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(long id, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveDeletedFolderAndTreeModifications(id, descendantsMapData);
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(long folderId, boolean isChecked)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveCheckStatusFolderAndTreeModifications(folderId, isChecked);
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(String username, long idElementMoved, long idNewParent, int newPseudoPosition,
            GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDragAndDropFolderModifications(username, idElementMoved, idNewParent, newPseudoPosition, descendantsMapData);
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
    public List<FolderDTO> searchFolders(PaginatedSearchRequest searchRequest) {
        return folderServiceDelegate.searchFolders(searchRequest);
    }

    @Override
    public List<FolderDTO> getFolders() {
        return folderServiceDelegate.getFolders();
    }

    @Override
    public long getFoldersCount(SearchRequest searchRequest) {
        return folderServiceDelegate.getFoldersCount(searchRequest);
    }

    @Override
    public List<FolderDTO> getChildrenFolders(long folderId, int num, int page) {
        return folderServiceDelegate.getChildrenFolders(folderId, num, page);
    }

    @Override
    public List<FolderDTO> getChildrenFoldersByFolderId(long folderId) {
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
    public List<FolderDTO> getUserFoldersByRequest(RequestById request) {
        return folderServiceDelegate.getUserFoldersByRequest(request);
    }

    @Override
    public List<FolderDTO> getUserFoldersByUserId(long userId) {
        return folderServiceDelegate.getUserFoldersByUserId(userId);
    }

    @Override
    public List<FolderDTO> getAllUserFolders(long userId, int num, int page) {
        return folderServiceDelegate.getAllUserFolders(userId, num, page);
    }

    @Override
    public List<FolderDTO> getAllUserFoldersByUserId(long userId) {
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
    public long insertLayer(GPLayer layer) {
        return layerServiceDelegate.insertLayer(layer);
    }

    @Override
    public long updateRasterLayer(GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.updateRasterLayer(layer);
    }

    @Override
    public long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.updateVectorLayer(layer);
    }

    @Override
    public boolean deleteLayer(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.deleteLayer(request);
    }

    @Override
    public long saveAddedLayerAndTreeModifications(String username,
            GPLayer layer, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayerAndTreeModifications(username, layer, descendantsMapData);
    }

    @Override
    public ArrayList<Long> saveAddedLayersAndTreeModifications(String username,
            List<GPLayer> layers, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayersAndTreeModifications(username, layers, descendantsMapData);
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(long id, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveDeletedLayerAndTreeModifications(id, descendantsMapData);
    }

    @Override
    public boolean saveCheckStatusLayerAndTreeModifications(long layerId, boolean isChecked)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveCheckStatusLayerAndTreeModifications(layerId, isChecked);
    }

    @Override
    public boolean fixCheckStatusLayerAndTreeModifications(long layerId, long oldFolderId, long newFolderId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.fixCheckStatusLayerAndTreeModifications(layerId, oldFolderId, newFolderId);
    }

    @Override
    public boolean saveDragAndDropLayerAndTreeModifications(String username, long idElementMoved,
            long idNewParent, int newPosition, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveDragAndDropLayerModifications(username, idElementMoved, idNewParent, newPosition, descendantsMapData);
    }

    @Override
    public boolean saveLayerProperties(String username, long layerId, String alias, float opacity, boolean checked)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveLayerProperties(username, layerId, alias, opacity, checked);
    }

    @Override
    public GPRasterLayer getRasterLayer(long layerId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getRasterLayer(layerId);
    }

    @Override
    public GPVectorLayer getVectorLayer(long layerId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getVectorLayer(layerId);
    }

    @Override
    public List<ShortLayerDTO> getLayers() {
        return layerServiceDelegate.getLayers();
    }

    @Override
    public List<StyleDTO> getLayerStyles(long layerId) {
        return layerServiceDelegate.getLayerStyles(layerId);
    }

    @Override
    public GPBBox getBBox(long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getBBox(layerId);
    }

    @Override
    public GPLayerInfo getLayerInfo(long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerInfo(layerId);
    }

//    @Override
//    public Point getGeometry(long layerId) throws ResourceNotFoundFault {
//        return layerServiceDelegate.getGeometry(layerId);
//    }
    @Override
    public ShortLayerDTO getShortLayer(long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getShortLayer(layerId);
    }

    @Override
    public GPLayerType getLayerType(long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerType(layerId);
    }

    @Override
    public ArrayList<String> getLayersDataSourceByOwner(String userName) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayersDataSourceByOwner(userName);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OWS">
    // ==========================================================================
    // === OWS
    // ==========================================================================
    @Override
    public long insertServer(GeoPlatformServer server) {
        return wmsServiceDelegate.insertServer(server);
    }

    @Override
    public long updateServer(GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return wmsServiceDelegate.updateServer(server);
    }

    @Override
    public boolean deleteServer(long idServer) throws ResourceNotFoundFault, IllegalParameterFault {
        return wmsServiceDelegate.deleteServer(idServer);
    }

    @Override
    public GeoPlatformServer getServerDetail(long idServer)
            throws ResourceNotFoundFault {
        return wmsServiceDelegate.getServerDetail(idServer);
    }

    @Override
    public ServerDTO getShortServer(String serverUrl) throws ResourceNotFoundFault {
        return wmsServiceDelegate.getShortServer(serverUrl);
    }

    @Override
    public List<ServerDTO> getAllServers() {
        return wmsServiceDelegate.getServers();
    }

    @Override
    public GeoPlatformServer getServerDetailByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        return wmsServiceDelegate.getServerDetailByUrl(serverUrl);
    }

    @Override
    public ServerDTO getCapabilities(RequestById request, String token)
            throws ResourceNotFoundFault {
        return wmsServiceDelegate.getCapabilities(request, token);
    }

    @Override
    public ServerDTO saveServer(Long id, String aliasServerName, String serverUrl, String token)
            throws ResourceNotFoundFault {
        return wmsServiceDelegate.saveServer(id, aliasServerName, serverUrl, token);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ACL">
    @Override
    public GuiComponentsPermissionMapData getUserGuiComponentVisible(long userId)
            throws ResourceNotFoundFault {
        return this.aclServiceDelegate.getUserGuiComponentVisible(userId);
    }
    //</editor-fold>
}