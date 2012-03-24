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
package org.geosdi.geoplatform.gui.server.service.impl;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.server.IGPCatalogFinderService;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.geosdi.geoplatform.services.GeoPlatformCSWService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 */
@Service("gpCatalogFinderService")
public class GPCatalogFinderService implements IGPCatalogFinderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GeoPlatformCSWService geoPlatformCSWClient;

    @Override
    public ArrayList<GPCSWServerBeanModel> getAllCSWServers(
            HttpServletRequest httpServletRequest) {
        List<ServerCSWDTO> serversDTO = geoPlatformCSWClient.getAllCSWServers();
        ArrayList<GPCSWServerBeanModel> servers = new ArrayList<GPCSWServerBeanModel>(
                serversDTO.size());
        for (ServerCSWDTO serverDTO : serversDTO) {
            GPCSWServerBeanModel server = this.convertServerDTO(serverDTO);
            servers.add(server);
        }

        return servers;
    }

    @Override
    public PagingLoadResult<GPCSWServerBeanModel> searchCSWServers(
            PagingLoadConfig config, String searchText, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {

        SearchRequest srq = new SearchRequest(searchText);

        Long serversCount = geoPlatformCSWClient.getCSWServersCount(srq);

        int start = config.getOffset();
        int page = start == 0 ? start : start / config.getLimit();

        PaginatedSearchRequest psr = new PaginatedSearchRequest(searchText,
                                                                config.getLimit(), page);

        List<ServerCSWDTO> serverList = geoPlatformCSWClient.searchCSWServers(psr);
        if (serverList == null) {
            logger.info("*** No CSW server ***");
            throw new GeoPlatformException("There are no results");
        }

        ArrayList<GPCSWServerBeanModel> searchUsers = new ArrayList<GPCSWServerBeanModel>(serverList.size());
        for (ServerCSWDTO serverDTO : serverList) {
            searchUsers.add(this.convertServerDTO(serverDTO));
        }

        return new BasePagingLoadResult<GPCSWServerBeanModel>(searchUsers,
                                                              config.getOffset(),
                                                              serversCount.intValue());
    }

    @Override
    public GPCSWServerBeanModel saveServerCSW(String alias, String serverUrl,
                                              HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        ServerCSWDTO serverCSW = null;
        try {
            serverCSW = geoPlatformCSWClient.saveServerCSW(alias, serverUrl);
        } catch (IllegalParameterFault ex) {
            logger.error(ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
        return this.convertServerDTO(serverCSW);
    }

    @Override
    public boolean deleteServerCSW(Long serverID, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            geoPlatformCSWClient.deleteServerCSW(serverID);
        } catch (ResourceNotFoundFault ex) {
            logger.error("The server with id " + serverID + " was not bean deleted");
            throw new GeoPlatformException("The server with id "
                    + serverID + " was not bean deleted");
        }
        return true;
    }

    @Override
    public PagingLoadResult<SummaryRecord> searchSummaryRecords(
            PagingLoadConfig config, CatalogFinderBean catalogFinder,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        Long serversCount = geoPlatformCSWClient.getSummaryRecordsCount(catalogFinder);

        int start = config.getOffset();
        int page = start == 0 ? start : start / config.getLimit();

        System.out.println("*** " + config);
        System.out.println("*** NUM " + config.getLimit() + " *** PAGE " + page);
        List<SummaryRecordDTO> recordList = geoPlatformCSWClient.searchSummaryRecords(config.getLimit(),
                                                                                      page, catalogFinder);
        if (recordList == null) {
            logger.info("*** No Summary Record ***");
            throw new GeoPlatformException("There are no results");
        }

        ArrayList<SummaryRecord> searchRecords = new ArrayList<SummaryRecord>(recordList.size());
        for (SummaryRecordDTO recordDTO : recordList) {
            searchRecords.add(this.convertSummaryRecordDTO(recordDTO));
        }

        return new BasePagingLoadResult<SummaryRecord>(searchRecords,
                                                       config.getOffset(),
                                                       serversCount.intValue());
    }

    /**
     * @param geoPlatformCSWService the geoPlatformCSWService to set
     */
    @Autowired
    public void setGeoPlatformCSWClient(
            @Qualifier("geoPlatformCSWClient") GeoPlatformCSWService geoPlatformCSWClient) {
        this.geoPlatformCSWClient = geoPlatformCSWClient;
    }

    private GPCSWServerBeanModel convertServerDTO(ServerCSWDTO serverDTO) {
        GPCSWServerBeanModel server = new GPCSWServerBeanModel();
        server.setId(serverDTO.getId());
        server.setUrlServer(serverDTO.getServerUrl());
        server.setTitle(serverDTO.getTitle());
        server.setAlias(serverDTO.getAlias());

        return server;
    }

    private SummaryRecord convertSummaryRecordDTO(SummaryRecordDTO summaryRecordDTO) {
        SummaryRecord summaryRecord = new SummaryRecord();
        summaryRecord.setIdentifier(summaryRecordDTO.getIdentifier());
        summaryRecord.setTitle(summaryRecordDTO.getTitle());
        summaryRecord.setAbstractText(summaryRecordDTO.getAbstractText());
        summaryRecord.setKeywords(summaryRecordDTO.getKeywords());

        return summaryRecord;
    }
}
