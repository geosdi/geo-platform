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

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Delete;
import org.codehaus.jra.Get;
import org.codehaus.jra.Post;
import org.codehaus.jra.Put;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;

/**
 * Public interface to define the service operations mapped via REST using CXF
 * framework.
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@WebService(name = "GeoPlatformCSWService",
            targetNamespace = "http://csw.services.geo-platform.org/")
public interface GeoPlatformCSWService {

    //<editor-fold defaultstate="collapsed" desc="CSW Server">
    // ==========================================================================
    // === CSW Server
    // ==========================================================================
    /**
     * Insert a server without verification (GetCapabilities request).
     *
     * @deprecated only for test purpose, use saveServerCSW method for
     * production.
     */
    @Put
    Long insertServerCSW(@WebParam(name = "server") GeoPlatformServer server)
            throws IllegalParameterFault;

    /**
     * Save a server after execute a GetCapabilities request.
     */
    @Post
    ServerCSWDTO saveServerCSW(
            @WebParam(name = "alias") String alias,
            @WebParam(name = "serverUrl") String serverUrl,
            @WebParam(name = "organization") String organization)
            throws IllegalParameterFault;

    @Delete
    boolean deleteServerCSW(@WebParam(name = "serverID") Long serverID)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "ServerCSW")
    List<ServerCSWDTO> getAllCSWServers(
            @WebParam(name = "organizationName") String organizationName)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "ServerCSW")
    GeoPlatformServer getServerDetailCSW(
            @WebParam(name = "serverID") Long serverID)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "ServerCSW")
    ServerCSWDTO getShortServerCSW(
            @WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "ServerCSW")
    GeoPlatformServer getServerDetailCSWByUrl(
            @WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;

    @Get
    @WebResult(name = "ServersCSWCount")
    int getCSWServersCount(SearchRequest request,
            @WebParam(name = "organization") String organization);

    @Get
    @WebResult(name = "ServersCSW")
    List<ServerCSWDTO> searchCSWServers(PaginatedSearchRequest request,
            @WebParam(name = "organization") String organization);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GetRecords request">
    // ==========================================================================
    // === GetRecords request
    // ==========================================================================
    @Get
    @WebResult(name = "RecordsCount")
    int getRecordsCount(
            @WebParam(name = "CatalogFinderBean") CatalogFinderBean catalogFinder)
            throws Exception;

    @Get
    @WebResult(name = "SummaryRecord")
    List<SummaryRecordDTO> searchSummaryRecords(
            @WebParam(name = "num") int num,
            @WebParam(name = "start") int start,
            @WebParam(name = "CatalogFinderBean") CatalogFinderBean catalogFinder)
            throws Exception;

    @Get
    @WebResult(name = "FullRecord")
    List<FullRecordDTO> searchFullRecords(
            @WebParam(name = "num") int num,
            @WebParam(name = "start") int start,
            @WebParam(name = "CatalogFinderBean") CatalogFinderBean catalogFinder)
            throws Exception;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GetRecordById request">
    // ==========================================================================
    // === GetRecordById request
    // ==========================================================================
    @Get
    @WebResult(name = "Metadata")
    String getRecordById(
            @WebParam(name = "serverID") Long serverID,
            @WebParam(name = "identifier") String identifier)
            throws Exception;
    //</editor-fold>
//    @Get
//    @WebResult(name = "Capabilities")
//    ServerDTO getCapabilities(
//            @WebParam(name = "request") RequestByID request,
//            @WebParam(name = "token") String token)
//            throws ResourceNotFoundFault;
}
