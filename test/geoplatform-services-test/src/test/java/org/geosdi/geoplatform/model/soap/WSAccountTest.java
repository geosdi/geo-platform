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
package org.geosdi.geoplatform.model.soap;

import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.response.ApplicationDTO;
import org.geosdi.geoplatform.response.ShortAccountDTO;
import org.geosdi.geoplatform.response.UserDTO;
import org.geosdi.geoplatform.response.UserDTOResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WSAccountTest extends BaseSoapServiceTest {

    @Test
    public void testAllAccounts() {
        List<ShortAccountDTO> accountList = gpWSClient.getAllAccounts().getAccounts();
        Assert.assertNotNull(accountList);
        logger.info("\n*** Number of Accounts into DB: {} ***",
                accountList.size());
        for (ShortAccountDTO account : accountList) {
            if (account instanceof UserDTO) {
                logger.info("\n*** User into DB:\n{}\n***", (UserDTO) account);
            } else if (account instanceof ApplicationDTO) {
                logger.info("\n*** Application into DB:\n{}\n***",
                        (ApplicationDTO) account);
            }
        }
    }

    @Test
    public void testAllOrganizationAccounts() throws Exception {
        // Initial test
        List<ShortAccountDTO> accountList = gpWSClient.getAccounts(
                organizationNameTest).getAccounts();
        Assert.assertNotNull(accountList);
        int numAccounts = accountList.size();
        logger.info("\n*** Number of Accounts for Organization \"{}\": {} ***",
                organizationNameTest, numAccounts);
        for (ShortAccountDTO account : accountList) {
            Assert.assertEquals(organizationNameTest, account.getOrganization());
        }

        // Insert User of the organization for test
        this.createAndInsertUser("to_search", organizationTest, GPRole.USER);

        // Insert the other Organization and a User for it
        GPOrganization otherOrganization = new GPOrganization(
                "other_organization_ws_test");
        Long otherOrganizationID = gpWSClient.insertOrganization(
                otherOrganization);
        this.createAndInsertUser("none_search", otherOrganization, GPRole.USER);

        // Final test
        accountList = gpWSClient.getAccounts(organizationNameTest).getAccounts();
        Assert.assertNotNull(accountList);
        Assert.assertEquals(numAccounts + 1, accountList.size());
        for (ShortAccountDTO account : accountList) {
            Assert.assertEquals(organizationNameTest, account.getOrganization());
        }

        // Delete the other Organization
        gpWSClient.deleteOrganization(otherOrganizationID);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testAllOrganizationAccountsIncorrect() throws Exception {
        String wrongOrganizationName = organizationNameTest + "_";
        gpWSClient.getAccounts(wrongOrganizationName);
    }

    @Test
    public void testRetrieveUser() throws ResourceNotFoundFault {
        // Number of Account Like
        long numAccountsLike = gpWSClient.getAccountsCount(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertEquals("Number of Account Like", 1L, numAccountsLike);

        // Get User from Id
        // Get UserDTO from Id
        UserDTOResponse userDTOResponse = gpWSClient.getShortUser(idUserTest);
        UserDTO userDTOFromWS = userDTOResponse.getUserDTO();
        Assert.assertNotNull(userDTOFromWS);
        Assert.assertEquals("Error found UserDTO from Id", idUserTest,
                userDTOFromWS.getId().longValue());
        // Get GPUser from Id
        GPUser userFromWS = gpWSClient.getUserDetail(idUserTest);
        Assert.assertNotNull(userFromWS);
        Assert.assertEquals("Error found GPUser from Id", idUserTest,
                userFromWS.getId().longValue());

        // Get User from Username
        // Get UserDTO from Username
        userDTOFromWS = gpWSClient.getShortUserByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS)).getUserDTO();
        Assert.assertNotNull(userDTOFromWS);
        Assert.assertEquals("Error found UserDTO from Username", idUserTest,
                userDTOFromWS.getId().longValue());
        // Get GPUser from Username
        userFromWS = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertNotNull(userFromWS);
        Assert.assertEquals("Error found GPUser from Username", idUserTest,
                userFromWS.getId().longValue());
    }

    @Test(expected = IllegalParameterFault.class)
    public void testInsertUserWithNoRoles() throws IllegalParameterFault {
        super.createAndInsertUser("user-no-roles", organizationTest);
    }

    @Test
    public void testInsertUserWithSingleRole() throws ResourceNotFoundFault {
        List<GPAuthority> authorities = gpWSClient.getAuthoritiesDetail(
                usernameTest).getAuthorities();
        Assert.assertNotNull("Authorities null", authorities);
        Assert.assertEquals("Number of Authorities of " + usernameTest, 1,
                authorities.size());

        GPAuthority authority = authorities.get(0);
        Assert.assertNotNull(authority);
        Assert.assertEquals("Authority string", GPRole.USER.getRole(),
                authority.getAuthority());
        Assert.assertEquals("Authority level", super.getTrustedLevelByRole(
                GPRole.USER), authority.getTrustedLevel());
        Assert.assertEquals("Authority username", usernameTest, authority.getAccountNaturalID());
    }

    @Test
    public void testInsertUserWithMultiRole() throws IllegalParameterFault,
            ResourceNotFoundFault {
        String usernameMultiRole = "user-multi-role";
        Long idUser = super.createAndInsertUser(usernameMultiRole,
                organizationTest, GPRole.ADMIN, GPRole.VIEWER);

        try {
            List<GPAuthority> authorities = gpWSClient.getAuthoritiesDetail(
                    usernameMultiRole).getAuthorities();
            Assert.assertNotNull(authorities);
            Assert.assertEquals("Number of Authorities of " + usernameMultiRole,
                    2, authorities.size());

            boolean isAdmin = false;
            boolean isViewer = false;
            for (GPAuthority authority : authorities) {
                Assert.assertNotNull(authority);
                Assert.assertEquals("Authority email", usernameMultiRole,
                        authority.getAccountNaturalID());
                if (GPRole.ADMIN.getRole().equals(authority.getAuthority())) {
                    isAdmin = true;
                } else if (GPRole.VIEWER.getRole().equals(
                        authority.getAuthority())) {
                    isViewer = true;
                }
            }
            Assert.assertTrue("Authority ADMIN string", isAdmin);
            Assert.assertTrue("Authority VIEWER string", isViewer);

        } finally {
            boolean check = gpWSClient.deleteAccount(idUser);
            Assert.assertTrue(check);
        }
    }

    @Test
    public void testInsertDuplicateUserWRTUsername() {
        GPUser user = super.createUser(usernameTest, organizationTest, GPRole.USER);
        try {
            gpWSClient.insertAccount(new InsertAccountRequest(user,
                    Boolean.FALSE));
            Assert.fail("User already exist wrt username");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("username")) { // Must be fail for other reasons
                Assert.fail(
                        "Not fail for User already exist wrt username, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testInsertDuplicateUserWRTEmail() {
        GPUser user = super.createUser("duplicate-email", organizationTest,
                GPRole.USER);
        user.setEmailAddress(super.userTest.getEmailAddress());
        try {
            gpWSClient.insertAccount(new InsertAccountRequest(user,
                    Boolean.FALSE));
            Assert.fail("User already exist wrt email");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("email")) { // Must be fail for other reasons
                Assert.fail(
                        "Not fail for User already exist wrt email, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testInsertIncorrectUserWRTUOrganization() {
        GPUser user = super.createUser("no-organization",
                new GPOrganization("organization-inexistent"), GPRole.USER);

        try {
            gpWSClient.insertAccount(new InsertAccountRequest(user,
                    Boolean.FALSE));
            Assert.fail("User incorrect wrt organization");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("organization")) { // Must be fail for other reasons
                Assert.fail(
                        "Not fail for User incorrect wrt organization, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testAuthorizationCorrectUsername() throws Exception {
        GPUser user = gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, passwordTest);
        Assert.assertNotNull("User is null", user);
        Assert.assertEquals(usernameTest, user.getUsername());
    }

    @Test
    public void testAuthorizationCorrectEmail() throws Exception {
        GPUser user = gpWSClient.getUserDetailByUsernameAndPassword(emailTest,
                passwordTest);
        Assert.assertNotNull("User is null", user);
        Assert.assertEquals(emailTest, user.getEmailAddress());
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testAuthorizationIncorrectUsername() throws Exception {
        String wrongUsername = usernameTest + "_";
        gpWSClient.getUserDetailByUsernameAndPassword(wrongUsername, passwordTest);
    }

    @Test(expected = ResourceNotFoundFault.class)
    public void testAuthorizationIncorrectEmail() throws Exception {
        String wrongEmail = emailTest + "_";
        gpWSClient.getUserDetailByUsernameAndPassword(wrongEmail, passwordTest);
    }

    @Test(expected = IllegalParameterFault.class)
    public void testAuthorizationIncorrectPassword() throws Exception {
        String wrongPassword = passwordTest + "_";
        gpWSClient.getUserDetailByUsernameAndPassword(usernameTest,
                wrongPassword);
    }

    @Test(expected = AccountLoginFault.class)
    public void testLoginFaultUserDisabled()
            throws ResourceNotFoundFault, IllegalParameterFault,
            AccountLoginFault {
        // Set disabled user
        userTest.setEnabled(false);
        gpWSClient.updateUser(userTest);

        // Must be throws AccountLoginFault because the user is disabled
        gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, passwordTest);
    }

    @Test(expected = AccountLoginFault.class)
    public void testLoginFaultUserExpired()
            throws ResourceNotFoundFault, IllegalParameterFault,
            AccountLoginFault {
        // Set temporary user
        gpWSClient.forceTemporaryAccount(idUserTest);

        // Set expired user (user must be temporary)
        gpWSClient.forceExpiredTemporaryAccount(idUserTest);

        // Must be throws AccountLoginFault because the user is expired
        gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, passwordTest);
    }

    @Test
    public void testUserErrorTemporarySetting()
            throws ResourceNotFoundFault, IllegalParameterFault {
        Assert.assertFalse("UserTest should be a standard account", userTest.isAccountTemporary());

        // Set the standard user to temporary (wrongly)
        userTest.setAccountTemporary(true);

        // Must be throws IllegalParameterFault because the standard user will be temporary
        gpWSClient.updateUser(userTest);
    }

    @Test
    public void updateUserSoapTest() throws Exception {
        Long idUser = super.createAndInsertUser("userToUpdate-SOAP", organizationTest, GPRole.ADMIN);

        GPUser user = gpWSClient.getUserDetail(idUser);
        logger.info("##################USER : {}\n", user);

        user.setName("UserToUpdate");
        gpWSClient.updateUser(user);

        user = gpWSClient.getUserDetail(idUser);
        logger.info("#################USER_UPDATED : {}\n", user);

        gpWSClient.deleteAccount(idUser);
    }

    @Test
    public void searchUsersTest() throws Exception {
        String usernameMultiRole = "user-test1-ws";
        Long idUser = super.createAndInsertUser(usernameMultiRole,
                organizationTest, GPRole.ADMIN, GPRole.VIEWER);

        try {
            insertMassiveUsers("-ws");
            List<UserDTO> users = gpWSClient.searchUsers(idUser,
                    new PaginatedSearchRequest(25, 0)).getSearchUsers();

            Assert.assertEquals(25, users.size());

            Assert.assertEquals(6, gpWSClient.searchUsers(idUser,
                    new PaginatedSearchRequest(25, 1)).getSearchUsers().size());
        } finally {
            Boolean check = gpWSClient.deleteAccount(idUser);
            Assert.assertTrue(check);
        }
    }
}
