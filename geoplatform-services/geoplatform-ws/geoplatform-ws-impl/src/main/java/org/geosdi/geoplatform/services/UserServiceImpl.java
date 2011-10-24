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

import com.googlecode.genericdao.search.Search;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.dao.GPUserProjectsDAO;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPUserProjects;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author giuseppe
 *
 */
class UserServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // DAO
    private GPUserDAO userDao;
    private GPUserProjectsDAO userProjectsDao;
    private GPProjectDAO projectDao;
    private GPAuthorityDAO authorityDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param userDao
     *          the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * @param userProjecsDao
     *          the userProjecsDao to set
     */
    public void setUserProjectsDao(GPUserProjectsDAO userProjecsDao) {
        this.userProjectsDao = userProjecsDao;
    }

    /**
     * @param projectDao
     *          the projectDao to set
     */
    public void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * @param authorityDao
     *          the authorityDao to set
     */
    public void setAuthorityDao(GPAuthorityDAO authorityDao) {
        this.authorityDao = authorityDao;
    }
    //</editor-fold>

    /**
     * This method is used to insert a User
     *
     * @param user the User object to insert
     * @return Long the User ID
     */
    public Long insertUser(GPUser user) throws IllegalParameterFault {
        this.checkDuplicateUser(user);

        List<GPAuthority> authorities = user.getGPAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            throw new IllegalParameterFault("User must have at least a role");
        }
        for (GPAuthority authority : authorities) {
            this.checkAuthority(authority.getAuthority());
            authority.setUser(user);
            authority.setUsername(user.getUsername());
        }

        // Always insert users as enabled
        user.setEnabled(true);
        userDao.persist(user);

        authorityDao.persist(authorities.toArray(new GPAuthority[authorities.size()]));

        return user.getId();
    }

    // TODO Manage authorities
    public Long updateUser(GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser orig = this.getUserById(user.getId());

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
    public boolean deleteUser(Long userId) throws ResourceNotFoundFault {
        GPUser user = this.getUserById(userId);

        authorityDao.removeAllUserAuthorities(user.getUsername());

        List<GPUserProjects> foldersList = userProjectsDao.findByOwnerUserId(userId);
        for (GPUserProjects userFolder : foldersList) {
            // Remove all UserProjects that reference (also of other users) by cascading
            if (!projectDao.remove(userFolder.getProject())) {
                return false;
            }
        }

        // Remove all UserProjects that reference by cascading
        // Only that reference to shared projects
        return userDao.remove(user);
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param userId is the ID of the User to retrieve
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public UserDTO getShortUser(Long userId) throws ResourceNotFoundFault {
        GPUser user = this.getUserById(userId);
        return new UserDTO(user);
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param userId is the ID of the User to retrieve
     * @return GPUser the detailed User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUserDetail(Long userId) throws ResourceNotFoundFault {
        return this.getUserById(userId);
    }

    /**
     *
     * Method to get a single User by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public UserDTO getShortUserByName(SearchRequest request)
            throws ResourceNotFoundFault {
        GPUser user = this.getUserByUsername(request.getNameLike());
        return new UserDTO(user);
    }

    /**
     *
     * Method to get a single User by username specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return GPUser the detailed User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUserDetailByName(SearchRequest request)
            throws ResourceNotFoundFault {
        return this.getUserByUsername(request.getNameLike());
    }

    /**
     * Get a set of selected Users using paging.
     * The paging parameters are specified in the REST request
     *
     * @param request the object representing the request parameters
     * @param userId the of user that will exclude from the search (logged user)
     * @return Users the list of Users found
     */
    public List<UserDTO> searchUsers(PaginatedSearchRequest request, Long userId)
            throws ResourceNotFoundFault {
        Search searchCriteria = new Search(GPUser.class);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addFilterNotEqual("id", userId);
        searchCriteria.addSortAsc("username");

        String like = request.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("username", like);
        }

        List<GPUser> userList = userDao.search(searchCriteria);
        for (GPUser user : userList) {
            user.setGPAuthorities(this.getGPAuthorities(user.getUsername()));
        }

        return UserDTO.convertToUserDTOList(userList);
    }

    // note: may take lot of space
    public List<UserDTO> getUsers() {
        List<GPUser> userList = userDao.findAll();
        return UserDTO.convertToUserDTOList(userList);
    }

    public Long getUsersCount(SearchRequest request) {
        Search searchCriteria = new Search(GPUser.class);

        if (request != null && request.getNameLike() != null) {
            searchCriteria.addFilterILike("username", request.getNameLike());
        }
        return new Long(userDao.count(searchCriteria));
    }

    public GPUser getUserDetailByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser user = null;
        try {
            user = userDao.findByUsername(username);
            if (user == null) {
                throw new ResourceNotFoundFault("User with specified username was not found");
            }
            if (!user.verify(password)) {
                throw new IllegalParameterFault("Specified password was incorrect");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalParameterFault(e.getMessage());
        }
        return user;
    }

    /**
     * @param username of User to retrieve authorities
     * @return Authorities in a list of String
     * @throws ResourceNotFoundFault 
     */
    public List<String> getUserAuthorities(Long userId) throws ResourceNotFoundFault {
        GPUser user = this.getUserById(userId);
        List<String> authorities = this.getAuthorities(user.getUsername());
        return authorities;
    }

    public List<GPAuthority> getUserGPAuthorities(String username) throws ResourceNotFoundFault {
        return this.getGPAuthorities(username);
    }

    private GPUser getUserById(Long userId) throws ResourceNotFoundFault {
        GPUser user = userDao.find(userId);
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", userId);
        }
        return user;
    }

    private GPUser getUserByUsername(String username) throws ResourceNotFoundFault {
        GPUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundFault("User not found (username=" + username + ")");
        }
        return user;
    }

    private List<String> getAuthorities(String username) throws ResourceNotFoundFault {
        List<GPAuthority> authorities = this.getGPAuthorities(username);
        return this.convertAuthorities(authorities);
    }

    private List<GPAuthority> getGPAuthorities(String username) throws ResourceNotFoundFault {
        List<GPAuthority> authorities = authorityDao.findByUsername(username);
        if (authorities.isEmpty()) {
            throw new ResourceNotFoundFault("User not found (username=" + username + ")");
        }
        return authorities;
    }

    private List<String> convertAuthorities(List<GPAuthority> authorities) {
        List<String> authorityName = new ArrayList<String>(authorities.size());
        for (GPAuthority authority : authorities) {
            authorityName.add(authority.getAuthority());
        }
        return authorityName;
    }

    private void checkAuthority(String authority) throws IllegalParameterFault {
        if (authority == null || authority.trim().equals("")) {
            throw new IllegalParameterFault("Authority is null or empty");
        }
        if (GPRole.fromString(authority) == null) {
            throw new IllegalParameterFault("Authority is incorrect");
        }
    }

    private void checkDuplicateUser(GPUser user) throws IllegalParameterFault {
        GPUser duplicateUser = userDao.findByUsername(user.getUsername());
        if (duplicateUser != null) {
            throw new IllegalParameterFault("User with username \""
                    + user.getUsername() + "\" already exists");
        }

        duplicateUser = userDao.findByEmail(user.getEmailAddress());
        if (duplicateUser != null) {
            throw new IllegalParameterFault("User with email \""
                    + user.getEmailAddress() + "\" already exists");
        }
    }
}
