/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import jakarta.annotation.Resource;
import org.geosdi.geoplatform.configurator.crypt.GPPooledPBEStringEncryptorDecorator;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.List;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.forLanguageTag;
import static org.geosdi.geoplatform.services.builder.GPWMSDescribeLayerBuilder.WMSDescribeLayerBuilder.wmsDescribeLayerBuilder;
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
    @Autowired
    private GPPooledPBEStringEncryptorDecorator pooledPBEStringEncryptorDecorator;
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
        String userName = null;
        String password = null;
        if (request.getId() != null) {
            GeoPlatformServer server = serverDao.find(request.getId());
            if (server == null) {
                throw new ResourceNotFoundFault("Server has been deleted", request.getId());
            }
            serverDTO = new ServerDTO(server);
            if (server.isProtected()) {
                userName = server.getAuthServer().getUsername();
                password = server.getAuthServer().getPassword();
            }
        } else {
            serverDTO = new ServerDTO();
            serverDTO.setServerUrl(serverUrl);
        }
        List<RasterLayerDTO> layers = this.wmsCapabilitiesBuilder.loadWMSCapabilities(serverUrl, token, authkey, userName, password);
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
        String userName = null;
        String password = null;
        if (request.getId() != null) {
            GeoPlatformServer server = serverDao.find(request.getId());
            if (server == null) {
                throw new ResourceNotFoundFault("Server has been deleted", request.getId());
            }
            serverDTO = new ServerDTO(server);
            if (server.isProtected()) {
                userName = server.getAuthServer().getUsername();
                password = this.pooledPBEStringEncryptorDecorator.decrypt(server.getAuthServer().getPassword());
            }
        } else {
            serverDTO = new ServerDTO();
            serverDTO.setServerUrl(serverUrl);
        }
        List<RasterLayerDTO> layers = this.wmsCapabilitiesBuilder.loadWMSCapabilitiesAuth(serverUrl, token, authkey, headers, userName, password);
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
        try {
           return wmsDescribeLayerBuilder().withServerURL(serverURL).withLayerName(layerName).build();
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