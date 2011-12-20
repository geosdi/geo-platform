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
import java.util.List;
import javax.jws.WebService;
import org.geosdi.geoplatform.configurator.jasypt.GPPooledPBEStringEncryptorDecorator;
import org.geosdi.geoplatform.responce.ApplicationDTO;
import org.springframework.transaction.annotation.Transactional;
import org.geosdi.geoplatform.core.acl.dao.AclClassDAO;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.acl.dao.AclObjectIdentityDAO;
import org.geosdi.geoplatform.core.acl.dao.AclSidDAO;
import org.geosdi.geoplatform.core.acl.dao.GuiComponentDAO;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.AccountExpiredFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.ShortRasterPropertiesDTO;
import org.geosdi.geoplatform.responce.StyleDTO;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 * 
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
@Transactional // Give atomicity on WS methods
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GeoPlatformService")
public class GeoPlatformServiceImpl implements GeoPlatformService {

    // DAO
    private GPAccountDAO accountDao;
    private GPAccountProjectDAO accountProjectDao;
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
    private AccountServiceImpl accountServiceDelegate;
    private ProjectServiceImpl projectServiceDelegate;
    private WMSServiceImpl wmsServiceDelegate;
    private FolderServiceImpl folderServiceDelegate;
    private LayerServiceImpl layerServiceDelegate;
    private AclServiceImpl aclServiceDelegate;
    // Services
    private GPSchedulerService schedulerService;
    //
    private GPPooledPBEStringEncryptorDecorator gpPooledPBEStringEncryptor;

    public GeoPlatformServiceImpl() {
        accountServiceDelegate = new AccountServiceImpl();
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
     * @param accountDao
     *            the accountDao to set
     */
    public void setAccountDao(GPAccountDAO accountDao) {
        this.accountDao = accountDao;
        this.accountServiceDelegate.setAccountDao(accountDao);
        this.projectServiceDelegate.setAccountDao(accountDao);
        this.aclServiceDelegate.setAccountDao(accountDao);
    }

    /**
     * @param accountProjectDao
     *            the accountProjectDao to set
     */
    public void setAccountProjectDao(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDao = accountProjectDao;
        this.accountServiceDelegate.setAccountProjectDao(accountProjectDao);
        this.projectServiceDelegate.setAccountProjectDao(accountProjectDao);
    }

    /**
     * @param projectDao
     *          the projectDao to set
     */
    public void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
        this.accountServiceDelegate.setProjectDao(projectDao);
        this.projectServiceDelegate.setProjectDao(projectDao);
        this.folderServiceDelegate.setProjectDao(projectDao);
        this.layerServiceDelegate.setProjectDao(projectDao);
    }

    /**
     * @param serverDao
     *            the serverDao to set
     */
    public void setServerDao(GPServerDAO serverDao) {
        this.serverDao = serverDao;
        this.wmsServiceDelegate.setServerDao(serverDao);
    }

    /**
     * @param folderDao
     *            the folderDao to set
     */
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
        this.accountServiceDelegate.setAuthorityDao(authorityDao);
        this.aclServiceDelegate.setAuthorityDao(authorityDao);
    }

    /**
     * @param classDao
     *          the classDao to set
     */
    public void setClassDao(AclClassDAO classDao) {
        this.classDao = classDao;
        this.aclServiceDelegate.setClassDao(classDao);
    }

    /**
     * @param sidDao
     *          the sidDao to set
     */
    public void setSidDao(AclSidDAO sidDao) {
        this.sidDao = sidDao;
        this.aclServiceDelegate.setSidDao(sidDao);
    }

    /**
     * @param objectIdentityDao
     *          the objectIdentityDao to set
     */
    public void setObjectIdentityDao(AclObjectIdentityDAO objectIdentityDao) {
        this.objectIdentityDao = objectIdentityDao;
        this.aclServiceDelegate.setObjectIdentityDao(objectIdentityDao);
    }

    /**
     * @param entryDao
     *          the entryDao to set
     */
    public void setEntryDao(AclEntryDAO entryDao) {
        this.entryDao = entryDao;
        this.aclServiceDelegate.setEntryDao(entryDao);
    }

    /**
     * @param guiComponentDao
     *          the guiComponentDao to set
     */
    public void setGuiComponentDao(GuiComponentDAO guiComponentDao) {
        this.guiComponentDao = guiComponentDao;
        this.aclServiceDelegate.setGuiComponentDao(guiComponentDao);
    }

    /**
     * @param schedulerService
     *          the schedulerService to set
     */
    public void setSchedulerService(GPSchedulerService schedulerService) {
        this.schedulerService = schedulerService;
        this.accountServiceDelegate.setSchedulerService(schedulerService);
    }

    /**
     * @param gpPooledPBEStringEncryptor the gpPooledPBEStringEncryptor to set
     * 
     */
    public void setGpPooledPBEStringEncryptor(GPPooledPBEStringEncryptorDecorator gpPooledPBEStringEncryptor) {
        this.gpPooledPBEStringEncryptor = gpPooledPBEStringEncryptor;
        this.accountServiceDelegate.setGpPooledPBEStringEncryptor(gpPooledPBEStringEncryptor);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Account">
    // ==========================================================================
    // === Account
    // ==========================================================================
    @Override
    public Long insertAccount(GPAccount account, boolean sendEmail)
            throws IllegalParameterFault {
        return accountServiceDelegate.insertAccount(account, sendEmail);
    }

    @Override
    public Long updateUser(GPUser user) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return accountServiceDelegate.updateUser(user);
    }

    @Override
    public Long updateApplication(GPApplication application) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return accountServiceDelegate.updateApplication(application);
    }

    @Override
    public Long updateOwnUser(UserDTO user,
            String currentPlainPassword, String newPlainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return accountServiceDelegate.updateOwnUser(user, currentPlainPassword, newPlainPassword);
    }

    @Override
    public boolean deleteAccount(Long accountID) throws ResourceNotFoundFault {
        return accountServiceDelegate.deleteAccount(accountID);
    }

    @Override
    public GPUser getUserDetail(Long userID) throws ResourceNotFoundFault {
        return accountServiceDelegate.getUserDetail(userID);
    }

    @Override
    public GPUser getUserDetailByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getUserDetailByUsername(request);
    }

    @Override
    public GPUser getUserDetailByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundFault, IllegalParameterFault, AccountExpiredFault {
        return accountServiceDelegate.getUserDetailByUsernameAndPassword(username, password);
    }

    @Override
    public GPApplication getApplicationDetail(Long applicationID) throws ResourceNotFoundFault {
        return accountServiceDelegate.getApplicationDetail(applicationID);
    }

    @Override
    public UserDTO getShortUser(Long userID) throws ResourceNotFoundFault {
        return accountServiceDelegate.getShortUser(userID);
    }

    @Override
    public UserDTO getShortUserByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getShortUserByUsername(request);
    }

    @Override
    public ApplicationDTO getShortApplication(Long applicationID) throws ResourceNotFoundFault {
        return accountServiceDelegate.getShortApplication(applicationID);
    }

    @Override
    public ApplicationDTO getShortApplicationByAppID(SearchRequest request) throws ResourceNotFoundFault {
        return accountServiceDelegate.getShortApplicationByAppID(request);
    }

    @Override
    public List<UserDTO> searchUsers(Long userID, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.searchUsers(userID, request);
    }

    @Override
    public List<ShortAccountDTO> getAccounts() {
        return accountServiceDelegate.getAccounts();
    }

    @Override
    public Long getAccountsCount(SearchRequest request) {
        return accountServiceDelegate.getAccountsCount(request);
    }

    @Override
    public List<String> getAuthorities(Long accountID)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getAuthorities(accountID);
    }

    @Override
    public List<GPAuthority> getAuthoritiesDetail(String stringID)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getAuthoritiesDetail(stringID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AccountProject">
    // ==========================================================================
    // === AccountProject
    // ==========================================================================
    @Override
    public Long insertAccountProject(GPAccountProject accountProject) throws IllegalParameterFault {
        return projectServiceDelegate.insertAccountProject(accountProject);
    }

    @Override
    public Long updateAccountProject(GPAccountProject accountProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.updateAccountProject(accountProject);
    }

    @Override
    public boolean deleteAccountProject(Long accountProjectID) throws ResourceNotFoundFault {
        return projectServiceDelegate.deleteAccountProject(accountProjectID);
    }

    @Override
    public GPAccountProject getAccountProject(Long accountProjectID) throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountProject(accountProjectID);
    }

    @Override
    public List<GPAccountProject> getAccountProjectsByAccountID(Long accountID) {
        return projectServiceDelegate.getAccountProjectsByAccountID(accountID);
    }

    @Override
    public List<GPAccountProject> getAccountProjectsByProjectID(Long projectID) {
        return projectServiceDelegate.getAccountProjectsByProjectID(projectID);
    }

    @Override
    public GPAccountProject getAccountProjectByAccountAndProjectIDs(Long accountID, Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountProjectByAccountAndProjectIDs(accountID, projectID);
    }

    @Override
    public Long getAccountProjectsCount(Long accountID, SearchRequest request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountProjectsCount(accountID, request);
    }

    @Override
    public List<ProjectDTO> searchAccountProjects(Long accountID, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.searchAccountProjects(accountID, request);
    }

    @Override
    public GPProject getDefaultProject(Long accountID) throws ResourceNotFoundFault {
        return projectServiceDelegate.getDefaultProject(accountID);
    }

    @Override
    public void updateDefaultProject(Long accountID, Long projectID) throws ResourceNotFoundFault {
        this.projectServiceDelegate.updateDefaultProject(accountID, projectID);
    }

    @Override
    public boolean saveAccountProjectProperties(AccountProjectPropertiesDTO accountProjectProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return this.projectServiceDelegate.saveAccountProjectProperties(accountProjectProperties);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================    
    @Override
    public Long saveProject(String stringID, GPProject project, boolean defaultProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return this.projectServiceDelegate.saveProject(stringID, project, defaultProject);
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
    public boolean deleteProject(Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.deleteProject(projectID);
    }

    @Override
    public GPProject getProjectDetail(Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getProjectDetail(projectID);
    }

    @Override
    public int getNumberOfElementsProject(Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getNumberOfElementsProject(projectID);
    }

    @Override
    public void setProjectShared(Long projectID)
            throws ResourceNotFoundFault {
        projectServiceDelegate.setProjectShared(projectID);
    }

    @Override
    public boolean setProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.setProjectOwner(request, false);
    }

    @Override
    public void forceProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault {
        projectServiceDelegate.setProjectOwner(request, true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    @Override
    public Long insertFolder(Long projectID, GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.insertFolder(projectID, folder);
    }

    @Override
    public Long updateFolder(GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.updateFolder(folder);
    }

    @Override
    public boolean deleteFolder(Long folderID) throws ResourceNotFoundFault {
        return folderServiceDelegate.deleteFolder(folderID);
    }

    @Override
    public Long saveFolderProperties(Long folderID, String name, boolean checked)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveFolderProperties(folderID, name, checked);
    }

    @Override
    public Long saveAddedFolderAndTreeModifications(Long projectID, Long parentID, GPFolder folder, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveAddedFolderAndTreeModifications(projectID, parentID, folder, descendantsMapData);
    }

    @Override
    public boolean saveDeletedFolderAndTreeModifications(Long folderID, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDeletedFolderAndTreeModifications(folderID, descendantsMapData);
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(Long folderID, boolean checked)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveCheckStatusFolderAndTreeModifications(folderID, checked);
    }

    @Override
    public boolean saveDragAndDropFolderAndTreeModifications(Long idFolderMoved, Long idNewParent, int newPosition,
            GPWebServiceMapData descendantsMapData) throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDragAndDropFolderModifications(idFolderMoved, idNewParent, newPosition, descendantsMapData);
    }

    @Override
    public FolderDTO getShortFolder(Long folderID) throws ResourceNotFoundFault {
        return folderServiceDelegate.getShortFolder(folderID);
    }

    @Override
    public GPFolder getFolderDetail(Long folderID) throws ResourceNotFoundFault {
        return folderServiceDelegate.getFolderDetail(folderID);
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
    public List<FolderDTO> getChildrenFoldersByRequest(RequestByID request) {
        return folderServiceDelegate.getChildrenFoldersByRequest(request);
    }

    @Override
    public List<FolderDTO> getChildrenFolders(Long folderID) {
        return folderServiceDelegate.getChildrenFolders(folderID);
    }

    @Override
    public TreeFolderElements getChildrenElements(Long folderID) {
        return folderServiceDelegate.getChildrenElements(folderID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / Project">
    @Override
    public List<FolderDTO> getRootFoldersByProjectID(Long projectID) {
        return projectServiceDelegate.getRootFoldersByProjectID(projectID);
    }

    @Override
    public ProjectDTO exportProject(Long projectID) throws ResourceNotFoundFault {
        return projectServiceDelegate.exportProject(projectID);
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
    public boolean deleteLayer(Long layerID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.deleteLayer(layerID);
    }

    @Override
    public Long saveAddedLayerAndTreeModifications(Long projectID, Long parentID, GPLayer layer, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayerAndTreeModifications(projectID, parentID, layer, descendantsMapData);
    }

    @Override
    public ArrayList<Long> saveAddedLayersAndTreeModifications(Long projectID, Long parentID, List<GPLayer> layers,
            GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayersAndTreeModifications(projectID, parentID, layers, descendantsMapData);
    }

    @Override
    public boolean saveDeletedLayerAndTreeModifications(Long id, GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveDeletedLayerAndTreeModifications(id, descendantsMapData);
    }

    @Override
    public boolean saveCheckStatusLayerAndTreeModifications(Long layerID, boolean checked)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveCheckStatusLayerAndTreeModifications(layerID, checked);
    }

    @Override
    public boolean fixCheckStatusLayerAndTreeModifications(Long layerID, Long oldFolderID, Long newFolderID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.fixCheckStatusLayerAndTreeModifications(layerID, oldFolderID, newFolderID);
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
    public GPRasterLayer getRasterLayer(Long layerID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getRasterLayer(layerID);
    }

    @Override
    public GPVectorLayer getVectorLayer(Long layerID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getVectorLayer(layerID);
    }

    @Override
    public List<ShortLayerDTO> getLayers(Long projectID) {
        return layerServiceDelegate.getLayers(projectID);
    }

    @Override
    public List<StyleDTO> getLayerStyles(Long layerID) {
        return layerServiceDelegate.getLayerStyles(layerID);
    }

    @Override
    public GPBBox getBBox(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getBBox(layerID);
    }

    @Override
    public GPLayerInfo getLayerInfo(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerInfo(layerID);
    }

//    @Override
//    public List<StyleDTO> getLayerStyles(Long layerID) {
//        return layerServiceDelegate.getLayerStyles(layerID);
//    }    
//
//    @Override
//    public Point getGeometry(Long layerID) throws ResourceNotFoundFault {
//        return layerServiceDelegate.getGeometry(layerID);
//    }
//    
    @Override
    public ShortLayerDTO getShortLayer(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getShortLayer(layerID);
    }

    @Override
    public GPLayerType getLayerType(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerType(layerID);
    }

    @Override
    public ArrayList<String> getLayersDataSourceByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayersDataSourceByProjectID(projectID);
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
    public ServerDTO getCapabilities(RequestByID request, String token)
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
    public GuiComponentsPermissionMapData getAccountGuiComponentVisible(Long accountID)
            throws ResourceNotFoundFault {
        return this.aclServiceDelegate.getAccountGuiComponentVisible(accountID);
    }
    //</editor-fold>
}
