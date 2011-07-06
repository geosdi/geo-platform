/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.SOAPFaultException;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.ISecurityService;
import org.geosdi.geoplatform.gui.utility.UserLoginEnum;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("securityService")
public class SecurityService implements ISecurityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GeoPlatformService geoPlatformServiceClient;

    @Override
    public String userLogin(String userName, String password, HttpServletRequest httpServletRequest) throws GeoPlatformException {
        GPUser user = null;
        try {
            user = geoPlatformServiceClient.getUserDetailByUsernameAndPassword(userName, password);
        } catch (ResourceNotFoundFault ex) {
            logger.error("SecurityService",
                    "Unable to find user with username: " + userName + " Error: " + ex);
            throw new GeoPlatformException("Unable to find user with username: " + userName);
        } catch (SOAPFaultException ex) {
            logger.error("Error on SecurityService: " + ex + " password incorrect");
            throw new GeoPlatformException("Password incorrect");
        }
        this.storeUserInSession(user, httpServletRequest);
        return user.getEmailAddress();
    }

    private void storeUserInSession(GPUser user, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        //TODO: Set the right time in seconds before session interrupt
        session.setMaxInactiveInterval(900);
        session.setAttribute(UserLoginEnum.USER_LOGGED.toString(), user);
    }

    private GPUser getUserAlreadyFromSession(HttpServletRequest httpServletRequest) {
        GPUser user = null;
        HttpSession session = httpServletRequest.getSession();
        Object userObj = session.getAttribute(UserLoginEnum.USER_LOGGED.toString());
        if (userObj != null && userObj instanceof GPUser) {
            user = (GPUser) userObj;
        }
        return user;
    }

    public GPUser loginFromSessionServer(HttpServletRequest httpServletRequest) {
        return getUserAlreadyFromSession(httpServletRequest);
    }

    private void deleteUserFromSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(UserLoginEnum.USER_LOGGED.toString());
    }


/**
 * @param geoPlatformServiceClient the geoPlatformServiceClient to set
 */
@Autowired
        public void setGeoPlatformServiceClient(
            @Qualifier("geoPlatformServiceClient") GeoPlatformService geoPlatformServiceClient) {
        this.geoPlatformServiceClient = geoPlatformServiceClient;
    }

    @Override
    public void invalidateSession(HttpServletRequest httpServletRequest) throws GeoPlatformException {
        //deleteUserFromSession(httpServletRequest);
        HttpSession session = httpServletRequest.getSession(false);
        session.invalidate();
    }
}
