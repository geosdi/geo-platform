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
package org.geosdi.geoplatform.gui.client.service;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.HashMap;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface UserRemoteAsync {

    void searchUsers(PagingLoadConfig config, String searchText, String organization,
            AsyncCallback<PagingLoadResult<GPUserManageDetail>> callback)
            throws GeoPlatformException;

    void insertUser(IGPUserManageDetail userDetail, String organization, AsyncCallback<Long> callback)
            throws GeoPlatformException;

    void insertUser(IGPUserManageDetail userDetail, String organization, boolean checkUserSession,
            boolean sendEmail, AsyncCallback<Long> callback) throws GeoPlatformException;

    void updateUser(IGPUserManageDetail userDetail, AsyncCallback<Long> callback)
            throws GeoPlatformException;

    void updateUserTreeOptions(IGPTreeOptions userTreeOptions, AsyncCallback<Long> callback)
            throws GeoPlatformException;

    void updateOwnUser(IGPUserManageDetail userDetail,
            String currentPlainPassword, String newPlainPassword,
            AsyncCallback<Long> callback)
            throws GeoPlatformException;

    void deleteUser(Long userID, AsyncCallback<Boolean> callback)
            throws GeoPlatformException;

    void getOwnUser(AsyncCallback<IGPUserManageDetail> callback);

    void getAllRoles(String organization, AsyncCallback<ArrayList<String>> callback);

    void getAllGuiComponentIDs(AsyncCallback<ArrayList<String>> callback);

    void getRolePermission(String role, String organization,
            AsyncCallback<HashMap<String, Boolean>> callback)
            throws GeoPlatformException;

    void updateRolePermission(String role, String organization, HashMap<String, Boolean> permissionMap,
            AsyncCallback<Boolean> callback)
            throws GeoPlatformException;

    void saveRole(String role, String organization, AsyncCallback<Boolean> callback)
            throws GeoPlatformException;
}
