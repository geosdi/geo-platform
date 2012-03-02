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
package org.geosdi.geoplatform.modelws;

import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.AccountExpiredFault;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.UserDTO;
import org.junit.Test;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 * 
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class WSAccountTest extends ServiceTest {
    // TODO check:
    //      searchUsers()
    //      updateUser()

    @Test
    public void testAccountsDB() {
        List<ShortAccountDTO> accountList = gpWSClient.getAccounts();
        logger.info("\n*** Number of Accounts into DB: {} ***", accountList.size());
        if (accountList != null) {
            for (Iterator<ShortAccountDTO> it = accountList.iterator(); it.hasNext();) {
                logger.info("\n*** Account into DB:\n{}\n***", it.next());
            }
        }
    }

    @Test
    public void testRetrieveUser() throws ResourceNotFoundFault {
        // Number of Accounts
        List<ShortAccountDTO> accountList = gpWSClient.getAccounts();
        Assert.assertNotNull(accountList);
        Assert.assertTrue("Number of Accounts stored into database", accountList.size() >= 1); // super.SetUp() added 1 user

        // Number of Account Like
        long numAccountsLike = gpWSClient.getAccountsCount(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertEquals("Number of Account Like", new Long(1).longValue(), numAccountsLike);

        // Get User from Id
        // Get UserDTO from Id
        UserDTO userDTOFromWS = gpWSClient.getShortUser(idUserTest);
        Assert.assertNotNull(userDTOFromWS);
        Assert.assertEquals("Error found UserDTO from Id", idUserTest, userDTOFromWS.getId().longValue());
        // Get GPUser from Id
        GPUser userFromWS = gpWSClient.getUserDetail(idUserTest);
        Assert.assertNotNull(userFromWS);
        Assert.assertEquals("Error found GPUser from Id", idUserTest, userFromWS.getId().longValue());

        // Get User from Username
        // Get UserDTO from Username
        userDTOFromWS = gpWSClient.getShortUserByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertNotNull(userDTOFromWS);
        Assert.assertEquals("Error found UserDTO from Username", idUserTest, userDTOFromWS.getId().longValue());
        // Get GPUser from Username
        userFromWS = gpWSClient.getUserDetailByUsername(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertNotNull(userFromWS);
        Assert.assertEquals("Error found GPUser from Username", idUserTest, userFromWS.getId().longValue());
    }

    @Test(expected = IllegalParameterFault.class)
    public void testInsertUserWithNoRoles() throws IllegalParameterFault {
        super.createAndInsertUser("user-no-roles");
    }

    @Test
    public void testInsertUserWithSingleRole() throws ResourceNotFoundFault {
        List<GPAuthority> authorities = gpWSClient.getAuthoritiesDetail(usernameTest);
        Assert.assertNotNull("Authorities null", authorities);
        Assert.assertEquals("Number of Authorities of " + usernameTest, 1, authorities.size());

        GPAuthority authority = authorities.get(0);
        Assert.assertNotNull(authority);
        Assert.assertEquals("Authority string", GPRole.USER.toString(), authority.getAuthority());
        Assert.assertEquals("Authority username", usernameTest, authority.getStringID());
    }

    @Test
    public void testInsertUserWithMultiRole() throws IllegalParameterFault, ResourceNotFoundFault {
        String usernameMultiRole = "username-multi-role";
        Long idUser = super.createAndInsertUser(usernameMultiRole, GPRole.ADMIN, GPRole.VIEWER);

        try {
            List<GPAuthority> authorities = gpWSClient.getAuthoritiesDetail(usernameMultiRole);
            Assert.assertNotNull(authorities);
            Assert.assertEquals("Number of Authorities of " + usernameMultiRole, 2, authorities.size());

            GPAuthority authority = authorities.get(0);
            Assert.assertNotNull(authority);
            Assert.assertEquals("Authority string", GPRole.ADMIN.toString(), authority.getAuthority());
            Assert.assertEquals("Authority username", usernameMultiRole, authority.getStringID());

            authority = authorities.get(1);
            Assert.assertNotNull(authority);
            Assert.assertEquals("Authority string", GPRole.VIEWER.toString(), authority.getAuthority());
            Assert.assertEquals("Authority username", usernameMultiRole, authority.getStringID());
        } finally {
            boolean check = gpWSClient.deleteAccount(idUser);
            Assert.assertTrue(check);
        }
    }

    @Test
    public void testInsertDuplicateUserWRTUsername() {
        GPUser user = super.createUser(super.usernameTest, GPRole.USER);
        try {
            gpWSClient.insertAccount(user, false);
            Assert.fail("User already exist wrt username");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("username")) { // Must be fail for other reasons
                Assert.fail("Not fail for User already exist wrt username, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testInsertDuplicateUserWRTUEmail() {
        GPUser user = super.createUser("duplicate-email", GPRole.USER);
        user.setEmailAddress(super.userTest.getEmailAddress());
        try {
            gpWSClient.insertAccount(user, false);
            Assert.fail("User already exist wrt email");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("email")) { // Must be fail for other reasons
                Assert.fail("Not fail for User already exist wrt email, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testGetUserDetailByUsernameAndPassword1()
            throws IllegalParameterFault, ResourceNotFoundFault, AccountExpiredFault {
        GPUser user = gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, "pwd_username_test_ws");
        Assert.assertNotNull("User is null", user);
    }

    @Test
    public void testGetUserDetailByUsernameAndPassword2()
            throws AccountExpiredFault {
        GPUser user = null;
        try {
            String newUsername = usernameTest + "_";
            user = gpWSClient.getUserDetailByUsernameAndPassword(newUsername, "pwd_username_test_ws");
            Assert.fail("Test must fail because username is wrong");
        } catch (ResourceNotFoundFault ex) {
            Assert.assertNull("User is not null", user);
        } catch (IllegalParameterFault ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testGetUserDetailByUsernameAndPassword3()
            throws AccountExpiredFault {
        GPUser user = null;
        try {
            user = gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, "pwd_username_test_ws_");
            Assert.fail("Test must fail because password is wrong");
        } catch (ResourceNotFoundFault ex) {
            Assert.fail(ex.getMessage());
        } catch (IllegalParameterFault ex) {
            Assert.assertNull("User is not null", user);
        }
    }
}
