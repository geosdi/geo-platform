/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.services;

import com.google.common.collect.Lists;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.v111.GPWMSGetFeatureInfoV111Request;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.httpclient.GeoSDIHttpClient;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoElement;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geosdi.geoplatform.services.response.GPLayerTypeResponse;
import org.geosdi.geoplatform.services.response.WMSGetFeatureInfoResponse;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;
import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.StyleImpl;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.ows.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.forLanguageTag;
import static org.geosdi.geoplatform.connector.pool.builder.v111.GPWMSConnectorBuilderPoolV111.wmsConnectorBuilderPoolV111;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;

public class GPWMSServiceImpl implements GPWMSService {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSServiceImpl.class);
    //
    private final static String EPSG_4326 = "EPSG:4326";
    private final static String EPSG_3857 = "EPSG:3857";
    private final static String EPSG_GOOGLE = "EPSG:900913";
    private static final String GEB = "earthbuilder.google.com";
    // DAO
    @Resource(name = "serverDAO")
    private GPServerDAO serverDao;
    @Resource(name = "wmsMessageSource")
    private MessageSource wmsMessageSource;
    @Resource(name = "wmsRequestValidator")
    private GPI18NValidator<GPI18NRequestValidator, String> wmsRequestValidator;

    /**
     * @param serverUrl
     * @param request
     * @param token
     * @param authkey
     * @return {@link ServerDTO}
     * @throws ResourceNotFoundFault
     */
    @Override
    public ServerDTO getCapabilities(String serverUrl, RequestByID request, String token, String authkey)
            throws ResourceNotFoundFault {
        ServerDTO serverDTO;
        if (request.getId() != null) {
            GeoPlatformServer server = serverDao.find(request.getId());
            if (server == null) {
                throw new ResourceNotFoundFault("Server has been deleted",
                        request.getId());
            }
            serverDTO = new ServerDTO(server);
        } else {
            serverDTO = new ServerDTO();
            serverDTO.setServerUrl(serverUrl);
        }
        WMSCapabilities wmsCapabilities = this.getWMSCapabilities(serverUrl, token, authkey);
        List<RasterLayerDTO> layers = this.convertToLayerList(wmsCapabilities.getLayer(), serverUrl);
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
     * @throws ResourceNotFoundFault
     */
    @Override
    public ServerDTO getCapabilitiesAuth(String serverUrl, RequestByID request, String token, String authkey,
            List<WMSHeaderParam> headers) throws ResourceNotFoundFault {
        ServerDTO serverDTO;
        if (request.getId() != null) {
            GeoPlatformServer server = serverDao.find(request.getId());
            if (server == null) {
                throw new ResourceNotFoundFault("Server has been deleted",
                        request.getId());
            }
            serverDTO = new ServerDTO(server);
        } else {
            serverDTO = new ServerDTO();
            serverDTO.setServerUrl(serverUrl);
        }
        WMSCapabilities wmsCapabilities = this.getWMSCapabilitiesAuth(serverUrl, token, authkey, headers);
        List<RasterLayerDTO> layers = this.convertToLayerList(wmsCapabilities.getLayer(), serverUrl);
        serverDTO.setLayerList(layers);
        return serverDTO;
    }

    @Override
    public ServerDTO getShortServer(String serverUrl)
            throws ResourceNotFoundFault {
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
     * @return {@link Response}
     * @throws Exception
     */
    @WebMethod(exclude = true)
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
        WMSGetFeatureInfoResponse wmsGetFeatureInfoResponse = new WMSGetFeatureInfoResponse();
        for (GPWMSGetFeatureInfoElement wmsGetFeatureInfoElement : request.getWmsFeatureInfoElements()) {
            try {
                IGPWMSConnectorStoreV111 wmsServerConnector = wmsConnectorBuilderPoolV111()
                        .withServerUrl(new URL(wmsGetFeatureInfoElement.getWmsServerURL()))
                        .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                                .withMaxTotalConnections(60)
                                .withDefaultMaxPerRoute(30)
                                .withMaxRedirect(15)
                                .build()).build();
                GPWMSGetFeatureInfoV111Request<Object> wmsGetFeatureInfoV111Request = wmsServerConnector.createGetFeatureInfoRequest();
                GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(request.getBoundingBox().toWMSBoundingBox(),
                        wmsGetFeatureInfoElement.getLayers(), request.getCrs(), request.getWidth(), request.getHeight());
                FeatureCollection featureCollection = (FeatureCollection) wmsGetFeatureInfoV111Request
                        .withFeatureCount(20)
                        .withQueryLayers(wmsGetFeatureInfoElement.getLayers().stream().toArray(s -> new String[s]))
                        .withX(request.getPoint().getX())
                        .withY(request.getPoint().getY())
                        .withInfoFormat(WMSFeatureInfoFormat.JSON)
                        .getResponse();
                logger.debug("########################FOUND : {}\n", featureCollection);
                wmsGetFeatureInfoResponse.addFeatureCollection(featureCollection);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return wmsGetFeatureInfoResponse;
    }

    private boolean isURLServerAlreadyExists(String serverUrl) {
        return serverDao.findByServerUrl(serverUrl) == null ? false : true;
    }

    private WMSCapabilities getWMSCapabilities(String serverUrl, String token,
            String authkey) throws ResourceNotFoundFault {
        URL serverURL;
        WebMapServer wms;
        WMSCapabilities cap = null;

        String urlServerEdited = this.editServerUrl(serverUrl, token, authkey);
        logger.debug("####################URL Server edited: {}\n", urlServerEdited);

        try {
            serverURL = new URL(urlServerEdited);
            wms = new WebMapServer(serverURL);
            cap = wms.getCapabilities();
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: " + e);
            throw new ResourceNotFoundFault("Malformed URL");
        } catch (ServiceException e) {
            logger.error("ServiceException: " + e);
            throw new ResourceNotFoundFault("Invalid URL");
        } catch (IOException e) {
            logger.error("IOException: " + e);
            throw new ResourceNotFoundFault("Inaccessible URL");
        } catch (Exception e) {
            logger.error("Exception: " + e);
            throw new ResourceNotFoundFault("Incorrect URL");
        }
        return cap;
    }

    /**
     * @param serverUrl
     * @param token
     * @param authkey
     * @param headers
     * @return {@link WMSCapabilities}
     * @throws ResourceNotFoundFault
     */
    private WMSCapabilities getWMSCapabilitiesAuth(String serverUrl, String token,
            String authkey, List<WMSHeaderParam> headers) throws ResourceNotFoundFault {
        URL serverURL;
        WebMapServer wms;
        WMSCapabilities cap = null;

        String urlServerEdited = this.editServerUrl(serverUrl, token, authkey);
        logger.debug("####################URL Server edited: {}\n", urlServerEdited);
        logger.info("Forward headers:  " + headers);

        try {
            serverURL = new URL(urlServerEdited);
            GeoSDIHttpClient authClient = new GeoSDIHttpClient();
            authClient.setHeaders(headers);
            wms = new WebMapServer(serverURL, authClient);
            cap = wms.getCapabilities();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new ResourceNotFoundFault("Malformed URL");
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ResourceNotFoundFault("Invalid URL");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourceNotFoundFault("Inaccessible URL");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundFault("Incorrect URL");
        }
        return cap;
    }

    private String editServerUrl(String urlServer, String token, String authkey) {
        StringBuilder stringBuilder = new StringBuilder(urlServer);
        if (!urlServer.contains("?")) {
            stringBuilder.append("?request=GetCapabilities");
        }

        if (urlServer.contains(GEB)) {
            stringBuilder.append("&access_token=").append(token);
        }
        if (authkey != null) {
            stringBuilder.append("&authkey=").append(authkey);
        }
        return stringBuilder.toString();
    }

    private List<RasterLayerDTO> convertToLayerList(Layer layer,
            String urlServer) {
        List<RasterLayerDTO> shortLayers = Lists.<RasterLayerDTO>newArrayList();

        RasterLayerDTO raster = this.getRasterAndSubRaster(layer, layer, urlServer);
        shortLayers.add(raster);

        return shortLayers;
    }

    private RasterLayerDTO getRasterAndSubRaster(Layer ancestorLayer,
            Layer layer, String urlServer) {
        RasterLayerDTO raster = this.convertLayerToRaster(ancestorLayer, layer, urlServer);

        List<Layer> subLayerList = layer.getLayerChildren();
        List<RasterLayerDTO> subRasterList = Lists.<RasterLayerDTO>newArrayListWithExpectedSize(
                subLayerList.size());
        raster.setSubLayerList(subRasterList);

        // ADD subRaster
        for (Layer layerIth : subLayerList) {
            RasterLayerDTO rasterIth = this.getRasterAndSubRaster(ancestorLayer, layerIth,
                    urlServer);
            subRasterList.add(rasterIth);
        }

        return raster;
    }

    private RasterLayerDTO convertLayerToRaster(Layer ancestorLayer, Layer layer,
            String urlServer) {
        RasterLayerDTO raster = new RasterLayerDTO();

        raster.setUrlServer(this.getUrlServer(urlServer));
        raster.setName(layer.getName());
        raster.setAbstractText(layer.get_abstract());
        if (layer.getTitle() == null || layer.getTitle().trim().equals("")) {
            raster.setTitle(layer.getName());
        } else {
            raster.setTitle(layer.getTitle());
        }
        Map<String, CRSEnvelope> additionalBounds = layer.getBoundingBoxes();

        logger.debug("ADDITIONAL BOUNDS ###############################"
                + additionalBounds.toString());

        if (!additionalBounds.isEmpty()) {
            if (additionalBounds.containsKey(EPSG_GOOGLE)
                    || additionalBounds.containsKey(EPSG_3857)) {
                CRSEnvelope env = additionalBounds
                        .get(EPSG_GOOGLE);
                if (env == null) {
                    env = additionalBounds.get(EPSG_3857);
                }
                raster.setBbox(this.createBbox(env));
                raster.setSrs(env.getEPSGCode());
            } else {
                raster.setBbox(this.createBbox(layer.getLatLonBoundingBox()));
                raster.setSrs(EPSG_4326);
            }

        } else {
            additionalBounds = ancestorLayer.getBoundingBoxes();
            if (additionalBounds.containsKey(EPSG_GOOGLE)
                    || additionalBounds.containsKey(EPSG_3857)) {
                CRSEnvelope env = additionalBounds
                        .get(EPSG_GOOGLE);
                if (env == null) {
                    env = additionalBounds.get(EPSG_3857);
                }
                raster.setBbox(this.createBbox(env));
                raster.setSrs(env.getEPSGCode());
                logger.info("GOOGLE");
            } else {
                raster.setBbox(this.createBbox(ancestorLayer.getLatLonBoundingBox()));
                raster.setSrs(EPSG_4326);
                logger.info("4326");
            }

        }

        logger.debug("Raster Name: " + raster.getName());
        logger.debug("Raster BBOX: " + raster.getBbox());
        logger.debug("Raster SRS: " + raster.getSrs());

        if (urlServer.contains(GEB)) {
            if (layer.getLatLonBoundingBox() != null) {
                raster.setBbox(this.createBbox(layer.getLatLonBoundingBox()));
            }
            raster.setSrs(EPSG_4326);
        }

        // Set LayerInfo of Raster Ith
        GPLayerInfo layerInfo = new GPLayerInfo();
        layerInfo.setQueryable(layer.isQueryable());
        if (layer.getKeywords() != null) {
            List<String> keywordList = Arrays.asList(layer.getKeywords());
            if (keywordList.size() > 0) {
                layerInfo.setKeywords(keywordList);
            }
        }
        raster.setLayerInfo(layerInfo);

        // Set Styles of Raster Ith
        List<StyleImpl> stylesImpl = layer.getStyles();
        logger.debug(
                "\n*** Layer \"{}\" has {} SubLayers and {} StyleImpl ***",
                layer.getTitle(), layer.getLayerChildren().size(),
                stylesImpl.size());

        raster.setStyleList(this.createStyleList(stylesImpl));

        return raster;
    }

    private List<ServerDTO> convertToServerList(
            List<GeoPlatformServer> serverList) {
        List<ServerDTO> shortServers = new ArrayList<ServerDTO>(
                serverList.size());
        ServerDTO serverDTOIth = null;
        for (GeoPlatformServer server : serverList) {
            serverDTOIth = new ServerDTO(server);
            shortServers.add(serverDTOIth);
        }
        return shortServers;
    }

    private List<String> createStyleList(List<StyleImpl> stylesImpl) {
        List<String> styleList = new ArrayList<String>(stylesImpl.size());
        for (StyleImpl style : stylesImpl) {
            styleList.add(style.getName());
        }
        return styleList;
    }

    private GPBBox createBbox(CRSEnvelope env) {
        return ((env != null) ? new GPBBox(env.getMinX(), env.getMinY(),
                env.getMaxX(),
                env.getMaxY()) : new GPBBox(-179, -89, 179, 89));
    }

    private String getUrlServer(String urlServer) {
        int index = -1;
        if (urlServer.contains(".map")) {
            index = urlServer.indexOf(".map") + 4;
        } else if (urlServer.contains("mapserv.exe")
                || urlServer.contains("mapserver")
                || urlServer.contains("mapserv")
                || urlServer.contains("usertoken")
                || urlServer.contains("map")) {
            index = urlServer.indexOf("&");
        } else {
            index = urlServer.indexOf("?");
            // index += 1;
        }
        if (index != -1) {
            String newUrl = urlServer.substring(0, index);
            return newUrl;
        }
        return urlServer;
    }

    private double longToSphericalMercatorX(double x) {
        return (x / 180.0) * 20037508.34;
    }

    private double latToSphericalMercatorY(double y) {
        if (y > 85.05112) {
            y = 85.05112;
        }

        if (y < -85.05112) {
            y = -85.05112;
        }

        y = (Math.PI / 180.0) * y;
        double tmp = Math.PI / 4.0 + y / 2.0;
        return 20037508.34 * Math.log(Math.tan(tmp)) / Math.PI;
    }
}
