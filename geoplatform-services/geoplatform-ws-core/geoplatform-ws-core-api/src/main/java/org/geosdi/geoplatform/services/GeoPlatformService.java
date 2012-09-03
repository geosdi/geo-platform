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

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.core.model.GSAccount;
import org.geosdi.geoplatform.core.model.GSResource;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.ApplicationDTO;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.RasterPropertiesDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.StyleDTO;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 * Public interface to define the service operations mapped via REST using CXT
 * framework.
 *
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@WebService(name = "GeoPlatformService",
            targetNamespace = "http://services.geo-platform.org/")
public interface GeoPlatformService {

    // <editor-fold defaultstate="collapsed" desc="Organization">
    // ==========================================================================
    // === Organization
    // ==========================================================================
    @Put
    @HttpResource(location = "/organization")
    @Deprecated
    Long insertOrganization(@WebParam(name = "organization") GPOrganization organization)
            throws IllegalParameterFault;

    @Delete
    @HttpResource(location = "/organization/{organizationID}")
    @Deprecated
    boolean deleteOrganization(@WebParam(name = "organizationID") Long organizationID)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Account (User and Application)">
    // ==========================================================================
    // === Account
    // ==========================================================================

    /**
     * Insert an Account (User or Application).
     *
     * @param account the Account to insert
     * @param sendEmail the flag for send to new User a registration email (not
     * for Applications)
     * @return the Account ID
     * @throws IllegalParameterFault if the account not have an Organization or
     * there is a duplicate Account
     */
    @Put
    @HttpResource(location = "/accounts")
    Long insertAccount(@WebParam(name = "account") GPAccount account,
            @WebParam(name = "sendEmail") boolean sendEmail)
            throws IllegalParameterFault;

    /**
     * Update a User and his Authorities.
     *
     * @param user the User to update
     * @return the User ID
     * @throws ResourceNotFoundFault if User not found
     * @throws IllegalParameterFault if User ID is null or update a standard
     * User to a temporary User
     */
    @Post
    @HttpResource(location = "/users")
    Long updateUser(@WebParam(name = "User") GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update a User, also his password. If password or email is changed will be
     * sent an email for modification.
     *
     * @param user the User to update
     * @param currentPlainPassword the plaintext password of the User
     * @param newPlainPassword the new plaintext password of the User
     * @return the User ID
     * @throws ResourceNotFoundFault if User not found
     * @throws IllegalParameterFault if User ID is null or the
     * currentPlainPassword is wrong
     */
    @Post
    @HttpResource(location = "/users")
    Long updateOwnUser(
            @WebParam(name = "User") UserDTO user,
            @WebParam(name = "currentPlainPassword") String currentPlainPassword,
            @WebParam(name = "newPlainPassword") String newPlainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update an Application and his Authorities.
     *
     * @param application the Application to update
     * @return the Application ID
     * @throws ResourceNotFoundFault if Application not found
     * @throws IllegalParameterFault if Application ID is null or update a
     * standard Application to a temporary Application
     */
    @Post
    @HttpResource(location = "/applications")
    Long updateApplication(
            @WebParam(name = "application") GPApplication application)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete an Account by ID. Delete his Authorities, the owner Project and
     * the reference to shared Project.
     *
     * @param accountID the Account ID
     * @return the result boolean operations
     * @throws ResourceNotFoundFault if Account not found
     */
    @Delete
    @HttpResource(location = "/accounts/{accountID}")
    boolean deleteAccount(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by ID.
     *
     * @param userID the User ID
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @HttpResource(location = "/users/{userID}")
    @WebResult(name = "User")
    GPUser getUserDetail(@WebParam(name = "userID") Long userID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by username.
     *
     * @param request the request that wrap the username
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @WebResult(name = "User")
    GPUser getUserDetailByUsername(SearchRequest request)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by username (or email) and password to authenticate it.
     *
     * @param username the username or the email of the User
     * @param plainPassword the plaintext password of the User
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found or not have Authorities
     * @throws IllegalParameterFault if the password is wrong
     * @throws AccountLoginFault if User is disabled or expired
     */
    @Get
    @WebResult(name = "User")
    GPUser getUserDetailByUsernameAndPassword(
            @WebParam(name = "username") String username,
            @WebParam(name = "plainPassword") String plainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault, AccountLoginFault;

    /**
     * Retrieve an Application by ID.
     *
     * @param applicationID the Application ID
     * @return the Application to retrieve
     * @throws ResourceNotFoundFault if Application not found
     */
    @Get
    @HttpResource(location = "/applications/{applicationID}")
    @WebResult(name = "Application")
    GPApplication getApplicationDetail(
            @WebParam(name = "applicationID") Long applicationID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve an Application by string ID.
     *
     * @param appID the Application string ID
     * @return the Application to retrieve
     * @throws ResourceNotFoundFault if Application not found
     * @throws AccountLoginFault if Application is disabled or expired
     */
    @Get
    @WebResult(name = "Application")
    GPApplication getApplication(@WebParam(name = "appID") String appID)
            throws ResourceNotFoundFault, AccountLoginFault;

    /**
     * Retrieve a User by ID.
     *
     * @param userID the User ID
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @HttpResource(location = "/users/{userID}")
    @WebResult(name = "User")
    UserDTO getShortUser(@WebParam(name = "userID") Long userID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by username.
     *
     * @param the request that wrap the username
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @WebResult(name = "User")
    UserDTO getShortUserByUsername(SearchRequest request)
            throws ResourceNotFoundFault;

    /**
     * Retrieve an Application by ID.
     *
     * @param applicationID the Application ID
     * @return the Application to retrieve
     * @throws ResourceNotFoundFault if Application not found
     */
    @Get
    @HttpResource(location = "/applications/{applicationID}")
    @WebResult(name = "Application")
    ApplicationDTO getShortApplication(
            @WebParam(name = "applicationID") Long applicationID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve an Application by string ID.
     *
     * @param request the request that wrap the string ID
     * @return the Application to retrieve
     * @throws ResourceNotFoundFault if Application not found
     */
    @Get
    @WebResult(name = "Application")
    ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault;

    /**
     * Search Users and their Authorities of the Organization of the User
     * retrieved by userID. The Users are searched by name like of username.
     *
     * @param userID the User ID that will exclude from the search (logged user)
     * @param request the request that wrap the search parameters
     * @return the paginate list of Users found
     * @throws ResourceNotFoundFault if User not found or a searched User not
     * have Authorities
     */
    @Get
    @HttpResource(location = "/users/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Users")
    List<UserDTO> searchUsers(@WebParam(name = "userID") Long userID,
            PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * Retrieve all Accounts.
     *
     * @return the list of Accounts
     */
    @Get
    @HttpResource(location = "/accounts")
    @WebResult(name = "Accounts")
    List<ShortAccountDTO> getAllAccounts();

    /**
     * Retrieve all Accounts within an Organization.
     *
     * @param organization the Organization name
     * @return the list of Accounts
     * @throws ResourceNotFoundFault if Organization not found
     */
    @Get
    @HttpResource(location = "/accounts/{}")
    @WebResult(name = "Accounts")
    List<ShortAccountDTO> getAccounts(
            @WebParam(name = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of Accounts found by search request.
     *
     * @param request the request that wrap the username (for User) or the
     * string ID (for Application)
     * @return the number of Accounts found
     */
    @Get
    @HttpResource(location = "/accounts/count/{nameLike}")
    @WebResult(name = "count")
    Long getAccountsCount(SearchRequest request);

    /**
     * Retrieve the number of Users of an Organization found by search request.
     *
     * @param organization the Organization name
     * @param request the request that wrap the username
     * @return the number of Users found
     */
    @Get
    @HttpResource(location = "/users/count/{nameLike}")
    @WebResult(name = "count")
    Long getUsersCount(@WebParam(name = "organization") String organization,
            SearchRequest request);

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param accountID the Account ID
     * @return the list of string Authorities
     * @throws ResourceNotFoundFault if Account not found or Account not have
     * Authorities
     */
    @Get
    @HttpResource(location = "/accounts/{id}/authorities")
    @WebResult(name = "Authorities")
    List<String> getAuthorities(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param stringID the username (for User) or the string ID (for
     * Application)
     * @return the list of Authorities
     * @throws ResourceNotFoundFault if Account not found or Account not have
     * Authorities
     */
    @HttpResource(location = "/accounts/{id}/authorities")
    @WebResult(name = "Authorities")
    List<GPAuthority> getAuthoritiesDetail(
            @WebParam(name = "stringID") String stringID)
            throws ResourceNotFoundFault;

    /**
     * Set an Account as temporary.
     *
     * @param accountID the Account ID
     * @throws ResourceNotFoundFault if Account not found
     */
    @Post
    @HttpResource(location = "/accounts/{accountID}")
    void forceTemporaryAccount(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Set a temporary Account as expired.
     *
     * @param accountID the Account ID
     * @throws ResourceNotFoundFault if Account not found
     * @throws IllegalParameterFault if Account is not temporary
     */
    @Post
    @HttpResource(location = "/accounts/{accountID}")
    void forceExpiredTemporaryAccount(
            @WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault, IllegalParameterFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="AccountProject">
    // ==========================================================================
    // === AccountProject
    // ==========================================================================
    @Put
    @HttpResource(location = "/accountProject")
    Long insertAccountProject(
            @WebParam(name = "accountProject") GPAccountProject accountProject)
            throws IllegalParameterFault;

    @Post
    @HttpResource(location = "/accountProject")
    Long updateAccountProject(
            @WebParam(name = "accountProject") GPAccountProject accountProject)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/accountProject/{accountProjectID}")
    boolean deleteAccountProject(
            @WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/accountProject/{accountProjectID}")
    @WebResult(name = "AccountProject")
    GPAccountProject getAccountProject(
            @WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/account/{accountID}")
    @WebResult(name = "AccountProject")
    List<GPAccountProject> getAccountProjectsByAccountID(
            @WebParam(name = "accountID") Long accountID);

    @Get
    @HttpResource(location = "/projects/{projectID}")
    @WebResult(name = "AccountProject")
    List<GPAccountProject> getAccountProjectsByProjectID(
            @WebParam(name = "projectID") Long projectID);

    @Get
    @HttpResource(location = "/account/{accountID}/projects/{projectID}")
    @WebResult(name = "AccountProject")
    GPAccountProject getAccountProjectByAccountAndProjectIDs(
            @WebParam(name = "accountID") Long accountID,
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/account/{accountID}")
    @WebResult(name = "AccountProject")
    Long getAccountProjectsCount(@WebParam(name = "accountID") Long accountID,
            SearchRequest request) throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/accounts/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Projects")
    List<ProjectDTO> searchAccountProjects(
            @WebParam(name = "accountID") Long accountID,
            PaginatedSearchRequest request) throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/account/{accountID}")
    @WebResult(name = "DefaultProject")
    GPProject getDefaultProject(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/account/defaultProject")
    void updateDefaultProject(@WebParam(name = "accountID") Long accountID,
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/account/project/{projectID}")
    boolean saveAccountProjectProperties(
            @WebParam(name = "accountProjectProperties") AccountProjectPropertiesDTO accountProjectProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Retrieve Users of a shared Project, except the administration of the
     * Project.
     *
     * @param sharedProjectID the shared Project ID
     * @return the list of Users to which the Project is shared
     * @throws ResourceNotFoundFault if Project not found
     * @throws IllegalParameterFault if Project is not shared
     */
    @Get
    @WebResult(name = "DefaultProject")
    List<ShortAccountDTO> getAccountsBySharedProjectID(
            @WebParam(name = "sharedProjectID") Long sharedProjectID)
            throws ResourceNotFoundFault, IllegalParameterFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================

    @Put
    @HttpResource(location = "/project")
    Long saveProject(@WebParam(name = "stringID") String stringID,
            @WebParam(name = "project") GPProject project,
            @WebParam(name = "defaultProject") boolean defaultProject)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/project")
    @Deprecated
    Long insertProject(@WebParam(name = "project") GPProject project)
            throws IllegalParameterFault;

    @Post
    @HttpResource(location = "/project")
    Long updateProject(@WebParam(name = "project") GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/projects/{projectID}")
    boolean deleteProject(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/projects/{projectID}")
    @WebResult(name = "Project")
    GPProject getProjectDetail(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/projects/{projectID}")
    @WebResult(name = "Project")
    int getNumberOfElementsProject(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/project/{projectID}/shared")
    void setProjectShared(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/project/{projectID}/owner/{accountID}")
    boolean setProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/project/{projectID}/forceowner/{accountID}")
    void forceProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/project/{projectID}")
    @WebResult(name = "TreeElements")
    ProjectDTO getExpandedElementsByProjectID(
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Viewport">
    // ==========================================================================
    // === Viewport
    // ==========================================================================

    @Get
    @HttpResource(location = "/viewport/{{accountProjectID}}")
    @WebResult(name = "Viewport")
    GPViewport getDefaultViewport(@WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/viewports/{{accountProjectID}}")
    @WebResult(name = "Viewport")
    ArrayList<GPViewport> getAccountProjectViewports(@WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/viewport")
    Long insertViewport(@WebParam(name = "accountProjectID") Long accountProjectID,
            @WebParam(name = "viewport") GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/viewport")
    void replaceViewportList(@WebParam(name = "accountProjectID") Long accountProjectID,
            @WebParam(name = "viewportList") ArrayList<GPViewport> viewportList)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/viewport")
    void saveOrUpdateViewportList(@WebParam(name = "accountProjectID") Long accountProjectID,
            @WebParam(name = "viewportList") ArrayList<GPViewport> viewportList)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/viewport")
    Long updateViewport(@WebParam(name = "viewport") GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/viewport/{viewportID}")
    boolean deleteViewport(@WebParam(name = "viewportID") Long viewportID)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================

    @Put
    @HttpResource(location = "/folder")
    @Deprecated
    Long insertFolder(@WebParam(name = "projectID") Long projectID,
            @WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/folder")
    @Deprecated
    Long updateFolder(@WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/folders/{folderID}")
    @Deprecated
    boolean deleteFolder(@WebParam(name = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/folder/{folderID}")
    Long saveFolderProperties(@WebParam(name = "folderID") Long folderID,
            @WebParam(name = "folderName") String folderName,
            @WebParam(name = "checked") boolean checked,
            @WebParam(name = "expanded") boolean expanded)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    // @HttpResource(location = "/folder/{descendantsMap}")
    Long saveAddedFolderAndTreeModifications(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "parentID") Long parentID,
            @WebParam(name = "folder") GPFolder folder,
            @WebParam(name = "descendantsMap") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    // @HttpResource(location = "/folder/{id}/{descendantsMap}")
    boolean saveDeletedFolderAndTreeModifications(
            @WebParam(name = "folderID") Long folderID,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/folder/{folderID}")
    boolean saveCheckStatusFolderAndTreeModifications(
            @WebParam(name = "folderID") Long folderID,
            @WebParam(name = "checked") boolean checked)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{idElementMoved}")
    boolean saveDragAndDropFolderAndTreeModifications(
            @WebParam(name = "idElementMoved") Long idElementMoved,
            @WebParam(name = "idNewParent") Long idNewParent,
            @WebParam(name = "newPosition") int newPosition,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/folders/{folderID}")
    @WebResult(name = "Folder")
    FolderDTO getShortFolder(@WebParam(name = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/folders/{folderID}")
    @WebResult(name = "Folder")
    GPFolder getFolderDetail(@WebParam(name = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/folders/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Folder")
    List<FolderDTO> searchFolders(PaginatedSearchRequest searchRequest);

    @Get
    @HttpResource(location = "/folders")
    @WebResult(name = "Folder")
    List<FolderDTO> getFolders();

    @Get
    @HttpResource(location = "/folders/count/{nameLike}")
    @WebResult(name = "count")
    long getFoldersCount(SearchRequest searchRequest);

    /**
     * @param folderID
     * @param num
     * @param page
     * @return Children folders.
     */
    @Get
    @HttpResource(location = "/folders/account/{id}/{num}/{page}")
    @WebResult(name = "Folder")
    List<FolderDTO> getChildrenFoldersByRequest(RequestByID request);

    /**
     * @param folderID
     * @return Children folders.
     */
    @Get
    @HttpResource(location = "/folders/account/{folderID}")
    @WebResult(name = "Folder")
    List<FolderDTO> getChildrenFolders(
            @WebParam(name = "folderID") Long folderID);

    /**
     * @param folderID
     * @return Children elements (folder and layer).
     */
    @Get
    @HttpResource(location = "/folders/{folderID}")
    @WebResult(name = "ChildrenElement")
    TreeFolderElements getChildrenElements(
            @WebParam(name = "folderID") Long folderID);

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Folder / Project">
    @Get
    // @HttpResource(location = "/projects/{projectID}")
    @WebResult(name = "RootFolders")
    List<FolderDTO> getRootFoldersByProjectID(
            @WebParam(name = "projectID") Long projectID);

    @Get
    // @HttpResource(location = "/projects/{projectID}")
    @WebResult(name = "Project")
    ProjectDTO exportProject(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/project")
    Long importProject(@WebParam(name = "projectDTO") ProjectDTO projectDTO,
            @WebParam(name = "accountID") Long accountID)
            throws IllegalParameterFault, ResourceNotFoundFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Layer (Raster and Vector)">
    // ==========================================================================
    // === Layer
    // ==========================================================================
    @Put
    @HttpResource(location = "/layer")
    Long insertLayer(@WebParam(name = "layer") GPLayer layer)
            throws IllegalParameterFault;

    @Post
    @HttpResource(location = "/layer")
    Long updateRasterLayer(@WebParam(name = "layer") GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/layer")
    Long updateVectorLayer(@WebParam(name = "layer") GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/layers/{layerID}")
    boolean deleteLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{descendantsMap}")
    Long saveAddedLayerAndTreeModifications(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "parentFolderID") Long parentFolderID,
            @WebParam(name = "layer") GPLayer layer,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/layers/{descendantsMap}")
    ArrayList<Long> saveAddedLayersAndTreeModifications(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "parentFolderID") Long parentFolderID,
            @WebParam(name = "layers") List<GPLayer> layers,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/layers/{id}/{descendantsMap}")
    boolean saveDeletedLayerAndTreeModifications(
            @WebParam(name = "layerID") Long layerID,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{layerID}")
    boolean saveCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerID") Long layerID,
            @WebParam(name = "checked") boolean checked)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{layerID}")
    boolean fixCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerID") Long layerID,
            @WebParam(name = "oldFolderID") Long oldFolderID,
            @WebParam(name = "newFolderID") Long newFolderID)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{idElementMoved}")
    boolean saveDragAndDropLayerAndTreeModifications(
            @WebParam(name = "idElementMoved") Long idElementMoved,
            @WebParam(name = "idNewParent") Long idNewParent,
            @WebParam(name = "newPosition") int newPosition,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/layer")
    boolean saveLayerProperties(
            @WebParam(name = "layerProperties") RasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * @return a raster layer.
     */
    @Get
    @WebResult(name = "RasterLayer")
    GPRasterLayer getRasterLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * @return a vector layer.
     */
    @Get
    @WebResult(name = "VectorLayer")
    GPVectorLayer getVectorLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/layers")
    @WebResult(name = "Layer")
    List<ShortLayerDTO> getLayers(@WebParam(name = "projectID") Long projectID);

    /**
     * @return Styles of a layer.
     */
    @Get
    @WebResult(name = "LayerStyles")
    List<StyleDTO> getLayerStyles(@WebParam(name = "layerID") Long layerID);

    /**
     * @return a short layer.
     */
    @Get
    @WebResult(name = "ShortLayerDTO")
    ShortLayerDTO getShortLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * @return BBox of a layer.
     */
    @Get
    @WebResult(name = "BBox")
    GPBBox getBBox(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * @return LayerInfo of a raster layer.
     */
    @Get
    @WebResult(name = "LayerInfo")
    GPLayerInfo getLayerInfo(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    // /**
    // * @return Styles of a layer.
    // */
    // @Get
    // @WebResult(name = "LayerStyles")
    // List<StyleDTO> getLayerStyles(@WebParam(name = "layerID") Long layerID);
    //
    // /**
    // * @return Geometry of a vector layer.
    // */
    // @Get
    // @WebResult(name = "Geometry")
    // Point getGeometry(@WebParam(name = "LayerID") Long layerID) throws
    // ResourceNotFoundFault;
    //
    /**
     * @return layer Type.
     */
    @Get
    @WebResult(name = "LayerType")
    GPLayerType getLayerType(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * @return layer data source of given owner.
     */
    @Get
    @WebResult(name = "LayerDataSources")
    ArrayList<String> getLayersDataSourceByProjectID(
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="ACL">
    // ==========================================================================
    // === ACL
    // ==========================================================================
    /**
     * Retrieve all Roles wrt an organization.
     *
     * @param organization organization name
     *
     * @return List of all Roles
     */
    @Get
    @HttpResource(location = "/roles")
    @WebResult(name = "Roles")
    List<String> getAllRoles(@WebParam(name = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all GuiComponet IDs.
     *
     * @return List of all Roles
     */
    @Get
    @HttpResource(location = "/permissions/ids")
    @WebResult(name = "GuiComponentIDs")
    List<String> getAllGuiComponentIDs();

    /**
     * Retrieve GUI Component permissions for an Application. <p> It is based
     * only on application ID.
     *
     * @param appID application ID
     *
     * @return Map that contains GUI Components permissions, with: <ul> <li> key
     * = ID Component </li> <li> value = Permission </li> </ul>
     * @throws ResourceNotFoundFault if the application is not found
     */
    @Get
    @HttpResource(location = "/permissions/application/{appID}")
    @WebResult(name = "ApplicationGuiComponentsPermissionMapData")
    GuiComponentsPermissionMapData getApplicationPermission(
            @WebParam(name = "accountID") String appID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve GUI Component permissions for an Account. <p> It is based on
     * accounts with disjoined authorities.
     *
     * @param accountID account ID
     *
     * @return Map that contains GUI Components permissions, with: <ul> <li> key
     * = ID Component </li> <li> value = Permission </li> </ul>
     * @throws ResourceNotFoundFault if the account is not found
     */
    @Get
    @HttpResource(location = "/permissions/account/{accountID}")
    @WebResult(name = "GuiComponentsPermissionMapData")
    GuiComponentsPermissionMapData getAccountPermission(
            @WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the GUI Component permissions for a Role (Authority).
     *
     * @param role role (authority) name
     * @param organization organization name
     *
     * @return Map that contains GUI Components permissions, with: key = ID
     * Component value = Permission
     * @throws ResourceNotFoundFault if the role (authority) is not found
     */
    @Get
    @HttpResource(location = "/permissions/{role}")
    @WebResult(name = "GuiComponentsPermissionMapData")
    GuiComponentsPermissionMapData getRolePermission(
            @WebParam(name = "role") String role,
            @WebParam(name = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Update the permission of a role (authority).
     *
     * @param role role (authority) name
     * @param organization organization name
     * @param mapComponentPermission map of GuiComponents permissions to update
     *
     * @return if the update was successful
     * @throws ResourceNotFoundFault if the role (authority) is not found
     */
    @Post
    @HttpResource(location = "/permissions/{role}")
    boolean updateRolePermission(
            @WebParam(name = "role") String role,
            @WebParam(name = "organization") String organization,
            @WebParam(name = "permissionMapData") GuiComponentsPermissionMapData mapComponentPermission)
            throws ResourceNotFoundFault;

    /**
     * Save a new role (authority).
     *
     * @param role role (authority) name
     * @param organization organization name
     *
     * @return if the saving was successful
     * @throws IllegalParameterFault if the role (authority) already exist
     */
    @Post
    @HttpResource(location = "/permissions/{role}")
    boolean saveRole(
            @WebParam(name = "role") String role,
            @WebParam(name = "organization") String organization)
            throws IllegalParameterFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Server">
    // ==========================================================================
    // === Server
    // ==========================================================================
    @Put
    @HttpResource(location = "/server")
    Long insertServer(@WebParam(name = "Server") GeoPlatformServer server);

    @Post
    @HttpResource(location = "/server")
    Long updateServer(@WebParam(name = "Server") GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/server/{idServer}")
    boolean deleteServer(@WebParam(name = "idServer") Long idServer)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/servers")
    @WebResult(name = "Server")
    List<ServerDTO> getAllServers();

    @Get
    @HttpResource(location = "/server/{idServer}")
    @WebResult(name = "Server")
    GeoPlatformServer getServerDetail(@WebParam(name = "idServer") Long idServer)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/server/{serverUrl}")
    @WebResult(name = "Servers")
    ServerDTO getShortServer(@WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/server")
    @WebResult(name = "Server")
    GeoPlatformServer getServerDetailByUrl(
            @WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/server")
    ServerDTO saveServer(@WebParam(name = "id") Long id,
            @WebParam(name = "aliasServerName") String aliasServerName,
            @WebParam(name = "serverUrl") String serverUrl,
            @WebParam(name = "organization") String organization)
            throws IllegalParameterFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Access Info">
    // ==========================================================================
    // === Access Info
    // ==========================================================================
    @Put
    @HttpResource(location = "/permissions/GSAccount")
    Long insertGSAccount(@WebParam(name = "GSAccount") GSAccount gsAccount);

    @Put
    @HttpResource(location = "/permissions/GSResource")
    Long insertGSResource(@WebParam(name = "GSResource") GSResource gsResource);

    @Get
    @HttpResource(location = "/permissions/access/{gsUser}/{layerName}")
    @WebResult(name = "GPAccessInfo")
    GSResource getGSResourceByLayerNameAndGsUser(@WebParam(name = "layerName") String layerName, @WebParam(name = "gsUser") String gsUser);

    @Get
    @HttpResource(location = "/permissions/access/{gsUser}/{workspace}")
    @WebResult(name = "GPAccessInfo")
    GSResource getGSResourceByWorkspaceAndGsUser(@WebParam(name = "workspace") String workspace, @WebParam(name = "gsUser") String gsUser);

    @Get
    @HttpResource(location = "/permissions/access/{authkey}")
    @WebResult(name = "GSUser")
    String getGSUserByAuthkey(@WebParam(name = "authkey") String authkey);
    // </editor-fold>
}
