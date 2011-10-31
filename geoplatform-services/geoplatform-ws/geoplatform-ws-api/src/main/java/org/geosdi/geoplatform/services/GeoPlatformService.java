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
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;

import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
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
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 * Public interface to define the service operations mapped via REST
 * using CXT framework
 */
@WebService(name = "GeoPlatformService", targetNamespace = "http://services.geo-platform.org/")
public interface GeoPlatformService {

    //<editor-fold defaultstate="collapsed" desc="User">
    // ==========================================================================
    // === User
    // ==========================================================================
    @Put
    @HttpResource(location = "/users")
    Long insertUser(@WebParam(name = "User") GPUser user)
            throws IllegalParameterFault;

    @Post
    @HttpResource(location = "/users")
    Long updateUser(@WebParam(name = "User") GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/users/{userId}")
    boolean deleteUser(@WebParam(name = "userId") Long userId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/users/{userId}")
    @WebResult(name = "User")
    UserDTO getShortUser(@WebParam(name = "userId") Long userId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/users/{userId}")
    @WebResult(name = "User")
    GPUser getUserDetail(@WebParam(name = "userId") Long userId)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    UserDTO getShortUserByName(SearchRequest request)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    GPUser getUserDetailByName(SearchRequest request)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    GPUser getUserDetailByUsernameAndPassword(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Get
    @HttpResource(location = "/users/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Users")
    List<UserDTO> searchUsers(@WebParam(name = "userId") Long userId,
            PaginatedSearchRequest searchRequest)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/users")
    @WebResult(name = "User")
    List<UserDTO> getUsers();

    @Get
    @HttpResource(location = "/users/count/{nameLike}")
    @WebResult(name = "count")
    Long getUsersCount(SearchRequest searchRequest);

    @Get
    @HttpResource(location = "/users/{id}/authorities")
    @WebResult(name = "Authorities")
    List<String> getUserAuthorities(@WebParam(name = "userId") Long userId)
            throws ResourceNotFoundFault;

    @HttpResource(location = "/users/{id}/authorities")
    @WebResult(name = "Authorities")
    List<GPAuthority> getUserGPAuthorities(
            @WebParam(name = "username") String username)
            throws ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UserProjects">
    // ==========================================================================
    // === UserProjects
    // ==========================================================================
    @Put
    @HttpResource(location = "/userProject")
    Long insertUserProject(@WebParam(name = "userProject") GPUserProjects userProject)
            throws IllegalParameterFault;

    @Post
    @HttpResource(location = "/userProject")
    Long updateUserProject(@WebParam(name = "userProject") GPUserProjects userProject)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/projects/{userProjectId}")
    boolean deleteUserProject(@WebParam(name = "userProjectId") Long userProjectId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/user-projects/{userProjectId}")
    @WebResult(name = "UserProject")
    GPUserProjects getUserProject(@WebParam(name = "userProjectId") Long userProjectId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/user/{userId}")
    @WebResult(name = "UserProject")
    List<GPUserProjects> getUserProjectsByUserId(@WebParam(name = "userId") Long userId);

    @Get
    @HttpResource(location = "/projects/{projectId}")
    @WebResult(name = "UserProject")
    List<GPUserProjects> getUserProjectsByProjectId(@WebParam(name = "projectId") Long projectId);

    @Get
    @HttpResource(location = "/user/{userId}/projects/{projectId}")
    @WebResult(name = "UserProject")
    GPUserProjects getUserProjectByUserAndProjectId(
            @WebParam(name = "userId") Long userId,
            @WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/user/{userId}")
    @WebResult(name = "UserProjects")
    Long getUserProjectsCount(@WebParam(name = "userId") Long userId,
            SearchRequest request)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/users/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Projects")
    List<ProjectDTO> searchUserProjects(@WebParam(name = "userId") Long userId,
            PaginatedSearchRequest request)
            throws ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================
    @Put
    @HttpResource(location = "/project")
    Long saveProject(
            @WebParam(name = "username") String username,
            @WebParam(name = "project") GPProject project)
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
    @HttpResource(location = "/projects/{projectId}")
    boolean deleteProject(@WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/projects/{projectId}")
    @WebResult(name = "Project")
    GPProject getProjectDetail(@WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/projects/{projectId}")
    @WebResult(name = "Project")
    int getNumberOfElementsProject(@WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/project/{projectId}/shared")
    void setProjectShared(@WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/project/{projectId}/owner/{userId}")
    boolean setProjectOwner(RequestByUserProject request)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/project/{projectId}/forceowner/{userId}")
    void forceProjectOwner(RequestByUserProject request)
            throws ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    @Put
    @HttpResource(location = "/folder")
    @Deprecated
    Long insertFolder(@WebParam(name = "projectId") Long projectId,
            @WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/folder")
    @Deprecated
    Long updateFolder(@WebParam(name = "folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/folders/{folderId}")
    @Deprecated
    boolean deleteFolder(@WebParam(name = "folderId") Long folderId)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/folder/{folderId}")
    Long saveFolderProperties(@WebParam(name = "folderId") Long folderId,
            @WebParam(name = "folderName") String folderName,
            @WebParam(name = "checked") boolean checked)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
//    @HttpResource(location = "/folder/{descendantsMap}")
    Long saveAddedFolderAndTreeModifications(
            @WebParam(name = "projectId") Long projectId,
            @WebParam(name = "parentId") Long parentId,
            @WebParam(name = "folder") GPFolder folder,
            @WebParam(name = "descendantsMap") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
//    @HttpResource(location = "/folder/{id}/{descendantsMap}")
    boolean saveDeletedFolderAndTreeModifications(
            @WebParam(name = "folderId") Long folderId,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/folder/{folderId}")
    boolean saveCheckStatusFolderAndTreeModifications(
            @WebParam(name = "folderId") Long folderId,
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
    @HttpResource(location = "/folders/{folderId}")
    @WebResult(name = "Folder")
    FolderDTO getShortFolder(@WebParam(name = "folderId") Long folderId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/folders/{folderId}")
    @WebResult(name = "Folder")
    GPFolder getFolderDetail(@WebParam(name = "folderId") Long folderId)
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
     * @param folderId 
     * @param num 
     * @param page 
     * @return Children folders.
     */
    @Get
    @HttpResource(location = "/folders/user/{id}/{num}/{page}")
    @WebResult(name = "Folder")
    List<FolderDTO> getChildrenFoldersByRequest(RequestById request);

    /**
     * @param folderId 
     * @return Children folders.
     */
    @Get
    @HttpResource(location = "/folders/user/{folderId}")
    @WebResult(name = "Folder")
    List<FolderDTO> getChildrenFolders(@WebParam(name = "folderId") Long folderId);

    /**
     * @param folderId 
     * @return Children elements (folder and layer).
     */
    @Get
    @HttpResource(location = "/folders/{folderId}")
    @WebResult(name = "ChildrenElement")
    TreeFolderElements getChildrenElements(@WebParam(name = "folderId") Long folderId);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / Project">
    @Get
    //@HttpResource(location = "/projects/{projectId}")
    @WebResult(name = "RootFolders")
    List<FolderDTO> getRootFoldersByProjectId(@WebParam(name = "projectId") Long projectId);

    @Get
    //@HttpResource(location = "/projects/{projectId}")
    @WebResult(name = "Project")
    ProjectDTO exportProject(@WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/project")
    Long importProject(@WebParam(name = "projectDTO") ProjectDTO projectDTO,
            @WebParam(name = "userID") Long userID)
            throws IllegalParameterFault, ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Layer / Style">
    // ==========================================================================
    // === Layer / Style
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
    @HttpResource(location = "/layers/{layerId}")
    boolean deleteLayer(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{descendantsMap}")
    Long saveAddedLayerAndTreeModifications(
            @WebParam(name = "layer") GPLayer layer,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/layers/{descendantsMap}")
    ArrayList<Long> saveAddedLayersAndTreeModifications(
            @WebParam(name = "projectId") Long projectId,
            @WebParam(name = "layers") List<GPLayer> layers,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/layers/{id}/{descendantsMap}")
    boolean saveDeletedLayerAndTreeModifications(
            @WebParam(name = "layerId") Long layerId,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{layerId}")
    boolean saveCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerId") Long layerId,
            @WebParam(name = "checked") boolean checked)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{layerId}")
    boolean fixCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerId") Long layerId,
            @WebParam(name = "oldUserFolderId") Long oldUserFolderId,
            @WebParam(name = "newUserFolderId") Long newUserFolderId)
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
            @WebParam(name = "layerProperties") ShortRasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * @return a raster layer.
     */
    @Get
    @WebResult(name = "RasterLayer")
    GPRasterLayer getRasterLayer(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return a vector layer.
     */
    @Get
    @WebResult(name = "VectorLayer")
    GPVectorLayer getVectorLayer(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/layers")
    @WebResult(name = "Layer")
    List<ShortLayerDTO> getLayers(@WebParam(name = "projectId") Long projectId);

    /**
     * @return Styles of a layer.
     */
    @Get
    @WebResult(name = "LayerStyles")
    List<StyleDTO> getLayerStyles(@WebParam(name = "layerId") Long layerId);

    /**
     * @return a short layer.
     */
    @Get
    @WebResult(name = "ShortLayerDTO")
    ShortLayerDTO getShortLayer(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return BBox of a layer.
     */
    @Get
    @WebResult(name = "BBox")
    GPBBox getBBox(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return LayerInfo of a raster layer.
     */
    @Get
    @WebResult(name = "LayerInfo")
    GPLayerInfo getLayerInfo(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

//    /**
//     * @return Styles of a layer.
//     */
//    @Get
//    @WebResult(name = "LayerStyles")
//    List<StyleDTO> getLayerStyles(@WebParam(name = "layerId") Long layerId);
//    
//    /**
//     * @return Geometry of a vector layer.
//     */
//    @Get
//    @WebResult(name = "Geometry")
//    Point getGeometry(@WebParam(name = "LayerId") Long layerId) throws ResourceNotFoundFault;
//    
    /**
     * @return layer Type.
     */
    @Get
    @WebResult(name = "LayerType")
    GPLayerType getLayerType(@WebParam(name = "layerId") Long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return layer data source of given owner.
     */
    @Get
    @WebResult(name = "LayerDataSources")
    ArrayList<String> getLayersDataSourceByProjectId(
            @WebParam(name = "projectId") Long projectId)
            throws ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OWS">
    // ==========================================================================
    // === OWS
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

    @Get
    @HttpResource(location = "/wms/capabilities/{id}")
    @WebResult(name = "Capabilities")
    ServerDTO getCapabilities(
            @WebParam(name = "request") RequestById request,
            @WebParam(name = "token") String token)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/server")
    ServerDTO saveServer(@WebParam(name = "id") Long id,
            @WebParam(name = "aliasServerName") String aliasServerName,
            @WebParam(name = "serverUrl") String serverUrl)
            throws IllegalParameterFault, ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ACL">
    @Get
    @HttpResource(location = "/user/{userId}")
    @WebResult(name = "GuiComponentsPermissionMapData")
    GuiComponentsPermissionMapData getUserGuiComponentVisible(
            @WebParam(name = "userId") Long userId)
            throws ResourceNotFoundFault;
    //</editor-fold>
}
