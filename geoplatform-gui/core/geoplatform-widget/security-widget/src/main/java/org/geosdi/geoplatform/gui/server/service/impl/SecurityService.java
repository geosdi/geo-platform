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
package org.geosdi.geoplatform.gui.server.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.SOAPFaultException;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.security.GPLoginUserDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.server.ISecurityService;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.server.SessionUtility.SessionProperty;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    //
    private GeoPlatformService geoPlatformServiceClient;
    //
    @Autowired
    private SessionUtility sessionUtility;

    @Override
    public IGPAccountDetail userLogin(String userName, String password,
                                      HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GPUser user = null;
        GuiComponentsPermissionMapData guiComponentPermission = null;
        try {
            user = geoPlatformServiceClient.getUserDetailByUsernameAndPassword(
                    userName, password);

            guiComponentPermission = geoPlatformServiceClient.getAccountPermission(
                    user.getId());
        } catch (ResourceNotFoundFault ex) {
            logger.error("SecurityService",
                         "Unable to find user with username: " + userName
                    + " Error: " + ex);
            throw new GeoPlatformException("Unable to find user with username: "
                    + userName);
        } catch (SOAPFaultException ex) {
            logger.error("Error on SecurityService: " + ex + " password incorrect");
            throw new GeoPlatformException("Password incorrect");
        } catch (IllegalParameterFault ex) {
            logger.error("Error on SecurityService: " + ex);
            throw new GeoPlatformException("Parameter incorrect");
        } catch (AccountLoginFault ex) {
            logger.error("Error on SecurityService: " + ex);
            throw new GeoPlatformException(ex.getMessage() + ", contact the administrator");
        }

        if (user.getDefaultProjectID() == null) {
            GPProject project = new GPProject();
            project.setName("Default Project");
            project.setShared(false);
            project.setId(this.saveDefaultProject(user, project));
        }

        this.sessionUtility.storeLoggedAccountAndDefaultProject(user,
                                                                user.getDefaultProjectID(),
                                                                httpServletRequest);

        IGPAccountDetail userDetail = this.convertAccountToDTO(user);

        userDetail.setComponentPermission(guiComponentPermission.getPermissionMap());

        return userDetail;
    }

    @Override
    public IGPAccountDetail applicationLogin(String appID,
                                             HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GPApplication application = null;
        GuiComponentsPermissionMapData guiComponentPermission = null;
        try {
            application = geoPlatformServiceClient.getApplication(appID);

            guiComponentPermission = geoPlatformServiceClient.getApplicationPermission(
                    application.getAppID());
        } catch (ResourceNotFoundFault ex) {
            logger.error("SecurityService",
                         "Unable to find application with appID: " + appID
                    + " Error: " + ex);
            throw new GeoPlatformException("Unable to find application with appID: "
                    + appID);
        } catch (AccountLoginFault ex) {
            logger.error("Error on SecurityService: " + ex);
            throw new GeoPlatformException(ex.getMessage() + ", contact the administrator");
        }

        if (application.getDefaultProjectID() == null) {
            GPProject project = new GPProject();
            project.setName("Default Project");
            project.setShared(false);
            project.setId(this.saveDefaultProject(application, project));
        }

        this.sessionUtility.storeLoggedAccountAndDefaultProject(application,
                                                                application.getDefaultProjectID(),
                                                                httpServletRequest);

        IGPAccountDetail accountDetail = this.convertAccountToDTO(application);

        accountDetail.setComponentPermission(guiComponentPermission.getPermissionMap());

        return accountDetail;
    }

    public GPAccount loginFromSessionServer(HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GPAccount account = null;
        try {
            account = this.sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
        return account;
    }

    private void deleteUserFromSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(SessionProperty.LOGGED_ACCOUNT.toString());
    }

    @Override
    public void invalidateSession(HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        //deleteUserFromSession(httpServletRequest);
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    private Long saveDefaultProject(GPAccount account, GPProject project)
            throws GeoPlatformException {
        Long idProject = null;
        try {
            idProject = this.geoPlatformServiceClient.saveProject(account.getStringID(),
                                                                  project, true);
            account.setDefaultProjectID(idProject);
        } catch (ResourceNotFoundFault rnf) {
            this.logger.error("Failed to save project on SecurityService: " + rnf);
            throw new GeoPlatformException(rnf);
        } catch (IllegalParameterFault ilg) {
            logger.error("Error on SecurityService: " + ilg);
            throw new GeoPlatformException("Parameter incorrect on saveProject");
        }
        return idProject;
    }

    private IGPAccountDetail convertAccountToDTO(GPAccount account) {
        GPLoginUserDetail accountDetail = new GPLoginUserDetail();
        accountDetail.setUsername(account.getStringID()); // Forced representation
        if (account instanceof GPUser) {
            GPUser user = (GPUser) account;
            accountDetail.setName(user.getUsername());
            accountDetail.setEmail(user.getEmailAddress());
        }
        return (IGPAccountDetail) accountDetail;
    }

    /**
     * @param geoPlatformServiceClient the geoPlatformServiceClient to set
     */
    @Autowired
    public void setGeoPlatformServiceClient(
            @Qualifier("geoPlatformServiceClient") GeoPlatformService geoPlatformServiceClient) {
        this.geoPlatformServiceClient = geoPlatformServiceClient;
    }
}
