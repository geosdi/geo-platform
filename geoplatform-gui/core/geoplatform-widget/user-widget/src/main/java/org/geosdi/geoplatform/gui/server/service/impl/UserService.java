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

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.server.IUserService;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
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

    @Override
    public PagingLoadResult<GPUserManageDetail> searchUsers(PagingLoadConfig config,
            String searchText,
            HttpServletRequest httpServletRequest) {
        GPUser user = this.getCheckLoggedUser(httpServletRequest);

        int start = config.getOffset();

        SearchRequest srq = new SearchRequest(searchText);

        Long usersCount = this.geoPlatformServiceClient.getUsersCount(srq);

        int page = start == 0 ? start : start / config.getLimit();

        PaginatedSearchRequest psr = new PaginatedSearchRequest(searchText,
                config.getLimit(), page);

        List<UserDTO> userList = null;
        try {
            userList = this.geoPlatformServiceClient.searchUsers(user.getId(), psr);
            if (userList == null) {
                throw new GeoPlatformException("There are no results");
            }
        } catch (ResourceNotFoundFault rnnf) {
            throw new GeoPlatformException(rnnf.getMessage()); // TODO Better message

        }

        ArrayList<GPUserManageDetail> searchUsers = new ArrayList<GPUserManageDetail>();
        for (UserDTO userDTO : userList) {
            GPUserManageDetail userDetail = this.convertToGPUserManageDetail(userDTO);
            searchUsers.add(userDetail);
        }

        return new BasePagingLoadResult<GPUserManageDetail>(searchUsers,
                config.getOffset(), usersCount.intValue());
    }

    @Override
    public Long insertUser(IGPUserManageDetail userDetail, String organization, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        this.getCheckLoggedUser(httpServletRequest);

        logger.info("\nUser to insert: {}\nof the organization: {}", userDetail, organization);
        Long iserId = null;
        try {
            GPUser user = this.convertToGPUser(userDetail);
            user.setOrganization(new GPOrganization(organization));

            iserId = geoPlatformServiceClient.insertAccount(user, true);
        } catch (IllegalParameterFault ipf) {
            throw new GeoPlatformException(ipf.getMessage());
        }

        return iserId;
    }

    @Override
    public Long updateUser(IGPUserManageDetail userDetail, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        this.getCheckLoggedUser(httpServletRequest);

        logger.info("User to update: " + userDetail);
        Long userID = null;
        try {
            GPUser user = this.convertToGPUser(userDetail);
            userID = geoPlatformServiceClient.updateUser(user);
        } catch (IllegalParameterFault ipf) {
            throw new GeoPlatformException(ipf.getMessage());
        } catch (ResourceNotFoundFault rnnf) {
            throw new GeoPlatformException(rnnf.getMessage());
        }

        return userID;
    }

    @Override
    public Long updateOwnUser(IGPUserManageDetail userDetail,
            String currentPlainPassword,
            String newPlainPassword,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {

        this.getCheckLoggedUser(httpServletRequest);

        logger.info("Own User to update: " + userDetail);
        Long userID = null;
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userDetail.getId());
            userDTO.setName(userDetail.getName());
            userDTO.setEmailAddress(userDetail.getEmail());

            userID = geoPlatformServiceClient.updateOwnUser(userDTO,
                    currentPlainPassword, newPlainPassword);

            sessionUtility.storeLoggedAccount(this.convertToGPUser(userDetail),
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
        return this.convertToGPUserManageDetail(user);
    }

    // All properties unless the password
    private GPUserManageDetail convertToGPUserManageDetail(UserDTO userDTO) {
        GPUserManageDetail user = new GPUserManageDetail();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmailAddress());
        user.setEnabled(userDTO.isEnabled());
        user.setCreationDate(userDTO.getCreationDate());
        user.setTemporary(userDTO.isTemporary());
        user.setExpired(userDTO.isExpired());
        user.setAuthority(this.convertToAuthority(userDTO.getRoles()));
        user.setOrganization(userDTO.getOrganization());
        return user;
    }

    // NOTE: Now a user must have at most one role
    private String convertToAuthority(List<String> roles) {
        Iterator<String> iterator = roles.iterator();
        String authority = null;
        if (iterator.hasNext()) {
            authority = iterator.next();
        }
        return authority;
    }

    // All properties unless the password
    private GPUserManageDetail convertToGPUserManageDetail(GPUser gpUser) {
        GPUserManageDetail user = new GPUserManageDetail();
        user.setId(gpUser.getId());
        user.setName(gpUser.getName());
        user.setUsername(gpUser.getUsername());
        user.setEmail(gpUser.getEmailAddress());
        user.setCreationDate(gpUser.getCreationDate());
        user.setTemporary(gpUser.isAccountTemporary());
        user.setAuthority(this.convertToGPAuthorities(gpUser.getGPAuthorities()));
        user.setOrganization(gpUser.getOrganization().getName()); // TODO Possibile NullPointerException
        return user;
    }

    // NOTE: Now a user must have at most one role
    private String convertToGPAuthorities(List<GPAuthority> authorities) {
        Iterator<GPAuthority> iterator = authorities.iterator();
        String authority = null;
        if (iterator.hasNext()) {
            authority = iterator.next().getAuthority();
        }
        return authority;
    }

    private GPUser getCheckLoggedUser(HttpServletRequest httpServletRequest) {
        try {
            return (GPUser) sessionUtility.getLoggedAccount(httpServletRequest);
        } catch (GPSessionTimeout timeout) {
            throw new GeoPlatformException(timeout);
        }
    }

    private GPUser convertToGPUser(IGPUserManageDetail userDetail) {
        GPUser user = new GPUser();

        if (userDetail.getId() != null) {
            user.setId(userDetail.getId());
            user.setEnabled(true);
        }

        user.setName(userDetail.getName());
        user.setEmailAddress(userDetail.getEmail());
        user.setUsername(userDetail.getUsername());
        user.setPassword(userDetail.getPassword());
        user.setEnabled(userDetail.isEnabled());
        user.setAccountTemporary(userDetail.isTemporary());

        GPAuthority authority = new GPAuthority();
        authority.setAuthority(userDetail.getAuthority().toString());
        user.setGPAuthorities(Arrays.asList(authority));

        return user;
    }

    @Override
    public ArrayList<String> getAllRoles(HttpServletRequest httpServletRequest) {
        return (ArrayList<String>) geoPlatformServiceClient.getAllRoles();
    }

    @Override
    public ArrayList<String> getAllGuiComponentIDs(HttpServletRequest httpServletRequest) {
        return (ArrayList<String>) geoPlatformServiceClient.getAllGuiComponentIDs();
    }

    @Override
    public HashMap<String, Boolean> getRolePermission(String role,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        HashMap<String, Boolean> permissionMap;

        try {
            GuiComponentsPermissionMapData rolePermission = geoPlatformServiceClient.getRolePermission(role);
            permissionMap = (HashMap<String, Boolean>) rolePermission.getPermissionMap();
        } catch (ResourceNotFoundFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
            throw new GeoPlatformException("Unable to find \"" + role + "\" role");
        }

        return permissionMap;
    }

    @Override
    public boolean updateRolePermission(String role,
            HashMap<String, Boolean> permissionMap,
            HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        GuiComponentsPermissionMapData rolePermission = new GuiComponentsPermissionMapData();
        rolePermission.setPermissionMap(permissionMap);
        try {
            return geoPlatformServiceClient.updateRolePermission(role, rolePermission);
        } catch (ResourceNotFoundFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
            throw new GeoPlatformException("Unable to find \"" + role + "\" role");
        }
    }

    @Override
    public boolean saveRole(String role, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        try {
            return geoPlatformServiceClient.saveRole(role);
        } catch (IllegalParameterFault ex) {
            logger.error(this.getClass().getSimpleName(), ex.getMessage());
            throw new GeoPlatformException("Error on saving the new role \"" + role + "\"");
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
