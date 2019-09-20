package org.geosdi.geoplatform.services.builder;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.builder.GPRasterLayerDTOBuilder.RasterLayerDTOBuilder;
import org.geosdi.geoplatform.services.httpclient.GeoSDIHttpClient;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geotools.ows.ServiceException;
import org.geotools.ows.wms.WMSCapabilities;
import org.geotools.ows.wms.WebMapServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.services.builder.GPRasterLayerDTOBuilder.RasterLayerDTOBuilder.GEB;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSCapabilitesBuilder implements IGPWMSCapabilitesBuilder {

    private static final long serialVersionUID = -4258917584592385529L;
    //
    private static final Logger logger = LoggerFactory.getLogger(GPWMSCapabilitesBuilder.class);
    //
    private static final GPRasterLayerDTOBuilder rasterLayerDTOBuilder = new RasterLayerDTOBuilder();

    /**
     * @param serverUrl
     * @param token
     * @param authkey
     * @return {@link ServerDTO}
     * @throws ResourceNotFoundFault
     */
    @Override
    public List<RasterLayerDTO> loadWMSCapabilities(@Nonnull(when = NEVER) String serverUrl, @Nullable String token, @Nullable String authkey) throws ResourceNotFoundFault {
        checkArgument((serverUrl != null) && !(serverUrl.trim().isEmpty()), "The Parameter serverURL must not be null or an empty string.");
        String urlServerEdited = this.editServerUrl(serverUrl, token, authkey);
        logger.debug("####################URL Server edited: {}\n", urlServerEdited);
        try {
            URL serverURL = new URL(urlServerEdited);
            WebMapServer wms = new WebMapServer(serverURL);
            WMSCapabilities wmsCapabilities = wms.getCapabilities();
            return rasterLayerDTOBuilder.convertToLayerList(wmsCapabilities.getLayer(), serverUrl);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: {}", e.getMessage());
            throw new ResourceNotFoundFault("Malformed URL");
        } catch (ServiceException e) {
            logger.error("ServiceException: {}", e.getMessage());
            throw new ResourceNotFoundFault("Invalid URL");
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
            throw new ResourceNotFoundFault("Inaccessible URL");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception: {}", e.getMessage());
            throw new ResourceNotFoundFault("Incorrect URL");
        }
    }

    /**
     * @param serverUrl
     * @param token
     * @param authkey
     * @param headers
     * @return {@link List<RasterLayerDTO>}
     * @throws ResourceNotFoundFault
     */
    @Override
    public List<RasterLayerDTO> loadWMSCapabilitiesAuth(@Nonnull(when = NEVER) String serverUrl, @Nullable String token,
            @Nullable String authkey, @Nullable List<WMSHeaderParam> headers) throws ResourceNotFoundFault {
        checkArgument((serverUrl != null) && !(serverUrl.trim().isEmpty()), "The Parameter serverURL must not be null or an empty string.");
        String urlServerEdited = this.editServerUrl(serverUrl, token, authkey);
        logger.debug("####################URL Server edited: {}\n", urlServerEdited);
        logger.info("Forward headers:  {}", headers);
        try {
            URL serverURL = new URL(urlServerEdited);
            GeoSDIHttpClient authClient = new GeoSDIHttpClient();
            authClient.setHeaders(headers);
            WebMapServer wms = new WebMapServer(serverURL, authClient);
            WMSCapabilities wmsCapabilities = wms.getCapabilities();
            return rasterLayerDTOBuilder.convertToLayerList(wmsCapabilities.getLayer(), serverUrl);
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
    }

    /**
     * @param urlServer
     * @param token
     * @param authkey
     * @return {@link String}
     */
    private String editServerUrl(String urlServer, String token, String authkey) {
        StringBuilder stringBuilder = new StringBuilder(urlServer);
        if (!urlServer.contains("?")) {
            stringBuilder.append("?request=GetCapabilities");
        }
        if (urlServer.contains(GEB) && (token != null)) {
            stringBuilder.append("&access_token=").append(token);
        }
        if (authkey != null) {
            stringBuilder.append("&authkey=").append(authkey);
        }
        return stringBuilder.toString();
    }
}