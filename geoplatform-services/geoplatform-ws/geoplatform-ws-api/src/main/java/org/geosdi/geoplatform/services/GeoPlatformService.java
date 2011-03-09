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
package org.geosdi.geoplatform.services;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.LayerList;
import org.geosdi.geoplatform.responce.UserList;

/**
 * @author giuseppe
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
	
	@Get
	@HttpResource(location = "/capabilities/{id}")
	@WebResult(name = "Capabilities")
	LayerList getCapabilities(RequestById request) throws ResourceNotFoundFault;

}
