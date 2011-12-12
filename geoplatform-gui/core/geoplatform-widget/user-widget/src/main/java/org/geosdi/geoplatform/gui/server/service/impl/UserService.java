/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.server.IUserService;
import org.geosdi.geoplatform.gui.server.SessionUtility;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
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
            String searchText, HttpServletRequest httpServletRequest) {
        GPUser user = this.getCheckLoggedUser(httpServletRequest);

        int start = config.getOffset();

        SearchRequest srq = new SearchRequest(searchText);

        Long usersCount = this.geoPlatformServiceClient.getAccountsCount(srq);

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
    public Long insertUser(IGPUserManageDetail userDetail, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        this.getCheckLoggedUser(httpServletRequest);

        logger.info("User to insert: " + userDetail);
        Long iserId = null;
        try {
            GPUser user = this.convertToGPUser(userDetail);
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
            String currentPlainPassword, HttpServletRequest httpServletRequest)
            throws GeoPlatformException {
        this.getCheckLoggedUser(httpServletRequest);

        logger.info("Own User to update: " + userDetail);
        Long userID = null;
        try {
            GPUser user = this.convertToGPUser(userDetail);
            userID = geoPlatformServiceClient.updateOwnUser(user, currentPlainPassword);
            sessionUtility.storeUserInSession(user, httpServletRequest);
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
        user.setTemporary(userDTO.isTemporary());
        user.setAuthority(this.convertToGPRole(userDTO.getRoles()));
        return user;
    }

    // NOTE: Now a user must have at most one role
    private GPRole convertToGPRole(List<String> authorities) {
        Iterator<String> iterator = authorities.iterator();
        if (iterator.hasNext()) {
            String authority = iterator.next();
            return GPRole.fromString(authority);
        }
        return GPRole.VIEWER;
    }

    // All properties unless the password
    private GPUserManageDetail convertToGPUserManageDetail(GPUser gpUser) {
        GPUserManageDetail user = new GPUserManageDetail();
        user.setId(gpUser.getId());
        user.setName(gpUser.getName());
        user.setUsername(gpUser.getUsername());
        user.setEmail(gpUser.getEmailAddress());
        user.setTemporary(gpUser.isAccountTemporary());
        user.setAuthority(this.convertToGPAuthorities(gpUser.getGPAuthorities()));
        return user;
    }

    // NOTE: Now a user must have at most one role
    private GPRole convertToGPAuthorities(List<GPAuthority> authorities) {
        Iterator<GPAuthority> iterator = authorities.iterator();
        if (iterator.hasNext()) {
            GPAuthority authority = iterator.next();
            return GPRole.fromString(authority.getAuthority());
        }
        return GPRole.VIEWER;
    }

    private GPUser getCheckLoggedUser(HttpServletRequest httpServletRequest) {
        try {
            return sessionUtility.getUserAlreadyFromSession(httpServletRequest);
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
        user.setAccountTemporary(userDetail.isTemporary());

        GPAuthority authority = new GPAuthority();
        authority.setAuthority(userDetail.getAuthority().toString());
        user.setGPAuthorities(Arrays.asList(authority));

        return user;
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
