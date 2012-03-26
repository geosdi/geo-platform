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

import java.util.List;
import javax.jws.WebService;
import org.geosdi.geoplatform.core.dao.GPServerDAO;

import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.cswconnector.CatalogGetCapabilitiesBean;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 */
@Transactional // Give atomicity on WS methods
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GeoPlatformCSWService")
public class GeoPlatformCSWServiceImpl implements GeoPlatformCSWService {

    // DAO
    private GPServerDAO serverDao;
    //
    private CatalogGetCapabilitiesBean catalogCapabilitiesBean;
    // Delegate
    private CSWServiceImpl cswServiceDelegate;

    /**
     * @param cswServiceDelegate
     */
    public GeoPlatformCSWServiceImpl() {
        this.cswServiceDelegate = new CSWServiceImpl();
    }

    /**
     * @param serverDao the serverDao to set
     */
    public void setServerDao(GPServerDAO serverDao) {
        this.serverDao = serverDao;
        this.cswServiceDelegate.setServerDao(serverDao);
    }

    /**
     * @param catalogCapabilitiesBean the catalogCapabilitiesBean to set
     */
    public void setCatalogCapabilitiesBean(CatalogGetCapabilitiesBean catalogCapabilitiesBean) {
        this.catalogCapabilitiesBean = catalogCapabilitiesBean;
        this.cswServiceDelegate.setCatalogCapabilitiesBean(catalogCapabilitiesBean);
    }

    @Override
    public Long insertServerCSW(GeoPlatformServer server) {
        return cswServiceDelegate.insertServerCSW(server);
    }

    @Override
    public ServerCSWDTO saveServerCSW(String alias, String serverUrl)
            throws IllegalParameterFault {
        return cswServiceDelegate.saveServerCSW(alias, serverUrl);
    }

    @Override
    public boolean deleteServerCSW(Long serverID) throws ResourceNotFoundFault {
        return cswServiceDelegate.deleteServerCSW(serverID);
    }

    @Override
    public List<ServerCSWDTO> getAllCSWServers() {
        return cswServiceDelegate.getAllCSWServers();
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
    public Long getCSWServersCount(SearchRequest request) {
        return cswServiceDelegate.getCSWServersCount(request);
    }

    @Override
    public List<ServerCSWDTO> searchCSWServers(PaginatedSearchRequest request) {
        return cswServiceDelegate.searchCSWServers(request);
    }

    @Override
    public Long getSummaryRecordsCount(CatalogFinderBean catalogFinder)
            throws IllegalParameterFault, ResourceNotFoundFault {
        return cswServiceDelegate.getSummaryRecordsCount(catalogFinder);
    }

    @Override
    public List<SummaryRecordDTO> searchSummaryRecords(int num, int page,
            CatalogFinderBean catalogFinder)
            throws IllegalParameterFault, ResourceNotFoundFault {
        return cswServiceDelegate.searchSummaryRecords(num, page, catalogFinder);
    }
}
