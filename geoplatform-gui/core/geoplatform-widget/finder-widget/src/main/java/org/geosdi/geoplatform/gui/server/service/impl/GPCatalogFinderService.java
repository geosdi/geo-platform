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
package org.geosdi.geoplatform.gui.server.service.impl;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.gui.client.model.AbstractRecord;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.server.IGPCatalogFinderService;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.AbstractRecordDTO;
import org.geosdi.geoplatform.responce.FullRecordDTO;
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
            String organizationName,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {

        ArrayList<GPCSWServerBeanModel> servers = new ArrayList<GPCSWServerBeanModel>();

        try {
            List<ServerCSWDTO> serversDTO = geoPlatformCSWClient.getAllCSWServers(
                    organizationName);
            for (ServerCSWDTO serverDTO : serversDTO) {
                GPCSWServerBeanModel server = this.convertServerDTO(serverDTO);
                servers.add(server);
            }
        } catch (ResourceNotFoundFault fault) {
            logger.error("GET ALL CSW SERVERS EXCEPTION : " + fault);
            throw new GeoPlatformException(fault);
        }

        return servers;
    }

    @Override
    public PagingLoadResult<GPCSWServerBeanModel> searchCSWServers(
            PagingLoadConfig config,
            String searchText,
            String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {

        SearchRequest srq = new SearchRequest(searchText);

        int serversCount = geoPlatformCSWClient.getCSWServersCount(srq,
                organization);

        ArrayList<GPCSWServerBeanModel> searchServers;
        if (serversCount == 0) {
            logger.info("\n*** No catalog found ***");
            searchServers = new ArrayList<GPCSWServerBeanModel>(0);
        } else {
            int start = config.getOffset();
            int page = start == 0 ? start : start / config.getLimit();

            PaginatedSearchRequest psr = new PaginatedSearchRequest(searchText,
                    config.getLimit(),
                    page);

            List<ServerCSWDTO> serverList = geoPlatformCSWClient.searchCSWServers(
                    psr, organization);

            searchServers = new ArrayList<GPCSWServerBeanModel>(
                    serverList.size());
            for (ServerCSWDTO serverDTO : serverList) {
                searchServers.add(this.convertServerDTO(serverDTO));
            }
        }

        return new BasePagingLoadResult<GPCSWServerBeanModel>(searchServers,
                config.getOffset(),
                serversCount);
    }

    @Override
    public GPCSWServerBeanModel saveServerCSW(String alias,
            String serverUrl,
            String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        ServerCSWDTO serverCSW = null;
        try {
            serverCSW = geoPlatformCSWClient.saveServerCSW(alias, serverUrl,
                    organization);
        } catch (IllegalParameterFault ex) {
            logger.error(ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
        return this.convertServerDTO(serverCSW);
    }

    @Override
    public boolean deleteServerCSW(Long serverID,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            geoPlatformCSWClient.deleteServerCSW(serverID);
        } catch (ResourceNotFoundFault ex) {
            String errorMessage = "The server with id " + serverID + " was not bean deleted";
            logger.error("### {} - {}", ex.getMessage(), errorMessage);
            throw new GeoPlatformException(errorMessage);
        }
        return true;
    }

    @Override
    public PagingLoadResult<SummaryRecord> searchSummaryRecords(
            PagingLoadConfig config,
            CatalogFinderBean catalogFinder,
            HttpServletRequest httpServletRequest)
            throws Exception {

        int recordsCount;
        ArrayList<SummaryRecord> searchRecords;
        try {
            recordsCount = geoPlatformCSWClient.getRecordsCount(catalogFinder);

            if (recordsCount == 0) {
                logger.info("\n*** No Summary Record found ***");
                searchRecords = new ArrayList<SummaryRecord>(0);

            } else {
                List<SummaryRecordDTO> recordList = geoPlatformCSWClient.searchSummaryRecords(
                        config.getLimit(), config.getOffset() + 1, catalogFinder);

                searchRecords = new ArrayList<SummaryRecord>(recordList.size());
                for (SummaryRecordDTO recordDTO : recordList) {
                    searchRecords.add(this.convertSummaryRecordDTO(recordDTO));
                }
            }

        } catch (IllegalParameterFault ex) {
            logger.error("\n*** IllegalParameterFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (ResourceNotFoundFault ex) {
            logger.error("\n*** ResourceNotFoundFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (ServerInternalFault ex) {
            logger.error("\n*** ServerInternalFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }

        return new BasePagingLoadResult<SummaryRecord>(searchRecords,
                config.getOffset(),
                recordsCount);
    }

    @Override
    public PagingLoadResult<FullRecord> searchFullRecords(
            PagingLoadConfig config,
            CatalogFinderBean catalogFinder,
            HttpServletRequest httpServletRequest)
            throws Exception {

        logger.debug(
                "\n--------------------------\n{}\n--------------------------\n",
                catalogFinder);

        int recordsCount;
        ArrayList<FullRecord> searchRecords;
        try {
            recordsCount = geoPlatformCSWClient.getRecordsCount(catalogFinder);

            if (recordsCount == 0) {
                logger.info("\n*** No Full Record found ***");
                searchRecords = new ArrayList<FullRecord>(0);

            } else {
                List<FullRecordDTO> recordList = geoPlatformCSWClient.searchFullRecords(
                        config.getLimit(), config.getOffset() + 1, catalogFinder);

                searchRecords = new ArrayList<FullRecord>(recordList.size());
                for (FullRecordDTO recordDTO : recordList) {
                    searchRecords.add(this.convertFullRecordDTO(recordDTO));
                }
            }

        } catch (IllegalParameterFault ex) {
            logger.error("\n*** IllegalParameterFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (ResourceNotFoundFault ex) {
            logger.error("\n*** ResourceNotFoundFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (ServerInternalFault ex) {
            logger.error("\n*** ServerInternalFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }

        return new BasePagingLoadResult<FullRecord>(searchRecords,
                config.getOffset(),
                recordsCount);
    }

    @Override
    public String getRecordById(Long serverID, String identifier,
            String moduleName,
            HttpServletRequest httpServletRequest) throws Exception {

        try {
            String url = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/" + moduleName + "/csw-template");
            logger.trace("PATH @@@@@@@@@@@@@@@@@@ {}", url);

            String response = geoPlatformCSWClient.getRecordById(serverID,
                    identifier);
            response = this.deAccent(response);

            String fileName = url + "/" + System.currentTimeMillis() + "-" + identifier + ".xml";
            File file = new File(fileName);
            FileUtils.writeStringToFile(file, response);

            logger.debug("Name FILE Created  @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}",
                    file.getName());

            return file.getName();

        } catch (IOException ex) {
            logger.error("\n*** IOException ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (IllegalParameterFault ex) {
            logger.error("\n*** IllegalParameterFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (ResourceNotFoundFault ex) {
            logger.error("\n*** ResourceNotFoundFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        } catch (ServerInternalFault ex) {
            logger.error("\n*** ServerInternalFault ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
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

    private <R extends AbstractRecord> R convertRecordDTO(R record,
            AbstractRecordDTO recordDTO) {
        record.setIdentifier(recordDTO.getIdentifier());
        record.setIdCatalog(recordDTO.getIdCatalog());
        record.setTitle(recordDTO.getTitle());
        record.setCatalogURL(recordDTO.getCatalogURL());
        record.setType(recordDTO.getType());
        record.setAbstractText(recordDTO.getAbstractText());
        record.setSubjects(recordDTO.getSubjects());

        return record;
    }

    private SummaryRecord convertSummaryRecordDTO(
            SummaryRecordDTO summaryRecordDTO) {
        return this.convertRecordDTO(new SummaryRecord(), summaryRecordDTO);
    }

    private FullRecord convertFullRecordDTO(FullRecordDTO fullRecordDTO) {
        FullRecord fullRecord = this.convertRecordDTO(new FullRecord(),
                fullRecordDTO);

        fullRecord.setBBox(this.convertBBoxDTO(fullRecordDTO.getBBox()));
        fullRecord.setCrs(fullRecordDTO.getCrs());
        fullRecord.setUriMap(fullRecordDTO.getUriMap());

        return fullRecord;
    }

    private BBoxClientInfo convertBBoxDTO(BBox bBox) {
        if (bBox == null) {
            return null;
        }

        BBoxClientInfo bBoxClient = new BBoxClientInfo();

        bBoxClient.setLowerLeftX(bBox.getMinX());
        bBoxClient.setLowerLeftY(bBox.getMinY());
        bBoxClient.setUpperRightX(bBox.getMaxX());
        bBoxClient.setUpperRightY(bBox.getMaxY());
        return bBoxClient;
    }

    public String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("'");
    }

}
