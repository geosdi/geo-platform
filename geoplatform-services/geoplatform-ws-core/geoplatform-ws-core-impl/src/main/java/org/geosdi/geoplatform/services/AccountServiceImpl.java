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
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.configurator.crypt.GPDigesterConfigutator;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPOrganizationDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.AccountLoginFault.LoginFaultType;
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

/**
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
class AccountServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // DAO
    private GPAccountDAO accountDao;
    private GPAccountProjectDAO accountProjectDao;
    private GPProjectDAO projectDao;
    private GPAuthorityDAO authorityDao;
    private GPOrganizationDAO organizationDao;
    // Services
    private GPSchedulerService schedulerService;
    //
    private GPDigesterConfigutator gpDigester;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param gpDigester the gpDigester to set
     */
    void setGpDigester(GPDigesterConfigutator gpDigester) {
        this.gpDigester = gpDigester;
    }

    /**
     * @param accountDao the accountDao to set
     */
    void setAccountDao(GPAccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @param accountProjectDao the accountProjecDao to set
     */
    void setAccountProjectDao(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDao = accountProjectDao;
    }

    /**
     * @param projectDao the projectDao to set
     */
    void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * @param authorityDao the authorityDao to set
     */
    void setAuthorityDao(GPAuthorityDAO authorityDao) {
        this.authorityDao = authorityDao;
    }

    /**
     * @param organizationDao the organizationDao to set
     */
    void setOrganizationDao(GPOrganizationDAO organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * @param schedulerService the schedulerService to set
     */
    void setSchedulerService(GPSchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }
    //</editor-fold>

    /**
     * @see
     * GeoPlatformService#insertAccount(org.geosdi.geoplatform.core.model.GPAccount,
     * boolean)
     */
    Long insertAccount(GPAccount account, boolean sendEmail)
            throws IllegalParameterFault {
        EntityCorrectness.checkAccountAndAuthority(account); // TODO assert

        GPOrganization org = organizationDao.findByName(account.getOrganization().getName());
        if (org == null) {
            throw new IllegalParameterFault("Account to save have an organization that does not exist");
        }
        account.setOrganization(org);

        this.checkDuplicateAccount(account);

        String plainPassword = "";
        if (account instanceof GPUser) {
            GPUser user = (GPUser) account;
            plainPassword = user.getPassword();
            user.setPassword(this.gpDigester.digest(user.getPassword()));
        }

        // TODO Set to false, and after user confirmation email enabling user account
//        account.setEnabled(true); // Always insert account as enabled
        accountDao.persist(account);

        List<GPAuthority> authorities = account.getGPAuthorities();
        for (GPAuthority authority : authorities) {
            authority.setAccount(account);
            authority.setStringID(account.getStringID());
        }
        authorityDao.persist(authorities.toArray(new GPAuthority[authorities.size()]));

        if (sendEmail && account instanceof GPUser) {
            GPUser user = (GPUser) account;

            GPUser clonedUser = this.cloneUser(user, plainPassword);
            schedulerService.sendEmailRegistration(clonedUser);
        }

        return account.getId();
    }

    private GPUser cloneUser(GPUser user, String plainPassword) {
        GPUser clonedUser = new GPUser();
        clonedUser.setEmailAddress(user.getEmailAddress());
        clonedUser.setName(user.getName());
        clonedUser.setSendEmail(user.isSendEmail());
        clonedUser.setUsername(user.getUsername());
        clonedUser.setEnabled(user.isEnabled());
        clonedUser.setAccountTemporary(user.isAccountTemporary());
        clonedUser.setPassword(plainPassword);

        return clonedUser;
    }

    /**
     * @see
     * GeoPlatformService#updateUser(org.geosdi.geoplatform.core.model.GPUser)
     */
    Long updateUser(GPUser user)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User \"ID\" must be NOT NULL");
        }
        GPUser orig = (GPUser) this.getAccountById(user.getId());
        EntityCorrectness.checkAccountLog(orig); // TODO assert

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
        if (password != null && !password.equals(orig.getPassword())) {
            orig.setPassword(this.gpDigester.digest(password));
        }
        this.updateAccount(orig, user);

        this.updateAccountAuthorities(orig.getUsername(), user.getGPAuthorities());

        accountDao.merge(orig);
        return orig.getId();
    }

    /**
     * @see
     * GeoPlatformService#updateOwnUser(org.geosdi.geoplatform.responce.UserDTO,
     * java.lang.String, java.lang.String)
     */
    Long updateOwnUser(UserDTO user,
            String currentPlainPassword, String newPlainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User \"ID\" must be NOT NULL");
        }
        GPUser orig = (GPUser) this.getAccountById(user.getId());
        EntityCorrectness.checkAccountLog(orig); // TODO assert

        // Check credentials
        boolean passwordChanged = false;
        if (newPlainPassword != null) {
            // Check password
            if (!this.gpDigester.matches(orig.getPassword(), currentPlainPassword)) {
                throw new IllegalParameterFault("Current password was incorrect");
            }
            passwordChanged = true;
            orig.setPassword(this.gpDigester.digest(newPlainPassword));
        }
        // Eventually update the email
        boolean emailChanged = false;
        String previousEmail = null;
        String email = user.getEmailAddress();
        if (!orig.getEmailAddress().equals(email)) {
            emailChanged = true;
            previousEmail = orig.getEmailAddress();
            orig.setEmailAddress(email);
        }
        // Eventually update the name
        String name = user.getName();
        if (name != null) {
            orig.setName(name);
        }

        accountDao.merge(orig);

        // Send an email for modification
        if (passwordChanged || emailChanged) {
            schedulerService.sendEmailModification(orig, previousEmail, newPlainPassword);
        }
        return orig.getId();
    }

    /**
     * @see
     * GeoPlatformService#updateApplication(org.geosdi.geoplatform.core.model.GPApplication)
     */
    Long updateApplication(GPAccount application)
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
     * @see GeoPlatformService#deleteAccount(java.lang.Long)
     */
    boolean deleteAccount(Long accountID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

//      Unnecessary Operation 
//      (All Authority will be removed on cascading removing GPAccount)   
//      authorityDao.removeAllUserAuthorities(account.getStringID());
//
        List<GPAccountProject> accountProjectList = accountProjectDao.findByOwnerAccountID(accountID);
        for (GPAccountProject accountProject : accountProjectList) {
            // Remove all AccountProject that reference (also of other accounts) by cascading            
            if (!projectDao.remove(accountProject.getProject())) {
                return false;
            }
        }

        // Remove all AccountProject that reference by cascading
        // Only that reference to shared projects
        return accountDao.remove(account);
    }

    /**
     * @see GeoPlatformService#getUserDetail(java.lang.Long)
     */
    GPUser getUserDetail(Long userID) throws ResourceNotFoundFault {
        GPUser user = (GPUser) this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return user;
    }

    /**
     * @see GeoPlatformService#getApplicationDetail(java.lang.Long)
     */
    GPApplication getApplicationDetail(Long applicationID) throws ResourceNotFoundFault {
        GPApplication application = (GPApplication) this.getAccountById(applicationID);
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return application;
    }

    /**
     * @see GeoPlatformService#getShortUser(java.lang.Long)
     */
    UserDTO getShortUser(Long userID) throws ResourceNotFoundFault {
        GPUser user = (GPUser) this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return new UserDTO(user);
    }

    /**
     * @see GeoPlatformService#getShortApplication(java.lang.Long)
     */
    ApplicationDTO getShortApplication(Long applicationID) throws ResourceNotFoundFault {
        GPApplication application = (GPApplication) this.getAccountById(applicationID);
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return new ApplicationDTO(application);
    }

    /**
     * @see
     * GeoPlatformService#getShortUserByUsername(org.geosdi.geoplatform.request.SearchRequest)
     */
    UserDTO getShortUserByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        GPUser user = this.getUserByUsername(request.getNameLike());
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return new UserDTO(user);
    }

    /**
     * @see
     * GeoPlatformService#getUserDetailByUsername(org.geosdi.geoplatform.request.SearchRequest)
     */
    GPUser getUserDetailByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        GPUser user = this.getUserByUsername(request.getNameLike());
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return user;
    }

    /**
     * @see
     * GeoPlatformService#getUserDetailByUsernameAndPassword(java.lang.String,
     * java.lang.String)
     */
    GPUser getUserDetailByUsernameAndPassword(String username, String plainPassword)
            throws ResourceNotFoundFault, IllegalParameterFault, AccountLoginFault {
        GPUser user = accountDao.findByUsername(username);
        if (user == null) {
            user = accountDao.findByEmail(username);
            if (user == null) {
                throw new ResourceNotFoundFault("User not found (username or email=" + username + ")");
            }
        }
        EntityCorrectness.checkAccountLog(user); // TODO assert

        if (!user.isAccountNonExpired()) {
            throw new AccountLoginFault(LoginFaultType.ACCOUNT_EXPIRED, username);
        }
        if (!user.isEnabled()) {
            throw new AccountLoginFault(LoginFaultType.ACCOUNT_DISABLED, username);
        }

        // Check password
        if (!this.gpDigester.matches(user.getPassword(), plainPassword)) {
            throw new IllegalParameterFault("Specified password was incorrect");
        }

        // Set authorities
        user.setGPAuthorities(this.getGPAuthorities(user.getStringID()));

        return user;
    }

    /**
     * @see GeoPlatformService#getApplication(java.lang.String)
     */
    GPApplication getApplication(String appID)
            throws ResourceNotFoundFault, AccountLoginFault {
        GPApplication application = this.getApplicationByAppId(appID);
        EntityCorrectness.checkAccountLog(application); // TODO assert

        if (!application.isAccountNonExpired()) {
            throw new AccountLoginFault(LoginFaultType.ACCOUNT_EXPIRED, appID);
        }
        if (!application.isEnabled()) {
            throw new AccountLoginFault(LoginFaultType.ACCOUNT_DISABLED, appID);
        }

        return application;
    }

    /**
     * @see
     * GeoPlatformService#getShortApplicationByAppID(org.geosdi.geoplatform.request.SearchRequest)
     */
    ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault {
        GPApplication application = this.getApplicationByAppId(request.getNameLike());
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return new ApplicationDTO(application);
    }

    /**
     * @see GeoPlatformService#searchUsers(java.lang.Long,
     * org.geosdi.geoplatform.request.PaginatedSearchRequest)
     */
    List<UserDTO> searchUsers(Long userID, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        GPAccount user = this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert

        Search searchCriteria = new Search(GPAccount.class);
        searchCriteria.addFilterNotEqual("id", userID);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addFilterNotEmpty("username");
        searchCriteria.addSortAsc("username");
        searchCriteria.addFilterEqual("organization.name", user.getOrganization().getName());

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

    /**
     * @see GeoPlatformService#getAllAccounts()
     */
    List<ShortAccountDTO> getAllAccounts() {
        List<GPAccount> accountList = accountDao.findAll();
        EntityCorrectness.checkAccountListLog(accountList); // TODO assert
        return ShortAccountDTO.convertToShortAccountDTOList(accountList);
    }

    /**
     * @see GeoPlatformService#getAccounts(java.lang.String)
     */
    List<ShortAccountDTO> getAccounts(String organization)
            throws ResourceNotFoundFault {
        GPOrganization org = organizationDao.findByName(organization);
        if (org == null) {
            throw new ResourceNotFoundFault("Organization \"" + organization + "\" not found.");
        }

        List<GPAccount> accountList = accountDao.findByOrganization(organization);
        EntityCorrectness.checkAccountListLog(accountList); // TODO assert
        return ShortAccountDTO.convertToShortAccountDTOList(accountList);
    }

    /**
     * @see
     * GeoPlatformService#getAccountsCount(org.geosdi.geoplatform.request.SearchRequest)
     */
    Long getAccountsCount(SearchRequest request) {
        Search searchCriteria = new Search(GPAccount.class);

        if (request != null && request.getNameLike() != null) {
            Filter fUsername = Filter.ilike("username", request.getNameLike());
            Filter fAppId = Filter.ilike("appID", request.getNameLike());
            searchCriteria.addFilterOr(fUsername, fAppId);
        }
        return Long.valueOf(accountDao.count(searchCriteria));
    }

    /**
     * @see GeoPlatformService#getUsersCount(java.lang.String,
     * org.geosdi.geoplatform.request.SearchRequest)
     */
    Long getUsersCount(String organization, SearchRequest request) {
        Search searchCriteria = new Search(GPAccount.class);
        searchCriteria.addFilterNotEmpty("username");
        searchCriteria.addFilterEqual("organization.name", organization);

        if (request != null && request.getNameLike() != null) {
            searchCriteria.addFilterILike("username", request.getNameLike());
        }
        return Long.valueOf(accountDao.count(searchCriteria));
    }

    /**
     * @see GeoPlatformService#getAuthorities(java.lang.Long)
     */
    List<String> getAuthorities(Long stringID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(stringID);
        EntityCorrectness.checkAccountLog(account); // TODO assert
        List<String> authorities = this.getAuthorities(account.getStringID());
        return authorities;
    }

    /**
     * @see GeoPlatformService#getAuthoritiesDetail(java.lang.String)
     */
    List<GPAuthority> getAuthoritiesDetail(String stringID) throws ResourceNotFoundFault {
        return this.getGPAuthorities(stringID);
    }

    /**
     * @see GeoPlatformService#forceTemporaryAccount(java.lang.Long)
     */
    void forceTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        account.setAccountTemporary(true);
        accountDao.merge(account);
    }

    /**
     * @see GeoPlatformService#forceExpiredTemporaryAccount(java.lang.Long)
     */
    void forceExpiredTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPAccount account = this.getAccountById(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        if (!account.isAccountTemporary()) {
            throw new IllegalParameterFault(
                    "The account must be temporary (ID = " + accountID + ")");
        }

        account.setAccountNonExpired(false);
        accountDao.merge(account);
    }

    /**
     ***************************************************************************
     */
    private List<String> getAuthorities(String stringID) throws ResourceNotFoundFault {
        List<GPAuthority> authorities = this.getGPAuthorities(stringID);
        return this.convertAuthorities(authorities);
    }

    private List<GPAuthority> getGPAuthorities(String stringID) throws ResourceNotFoundFault {
        List<GPAuthority> authorities = authorityDao.findShortByStringID(stringID);
        if (authorities.isEmpty()) {
            throw new ResourceNotFoundFault("Account (stringID=" + stringID + ") has no authority");
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
     * Updates all common fields of User and Application (Account type).
     */
    private void updateAccount(GPAccount accountToUpdate, GPAccount account)
            throws IllegalParameterFault {
        accountToUpdate.setEnabled(account.isEnabled());
        accountToUpdate.setLoadExpandedFolders(account.isLoadExpandedFolders());

        if (!accountToUpdate.isAccountTemporary() && account.isAccountTemporary()) {
            throw new IllegalParameterFault("A standard account cannot be changed to temporary account");
        }
        accountToUpdate.setAccountTemporary(account.isAccountTemporary());
        Long defaultProjectID = account.getDefaultProjectID();
        if (defaultProjectID != null) {
            accountToUpdate.setDefaultProjectID(defaultProjectID);
        }
    }
}
