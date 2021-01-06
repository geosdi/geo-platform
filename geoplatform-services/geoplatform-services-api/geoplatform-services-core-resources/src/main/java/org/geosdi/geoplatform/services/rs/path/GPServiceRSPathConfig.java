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
package org.geosdi.geoplatform.services.rs.path;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPServiceRSPathConfig {

    public static final String DEFAULT_RS_SERVICE_PATH = "/";

    public static final String GP_CORE_SERVICE_RS_PATH = "/jsonCore";
    public static final String GP_SECURE_CORE_SERVICE_RS_PATH = "/jsonSecureCore";

    /**
     * SECURE RESOURCES PATHS
     */
    public static final String GP_SECURE_ACCOUNTS_PATH = "/jsonSecureAccount";
    public static final String GP_SECURE_ACL_PATH = "/jsonSecureAcl";
    public static final String GP_SECURE_FOLDER_PATH = "/jsonSecureFolder";
    public static final String GP_SECURE_LAYER_PATH = "/jsonSecureLayer";
    public static final String GP_SECURE_MESSAGE_PATH = "/jsonSecureMessage";
    public static final String GP_SECURE_ORGANIZATION_PATH = "/jsonSecureOrganization";
    public static final String GP_SECURE_PROJECT_PATH = "/jsonSecureProject";
    public static final String GP_SECURE_SERVER_PATH = "/jsonSecureServer";
    public static final String GP_SECURE_VIEWPORT_PATH = "/jsonSecureViewport";
    /**
     * ORGANIZATIONS PATH
     */
    private static final String ORGANIZATION_BASE_PATH = "/organizations/";
    public static final String INSERT_ORGANIZATION_PATH = ORGANIZATION_BASE_PATH
            + "insertOrganization";
    public static final String DELETE_ORGANIZATION_PATH = ORGANIZATION_BASE_PATH
            + "deleteOrganization/{organizationID}";
    public static final String GET_ALL_ROLES_PATH = ORGANIZATION_BASE_PATH
            + "getAllRoles/{organization}";
    public static final String GET_ROLE_PERMISSION_PATH = ORGANIZATION_BASE_PATH
            + "getRolePermission";
    public static final String UPDATE_ROLE_PERMISSION_PATH = ORGANIZATION_BASE_PATH
            + "updateRolePermission";
    public static final String SAVE_ROLE_PATH = ORGANIZATION_BASE_PATH
            + "saveRole";

    /**
     * ACCOUNTS PATH
     */
    private static final String ACCOUNTS_PATH = "/accounts/";
    public static final String GET_ALL_ORGANIZATION_ACCOUNTS_PATH = ACCOUNTS_PATH
            + "getAllOrganizationAccount/{organization}";
    public static final String GET_ALL_ACCOUNTS_PATH = ACCOUNTS_PATH + "getAllAccounts";
    public static final String GET_USER_DETAIL_BY_ID_PATH = ACCOUNTS_PATH
            + "getUserDetail/{userID}";
    private static final String GET_USER_DETAIL_PATH = ACCOUNTS_PATH
            + "getUserDetail/";
    public static final String GET_USER_DETAIL_BY_USERNAME_PATH = GET_USER_DETAIL_PATH
            + "getUserDetailByUsername";
    public static final String GET_USER_DETAIL_BY_USERNAME_AND_PASSWORD_PATH = GET_USER_DETAIL_PATH
            + "getUserDetailByUsernameAndPassword";
    public static final String GET_ACCOUNTS_COUNT_PATH = ACCOUNTS_PATH
            + "getAccountsCount";
    public static final String GET_SHORT_USER_BY_ID_PATH = ACCOUNTS_PATH
            + "getShortUser/{userID}";
    public static final String GET_SHORT_USER_BY_USERNAME_PATH = ACCOUNTS_PATH
            + "getShortUserByUsername";
    public static final String INSERT_ACCOUNT_PATH = ACCOUNTS_PATH + "insertAccount";
    public static final String UPDATE_USER_PATH = ACCOUNTS_PATH + "updateUser";
    public static final String DELETE_ACCOUNT_PATH = ACCOUNTS_PATH
            + "deleteAccount";
    public static final String SEARCH_USERS_PATH = ACCOUNTS_PATH + "searchUsers";
    public static final String SEARCH_ENABLED_USERS_PATH = ACCOUNTS_PATH + "searchEnabledUsers";
    public static final String GET_USERS_COUNT_PATH = ACCOUNTS_PATH
            + "getUsersCount";
    public static final String FORCE_TEMPORARY_ACCOUNT_PATH = ACCOUNTS_PATH
            + "forceTemporaryAccount";
    public static final String FORCE_EXPIRED_TEMPORARY_ACCOUNT_PATH = ACCOUNTS_PATH
            + "forceExpiredTemporaryAccount";
    public static final String GET_ACCOUNT_PERMISSIONS_PATH = ACCOUNTS_PATH
            + "getAccountPermission/{accountID}";

    /**
     * AUTHORITIES PATH
     */
    private static final String AUTHORITIES_PATH = "/authorities/";
    public static final String GET_AUTHORITIES_BY_ACCOUNT_NATURAL_ID = AUTHORITIES_PATH
            + "getAuthoritiesByAccountNaturalID/{accountNaturalID}";
    public static final String GET_AUTHORITIES_PATH = AUTHORITIES_PATH
            + "getAuthorities/{accountID}";

    /**
     * PROJECTS PATH
     */
    private static final String PROJECTS_PATH = "/projects/";
    public static final String INSERT_PROJECT_PATH = PROJECTS_PATH
            + "insertProject";
    public static final String UPDATE_PROJECT_PATH = PROJECTS_PATH
            + "updateProject";
    public static final String GET_PROJECT_DETAIL_PATH = PROJECTS_PATH
            + "getProjectDetail/{projectID}";
    public static final String EXPORT_PROJECT_PATH = PROJECTS_PATH
            + "exportProject/{projectID}";
    public static final String IMPORT_PROJECT_PATH = PROJECTS_PATH
            + "importProject";
    public static final String DELETE_PROJECT_PATH = PROJECTS_PATH
            + "deleteProject/{projectID}";
    public static final String GET_DEFAULT_PROJECT_PATH = PROJECTS_PATH
            + "getDefaultProject/{accountID}";
    public static final String GET_DEFAULT_PROJECT_DTO_PATH = PROJECTS_PATH
            + "getDefaultProjectDTO/{accountID}";
    public static final String UPDATE_DEFAULT_PROJECT_PATH = PROJECTS_PATH
            + "updateDefaultProject";
    public static final String SAVE_ACCOUNT_PROJECT_PROPERTIES_PATH = PROJECTS_PATH
            + "saveAccountProjectProperties";
    public static final String SAVE_PROJECT_PATH = PROJECTS_PATH
            + "saveProject";
    public static final String CLONE_PROJECT_PATH = PROJECTS_PATH
            + "cloneProject";
    public static final String SET_PROJECT_SHARED_PATH = PROJECTS_PATH
            + "setProjectShared";
    public static final String FIND_INTERNAL_PUBLIC_PROJECTS = PROJECTS_PATH + "findInternalPublicProjects";
    public static final String FIND_EXTERNAL_PUBLIC_PROJECTS = PROJECTS_PATH + "findExternalPublicProjects";

    /**
     * ACCOUNT PROJECTS PATH *
     */
    private static final String ACCOUNT_PROJECTS_PATH = "/accountprojects/";
    public static final String GET_ACCOUNT_PROJECTS_BY_ACCOUNT_ID = ACCOUNT_PROJECTS_PATH
            + "getAccountProjectsByAccountID/{accountID}";
    public static final String INSERT_ACCOUNT_PROJECT_PATH = ACCOUNT_PROJECTS_PATH
            + "insertAccountProject";
    public static final String GET_ACCOUNTS_BY_PROJECT_ID_PATH = ACCOUNT_PROJECTS_PATH
            + "getAccountsByProject/{projectID}";
    public static final String GET_ACCOUNTS_TO_SHARE_BY_PROJECT_ID_PATH = ACCOUNT_PROJECTS_PATH
            + "getAccountsToShare/{projectID}";
    public static final String GET_PROJECT_OWNER_PATH = ACCOUNT_PROJECTS_PATH
            + "getProjectOwner/{projectID}";
    public static final String SET_PROJECT_OWNER_PATH = ACCOUNT_PROJECTS_PATH
            + "setProjectOwner";
    public static final String UPDATE_ACCOUNTS_PROJECT_SHARING_PATH = ACCOUNT_PROJECTS_PATH
            + "updateAccountsProjectSharing";
    public static final String UPDATE_ACCOUNT_PROJECT_PATH = ACCOUNT_PROJECTS_PATH
            + "updateAccountProject";
    public static final String DELETE_ACCOUNT_PROJECT_PATH = ACCOUNT_PROJECTS_PATH
            + "deleteAccountProject/{accountProjectID}";
    public static final String GET_ACCOUNT_PROJECT_PATH = ACCOUNT_PROJECTS_PATH
            + "getAccountProject/{accountProjectID}";
    public static final String GET_ACCOUNT_PROJECTS_BY_PROJECT_ID_PATH = ACCOUNT_PROJECTS_PATH
            + "getAccountProjectsByProjectID/{projectID}";
    public static final String GET_ACCOUNT_PROJECT_BY_ACCOUNT_AND_PROJECT_IDS_PATH = ACCOUNT_PROJECTS_PATH
            + "getAccountProjectByAccountAndProjectIDs/{accountID}/{projectID}";
    public static final String GET_ACCOUNT_PROJECTS_COUNT_PATH = ACCOUNT_PROJECTS_PATH
            + "getAccountProjectsCount";
    public static final String GET_DEFAULT_ACCOUNT_PROJECT_PATH = ACCOUNT_PROJECTS_PATH
            + "getDefaultAccountProject/{accountID}";
    public static final String SEARCH_ACCOUNT_PROJECTS_PATH = ACCOUNT_PROJECTS_PATH
            + "searchAccountProjects";

    /**
     * FOLDERS PATH
     */
    private static final String FOLDERS_PATH = "/folders/";
    public static final String INSERT_FOLDER_PATH = FOLDERS_PATH
            + "insertFolder";
    public static final String UPDATE_FOLDER_PATH = FOLDERS_PATH
            + "updateFolder";
    public static final String DELETE_FOLDER_PATH = FOLDERS_PATH
            + "deleteFolder";
    public static final String GET_CHILDREN_FOLDERS_PATH = FOLDERS_PATH
            + "getChildrenFolders/{folderID}";
    public static final String SAVE_ADDED_FOLDER_AND_TREE_MODICATIONS_PATH = FOLDERS_PATH
            + "saveAddedFolderAndTreeModifications";
    public static final String SAVE_DELETED_FOLDER_AND_TREE_MODIFICATIONS_PATH = FOLDERS_PATH
            + "saveDeletedFolderAndTreeModifications";
    public static final String SAVE_DD_FOLDER_AND_TREE_MODIFICATIONS_PATH = FOLDERS_PATH
            + "saveDragAndDropFolderAndTreeModifications";
    public static final String GET_FOLDER_DETAIL_PATH = FOLDERS_PATH
            + "getFolderDetail/{folderID}";
    public static final String GET_SHORT_FOLDER_PATH = FOLDERS_PATH
            + "getShortFolder/{folderID}";
    public static final String GET_SHORT_PROJECT_PATH = FOLDERS_PATH
            + "getShortProject/{projectID}";
    public static final String GET_NUMBER_OF_ELEMENTS_PROJECT_PATH = FOLDERS_PATH
            + "getNumberOfElementsProject/{projectID}";
    public static final String GET_PROJECT_WITH_ROOT_FOLDERS_PATH = FOLDERS_PATH
            + "getProjectWithRootFolders/{projectID}/{accountID}";
    public static final String GET_PROJECT_WITH_EXPANDED_FOLDERS_PATH = FOLDERS_PATH
            + "getProjectWithExpandedFolders/{projectID}/{accountID}";
    public static final String GET_CHILDREN_ELEMENTS_PATH = FOLDERS_PATH
            + "getChildrenElements/{folderID}";
    public static final String SAVE_FOLDER_PROPERTIES_PATH = FOLDERS_PATH
            + "saveFolderProperties";

    /**
     * LAYERS PATH
     */
    private static final String LAYERS_PATH = "/layers/";
    public static final String INSERT_LAYER_PATH = LAYERS_PATH + "insertLayer";
    public static final String GET_RASTER_LAYER_PATH = LAYERS_PATH
            + "getRasterLayer/{layerID}";
    public static final String GET_VECTOR_LAYER_PATH = LAYERS_PATH
            + "getVectorLayer/{layerID}";
    public static final String ADD_LAYERS_AND_TREE_MODIFICATIONS_PATH = LAYERS_PATH
            + "addLayersAndTreeModifications";
    public static final String ADD_LAYER_AND_TREE_MODIFICATIONS_PATH = LAYERS_PATH
            + "saveAddedLayerAndTreeModifications";
    public static final String DELETE_LAYER_AND_TREE_MODIFICATIONS_PATH = LAYERS_PATH
            + "saveDeletedLayerAndTreeModifications";
    public static final String SAVE_CHECK_STATUS_LAYER_AND_TREE_MODIFICATION_PATH = LAYERS_PATH
            + "saveCheckStatusLayerAndTreeModifications";
    public static final String SAVE_DD_LAYER_AND_TREE_MODIFICATIONS_PATH = LAYERS_PATH
            + "saveDragAndDropLayerAndTreeModifications";
    public static final String SAVE_LAYERS_PROPERTIES_PATH = LAYERS_PATH
            + "saveLayerProperties";
    public static final String GET_SHORT_LAYER_PATH = LAYERS_PATH
            + "getShortLayer/{layerID}";
    public static final String UPDATE_RASTER_LAYER_PARH = LAYERS_PATH
            + "updateRasterLayer";
    public static final String UPDATE_VECTOR_LAYER_PATH = LAYERS_PATH
            + "updateVectorLayer";
    public static final String DELETE_LAYER_PATH = LAYERS_PATH + "deleteLayer";
    public static final String GET_LAYERS_PATH = LAYERS_PATH
            + "getLayers/{projectID}";
    public static final String GET_FIRST_LEVEL_LAYERS_PATH = LAYERS_PATH
            + "getFirstLevelLayers/{projectID}";
    public static final String GET_LAYER_BBOX_PATH = LAYERS_PATH
            + "getBBox/{layerID}";
    public static final String GET_LAYER_INFO_PATH = LAYERS_PATH
            + "getLayerInfo/{layerID}";
    public static final String GET_LAYER_TYPE_PATH = LAYERS_PATH
            + "getLayerType/{layerID}";
    public static final String GET_LAYERS_DATA_SOURCE_BY_PROJECT_ID_PATH = LAYERS_PATH
            + "getLayersDataSourceByProjectID/{projectID}";

    /**
     * MESSAGES PATH
     */
    private static final String MESSAGES_PATH = "/messages/";
    public static final String INSERT_MESSAGE_PATH = MESSAGES_PATH
            + "insertMessage";
    public static final String GET_MESSAGE_DETAIL_PATH = MESSAGES_PATH
            + "getMessageDetail/{messageID}";
    public static final String INSERT_MULTI_MESSAGE_PATH = MESSAGES_PATH
            + "insertMultiMessage";
    public static final String GET_ALL_MESSAGES_BY_RECIPIENT_PATH = MESSAGES_PATH
            + "getAllMessagesByRecipient/{recipientID}";
    public static final String DELETE_MESSAGE_PATH = MESSAGES_PATH
            + "deleteMessage/{messageID}";
    public static final String MARK_MESSAGE_AS_READ_PATH = MESSAGES_PATH
            + "markMessageAsRead";
    public static final String GET_UNREAD_MESSAGES_BY_RECIPIENT_PATH = MESSAGES_PATH
            + "getUnreadMessagesByRecipient/{recipientID}";
    public static final String MARK_ALL_MESSAGES_AS_READ_BY_RECIPIENT_PATH = MESSAGES_PATH
            + "markAllMessagesAsReadByRecipient";
    public static final String MARK_MESSAGES_AS_READ_BY_DATE_PATH = MESSAGES_PATH
            + "markMessagesAsReadByDate";

    /**
     * VIEWPORTS PATH
     */
    private static final String VIEWPORTS_PATH = "/viewports/";
    public static final String GET_DEFAULT_VIEWPORT_PATH = VIEWPORTS_PATH
            + "getDefaultViewport/{accountProjectID}";
    public static final String GET_ACCOUNT_PROJECT_VIEWPORTS_PATH = VIEWPORTS_PATH
            + "getAccountProjectViewports/{accountProjectID}";
    public static final String INSERT_VIEWPORT_PATH = VIEWPORTS_PATH
            + "insertViewport";
    public static final String UPDATE_VIEWPORT_PATH = VIEWPORTS_PATH
            + "updateViewport";
    public static final String GET_VIEWPORT_BY_ID_PATH = VIEWPORTS_PATH
            + "getViewportById";
    public static final String DELETE_VIEWPORT_PATH = VIEWPORTS_PATH
            + "deleteViewport";
    public static final String SAVE_OR_UPDATE_VIEWPORT_LIST_PATH = VIEWPORTS_PATH
            + "saveOrUpdateViewportList";
    public static final String REPLACE_VIEWPORT_LIST_PATH = VIEWPORTS_PATH
            + "replaceViewportList";

    /**
     * SERVERS PATH
     */
    private static final String SERVERS_PATH = "/servers/";
    public static final String INSERT_SERVER_PATH = SERVERS_PATH
            + "insertServer";
    public static final String UPDATE_SERVER_PATH = SERVERS_PATH
            + "updateServer";
    public static final String DELETE_SERVER_PATH = SERVERS_PATH
            + "deleteServer";
    public static final String GET_ALL_SERVERS_PATH = SERVERS_PATH
            + "getAllServers/{organizazionName}";
    public static final String GET_SERVER_DETAIL_PATH = SERVERS_PATH
            + "getServerDetail/{serverID}";
    public static final String GET_SHORT_SERVER_PATH = SERVERS_PATH
            + "getShortServer";
    public static final String GET_SERVER_DETAIL_BY_URL_PATH = SERVERS_PATH
            + "getServerDetailByUrl";
    public static final String SAVE_SERVER_PATH = SERVERS_PATH + "saveServer";

    /**
     * GEOCODING
     */
    public static final String SEARCH_ADDRESS_PATH = "/searchAddress";

    /**
     * WMS CAPABILITIES PATH
     */
    public static final String WMS_SERVICE_RS_PATH = "/jsonWMS";
    public static final String GET_WMS_SERVER_BY_URL = "/getServerByUrl";
    public static final String GET_WMS_CAPABILITIES = "/getCapabilities";
    public static final String GET_LAYER_TYPE = "/getLayerType";
    public static final String WMS_GET_FEATURE_INFO_PATH = "/wmsGetFeatureInfo";

    private GPServiceRSPathConfig() {
    }

}
