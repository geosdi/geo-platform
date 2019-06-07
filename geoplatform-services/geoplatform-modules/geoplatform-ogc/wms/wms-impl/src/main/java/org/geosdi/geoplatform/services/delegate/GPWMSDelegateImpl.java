package org.geosdi.geoplatform.services.delegate;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.builder.GPWMSCapabilitesBuilder;
import org.geosdi.geoplatform.services.builder.IGPWMSCapabilitesBuilder;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geosdi.geoplatform.services.response.GPLayerTypeResponse;
import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.forLanguageTag;
import static org.geosdi.geoplatform.services.builder.WMSGetFeatureInfoResponseBuilder.wmsGetFeatureInfoResponseBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPWMSDelegateImpl implements GPWMSDelagate {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSDelegateImpl.class);
    // DAO
    @Resource(name = "serverDAO")
    private GPServerDAO serverDao;
    @Resource(name = "wmsMessageSource")
    private MessageSource wmsMessageSource;
    @Resource(name = "wmsRequestValidator")
    private GPI18NValidator<GPI18NRequestValidator, String> wmsRequestValidator;
    private IGPWMSCapabilitesBuilder wmsCapabilitiesBuilder = new GPWMSCapabilitesBuilder();

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @return {@link ServerDTO}
     * @throws Exception
     */
    @Override
    public ServerDTO getCapabilities(String serverUrl, RequestByID request, String token, String authkey) throws ResourceNotFoundFault {
        ServerDTO serverDTO;
        if (request.getId() != null) {
            GeoPlatformServer server = serverDao.find(request.getId());
            if (server == null) {
                throw new ResourceNotFoundFault("Server has been deleted", request.getId());
            }
            serverDTO = new ServerDTO(server);
        } else {
            serverDTO = new ServerDTO();
            serverDTO.setServerUrl(serverUrl);
        }
        List<RasterLayerDTO> layers = this.wmsCapabilitiesBuilder.loadWMSCapabilities(serverUrl, token, authkey);
        serverDTO.setLayerList(layers);
        return serverDTO;
    }

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @param headers
     * @return {@link ServerDTO}
     * @throws Exception
     */
    @Override
    public ServerDTO getCapabilitiesAuth(String serverUrl, RequestByID request, String token, String authkey, List<WMSHeaderParam> headers) throws ResourceNotFoundFault {
        ServerDTO serverDTO;
        if (request.getId() != null) {
            GeoPlatformServer server = serverDao.find(request.getId());
            if (server == null) {
                throw new ResourceNotFoundFault("Server has been deleted", request.getId());
            }
            serverDTO = new ServerDTO(server);
        } else {
            serverDTO = new ServerDTO();
            serverDTO.setServerUrl(serverUrl);
        }
        List<RasterLayerDTO> layers = this.wmsCapabilitiesBuilder.loadWMSCapabilitiesAuth(serverUrl, token, authkey, headers);
        serverDTO.setLayerList(layers);
        return serverDTO;
    }

    /**
     * @param serverUrl
     * @return {@link ServerDTO}
     * @throws Exception
     */
    @Override
    public ServerDTO getShortServer(String serverUrl) throws ResourceNotFoundFault {
        GeoPlatformServer server = serverDao.findByServerUrl(serverUrl);
        if (server == null) {
            throw new ResourceNotFoundFault("Server not found " + serverUrl);
        }
        return new ServerDTO(server);
    }

    /**
     * @param serverURL
     * @param layerName
     * @return {@link GPLayerTypeResponse}
     * @throws Exception
     */
    @Override
    public GPLayerTypeResponse getLayerType(String serverURL, String layerName) throws Exception {
        checkArgument((serverURL != null) && !(serverURL.trim().isEmpty()),
                "The Parameter serverURL must not be null or an empty string.");
        checkArgument((layerName != null) && !(layerName.trim().isEmpty()),
                "The Parameter layerName must not be null or an empty string.");
        logger.debug("###########################TRYING TO RETRIEVE LAYER_TYPE with serverURL : {} - layerName : {]\n",
                serverURL, layerName);
        int index = serverURL.indexOf("?");
        if (index != -1) {
            serverURL = serverURL.substring(0, index);
        }
        String decribeLayerUrl = serverURL.concat("?service=WMS&request=DescribeLayer&version=1.1.1&layers=").concat(layerName);
        logger.info("#########################DESCRIBE_LAYER_URL : {}\n", decribeLayerUrl);
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(decribeLayerUrl);
            httpClient.executeMethod(getMethod);
            InputStream inputStream = getMethod.getResponseBodyAsStream();
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", FALSE);
            spf.setFeature("http://xml.org/sax/features/validation", FALSE);

            XMLReader xmlReader = spf.newSAXParser().getXMLReader();
            InputSource inputSource = new InputSource(new InputStreamReader(inputStream));
            SAXSource source = new SAXSource(xmlReader, inputSource);
            JAXBContext jaxbContext = JAXBContext.newInstance(WMSDescribeLayerResponse.class);
            WMSDescribeLayerResponse describeLayerResponse = (WMSDescribeLayerResponse) jaxbContext.createUnmarshaller().unmarshal(source);
            return new GPLayerTypeResponse(describeLayerResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServerInternalFault(ex.getMessage());
        }
    }

    /**
     * @param request
     * @return {@link WMSGetFeatureInfoResponse}
     * @throws Exception
     */
    @Override
    public WMSGetFeatureInfoResponse wmsGetFeatureInfo(GPWMSGetFeatureInfoRequest request) throws Exception {
        if (request == null) {
            throw new IllegalParameterFault(this.wmsMessageSource.getMessage("gp_wms_request.valid",
                    new Object[]{"GPWMSGetFeatureInfoRequest"}, ENGLISH));
        }
        logger.trace("##########################Validating Request -------------------> {}\n", request);
        String message = this.wmsRequestValidator.validate(request, forLanguageTag(request.getLang()));
        if (message != null)
            throw new IllegalParameterFault(message);
        return wmsGetFeatureInfoResponseBuilder()
                .withRequest(request)
                .build();
    }
}