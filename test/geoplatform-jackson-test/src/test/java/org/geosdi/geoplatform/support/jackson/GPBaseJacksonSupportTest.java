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
package org.geosdi.geoplatform.support.jackson;

import java.text.SimpleDateFormat;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseJacksonSupportTest {
    
    protected static final Logger logger = LoggerFactory.getLogger(
            GPBaseJacksonSupportTest.class);
    protected static GPJacksonSupport jacksonSupport;
    
    static final String ACCOUNTS_DATA_JSON = "accounts-data.json";
    static final String PROJECTS_DATA_JSON = "projects-data.json";
    static final String PROJECT_DATA_JSON = "project-data.json";
    static final String GET_ALL_PROJECTS_DATA_JSON = "GetAllProject-data.json";
    static final String INSERT_ACCOUNT_REQUEST_DATA_JSON = "InsertAccountRequest-data.json";
    static final String ACCOUNT_PROJECT_DATA_JSON = "accountproject-data.json";
    static final String GET_AUTHORITIES_RESPONSE_DATA_JSON = "GetAuthoritiesResponse-data.json";
    static final String GET_AUTHORITY_RESPONSE_DATA_JSON = "GetAuthorityResponse-data.json";
    static final String SEARCH_USERS_RESPONSE_DATA_JSON = "SearchUsersResponse-data.json";
    static final String FOLDER_DATA_JSON = "folder-data.json";
    static final String INSERT_FOLDER_REQUEST_DATA_JSON = "InsertFolderRequest-data.json";
    static final String ADD_FOLDER_TREE_MODIFICATION_REQUEST_DATA_JSON = "WSAddFolderAndTreeModificationsRequest-data.json";
    static final String DELETE_FOLDER_TREE_MODIFICATION_REQUEST_DATA_JSON = "WSDeleteFolderAndTreeModifications-data.json";
    static final String GET_CHILDREN_FOLDER_DATA_JSON = "GetChildrenFolder-data.json";
    static final String DD_FOLDER_TREE_MODIFICATION_REQUEST_DATA_JSON = "WSDDFolderAndTreeModificationsRequest-data.json";
    static final String INSERT_LAYER_REQUEST_DATA_JSON = "InsertLayerRequest-data.json";
    static final String RASTER_LAYER_DATA_JSON = "RasterLayer-data.json";
    static final String VECTOR_LAYER_DATA_JSON = "VectorLayer-data.json";
    static final String SHORT_LAYER_DTO_DATA_JSON = "ShortLayerDTO-data.json";
    static final String SHORT_LAYER_DTO_CONTAINER_DATA_JSON = "ShortLayerDTOContainer-data.json";
    static final String TREE_FOLDER_ELEMENTS_STORE_DATA_JSON = "TreeFolderElementsStore-data.json";
    static final String ADD_LAYER_TREE_MODIFICATION_REQUEST_DATA_JSON = "WSAddLayerAndTreeModificationsRequest-data.json";
    static final String DELETE_LAYER_TREE_MODIFICATION_REQUEST_DATA_JSON = "WSDeleteLayerAndTreeModificationsRequest-data.json";
    static final String DD_LAYER_TREE_MODIFICATION_REQUEST_DATA_JSON = "WSDDLayerAndTreeModificationsRequest-data.json";
    static final String GET_BBOX_DATA_JSON = "GetBBox-data.json";
    static final String GET_LAYER_INFO_DATA_JSON = "GetLayerInfo-data.json";
    static final String GET_LAYERS_DATA_SOURCE_DATA_JSON = "GetLayersDataSource-data.json";
    static final String MESSAGE_DATA_JSON = "Message-data.json";
    static final String MESSAGE_DTO_DATA_JSON = "MessageDTO-data.json";
    static final String GET_MESSAGE_RESPONSE_DATA_JSON = "GetMessageResponse-data.json";
    static final String SAVE_ROLE_REQUEST_DATA_JSON = "WSSaveRoleRequest-data.json";
    static final String GUI_COMPONENTS_PERMISSION_DATA_JSON = "GuiComponentsPermissionDataMap-data.json";
    static final String PUT_ROLE_PERMISSION_REQUEST_DATA_JSON = "WSPutRolePermissionRequest-data.json";
    static final String GET_ROLE_RESPONSE_DATA_JSON = "GetRoleReponse-data.json";
    static final String SERVER_DATA_JSON = "Server-data.json";
    static final String SERVER_DTO_DATA_JSON = "ServerDTO-data.json";
    static final String SERVER_DTO_CONTAINER_DATA_JSON = "ServerDTOContainer-data.json";
    
    @BeforeClass
    public static void beforeClass() {
        jacksonSupport = new GPJacksonSupport();
        jacksonSupport.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
    }
    
    protected final void marshall(Object value) throws Exception {
        jacksonSupport.getDefaultMapper().writeValue(System.out, value);
        System.out.print("\n\n");
    }
    
}
