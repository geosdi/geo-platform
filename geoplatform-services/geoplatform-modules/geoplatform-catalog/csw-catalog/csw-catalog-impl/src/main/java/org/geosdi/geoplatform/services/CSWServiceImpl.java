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

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.connector.api.capabilities.model.csw.CatalogCapabilities;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.cswconnector.CatalogGetCapabilitiesBean;
import org.geosdi.geoplatform.cswconnector.CatalogVersionException;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.geosdi.geoplatform.services.development.CSWEntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email  michele.santomauro@geosdi.org
 *
 */
class CSWServiceImpl {

    final private Logger logger = LoggerFactory.getLogger(CSWServiceImpl.class);
//    static {
//        pool = CSWMarshallerPool.getInstance();
//    }
    // DAO
    private GPServerDAO serverDao;
    //
    private CatalogGetCapabilitiesBean catalogCapabilitiesBean;
    //      
//    private MarshallerPool pool = CSWMarshallerPool.getInstance();
//    private static MarshallerPool pool;
//    private Unmarshaller um;

    /**
     * @param serverDao the serverDao to set
     */
    void setServerDao(GPServerDAO serverDao) {
        this.serverDao = serverDao;
    }

    /**
     * @param catalogCapabilitiesBean the catalogCapabilitiesBean to set
     */
    public void setCatalogCapabilitiesBean(CatalogGetCapabilitiesBean catalogCapabilitiesBean) {
        this.catalogCapabilitiesBean = catalogCapabilitiesBean;
    }

    /**
     * @see GeoPlatformCSWService#insertServerCSW(org.geosdi.geoplatform.core.model.GeoPlatformServer)
     */
    Long insertServerCSW(GeoPlatformServer server) {
        /** IMPORTANT TO AVOID EXCEPTION IN DB FOR UNIQUE URL SERVER **/
        GeoPlatformServer serverSearch = serverDao.findByServerUrl(
                this.convertUrl(server.getServerUrl()));
        if (serverSearch != null) {
            return serverSearch.getId();
        }

        server.setServerType(GPCapabilityType.CSW);

        serverDao.persist(server);
        return server.getId();
    }

    /**
     * @see GeoPlatformCSWService#saveServerCSW(java.lang.String, java.lang.String)
     */
    ServerCSWDTO saveServerCSW(String alias, String serverUrl)
            throws IllegalParameterFault {
        serverUrl = this.convertUrl(serverUrl);
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server != null) { // If there is already a server with the specified URLs
            return new ServerCSWDTO(server);
        }

        try {
//            um = pool.acquireUnmarshaller();
//
//            GPCSWServerConnector serverConnector = GeoPlatformCSWConnectorBuilder.newConnector().
//                    withServerUrl(serverURL).build();
//
//            // make a getCapabilities request
//            final GetCapabilitiesRequest getCapa = serverConnector.createGetCapabilities();
//
//            // unmarshall the response
//            InputStream is = getCapa.getResponseStream();
//            Capabilities capabilities = (Capabilities) um.unmarshal(is);

            CatalogCapabilities capabilities = catalogCapabilitiesBean.bindUrl(serverUrl);

            server = new GeoPlatformServer();
            server.setServerType(GPCapabilityType.CSW);
            server.setServerUrl(serverUrl);
            server.setAliasName(alias);

            server.setTitle(capabilities.getServiceIdentification().getTitle());
            server.setAbstractServer(capabilities.getServiceIdentification().getAbstractText());
            server.setName(capabilities.getServiceProvider().getProviderName());

            CSWEntityCorrectness.checkServerCSW(server); // TODO assert
            serverDao.save(server);

        } catch (MalformedURLException ex) {
            logger.error("### MalformedURLException: " + ex.getMessage());
            throw new IllegalParameterFault("Malformed URL");
        } catch (IOException ex) {
            logger.error("### IOException: " + ex.getMessage());
            throw new IllegalParameterFault("Error on parse response stream");
        } catch (CatalogVersionException ex) {
            logger.error("### CatalogVersionException: " + ex.getMessage());
            throw new IllegalParameterFault("The catalog version must be 2.0.2");
//        } finally {
//            if (um != null) {
//                pool.release(um);
//            }
        }

        return new ServerCSWDTO(server);
    }

    /**
     * @see GeoPlatformCSWService#deleteServerCSW(java.lang.Long) 
     */
    boolean deleteServerCSW(Long serverID) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        CSWEntityCorrectness.checkServerCSW(server); // TODO assert

        return serverDao.remove(server);
    }

    /**
     * @see GeoPlatformCSWService#getAllCSWServers() 
     */
    List<ServerCSWDTO> getAllCSWServers() {
        List<GeoPlatformServer> found = serverDao.findAll(GPCapabilityType.CSW);
        return convertToServerList(found);
    }

    /**
     * @see GeoPlatformCSWService#getServerDetailCSW(java.lang.Long) 
     */
    GeoPlatformServer getServerDetailCSW(Long serverID)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        CSWEntityCorrectness.checkServerCSW(server); // TODO assert

        return server;
    }

    /**
     * @see GeoPlatformCSWService#getServerDetailCSWByUrl(java.lang.String) 
     */
    GeoPlatformServer getServerDetailCSWByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }
        CSWEntityCorrectness.checkServerCSW(server); // TODO assert

        return server;
    }

    /**
     * @see GeoPlatformCSWService#getShortServerCSW(java.lang.String) 
     */
    ServerCSWDTO getShortServerCSW(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }
        CSWEntityCorrectness.checkServerCSW(server); // TODO assert

        return new ServerCSWDTO(server);
    }

    Long getCSWServersCount(SearchRequest request) {
        System.out.println("### " + request);
        Search searchCriteria = new Search(GeoPlatformServer.class);
        searchCriteria.addFilterEqual("serverType", GPCapabilityType.CSW);

        String like = request.getNameLike();
        if (like != null) {
            Filter fTitle = Filter.ilike("title", like);
            Filter fAlias = Filter.ilike("aliasName", like);
//            Filter fUrl = Filter.ilike("serverUrl", like);
            searchCriteria.addFilterOr(fTitle, fAlias);
        }
        return new Long(serverDao.count(searchCriteria));
    }

    List<ServerCSWDTO> searchCSWServers(PaginatedSearchRequest request) {
        Search searchCriteria = new Search(GeoPlatformServer.class);
        searchCriteria.addFilterEqual("serverType", GPCapabilityType.CSW);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addSortAsc("aliasName");

        String like = request.getNameLike();
        if (like != null) {
            Filter fTitle = Filter.ilike("title", like);
            Filter fAlias = Filter.ilike("aliasName", like);
//            Filter fUrl = Filter.ilike("serverUrl", like);
            searchCriteria.addFilterOr(fTitle, fAlias);
        }

        List<GeoPlatformServer> serverList = serverDao.search(searchCriteria);
        return this.convertToServerList(serverList);
    }

    private List<ServerCSWDTO> convertToServerList(List<GeoPlatformServer> servers) {
        List<ServerCSWDTO> shortServers = new ArrayList<ServerCSWDTO>(
                servers.size());
        for (GeoPlatformServer server : servers) {
            shortServers.add(new ServerCSWDTO(server));
        }
        return shortServers;
    }

    private String convertUrl(String serverUrl) {
        int index = serverUrl.indexOf("?");
        if (index != -1) {
            return serverUrl.substring(0, index);
        }
        return serverUrl;
    }

    // TODO Implement me
    Long getSummaryRecordsCount(CatalogFinderBean catalogFinder) {
        return new Long(75);
    }

    // TODO Implement me
    List<SummaryRecordDTO> searchSummaryRecords(int num, int page,
                                                CatalogFinderBean catalogFinder) {
        logger.info("\n*** {}", catalogFinder);
//        Long serverID = catalogFinder.getServerID();
//
//        SearchInfo search = catalogFinder.getSearchInfo();
//        search.getSearchText();
//        search.isSearchTitle();
//        search.isSearchAbstract();
//        search.isSearchKeywords();
//
//        BBoxInfo bBox = catalogFinder.getbBoxInfo();
//        bBox.getSearchBBoxType();
//        bBox.getbBox();
//
//        TemporalInfo temporal = catalogFinder.getTemporalInfo();
//        temporal.getStartDate();
//        temporal.getEndDate();

        return this.createDummySummaryRecords(num, page);
    }

    private List<SummaryRecordDTO> createDummySummaryRecords(int num, int page) {
        int start = 10 + (num * page);
        int end = start + num - 1;
        logger.info("\n*** start {} --- end {}", start, end);

        List<SummaryRecordDTO> list = new ArrayList<SummaryRecordDTO>(num);

        for (int i = start; i <= end; i++) {
            SummaryRecordDTO dto = new SummaryRecordDTO();
            dto.setIdentifier("ID_" + i);
            dto.setTitle("Title_" + i);
            dto.setAbstractText("Abstract_" + i);
            dto.setKeywords("Keywords_" + i);

            list.add(dto);
        }

        return list;
    }
}
