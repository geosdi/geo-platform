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
package org.geosdi.geoplatform.services.delegate;

import com.google.common.collect.Lists;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.connector.CatalogGetCapabilitiesBean;
import org.geosdi.geoplatform.connector.CatalogVersionException;
import org.geosdi.geoplatform.connector.GPCSWConnectorBuilder;
import org.geosdi.geoplatform.connector.GPCatalogConnectorStore;
import org.geosdi.geoplatform.connector.api.capabilities.model.csw.CatalogCapabilities;
import org.geosdi.geoplatform.connector.proxy.CSWProxyConnectionConfiguration;
import static org.geosdi.geoplatform.connector.schema.CSWOperationsWithOutputSchema.GET_RECORD_BY_ID;
import org.geosdi.geoplatform.connector.schema.CSWOutputSchemaFinder;
import org.geosdi.geoplatform.connector.security.SnipcCatalogBeanProvider;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordByIdRequest;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.core.dao.GPOrganizationDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.geosdi.geoplatform.services.BindingUtility;
import org.geosdi.geoplatform.services.GeoPlatformCSWService;
import org.geosdi.geoplatform.services.delegate.dc.DublinCoreAnalyzer;
import org.geosdi.geoplatform.services.development.CSWEntityCorrectness;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.AbstractRecordType;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordByIdResponseType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordsResponseType;
import org.geosdi.geoplatform.xml.csw.v202.RecordType;
import org.geosdi.geoplatform.xml.csw.v202.ResultType;
import org.geosdi.geoplatform.xml.csw.v202.SummaryRecordType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class CSWServiceDelegate implements CSWDelegate {

    private final static Logger logger = LoggerFactory.getLogger(CSWServiceDelegate.class);
    //
    @Resource(name = "gpCSWOutputSchemaFinder")
    private CSWOutputSchemaFinder gpCSWOutputSchemaFinder;
    @Autowired
    private GPServerDAO serverDao;
    @Autowired
    private GPOrganizationDAO organizationDao;
    //
    @Autowired
    private CatalogGetCapabilitiesBean catalogCapabilitiesBean;
    @Autowired
    private SnipcCatalogBeanProvider snipcProvider;
    @Autowired
    private CSWProxyConnectionConfiguration cswProxyConfiguration;
    private DublinCoreAnalyzer gpDublinCoreAnalyzer = new DublinCoreAnalyzer.GPDublinCoreAnalyzer();

    /**
     * @see
     * GeoPlatformCSWService#insertServerCSW(org.geosdi.geoplatform.core.model.GeoPlatformServer)
     */
    @Override
    public Long insertServerCSW(GeoPlatformServer server) throws IllegalParameterFault {
        CSWEntityCorrectness.checkCSWServer(server); // TODO assert

        /**
         * IMPORTANT TO AVOID EXCEPTION IN DB FOR UNIQUE URL SERVER *
         */
        String serverUrl = this.deleteQueryStringFromURL(server.getServerUrl());
        GeoPlatformServer serverSearch = serverDao.findByServerUrl(serverUrl);
        if (serverSearch != null) { // If there is already a server with the specified URLs
            return serverSearch.getId();
        }

        server.setServerType(GPCapabilityType.CSW);

        serverDao.persist(server);
        return server.getId();
    }

    /**
     * @see GeoPlatformCSWService#saveServerCSW(java.lang.String,
     * java.lang.String)
     */
    @Override
    public ServerCSWDTO saveServerCSW(String alias, String serverUrl,
            String organization) throws IllegalParameterFault {

        serverUrl = this.deleteQueryStringFromURL(serverUrl);
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server != null) { // If there is already a server with the specified URLs
            return new ServerCSWDTO(server);
        }

        GPOrganization org = organizationDao.findByName(organization);
        if (org == null) {
            throw new IllegalParameterFault(
                    "CSW Server to save have an organization that does not exist");
        }

        try {
            CatalogCapabilities capabilities = catalogCapabilitiesBean.bindUrl(
                    serverUrl);

            server = new GeoPlatformServer();
            server.setServerType(GPCapabilityType.CSW);
            server.setServerUrl(serverUrl);
            server.setAliasName(alias);
            server.setOrganization(org);

            server.setTitle(capabilities.getServiceIdentification().getTitle());
            server.setAbstractServer(
                    capabilities.getServiceIdentification().getAbstractText());
            server.setName(capabilities.getServiceProvider().getProviderName());

            CSWEntityCorrectness.checkCSWServer(server); // TODO assert
            serverDao.persist(server);

        } catch (MalformedURLException ex) {
            logger.error("### MalformedURLException: {}", ex.getMessage());
            throw new IllegalParameterFault("Malformed URL");
        } catch (IOException ex) {
            logger.error("### IOException: {}", ex.getMessage());
            throw new IllegalParameterFault("Error on parse response stream");
        } catch (CatalogVersionException ex) {
            logger.error("### CatalogVersionException: {}", ex.getMessage());
            throw new IllegalParameterFault("The catalog version must be 2.0.2");
        }

        return new ServerCSWDTO(server);
    }

    /**
     * @see GeoPlatformCSWService#deleteServerCSW(java.lang.Long)
     */
    @Override
    public boolean deleteServerCSW(Long serverID) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        CSWEntityCorrectness.checkCSWServerLog(server); // TODO assert

        return serverDao.remove(server);
    }

    /**
     * @see GeoPlatformCSWService#getAllCSWServers()
     */
    @Override
    public List<ServerCSWDTO> getAllCSWServers(String organizationName) throws ResourceNotFoundFault {
        GPOrganization organization = organizationDao.findByName(
                organizationName);

        if (organization == null) {
            throw new ResourceNotFoundFault("Organization with name "
                    + organizationName + "was not found.");
        }

        List<GeoPlatformServer> found = serverDao.findAll(organization.getId(),
                GPCapabilityType.CSW);
        return convertServerList(found);
    }

    /**
     * @see GeoPlatformCSWService#getServerDetailCSW(java.lang.Long)
     */
    @Override
    public GeoPlatformServer getServerDetailCSW(Long serverID)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        CSWEntityCorrectness.checkCSWServerLog(server); // TODO assert

        return server;
    }

    /**
     * @see GeoPlatformCSWService#getServerDetailCSWByUrl(java.lang.String)
     */
    @Override
    public GeoPlatformServer getServerDetailCSWByUrl(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }
        CSWEntityCorrectness.checkCSWServerLog(server); // TODO assert

        return server;
    }

    /**
     * @see GeoPlatformCSWService#getShortServerCSW(java.lang.String)
     */
    @Override
    public ServerCSWDTO getShortServerCSW(String serverUrl)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }
        CSWEntityCorrectness.checkCSWServerLog(server); // TODO assert

        return new ServerCSWDTO(server);
    }

    @Override
    public int getCSWServersCount(SearchRequest request,
            String organization) {
        logger.trace("\n*** CSWServersCount: {} ***", request);
        Search searchCriteria = new Search(GeoPlatformServer.class);
        searchCriteria.addFilterEqual("serverType", GPCapabilityType.CSW);
        searchCriteria.addFilterEqual("organization.name", organization);

        String like = request.getNameLike();
        if (like != null) {
            Filter fTitle = Filter.ilike("title", like);
            Filter fAlias = Filter.ilike("aliasName", like);
//            Filter fUrl = Filter.ilike("serverUrl", like);
            searchCriteria.addFilterOr(fTitle, fAlias);
        }
        return serverDao.count(searchCriteria);
    }

    @Override
    public List<ServerCSWDTO> searchCSWServers(PaginatedSearchRequest request,
            String organization) {
        Search searchCriteria = new Search(GeoPlatformServer.class);
        searchCriteria.addFilterEqual("serverType", GPCapabilityType.CSW);
        searchCriteria.addFilterEqual("organization.name", organization);
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
        return this.convertServerList(serverList);
    }

    private List<ServerCSWDTO> convertServerList(List<GeoPlatformServer> servers) {
        List<ServerCSWDTO> shorts = new ArrayList<ServerCSWDTO>(servers.size());
        for (GeoPlatformServer server : servers) {
            shorts.add(new ServerCSWDTO(server));
        }
        return shorts;
    }

    private String deleteQueryStringFromURL(String serverUrl) {
        if (serverUrl == null) {
            return "";
        }

        int index = serverUrl.indexOf("?");
        if (index != -1) {
            return serverUrl.substring(0, index);
        }
        return serverUrl;
    }

    @Override
    public int getRecordsCount(CatalogFinderBean catalogFinder) throws Exception {
        logger.trace("########################## getRecordsCount {}\n", catalogFinder);
        GeoPlatformServer server = this.getCSWServerByID(catalogFinder.getServerID());
        CatalogGetRecordsRequest<GetRecordsResponseType> request = this.createGetRecordsRequest(server.getServerUrl());
        request.setTypeName(TypeName.RECORD_V202);
        request.setOutputSchema(OutputSchema.CSW_V202);
        request.setElementSetName(ElementSetType.BRIEF.value());
        request.setResultType(ResultType.HITS.value());
        request.setConstraintLanguage(ConstraintLanguage.FILTER);
        request.setConstraintLanguageVersion(ConstraintLanguageVersion.V110);
        request.setCatalogFinder(catalogFinder);
        GetRecordsResponseType response = this.createGetRecordsResponse(request);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@RESPONSE : {}\n\n\n", request);
        return response.getSearchResults().getNumberOfRecordsMatched().intValue();
    }

    /**
     * TODO Factorize source code for search*Records methods.
     *
     * TODO GMD list.
     */
    @Override
    public List<SummaryRecordDTO> searchSummaryRecords(int num, int start,
            CatalogFinderBean catalogFinder) throws Exception {
        logger.trace("\n*** searchSummaryRecords ***\n{}", catalogFinder);

        GeoPlatformServer server = this.getCSWServerByID(
                catalogFinder.getServerID());

        CatalogGetRecordsRequest<GetRecordsResponseType> request
                = this.createGetRecordsRequest(server.getServerUrl());

        request.setTypeName(TypeName.RECORD_V202);
        request.setOutputSchema(OutputSchema.CSW_V202);
        request.setElementSetName(ElementSetType.SUMMARY.value());
        request.setResultType(ResultType.RESULTS.value());

        request.setConstraintLanguage(ConstraintLanguage.FILTER);
        request.setConstraintLanguageVersion(ConstraintLanguageVersion.V110);
        request.setCatalogFinder(catalogFinder);

        // Pagination search
        request.setMaxRecords(BigInteger.valueOf(num));
        request.setStartPosition(BigInteger.valueOf(start));
        logger.debug("\n*** Num: {} *** Start: {} ***",
                request.getMaxRecords(), request.getStartPosition());

        GetRecordsResponseType response = this.createGetRecordsResponse(request);
        logger.debug(
                "\n*** Records matched: {} *** Records returned: {} *** Record next: {} ***",
                response.getSearchResults().getNumberOfRecordsMatched(),
                response.getSearchResults().getNumberOfRecordsReturned(),
                response.getSearchResults().getNextRecord());

        if (response.getSearchResults().getNumberOfRecordsReturned().intValue()
                != response.getSearchResults().getAbstractRecord().size()) {
            throw new ServerInternalFault("CSW Catalog Server Error: incorrect number of records, expected "
                    + response.getSearchResults().getNumberOfRecordsReturned() + " but was "
                    + response.getSearchResults().getAbstractRecord().size());
        }

        List<JAXBElement<? extends AbstractRecordType>> records
                = response.getSearchResults().getAbstractRecord();
        logger.trace("\n*** Record list size: {} ***", records.size());

        List<SummaryRecordDTO> recordListDTO = new ArrayList<SummaryRecordDTO>(
                records.size());
        for (JAXBElement<? extends AbstractRecordType> record : records) {
            SummaryRecordType summary = (SummaryRecordType) record.getValue();
            recordListDTO.add(this.convertSummaryRecords(summary, server));
        }

        return recordListDTO;
    }

    /**
     * TODO Factorize source code for search*Records methods.
     *
     * Changed wrt searchSummaryRecords: 1 - ElementSetType.FULL 2 - Downcast to
     * RecordType
     *
     * TODO GMD list.
     */
    @Override
    public List<FullRecordDTO> searchFullRecords(int num, int start,
            CatalogFinderBean catalogFinder)
            throws Exception {
        logger.trace("\n*** searchFullRecords ***\n{}", catalogFinder);

        GeoPlatformServer server = this.getCSWServerByID(
                catalogFinder.getServerID());

        CatalogGetRecordsRequest<GetRecordsResponseType> request
                = this.createGetRecordsRequest(server.getServerUrl());

        request.setTypeName(TypeName.RECORD_V202);
        request.setOutputSchema(OutputSchema.CSW_V202);
        request.setElementSetName(ElementSetType.FULL.value());
        request.setResultType(ResultType.RESULTS.value());

        request.setConstraintLanguage(ConstraintLanguage.FILTER);
        request.setConstraintLanguageVersion(ConstraintLanguageVersion.V110);
        request.setCatalogFinder(catalogFinder);

        // Pagination search
        request.setMaxRecords(BigInteger.valueOf(num));
        request.setStartPosition(BigInteger.valueOf(start));
        logger.debug("\n*** Num: {} *** Start: {} ***",
                request.getMaxRecords(), request.getStartPosition());

        GetRecordsResponseType response = this.createGetRecordsResponse(request);
        logger.debug(
                "\n*** Records matched: {} *** Records returned: {} *** Record next: {} ***",
                response.getSearchResults().getNumberOfRecordsMatched(),
                response.getSearchResults().getNumberOfRecordsReturned(),
                response.getSearchResults().getNextRecord());

        if (response.getSearchResults().getNumberOfRecordsReturned().intValue()
                != response.getSearchResults().getAbstractRecord().size()) {
            throw new ServerInternalFault("CSW Catalog Server Error: incorrect number of records, expected "
                    + response.getSearchResults().getNumberOfRecordsReturned() + " but was "
                    + response.getSearchResults().getAbstractRecord().size());
        }

        List<JAXBElement<? extends AbstractRecordType>> records
                = response.getSearchResults().getAbstractRecord();
        logger.trace("\n*** Record list size: {} ***", records.size());

        List<FullRecordDTO> recordListDTO = Lists.<FullRecordDTO>newArrayListWithCapacity(
                records.size());
        for (JAXBElement<? extends AbstractRecordType> r : records) {
            RecordType record = (RecordType) r.getValue();
            recordListDTO.add(this.convertFullRecords(record, server));
        }

        return recordListDTO;
    }

    private GeoPlatformServer getCSWServerByID(Long serverID)
            throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.find(serverID);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found", serverID);
        }
        CSWEntityCorrectness.checkCSWServerLog(server); // TODO assert

        return server;
    }

    private SummaryRecordDTO convertSummaryRecords(SummaryRecordType record,
            GeoPlatformServer server) {

        SummaryRecordDTO dto = new SummaryRecordDTO();
        dto.setIdCatalog(server.getId());
        dto.setCatalogURL(server.getServerUrl());
        dto.setIdentifier(
                BindingUtility.convertJaxbLiteralListToString(
                        record.getIdentifier()));
        dto.setTitle(
                BindingUtility.convertJaxbLiteralListToString(
                        record.getTitle()));
        dto.setAbstractText(
                BindingUtility.convertLiteralListToString(
                        record.getAbstract()));
        dto.setSubjects(
                BindingUtility.convertLiteralListToList(record.getSubject()));

        return dto;
    }

    private FullRecordDTO convertFullRecords(RecordType record,
            GeoPlatformServer server) {
        FullRecordDTO dto = new FullRecordDTO();
        dto.setIdCatalog(server.getId());
        dto.setCatalogURL(server.getServerUrl());
        this.gpDublinCoreAnalyzer.analyzeRecord(record, dto);

        return dto;
    }

    private CatalogGetRecordsRequest<GetRecordsResponseType> createGetRecordsRequest(String serverUrl) throws Exception {
        GPCatalogConnectorStore serverConnector = null;
        try {
            URL url = new URL(serverUrl);
            GPCSWConnectorBuilder builder = GPCSWConnectorBuilder.newConnector()
                    .withServerUrl(url)
                    .withProxyConfiguration(cswProxyConfiguration);

            if (serverUrl.contains("snipc.protezionecivile.it")) {
                GPSecurityConnector securityConnector = new BasicPreemptiveSecurityConnector(
                        snipcProvider.getSnipcUsername(),
                        snipcProvider.getSnipcPassword());
                builder.withClientSecurity(securityConnector);
            }

            serverConnector = builder.build();

        } catch (MalformedURLException ex) {
            logger.error("### MalformedURLException: {}", ex.getMessage());
            throw new IllegalParameterFault("Malformed URL");
        }
        CatalogGetRecordsRequest<GetRecordsResponseType> request = serverConnector.createGetRecordsRequest();

        return request;
    }

    private GetRecordsResponseType createGetRecordsResponse(CatalogGetRecordsRequest<GetRecordsResponseType> request) throws Exception {
        GetRecordsResponseType response = null;
        try {
            response = request.getResponse();
        } catch (IOException ex) {
            logger.error("### IOException: {}", ex.getMessage());
            throw new IllegalParameterFault("Error on parse response stream");
        }

        return response;
    }

    @Override
    public String getRecordById(Long serverID, String identifier)
            throws Exception {
        logger.trace("\n*** GetRecordById ***\n");

        GeoPlatformServer server = this.getCSWServerByID(serverID);

        GPCatalogConnectorStore serverConnector = this.createServerConnector(
                server.getServerUrl());

        OutputSchema outputSchema = this.gpCSWOutputSchemaFinder
                .retrieveBestOutputSchemaForRequest(serverConnector,
                        GET_RECORD_BY_ID.toString());

        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request
                = serverConnector.createGetRecordByIdRequest();
        request.setId(identifier);
        request.setElementSetType(ElementSetType.FULL.value());
        request.setOutputSchema(outputSchema);

        String response = this.createGetRecordByIdResponseAsString(request);

        String responseXSL = this.insertStylesheet(response);

        return responseXSL;
    }

    private GPCatalogConnectorStore createServerConnector(String serverUrl)
            throws Exception {
        GPCatalogConnectorStore serverConnector;
        try {
            URL url = new URL(serverUrl);
            GPCSWConnectorBuilder builder = GPCSWConnectorBuilder.newConnector()
                    .withServerUrl(url).withProxyConfiguration(
                            cswProxyConfiguration);

            if (serverUrl.contains("snipc.protezionecivile.it")) {
                GPSecurityConnector securityConnector = new BasicPreemptiveSecurityConnector(
                        snipcProvider.getSnipcUsername(),
                        snipcProvider.getSnipcPassword());
                builder.withClientSecurity(securityConnector);
            }

            serverConnector = builder.build();
        } catch (MalformedURLException ex) {
            logger.error("### MalformedURLException: {}", ex.getMessage());
            throw new IllegalParameterFault("Malformed URL");
        }
        return serverConnector;
    }

    private String createGetRecordByIdResponseAsString(
            CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request)
            throws Exception {

        String response = null;
        try {
            response = request.getResponseAsString();
        } catch (IOException ex) {
            logger.error("### IOException: " + ex.getMessage());
            throw new IllegalParameterFault("Error on parse response stream");
        }

        return response;
    }

    /**
     * Insert into XML response the processing instruction related to stylesheet
     * wrt the OutputSchema and catalog URL.
     */
    private String insertStylesheet(String response) {
        String responseXSL = response;

        String stylesheet = "catalog-snipc.xsl";
        int ind = response.lastIndexOf("?>");
        responseXSL = responseXSL.substring(0, ind + 2)
                + "\n<?xml-stylesheet type=\"text/xsl\" href=\"" + stylesheet + "\"?>"
                + responseXSL.substring(ind + 3);

        return responseXSL;
    }

}