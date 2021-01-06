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

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.HashMap;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.server.IUserService;
import org.geosdi.geoplatform.gui.server.spring.xsrf.GPAutoInjectingXsrfTokenServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UserRemoteImpl extends GPAutoInjectingXsrfTokenServiceServlet
        implements UserRemote {

    private static final long serialVersionUID = 6832264543827688477L;
    //
    @Autowired
    private IUserService userService;

    @Override
    public PagingLoadResult<GPUserManageDetail> searchUsers(PagingLoadConfig config,
            String searchText, String organization) {
        return userService.searchUsers(config, searchText, organization, super.getThreadLocalRequest());
    }

    @Override
    public Long insertUser(IGPUserManageDetail userDetail, String organization)
            throws GeoPlatformException {
        return userService.insertUser(userDetail, organization, super.getThreadLocalRequest());
    }

    @Override
    public Long insertUser(IGPUserManageDetail userDetail, String organization,
            boolean checkUserSession, boolean sendEmail) throws GeoPlatformException {
        return userService.insertUser(userDetail, organization, 
                super.getThreadLocalRequest(), checkUserSession, sendEmail);
    }

    @Override
    public Long updateUser(IGPUserManageDetail userDetail)
            throws GeoPlatformException {
        return userService.updateUser(userDetail, super.getThreadLocalRequest());
    }

    @Override
    public Long updateUserTreeOptions(IGPTreeOptions userTreeOptions)
            throws GeoPlatformException {
        return userService.updateUserTreeOptions(userTreeOptions, super.getThreadLocalRequest());
    }

    @Override
    public Long updateOwnUser(IGPUserManageDetail userDetail,
            String currentPlainPassword, String newPlainPassword)
            throws GeoPlatformException {
        return userService.updateOwnUser(userDetail, currentPlainPassword,
                                         newPlainPassword, super.getThreadLocalRequest());
    }

    @Override
    public boolean deleteUser(Long userID) throws GeoPlatformException {
        return userService.deleteUser(userID, super.getThreadLocalRequest());
    }

    @Override
    public IGPUserManageDetail getOwnUser() {
        return userService.getOwnUser(super.getThreadLocalRequest());
    }

    @Override
    public ArrayList<String> getAllRoles(String organization) {
        return userService.getAllRoles(organization, super.getThreadLocalRequest());
    }

    @Override
    public ArrayList<String> getAllGuiComponentIDs() {
        return userService.getAllGuiComponentIDs(super.getThreadLocalRequest());
    }

    @Override
    public HashMap<String, Boolean> getRolePermission(String role, String organization)
            throws GeoPlatformException {
        return userService.getRolePermission(role, organization, super.getThreadLocalRequest());
    }

    @Override
    public boolean updateRolePermission(String role, String organization, HashMap<String, Boolean> permissionMap)
            throws GeoPlatformException {
        return userService.updateRolePermission(role, organization, permissionMap, super.getThreadLocalRequest());
    }

    @Override
    public boolean saveRole(String role, String organization) throws GeoPlatformException {
        return userService.saveRole(role, organization, super.getThreadLocalRequest());
    }
}
