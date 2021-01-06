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
package org.geosdi.geoplatform.gui.server.gwt;

import org.geosdi.geoplatform.gui.client.service.SecurityRemote;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.server.ISecurityService;
import org.geosdi.geoplatform.gui.server.command.login.basic.BasicLoginCommand;
import org.geosdi.geoplatform.gui.server.command.login.sso.SSOLoginCommand;
import org.geosdi.geoplatform.gui.server.spring.GPAutoInjectingRemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 */
public class SecurityRemoteImpl extends GPAutoInjectingRemoteServiceServlet
        implements SecurityRemote {

    private static final long serialVersionUID = -1494707375482103152L;
    //
    @Autowired
    private ISecurityService securityService;

    @Override
    @Deprecated
    public IGPAccountDetail userLogin(String userName, String password, Long projectID) throws
            GeoPlatformException {
        return this.securityService.userLogin(userName, password, projectID, 
                super.getThreadLocalRequest());
    }

    /**
     * @see SSOLoginCommand
     * @return
     * @throws GeoPlatformException
     * @deprecated
     *
     */
    @Override
    @Deprecated
    public IGPAccountDetail ssoLogin() throws GeoPlatformException {
        return this.securityService.ssoLogin(super.getThreadLocalRequest());
    }

    /**
     * @see BasicLoginCommand
     * @return
     * @throws GeoPlatformException
     * @deprecated
     *
     */
    @Override
    @Deprecated
    public IGPAccountDetail casLogin() throws GeoPlatformException {
        return this.securityService.casLogin(super.getThreadLocalRequest());
    }

    @Override
    @Deprecated
    public IGPAccountDetail applicationLogin(String appID) throws
            GeoPlatformException {
        return this.securityService.applicationLogin(appID, super.
                getThreadLocalRequest());
    }

    @Override
    @Deprecated
    public void invalidateSession() throws GeoPlatformException {
        this.securityService.invalidateSession(super.getThreadLocalRequest());
    }
}
