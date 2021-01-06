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
package org.geosdi.geoplatform.core.delegate.impl.account;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.geosdi.geoplatform.core.dao.*;
import org.geosdi.geoplatform.core.delegate.api.account.AccountDelegate;
import org.geosdi.geoplatform.core.model.*;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.jasypt.support.GPDigesterConfigurator;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.response.authority.GetAuthoritiesResponseWS;
import org.geosdi.geoplatform.response.authority.GetAuthorityResponse;
import org.geosdi.geoplatform.response.factory.AccountDTOFactory;
import org.geosdi.geoplatform.scheduler.delegate.api.SchedulerDelegate;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.TRUE;

/**
 * Account service delegate.
 *
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Component(value = "gpAccountDelegate")
public class GPAccountDelegate implements AccountDelegate {

    private static final Logger logger = LoggerFactory.getLogger(
            GPAccountDelegate.class);
    //
    @Autowired
    private GPAccountDAO accountDao;
    @Autowired
    private GPAccountProjectDAO accountProjectDao;
    @Autowired
    private GPProjectDAO projectDao;
    @Autowired
    private GPAuthorityDAO authorityDao;
    @Autowired
    private GPOrganizationDAO organizationDao;
    @Resource(name = "gpSchedulerDelegate")
    private SchedulerDelegate gpSchedulerDelegate;
    @Resource(name = "gpDigesterSHA1")
    private GPDigesterConfigurator gpDigester;

    @Override
    public Long insertAccount(InsertAccountRequest insertAccountRequest) throws IllegalParameterFault {
        checkNotNull(insertAccountRequest, "The InsertAccountRequest must not be null");
        GPAccount account = insertAccountRequest.getAccount();
        boolean sendEmail = insertAccountRequest.isSendEmail();

        EntityCorrectness.checkAccountAndAuthority(account); // TODO assert

        GPOrganization org = organizationDao.findByName(
                account.getOrganization().getName());
        if (org == null) {
            throw new IllegalParameterFault(
                    "Account to save have an organization that does not exist");
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
            authority.setAccountNaturalID(account.getNaturalID());
        }
        authorityDao.persist(authorities.toArray(
                new GPAuthority[authorities.size()]));

        if (sendEmail && account instanceof GPUser) {
            GPUser user = (GPUser) account;

            GPUser clonedUser = this.cloneUser(user, plainPassword);
            gpSchedulerDelegate.sendEmailRegistration(clonedUser);
        }

        return account.getId();
    }

    @Override
    public Long updateUser(GPUser user) throws ResourceNotFoundFault, IllegalParameterFault {
        if (user == null || user.getId() == null) {
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

        this.updateAccountAuthorities(orig.getUsername(),
                user.getGPAuthorities());

        accountDao.merge(orig);
        return orig.getId();
    }

    @Override
    public Long updateOwnUser(UserDTO user,
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
            if (!this.gpDigester.matches(orig.getPassword(),
                    currentPlainPassword)) {
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
            gpSchedulerDelegate.sendEmailModification(orig, previousEmail,
                    newPlainPassword);
        }
        return orig.getId();
    }

    @Override
    public Long updateApplication(GPAccount application)
            throws ResourceNotFoundFault, IllegalParameterFault {
        if (application.getId() == null) {
            throw new IllegalArgumentException(
                    "Application \"ID\" must be NOT NULL");
        }
        GPApplication orig = (GPApplication) this.getAccountById(
                application.getId());
        EntityCorrectness.checkAccountLog(orig); // TODO assert

        // Set the values (except appID and property not managed)
        this.updateAccount(orig, application);

        this.updateAccountAuthorities(orig.getAppID(),
                application.getGPAuthorities());

        accountDao.merge(orig);
        return orig.getId();
    }

    @Override
    public Boolean deleteAccount(Long accountID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        List<GPAccountProject> accountProjectList = accountProjectDao.findByOwnerAccountID(
                accountID);
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

    @Override
    public GPUser getUserDetail(Long userID) throws ResourceNotFoundFault {
        GPUser user = (GPUser) this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return user;
    }

    @Override
    public GPApplication getApplicationDetail(Long applicationID) throws
            ResourceNotFoundFault {
        GPApplication application = (GPApplication) this.getAccountById(
                applicationID);
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return application;
    }

    @Override
    public UserDTOResponse getShortUser(Long userID) throws ResourceNotFoundFault {
        GPUser user = (GPUser) this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return new UserDTOResponse(new UserDTO(user));
    }

    @Override
    public ApplicationDTO getShortApplication(Long applicationID) throws
            ResourceNotFoundFault {
        GPApplication application = (GPApplication) this.getAccountById(
                applicationID);
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return new ApplicationDTO(application);
    }

    @Override
    public UserDTOResponse getShortUserByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        GPUser user = this.getUserByUsername(request.getNameLike());
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return new UserDTOResponse(new UserDTO(user));
    }

    @Override
    public GPUser getUserDetailByUsername(SearchRequest request)
            throws ResourceNotFoundFault {
        logger.debug("\n\n@@@@@@@@@@@@@@@@@ SearchRequest : {} "
                + "@@@@@@@@@@@@@@@@@@\n\n", request);

        GPUser user = this.getUserByUsername(request.getNameLike());
        // Set authorities
        user.setGPAuthorities(this.getGPAuthorities(user.getNaturalID()));
        EntityCorrectness.checkAccountLog(user); // TODO assert
        return user;
    }

    @Override
    public GPUser getUserDetailByUsernameAndPassword(String username, String plainPassword) throws ResourceNotFoundFault, IllegalParameterFault,
            AccountLoginFault {
        GPUser user = accountDao.findByUsername(username);
        if (user == null) {
            user = accountDao.findByEmail(username);
            if (user == null) {
                throw new ResourceNotFoundFault(
                        "User not found (username or email=" + username + ")");
            }
        }
        EntityCorrectness.checkAccountLog(user); // TODO assert

        if (!user.isAccountNonExpired()) {
            throw new AccountLoginFault(
                    AccountLoginFault.LoginFaultType.ACCOUNT_EXPIRED, username);
        }
        if (!user.isEnabled()) {
            throw new AccountLoginFault(
                    AccountLoginFault.LoginFaultType.ACCOUNT_DISABLED,
                    username);
        }

        // Check password
        if (!this.gpDigester.matches(user.getPassword(), plainPassword)) {
            throw new IllegalParameterFault("Specified password was incorrect");
        }

        // Set authorities
        user.setGPAuthorities(this.getGPAuthorities(user.getNaturalID()));

        return user;
    }

    @Override
    public GPApplication getApplication(String appID)
            throws ResourceNotFoundFault, AccountLoginFault {
        GPApplication application = this.getApplicationByAppId(appID);
        EntityCorrectness.checkAccountLog(application); // TODO assert

        if (!application.isAccountNonExpired()) {
            throw new AccountLoginFault(
                    AccountLoginFault.LoginFaultType.ACCOUNT_EXPIRED, appID);
        }
        if (!application.isEnabled()) {
            throw new AccountLoginFault(
                    AccountLoginFault.LoginFaultType.ACCOUNT_DISABLED, appID);
        }

        return application;
    }

    @Override
    public ApplicationDTO getShortApplicationByAppID(SearchRequest request)
            throws ResourceNotFoundFault {
        GPApplication application = this.getApplicationByAppId(
                request.getNameLike());
        EntityCorrectness.checkAccountLog(application); // TODO assert
        return new ApplicationDTO(application);
    }

    @Override
    public SearchUsersResponseWS searchUsers(Long userID,
            PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        GPAccount user = this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert

        Search searchCriteria = new Search(GPAccount.class);
        searchCriteria.addFilterNotEqual("id", userID);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addFilterNotEmpty("username");
        searchCriteria.addSortAsc("username");
        searchCriteria.addFilterEqual("organization.name",
                user.getOrganization().getName());

        String like = request.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("username", like);
        }

        List<GPAccount> accountList = accountDao.search(searchCriteria);
        List<GPUser> userList = new ArrayList<>(accountList.size());
        for (GPAccount account : accountList) {
            account.setGPAuthorities(this.getGPAuthorities(
                    account.getNaturalID()));
            EntityCorrectness.checkAccountAndAuthorityLog(account); // TODO assert
            userList.add((GPUser) account);
        }
        return new SearchUsersResponseWS(AccountDTOFactory.buildUserDTOList(userList));
    }

    /**
     * @param userID
     * @param request
     * @return {@link SearchUsersResponseWS}
     * @throws ResourceNotFoundFault
     */
    @Override
    public SearchUsersResponseWS searchEnabledUsers(Long userID, PaginatedSearchRequest request) throws ResourceNotFoundFault {
        GPAccount user = this.getAccountById(userID);
        EntityCorrectness.checkAccountLog(user); // TODO assert

        Search searchCriteria = new Search(GPAccount.class);
        searchCriteria.addFilterNotEqual("id", userID);
        searchCriteria.setMaxResults(request.getNum());
        searchCriteria.setPage(request.getPage());
        searchCriteria.addFilterNotEmpty("username");
        searchCriteria.addSortAsc("username");
        searchCriteria.addFilterEqual("organization.name", user.getOrganization().getName());
        searchCriteria.addFilterEqual("enabled", TRUE);

        String like = request.getNameLike();
        if (like != null) {
            searchCriteria.addFilterILike("username", like);
        }

        List<GPAccount> accountList = accountDao.search(searchCriteria);
        List<GPUser> userList = new ArrayList<>(accountList.size());
        for (GPAccount account : accountList) {
            account.setGPAuthorities(this.getGPAuthorities(
                    account.getNaturalID()));
            EntityCorrectness.checkAccountAndAuthorityLog(account); // TODO assert
            userList.add((GPUser) account);
        }
        return new SearchUsersResponseWS(AccountDTOFactory.buildUserDTOList(userList));
    }

    @Override
    public ShortAccountDTOContainer getAllAccounts() {
        List<GPAccount> accountList = accountDao.findAll();
        EntityCorrectness.checkAccountListLog(accountList); // TODO assert
        return new ShortAccountDTOContainer(AccountDTOFactory
                .buildShortAccountDTOList(accountList));
    }

    @Override
    public ShortAccountDTOContainer getAccounts(String organization)
            throws ResourceNotFoundFault {
        GPOrganization org = organizationDao.findByName(organization);
        if (org == null) {
            throw new ResourceNotFoundFault(
                    "Organization \"" + organization + "\" not found.");
        }

        List<GPAccount> accountList = accountDao.findByOrganization(organization);
        EntityCorrectness.checkAccountListLog(accountList); // TODO assert

        return new ShortAccountDTOContainer(AccountDTOFactory
                .buildShortAccountDTOList(accountList));
    }

    @Override
    public Long getAccountsCount(SearchRequest request) {
        Search searchCriteria = new Search(GPAccount.class);

        if (request != null && request.getNameLike() != null) {
            Filter fUsername = Filter.ilike("username", request.getNameLike());
            Filter fAppId = Filter.ilike("appID", request.getNameLike());
            searchCriteria.addFilterOr(fUsername, fAppId);
        }
        return (long) accountDao.count(searchCriteria);
    }

    @Override
    public Long getUsersCount(String organization, SearchRequest request) {
        Search searchCriteria = new Search(GPAccount.class);
        searchCriteria.addFilterNotEmpty("username");
        searchCriteria.addFilterEqual("organization.name", organization);

        if (request != null && request.getNameLike() != null) {
            searchCriteria.addFilterILike("username", request.getNameLike());
        }
        return Long.valueOf(accountDao.count(searchCriteria));
    }

    @Override
    public GetAuthorityResponse getAuthorities(Long accountNaturalID) throws
            ResourceNotFoundFault {
        GPAccount account = this.getAccountById(accountNaturalID);
        EntityCorrectness.checkAccountLog(account); // TODO assert
        return new GetAuthorityResponse(this.getAuthorities(
                account.getNaturalID()));
    }

    @Override
    public GetAuthoritiesResponseWS getAuthoritiesDetail(String accountNaturalID)
            throws ResourceNotFoundFault {
        return new GetAuthoritiesResponseWS(
                this.getGPAuthorities(accountNaturalID));
    }

    @Override
    public void forceTemporaryAccount(Long accountID)
            throws ResourceNotFoundFault {
        GPAccount account = this.getAccountById(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        account.setAccountTemporary(true);
        accountDao.merge(account);
    }

    @Override
    public void forceExpiredTemporaryAccount(Long accountID)
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
     * **************************************************************************
     */
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

    private List<String> getAuthorities(String accountNaturalID) throws
            ResourceNotFoundFault {
        List<GPAuthority> authorities = this.getGPAuthorities(accountNaturalID);
        return this.convertAuthorities(authorities);
    }

    private List<GPAuthority> getGPAuthorities(String accountNaturalID) throws
            ResourceNotFoundFault {
        List<GPAuthority> authorities = authorityDao.findShortByAccountNaturalID(
                accountNaturalID);
        if (authorities.isEmpty()) {
            throw new ResourceNotFoundFault(
                    "Account (naturalID=" + accountNaturalID + ") has no authority");
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

    private GPAccount getAccountById(Long accountID) throws
            ResourceNotFoundFault {
        GPAccount account = accountDao.find(accountID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account not found", accountID);
        }
        return account;
    }

    private GPUser getUserByUsername(String username) throws
            ResourceNotFoundFault {
        GPUser user = accountDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundFault(
                    "User not found (username=" + username + ")");
        }
        return user;
    }

    private GPApplication getApplicationByAppId(String appID) throws
            ResourceNotFoundFault {
        GPApplication application = accountDao.findByAppID(appID);
        if (application == null) {
            throw new ResourceNotFoundFault(
                    "Application not found (appID=" + appID + ")");
        }
        return application;
    }

    private void checkDuplicateAccount(GPAccount account) throws
            IllegalParameterFault {
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

    private void updateAccountAuthorities(String accountNaturalID,
            List<GPAuthority> authorities) {
        if (authorities != null && !authorities.isEmpty()) {
            if (authorities.size() > 1) {
                throw new UnsupportedOperationException(
                        "Not supported the update of multi-authorities: new authorities have more than one authority");
            }

            List<GPAuthority> origAuthorities = authorityDao.findByAccountNaturalID(
                    accountNaturalID);
            if (origAuthorities.size() > 1) {
                throw new UnsupportedOperationException(
                        "Not supported the update of multi-authorities: persisted authorities have more than one authority");
            }

            GPAuthority authority = authorities.get(0);
            GPAuthority origAuthority = origAuthorities.get(0);
            if (!authority.getAuthority().equals(origAuthority.getAuthority())
                    || authority.getTrustedLevel() != origAuthority.getTrustedLevel()) {
                origAuthority.setAuthority(authority.getAuthority());
                origAuthority.setTrustedLevel(authority.getTrustedLevel());
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

//        if (!accountToUpdate.isAccountTemporary() && account.isAccountTemporary()) {
//            throw new IllegalParameterFault(
//                    "A standard account cannot be changed to temporary account");
//        }
        accountToUpdate.setAccountTemporary(account.isAccountTemporary());
    }

}
