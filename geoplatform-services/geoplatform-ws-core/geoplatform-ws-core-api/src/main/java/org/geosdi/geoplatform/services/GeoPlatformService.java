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

import com.vividsolutions.jts.geom.Geometry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
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
import org.geosdi.geoplatform.core.model.GPMessage;
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
import org.geosdi.geoplatform.responce.MessageDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.RasterPropertiesDTO;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
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
    /**
     * Insert an Organization.
     *
     * @param organization the Organization to insert
     * @return the Organization ID
     * @throws IllegalParameterFault if Organization is not valid
     * @deprecated only for test purpose
     */
    @Put
    @Deprecated
    Long insertOrganization(@WebParam(name = "organization") GPOrganization organization)
            throws IllegalParameterFault;

    /**
     * Delete an Organization by ID.
     *
     * @param organizationID the Organization ID
     * @return true if the Organization was deleted
     * @throws ResourceNotFoundFault if Organization not found
     * @deprecated only for test purpose
     */
    @Delete
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
    Long updateApplication(
            @WebParam(name = "application") GPApplication application)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete an Account by ID. Delete his Authorities, the owner Project and
     * the reference to shared Project.
     *
     * @param accountID the Account ID
     * @return true if the Account was deleted
     * @throws ResourceNotFoundFault if Account not found
     */
    @Delete
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
    @WebResult(name = "user")
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
    @WebResult(name = "user")
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
    @WebResult(name = "user")
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
    @WebResult(name = "application")
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
    @WebResult(name = "application")
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
    @WebResult(name = "user")
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
    @WebResult(name = "user")
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
    @WebResult(name = "application")
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
    @WebResult(name = "application")
    ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault;

    /**
     * Search Users and their Authorities in paginated way. The Users are
     * searched by name like of username. Note: the Organization of the User is
     * retrieved by User ID.
     *
     * @param userID the User ID that will exclude from the search (logged user)
     * @param request the request that wrap the search parameters
     * @return the paginate list of Users found
     * @throws ResourceNotFoundFault if User not found or a searched User not
     * have Authorities
     */
    @Get
    @WebResult(name = "user")
    List<UserDTO> searchUsers(@WebParam(name = "userID") Long userID,
            PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * Retrieve all Accounts.
     *
     * @return the list of Accounts
     */
    @Get
    @WebResult(name = "account")
    List<ShortAccountDTO> getAllAccounts();

    /**
     * Retrieve all Accounts within an Organization.
     *
     * @param organization the Organization name
     * @return the list of Accounts
     * @throws ResourceNotFoundFault if Organization not found
     */
    @Get
    @WebResult(name = "account")
    List<ShortAccountDTO> getAccounts(
            @WebParam(name = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of Accounts founded by search request.
     *
     * @param request the request that wrap the username (for User) or the
     * string ID (for Application)
     * @return the number of Accounts found
     */
    @Get
    @WebResult(name = "count")
    Long getAccountsCount(SearchRequest request);

    /**
     * Retrieve the number of Users of an Organization founded by search
     * request.
     *
     * @param organization the Organization name
     * @param request the request that wrap the username
     * @return the number of Users found
     */
    @Get
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
    @WebResult(name = "authority")
    List<String> getAuthorities(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param accountNaturalID the username (for User) or the appID (for
     * Application)
     * @return the list of Authorities
     * @throws ResourceNotFoundFault if Account not found or Account not have
     * Authorities
     */
    @Get
    @WebResult(name = "authority")
    List<GPAuthority> getAuthoritiesDetail(
            @WebParam(name = "accountNaturalID") String accountNaturalID)
            throws ResourceNotFoundFault;

    /**
     * Set an Account as temporary.
     *
     * @param accountID the Account ID
     * @throws ResourceNotFoundFault if Account not found
     */
    @Post
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
    void forceExpiredTemporaryAccount(
            @WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault, IllegalParameterFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="AccountProject">
    // ==========================================================================
    // === AccountProject
    // ==========================================================================
    /**
     * Insert an AccountProject.
     *
     * @param accountProject the AccountProject to insert
     * @return the AccountProject ID
     * @throws IllegalParameterFault if AccountProject is not valid
     */
    @Put
    Long insertAccountProject(
            @WebParam(name = "accountProject") GPAccountProject accountProject)
            throws IllegalParameterFault;

    /**
     * Update an AccountProject, the Account and the Project attached.
     *
     * @param accountProject the AccountProject to update
     * @return the AccountProject ID
     * @throws ResourceNotFoundFault if AccountProject, Account or Project not
     * found
     * @throws IllegalParameterFault if AccountProject, Account or Project are
     * not valid
     */
    @Post
    Long updateAccountProject(
            @WebParam(name = "accountProject") GPAccountProject accountProject)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete an AccountProject by ID.
     *
     * @param accountProjectID the AccountProject to delete
     * @return true id the AccountProject was deleted
     * @throws ResourceNotFoundFault if AccountProject not found
     */
    @Delete
    boolean deleteAccountProject(
            @WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve an AccountProject by ID.
     *
     * @param accountProjectID the AccountProject ID
     * @return the AccountProject to retrieve
     * @throws ResourceNotFoundFault if AccountProject not found
     */
    @Get
    @WebResult(name = "accountProject")
    GPAccountProject getAccountProject(
            @WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the AccountProjects of an Account.
     *
     * @param accountID the Account ID
     * @return List of AccountProject
     */
    @Get
    @WebResult(name = "accountProject")
    List<GPAccountProject> getAccountProjectsByAccountID(
            @WebParam(name = "accountID") Long accountID);

    /**
     * Retrieve the AccountProjects for a Project.
     *
     * @param projectID the Project ID
     * @return List of AccountProject
     */
    @Get
    @WebResult(name = "accountProject")
    List<GPAccountProject> getAccountProjectsByProjectID(
            @WebParam(name = "projectID") Long projectID);

    /**
     * Retrieve an AccountProject of an Account for a Project.
     *
     * @param accountID the Account ID
     * @param projectID the Project ID
     * @return the AccountProject to retrieve
     * @throws ResourceNotFoundFault if AccountProject not found
     */
    @Get
    @WebResult(name = "accountProject")
    GPAccountProject getAccountProjectByAccountAndProjectIDs(
            @WebParam(name = "accountID") Long accountID,
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of AccountProjects of an Account, founded by search
     * request.
     *
     * @param accountID the Account ID
     * @param request the request that wrap the username (for User) or the
     * string ID (for Application)
     * @return the number of AccountProjects found
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    Long getAccountProjectsCount(@WebParam(name = "accountID") Long accountID,
            SearchRequest request) throws ResourceNotFoundFault;

    /**
     * Retrieve the default AccountProject of an Account, or null if the Account
     * don't have a default Project.
     *
     * @param accountID the Account ID
     * @return the AccountProject to retrieve or null
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @WebResult(name = "defaultAccountProject")
    GPAccountProject getDefaultAccountProject(
            @WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all the Account Projects in paginated way. There are two Project
     * type, first that the Account is the owner, and latter related at a shared
     * Project.
     * <p/>
     * Each Project result, if shared, contain the own Account owner.
     *
     * @param accountID the Account ID
     * @param request the request that wrap Project ID, Project name and Account
     * ID
     * @return the paginated list of Projects of the Account
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @WebResult(name = "project")
    List<ProjectDTO> searchAccountProjects(
            @WebParam(name = "accountID") Long accountID,
            PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * Retrieve the Account owner of a Project.
     *
     * @param projectID the Project ID
     * @return the Account owner
     * @throws ResourceNotFoundFault if Project not found
     */
    @Post
    @WebResult(name = "projectOwner")
    GPAccount getProjectOwner(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Set an Account owner for a Project.
     *
     * @param request the request that wrap Project ID and Account ID
     * @return true if the Account owner was changed
     * @throws ResourceNotFoundFault if Project or Account not found
     */
    @Post
    boolean setProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault;

    /**
     * Force an Account owner for a Project.
     *
     * @param request the request that wrap Project ID and Account ID
     * @return true if the Account owner was forced
     * @throws ResourceNotFoundFault if Project or Account not found
     */
    @Post
    void forceProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the default Project of an Account. If the Account don't have a
     * default Project, return null.
     *
     * @param accountID the Account ID
     * @return the default Project or null
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @WebResult(name = "defaultProject")
    GPProject getDefaultProject(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the default Project of an Account. The Project result, if
     * shared, contain the own Account owner. If the Account don't have a
     * default Project, return null.
     *
     * @param accountID the Account ID
     * @return the default Project or null
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @WebResult(name = "defaultProjectDTO")
    ProjectDTO getDefaultProjectDTO(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Update the default Project for an Account.
     *
     * @param accountID the Account ID
     * @param projectID the Project ID
     * @return true if the default Project was updated
     * @throws ResourceNotFoundFault if Account or Project not found
     */
    @Post
    GPProject updateDefaultProject(@WebParam(name = "accountID") Long accountID,
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Save the Project information and optionally set it as default for an
     * Account.
     *
     * @param accountProjectProperties request that wrap Project information and
     * Account ID
     * @return true if the properties was saved
     * @throws ResourceNotFoundFault if Project or Account not found
     * @throws IllegalParameterFault if Project name is invalid
     */
    @Post
    boolean saveAccountProjectProperties(
            @WebParam(name = "accountProjectProperties") AccountProjectPropertiesDTO accountProjectProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Retrieve all Accounts of a (shared) Project.
     *
     * @param projectID the Project ID
     * @return the Account owner and of the Accounts to which the Project is
     * shared
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "account")
    List<ShortAccountDTO> getAccountsByProjectID(
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all Accounts to which it is possible to share a Project.
     *
     * @param projectID the Project ID
     * @return the Accounts to which the Project is not shared
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "account")
    List<ShortAccountDTO> getAccountsToShareByProjectID(
            @WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Update all at once the relations of sharing for a Project. The Account
     * owner ID relation must be present, otherwise the Project will be unshare.
     *
     * @param projectID the Project ID
     * @param accountIDsProject the Account IDs which will be updated the
     * relation of sharing
     * @return true if the relations of sharing are updated
     * @throws ResourceNotFoundFault if Project not found or an Account not
     * found
     */
    @Post
    @WebResult(name = "account")
    boolean updateAccountsProjectSharing(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "accountIDsProject") List<Long> accountIDsProject)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================

    /**
     * Save a Project and its Account owner.
     *
     * @param accountNaturalID the string ID of the Account (username for User
     * and appID for Application) owner of the Project
     * @param project the Project to save
     * @param defaultProject flag for save the Project as default for the
     * Account
     * @return the Project ID
     * @throws ResourceNotFoundFault if the Account not found
     * @throws IllegalParameterFault if the Project is not valid
     */
    @Put
    Long saveProject(@WebParam(name = "accountNaturalID") String accountNaturalID,
            @WebParam(name = "project") GPProject project,
            @WebParam(name = "defaultProject") boolean defaultProject)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Save a Project.
     *
     * @param project the Project to save
     * @return the Project ID
     * @throws IllegalParameterFault if the Project is not valid
     * @deprecated only for test purpose
     * @see #saveProject(java.lang.String,
     * org.geosdi.geoplatform.core.model.GPProject, boolean)
     */
    @Put
    @Deprecated
    Long insertProject(@WebParam(name = "project") GPProject project)
            throws IllegalParameterFault;

    /**
     * Update a Project.
     *
     * @param project the Project to update
     * @return the Project ID
     * @throws ResourceNotFoundFault if the Project not found
     * @throws IllegalParameterFault if the Project is not valid
     */
    @Post
    Long updateProject(@WebParam(name = "project") GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Project.
     *
     * @param project the Project to delete
     * @return true if the Project was deleted
     * @throws ResourceNotFoundFault if the Project not found
     */
    @Delete
    boolean deleteProject(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Project.
     *
     * @param projectID the Project ID
     * @return the Project to retrieve
     * @throws ResourceNotFoundFault if the Project not found
     */
    @Get
    @WebResult(name = "project")
    GPProject getProjectDetail(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of elements of a Project.
     *
     * @param projectID the Project ID
     * @return the number of elements to retrieve
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "project")
    int getNumberOfElementsProject(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Set a Project as shared.
     *
     * @param projectID the Project ID
     * @throws ResourceNotFoundFault if Project not found
     */
    @Post
    void setProjectShared(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Viewport">
    // ==========================================================================
    // === Viewport
    // ==========================================================================
    @Get
    @WebResult(name = "viewport")
    GPViewport getDefaultViewport(
            @WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "viewport")
    ArrayList<GPViewport> getAccountProjectViewports(
            @WebParam(name = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Put
    Long insertViewport(@WebParam(name = "accountProjectID") Long accountProjectID,
            @WebParam(name = "viewport") GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    void replaceViewportList(@WebParam(name = "accountProjectID") Long accountProjectID,
            @WebParam(name = "viewportList") ArrayList<GPViewport> viewportList)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    void saveOrUpdateViewportList(@WebParam(name = "accountProjectID") Long accountProjectID,
            @WebParam(name = "viewportList") ArrayList<GPViewport> viewportList)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    Long updateViewport(@WebParam(name = "viewport") GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    boolean deleteViewport(@WebParam(name = "viewportID") Long viewportID)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================

    /**
     * Insert a Folder into a Project.
     *
     * @param projectID the Project ID
     * @param folder the Folder to insert
     * @return the Folder ID
     * @throws ResourceNotFoundFault if Project not found
     * @throws IllegalParameterFault if Folder is not valid
     * @deprecated only for test purpose
     * @see #saveAddedFolderAndTreeModifications(java.lang.Long, java.lang.Long,
     * org.geosdi.geoplatform.core.model.GPFolder,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Put
    @Deprecated
    Long insertFolder(@WebParam(name = "projectID") Long projectID,
            @WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update a Folder.
     *
     * @param folder the Folder to update
     * @return the Folder ID
     * @throws ResourceNotFoundFault if Folder not found
     * @throws IllegalParameterFault if Folder is not valid
     * @deprecated only for test purpose
     * @see #saveFolderProperties(java.lang.Long, java.lang.String, boolean,
     * boolean)
     */
    @Post
    @Deprecated
    Long updateFolder(@WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Folder by ID.
     *
     * @param folderID the Folder ID
     * @return true if the Folder was deleted
     * @throws ResourceNotFoundFault if Folder not found
     * @deprecated only for test purpose
     * @see #saveDeletedFolderAndTreeModifications(java.lang.Long,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Delete
    @Deprecated
    boolean deleteFolder(@WebParam(name = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    /**
     * Update properties (name, checked and expanded) of a Folder.
     *
     * @param folderID the Folder ID
     * @param folderName the Folder name
     * @param checked the Folder checked
     * @param expanded the Folder expanded
     * @return the Folder ID
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    @Post
    Long saveFolderProperties(@WebParam(name = "folderID") Long folderID,
            @WebParam(name = "folderName") String folderName,
            @WebParam(name = "checked") boolean checked,
            @WebParam(name = "expanded") boolean expanded)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Insert a Folder, moreover manage Folder ancestors and positions on tree.
     *
     * @param projectID the Project ID
     * @param parentID the Folder parent ID
     * @param folder the Folder to insert
     * @param descendantsMapData the map of descendants (key = ancestor Folder
     * ID, value = number of its descendants)
     * @return the Folder ID
     * @throws ResourceNotFoundFault if Project or parent Folder not found
     * @throws IllegalParameterFault if the map of descendants is empty for a
     * folder sibling, or if the Folder is not valid
     */
    @Put
    Long saveAddedFolderAndTreeModifications(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "parentID") Long parentID,
            @WebParam(name = "folder") GPFolder folder,
            @WebParam(name = "descendantsMap") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Folder by ID, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param folderID the Folder ID
     * @param descendantsMapData the map of descendants (key = ancestor Folder
     * ID, value = number of its descendants)
     * @return true if the Folder was deleted
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Delete
    boolean saveDeletedFolderAndTreeModifications(
            @WebParam(name = "folderID") Long folderID,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    /**
     * Save the check status of a Folder and manage Folder ancestors.
     *
     * @param folderID the Folder ID
     * @param checked the check status
     * @return true if the check status was saved
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Put
    boolean saveCheckStatusFolderAndTreeModifications(
            @WebParam(name = "folderID") Long folderID,
            @WebParam(name = "checked") boolean checked)
            throws ResourceNotFoundFault;

    /**
     * Shift a Folder to a new position with a new parent Folder, and manage
     * positions on tree.
     *
     * @param idFolderMoved the Folder ID to move
     * @param newParentID the new Folder parent ID, set conventionally 0 if
     * idFolderMoved is refer to a folder of root
     * @param newPosition the new position of Folder
     * @param descendantsMapData the map of descendants (key = ancestors Folder
     * ID, value = number of its descendants)
     * @return true if the Folder wad shifted
     * @throws ResourceNotFoundFault if Folder or parent Folder not found
     */
    @Put
    boolean saveDragAndDropFolderAndTreeModifications(
            @WebParam(name = "folderMovedID") Long folderMovedID,
            @WebParam(name = "newParentID") Long newParentID,
            @WebParam(name = "newPosition") int newPosition,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Folder by ID
     *
     * @param folderID the Folder ID
     * @return the Folder to retrieve
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Get
    @WebResult(name = "folder")
    FolderDTO getShortFolder(@WebParam(name = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Folder by ID
     *
     * @param folderID the Folder ID
     * @return the Folder to retrieve
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Get
    @WebResult(name = "folder")
    GPFolder getFolderDetail(@WebParam(name = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    /**
     * Search Folders in paginated way. The Folders are searched by name like of
     * Folder name.
     *
     * @param request the request that wrap the search parameters
     * @return the paginated list of the Folders founded
     */
    @Get
    @WebResult(name = "folder")
    List<FolderDTO> searchFolders(PaginatedSearchRequest request);

    /**
     * Retrieve all Folders.
     *
     * @return the list of all Folders
     */
    @Get
    @WebResult(name = "folder")
    List<FolderDTO> getFolders();

    /**
     * Retrieve the number of Folders searched by name like of Folder name.
     *
     * @param request the request that wrap the search parameters
     * @return the number of Folders founded
     */
    @Get
    @WebResult(name = "count")
    long getFoldersCount(SearchRequest request);

    /**
     * Retrieve the children Folder of a Folder.
     *
     * @param request the request that wrap the search parameters
     * @return the list of children Folder
     */
    @Get
    @WebResult(name = "folder")
    List<FolderDTO> getChildrenFoldersByRequest(RequestByID request);

    /**
     * Retrieve the children Folder of a Folder by ID.
     *
     * @param folderID the Folder ID
     * @return the list of children Folder
     */
    @Get
    @WebResult(name = "folder")
    List<FolderDTO> getChildrenFolders(
            @WebParam(name = "folderID") Long folderID);

    /**
     * Retrieve the children elements (Folders and Layers) of a Folder.
     *
     * @param folderID the Folder ID
     * @return the tree of children elements (Folders and Layers)
     */
    @Get
    @WebResult(name = "childrenElement")
    TreeFolderElements getChildrenElements(
            @WebParam(name = "folderID") Long folderID);
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Folder / Project">

    /**
     * Retrieve only the root folders (top-level folders) of a Project.
     * <p/>
     * Return the Account owner only if the Account that load the project is not
     * the Account owner.
     *
     * @param projectID the Project ID
     * @return the root folders
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "project")
    ProjectDTO getProjectWithRootFolders(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Project. Retrieve also the root folders (top-level folders)
     * with content (sub-folders and layers), so all expanded folders (at any
     * level) in cascade.
     * <p/>
     * Return the Account owner only if the Account that load the project is not
     * the Account owner.
     *
     * @param projectID the Project ID
     * @return the Project to retrieve
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "project")
    ProjectDTO getProjectWithExpandedFolders(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Export a Project with its contents (folders and layers).
     *
     * @param projectID the Project ID
     * @return the Project to export
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "project")
    ProjectDTO exportProject(@WebParam(name = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Import a Project with its contents (folders and layers) for an Account
     * owner.
     *
     * @param projectDTO the Project to import
     * @param accountID the Account owner ID of the Project
     * @return the Project ID
     * @throws IllegalParameterFault if Project and its contents is not valid
     * @throws ResourceNotFoundFault if Account not found
     */
    @Put
    Long importProject(@WebParam(name = "projectDTO") ProjectDTO projectDTO,
            @WebParam(name = "accountID") Long accountID)
            throws IllegalParameterFault, ResourceNotFoundFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Layer (Raster and Vector)">
    // ==========================================================================
    // === Layer
    // ==========================================================================
    /**
     * Insert a Layer.
     *
     * @param layer the Layer to insert
     * @return the Layer ID
     * @throws IllegalParameterFault if Layer is not valid
     * @deprecated only for test purpose
     * @see #saveAddedLayerAndTreeModifications(java.lang.Long, java.lang.Long,
     * org.geosdi.geoplatform.core.model.GPLayer,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Put
    @Deprecated
    Long insertLayer(@WebParam(name = "layer") GPLayer layer)
            throws IllegalParameterFault;

    /**
     * Update a raster Layer.
     *
     * @param layer the Layer to update
     * @return the Layer ID
     * @throws ResourceNotFoundFault if Layer not found
     * @throws IllegalParameterFault if the Layer is not valid
     * @deprecated only for test purpose
     * @see
     * #saveLayerProperties(org.geosdi.geoplatform.responce.RasterPropertiesDTO)
     */
    @Post
    @Deprecated
    Long updateRasterLayer(@WebParam(name = "layer") GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update a vector Layer.
     *
     * @param layer the Layer to update
     * @return the Layer ID
     * @throws ResourceNotFoundFault if Layer not found
     * @throws IllegalParameterFault if the Layer is not valid
     * @deprecated only for test purpose
     */
    @Post
    @Deprecated
    Long updateVectorLayer(@WebParam(name = "layer") GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Layer by ID.
     *
     * @param layerID the Layer ID
     * @return true if the Layer was deleted
     * @throws ResourceNotFoundFault if Layer not found
     * @deprecated only for test purpose
     * @see #saveDeletedLayerAndTreeModifications(java.lang.Long,
     * org.geosdi.geoplatform.responce.collection.GPWebServiceMapData)
     */
    @Delete
    @Deprecated
    boolean deleteLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Insert a Layer, moreover manage Folder ancestors and positions on tree.
     *
     * @param projectID the Project ID
     * @param parentFolderID the parent Folder ID
     * @param layer the Layer to insert
     * @param descendantsMapData the map of descendants (key = ancestor Folder
     * ID, value = number of its descendants)
     * @return the Layer ID
     * @throws ResourceNotFoundFault if Project or parent Folder not found
     * @throws IllegalParameterFault if Layer is not valid
     */
    @Put
    Long saveAddedLayerAndTreeModifications(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "parentFolderID") Long parentFolderID,
            @WebParam(name = "layer") GPLayer layer,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Insert a Layer list, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param projectID the Project ID
     * @param parentFolderID the parent Folder ID
     * @param layers the Layer list to insert
     * @param descendantsMapData the map of descendants (key = ancestor Folder
     * ID, value = number of its descendants)
     * @return the Layer list ID
     * @throws ResourceNotFoundFault if Project or parent Folder not found
     * @throws IllegalParameterFault if Layer or Layer list are not valid
     */
    @Put
    @WebResult(name = "layerID")
    ArrayList<Long> saveAddedLayersAndTreeModifications(
            @WebParam(name = "projectID") Long projectID,
            @WebParam(name = "parentFolderID") Long parentFolderID,
            @WebParam(name = "layers") List<GPLayer> layers,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Layer by ID, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param layerID the Layer ID
     * @param descendantsMapData the map of descendants (key = ancestor Folder
     * ID, value = number of its descendants)
     * @return true if the Layer was deleted
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Delete
    boolean saveDeletedLayerAndTreeModifications(
            @WebParam(name = "layerID") Long layerID,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    /**
     * Save the check status of a Layer and manage Folder ancestors.
     *
     * @param layerID the Layer ID
     * @param checked the check status
     * @return true if the check status was saved
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Put
    boolean saveCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerID") Long layerID,
            @WebParam(name = "checked") boolean checked)
            throws ResourceNotFoundFault;

    /**
     * Fix the check status of old and new ancestors for a layer checked, with
     * Drag and Drop operation.
     *
     * @param layerID the Layer ID
     * @param oldFolderID the origin parent Folder ID
     * @param newFolderID the final parent Folder ID
     * @return true if the check status was fixed
     * @throws ResourceNotFoundFault if Layer or old/new ancestors Folder not
     * found
     */
    @Put
    boolean fixCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerID") Long layerID,
            @WebParam(name = "oldFolderID") Long oldFolderID,
            @WebParam(name = "newFolderID") Long newFolderID)
            throws ResourceNotFoundFault;

    /**
     * Shift a Layer to a new position with a new parent Folder, and manage
     * positions on tree.
     *
     * @param layerMovedID the Layer ID to move
     * @param newParentID the new Folder parent ID
     * @param newPosition the new position of Layer
     * @param descendantsMapData the map of descendants (key = ancestors Folder
     * ID, value = number of its descendants)
     * @return true if the Layer wad shifted
     * @throws ResourceNotFoundFault if Layer or parent Folder not found
     * @throws IllegalParameterFault if parent Folder ID is null
     */
    @Put
    boolean saveDragAndDropLayerAndTreeModifications(
            @WebParam(name = "layerMovedID") Long layerMovedID,
            @WebParam(name = "newParentID") Long newParentID,
            @WebParam(name = "newPosition") int newPosition,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update properties (alias, opacity, styles, CQL filter and checked) of a
     * Layer.
     *
     * @param layerProperties the Layer properties
     * @return true if the Layer was updated
     * @throws ResourceNotFoundFault if Layer not found
     * @throws IllegalParameterFault if Layer is not valid
     */
    @Post
    boolean saveLayerProperties(
            @WebParam(name = "layerProperties") RasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Retrieve a raster Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     * @throws ResourceNotFoundFault if raster Layer not found
     */
    @Get
    @WebResult(name = "layer")
    GPLayer getLayerDetail(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a raster Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     * @throws ResourceNotFoundFault if raster Layer not found
     */
    @Get
    @WebResult(name = "rasterLayer")
    GPRasterLayer getRasterLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a vector Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     * @throws ResourceNotFoundFault if vector Layer not found
     */
    @Get
    @WebResult(name = "vectorLayer")
    GPVectorLayer getVectorLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @WebResult(name = "shortLayerDTO")
    ShortLayerDTO getShortLayer(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a list of all Layers in a Project.
     *
     * @param projectID the Project ID
     * @return the list of Layers
     */
    @Get
    @WebResult(name = "layer")
    List<ShortLayerDTO> getLayers(@WebParam(name = "projectID") Long projectID);

    /**
     * Retrieve the Bounding Box of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Bounding Box
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @WebResult(name = "bBox")
    GPBBox getBBox(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the info of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Layer info
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @WebResult(name = "layerInfo")
    GPLayerInfo getLayerInfo(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;
//
//    /**
//     * @return Styles of a layer.
//     */
//    @Get
//    @WebResult(name = "layerStyle")
//    List<StyleDTO> getLayerStyles(@WebParam(name = "layerID") Long layerID);
//    
//     /**
//     * @return Styles of a layer.
//     */
//     @Get
//     @WebResult(name = "layerStyle")
//     List<StyleDTO> getLayerStyles(@WebParam(name = "layerID") Long layerID);

    /**
     * Retrieve the Geometry of a vector Layer.
     *
     * @param layerID the Layer ID
     * @return the Geometry
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @WebResult(name = "geometry")
    Geometry getGeometry(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the type of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Layer type
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @WebResult(name = "layerType")
    GPLayerType getLayerType(@WebParam(name = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the list of all data source (server URL) of a Project.
     *
     * @param projectID the Project ID
     * @return the list of data source
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @WebResult(name = "layerDataSource")
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
    @WebResult(name = "role")
    List<String> getAllRoles(@WebParam(name = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all GuiComponet IDs.
     *
     * @return List of all Roles
     */
    @Get
    @WebResult(name = "guiComponentID")
    List<String> getAllGuiComponentIDs();

    /**
     * Retrieve GUI Component permissions for an Application.
     * <p/>
     * It is based only on application ID.
     *
     * @param appID application ID
     *
     * @return Map that contains GUI Components permissions, with: <ul> <li> key
     * = ID Component </li> <li> value = Permission </li> </ul>
     * @throws ResourceNotFoundFault if the application is not found
     */
    @Get
    @WebResult(name = "applicationGuiComponentsPermissionMapData")
    GuiComponentsPermissionMapData getApplicationPermission(
            @WebParam(name = "accountID") String appID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve GUI Component permissions for an Account.
     * <p/>
     * It is based on accounts with disjoined authorities.
     *
     * @param accountID account ID
     *
     * @return Map that contains GUI Components permissions, with: <ul> <li> key
     * = ID Component </li> <li> value = Permission </li> </ul>
     * @throws ResourceNotFoundFault if the account is not found
     */
    @Get
    @WebResult(name = "guiComponentsPermissionMapData")
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
    @WebResult(name = "guiComponentsPermissionMapData")
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
    Long insertServer(@WebParam(name = "server") GeoPlatformServer server);

    @Post
    Long updateServer(@WebParam(name = "server") GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    boolean deleteServer(@WebParam(name = "serverID") Long serverID)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "server")
    List<ServerDTO> getAllServers();

    @Get
    @WebResult(name = "server")
    GeoPlatformServer getServerDetail(@WebParam(name = "serverID") Long serverID)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "server")
    ServerDTO getShortServer(@WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "server")
    GeoPlatformServer getServerDetailByUrl(
            @WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Post
    ServerDTO saveServer(@WebParam(name = "id") Long id,
            @WebParam(name = "aliasServerName") String aliasServerName,
            @WebParam(name = "serverUrl") String serverUrl,
            @WebParam(name = "organization") String organization)
            throws IllegalParameterFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Message">
    // ==========================================================================
    // === Message
    // ==========================================================================
    /**
     * Insert a Message.
     *
     * @param message the Message to insert
     * @return the Message ID
     * @throws ResourceNotFoundFault if the Account recipient or sender not
     * found
     * @throws IllegalParameterFault if Message is not valid
     */
    @Put
    Long insertMessage(@WebParam(name = "message") GPMessage message)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Insert a same Message for each Account recipient. Ignore message where
     * sender and recipient are the same.
     *
     * @param messageDTO the Message to insert for each Account recipient
     * @return true if the Messages was added
     * @throws ResourceNotFoundFault if Account sender or one Account recipient
     * not found
     */
    @Put
    boolean insertMultiMessage(@WebParam(name = "messageDTO") MessageDTO messageDTO)
            throws ResourceNotFoundFault;

    /**
     * Delete a Message by ID.
     *
     * @param messageID the Message ID
     * @return true if the Message was deleted
     * @throws ResourceNotFoundFault if Message not found
     */
    @Delete
    boolean deleteMessage(@WebParam(name = "messageID") Long messageID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Message by ID.
     *
     * @param messageID the Message ID
     * @return the Message to retrieve
     * @throws ResourceNotFoundFault if Message not found
     */
    @Get
    GPMessage getMessageDetail(@WebParam(name = "messageID") Long messageID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all Messages of an Account recipient, sorted in descending order
     * by creation date.
     *
     * @param recipientID the Account recipient ID
     * @return list of all Messages
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    @Get
    @WebResult(name = "message")
    List<GPMessage> getAllMessagesByRecipient(
            @WebParam(name = "recipientID") Long recipientID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve unread Messages of an Account recipient, sorted in descending
     * order by creation date.
     *
     * @param recipientID the Account recipient ID
     * @return list of unread Messages
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    @Get
    @WebResult(name = "message")
    List<GPMessage> getUnreadMessagesByRecipient(
            @WebParam(name = "recipientID") Long recipientID)
            throws ResourceNotFoundFault;

    /**
     * Mark a Message as read.
     *
     * @param messageID the Message ID
     * @return true if the Message was marked
     * @throws ResourceNotFoundFault if Message not found
     */
    boolean markMessageAsRead(@WebParam(name = "messageID") Long messageID)
            throws ResourceNotFoundFault;

    /**
     * Mark all Messages of an Account recipient as read.
     *
     * @param recipientID the Account recipient ID
     * @return true if the Messages was marked
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    @Post
    boolean markAllMessagesAsReadByRecipient(
            @WebParam(name = "recipientID") Long recipientID)
            throws ResourceNotFoundFault;

    /**
     * Mark all unread Messages, until a date, of an Account recipient as read.
     *
     * @param recipientID the Account recipient ID
     * @param toDate the date to search unread Messages until this date
     * @return true if the Messages was marked
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    @Post
    boolean markMessagesAsReadByDate(
            @WebParam(name = "recipientID") Long recipientID,
            @WebParam(name = "toDate") Date toDate)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Access Info">
    // ==========================================================================
    // === Access Info
    // ==========================================================================

    @Put
    Long insertGSAccount(@WebParam(name = "gsAccount") GSAccount gsAccount);

    @Put
    Long insertGSResource(@WebParam(name = "gsResource") GSResource gsResource);

    @Get
    @WebResult(name = "accessInfo")
    GSResource getGSResourceByLayerNameAndGsUser(
            @WebParam(name = "layerName") String layerName,
            @WebParam(name = "gsUser") String gsUser);

    @Get
    @WebResult(name = "accessInfo")
    GSResource getGSResourceByWorkspaceAndGsUser(
            @WebParam(name = "workspace") String workspace,
            @WebParam(name = "gsUser") String gsUser);

    @Get
    @WebResult(name = "gsUser")
    String getGSUserByAuthkey(@WebParam(name = "authkey") String authkey);
    // </editor-fold>
}
