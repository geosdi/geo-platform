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
import javax.annotation.Resource;
import javax.jws.WebService;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.geosdi.geoplatform.services.delegate.CSWDelegate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Transactional // Give atomicity on WS methods
@WebService(
        endpointInterface = "org.geosdi.geoplatform.services.GeoPlatformCSWService")
public class GeoPlatformCSWServiceImpl implements GeoPlatformCSWService {

    @Resource(name = "cswServiceDelegate")
    private CSWDelegate cswServiceDelegate;

    @Override
    public Long insertServerCSW(GeoPlatformServer server) throws IllegalParameterFault {
        return cswServiceDelegate.insertServerCSW(server);
    }

    @Override
    public ServerCSWDTO saveServerCSW(String alias, String serverUrl,
            String organization)
            throws IllegalParameterFault {
        return cswServiceDelegate.saveServerCSW(alias, serverUrl, organization);
    }

    @Override
    public boolean deleteServerCSW(Long serverID) throws ResourceNotFoundFault {
        return cswServiceDelegate.deleteServerCSW(serverID);
    }

    @Override
    public List<ServerCSWDTO> getAllCSWServers(String organizationName)
            throws ResourceNotFoundFault {
        return cswServiceDelegate.getAllCSWServers(organizationName);
    }

    @Override
    public GeoPlatformServer getServerDetailCSW(Long serverID)
            throws ResourceNotFoundFault {
        return cswServiceDelegate.getServerDetailCSW(serverID);
    }

    @Override
    public GeoPlatformServer getServerDetailCSWByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        return cswServiceDelegate.getServerDetailCSWByUrl(serverUrl);
    }

    @Override
    public ServerCSWDTO getShortServerCSW(String serverUrl)
            throws ResourceNotFoundFault {
        return cswServiceDelegate.getShortServerCSW(serverUrl);
    }

    @Override
    public int getCSWServersCount(SearchRequest request, String organization) {
        return cswServiceDelegate.getCSWServersCount(request, organization);
    }

    @Override
    public List<ServerCSWDTO> searchCSWServers(PaginatedSearchRequest request,
            String organization) {
        return cswServiceDelegate.searchCSWServers(request, organization);
    }

    @Override
    public int getRecordsCount(CatalogFinderBean catalogFinder)
            throws Exception {
        return cswServiceDelegate.getRecordsCount(catalogFinder);
    }

    @Override
    public List<SummaryRecordDTO> searchSummaryRecords(int num, int start,
            CatalogFinderBean catalogFinder)
            throws Exception {
        return cswServiceDelegate.searchSummaryRecords(num, start, catalogFinder);
    }

    @Override
    public List<FullRecordDTO> searchFullRecords(int num, int start,
            CatalogFinderBean catalogFinder)
            throws Exception {
        return cswServiceDelegate.searchFullRecords(num, start, catalogFinder);
    }

    @Override
    public String getRecordById(Long serverID, String identifier)
            throws Exception {
        return cswServiceDelegate.getRecordById(serverID, identifier);
    }

}
