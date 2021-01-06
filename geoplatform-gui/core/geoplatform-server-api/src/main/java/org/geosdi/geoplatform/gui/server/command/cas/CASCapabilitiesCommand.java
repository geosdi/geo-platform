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
package org.geosdi.geoplatform.gui.server.command.cas;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.gui.command.capabilities.basic.BasicCapabilitiesResponse;
import org.geosdi.geoplatform.gui.command.capabilities.cas.CASCapabilitiesRequest;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.server.service.IOGCService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy(true)
@Profile(value = "gs_cas")
@Component(value = "command.capabilities.CasCapabilitiesCommand")
public class CASCapabilitiesCommand implements
        GPCommand<CASCapabilitiesRequest, BasicCapabilitiesResponse> {

    private static final Logger logger = LoggerFactory.getLogger(
            CASCapabilitiesCommand.class);
    //
    @Autowired
    private IOGCService ogcService;
    //
    private @Value("configurator{geoserver_url}")
    String geoserverUrl;

    @Override
    public BasicCapabilitiesResponse execute(CASCapabilitiesRequest request,
            HttpServletRequest httpServletRequest) {
        logger.info("CAS getWMSCapabilities is running...");
        String serverUrl = request.getServerUrl();
        if (serverUrl.contains(geoserverUrl)) {
            Assertion casAssertion = (AssertionImpl) httpServletRequest.
                    getSession().getAttribute(
                    AbstractCasFilter.CONST_CAS_ASSERTION);
            AttributePrincipal attributePrincipal = casAssertion.getPrincipal();
//            String proxyTicket = attributePrincipal.getProxyTicketFor("https://localhost:6443/geoserver/wms?request=GetCapabilities");
            String proxyTicket = attributePrincipal.getProxyTicketFor(
                    geoserverUrl + "/wms?wmtver=1.0.0&request=capabilities");
            logger.debug("Proxy ticket ***************: " + proxyTicket);
            try {
                serverUrl += "?ticket=" + URLEncoder.
                        encode(proxyTicket, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                logger.error("Error on encoding CAS ticket", ex);
            }
            logger.info("serverURL: " + serverUrl);
        }

        logger.debug("##################### Executing {} Command", this.
                getClass().getSimpleName());

        ArrayList<? extends GPLayerGrid> capabilitiesResult = this.ogcService.getCapabilities(
                serverUrl, httpServletRequest, request.getIdServer());

        logger.debug("##################### FOUND {} ", capabilitiesResult);

        return new BasicCapabilitiesResponse(capabilitiesResult);
    }
}
