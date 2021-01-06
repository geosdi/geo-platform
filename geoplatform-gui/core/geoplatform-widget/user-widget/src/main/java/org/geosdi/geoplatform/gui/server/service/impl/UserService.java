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
package org.geosdi.geoplatform.gui.server.service.impl;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.server.IUserService;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.server.service.converter.DTOUserConverter;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.response.UserDTO;
import org.geosdi.geoplatform.response.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Service("userService")
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GeoPlatformService geoPlatformServiceClient;
    //
    @Autowired
    private SessionUtility sessionUtility;
    @Autowired
    private DTOUserConverter dtoUserConverter;

    @Override
    public PagingLoadResult<GPUserManageDetail> searchUsers(
            PagingLoadConfig config,
            String searchText, String organization,
            HttpServletRequest httpServletRequest) {
        GPUser user = this.getCheckLoggedUser(httpServletRequest);

        SearchRequest srq = new SearchRequest(searchText);

        Long usersCount = this.geoPlatformServiceClient.getUsersCount(
                organization, srq);

        int start = config.getOffset();
        int page = start == 0 ? start : start / config.getLimit();

        PaginatedSearchRequest psr = new PaginatedSearchRequest(searchText,
                config.getLimit(), page);

        List<UserDTO> userList = null;
        try {
            userList = this.geoPlatformServiceClient.searchUsers(user.getId(),
                    psr).getSearchUsers();
            if (userList == null) {
                throw new GeoPlatformException("There are no results");
            }
        } catch (ResourceNotFoundFault rnnf) {
            throw new GeoPlatformException(rnnf.getMessage()); // TODO Better message
        }

        List<GPUserManageDetail> searchUsers = new ArrayList<GPUserManageDetail>();
        for (UserDTO userDTO : userList) {
            GPUserManageDetail userDetail = this.dtoUserConverter.convertToGPUserManageDetail(
                    userDTO);
            searchUsers.add(userDetail);
        }

        return new BasePagingLoadResult<GPUserManageDetail>(searchUsers,
                config.getOffset(), usersCount.intValue());
    }

    @Override
    public Long insertUser(IGPUserManageDetail userDetail, String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        return this.insertUser(userDetail, organization, httpServletRequest,
                Boolean.TRUE, Boolean.TRUE);
    }

    @Override
    public Long insertUser(IGPUserManageDetail userDetail, String organization,
            HttpServletRequest httpServletRequest, boolean checkUserSession, 
            boolean sendEmail)
            throws GeoPlatformException {
        if (checkUserSession) {
            this.getCheckLoggedUser(httpServletRequest);
        }
        logger.debug("\nUser to INSERT (of the organization \"{}\"):\n{}",
                organization, userDetail);
        Long iserId = null;
        try {
            GPUser user = this.dtoUserConverter.convertToGPUser(userDetail);
            user.setOrganization(new GPOrganization(organization));

            iserId = geoPlatformServiceClient.insertAccount(
                    new InsertAccountRequest(user, sendEmail));
        } catch (IllegalParameterFault ipf) {
            throw new GeoPlatformException(ipf.getMessage());
        }
        return iserId;
    }

    @Override
    public Long updateUser(IGPUserManageDetail userDetail,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        this.getCheckLoggedUser(httpServletRequest);

        logger.debug("\nUser to UPDATE:\n{}", userDetail);
        Long userID = null;
        try {
            GPUser user = this.dtoUserConverter.convertToGPUser(userDetail);
            userID = geoPlatformServiceClient.updateUser(user);
        } catch (IllegalParameterFault ipf) {
            throw new GeoPlatformException(ipf.getMessage());
        } catch (ResourceNotFoundFault rnnf) {
            throw new GeoPlatformException(rnnf.getMessage());
        }

        return userID;
    }

    @Override
    public Long updateUserTreeOptions(IGPTreeOptions userTreeOptions,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GPUser gPUser = this.getCheckLoggedUser(httpServletRequest);
        logger.debug("\nUserTreeOptions to UPDATE:\n{}", userTreeOptions);
        Long accountProjectID = null;
        try {
            gPUser.setLoadExpandedFolders(
                    userTreeOptions.isLoadExpandedFolders());
            accountProjectID = geoPlatformServiceClient.updateUser(gPUser);
        } catch (IllegalParameterFault ipf) {
            throw new GeoPlatformException(ipf.getMessage());
        } catch (ResourceNotFoundFault rnnf) {
            throw new GeoPlatformException(rnnf.getMessage());
        }
        return accountProjectID;
    }

    @Override
    public Long updateOwnUser(IGPUserManageDetail userDetail,
            String currentPlainPassword,
            String newPlainPassword,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {

        this.getCheckLoggedUser(httpServletRequest);

        logger.debug("\nOWN User to update:\n{}", userDetail);
        Long userID = null;
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userDetail.getId());
            userDTO.setName(userDetail.getName());
            userDTO.setEmailAddress(userDetail.getEmail());

            userID = geoPlatformServiceClient.updateOwnUser(userDTO,
                    currentPlainPassword, newPlainPassword);

            sessionUtility.storeLoggedAccount(
                    this.dtoUserConverter.convertToGPUser(userDetail),
                    httpServletRequest);
        } catch (IllegalParameterFault ipf) {
            throw new GeoPlatformException(ipf.getMessage());
        } catch (ResourceNotFoundFault rnnf) {
            throw new GeoPlatformException(rnnf.getMessage());
        }

        return userID;
    }

    @Override
    public boolean deleteUser(Long userID, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        this.getCheckLoggedUser(httpServletRequest);

        try {
            return geoPlatformServiceClient.deleteAccount(userID);
        } catch (ResourceNotFoundFault ex) {
            logger.error("\n*** " + ex.getMessage());
            throw new GeoPlatformException("User not found");
        }
    }

    @Override
    public IGPUserManageDetail getOwnUser(HttpServletRequest httpServletRequest) {
        GPUser user = this.getCheckLoggedUser(httpServletRequest);
        return this.dtoUserConverter.convertToGPUserManageDetail(user);
    }

    private GPUser getCheckLoggedUser(HttpServletRequest httpServletRequest) {
        try {
            return (GPUser) sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
    }

    @Override
    public ArrayList<String> getAllRoles(String organization,
            HttpServletRequest httpServletRequest) {
        ArrayList<String> roles = null;
        try {
            roles = (ArrayList<String>) geoPlatformServiceClient.getAllRoles(
                    organization).getRoles();
        } catch (ResourceNotFoundFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
        }
        return roles;
    }

    @Override
    public ArrayList<String> getAllGuiComponentIDs(
            HttpServletRequest httpServletRequest) {
        return (ArrayList<String>) geoPlatformServiceClient.getAllGuiComponentIDs();
    }

    @Override
    public HashMap<String, Boolean> getRolePermission(String role,
            String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        HashMap<String, Boolean> permissionMap;

        try {
            GuiComponentsPermissionMapData rolePermission = geoPlatformServiceClient.getRolePermission(
                    role, organization);
            permissionMap = (HashMap<String, Boolean>) rolePermission.getPermissionMap();
        } catch (ResourceNotFoundFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
            throw new GeoPlatformException(
                    "Unable to find \"" + role + "\" role");
        }

        return permissionMap;
    }

    @Override
    public boolean updateRolePermission(String role, String organization,
            HashMap<String, Boolean> permissionMap,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GuiComponentsPermissionMapData rolePermission = new GuiComponentsPermissionMapData();
        rolePermission.setPermissionMap(permissionMap);
        try {
            return geoPlatformServiceClient.updateRolePermission(
                    new WSPutRolePermissionRequest(rolePermission, role,
                            organization));
        } catch (ResourceNotFoundFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
            throw new GeoPlatformException(
                    "Unable to find \"" + role + "\" role");
        }
    }

    @Override
    public boolean saveRole(String role, String organization,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            return geoPlatformServiceClient.saveRole(new WSSaveRoleRequest(role,
                    organization));
        } catch (IllegalParameterFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
            throw new GeoPlatformException(
                    "Error on saving the new role \"" + role + "\"");
        }
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
