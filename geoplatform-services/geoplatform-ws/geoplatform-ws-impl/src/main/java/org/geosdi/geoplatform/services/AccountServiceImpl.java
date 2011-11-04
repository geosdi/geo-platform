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
import com.googlecode.genericdao.search.Filter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.Utility;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ApplicationDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.domain.BasePermission;

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
class AccountServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // DAO
    private GPAccountDAO accountDao;
    private GPAccountProjectDAO accountProjectDao;
    private GPProjectDAO projectDao;
    private GPAuthorityDAO authorityDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param accountDao
     *          the accountDao to set
     */
    public void setAccountDao(GPAccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @param accountProjectDao
     *          the accountProjecDao to set
     */
    public void setAccountProjectDao(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDao = accountProjectDao;
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
     * This method is used to insert an Account
     *
     * @param account the Account object to insert
     * @return Long the Account ID
     */
    public Long insertAccount(GPAccount account) throws IllegalParameterFault {
        EntityCorrectness.checkAccountAndAuthority(account); // TODO assert
        this.checkDuplicateAccount(account);

        List<GPAuthority> authorities = account.getGPAuthorities();
        for (GPAuthority authority : authorities) {
            authority.setAccount(account);
            authority.setStringID(account.getStringID());
        }

        account.setEnabled(true); // Always insert users as enabled
        if (account instanceof GPUser) {
            GPUser user = (GPUser) account;
            user.setPassword(Utility.md5hash(user.getPassword())); // Hash password
        }
        accountDao.persist(account);

        authorityDao.persist(authorities.toArray(new GPAuthority[authorities.size()]));

        return account.getId();
    }

    public Long updateUser(GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User \"ID\" must be NOT NULL");
        }
        GPUser orig = (GPUser) this.getAccountById(user.getId());
        EntityCorrectness.checkAccountLog(orig); // TODO assert

//        if (!user.getEmailAddress().equals(orig.getEmailAddress()) && orig.isEnabled()) {
//            throw new IllegalParameterFault("Can't update the email address for registered user:" + user.getId());
//        }
//
//        if (user.isEnabled() != orig.isEnabled()) {
//            throw new IllegalParameterFault("Can't change user enabled status for user:" + user.getId());
//        }

        // Set the values (except username and property not managed)
        String name = user.getName();
        if (name != null) {
            orig.setName(name);
        }
        String email = user.getEmailAddress();
        if (email != null) {
            orig.setEmailAddress(email);
        }
        String password = user.getPassword();
        if (password != null) {
            orig.setPassword(Utility.md5hash(password)); // Hash password
        }
        this.updateAccount(orig, user);

        this.updateAccountAuthorities(orig.getUsername(), user.getGPAuthorities());

        accountDao.merge(orig);
        return orig.getId();
    }

    public Long updateApplication(GPAccount application)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (application.getId() == null) {
            throw new IllegalArgumentException("Application \"ID\" must be NOT NULL");
        }
        GPApplication orig = (GPApplication) this.getAccountById(application.getId());
        EntityCorrectness.checkAccountLog(orig); // TODO assert

        // Set the values (except appID and property not managed)
        this.updateAccount(orig, application);

        this.updateAccountAuthorities(orig.getAppID(), application.getGPAuthorities());

        accountDao.merge(orig);
        return orig.getId();
    }

    /**
     * Delete a Account by ID
     *
     * @throws ResourceNotFoundFault
     */
    public boolean deleteAccount(Long accountID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        authorityDao.removeAllUserAuthorities(account.getStringID());

        List<GPAccountProject> accountProjectList = accountProjectDao.findByAccountID(accountID);
        for (GPAccountProject accountProject : accountProjectList) {
            // Remove all AccountProject that reference (also of other users) by cascading
            if (accountProject.getPermissionMask() == BasePermission.ADMINISTRATION.getMask()) {
                if (!projectDao.remove(accountProject.getProject())) {
                    return false;
                }
            } else {
                // Remove all AccountProject that reference by cascading
                // Only that reference to shared projects
                accountProjectDao.remove(accountProject);
            }
        }

        return accountDao.remove(account);
    }

    /**
     * Method to get a single User by ID specified in the REST request
     *
     * @param userID is the ID of the User to retrieve
     * @return GPUser the detailed User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUserDetail(Long userID) throws ResourceNotFoundFault {
        GPUser user = (GPUser) this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return user;
    }

    /**
     * Method to get a single Application by ID specified in the REST request
     *
     * @param applicationID is the ID of the Application to retrieve
     * @return GPApplication the detailed Application object
     * @throws ResourceNotFoundFault
     */
    public GPApplication getApplicationDetail(Long applicationID) throws ResourceNotFoundFault {
        GPApplication application = (GPApplication) this.getAccountById(applicationID);
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return application;
    }

    /**
     * Method to get a single User by ID specified in the REST request
     *
     * @param userID is the ID of the User to retrieve
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public UserDTO getShortUser(Long userID) throws ResourceNotFoundFault {
        GPUser user = (GPUser) this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return new UserDTO(user);
    }

    /**
     * Method to get a single Application by ID specified in the REST request
     *
     * @param applicationID is the ID of the Application to retrieve
     * @return ApplicationDTO the short Application object
     * @throws ResourceNotFoundFault
     */
    public ApplicationDTO getShortApplication(Long applicationID) throws ResourceNotFoundFault {
        GPApplication application = (GPApplication) this.getAccountById(applicationID);
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return new ApplicationDTO(application);
    }

    /**
     * Method to get a single User by username specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public UserDTO getShortUserByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        GPUser user = this.getUserByUsername(request.getNameLike());
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return new UserDTO(user);
    }

    /**
     * Method to get a single User by username specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return GPUser the detailed User object
     * @throws ResourceNotFoundFault
     */
    public GPUser getUserDetailByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        GPUser user = this.getUserByUsername(request.getNameLike());
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return user;
    }

    public GPUser getUserDetailByUsernameAndPassword(String username, String password)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPUser user = null;
        try {
            user = accountDao.findByUsername(username);
            if (user == null) {
                throw new ResourceNotFoundFault("User with specified username was not found");
            }
            if (!user.verify(password)) {
                throw new IllegalParameterFault("Specified password was incorrect");
            }
            EntityCorrectness.checkAccountLog(user); // TODO assert
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalParameterFault(e.getMessage());
        }
        return user;
    }

    /**
     * Method to get a single Application by ID specified in the REST request
     *
     * @param request the object representing the request parameters
     * @return UserDTO the short User object
     * @throws ResourceNotFoundFault
     */
    public ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault {
        GPApplication application = this.getApplicationByAppId(request.getNameLike());
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return new ApplicationDTO(application);
    }

    /**
     * Get a set of selected Users using paging.
     * The paging parameters are specified in the REST request
     *
     * @param request the object representing the request parameters
     * @param userID the of user that will exclude from the search (logged user)
     * @return Users the list of Users found
     */
    public List<UserDTO> searchUsers(Long userID, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        EntityCorrectness.checkAccountLog(this.getAccountById(userID)); // TODO assert

        Search searchCriteria = new Search(GPAccount.class);
        searchCriteria.addFilterNotEqual("id", userID);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addSortAsc("username");

        String like = request.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("username", like);
        }

        List<GPAccount> accountList = accountDao.search(searchCriteria);
        List<GPUser> userList = new ArrayList<GPUser>(accountList.size());
        for (GPAccount account : accountList) {
            account.setGPAuthorities(this.getGPAuthorities(account.getStringID()));
            EntityCorrectness.checkAccountAndAuthorityLog(account); // TODO assert
            userList.add((GPUser) account);
        }

        return UserDTO.convertToUserDTOList(userList);
    }

    // Note: may take lot of space
    public List<ShortAccountDTO> getAccounts() {
        List<GPAccount> accountList = accountDao.findAll();
        for (GPAccount account : accountList) {
            EntityCorrectness.checkAccountLog(account); // TODO assert
        }
        return ShortAccountDTO.convertToShortAccountDTOList(accountList);
    }

    public Long getAccountsCount(SearchRequest request) {
        Search searchCriteria = new Search(GPAccount.class);

        if (request != null && request.getNameLike() != null) {
            Filter fUsername = Filter.ilike("username", request.getNameLike());
            Filter fAppId = Filter.ilike("appID", request.getNameLike());
            searchCriteria.addFilterOr(fUsername, fAppId); // TODO check
        }
        return new Long(accountDao.count(searchCriteria));
    }

    /**
     * @param username of User to retrieve authorities
     * @return Authorities in a list of String
     * @throws ResourceNotFoundFault 
     */
    public List<String> getAuthorities(Long stringID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(stringID);
        EntityCorrectness.checkAccountLog(account); // TODO assert
        List<String> authorities = this.getAuthorities(account.getStringID());
        return authorities;
    }

    public List<GPAuthority> getAuthoritiesDetail(String stringID) throws ResourceNotFoundFault {
        return this.getGPAuthorities(stringID);
    }

    private List<String> getAuthorities(String stringID) throws ResourceNotFoundFault {
        List<GPAuthority> authorities = this.getGPAuthorities(stringID);
        return this.convertAuthorities(authorities);
    }

    private List<GPAuthority> getGPAuthorities(String stringID) throws ResourceNotFoundFault {
        List<GPAuthority> authorities = authorityDao.findByStringID(stringID);
        if (authorities.isEmpty()) {
            throw new ResourceNotFoundFault("Account not found (stringID=" + stringID + ")");
        }
        EntityCorrectness.checkAuthorityLog(authorities);
        return authorities;
    }

    private List<String> convertAuthorities(List<GPAuthority> authorities) {
        List<String> authorityName = new ArrayList<String>(authorities.size());
        for (GPAuthority authority : authorities) {
            authorityName.add(authority.getAuthority());
        }
        return authorityName;
    }

    private GPAccount getAccountById(Long accountID) throws ResourceNotFoundFault {
        GPAccount account = accountDao.find(accountID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account not found", accountID);
        }
        return account;
    }

    private GPUser getUserByUsername(String username) throws ResourceNotFoundFault {
        GPUser user = accountDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundFault("User not found (username=" + username + ")");
        }
        return user;
    }

    private GPApplication getApplicationByAppId(String appID) throws ResourceNotFoundFault {
        GPApplication application = accountDao.findByAppID(appID);
        if (application == null) {
            throw new ResourceNotFoundFault("Application not found (appID=" + appID + ")");
        }
        return application;
    }

    private void checkDuplicateAccount(GPAccount account) throws IllegalParameterFault {
        if (account instanceof GPUser) { // User
            GPUser user = (GPUser) account;
            if (accountDao.findByUsername(user.getUsername()) != null) {
                throw new IllegalParameterFault("User with username \""
                        + user.getUsername() + "\" already exists");
            }

            if (accountDao.findByEmail(user.getEmailAddress()) != null) {
                throw new IllegalParameterFault("User with email \""
                        + user.getEmailAddress() + "\" already exists");
            }
        } else { // Application
            GPApplication application = (GPApplication) account;
            if (accountDao.findByAppID(application.getAppID()) != null) {
                throw new IllegalParameterFault("Application with AppId \""
                        + application.getAppID() + "\" already exists");
            }
        }
    }

    private void updateAccountAuthorities(String stringID, List<GPAuthority> authorities) {
        if (authorities != null && !authorities.isEmpty()) {
            if (authorities.size() > 1) {
                throw new UnsupportedOperationException("Not supported the update of multi-authorities: new authorities have more than one authority");
            }

            List<GPAuthority> origAuthorities = authorityDao.findByStringID(stringID);
            if (origAuthorities.size() > 1) {
                throw new UnsupportedOperationException("Not supported the update of multi-authorities: persisted authorities have more than one authority");
            }

            String stringAuthority = authorities.get(0).getAuthority();
            GPAuthority origAuthority = origAuthorities.get(0);
            if (!stringAuthority.equals(origAuthority.getAuthority())) {
                origAuthority.setAuthority(stringAuthority);
                authorityDao.merge(origAuthority);
            }
        }
    }

    /**
     * Updates all common fields of user and application (GPAccount) 
     */
    private void updateAccount(GPAccount accountToUpdate, GPAccount account) {
        Long defaultProjectID = account.getDefaultProjectID();
        if (defaultProjectID != null) {
            accountToUpdate.setDefaultProjectID(defaultProjectID);
        }
    }
}
