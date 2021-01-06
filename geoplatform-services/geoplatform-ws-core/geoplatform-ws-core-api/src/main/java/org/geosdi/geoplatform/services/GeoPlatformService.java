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
package org.geosdi.geoplatform.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.cxf.annotations.FastInfoset;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.request.*;
import org.geosdi.geoplatform.request.folder.InsertFolderRequest;
import org.geosdi.geoplatform.request.folder.WSAddFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.request.folder.WSDeleteFolderAndTreeModifications;
import org.geosdi.geoplatform.request.layer.*;
import org.geosdi.geoplatform.request.message.MarkMessageReadByDateRequest;
import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.request.project.CloneProjectRequest;
import org.geosdi.geoplatform.request.project.ImportProjectRequest;
import org.geosdi.geoplatform.request.project.SaveProjectRequest;
import org.geosdi.geoplatform.request.server.WSSaveServerRequest;
import org.geosdi.geoplatform.request.viewport.InsertViewportRequest;
import org.geosdi.geoplatform.request.viewport.ManageViewportRequest;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.authority.GetAuthoritiesResponseWS;
import org.geosdi.geoplatform.response.authority.GetAuthorityResponse;
import org.geosdi.geoplatform.response.collection.ChildrenFolderStore;
import org.geosdi.geoplatform.response.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.response.collection.LongListStore;
import org.geosdi.geoplatform.response.collection.TreeFolderElementsStore;
import org.geosdi.geoplatform.response.message.GetMessageResponse;
import org.geosdi.geoplatform.response.role.GPGetRoleResponse;
import org.geosdi.geoplatform.response.role.WSGetRoleResponse;
import org.geosdi.geoplatform.response.viewport.WSGetViewportResponse;
import org.geosdi.geoplatform.services.core.api.GPCoreServiceApi;
import org.geosdi.geoplatform.services.rs.path.GPServiceRSPathConfig;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Public interface to define the service operations mapped via REST and SOAP
 * using {http://cxf.apache.org/} Framework.
 *
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * @author Nazzareno Sileno - CNR IMAA - geoSDI
 */
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowOrigins = {
                "*"
        },
        allowCredentials = true,
        maxAge = 1,
        allowHeaders = {
                "X-custom-1", "X-custom-2", "authorization", "content-type", "X-HTTP-Method-Override"

        },
        exposeHeaders = {
                "X-custom-3", "X-custom-4", "authorization", "content-type", "X-HTTP-Method-Override"

        }
)
@Path(value = GPServiceRSPathConfig.DEFAULT_RS_SERVICE_PATH)
@Api(value = GPServiceRSPathConfig.GP_CORE_SERVICE_RS_PATH,
        description = "Base GeoPlatform REST Service Core")
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@FastInfoset(serializerMinAttributeValueSize = 200, serializerMaxAttributeValueSize = 400,
        serializerMinCharacterContentChunkSize = 100, serializerAttributeValueMapMemoryLimit = 200,
        serializerMaxCharacterContentChunkSize = 300, serializerCharacterContentChunkMapMemoryLimit = 200)
@WebService(name = "GeoPlatformService",
        targetNamespace = "http://services.geo-platform.org/")
public interface GeoPlatformService extends GPCoreServiceApi {

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
    @Post
    @Path(value = GPServiceRSPathConfig.INSERT_ORGANIZATION_PATH)
    @POST
    @Deprecated
    @Override
    Long insertOrganization(
            @WebParam(name = "organization") GPOrganization organization)
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
    @Path(value = GPServiceRSPathConfig.DELETE_ORGANIZATION_PATH)
    @DELETE
    @Deprecated
    @Override
    Boolean deleteOrganization(
            @WebParam(name = "organizationID")
            @PathParam(value = "organizationID") Long organizationID)
            throws ResourceNotFoundFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Account (User and Application)">
    // ==========================================================================
    // === Account
    // ==========================================================================

    /**
     * Insert an Account (User or Application).
     *
     * @param insertAccountRequest
     * @return the Account ID
     * @throws IllegalParameterFault if the account not have an Organization or
     *                               there is a duplicate Account
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_ACCOUNT_PATH)
    @Override
    Long insertAccount(@WebParam(name = "InsertAccountRequest") InsertAccountRequest insertAccountRequest)
            throws IllegalParameterFault;

    /**
     * @param emailRecipient
     * @param userNameToNotify
     * @throws IllegalParameterFault
     */
    @Post
    void sendCASNewUserNotification(List<String> emailRecipient, String userNameToNotify) throws IllegalParameterFault;

    /**
     * Update a User and his Authorities.
     *
     * @param user the User to update
     * @return the User ID
     * @throws ResourceNotFoundFault if User not found
     * @throws IllegalParameterFault if User ID is null or update a standard
     *                               User to a temporary User
     */
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_USER_PATH)
    @Put
    @Override
    Long updateUser(@WebParam(name = "User") GPUser user) throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update a User, also his password. If password or email is changed will be
     * sent an email for modification.
     *
     * @param user                 the User to update
     * @param currentPlainPassword the plaintext password of the User
     * @param newPlainPassword     the new plaintext password of the User
     * @return the User ID
     * @throws ResourceNotFoundFault if User not found
     * @throws IllegalParameterFault if User ID is null or the
     *                               currentPlainPassword is wrong
     */
    @Put
    Long updateOwnUser(@WebParam(name = "User") UserDTO user, @WebParam(name = "currentPlainPassword") String currentPlainPassword,
            @WebParam(name = "newPlainPassword") String newPlainPassword) throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update an Application and his Authorities.
     *
     * @param application the Application to update
     * @return the Application ID
     * @throws ResourceNotFoundFault if Application not found
     * @throws IllegalParameterFault if Application ID is null or update a
     *                               standard Application to a temporary Application
     */
    @Post
    Long updateApplication(@WebParam(name = "application") GPApplication application) throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete an Account by ID. Delete his Authorities, the owner Project and
     * the reference to shared Project.
     *
     * @param accountID the Account ID
     * @return true if the Account was deleted
     * @throws ResourceNotFoundFault if Account not found
     */
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_ACCOUNT_PATH)
    @Delete
    @Override
    Boolean deleteAccount(@WebParam(name = "accountID") @QueryParam(value = "accountID") Long accountID) throws ResourceNotFoundFault;

    /**
     * Retrieve a User by ID.
     *
     * @param userID the User ID
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @GET
    @ApiOperation(value = "Find an User by userID",
            notes = "Search a User with the specific userID ",
            response = GPUser.class)
    @ApiResponse(code = 404, message = "User Not Found")
    @Path(value = GPServiceRSPathConfig.GET_USER_DETAIL_BY_ID_PATH)
    @WebResult(name = "user")
    @Override
    GPUser getUserDetail(@WebParam(name = "userID") @PathParam(value = "userID") @ApiParam(name = "userID", value = "The ID of user to fetch", required = true) Long userID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by username.
     *
     * @param request the request that wrap the username
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @GET
    @ApiOperation(value = "Find an User by UserName",
            notes = "Search a User with the specific UserName ",
            response = GPUser.class)
    @ApiResponse(code = 404, message = "User Not Found")
    @Path(value = GPServiceRSPathConfig.GET_USER_DETAIL_BY_USERNAME_PATH)
    @WebResult(name = "user")
    @Override
    GPUser getUserDetailByUsername(@QueryParam("") @ApiParam(
            name = "searchRequest",
            value = "The SearchRequest to use to find User by UserName",
            required = true) SearchRequest request)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by username (or email) and password to authenticate it.
     *
     * @param username      the username or the email of the User
     * @param plainPassword the plaintext password of the User
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found or not have Authorities
     * @throws IllegalParameterFault if the password is wrong
     * @throws AccountLoginFault     if User is disabled or expired
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_USER_DETAIL_BY_USERNAME_AND_PASSWORD_PATH)
    @WebResult(name = "user")
    @Override
    GPUser getUserDetailByUsernameAndPassword(
            @WebParam(name = "username")
            @QueryParam(value = "username") String username,
            @WebParam(name = "plainPassword")
            @QueryParam(value = "plainPassword") String plainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault,
            AccountLoginFault;

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
     * @throws AccountLoginFault     if Application is disabled or expired
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SHORT_USER_BY_ID_PATH)
    @WebResult(name = "user")
    @Override
    UserDTOResponse getShortUser(@WebParam(name = "userID")
    @PathParam(value = "userID") Long userID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a User by username.
     *
     * @param request
     * @return the User to retrieve
     * @throws ResourceNotFoundFault if User not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SHORT_USER_BY_USERNAME_PATH)
    @WebResult(name = "user")
    @Override
    UserDTOResponse getShortUserByUsername(@QueryParam("") SearchRequest request)
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
     * @param userID  the User ID that will exclude from the search (logged user)
     * @param request the request that wrap the search parameters
     * @return the paginate list of Users found
     * @throws ResourceNotFoundFault if User not found or a searched User not
     *                               have Authorities
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.SEARCH_USERS_PATH)
    @WebResult(name = "searchUserResponse")
    @Override
    SearchUsersResponseWS searchUsers(@WebParam(name = "userID") @QueryParam(value = "userID") Long userID,
            @QueryParam("") PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * @param userID
     * @param request
     * @return {@link SearchUsersResponseWS}
     * @throws ResourceNotFoundFault
     */
    @GET
    @Path(value = GPServiceRSPathConfig.SEARCH_ENABLED_USERS_PATH)
    SearchUsersResponseWS searchEnabledUsers(@QueryParam(value = "userID") Long userID, @QueryParam("") PaginatedSearchRequest request) throws ResourceNotFoundFault;

    /**
     * Retrieve all Accounts.
     *
     * @return the list of Accounts
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ALL_ACCOUNTS_PATH)
    @WebResult(name = "account")
    @Override
    ShortAccountDTOContainer getAllAccounts();

    /**
     * Retrieve all Accounts within an Organization.
     *
     * @param organization the Organization name
     * @return the list of Accounts
     * @throws ResourceNotFoundFault if Organization not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ALL_ORGANIZATION_ACCOUNTS_PATH)
    @WebResult(name = "account")
    @Override
    ShortAccountDTOContainer getAccounts(
            @WebParam(name = "organization")
            @PathParam(value = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of Accounts founded by search request.
     *
     * @param request the request that wrap the username (for User) or the
     *                string ID (for Application)
     * @return the number of Accounts found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNTS_COUNT_PATH)
    @WebResult(name = "count")
    @Override
    Long getAccountsCount(@QueryParam("") SearchRequest request);

    /**
     * Retrieve the number of Users of an Organization founded by search
     * request.
     *
     * @param organization the Organization name
     * @param request      the request that wrap the username
     * @return the number of Users found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_USERS_COUNT_PATH)
    @WebResult(name = "count")
    @Override
    Long getUsersCount(@WebParam(name = "organization")
    @QueryParam(value = "organization") String organization,
            @QueryParam("") SearchRequest request);

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param accountID the Account ID
     * @return the list of string Authorities
     * @throws ResourceNotFoundFault if Account not found or Account not have
     *                               Authorities
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_AUTHORITIES_PATH)
    @WebResult(name = "authority")
    @Override
    GetAuthorityResponse getAuthorities(@WebParam(name = "accountID")
    @PathParam(value = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the Authorities of an Account.
     *
     * @param accountNaturalID the username (for User) or the appID (for
     *                         Application)
     * @return the list of Authorities
     * @throws ResourceNotFoundFault if Account not found or Account not have
     *                               Authorities
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_AUTHORITIES_BY_ACCOUNT_NATURAL_ID)
    @WebResult(name = "authority")
    @Override
    GetAuthoritiesResponseWS getAuthoritiesDetail(
            @WebParam(name = "accountNaturalID")
            @PathParam(value = "accountNaturalID") String accountNaturalID)
            throws ResourceNotFoundFault;

    /**
     * Set an Account as temporary.
     *
     * @param accountID the Account ID
     * @throws ResourceNotFoundFault if Account not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.FORCE_TEMPORARY_ACCOUNT_PATH)
    @Override
    void forceTemporaryAccount(@WebParam(name = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Set a temporary Account as expired.
     *
     * @param accountID the Account ID
     * @throws ResourceNotFoundFault if Account not found
     * @throws IllegalParameterFault if Account is not temporary
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.FORCE_EXPIRED_TEMPORARY_ACCOUNT_PATH)
    @Override
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
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_ACCOUNT_PROJECT_PATH)
    @Override
    Long insertAccountProject(
            @WebParam(name = "accountProject") GPAccountProject accountProject)
            throws IllegalParameterFault;

    /**
     * Update an AccountProject, the Account and the Project attached.
     *
     * @param accountProject the AccountProject to update
     * @return the AccountProject ID
     * @throws ResourceNotFoundFault if AccountProject, Account or Project not
     *                               found
     * @throws IllegalParameterFault if AccountProject, Account or Project are
     *                               not valid
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_ACCOUNT_PROJECT_PATH)
    @Override
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
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_ACCOUNT_PROJECT_PATH)
    @Override
    Boolean deleteAccountProject(
            @WebParam(name = "accountProjectID")
            @PathParam(value = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve an AccountProject by ID.
     *
     * @param accountProjectID the AccountProject ID
     * @return the AccountProject to retrieve
     * @throws ResourceNotFoundFault if AccountProject not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PROJECT_PATH)
    @WebResult(name = "accountProject")
    @Override
    GPAccountProject getAccountProject(
            @WebParam(name = "accountProjectID")
            @PathParam(value = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the AccountProjects of an Account.
     *
     * @param accountID the Account ID
     * @return List of AccountProject
     */
    @Get
    @GET
    @CrossOriginResourceSharing(
            allowOrigins = {"http://127.0.0.1:9001"},
            allowCredentials = true,
            exposeHeaders = {"X-custom-3", "X-custom-4"}
    )
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PROJECTS_BY_ACCOUNT_ID)
    @WebResult(name = "accountProject")
    @Override
    WSGetAccountProjectsResponse getAccountProjectsByAccountID(@PathParam(
            value = "accountID")
    @WebParam(name = "accountID") Long accountID);

    /**
     * Retrieve the AccountProjects for a Project.
     *
     * @param projectID the Project ID
     * @return List of AccountProject
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PROJECTS_BY_PROJECT_ID_PATH)
    @WebResult(name = "accountProject")
    @Override
    WSGetAccountProjectsResponse getAccountProjectsByProjectID(
            @WebParam(name = "projectID")
            @PathParam(value = "projectID") Long projectID);

    /**
     * Retrieve an AccountProject of an Account for a Project.
     *
     * @param accountID the Account ID
     * @param projectID the Project ID
     * @return the AccountProject to retrieve
     * @throws ResourceNotFoundFault if AccountProject not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PROJECT_BY_ACCOUNT_AND_PROJECT_IDS_PATH)
    @WebResult(name = "accountProject")
    @Override
    GPAccountProject getAccountProjectByAccountAndProjectIDs(
            @WebParam(name = "accountID")
            @PathParam(value = "accountID") Long accountID,
            @WebParam(name = "projectID")
            @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of AccountProjects of an Account, founded by search
     * request.
     *
     * @param accountID the Account ID
     * @param request   the request that wrap the username (for User) or the
     *                  string ID (for Application)
     * @return the number of AccountProjects found
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PROJECTS_COUNT_PATH)
    @Override
    Long getAccountProjectsCount(@WebParam(name = "accountID")
    @QueryParam(value = "accountID") Long accountID,
            @QueryParam("") SearchRequest request) throws ResourceNotFoundFault;

    /**
     * Retrieve the default AccountProject of an Account, or null if the Account
     * don't have a default Project.
     *
     * @param accountID the Account ID
     * @return the AccountProject to retrieve or null
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_DEFAULT_ACCOUNT_PROJECT_PATH)
    @WebResult(name = "defaultAccountProject")
    @Override
    GPAccountProject getDefaultAccountProject(
            @WebParam(name = "accountID")
            @PathParam(value = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all the Account Projects in paginated way. There are two Project
     * type, first that the Account is the owner, and latter related at a shared
     * Project.
     * <p/>
     * Each Project result, if shared, contain the own Account owner.
     *
     * @param accountID the Account ID
     * @param request   the request that wrap Project ID, Project name and Account
     *                  ID
     * @return the paginated list of Projects of the Account
     * @throws ResourceNotFoundFault if Account not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.SEARCH_ACCOUNT_PROJECTS_PATH)
    @WebResult(name = "project")
    @Override
    List<ProjectDTO> searchAccountProjects(
            @WebParam(name = "accountID")
            @QueryParam(value = "accountID") Long accountID,
            @QueryParam("") PaginatedSearchRequest request)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the Account owner of a Project.
     *
     * @param projectID the Project ID
     * @return the Account owner
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_PROJECT_OWNER_PATH)
    @WebResult(name = "projectOwner")
    @Override
    GPAccountProject getProjectOwner(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Set an Account owner for a Project.
     *
     * @param request the request that wrap Project ID and Account ID
     * @return true if the Account owner was changed
     * @throws ResourceNotFoundFault if Project or Account not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SET_PROJECT_OWNER_PATH)
    @Override
    Boolean setProjectOwner(RequestByAccountProjectIDs request)
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_DEFAULT_PROJECT_PATH)
    @WebResult(name = "defaultProject")
    @Override
    GPProject getDefaultProject(@WebParam(name = "accountID")
    @PathParam(value = "accountID") Long accountID)
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_DEFAULT_PROJECT_DTO_PATH)
    @WebResult(name = "defaultProjectDTO")
    @Override
    ProjectDTO getDefaultProjectDTO(@WebParam(name = "accountID")
    @PathParam(value = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Update the default Project for an Account.
     *
     * @param accountID the Account ID
     * @param projectID the Project ID
     * @return true if the default Project was updated
     * @throws ResourceNotFoundFault if Account or Project not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_DEFAULT_PROJECT_PATH)
    @Override
    GPProject updateDefaultProject(@WebParam(name = "accountID")
    @QueryParam(value = "accountID") Long accountID,
            @WebParam(name = "projectID")
            @QueryParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Save the Project information and optionally set it as default for an
     * Account.
     *
     * @param accountProjectProperties request that wrap Project information and
     *                                 Account ID
     * @return true if the properties was saved
     * @throws ResourceNotFoundFault if Project or Account not found
     * @throws IllegalParameterFault if Project name is invalid
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_ACCOUNT_PROJECT_PROPERTIES_PATH)
    @Override
    Boolean saveAccountProjectProperties(
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNTS_BY_PROJECT_ID_PATH)
    @WebResult(name = "account")
    @Override
    ShortAccountDTOContainer getAccountsByProjectID(
            @WebParam(name = "projectID")
            @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve all Accounts to which it is possible to share a Project.
     *
     * @param projectID the Project ID
     * @return the Accounts to which the Project is not shared
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNTS_TO_SHARE_BY_PROJECT_ID_PATH)
    @WebResult(name = "account")
    @Override
    ShortAccountDTOContainer getAccountsToShareByProjectID(
            @WebParam(name = "projectID")
            @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Update all at once the relations of sharing for a Project. The Account
     * owner ID relation must be present, otherwise the Project will be unshare.
     *
     * @param apRequest {@link PutAccountsProjectRequest}
     * @return true if the relations of sharing are updated
     * @throws ResourceNotFoundFault                                  if Project not found or an Account not
     *                                                                found
     * @throws org.geosdi.geoplatform.exception.IllegalParameterFault
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_ACCOUNTS_PROJECT_SHARING_PATH)
    @WebResult(name = "account")
    @Override
    Boolean updateAccountsProjectSharing(PutAccountsProjectRequest apRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================

    /**
     * Save a Project and its Account owner.
     *
     * @param saveProjectRequest
     * @return the Project ID
     * @throws ResourceNotFoundFault if the Account not found
     * @throws IllegalParameterFault if the Project is not valid
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.SAVE_PROJECT_PATH)
    @Override
    Long saveProject(
            @WebParam(name = "saveProjectRequest") SaveProjectRequest saveProjectRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Clonse a Project.
     *
     * @param cloneProjectRequest
     * @return the Project ID
     * @throws ResourceNotFoundFault if the Account not found
     * @throws IllegalParameterFault if the Project is not valid
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.CLONE_PROJECT_PATH)
    @Override
    Long cloneProject(
            @WebParam(name = "cloneProjectRequest") CloneProjectRequest cloneProjectRequest)
            throws Exception;

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    @POST
    @Path(value = GPServiceRSPathConfig.FIND_INTERNAL_PUBLIC_PROJECTS)
    ProjectDTOContainer findInternalPublicProjects(PaginatedSearchRequest request) throws Exception;

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    @POST
    @Path(value = GPServiceRSPathConfig.FIND_EXTERNAL_PUBLIC_PROJECTS)
    ProjectDTOContainer findExternalPublicProjects(PaginatedSearchRequest request) throws Exception;

    /**
     * Save a Project.
     *
     * @param project the Project to save
     * @return the Project ID
     * @throws IllegalParameterFault if the Project is not valid
     * @see #saveProject(SaveProjectRequest)
     * @deprecated only for test purpose
     */
    @Post
    @Path(value = GPServiceRSPathConfig.INSERT_PROJECT_PATH)
    @POST
    @Deprecated
    @Override
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
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_PROJECT_PATH)
    @Override
    Long updateProject(@WebParam(name = "project") GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Project.
     *
     * @param projectID the Project to delete
     * @return true if the Project was deleted
     * @throws ResourceNotFoundFault if the Project not found
     */
    @Delete
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_PROJECT_PATH)
    @Override
    Boolean deleteProject(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Project.
     *
     * @param projectID the Project ID
     * @return the Project to retrieve
     * @throws ResourceNotFoundFault if the Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_PROJECT_DETAIL_PATH)
    @WebResult(name = "project")
    @Override
    GPProject getProjectDetail(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the number of elements of a Project.
     *
     * @param projectID the Project ID
     * @return the number of elements to retrieve
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_NUMBER_OF_ELEMENTS_PROJECT_PATH)
    @WebResult(name = "project")
    @Override
    Integer getNumberOfElementsProject(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    /**
     * @param projectID
     * @return {@link ShortProjectDTO}
     * @throws ResourceNotFoundFault
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SHORT_PROJECT_PATH)
    ShortProjectDTO getShortProject(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID) throws ResourceNotFoundFault;

    /**
     * Set a Project as shared.
     *
     * @param projectID the Project ID
     * @throws ResourceNotFoundFault if Project not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SET_PROJECT_SHARED_PATH)
    @Override
    void setProjectShared(@WebParam(name = "projectID")
    @QueryParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Viewport">
    // ==========================================================================
    // === Viewport
    // ==========================================================================
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_DEFAULT_VIEWPORT_PATH)
    @WebResult(name = "viewport")
    @Override
    GPViewport getDefaultViewport(@WebParam(name = "accountProjectID")
    @PathParam(value = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PROJECT_VIEWPORTS_PATH)
    @WebResult(name = "wsGetVieportResponse")
    @Override
    WSGetViewportResponse getAccountProjectViewports(@WebParam(
            name = "accountProjectID")
    @PathParam(value = "accountProjectID") Long accountProjectID)
            throws ResourceNotFoundFault;

    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_VIEWPORT_PATH)
    @Override
    Long insertViewport(
            @WebParam(name = "insertViewportReq") InsertViewportRequest insertViewportReq)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_VIEWPORT_PATH)
    @Override
    Long updateViewport(@WebParam(name = "viewport") GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_VIEWPORT_BY_ID_PATH)
    @WebResult(name = "viewport")
    @Override
    GPViewport getViewportById(@WebParam(name = "idViewport")
    @QueryParam(value = "idViewport") Long idViewport)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_VIEWPORT_PATH)
    @Override
    Boolean deleteViewport(@WebParam(name = "viewportID")
    @QueryParam(value = "viewportID") Long viewportID)
            throws ResourceNotFoundFault;

    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_OR_UPDATE_VIEWPORT_LIST_PATH)
    @Override
    void saveOrUpdateViewportList(
            @WebParam(name = "request") ManageViewportRequest request)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.REPLACE_VIEWPORT_LIST_PATH)
    @Override
    void replaceViewportList(
            @WebParam(name = "request") ManageViewportRequest request)
            throws ResourceNotFoundFault, IllegalParameterFault;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================

    /**
     * Insert a Folder into a Project.
     *
     * @param insertFolderRequest
     * @return the Folder ID
     * @throws ResourceNotFoundFault if Project not found
     * @throws IllegalParameterFault if Folder is not valid
     * @see #saveAddedFolderAndTreeModifications(WSAddFolderAndTreeModificationsRequest)
     * @deprecated only for test purpose
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_FOLDER_PATH)
    @Deprecated
    @Override
    Long insertFolder(
            @WebParam(name = "insertFolderRequest") InsertFolderRequest insertFolderRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Update a Folder.
     *
     * @param folder the Folder to update
     * @return the Folder ID
     * @throws ResourceNotFoundFault if Folder not found
     * @throws IllegalParameterFault if Folder is not valid
     * @see #saveFolderProperties(java.lang.Long, java.lang.String, boolean,
     * boolean)
     * @deprecated only for test purpose
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_FOLDER_PATH)
    @Deprecated
    @Override
    Long updateFolder(@WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Folder by ID.
     *
     * @param folderID the Folder ID
     * @return true if the Folder was deleted
     * @throws ResourceNotFoundFault if Folder not found
     * @see #saveDeletedFolderAndTreeModifications(WSDeleteFolderAndTreeModifications)
     * @deprecated only for test purpose
     */
    @Delete
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_FOLDER_PATH)
    @Deprecated
    @Override
    Boolean deleteFolder(@WebParam(name = "folderID")
    @QueryParam(value = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    /**
     * Update properties (name, checked and expanded) of a Folder.
     *
     * @param folderID   the Folder ID
     * @param folderName the Folder name
     * @param checked    the Folder checked
     * @param expanded   the Folder expanded
     * @return the Folder ID
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.SAVE_FOLDER_PROPERTIES_PATH)
    @Override
    Long saveFolderProperties(@WebParam(name = "folderID")
    @QueryParam(value = "folderID") Long folderID,
            @WebParam(name = "folderName")
            @QueryParam(value = "folderName") String folderName,
            @WebParam(name = "checked")
            @QueryParam(value = "checked") boolean checked,
            @WebParam(name = "expanded")
            @QueryParam(value = "expanded") boolean expanded)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Insert a Folder, moreover manage Folder ancestors and positions on tree.
     *
     * @param sftModificationRequest
     * @return the Folder ID
     * @throws ResourceNotFoundFault if Project or parent Folder not found
     * @throws IllegalParameterFault if the map of descendants is empty for a
     *                               folder sibling, or if the Folder is not valid
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_ADDED_FOLDER_AND_TREE_MODICATIONS_PATH)
    @Override
    Long saveAddedFolderAndTreeModifications(@WebParam(
            name = "sftModificationRequest") WSAddFolderAndTreeModificationsRequest sftModificationRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Folder by ID, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param sdfModificationRequest
     * @return true if the Folder was deleted
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_DELETED_FOLDER_AND_TREE_MODIFICATIONS_PATH)
    @Override
    Boolean saveDeletedFolderAndTreeModifications(
            @WebParam(name = "sdfModificationRequest") WSDeleteFolderAndTreeModifications sdfModificationRequest)
            throws ResourceNotFoundFault;

    /**
     * Save the check status of a Folder and manage Folder ancestors.
     *
     * @param folderID the Folder ID
     * @param checked  the check status
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
     * @param sddfTreeModificationRequest
     * @return true if the Folder wad shifted
     * @throws ResourceNotFoundFault if Folder or parent Folder not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_DD_FOLDER_AND_TREE_MODIFICATIONS_PATH)
    @Override
    Boolean saveDragAndDropFolderAndTreeModifications(
            @WebParam(name = "sddfTreeModificationRequest") WSDDFolderAndTreeModifications sddfTreeModificationRequest)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Folder by ID
     *
     * @param folderID the Folder ID
     * @return the Folder to retrieve
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SHORT_FOLDER_PATH)
    @WebResult(name = "folder")
    @Override
    FolderDTO getShortFolder(@WebParam(name = "folderID")
    @PathParam(value = "folderID") Long folderID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Folder by ID
     *
     * @param folderID the Folder ID
     * @return the Folder to retrieve
     * @throws ResourceNotFoundFault if Folder not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_FOLDER_DETAIL_PATH)
    @WebResult(name = "folder")
    @Override
    GPFolder getFolderDetail(@WebParam(name = "folderID")
    @PathParam(value = "folderID") Long folderID)
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_CHILDREN_FOLDERS_PATH)
    @WebResult(name = "folder")
    @Override
    ChildrenFolderStore getChildrenFolders(@WebParam(name = "folderID")
    @PathParam(value = "folderID") Long folderID);

    /**
     * Retrieve the children elements (Folders and Layers) of a Folder.
     *
     * @param folderID the Folder ID
     * @return the tree of children elements (Folders and Layers)
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_CHILDREN_ELEMENTS_PATH)
    @WebResult(name = "childrenElement")
    @Override
    TreeFolderElementsStore getChildrenElements(@WebParam(name = "folderID")
    @PathParam(value = "folderID") Long folderID);
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_PROJECT_WITH_ROOT_FOLDERS_PATH)
    @WebResult(name = "project")
    @Override
    ProjectDTO getProjectWithRootFolders(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID,
            @WebParam(name = "accountID")
            @PathParam(value = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * <p>
     * Retrieve a Project. Retrieve also the root folders (top-level folders)
     * with content (sub-folders and layers), so all expanded folders (at any
     * level) in cascade.
     * <p/>
     * <p>
     * Return the Account owner only if the Account that load the project is not
     * the Account owner.
     * </p>
     *
     * @param projectID the Project ID
     * @param accountID
     * @return the Project to retrieve
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_PROJECT_WITH_EXPANDED_FOLDERS_PATH)
    @WebResult(name = "project")
    @Override
    ProjectDTO getProjectWithExpandedFolders(@WebParam(name = "projectID") @PathParam(value = "projectID") Long projectID,
            @WebParam(name = "accountID") @PathParam(value = "accountID") Long accountID) throws ResourceNotFoundFault;

    /**
     * Export a Project with its contents (folders and layers).
     *
     * @param projectID the Project ID
     * @return the Project to export
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.EXPORT_PROJECT_PATH)
    @WebResult(name = "project")
    @Override
    ProjectDTO exportProject(@WebParam(name = "projectID") @PathParam(value = "projectID") Long projectID) throws ResourceNotFoundFault;

    /**
     * Import a Project with its contents (folders and layers) for an Account
     * owner.
     *
     * @param impRequest
     * @return the Project ID
     * @throws IllegalParameterFault if Project and its contents is not valid
     * @throws ResourceNotFoundFault if Account not found
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.IMPORT_PROJECT_PATH)
    @Override
    Long importProject(@WebParam(name = "impRequest") ImportProjectRequest impRequest) throws Exception;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Layer (Raster and Vector)">
    // ==========================================================================
    // === Layer
    // ==========================================================================

    /**
     * Insert a Layer.
     *
     * @param layerRequest
     * @return Long
     * @throws org.geosdi.geoplatform.exception.IllegalParameterFault
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_LAYER_PATH)
    @Deprecated
    @Override
    Long insertLayer(
            @WebParam(name = "layerRequest") InsertLayerRequest layerRequest)
            throws IllegalParameterFault;

    /**
     * Update a raster Layer.
     *
     * @param layer the Layer to update
     * @return the Layer ID
     * @throws ResourceNotFoundFault if Layer not found
     * @throws IllegalParameterFault if the Layer is not valid
     * @see #saveLayerProperties(RasterPropertiesDTO)
     * @deprecated only for test purpose
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_RASTER_LAYER_PARH)
    @Deprecated
    @Override
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
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_VECTOR_LAYER_PATH)
    @Deprecated
    @Override
    Long updateVectorLayer(@WebParam(name = "layer") GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Layer by ID.
     *
     * @param layerID the Layer ID
     * @return true if the Layer was deleted
     * @throws ResourceNotFoundFault if Layer not found
     * @see #saveDeletedLayerAndTreeModifications(WSDeleteLayerAndTreeModificationsRequest)
     * @deprecated only for test purpose
     */
    @Delete
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_LAYER_PATH)
    @Deprecated
    @Override
    Boolean deleteLayer(@WebParam(name = "layerID")
    @QueryParam(value = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Insert a Layer, moreover manage Folder ancestors and positions on tree.
     *
     * @param addLayerRequest
     * @return the Layer ID
     * @throws ResourceNotFoundFault if Project or parent Folder not found
     * @throws IllegalParameterFault if Layer is not valid
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.ADD_LAYER_AND_TREE_MODIFICATIONS_PATH)
    @Override
    Long saveAddedLayerAndTreeModifications(
            @WebParam(name = "addLayerRequest") WSAddLayerAndTreeModificationsRequest addLayerRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Insert a Layer list, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param addLayersRequest
     * @return the Layer list ID
     * @throws ResourceNotFoundFault if Project or parent Folder not found
     * @throws IllegalParameterFault if Layer or Layer list are not valid
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.ADD_LAYERS_AND_TREE_MODIFICATIONS_PATH)
    @WebResult(name = "layerID")
    @Override
    LongListStore saveAddedLayersAndTreeModifications(
            @WebParam(name = "addLayersRequest") WSAddLayersAndTreeModificationsRequest addLayersRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Delete a Layer by ID, moreover manage Folder ancestors and positions on
     * tree.
     *
     * @param deleteLayerRequest
     * @return true if the Layer was deleted
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.DELETE_LAYER_AND_TREE_MODIFICATIONS_PATH)
    @Override
    Boolean saveDeletedLayerAndTreeModifications(
            @WebParam(name = "deleteLayerRequest") WSDeleteLayerAndTreeModificationsRequest deleteLayerRequest)
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
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_CHECK_STATUS_LAYER_AND_TREE_MODIFICATION_PATH)
    @Override
    Boolean saveCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerID")
            @QueryParam(value = "layerID") Long layerID,
            @WebParam(name = "checked")
            @QueryParam(value = "checked") boolean checked)
            throws ResourceNotFoundFault;

    /**
     * Fix the check status of old and new ancestors for a layer checked, with
     * Drag and Drop operation.
     *
     * @param layerID     the Layer ID
     * @param oldFolderID the origin parent Folder ID
     * @param newFolderID the final parent Folder ID
     * @return true if the check status was fixed
     * @throws ResourceNotFoundFault if Layer or old/new ancestors Folder not
     *                               found
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
     * @param ddLayerReq
     * @return true if the Layer wad shifted
     * @throws ResourceNotFoundFault if Layer or parent Folder not found
     * @throws IllegalParameterFault if parent Folder ID is null
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_DD_LAYER_AND_TREE_MODIFICATIONS_PATH)
    @Override
    Boolean saveDragAndDropLayerAndTreeModifications(
            @WebParam(name = "ddLayerReq") WSDDLayerAndTreeModificationsRequest ddLayerReq)
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
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_LAYERS_PROPERTIES_PATH)
    @Override
    Boolean saveLayerProperties(
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_RASTER_LAYER_PATH)
    @WebResult(name = "rasterLayer")
    @Override
    GPRasterLayerResponse getRasterLayer(@WebParam(name = "layerID")
    @PathParam(value = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a vector Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     * @throws ResourceNotFoundFault if vector Layer not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_VECTOR_LAYER_PATH)
    @WebResult(name = "vectorLayer")
    @Override
    GPVectorLayerResponse getVectorLayer(@WebParam(name = "layerID")
    @PathParam(value = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Layer by ID.
     *
     * @param layerID the Layer ID
     * @return the Layer to retrieve
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SHORT_LAYER_PATH)
    @WebResult(name = "shortLayerDTO")
    ShortLayerDTO getShortLayer(@WebParam(name = "layerID")
    @PathParam(value = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a list of all Layers in a Project.
     *
     * @param projectID the Project ID
     * @return the list of Layers
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_LAYERS_PATH)
    @WebResult(name = "layer")
    @Override
    ShortLayerDTOContainer getLayers(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID);

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_FIRST_LEVEL_LAYERS_PATH)
    @WebResult(name = "layers")
    @Override
    ShortLayerDTOContainer getFirstLevelLayers(@WebParam(name = "projectID")
    @PathParam(value = "projectID") Long projectID);

    /**
     * Retrieve the Bounding Box of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Bounding Box
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_LAYER_BBOX_PATH)
    @WebResult(name = "bBox")
    @Override
    GPBBox getBBox(@WebParam(name = "layerID")
    @PathParam(value = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the info of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Layer info
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_LAYER_INFO_PATH)
    @WebResult(name = "layerInfo")
    @Override
    GPLayerInfo getLayerInfo(@WebParam(name = "layerID")
    @PathParam(value = "layerID") Long layerID)
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
//    @Get
//    @WebResult(name = "geometry")
//    GeometryDTO getGeometry(@WebParam(name = "layerID") Long layerID)
//            throws ResourceNotFoundFault;

    /**
     * Retrieve the type of a Layer.
     *
     * @param layerID the Layer ID
     * @return the Layer type
     * @throws ResourceNotFoundFault if Layer not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_LAYER_TYPE_PATH)
    @WebResult(name = "layerType")
    @Override
    GPLayerType getLayerType(@WebParam(name = "layerID")
    @PathParam(value = "layerID") Long layerID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the list of all data source (server URL) of a Project.
     *
     * @param projectID the Project ID
     * @return the list of data source
     * @throws ResourceNotFoundFault if Project not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_LAYERS_DATA_SOURCE_BY_PROJECT_ID_PATH)
    @WebResult(name = "layerDataSource")
    @Override
    GetDataSourceResponse getLayersDataSourceByProjectID(
            @WebParam(name = "projectID")
            @PathParam(value = "projectID") Long projectID)
            throws ResourceNotFoundFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="ACL">
    // ==========================================================================
    // === ACL
    // ==========================================================================

    /**
     * Retrieve all Organization Roles.
     *
     * @param organization organization name
     * @return {@link GPGetRoleResponse} GetRoleResponse with all Roles
     * @throws org.geosdi.geoplatform.exception.ResourceNotFoundFault
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ALL_ROLES_PATH)
    @WebResult(name = "role")
    @Override
    WSGetRoleResponse getAllRoles(
            @WebParam(name = "organization")
            @PathParam(value = "organization") String organization)
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
     * @return Map that contains GUI Components permissions, with: <ul> <li> key
     * = ID Component </li> <li> value = Permission </li> </ul>
     * @throws ResourceNotFoundFault if the account is not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ACCOUNT_PERMISSIONS_PATH)
    @WebResult(name = "guiComponentsPermissionMapData")
    @Override
    GuiComponentsPermissionMapData getAccountPermission(
            @WebParam(name = "accountID")
            @PathParam(value = "accountID") Long accountID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve the GUI Component permissions for a Role (Authority).
     *
     * @param role         role (authority) name
     * @param organization organization name
     * @return Map that contains GUI Components permissions, with: key = ID
     * Component value = Permission
     * @throws ResourceNotFoundFault if the role (authority) is not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ROLE_PERMISSION_PATH)
    @WebResult(name = "guiComponentsPermissionMapData")
    @Override
    GuiComponentsPermissionMapData getRolePermission(
            @WebParam(name = "role")
            @QueryParam(value = "role") String role,
            @WebParam(name = "organization")
            @QueryParam(value = "organization") String organization)
            throws ResourceNotFoundFault;

    /**
     * Update the permission of a role (authority).
     *
     * @param putRolePermissionReq
     * @return if the update was successful
     * @throws ResourceNotFoundFault if the role (authority) is not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_ROLE_PERMISSION_PATH)
    @Override
    Boolean updateRolePermission(
            @WebParam(name = "putRolePermissionReq") WSPutRolePermissionRequest putRolePermissionReq)
            throws ResourceNotFoundFault;

    /**
     * Save a new role (authority).
     *
     * @param saveRoleReq
     * @return if the saving was successful
     * @throws IllegalParameterFault if the role (authority) already exist
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.SAVE_ROLE_PATH)
    @Override
    Boolean saveRole(
            @WebParam(name = "saveRoleReq") WSSaveRoleRequest saveRoleReq)
            throws IllegalParameterFault;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Server">
    // ==========================================================================
    // === Server
    // ==========================================================================
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_SERVER_PATH)
    @Override
    Long insertServer(@WebParam(name = "server") GeoPlatformServer server);

    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.UPDATE_SERVER_PATH)
    @Override
    Long updateServer(@WebParam(name = "server") GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_SERVER_PATH)
    @Override
    Boolean deleteServer(@WebParam(name = "serverID")
    @QueryParam(value = "serverID") Long serverID)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ALL_SERVERS_PATH)
    @WebResult(name = "server")
    @Override
    ServerDTOContainer getAllServers(
            @WebParam(name = "organizazionName")
            @PathParam(value = "organizazionName") String organizazionName)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SERVER_DETAIL_PATH)
    @WebResult(name = "server")
    @Override
    GeoPlatformServer getServerDetail(@WebParam(name = "serverID")
    @PathParam(value = "serverID") Long serverID)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SHORT_SERVER_PATH)
    @WebResult(name = "server")
    @Override
    ServerDTO getShortServer(@WebParam(name = "serverUrl")
    @QueryParam(value = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_SERVER_DETAIL_BY_URL_PATH)
    @WebResult(name = "server")
    @Override
    GeoPlatformServer getServerDetailByUrl(
            @WebParam(name = "serverUrl")
            @QueryParam(value = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.SAVE_SERVER_PATH)
    @Override
    ServerDTO saveServer(
            @WebParam(name = "saveServerReq") WSSaveServerRequest saveServerReq)
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
     *                               found
     * @throws IllegalParameterFault if Message is not valid
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_MESSAGE_PATH)
    @Override
    Long insertMessage(@WebParam(name = "message") GPMessage message)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * Insert a same Message for each Account recipient. Ignore message where
     * sender and recipient are the same.
     *
     * @param messageDTO the Message to insert for each Account recipient
     * @return true if the Messages was added
     * @throws ResourceNotFoundFault if Account sender or one Account recipient
     *                               not found
     */
    @Post
    @POST
    @Path(value = GPServiceRSPathConfig.INSERT_MULTI_MESSAGE_PATH)
    @Override
    Boolean insertMultiMessage(
            @WebParam(name = "messageDTO") MessageDTO messageDTO)
            throws ResourceNotFoundFault;

    /**
     * Delete a Message by ID.
     *
     * @param messageID the Message ID
     * @return true if the Message was deleted
     * @throws ResourceNotFoundFault if Message not found
     */
    @Delete
    @DELETE
    @Path(value = GPServiceRSPathConfig.DELETE_MESSAGE_PATH)
    @Override
    Boolean deleteMessage(@WebParam(name = "messageID")
    @PathParam(value = "messageID") Long messageID)
            throws ResourceNotFoundFault;

    /**
     * Retrieve a Message by ID.
     *
     * @param messageID the Message ID
     * @return the Message to retrieve
     * @throws ResourceNotFoundFault if Message not found
     */
    @Get
    @GET
    @Path(value = GPServiceRSPathConfig.GET_MESSAGE_DETAIL_PATH)
    @Override
    GPMessage getMessageDetail(@WebParam(name = "messageID")
    @PathParam(value = "messageID") Long messageID)
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_ALL_MESSAGES_BY_RECIPIENT_PATH)
    @WebResult(name = "message")
    @Override
    GetMessageResponse getAllMessagesByRecipient(
            @WebParam(name = "recipientID")
            @PathParam(value = "recipientID") Long recipientID)
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
    @GET
    @Path(value = GPServiceRSPathConfig.GET_UNREAD_MESSAGES_BY_RECIPIENT_PATH)
    @WebResult(name = "message")
    @Override
    GetMessageResponse getUnreadMessagesByRecipient(
            @WebParam(name = "recipientID")
            @PathParam(value = "recipientID") Long recipientID)
            throws ResourceNotFoundFault;

    /**
     * Mark a Message as read.
     *
     * @param messageID the Message ID
     * @return true if the Message was marked
     * @throws ResourceNotFoundFault if Message not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.MARK_MESSAGE_AS_READ_PATH)
    @Override
    Boolean markMessageAsRead(@WebParam(name = "messageID") Long messageID)
            throws ResourceNotFoundFault;

    /**
     * Mark all Messages of an Account recipient as read.
     *
     * @param recipientID the Account recipient ID
     * @return true if the Messages was marked
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.MARK_ALL_MESSAGES_AS_READ_BY_RECIPIENT_PATH)
    @Override
    Boolean markAllMessagesAsReadByRecipient(
            @WebParam(name = "recipientID") Long recipientID)
            throws ResourceNotFoundFault;

    /**
     * Mark all unread Messages, until a date, of an Account recipient as read.
     *
     * @param markMessageAsReadByDateReq
     * @return true if the Messages was marked
     * @throws ResourceNotFoundFault if Account recipient not found
     */
    @Put
    @PUT
    @Path(value = GPServiceRSPathConfig.MARK_MESSAGES_AS_READ_BY_DATE_PATH)
    @Override
    Boolean markMessagesAsReadByDate(
            @WebParam(name = "markMessageAsReadByDateReq") MarkMessageReadByDateRequest markMessageAsReadByDateReq)
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
