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
package org.geosdi.geoplatform;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.request.LikePatternType;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.UserDTO;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class WSUserTest extends ServiceTest {
    // TODO check:
    //      searchUsers()
    //      updateUser()

    @Test
    public void testUsersDB() {
        List<UserDTO> userList = gpWSClient.getUsers();
        logger.info("\n*** Number of Users into DB: {} ***", userList.size());
        if (userList != null) {
            for (Iterator<UserDTO> it = userList.iterator(); it.hasNext();) {
                logger.info("\n*** USER into DB:\n{}\n***", it.next());
            }
        }
    }

    @Test
    public void testRetrieveUser() throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ testRetrieveUser @@@");

        // Number of Users
        List<UserDTO> userList = gpWSClient.getUsers();
        Assert.assertNotNull(userList);
        Assert.assertTrue("Number of Users stored into database", userList.size() >= 1); // super.SetUp() added 1 user

        // Number of User Like
        long numUsersLike = gpWSClient.getUsersCount(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertEquals("Number of User Like", new Long(1).longValue(), numUsersLike);

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
        userDTOFromWS = gpWSClient.getShortUserByName(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertNotNull(userDTOFromWS);
        Assert.assertEquals("Error found UserDTO from Username", idUserTest, userDTOFromWS.getId().longValue());
        // Get GPUser from Username
        userFromWS = gpWSClient.getUserDetailByName(
                new SearchRequest(usernameTest, LikePatternType.CONTENT_EQUALS));
        Assert.assertNotNull(userFromWS);
        Assert.assertEquals("Error found GPUser from Username", idUserTest, userFromWS.getId().longValue());
    }

    @Test
    public void testInsertUserWithNoRoles() {
        try {
            super.createAndInsertUser("user-no-roles");
            Assert.fail("User must have at least a role");
        } catch (IllegalParameterFault ex) {
        }
    }

    @Test
    public void testInsertUserWithIncorrectRole() {
        GPAuthority authority = new GPAuthority();
        authority.setAuthority("Incorrect");

        GPUser user = super.createUser("user-incorrect-role");
        user.setGPAuthorities(Arrays.asList(authority));

        try {
            gpWSClient.insertUser(user);
            Assert.fail("User have an incorrect role");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("authority")) { // Must be fail for other reasons
                Assert.fail("Not fail for User have an incorrect role, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testInsertUserWithSingleRole() throws ResourceNotFoundFault {
        List<GPAuthority> authorities = gpWSClient.getUserGPAuthorities(usernameTest);
        Assert.assertNotNull("Authorities null", authorities);
        Assert.assertEquals("Number of Authorities of " + usernameTest, 1, authorities.size());

        GPAuthority authority = authorities.get(0);
        Assert.assertNotNull(authority);
        Assert.assertEquals("Authority string", GPRole.USER.toString(), authority.getAuthority());
        Assert.assertEquals("Authority username", usernameTest, authority.getUsername());
        Assert.assertEquals("Authority user.id", userTest.getId(), authority.getUser().getId());
    }

    @Test
    public void testInsertUserWithMultiRole() throws IllegalParameterFault, ResourceNotFoundFault {
        String usernameMultiRole = "username-multi-role";
        Long idUser = super.createAndInsertUser(usernameMultiRole, GPRole.ADMIN, GPRole.VIEWER);
        GPUser user = gpWSClient.getUserDetail(idUser);

        try {
            List<GPAuthority> authorities = gpWSClient.getUserGPAuthorities(usernameMultiRole);
            Assert.assertNotNull(authorities);
            Assert.assertEquals("Number of Authorities of " + usernameMultiRole, 2, authorities.size());

            GPAuthority authority = authorities.get(0);
            Assert.assertNotNull(authority);
            Assert.assertEquals("Authority string", GPRole.ADMIN.toString(), authority.getAuthority());
            Assert.assertEquals("Authority username", usernameMultiRole, authority.getUsername());
            Assert.assertEquals("Authority user.id", user.getId(), authority.getUser().getId());

            authority = authorities.get(1);
            Assert.assertNotNull(authority);
            Assert.assertEquals("Authority string", GPRole.VIEWER.toString(), authority.getAuthority());
            Assert.assertEquals("Authority username", usernameMultiRole, authority.getUsername());
            Assert.assertEquals("Authority user.id", user.getId(), authority.getUser().getId());
        } finally {
            boolean check = gpWSClient.deleteUser(idUser);
            Assert.assertTrue(check);
        }
    }

    @Test
    public void testInsertDuplicateUserWRTUsername() {
        GPUser user = super.createUser(super.usernameTest, GPRole.USER);
        try {
            gpWSClient.insertUser(user);
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
            gpWSClient.insertUser(user);
            Assert.fail("User already exist wrt email");
        } catch (IllegalParameterFault ex) {
            if (!ex.getMessage().toLowerCase().contains("email")) { // Must be fail for other reasons
                Assert.fail("Not fail for User already exist wrt email, but for: " + ex.getMessage());
            }
        }
    }

    @Test
    public void testGetUserDetailByUsernameAndPassword1()
            throws IllegalParameterFault, ResourceNotFoundFault {
        GPUser user = gpWSClient.getUserDetailByUsernameAndPassword(usernameTest, "pwd_username_test_ws");
        Assert.assertNotNull("User is null", user);
    }

    @Test
    public void testGetUserDetailByUsernameAndPassword2() {
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
    public void testGetUserDetailByUsernameAndPassword3() {
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
