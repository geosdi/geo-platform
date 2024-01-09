/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.model.rest;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.AccountLoginFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.rs.GPRestExceptionMessage;
import org.geosdi.geoplatform.request.InsertAccountRequest;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.response.ApplicationDTO;
import org.geosdi.geoplatform.response.ShortAccountDTO;
import org.geosdi.geoplatform.response.UserDTO;
import org.geosdi.geoplatform.response.UserDTOResponse;
import org.junit.Test;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.geosdi.geoplatform.gui.shared.GPRole.*;
import static org.geosdi.geoplatform.request.LikePatternType.CONTENT_EQUALS;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class RSAccountTest extends BasicRestServiceTest {

    @Test
    public void testAllAccountsRest() {
        List<ShortAccountDTO> accountList = gpWSClient.getAllAccounts().getAccounts();
        assertNotNull(accountList);
        logger.info("\n*** Number of Accounts into DB: {} ***", accountList.size());
        for (ShortAccountDTO account : accountList) {
            if (account instanceof UserDTO) {
                logger.info("\n*** User into DB:\n{}\n***", account);
            } else if (account instanceof ApplicationDTO) {
                logger.info("\n*** Application into DB:\n{}\n***", account);
            }
        }
    }

    @Test
    public void testAllOrganizationAccountsRest() throws Exception {
        // Initial test
        List<ShortAccountDTO> accountList = gpWSClient.getAccounts(organizationNameRSTest).getAccounts();
        assertNotNull(accountList);
        int numAccounts = accountList.size();
        logger.info("\n*** Number of Accounts for Organization \"{}\": {} ***", organizationNameRSTest, numAccounts);
        for (ShortAccountDTO account : accountList) {
            assertEquals(organizationNameRSTest, account.getOrganization());
        }
        // Insert User of the organization for test
        this.createAndInsertUser("to_search", organizationTest, USER);
        // Insert the other Organization and a User for it
        GPOrganization otherOrganization = new GPOrganization("other_organization_ws_test");Long otherOrganizationID = gpWSClient.insertOrganization(otherOrganization);
        this.createAndInsertUser("none_search", otherOrganization, USER);
        // Final test
        accountList = gpWSClient.getAccounts(organizationNameRSTest).getAccounts();
        assertNotNull(accountList);
        assertEquals(numAccounts + 1, accountList.size());
        for (ShortAccountDTO account : accountList) {
            assertEquals(organizationNameRSTest, account.getOrganization());
        }
        // Delete the other Organization
        gpWSClient.deleteOrganization(otherOrganizationID);
    }

    @Test(expected = NotFoundException.class)
    public void testAllOrganizationAccountsIncorrectRest() throws Exception {
        String wrongOrganizationName = organizationNameRSTest + "_";
        gpWSClient.getAccounts(wrongOrganizationName);
    }

    @Test
    public void testRetrieveUserRest() throws ResourceNotFoundFault {
        // Number of Account Like
        long numAccountsLike = gpWSClient.getAccountsCount(new SearchRequest(usernameTest, CONTENT_EQUALS));
        assertEquals("Number of Account Like", 1L, numAccountsLike);
        // Get User from Id
        // Get UserDTO from Id
        UserDTOResponse userDTOResponse = gpWSClient.getShortUser(this.userTest.getId());
        UserDTO userDTOFromWS = userDTOResponse.getUserDTO();
        assertNotNull(userDTOFromWS);
        assertEquals("Error found UserDTO from Id", this.userTest.getId().longValue(),
                userDTOFromWS.getId().longValue());
        // Get GPUser from Id
        GPUser userFromWS = gpWSClient.getUserDetail(this.userTest.getId());
        assertNotNull(userFromWS);
        assertEquals("Error found GPUser from Id", this.userTest.getId().longValue(),
                userFromWS.getId().longValue());

        // Get User from Username
        // Get UserDTO from Username
        userDTOFromWS = gpWSClient.getShortUserByUsername(
                new SearchRequest(usernameTest, CONTENT_EQUALS)).getUserDTO();
        assertNotNull(userDTOFromWS);
        assertEquals("Error found UserDTO from Username", this.userTest.getId().longValue(),
                userDTOFromWS.getId().longValue());
        // Get GPUser from Username
        userFromWS = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, CONTENT_EQUALS));
        assertNotNull(userFromWS);
       assertEquals("Error found GPUser from Username", this.userTest.getId().longValue(),
                userFromWS.getId().longValue());
    }

    @Test(expected = BadRequestException.class)
    public void testInsertUserWithNoRolesRest() throws IllegalParameterFault {
        super.createAndInsertUser("user-no-roles-rest", organizationTest);
    }

    @Test
    public void testInsertUserWithSingleRoleRest() throws ResourceNotFoundFault {
        List<GPAuthority> authorities = gpWSClient.getAuthoritiesDetail(usernameTest).getAuthorities();
        assertNotNull("Authorities null", authorities);
        assertEquals("Number of Authorities of " + usernameTest, 1, authorities.size());
        GPAuthority authority = authorities.get(0);
        assertNotNull(authority);
        assertEquals("Authority string", USER.getRole(), authority.getAuthority());
        assertEquals("Authority level", super.getTrustedLevelByRole(USER), authority.getTrustedLevel());
        assertEquals("Authority username", usernameTest, authority.getAccountNaturalID());
    }

    @Test
    public void testInsertUserWithMultiRoleRest() throws IllegalParameterFault,
            ResourceNotFoundFault {
        String usernameMultiRole = "user-multi-role-rs";
        GPUser user = super.createAndInsertUser(usernameMultiRole,
                organizationTest, ADMIN, VIEWER);

        try {
            List<GPAuthority> authorities = gpWSClient.getAuthoritiesDetail(usernameMultiRole).getAuthorities();
            assertNotNull(authorities);
            assertEquals("Number of Authorities of " + usernameMultiRole, 2, authorities.size());
            boolean isAdmin = false;
            boolean isViewer = false;
            for (GPAuthority authority : authorities) {
                assertNotNull(authority);
                assertEquals("Authority email", usernameMultiRole, authority.getAccountNaturalID());
                if (ADMIN.getRole().equals(authority.getAuthority())) {
                    isAdmin = true;
                } else if (VIEWER.getRole().equals(authority.getAuthority())) {
                    isViewer = true;
                }
            }
            assertTrue("Authority ADMIN string", isAdmin);
            assertTrue("Authority VIEWER string", isViewer);
        } finally {
            boolean check = gpWSClient.deleteAccount(user.getId());
            assertTrue(check);
        }
    }

    @Test
    public void testInsertDuplicateUserWRTUsernameRest() throws Exception {
        GPUser user = super.createUser(usernameTest, organizationTest, USER);
        try {
            gpWSClient.insertAccount(new InsertAccountRequest(user, FALSE));
            fail("User already exist wrt username");
        } catch (ClientErrorException ex) {
            GPRestExceptionMessage exMess = ex.getResponse().readEntity(GPRestExceptionMessage.class);
            logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", exMess);
            if (!exMess.getMessage().toLowerCase().contains("username")) { // Must be fail for other reasons
                fail("Not fail for User already exist wrt username, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testInsertDuplicateUserWRTEmailRest() throws Exception {
        GPUser user = super.createUser("duplicate-email-rs", organizationTest, USER);
        user.setEmailAddress(super.userTest.getEmailAddress());
        try {
            gpWSClient.insertAccount(new InsertAccountRequest(user, FALSE));
            fail("User already exist wrt email");
        } catch (ClientErrorException ex) {
            GPRestExceptionMessage exMess = ex.getResponse().readEntity(GPRestExceptionMessage.class);
            logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", exMess);
            if (!exMess.getMessage().toLowerCase().contains("email")) { // Must be fail for other reasons
                fail("Not fail for User already exist wrt email, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testInsertIncorrectUserWRTUOrganizationRest() throws Exception {
        GPUser user = super.createUser("no-organization-rs", new GPOrganization("organization-inexistent-rs"), USER);
        try {
            gpWSClient.insertAccount(new InsertAccountRequest(user, FALSE));
            fail("User incorrect wrt organization");
        } catch (ClientErrorException ex) {
            GPRestExceptionMessage exMess = ex.getResponse().readEntity(GPRestExceptionMessage.class);
            logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", exMess);
            if (!exMess.getMessage().toLowerCase().contains("organization")) { // Must be fail for other reasons
                fail(
                        "Not fail for User incorrect wrt organization, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testAuthorizationCorrectUsernameRest() throws Exception {
        GPUser user = gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, passwordTest);
        assertNotNull("User is null", user);
        assertEquals(usernameTest, user.getUsername());
    }

    @Test
    public void testAuthorizationCorrectEmailRest() throws Exception {
        GPUser user = gpWSClient.getUserDetailByUsernameAndPassword(emailTest, passwordTest);
        assertNotNull("User is null", user);
        assertEquals(emailTest, user.getEmailAddress());
    }

    @Test(expected = NotFoundException.class)
    public void testAuthorizationIncorrectUsernameRest() throws Exception {
        String wrongUsername = usernameTest + "_";
        gpWSClient.getUserDetailByUsernameAndPassword(wrongUsername, passwordTest);
    }

    @Test(expected = NotFoundException.class)
    public void testAuthorizationIncorrectEmailRest() throws Exception {
        String wrongEmail = emailTest + "_";
        gpWSClient.getUserDetailByUsernameAndPassword(wrongEmail, passwordTest);
    }

    @Test(expected = BadRequestException.class)
    public void testAuthorizationIncorrectPasswordRest() throws Exception {
        String wrongPassword = passwordTest + "_";
        gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, wrongPassword);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testLoginFaultUserDisabledRest() throws ResourceNotFoundFault, IllegalParameterFault, AccountLoginFault {
        // Set disabled user
        userTest.setEnabled(false);
        gpWSClient.updateUser(userTest);

        // Must be throws AccountLoginFault because the user is disabled
        gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, passwordTest);
    }

    @Test
    public void updateUserRestTest() throws Exception {
        GPUser user1 = super.createAndInsertUser("userToUpdate-SOAP", organizationTest, ADMIN);

        GPUser user = gpWSClient.getUserDetail(user1.getId());
        logger.info("##################USER : {}\n", user);

        user.setName("UserToUpdate");
        gpWSClient.updateUser(user);

        user = gpWSClient.getUserDetail(user1.getId());
        logger.info("#################USER_UPDATED : {}\n", user);

        gpWSClient.deleteAccount(user1.getId());
    }

    @Test
    public void searchUsersTestRest() throws Exception {
        String usernameMultiRole = "user-test1-rs";
        GPUser user = super.createAndInsertUser(usernameMultiRole,
                organizationTest, ADMIN, VIEWER);

        try {
            insertMassiveUsers("-rs");
            List<UserDTO> users = gpWSClient.searchUsers(user.getId(),
                    new PaginatedSearchRequest(25, 0)).getSearchUsers();

            assertEquals(25, users.size());

            assertEquals(6, gpWSClient.searchUsers(user.getId(),
                    new PaginatedSearchRequest(25, 1)).getSearchUsers().size());

            Long userCount = gpWSClient.getUsersCount(organizationTest.getName(),
                    null);

            assertEquals(32, userCount.intValue());
        } finally {
            Boolean check = gpWSClient.deleteAccount(user.getId());
            assertTrue(check);
        }
    }

    @Test
    public void getAuthoritiesTestRest() throws Exception {
        String usernameMultiRole = "user-auth-rs";
        GPUser user = super.createAndInsertUser(usernameMultiRole,
                organizationTest, ADMIN, VIEWER);

        List<String> authorities = gpWSClient.getAuthorities(user.getId()).getAuthorities();
        assertEquals(2, authorities.size());

        logger.debug("\n@@@@@@@@@@@@@@@@@@@@@@Authorities : {}", authorities);
    }

    @Test
    public void forceTemporaryAccountTestRest() throws Exception {
        String usernameTmp = "user-tmp-rs";
        GPUser user = super.createAndInsertUser(usernameTmp,
                organizationTest, ADMIN, VIEWER);

        gpWSClient.forceTemporaryAccount(user.getId());

        GPUser tmpUser = gpWSClient.getUserDetail(user.getId());
        assertEquals(Boolean.TRUE, tmpUser.isAccountTemporary());

        gpWSClient.forceExpiredTemporaryAccount(user.getId());
        tmpUser = gpWSClient.getUserDetail(user.getId());

        assertEquals(Boolean.FALSE, tmpUser.isAccountNonExpired());
    }

}
