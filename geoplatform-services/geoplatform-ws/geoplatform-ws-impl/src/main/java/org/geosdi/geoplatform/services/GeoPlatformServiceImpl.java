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

import java.util.ArrayList;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import java.util.List;
import javax.jws.WebService;
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
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.dao.GPUserProjectsDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPUserProjects;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.RequestByUserProject;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.ShortRasterPropertiesDTO;
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
    private GPUserProjectsDAO userProjectsDao;
    private GPProjectDAO projectDao;
    private GPServerDAO serverDao;
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
//    private GPStyleDAO styleDao;
    private GPAuthorityDAO authorityDao;
    // ACL DAO
    private AclClassDAO classDao;
    private AclSidDAO sidDao;
    private AclObjectIdentityDAO objectIdentityDao;
    private AclEntryDAO entryDao;
    private GuiComponentDAO guiComponentDao;
    // Delegate
    private UserServiceImpl userServiceDelegate;
    private ProjectServiceImpl projectServiceDelegate;
    private WMSServiceImpl wmsServiceDelegate;
    private FolderServiceImpl folderServiceDelegate;
    private LayerServiceImpl layerServiceDelegate;
    private AclServiceImpl aclServiceDelegate;

    public GeoPlatformServiceImpl() {
        userServiceDelegate = new UserServiceImpl();
        projectServiceDelegate = new ProjectServiceImpl();
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
        this.projectServiceDelegate.setUserDao(userDao);
        this.layerServiceDelegate.setUserDao(userDao);
        this.aclServiceDelegate.setUserDao(userDao);
    }

    /**
     * @param userProjectsDao
     *            the userProjectsDao to set
     */
    @Autowired
    public void setUserProjectsDao(GPUserProjectsDAO userProjectsDao) {
        this.userProjectsDao = userProjectsDao;
        this.userServiceDelegate.setUserProjectsDao(userProjectsDao);
        this.projectServiceDelegate.setUserProjectsDao(userProjectsDao);
    }

    /**
     * @param projectDao
     *          the projectDao to set
     */
    @Autowired
    public void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
        this.userServiceDelegate.setProjectDao(projectDao);
        this.projectServiceDelegate.setProjectDao(projectDao);
        this.folderServiceDelegate.setProjectDao(projectDao);
        this.layerServiceDelegate.setProjectDao(projectDao);
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
        this.projectServiceDelegate.setFolderDao(folderDao);
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
        this.projectServiceDelegate.setLayerDao(layerDao);
    }

//    /**
//     * @param styleDao
//     *            the styleDao to set
//     */
//    @Autowired
//    public void setStyleDao(GPStyleDAO styleDao) {
//        this.styleDao = styleDao;
//        this.layerServiceDelegate.setStyleDao(styleDao);
//    }
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
    public Long insertUser(GPUser user) throws IllegalParameterFault {
        return userServiceDelegate.insertUser(user);
    }

    @Override
    public Long updateUser(GPUser user) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return userServiceDelegate.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long userId) throws ResourceNotFoundFault {
        return userServiceDelegate.deleteUser(userId);
    }

    @Override
    public UserDTO getShortUser(Long userId) throws ResourceNotFoundFault {
        return userServiceDelegate.getShortUser(userId);
    }

    @Override
    public GPUser getUserDetail(Long userId) throws ResourceNotFoundFault {
        return userServiceDelegate.getUserDetail(userId);
    }

    @Override
    public UserDTO getShortUserByName(SearchRequest request)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getShortUserByName(request);
    }

    @Override
    public GPUser getUserDetailByName(SearchRequest request)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getUserDetailByName(request);
    }

    @Override
    public List<UserDTO> searchUsers(Long userId, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        return userServiceDelegate.searchUsers(userId, request);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userServiceDelegate.getUsers();
    }

    @Override
    public Long getUsersCount(SearchRequest request) {
        return userServiceDelegate.getUsersCount(request);
    }

    @Override
    public GPUser getUserDetailByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return userServiceDelegate.getUserDetailByUsernameAndPassword(username, password);
    }

    @Override
    public List<String> getUserAuthorities(Long userId)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getUserAuthorities(userId);
    }

    @Override
    public List<GPAuthority> getUserGPAuthorities(String username)
            throws ResourceNotFoundFault {
        return userServiceDelegate.getUserGPAuthorities(username);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UserProjects">
    // ==========================================================================
    // === UserProjects
    // ==========================================================================
    @Override
    public Long insertUserProject(GPUserProjects userProject) throws IllegalParameterFault {
        return projectServiceDelegate.insertUserProject(userProject);
    }

    @Override
    public Long updateUserProject(GPUserProjects userProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.updateUserProject(userProject);
    }

    @Override
    public boolean deleteUserProject(Long userProjectId) throws ResourceNotFoundFault {
        return projectServiceDelegate.deleteUserProject(userProjectId);
    }

    @Override
    public GPUserProjects getUserProject(Long userProjectId) throws ResourceNotFoundFault {
        return projectServiceDelegate.getUserProject(userProjectId);
    }

    @Override
    public List<GPUserProjects> getUserProjectsByUserId(Long userId) {
        return projectServiceDelegate.getUserProjectsByUserId(userId);
    }

    @Override
    public List<GPUserProjects> getUserProjectsByProjectId(Long projectId) {
        return projectServiceDelegate.getUserProjectsByProjectId(projectId);
    }

    @Override
    public GPUserProjects getUserProjectByUserAndProjectId(Long userId, Long projectId)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getUserProjectByUserAndProjectId(userId, projectId);
    }

    @Override
    public Long getUserProjectsCount(Long userId, SearchRequest request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getUserProjectsCount(userId, request);
    }

    @Override
    public List<ProjectDTO> searchUserProjects(Long userId, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.searchUserProjects(userId, request);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================    
    @Override
    public Long saveProject(String username, GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return this.projectServiceDelegate.saveProject(username, project);
    }

    @Override
    public Long insertProject(GPProject project) throws IllegalParameterFault {
        return this.projectServiceDelegate.insertProject(project);
    }

    @Override
    public Long updateProject(GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.updateProject(project);
    }

    @Override
    public boolean deleteProject(Long projectId)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.deleteProject(projectId);
    }

    @Override
    public GPProject getProjectDetail(Long projectId)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getProjectDetail(projectId);
    }

    @Override
    public int getNumberOfElementsProject(Long projectId)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getNumberOfElementsProject(projectId);
    }

    @Override
    public void setProjectShared(Long projectId)
            throws ResourceNotFoundFault {
        projectServiceDelegate.setProjectShared(projectId);
    }

    @Override
    public boolean setProjectOwner(RequestByUserProject request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.setProjectOwner(request, false);
    }

    @Override
    public void forceProjectOwner(RequestByUserProject request)
            throws ResourceNotFoundFault {
        projectServiceDelegate.setProjectOwner(request, true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    @Override
    public Long insertFolder(Long projectId, GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.insertFolder(projectId, folder);
    }

    @Override
    public Long updateFolder(GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.updateFolder(folder);
    }

    @Override
    public boolean deleteFolder(Long folderId) throws ResourceNotFoundFault {
        return folderServiceDelegate.deleteFolder(folderId);
    }

    @Override
    public Long saveFolderProperties(Long folderId, String name, boolean checked)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveFolderProperties(folderId, name, checked);
    }

    @Override
    public Long saveAddedFolderAndTreeModifications(Long projectId, Long parentId, GPFolder folder, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveAddedFolderAndTreeModifications(projectId, parentId, folder, descendantsMapData);
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(Long userFolderId, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDeletedFolderAndTreeModifications(userFolderId, descendantsMapData);
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(Long userFolderId, boolean checked)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveCheckStatusFolderAndTreeModifications(userFolderId, checked);
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(Long idFolderMoved, Long idNewParent, int newPosition,
            GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDragAndDropFolderModifications(idFolderMoved, idNewParent, newPosition, descendantsMapData);
    }

    @Override
    public FolderDTO getShortFolder(Long userFolderId) throws ResourceNotFoundFault {
        return folderServiceDelegate.getShortFolder(userFolderId);
    }

    @Override
    public GPFolder getFolderDetail(Long folderId) throws ResourceNotFoundFault {
        return folderServiceDelegate.getFolderDetail(folderId);
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
    public List<FolderDTO> getChildrenFoldersByRequest(RequestById request) {
        return folderServiceDelegate.getChildrenFoldersByRequest(request);
    }

    @Override
    public List<FolderDTO> getChildrenFolders(Long userFolderId) {
        return folderServiceDelegate.getChildrenFolders(userFolderId);
    }

    @Override
    public TreeFolderElements getChildrenElements(Long userFolderId) {
        return folderServiceDelegate.getChildrenElements(userFolderId);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / Project">
    @Override
    public List<FolderDTO> getRootFoldersByProjectId(Long projectId) {
        return projectServiceDelegate.getRootFoldersByProjectId(projectId);
    }

    @Override
    public ProjectDTO exportProject(Long projectId) throws ResourceNotFoundFault {
        return projectServiceDelegate.exportProject(projectId);
    }

    @Override
    public Long importProject(ProjectDTO projectDTO, Long userID)
            throws IllegalParameterFault, ResourceNotFoundFault {
        return projectServiceDelegate.importProject(projectDTO, userID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Layer / Style">
    // ==========================================================================
    // === Layer / Style
    // ==========================================================================
    @Override
    public Long insertLayer(GPLayer layer) throws IllegalParameterFault {
        return layerServiceDelegate.insertLayer(layer);
    }

    @Override
    public Long updateRasterLayer(GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.updateRasterLayer(layer);
    }

    @Override
    public Long updateVectorLayer(GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.updateVectorLayer(layer);
    }

    @Override
    public boolean deleteLayer(Long layerId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.deleteLayer(layerId);
    }

    @Override
    public Long saveAddedLayerAndTreeModifications(GPLayer layer, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayerAndTreeModifications(layer, descendantsMapData);
    }

    @Override
    public ArrayList<Long> saveAddedLayersAndTreeModifications(Long projectId, List<GPLayer> layers,
            GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayersAndTreeModifications(projectId, layers,
                descendantsMapData);
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(Long id, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveDeletedLayerAndTreeModifications(id, descendantsMapData);
    }

    @Override
    public boolean saveCheckStatusLayerAndTreeModifications(Long layerId, boolean checked)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveCheckStatusLayerAndTreeModifications(layerId, checked);
    }

    @Override
    public boolean fixCheckStatusLayerAndTreeModifications(Long layerId, Long oldUserFolderId, Long newUserFolderId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.fixCheckStatusLayerAndTreeModifications(layerId, oldUserFolderId, newUserFolderId);
    }

    @Override
    public boolean saveDragAndDropLayerAndTreeModifications(Long idLayerMoved, Long idNewParent, int newPosition,
            GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveDragAndDropLayerModifications(idLayerMoved, idNewParent, newPosition, descendantsMapData);
    }

    @Override
    public boolean saveLayerProperties(ShortRasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveLayerProperties(layerProperties);
    }

    @Override
    public GPRasterLayer getRasterLayer(Long layerId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getRasterLayer(layerId);
    }

    @Override
    public GPVectorLayer getVectorLayer(Long layerId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getVectorLayer(layerId);
    }

    @Override
    public List<ShortLayerDTO> getLayers(Long projectId) {
        return layerServiceDelegate.getLayers(projectId);
    }

    @Override
    public List<StyleDTO> getLayerStyles(Long layerId) {
        return layerServiceDelegate.getLayerStyles(layerId);
    }

    @Override
    public GPBBox getBBox(Long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getBBox(layerId);
    }

    @Override
    public GPLayerInfo getLayerInfo(Long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerInfo(layerId);
    }

//    @Override
//    public List<StyleDTO> getLayerStyles(Long layerId) {
//        return layerServiceDelegate.getLayerStyles(layerId);
//    }    
//
//    @Override
//    public Point getGeometry(Long layerId) throws ResourceNotFoundFault {
//        return layerServiceDelegate.getGeometry(layerId);
//    }
//    
    @Override
    public ShortLayerDTO getShortLayer(Long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getShortLayer(layerId);
    }

    @Override
    public GPLayerType getLayerType(Long layerId) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerType(layerId);
    }

    @Override
    public ArrayList<String> getLayersDataSourceByProjectId(Long projectId)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayersDataSourceByProjectId(projectId);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OWS">
    // ==========================================================================
    // === OWS
    // ==========================================================================
    @Override
    public Long insertServer(GeoPlatformServer server) {
        return wmsServiceDelegate.insertServer(server);
    }

    @Override
    public Long updateServer(GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return wmsServiceDelegate.updateServer(server);
    }

    @Override
    public boolean deleteServer(Long idServer) throws ResourceNotFoundFault {
        return wmsServiceDelegate.deleteServer(idServer);
    }

    @Override
    public GeoPlatformServer getServerDetail(Long idServer)
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
    public ServerDTO saveServer(Long id, String aliasServerName, String serverUrl)
            throws IllegalParameterFault, ResourceNotFoundFault {
        return wmsServiceDelegate.saveServer(id, aliasServerName, serverUrl);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ACL">
    @Override
    public GuiComponentsPermissionMapData getUserGuiComponentVisible(Long userId)
            throws ResourceNotFoundFault {
        return this.aclServiceDelegate.getUserGuiComponentVisible(userId);
    }
    //</editor-fold>
}
