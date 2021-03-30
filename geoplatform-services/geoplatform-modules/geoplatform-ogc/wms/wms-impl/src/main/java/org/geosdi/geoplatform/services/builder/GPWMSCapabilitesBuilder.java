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
package org.geosdi.geoplatform.services.builder;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.services.builder.GPRasterLayerDTOBuilder.RasterLayerDTOBuilder;
import org.geosdi.geoplatform.services.request.WMSHeaderParam;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.ows.ServiceException;
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
    public List<RasterLayerDTO> loadWMSCapabilities(@Nonnull(when = NEVER) String serverUrl, @Nullable String token,
            @Nullable String authkey, @Nullable String userName, @Nullable String password) throws ResourceNotFoundFault {
        checkArgument((serverUrl != null) && !(serverUrl.trim().isEmpty()), "The Parameter serverURL must not be null or an empty string.");
        String urlServerEdited = this.editServerUrl(serverUrl, token, authkey);
        logger.debug("####################URL Server edited: {}\n", urlServerEdited);
        try {
            URL serverURL = new URL(urlServerEdited);
            GeoSDIHttpClient5 authClient = new GeoSDIHttpClient5();
            authClient.setUser(userName);
            authClient.setPassword(password);
            WebMapServer wms = new WebMapServer(serverURL, authClient);
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
            @Nullable String authkey, @Nullable List<WMSHeaderParam> headers, @Nullable String userName, @Nullable String password) throws ResourceNotFoundFault {
        checkArgument((serverUrl != null) && !(serverUrl.trim().isEmpty()), "The Parameter serverURL must not be null or an empty string.");
        String urlServerEdited = this.editServerUrl(serverUrl, token, authkey);
        logger.debug("####################URL Server edited: {}\n", urlServerEdited);
        logger.info("Forward headers:  {}", headers);
        try {
            URL serverURL = new URL(urlServerEdited);
            GeoSDIHttpClient5 authClient = new GeoSDIHttpClient5();
            authClient.setUser(userName);
            authClient.setPassword(password);
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