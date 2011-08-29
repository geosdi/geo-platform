//<editor-fold defaultstate="collapsed" desc="License">
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
//</editor-fold>
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.UserDTO;

/**
 * @author giuseppe
 *
 */
class UserServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // DAO
    private GPUserDAO userDao;
    private GPAuthorityDAO authorityDao;

    /**
     * @param userDao
     *          the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * @param authorityDao
     *          the authorityDao to set
     */
    public void setAuthorityDao(GPAuthorityDAO authorityDao) {
        this.authorityDao = authorityDao;
    }

    /**
     * This method is used to insert a User
     *
     * @param user the User object to insert
     * @return long the User ID
     */
    public long insertUser(GPUser user) throws IllegalParameterFault {
        GPUser duplicateUser = userDao.findByUsername(user.getUsername());
        if (duplicateUser != null) {
            throw new IllegalParameterFault("User with username \"" + user.getUsername() + "\" already exists");
        }

        // Always insert users as enabled
        user.setEnabled(true);
        userDao.persist(user);

        return user.getId();
    }

    public long updateUser(GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser orig = userDao.find(user.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("User not found", user.getId());
        }

        // manual checks (awful!)
        if (!user.getEmailAddress().equals(orig.getEmailAddress()) && orig.isEnabled()) {
            throw new IllegalParameterFault("Can't update the mail address for registered user:" + user.getId());
        }

        if (user.isEnabled() != orig.isEnabled()) {
            throw new IllegalParameterFault("Can't change user enabled status for user:" + user.getId());
        }

        // set the values
        orig.setEmailAddress(user.getEmailAddress());
        if (user.getPassword() != null) {
            orig.setPassword(user.getPassword());
        }
        orig.setSendEmail(user.isSendEmail());
        orig.setUsername(user.getUsername());

        userDao.merge(orig);
        return orig.getId();
    }

    /**
     * Delete a User by ID
     *
     * @throws ResourceNotFoundFault
     */
    public boolean deleteUser(RequestById request)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser user = userDao.find(request.getId());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", request.getId());
        }

        return userDao.remove(user);
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public UserDTO getShortUser(RequestById request)
            throws ResourceNotFoundFault {
        GPUser user = userDao.find(request.getId());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", request.getId());
        }

        return new UserDTO(user);
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return GPUser the detailed User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUserDetail(RequestById request)
            throws ResourceNotFoundFault {
        GPUser user = userDao.find(request.getId());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", request.getId());
        }

        return user;
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public UserDTO getShortUserByName(SearchRequest username)
            throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(username.getNameLike());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found (username=" + username.getNameLike() + ")");
        }

        return new UserDTO(user);
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return GPUser the detailed User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUserDetailByName(SearchRequest username)
            throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(username.getNameLike());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found (username=" + username.getNameLike() + ")");
        }

        return user;
    }

    /**
     * Get a set of selected Users using paging.
     * The paging parameters are specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return Users the list of Users found
     */
    public List<UserDTO> searchUsers(PaginatedSearchRequest request) {
        Search searchCriteria = new Search(GPUser.class);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addSortAsc("username");

        String like = request.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("username", like);
        }

        List<GPUser> userList = userDao.search(searchCriteria);

        return convertToUserList(userList);
    }

    // note: may take lot of space
    public List<UserDTO> getUsers() {
        List<GPUser> userList = userDao.findAll();
        return convertToUserList(userList);
    }

    public long getUsersCount(SearchRequest request) {
        Search searchCriteria = new Search(GPUser.class);

        if (request != null && request.getNameLike() != null) {
            searchCriteria.addFilterILike("username", request.getNameLike());
        }
        return userDao.count(searchCriteria);
    }

    public GPUser getUserDetailByUsernameAndPassword(String username) {
        Search searchCriteria = new Search(GPUser.class);

        Filter usernameFilter = Filter.equal("username", username);
        searchCriteria.addFilter(usernameFilter);

        List<GPUser> usersList = userDao.search(searchCriteria);
        if (usersList.isEmpty()) {
            return null;
        }
        return usersList.get(0);
    }

    public List<String> getUserAuthorities(long userId)
            throws ResourceNotFoundFault {
        // Retrieve the user
        GPUser user = userDao.find(userId);
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", userId);
        }

        // Retrieve the Authorities of the User
        List<GPAuthority> authorities = authorityDao.findByUsername(user.getUsername());
        logger.trace("\n*** #Authorities: {} ***", authorities.size());

        List<String> authorityName = new ArrayList<String>(authorities.size());
        for (GPAuthority authority : authorities) {
            authorityName.add(authority.getAuthority());
        }

        return authorityName;
    }

    private List<UserDTO> convertToUserList(List<GPUser> userList) {
        List<UserDTO> usersDTO = new ArrayList<UserDTO>(userList.size());

        for (GPUser dGUser : userList) {
            usersDTO.add(new UserDTO(dGUser));
        }

        return usersDTO;
    }
}
