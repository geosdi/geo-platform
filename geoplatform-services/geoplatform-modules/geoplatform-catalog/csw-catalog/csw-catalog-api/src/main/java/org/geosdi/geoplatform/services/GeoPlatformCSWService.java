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

import javax.jws.WebParam;
import javax.jws.WebService;

import org.codehaus.jra.Delete;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.CSWResourceNotFoundFault;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 * 
 * 
 * Public interface to define the service operations mapped via REST
 * using CXF framework
 */
@WebService(name = "GeoPlatformCSWService",
            targetNamespace = "http://csw.services.geo-platform.org/")
public interface GeoPlatformCSWService {

    //<editor-fold defaultstate="collapsed" desc="CSW Server">
    // ==========================================================================
    // === CSW Server
    // ==========================================================================
    @Put
    @HttpResource(location = "/csw/server")
    Long insertCSWServer(@WebParam(name = "cswServer") GeoPlatformServer cswServer);

    @Delete
    @HttpResource(location = "/csw/server/{idServer}")
    boolean deleteCSWServer(@WebParam(name = "cswServerId") Long cswServerId)
            throws CSWResourceNotFoundFault;

//    @Get
//    @HttpResource(location = "/csw/servers")
//    @WebResult(name = "cswServer")
//    List<ServerDTO> getAllCSWServers();
//
//    @Get
//    @HttpResource(location = "/csw/server/{cswServerId}")
//    @WebResult(name = "cswServer")
//    GeoPlatformServer getCSWServerDetail(@WebParam(name = "cswServerId") Long cswServerId)
//            throws ResourceNotFoundFault;
//
//    @Get
//    @HttpResource(location = "/csw/server/{serverUrl}")
//    @WebResult(name = "Servers")
//    ServerDTO getShortServer(@WebParam(name = "serverUrl") String serverUrl)
//            throws ResourceNotFoundFault;
//
//    @Get
//    @HttpResource(location = "/csw/server")
//    @WebResult(name = "Server")
//    GeoPlatformServer getServerDetailByUrl(
//            @WebParam(name = "serverUrl") String serverUrl)
//            throws ResourceNotFoundFault;
//
//    @Get
//    @HttpResource(location = "/wms/capabilities/{id}")
//    @WebResult(name = "Capabilities")
//    ServerDTO getCapabilities(
//            @WebParam(name = "request") RequestByID request,
//            @WebParam(name = "token") String token)
//            throws ResourceNotFoundFault;
//
//    @Post
//    @HttpResource(location = "/csw/server")
//    ServerDTO saveServer(@WebParam(name = "id") Long id,
//                         @WebParam(name = "aliasServerName") String aliasServerName,
//                         @WebParam(name = "serverUrl") String serverUrl)
//            throws IllegalParameterFault, ResourceNotFoundFault;
    //</editor-fold>
}
