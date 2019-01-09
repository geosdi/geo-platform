/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("sessionUtility")
public class SessionUtility {

    private static final int SESSIONE_EXPIRATION = 86400;

    public enum SessionProperty {

        LOGGED_ACCOUNT, DEFAULT_PROJECT;
    }

    public Long getDefaultProject(HttpServletRequest httpServletRequest)
            throws GPSessionTimeout {
        HttpSession session = httpServletRequest.getSession();
        Long projectId = (Long) session.getAttribute(SessionProperty.DEFAULT_PROJECT.toString());
        if (projectId != null) {
            return projectId;
        } else {
            System.out.println("\n*** Session Project ID null ***");
            throw new GPSessionTimeout("Session Timeout");
        }
    }

    public GPAccount getLoggedAccount(HttpServletRequest httpServletRequest)
            throws GPSessionTimeout {
        GPAccount account = null;
        HttpSession session = httpServletRequest.getSession();
        Object accountObj = session.getAttribute(SessionProperty.LOGGED_ACCOUNT.toString());
        if (accountObj != null && accountObj instanceof GPAccount) {
            account = (GPAccount) accountObj;
        } else {
            System.out.println("\n*** Session Account null ***");
            throw new GPSessionTimeout("Session Timeout");
        }
        return account;
    }

    public void storeLoggedAccount(GPAccount account,
            HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        //TODO: Set the right time in seconds before session interrupt
        session.setMaxInactiveInterval(SESSIONE_EXPIRATION);
        session.setAttribute(SessionProperty.LOGGED_ACCOUNT.toString(), account);
    }

    public void storeDefaultProject(Long projectID,
            HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        //TODO: Set the right time in seconds before session interrupt
        session.setMaxInactiveInterval(SESSIONE_EXPIRATION);
        session.setAttribute(SessionProperty.DEFAULT_PROJECT.toString(), projectID);
    }

    public void storeLoggedAccountAndDefaultProject(GPAccount account,
            Long projectID, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        //TODO: Set the right time in seconds before session interrupt
        session.setMaxInactiveInterval(SESSIONE_EXPIRATION);
        session.setAttribute(SessionProperty.LOGGED_ACCOUNT.toString(), account);
        session.setAttribute(SessionProperty.DEFAULT_PROJECT.toString(), projectID);
    }
}
