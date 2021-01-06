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
package org.geosdi.geoplatform.gui.server.command.login.cas;

import org.geosdi.geoplatform.gui.client.command.login.cas.XMPPCASGetDataLoginRequest;
import org.geosdi.geoplatform.gui.client.command.login.xmpp.XMPPGetDataLoginResponse;
import org.geosdi.geoplatform.gui.client.model.security.XMPPLoginDetails;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.server.ISecurityService;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Lazy(true)
@Component(value = "command.login.XMPPCASGetDataLoginCommand")
@Profile(value = "cas")
public class XMPPCASGetDataLoginCommand implements
        GPCommand<XMPPCASGetDataLoginRequest, XMPPGetDataLoginResponse> {

    private static final Logger logger = LoggerFactory.getLogger(XMPPCASGetDataLoginCommand.class);
    //
    @Autowired
    private ISecurityService securityService;

    @Override
    public XMPPGetDataLoginResponse execute(XMPPCASGetDataLoginRequest request, HttpServletRequest httpServletRequest) {
        Assertion assertion = (AssertionImpl) httpServletRequest.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (assertion == null) {
            logger.error("Cas assertion is null");
        }
        String userName = assertion.getPrincipal().getName();
        logger.info("Username retrieved: " + userName);
        XMPPLoginDetails xMPPLoginDetails = this.securityService.xmppGetDataLogin(userName, httpServletRequest);
        logger.info(xMPPLoginDetails.toString());
        return new XMPPGetDataLoginResponse(xMPPLoginDetails);
    }

    @PostConstruct
    public void postConstruct() {
        checkNotNull(securityService, "The SecurityService must not be null.");
    }
}
