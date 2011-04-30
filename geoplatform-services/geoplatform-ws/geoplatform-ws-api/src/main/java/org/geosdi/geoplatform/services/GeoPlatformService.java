//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.RequestByUserFolder;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderList;
import org.geosdi.geoplatform.responce.ElementList;
import org.geosdi.geoplatform.responce.LayerList;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.TreeFolderElements;
import org.geosdi.geoplatform.responce.UserList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 *         Public interface to define the service operations mapped via REST
 *         using CXT framework
 */
@WebService(name = "GeoPlatformService", targetNamespace = "http://services.geo-platform.org/")
public interface GeoPlatformService {

    // ==========================================================================
    // === Users
    // ==========================================================================
    @Put
    @HttpResource(location = "/users")
    long insertUser(@WebParam(name = "User") GPUser user);

    @Post
    @HttpResource(location = "/users")
    long updateUser(@WebParam(name = "User") GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Get
    @HttpResource(location = "/users/{id}")
    @WebResult(name = "User")
    GPUser getUser(RequestById request) throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "User")
    GPUser getUserByName(SearchRequest username) throws ResourceNotFoundFault;

    @Delete
    @HttpResource(location = "/users/{id}")
    boolean deleteUser(RequestById request) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Get
    @HttpResource(location = "/users")
    @WebResult(name = "Users")
    UserList getUsers();

    @Get
    @HttpResource(location = "/users/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Users")
    UserList searchUsers(PaginatedSearchRequest searchRequest);

    @Get
    @HttpResource(location = "/users/count/{nameLike}")
    @WebResult(name = "count")
    long getUsersCount(SearchRequest searchRequest);

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

    @Get
    @HttpResource(location = "/folders/{id}")
    @WebResult(name = "Folder")
    GPFolder getFolder(RequestById request) throws ResourceNotFoundFault;

    @Delete
    @HttpResource(location = "/folders/{id}")
    boolean deleteFolder(RequestById request) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Get
    @HttpResource(location = "/folders")
    @WebResult(name = "Folders")
    FolderList getFolders();

    @Get
    @HttpResource(location = "/folders/search/{num}/{page}/{nameLike}")
    @WebResult(name = "Folders")
    FolderList searchFolders(PaginatedSearchRequest searchRequest);

    @Get
    @HttpResource(location = "/folders/count/{nameLike}")
    @WebResult(name = "count")
    long getFoldersCount(SearchRequest searchRequest);

    @Get
    @HttpResource(location = "/folders/user/{id}/count")
    @WebResult(name = "count")
    long getUserFoldersCount(RequestById request);

    // ==========================================================================
    // === Folder / User
    // ==========================================================================
    @Get
    @HttpResource(location = "/users/{id}/folder/{num}/{page}")
    @WebResult(name = "FolderList")
    List<GPFolder> getUserFolders(RequestById request);

    /**
     * @return Owned and shared Folders visible to a given user.
     */
    FolderList getAllUserFolders(long userId, int num, int page);

    /**
     * @return Children folders.
     */
    FolderList getChildrenFolders(long folderId, int num, int page);

    /**
     * @return Children elements (folder and layer).
     */
    TreeFolderElements getChildrenElements(long folderId);

    /**
     * @return Count Owned and shared Folders visible to a given user.
     */
    int getAllUserFoldersCount(long userId);

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

    // ==========================================================================
    // === OWS
    // ==========================================================================
    @Get
    @HttpResource(location = "/wms/capabilities/{id}")
    @WebResult(name = "Capabilities")
    LayerList getCapabilities(RequestById request) throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/servers/{serverUrl}")
    @WebResult(name = "Servers")
    ServerDTO getServer(@WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;
}
