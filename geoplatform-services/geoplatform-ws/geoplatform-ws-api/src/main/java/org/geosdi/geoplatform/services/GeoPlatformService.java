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
import javax.xml.ws.soap.SOAPFaultException;
import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;

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
import org.geosdi.geoplatform.responce.ShortRasterPropertiesDTO;
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
    long insertUser(@WebParam(name = "User") GPUser user)
            throws IllegalParameterFault;

    @Post
    @HttpResource(location = "/users")
    long updateUser(@WebParam(name = "User") GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/users/{id}")
    boolean deleteUser(RequestById request) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Get
    @HttpResource(location = "/users/{id}")
    @WebResult(name = "User")
    UserDTO getShortUser(RequestById request) throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/users/{id}")
    @WebResult(name = "User")
    GPUser getUserDetail(RequestById request) throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    UserDTO getShortUserByName(SearchRequest username) throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    GPUser getUserDetailByName(SearchRequest username) throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    GPUser getUserDetailByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundFault, SOAPFaultException;

    @Get
    @HttpResource(location = "/users/search/{num}/{page}/{nameLike}")
    @WebResult(name = "User")
    List<UserDTO> searchUsers(PaginatedSearchRequest searchRequest);

    @Get
    @HttpResource(location = "/users")
    @WebResult(name = "User")
    List<UserDTO> getUsers();

    @Get
    @HttpResource(location = "/users/count/{nameLike}")
    @WebResult(name = "count")
    long getUsersCount(SearchRequest searchRequest);

    @Get
    @HttpResource(location = "/users/{id}/authorities")
    @WebResult(name = "Authority")
    List<String> getUserAuthorities(@WebParam(name = "userId") long userId)
            throws ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    @Put
    @HttpResource(location = "/folder")
    long insertFolder(@WebParam(name = "Folder") GPFolder folder);

    @Post
    @HttpResource(location = "/folder")
    long updateFolder(@WebParam(name = "Folder") GPFolder folder)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/folder/{folderId}")
    long renameFolder(@WebParam(name = "folderId") long folderId,
            @WebParam(name = "folderName") String folderName)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/folders/{id}")
    boolean deleteFolder(RequestById request) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Put
    @HttpResource(location = "/folder/{descendantsMap}")
    long saveAddedFolderAndTreeModifications(
            @WebParam(name = "folder") GPFolder folder,
            @WebParam(name = "descendantsMap") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Delete
    @HttpResource(location = "/folder/{id}/{descendantsMap}")
    boolean saveDeletedFolderAndTreeModifications(@WebParam(name = "id") long id,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/folder/{folderId}")
    boolean saveCheckStatusFolderAndTreeModifications(
            @WebParam(name = "folderId") long folderId,
            @WebParam(name = "isChecked") boolean isChecked)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{idElementMoved}")
    boolean saveDragAndDropFolderAndTreeModifications(
            @WebParam(name = "username") String username,
            @WebParam(name = "idElementMoved") long idElementMoved,
            @WebParam(name = "idNewParent") long idNewParent,
            @WebParam(name = "newPosition") int newPseudoPosition,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/folders/{id}")
    @WebResult(name = "Folder")
    FolderDTO getShortFolder(RequestById request) throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/folders/{id}")
    @WebResult(name = "Folder")
    GPFolder getFolderDetail(RequestById request) throws ResourceNotFoundFault;

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
    @HttpResource(location = "/folders/user/{folderId}/{num}/{page}")
    @WebResult(name = "Folder")
    List<FolderDTO> getChildrenFolders(
            @WebParam(name = "folderId") long folderId,
            @WebParam(name = "num") int num,
            @WebParam(name = "page") int page);

    /**
     * @param folderId 
     * @return Children folders.
     */
    @Get
    @HttpResource(location = "/folders/user/{folderId}")
    @WebResult(name = "Folder")
    List<FolderDTO> getChildrenFoldersByFolderId(@WebParam(name = "folderId") long folderId);

    /**
     * @param folderId 
     * @return Children elements (folder and layer).
     */
    @Get
    @HttpResource(location = "/folders/{folderId}")
    @WebResult(name = "ChildrenElement")
    TreeFolderElements getChildrenElements(@WebParam(name = "folderId") long folderId);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / User">
    // ==========================================================================
    // === Folder / User
    // ==========================================================================
    @Post
    @HttpResource(location = "/folder/{id}/shared")
    void setFolderShared(RequestById request) throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/folder/{folderId}/owner/{userId}")
    boolean setFolderOwner(RequestByUserFolder request)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/folder/{folderId}/forceowner/{userId}")
    void forceFolderOwner(RequestByUserFolder request)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/users/{id}/folder/{num}/{page}")
    @WebResult(name = "Folder")
    List<FolderDTO> getUserFoldersByRequest(RequestById request);

    @Get
    @HttpResource(location = "/users/{userId}/folder")
    @WebResult(name = "Folder")
    List<FolderDTO> getUserFoldersByUserId(@WebParam(name = "userId") long userId);

    /**
     * @return Owned and shared Folders visible to a given user.
     */
    @Get
    //@HttpResource(location = "/users/{userId}/folder/{num}/{page}")
    @WebResult(name = "Folder")
    List<FolderDTO> getAllUserFolders(
            @WebParam(name = "userId") long userId,
            @WebParam(name = "num") int num,
            @WebParam(name = "page") int page);

    /**
     * @return Owned and shared Folders visible to a given user.
     */
    @Get
    //@HttpResource(location = "/users/{userId}/folder/{num}/{page}")
    @WebResult(name = "Folder")
    List<FolderDTO> getAllUserFoldersByUserId(@WebParam(name = "userId") long userId);

    @Get
    @HttpResource(location = "/folders/user/{id}/count")
    @WebResult(name = "count")
    long getUserFoldersCount(RequestById request);

    /**
     * @return Count Owned and shared Folders visible to a given user.
     */
    @Get
    @HttpResource(location = "/folders/{userId}")
    @WebResult(name = "count")
    int getAllUserFoldersCount(@WebParam(name = "userId") long userId);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Layer / Style">
    // ==========================================================================
    // === Layer / Style
    // ==========================================================================
    @Put
    @HttpResource(location = "/layer")
    long insertLayer(@WebParam(name = "Layer") GPLayer layer);

    @Post
    @HttpResource(location = "/layer")
    long updateRasterLayer(@WebParam(name = "Layer") GPRasterLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Post
    @HttpResource(location = "/layer")
    long updateVectorLayer(@WebParam(name = "Layer") GPVectorLayer layer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/layers/{id}")
    boolean deleteLayer(RequestById request) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Put
    @HttpResource(location = "/layer/{descendantsMap}")
    long saveAddedLayerAndTreeModifications(
            @WebParam(name = "username") String username,
            @WebParam(name = "layer") GPLayer layer,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/layers/{descendantsMap}")
    ArrayList<Long> saveAddedLayersAndTreeModifications(
            @WebParam(name = "username") String username,
            @WebParam(name = "layers") List<GPLayer> layers,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/layers/{id}/{descendantsMap}")
    boolean saveDeletedLayerAndTreeModifications(@WebParam(name = "id") long id,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Put
    @HttpResource(location = "/layer/{layerId}")
    boolean saveCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerId") long layerId,
            @WebParam(name = "isChecked") boolean isChecked)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{layerId}")
    boolean fixCheckStatusLayerAndTreeModifications(
            @WebParam(name = "layerId") long layerId,
            @WebParam(name = "oldFolderId") long oldFolderId,
            @WebParam(name = "newFolderId") long newFolderId)
            throws ResourceNotFoundFault;

    @Put
    @HttpResource(location = "/layer/{idElementMoved}")
    boolean saveDragAndDropLayerAndTreeModifications(
            @WebParam(name = "username") String username,
            @WebParam(name = "idElementMoved") long idElementMoved,
            @WebParam(name = "idNewParent") long idNewParent,
            @WebParam(name = "newPosition") int newPosition,
            @WebParam(name = "descendantsMapData") GPWebServiceMapData descendantsMapData)
            throws ResourceNotFoundFault;

    @Post
    @HttpResource(location = "/layer")
    boolean saveLayerProperties(@WebParam(name = "username") String username,
            @WebParam(name = "layerProperties") ShortRasterPropertiesDTO layerProperties)
            throws ResourceNotFoundFault, IllegalParameterFault;

    /**
     * @return a raster layer.
     */
    @Get
    @WebResult(name = "RasterLayer")
    GPRasterLayer getRasterLayer(@WebParam(name = "GPRasterLayer") long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return a vector layer.
     */
    @Get
    @WebResult(name = "VectorLayer")
    GPVectorLayer getVectorLayer(@WebParam(name = "GPVectorLayer") long layerId)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/layers")
    @WebResult(name = "Layer")
    List<ShortLayerDTO> getLayers();

//    /**
//     * @return Styles of a layer.
//     */
//    @Get
//    @WebResult(name = "LayerStyles")
//    List<StyleDTO> getLayerStyles(@WebParam(name = "LayerId") long layerId);
    /**
     * @return a short layer.
     */
    @Get
    @WebResult(name = "ShortLayerDTO")
    ShortLayerDTO getShortLayer(@WebParam(name = "GPLayerId") long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return BBox of a layer.
     */
    @Get
    @WebResult(name = "BBox")
    GPBBox getBBox(@WebParam(name = "LayerId") long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return LayerInfo of a raster layer.
     */
    @Get
    @WebResult(name = "LayerInfo")
    GPLayerInfo getLayerInfo(@WebParam(name = "LayerId") long layerId)
            throws ResourceNotFoundFault;

//    /**
//     * @return Geometry of a vector layer.
//     */
//    @Get
//    @WebResult(name = "Geometry")
//    Point getGeometry(@WebParam(name = "LayerId") long layerId) throws ResourceNotFoundFault;
    /**
     * @return layer Type.
     */
    @Get
    @WebResult(name = "LayerType")
    GPLayerType getLayerType(@WebParam(name = "LayerId") long layerId)
            throws ResourceNotFoundFault;

    /**
     * @return layer data source of given owner.
     */
    @Get
    @WebResult(name = "LayerDataSources")
    ArrayList<String> getLayersDataSourceByOwner(
            @WebParam(name = "userName") String userName)
            throws ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OWS">
    // ==========================================================================
    // === OWS
    // ==========================================================================
    @Put
    @HttpResource(location = "/server")
    long insertServer(@WebParam(name = "Server") GeoPlatformServer server);

    @Post
    @HttpResource(location = "/server")
    long updateServer(@WebParam(name = "Server") GeoPlatformServer server)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Delete
    @HttpResource(location = "/server/{idServer}")
    boolean deleteServer(@WebParam(name = "idServer") long idServer)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Get
    @HttpResource(location = "/servers")
    @WebResult(name = "Server")
    List<ServerDTO> getAllServers();

    @Get
    @HttpResource(location = "/server/{idServer}")
    @WebResult(name = "Server")
    GeoPlatformServer getServerDetail(@WebParam(name = "idServer") long idServer)
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
            @WebParam(name = "userId") long userId)
            throws ResourceNotFoundFault;
    //</editor-fold>
}
