/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ShortUser;
import org.geosdi.geoplatform.responce.UserList;

import com.trg.search.Search;

/**
 * @author giuseppe
 *
 */
class UserServiceImpl {

    final private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private GPUserDAO userDao;

    // note: may take lot of space
    public UserList getUsers() {
        List<GPUser> userList = userDao.findAll();
        return convertToShortList(userList);
    }

    /**
     * This method is used to insert a User
     *
     * @param user the User object to insert
     * @return long the User ID
     */
    public long insertUser(GPUser user) {
        // Always insert users as disabled
        user.setEnabled(true);
        userDao.persist(user);

        return user.getId();
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return DGUser the User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUser(RequestById request) throws ResourceNotFoundFault {
        GPUser user = userDao.find(request.getId());

        if (user == null) {
            throw new ResourceNotFoundFault("User not found", request.getId());
        }

        return user;
    }

    public GPUser getUserByName(SearchRequest username) throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(username.getNameLike());

        if (user == null) {
            throw new ResourceNotFoundFault("User not found (name=" + username.getNameLike() + ")");
        }

        return user;
    }

    /**
     * Delete a User by ID
     *
     * @throws ResourceNotFoundFault
     */
    public boolean deleteUser(RequestById request) throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser user = userDao.find(request.getId());

        if (user == null) {
            throw new ResourceNotFoundFault("User not found", request.getId());
        }

        return userDao.remove(user);
    }

    /**
     * Get a set of selected Users using paging.
     * The paging parameters are specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return Users the list of Users found
     */
    public UserList searchUsers(PaginatedSearchRequest request) {

        Search searchCriteria = new Search(GPUser.class);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addSortAsc("username");

        String like = request.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("username", like);
        }

        List<GPUser> userList = userDao.search(searchCriteria);

        return convertToShortList(userList);
    }

    public long getUsersCount(SearchRequest request) {
        Search searchCriteria = new Search(GPUser.class);

        if (request != null && request.getNameLike() != null) {
            searchCriteria.addFilterILike("username", request.getNameLike());
        }
        return userDao.count(searchCriteria);
    }

    public long updateUser(GPUser user) throws ResourceNotFoundFault, IllegalParameterFault {
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

    private UserList convertToShortList(List<GPUser> userList) {
        List<ShortUser> shortUsers = new ArrayList<ShortUser>(userList.size());
        for (GPUser dGUser : userList) {
            shortUsers.add(new ShortUser(dGUser));
        }

        UserList users = new UserList();
        users.setList(shortUsers);
        return users;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }
}
