/**
 *
 * geo-platform Rich webgis framework http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details. You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is making a
 * combined work based on this library. Thus, the terms and conditions of the
 * GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules, and
 * to copy and distribute the resulting executable under terms of your choice,
 * provided that you also meet, for each linked independent module, the terms
 * and conditions of the license of that module. An independent module is a
 * module which is not derived from or based on this library. If you modify this
 * library, you may extend this exception to your version of the library, but
 * you are not obligated to do so. If you do not wish to do so, delete this
 * exception statement from your version.
 */
package org.geosdi.geoplatform.services;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.geosdi.geoplatform.core.acl.dao.AclClassDAO;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.acl.dao.AclObjectIdentityDAO;
import org.geosdi.geoplatform.core.acl.dao.AclSidDAO;
import org.geosdi.geoplatform.core.acl.dao.GuiComponentDAO;
import org.geosdi.geoplatform.core.dao.*;
import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.jasypt.support.GPDigesterConfigurator;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.folder.InsertFolderRequest;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.PutAccountsProjectRequest;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.request.folder.WSAddFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.folder.WSDeleteFolderAndTreeModifications;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.request.layer.InsertLayerRequest;
import org.geosdi.geoplatform.request.layer.WSAddLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSAddLayersAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDDLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDeleteLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.message.MarkMessageReadByDateRequest;
import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.request.project.ImportProjectRequest;
import org.geosdi.geoplatform.request.project.SaveProjectRequest;
import org.geosdi.geoplatform.request.server.WSSaveServerRequest;
import org.geosdi.geoplatform.request.viewport.InsertViewportRequest;
import org.geosdi.geoplatform.request.viewport.ManageViewportRequest;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.ApplicationDTO;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.GetDataSourceResponse;
import org.geosdi.geoplatform.responce.collection.LongListStore;
import org.geosdi.geoplatform.responce.MessageDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.RasterPropertiesDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTOContainer;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.WSGetAccountProjectsResponse;
import org.geosdi.geoplatform.responce.authority.GetAuthorityResponse;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElementsStore;
import org.springframework.transaction.annotation.Transactional;

/**
 * Web Service implementation of {@link GeoPlatformService} endpoint.
 *
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 */
@Transactional // Give atomicity on WS methods
@WebService(
        endpointInterface = "org.geosdi.geoplatform.services.GeoPlatformService")
public class GeoPlatformServiceImpl implements GeoPlatformService {

    // DAO
    private GPAccountDAO accountDAO;
    private GPAccountProjectDAO accountProjectDAO;
    private GPProjectDAO projectDAO;
    private GPServerDAO serverDAO;
    private GPFolderDAO folderDAO;
    private GPLayerDAO layerDAO;
//    private GPStyleDAO styleDao;
    private GPAuthorityDAO authorityDAO;
    private GPOrganizationDAO organizationDAO;
    private GPMessageDAO messageDAo;
    private GPViewportDAO viewportDAO;
    // Access Info DAO
    private GSResourceDAO gsResourceDAO;
    private GSAccountDAO gsAccountDAO;
    // ACL DAO
    private AclClassDAO classDAO;
    private AclSidDAO sidDAO;
    private AclObjectIdentityDAO objectIdentityDAO;
    private AclEntryDAO entryDAO;
    private GuiComponentDAO guiComponentDAO;
    // Delegate
    private OrganizationServiceImpl organizationServiceDelegate;
    private AccountServiceImpl accountServiceDelegate;
    private ProjectServiceImpl projectServiceDelegate;
    private ViewportServiceImpl viewportServiceDelegate;
    private FolderServiceImpl folderServiceDelegate;
    private LayerServiceImpl layerServiceDelegate;
    private AclServiceImpl aclServiceDelegate;
    private ServerServiceImpl serverServiceDelegate;
    private MessageServiceImpl messageServiceDelegate;
    private SchedulerServiceImpl schedulerServiceDelegate;
    // Services
    private GPSchedulerService schedulerService;
    // Utility
    private GPDigesterConfigurator gpDigester;

    /**
     * Default constructor create each service delegate.
     */
    public GeoPlatformServiceImpl() {
        organizationServiceDelegate = new OrganizationServiceImpl();
        accountServiceDelegate = new AccountServiceImpl();
        projectServiceDelegate = new ProjectServiceImpl();
        projectServiceDelegate.setAccountService(accountServiceDelegate);
        viewportServiceDelegate = new ViewportServiceImpl();
        folderServiceDelegate = new FolderServiceImpl();
        layerServiceDelegate = new LayerServiceImpl();
        aclServiceDelegate = new AclServiceImpl();
        serverServiceDelegate = new ServerServiceImpl();
        messageServiceDelegate = new MessageServiceImpl();
        schedulerServiceDelegate = new SchedulerServiceImpl();
    }

    //<editor-fold defaultstate="collapsed" desc="DAOs IoC">
    // =========================================================================
    // === DAOs IoC
    // =========================================================================
    /**
     * @param accountDao the accountDao to set
     */
    public void setAccountDAO(GPAccountDAO accountDao) {
        this.accountDAO = accountDao;
        this.accountServiceDelegate.setAccountDao(accountDao);
        this.projectServiceDelegate.setAccountDao(accountDao);
        this.aclServiceDelegate.setAccountDao(accountDao);
        this.messageServiceDelegate.setAccountDao(accountDao);
    }

    /**
     * @param serverDao the serverDao to set
     */
    public void setServerDAO(GPServerDAO serverDao) {
        this.serverDAO = serverDao;
        this.serverServiceDelegate.setServerDao(serverDao);
    }

    /**
     * @param accountProjectDao the accountProjectDao to set
     */
    public void setAccountProjectDAO(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDAO = accountProjectDao;
        this.accountServiceDelegate.setAccountProjectDao(accountProjectDao);
        this.projectServiceDelegate.setAccountProjectDao(accountProjectDao);
        this.viewportServiceDelegate.setAccountProjectDao(accountProjectDao);
    }

    /**
     * @param projectDao the projectDao to set
     */
    public void setProjectDAO(GPProjectDAO projectDao) {
        this.projectDAO = projectDao;
        this.accountServiceDelegate.setProjectDao(projectDao);
        this.projectServiceDelegate.setProjectDao(projectDao);
        this.folderServiceDelegate.setProjectDao(projectDao);
        this.layerServiceDelegate.setProjectDao(projectDao);
    }

    /**
     * @param folderDao the folderDao to set
     */
    public void setFolderDAO(GPFolderDAO folderDao) {
        this.folderDAO = folderDao;
        this.folderServiceDelegate.setFolderDao(folderDao);
        this.layerServiceDelegate.setFolderDao(folderDao);
        this.projectServiceDelegate.setFolderDao(folderDao);
    }

    /**
     * @param layerDao the layerDao to set
     */
    public void setLayerDAO(GPLayerDAO layerDao) {
        this.layerDAO = layerDao;
        this.folderServiceDelegate.setLayerDao(layerDao);
        this.layerServiceDelegate.setLayerDao(layerDao);
        this.projectServiceDelegate.setLayerDao(layerDao);
    }
//
//    /**
//     * @param styleDao
//     *            the styleDao to set
//     */
//    public void setStyleDao(GPStyleDAO styleDao) {
//        this.styleDao = styleDao;
//        this.layerServiceDelegate.setStyleDao(styleDao);
//    }

    /**
     * @param authorityDao the authorityDao to set
     */
    public void setAuthorityDAO(GPAuthorityDAO authorityDao) {
        this.authorityDAO = authorityDao;
        this.accountServiceDelegate.setAuthorityDao(authorityDao);
        this.aclServiceDelegate.setAuthorityDao(authorityDao);
    }

    /**
     * @param organizationDao the organizationDao to set
     */
    public void setOrganizationDAO(GPOrganizationDAO organizationDao) {
        this.organizationDAO = organizationDao;
        this.organizationServiceDelegate.setOrganizationDao(organizationDao);
        this.aclServiceDelegate.setOrganizationDao(organizationDao);
        this.accountServiceDelegate.setOrganizationDao(organizationDao);
        this.serverServiceDelegate.setOrganizationDao(organizationDao);
    }

    /**
     * @param messageDao the messageDao to set
     */
    public void setMessageDAO(GPMessageDAO messageDao) {
        this.messageDAo = messageDao;
        this.messageServiceDelegate.setMessageDao(messageDao);
    }

    /**
     * @param classDao the classDao to set
     */
    public void setClassDAO(AclClassDAO classDao) {
        this.classDAO = classDao;
        this.aclServiceDelegate.setClassDao(classDao);
    }

    /**
     * @param sidDao the sidDao to set
     */
    public void setSidDAO(AclSidDAO sidDao) {
        this.sidDAO = sidDao;
        this.aclServiceDelegate.setSidDao(sidDao);
    }

    /**
     * @param objectIdentityDao the objectIdentityDao to set
     */
    public void setObjectIdentityDAO(AclObjectIdentityDAO objectIdentityDao) {
        this.objectIdentityDAO = objectIdentityDao;
        this.aclServiceDelegate.setObjectIdentityDao(objectIdentityDao);
    }

    /**
     * @param entryDao the entryDao to set
     */
    public void setEntryDAO(AclEntryDAO entryDao) {
        this.entryDAO = entryDao;
        this.aclServiceDelegate.setEntryDao(entryDao);
    }

    /**
     * @param guiComponentDao the guiComponentDao to set
     */
    public void setGuiComponentDAO(GuiComponentDAO guiComponentDao) {
        this.guiComponentDAO = guiComponentDao;
        this.aclServiceDelegate.setGuiComponentDao(guiComponentDao);
    }

    /**
     * @param schedulerService the schedulerService to set
     */
    public void setSchedulerService(GPSchedulerService schedulerService) {
        this.schedulerService = schedulerService;
        this.schedulerServiceDelegate.setSchedulerService(schedulerService);
        this.accountServiceDelegate.setSchedulerService(schedulerService);
    }

    /**
     * @param gpDigester the gpDigester to set
     *
     */
    public void setGpDigester(GPDigesterConfigurator gpDigester) {
        this.gpDigester = gpDigester;
        this.accountServiceDelegate.setGpDigester(gpDigester);
    }

    /**
     * @param viewportDAO the viewportDAO to set
     */
    public void setViewportDAO(GPViewportDAO viewportDAO) {
        this.viewportDAO = viewportDAO;
        this.viewportServiceDelegate.setViewportDao(viewportDAO);
    }

    /**
     * @param gsAccountDAO the gsAccountDAO to set
     */
    public void setGsAccountDAO(GSAccountDAO gsAccountDAO) {
        this.gsAccountDAO = gsAccountDAO;
    }

    /**
     * @param gsResourceDAO the gsResourceDAO to set
     */
    public void setGsResourceDAO(GSResourceDAO gsResourceDAO) {
        this.gsResourceDAO = gsResourceDAO;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Organization">
    // =========================================================================
    // === Organization
    // =========================================================================
    @Override
    public Long insertOrganization(GPOrganization organization) throws
            IllegalParameterFault {
        return organizationServiceDelegate.insertOrganization(organization);
    }

    @Override
    public Boolean deleteOrganization(Long organizationID) throws
            ResourceNotFoundFault {
        return organizationServiceDelegate.deleteOrganization(organizationID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Account (User and Application)">
    // =========================================================================
    // === Account
    // =========================================================================
    @Override
    public Long insertAccount(InsertAccountRequest insertAccountRequest)
            throws IllegalParameterFault {
        return accountServiceDelegate.insertAccount(insertAccountRequest);
    }

    @Override
    public void sendCASNewUserNotification(List<String> emailRecipient,
            String userNameToNotify) throws IllegalParameterFault {
        this.schedulerServiceDelegate.sendCASNewUserNotification(emailRecipient,
                userNameToNotify);
    }

    @Override
    public Long updateUser(GPUser user) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return accountServiceDelegate.updateUser(user);
    }

    @Override
    public Long updateApplication(GPApplication application) throws
            ResourceNotFoundFault,
            IllegalParameterFault {
        return accountServiceDelegate.updateApplication(application);
    }

    @Override
    public Long updateOwnUser(UserDTO user,
            String currentPlainPassword,
            String newPlainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return accountServiceDelegate.updateOwnUser(user, currentPlainPassword,
                newPlainPassword);
    }

    @Override
    public Boolean deleteAccount(Long accountID) throws ResourceNotFoundFault {
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
    public GPUser getUserDetailByUsernameAndPassword(String username,
            String plainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault,
            AccountLoginFault {
        return accountServiceDelegate.getUserDetailByUsernameAndPassword(
                username, plainPassword);
    }

    @Override
    public GPApplication getApplicationDetail(Long applicationID) throws
            ResourceNotFoundFault {
        return accountServiceDelegate.getApplicationDetail(applicationID);
    }

    @Override
    public GPApplication getApplication(String appID)
            throws ResourceNotFoundFault, AccountLoginFault {
        return accountServiceDelegate.getApplication(appID);
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
    public ApplicationDTO getShortApplication(Long applicationID) throws
            ResourceNotFoundFault {
        return accountServiceDelegate.getShortApplication(applicationID);
    }

    @Override
    public ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getShortApplicationByAppID(request);
    }

    @Override
    public List<UserDTO> searchUsers(Long userID,
            PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.searchUsers(userID, request);
    }

    @Override
    public ShortAccountDTOContainer getAllAccounts() {
        return accountServiceDelegate.getAllAccounts();
    }

    @Override
    public ShortAccountDTOContainer getAccounts(String organization)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getAccounts(organization);
    }

    @Override
    public Long getAccountsCount(SearchRequest request) {
        return accountServiceDelegate.getAccountsCount(request);
    }

    @Override
    public Long getUsersCount(String organization,
            SearchRequest request) {
        return accountServiceDelegate.getUsersCount(organization, request);
    }

    @Override
    public GetAuthorityResponse getAuthorities(Long accountID)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getAuthorities(accountID);
    }

    @Override
    public List<GPAuthority> getAuthoritiesDetail(String accountNaturalID)
            throws ResourceNotFoundFault {
        return accountServiceDelegate.getAuthoritiesDetail(accountNaturalID);
    }

    @Override
    public void forceTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault {
        accountServiceDelegate.forceTemporaryAccount(accountID);
    }

    @Override
    public void forceExpiredTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault, IllegalParameterFault {
        accountServiceDelegate.forceExpiredTemporaryAccount(accountID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AccountProject">
    // =========================================================================
    // === AccountProject
    // =========================================================================
    @Override
    public Long insertAccountProject(GPAccountProject accountProject) throws
            IllegalParameterFault {
        return projectServiceDelegate.insertAccountProject(accountProject);
    }

    @Override
    public Long updateAccountProject(GPAccountProject accountProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.updateAccountProject(accountProject);
    }

    @Override
    public Boolean deleteAccountProject(Long accountProjectID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.deleteAccountProject(accountProjectID);
    }

    @Override
    public GPAccountProject getAccountProject(Long accountProjectID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getAccountProject(accountProjectID);
    }

    @Override
    public WSGetAccountProjectsResponse getAccountProjectsByAccountID(Long accountID) {
        return projectServiceDelegate.getAccountProjectsByAccountID(accountID);
    }

    @Override
    public WSGetAccountProjectsResponse getAccountProjectsByProjectID(Long projectID) {
        return projectServiceDelegate.getAccountProjectsByProjectID(projectID);
    }

    @Override
    public GPAccountProject getAccountProjectByAccountAndProjectIDs(
            Long accountID,
            Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountProjectByAccountAndProjectIDs(
                accountID, projectID);
    }

    @Override
    public Long getAccountProjectsCount(Long accountID,
            SearchRequest request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountProjectsCount(accountID, request);
    }

    @Override
    public GPAccountProject getDefaultAccountProject(Long accountID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getDefaultAccountProject(accountID);
    }

    @Override
    public List<ProjectDTO> searchAccountProjects(Long accountID,
            PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.searchAccountProjects(accountID, request);
    }

    @Override
    public Boolean setProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.setProjectOwner(request, false);
    }

    @Override
    public void forceProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault {
        projectServiceDelegate.setProjectOwner(request, true);
    }

    @Override
    public GPAccountProject getProjectOwner(Long projectID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getProjectOwner(projectID);
    }

    @Override
    public GPProject getDefaultProject(Long accountID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getDefaultProject(accountID);
    }

    @Override
    public ProjectDTO getDefaultProjectDTO(Long accountID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getDefaultProjectDTO(accountID);
    }

    @Override
    public GPProject updateDefaultProject(Long accountID,
            Long projectID) throws ResourceNotFoundFault {
        return projectServiceDelegate.updateDefaultProject(accountID, projectID);
    }

    @Override
    public Boolean saveAccountProjectProperties(
            AccountProjectPropertiesDTO accountProjectProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.saveAccountProjectProperties(
                accountProjectProperties);
    }

    @Override
    public ShortAccountDTOContainer getAccountsByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountsBySharedProjectID(projectID);
    }

    @Override
    public ShortAccountDTOContainer getAccountsToShareByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getAccountsToShareByProjectID(projectID);
    }

    @Override
    public Boolean updateAccountsProjectSharing(
            PutAccountsProjectRequest apRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.updateAccountsProjectSharing(apRequest);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Project">
    // =========================================================================
    // === Project
    // =========================================================================
    @Override
    public Long saveProject(SaveProjectRequest saveProjectRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.saveProject(saveProjectRequest);
    }

    @Override
    public Long insertProject(GPProject project) throws IllegalParameterFault {
        return projectServiceDelegate.insertProject(project);
    }

    @Override
    public Long updateProject(GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return projectServiceDelegate.updateProject(project);
    }

    @Override
    public Boolean deleteProject(Long projectID) throws ResourceNotFoundFault {
        return projectServiceDelegate.deleteProject(projectID);
    }

    @Override
    public GPProject getProjectDetail(Long projectID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getProjectDetail(projectID);
    }

    @Override
    public Integer getNumberOfElementsProject(Long projectID) throws
            ResourceNotFoundFault {
        return projectServiceDelegate.getNumberOfElementsProject(projectID);
    }

    @Override
    public void setProjectShared(Long projectID) throws ResourceNotFoundFault {
        projectServiceDelegate.setProjectShared(projectID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Viewport">
    // =========================================================================
    // === Viewport
    // =========================================================================
    @Override
    public GPViewport getDefaultViewport(Long accountProjectID)
            throws ResourceNotFoundFault {
        return viewportServiceDelegate.getDefaultViewport(accountProjectID);
    }

    @Override
    public ArrayList<GPViewport> getAccountProjectViewports(
            Long accountProjectID)
            throws ResourceNotFoundFault {
        return viewportServiceDelegate.getAccountProjectViewports(
                accountProjectID);
    }

    @Override
    public Long insertViewport(InsertViewportRequest insertViewportReq)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return viewportServiceDelegate.insertViewport(insertViewportReq);
    }

    @Override
    public void replaceViewportList(ManageViewportRequest request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        viewportServiceDelegate.replaceViewportList(request);
    }

    @Override
    public void saveOrUpdateViewportList(ManageViewportRequest request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        viewportServiceDelegate.saveOrUpdateViewportList(request);
    }

    @Override
    public Long updateViewport(GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return viewportServiceDelegate.updateViewport(viewport);
    }

    @Override
    public Boolean deleteViewport(Long viewportID) throws ResourceNotFoundFault {
        return viewportServiceDelegate.deleteViewport(viewportID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // =========================================================================
    // === Folder
    // =========================================================================
    @Override
    public Long insertFolder(InsertFolderRequest insertFolderRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.insertFolder(insertFolderRequest);
    }

    @Override
    public Long updateFolder(GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.updateFolder(folder);
    }

    @Override
    public Boolean deleteFolder(Long folderID) throws ResourceNotFoundFault {
        return folderServiceDelegate.deleteFolder(folderID);
    }

    @Override
    public Long saveFolderProperties(Long folderID,
            String name,
            boolean checked,
            boolean expanded)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveFolderProperties(folderID, name,
                checked, expanded);
    }

    @Override
    public Long saveAddedFolderAndTreeModifications(
            WSAddFolderAndTreeModificationsRequest sftModificationRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return folderServiceDelegate.saveAddedFolderAndTreeModifications(
                sftModificationRequest);
    }

    @Override
    public Boolean saveDeletedFolderAndTreeModifications(
            WSDeleteFolderAndTreeModifications sdfModificationRequest)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDeletedFolderAndTreeModifications(
                sdfModificationRequest);
    }

    @Override
    public boolean saveCheckStatusFolderAndTreeModifications(Long folderID,
            boolean checked)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveCheckStatusFolderAndTreeModifications(
                folderID, checked);
    }

    @Override
    public Boolean saveDragAndDropFolderAndTreeModifications(
            WSDDFolderAndTreeModifications sddfTreeModificationRequest)
            throws ResourceNotFoundFault {
        return folderServiceDelegate.saveDragAndDropFolderModifications(
                sddfTreeModificationRequest);
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
    public TreeFolderElementsStore getChildrenElements(Long folderID) {
        return folderServiceDelegate.getChildrenElements(folderID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / Project">
    @Override
    public ProjectDTO getProjectWithRootFolders(Long projectID,
            Long accountID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getProjectWithRootFolders(projectID,
                accountID);
    }

    @Override
    public ProjectDTO getProjectWithExpandedFolders(Long projectID,
            Long accountID)
            throws ResourceNotFoundFault {
        return projectServiceDelegate.getProjectWithExpandedFolders(projectID,
                accountID);
    }

    @Override
    public ProjectDTO exportProject(Long projectID) throws ResourceNotFoundFault {
        return projectServiceDelegate.exportProject(projectID);
    }

    @Override
    public Long importProject(ImportProjectRequest impRequest)
            throws IllegalParameterFault, ResourceNotFoundFault {
        return projectServiceDelegate.importProject(impRequest);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Layer (Raster and Vector)">
    // =========================================================================
    // === Layer
    // =========================================================================
    @Override
    public Long insertLayer(InsertLayerRequest layerRequest) throws
            IllegalParameterFault {
        return layerServiceDelegate.insertLayer(layerRequest);
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
    public Boolean deleteLayer(Long layerID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.deleteLayer(layerID);
    }

    @Override
    public Long saveAddedLayerAndTreeModifications(
            WSAddLayerAndTreeModificationsRequest addLayerRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayerAndTreeModifications(
                addLayerRequest);
    }

    @Override
    public LongListStore saveAddedLayersAndTreeModifications(
            WSAddLayersAndTreeModificationsRequest addLayersRequest)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveAddedLayersAndTreeModifications(
                addLayersRequest);
    }

    @Override
    public Boolean saveDeletedLayerAndTreeModifications(
            WSDeleteLayerAndTreeModificationsRequest deleteLayerRequest)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.saveDeletedLayerAndTreeModifications(
                deleteLayerRequest);
    }

    @Override
    public Boolean saveCheckStatusLayerAndTreeModifications(Long layerID,
            boolean checked) throws ResourceNotFoundFault {
        return layerServiceDelegate.saveCheckStatusLayerAndTreeModifications(
                layerID, checked);
    }

    @Override
    public boolean fixCheckStatusLayerAndTreeModifications(Long layerID,
            Long oldFolderID,
            Long newFolderID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.fixCheckStatusLayerAndTreeModifications(
                layerID, oldFolderID, newFolderID);
    }

    @Override
    public Boolean saveDragAndDropLayerAndTreeModifications(
            WSDDLayerAndTreeModificationsRequest ddLayerReq)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveDragAndDropLayerModifications(
                ddLayerReq);
    }

    @Override
    public Boolean saveLayerProperties(RasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return layerServiceDelegate.saveLayerProperties(layerProperties);
    }

    @Override
    public GPLayer getLayerDetail(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerDetail(layerID);
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
    public GPBBox getBBox(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getBBox(layerID);
    }

    @Override
    public GPLayerInfo getLayerInfo(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerInfo(layerID);
    }
//
//    @Override
//    public List<StyleDTO> getLayerStyles(Long layerID) {
//        return layerServiceDelegate.getLayerStyles(layerID);
//    }    
//    @Override
//    public List<StyleDTO> getLayerStyles(Long layerID) {
//        return layerServiceDelegate.getLayerStyles(layerID);
//    }    
//
//    @Override
//    public GeometryDTO getGeometry(Long layerID) throws ResourceNotFoundFault {
//        return layerServiceDelegate.getGeometry(layerID);
//    }

    @Override
    public ShortLayerDTO getShortLayer(Long layerID) throws
            ResourceNotFoundFault {
        return layerServiceDelegate.getShortLayer(layerID);
    }

    @Override
    public GPLayerType getLayerType(Long layerID) throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayerType(layerID);
    }

    @Override
    public GetDataSourceResponse getLayersDataSourceByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        return layerServiceDelegate.getLayersDataSourceByProjectID(projectID);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ACL">
    // =========================================================================
    // === ACL
    // =========================================================================    
    @Override
    public List<String> getAllRoles(String organization) throws
            ResourceNotFoundFault {
        return aclServiceDelegate.getAllRoles(organization);
    }

    @Override
    public List<String> getAllGuiComponentIDs() {
        return aclServiceDelegate.getAllGuiComponentIDs();
    }

    @Override
    public GuiComponentsPermissionMapData getApplicationPermission(String appID)
            throws ResourceNotFoundFault {
        return this.aclServiceDelegate.getApplicationPermission(appID);
    }

    @Override
    public GuiComponentsPermissionMapData getAccountPermission(Long accountID)
            throws ResourceNotFoundFault {
        return this.aclServiceDelegate.getAccountPermission(accountID);
    }

    @Override
    public GuiComponentsPermissionMapData getRolePermission(String role,
            String organization)
            throws ResourceNotFoundFault {
        return aclServiceDelegate.getRolePermission(role, organization);
    }

    @Override
    public Boolean updateRolePermission(
            WSPutRolePermissionRequest putRolePermissionReq)
            throws ResourceNotFoundFault {
        return aclServiceDelegate.updateRolePermission(putRolePermissionReq);
    }

    @Override
    public Boolean saveRole(WSSaveRoleRequest saveRoleReq) throws
            IllegalParameterFault {
        return aclServiceDelegate.saveRole(saveRoleReq);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Server">
    // =========================================================================
    // === Server
    // =========================================================================
    @Override
    public Long insertServer(GeoPlatformServer server) {
        return serverServiceDelegate.insertServer(server);
    }

    @Override
    public Long updateServer(GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault {
        return serverServiceDelegate.updateServer(server);
    }

    @Override
    public Boolean deleteServer(Long idServer) throws ResourceNotFoundFault {
        return serverServiceDelegate.deleteServer(idServer);
    }

    @Override
    public GeoPlatformServer getServerDetail(Long idServer)
            throws ResourceNotFoundFault {
        return serverServiceDelegate.getServerDetail(idServer);
    }

    @Override
    public ServerDTO getShortServer(String serverUrl) throws
            ResourceNotFoundFault {
        return serverServiceDelegate.getShortServer(serverUrl);
    }

    @Override
    public List<ServerDTO> getAllServers(String organizationName) throws
            ResourceNotFoundFault {
        return serverServiceDelegate.getServers(organizationName);
    }

    @Override
    public GeoPlatformServer getServerDetailByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        return serverServiceDelegate.getServerDetailByUrl(serverUrl);
    }

    @Override
    public ServerDTO saveServer(WSSaveServerRequest saveServerReq)
            throws IllegalParameterFault {
        return serverServiceDelegate.saveServer(saveServerReq);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Message">
    // =========================================================================
    // === Message
    // =========================================================================
    @Override
    public Long insertMessage(GPMessage message) throws ResourceNotFoundFault,
            IllegalParameterFault {
        return messageServiceDelegate.insertMessage(message);
    }

    @Override
    public Boolean insertMultiMessage(MessageDTO messageDTO) throws
            ResourceNotFoundFault {
        return messageServiceDelegate.insertMultiMessage(messageDTO);
    }

    @Override
    public Boolean deleteMessage(Long messageID) throws ResourceNotFoundFault {
        return messageServiceDelegate.deleteMessage(messageID);
    }

    @Override
    public GPMessage getMessageDetail(Long messageID) throws
            ResourceNotFoundFault {
        return messageServiceDelegate.getMessageDetail(messageID);
    }

    @Override
    public List<GPMessage> getAllMessagesByRecipient(Long recipientID) throws
            ResourceNotFoundFault {
        return messageServiceDelegate.getAllMessagesByRecipient(recipientID);
    }

    @Override
    public List<GPMessage> getUnreadMessagesByRecipient(Long recipientID) throws
            ResourceNotFoundFault {
        return messageServiceDelegate.getUnreadMessagesByRecipient(recipientID);
    }

    @Override
    public Boolean markMessageAsRead(Long recipientID) throws
            ResourceNotFoundFault {
        return messageServiceDelegate.markMessageAsRead(recipientID);
    }

    @Override
    public Boolean markAllMessagesAsReadByRecipient(Long recipientID) throws
            ResourceNotFoundFault {
        return messageServiceDelegate.markAllMessagesAsReadByRecipient(
                recipientID);
    }

    @Override
    public Boolean markMessagesAsReadByDate(
            MarkMessageReadByDateRequest markMessageAsReadByDateReq)
            throws ResourceNotFoundFault {
        return messageServiceDelegate.markMessagesAsReadByDate(
                markMessageAsReadByDateReq);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Access Info">
    // =========================================================================
    // === Access Info
    // =========================================================================
    @Override
    public Long insertGSAccount(GSAccount gsAccount) {
        this.gsAccountDAO.persist(gsAccount);
        return gsAccount.getId();
    }

    @Override
    public Long insertGSResource(GSResource gsResource) {
        this.gsResourceDAO.persist(gsResource);
        return gsResource.getId();
    }

    @Override
    public GSResource getGSResourceByLayerNameAndGsUser(String layerName,
            String gsUser) {
        return this.gsResourceDAO.findByLayerNameAndGsUser(layerName, gsUser);
    }

    @Override
    public GSResource getGSResourceByWorkspaceAndGsUser(String workspace,
            String gsUser) {
        return this.gsResourceDAO.findByWorkspaceAndGsUser(workspace, gsUser);
    }

    @Override
    public String getGSUserByAuthkey(String authkey) {
        return this.gsAccountDAO.findGSUserNameByAuthkey(authkey).getGsuser();
    }
    // </editor-fold>
}
