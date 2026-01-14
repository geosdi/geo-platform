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
package org.geosdi.geoplatform.gui.server;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface IUserService extends InitializingBean {

    /**
     * @param config
     * @param searchText
     * @param organization
     * @param httpServletRequest
     * @return {@link PagingLoadResult<GPUserManageDetail>}
     */
    BasePagingLoadResult<GPUserManageDetail> searchUsers(PagingLoadConfig config, String searchText,
            String organization, HttpServletRequest httpServletRequest);

    /**
     * @param userDetail
     * @param organization
     * @param httpServletRequest
     * @return {@link Long}
     * @throws GeoPlatformException
     */
    Long insertUser(IGPUserManageDetail userDetail, String organization, HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    /**
     * @param userDetail
     * @param organization
     * @param httpServletRequest
     * @param checkUserSession
     * @param sendEmail
     * @return {@link Long}
     * @throws GeoPlatformException
     */
    Long insertUser(IGPUserManageDetail userDetail, String organization, HttpServletRequest httpServletRequest,
            boolean checkUserSession, boolean sendEmail) throws GeoPlatformException;

    /**
     * @param userTreeOptions
     * @param httpServletRequest
     * @return {@link Long}
     * @throws GeoPlatformException
     */
    Long updateUserTreeOptions(IGPTreeOptions userTreeOptions, HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    /**
     * @param userDetail
     * @param httpServletRequest
     * @return {@link Long}
     * @throws GeoPlatformException
     */
    Long updateUser(IGPUserManageDetail userDetail, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    /**
     * @param userDetail
     * @param currentPlainPassword
     * @param newPlainPassword
     * @param httpServletRequest
     * @return {@link Long}
     * @throws GeoPlatformException
     */
    Long updateOwnUser(IGPUserManageDetail userDetail, String currentPlainPassword, String newPlainPassword,
            HttpServletRequest httpServletRequest) throws GeoPlatformException;

    /**
     * @param userID
     * @param httpServletRequest
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    boolean deleteUser(Long userID, HttpServletRequest httpServletRequest) throws GeoPlatformException;

    /**
     * @param httpServletRequest
     * @return {@link IGPUserManageDetail}
     */
    IGPUserManageDetail getOwnUser(HttpServletRequest httpServletRequest);

    /**
     * @param organization
     * @param httpServletRequest
     * @return {@link ArrayList<String>}
     */
    ArrayList<String> getAllRoles(String organization, HttpServletRequest httpServletRequest);

    /**
     * @param httpServletRequest
     * @return {@link ArrayList<String>}
     */
    ArrayList<String> getAllGuiComponentIDs(HttpServletRequest httpServletRequest);

    /**
     * @param role
     * @param organization
     * @param httpServletRequest
     * @return {@link HashMap<String, Boolean>}
     * @throws GeoPlatformException
     */
    HashMap<String, Boolean> getRolePermission(String role, String organization, HttpServletRequest httpServletRequest)
            throws GeoPlatformException;

    /**
     * @param role
     * @param organization
     * @param permissionMap
     * @param httpServletRequest
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    boolean updateRolePermission(String role, String organization, HashMap<String, Boolean> permissionMap,
            HttpServletRequest httpServletRequest) throws GeoPlatformException;

    /**
     * @param role
     * @param organization
     * @param httpServletRequest
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    boolean saveRole(String role, String organization, HttpServletRequest httpServletRequest)
            throws GeoPlatformException;
}