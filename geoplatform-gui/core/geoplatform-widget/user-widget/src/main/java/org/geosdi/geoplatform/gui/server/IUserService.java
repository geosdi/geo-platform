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
package org.geosdi.geoplatform.gui.server;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface IUserService {

    PagingLoadResult<GPUserManageDetail> searchUsers(PagingLoadConfig config,
            String searchText, String organization,
            HttpServletRequest httpServletRequest);

    Long insertUser(IGPUserManageDetail userDetail, String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    Long insertUser(IGPUserManageDetail userDetail, String organization,
            HttpServletRequest httpServletRequest, boolean checkUserSession, boolean sendEmail)
            throws GeoPlatformException;

    Long updateUserTreeOptions(IGPTreeOptions userTreeOptions,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    Long updateUser(IGPUserManageDetail userDetail,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    Long updateOwnUser(IGPUserManageDetail userDetail,
            String currentPlainPassword, String newPlainPassword,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    boolean deleteUser(Long userID, HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    IGPUserManageDetail getOwnUser(HttpServletRequest httpServletRequest);

    ArrayList<String> getAllRoles(String organization, HttpServletRequest httpServletRequest);

    ArrayList<String> getAllGuiComponentIDs(HttpServletRequest httpServletRequest);

    HashMap<String, Boolean> getRolePermission(String role, String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    boolean updateRolePermission(String role, String organization, HashMap<String, Boolean> permissionMap,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    boolean saveRole(String role, String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException;
}