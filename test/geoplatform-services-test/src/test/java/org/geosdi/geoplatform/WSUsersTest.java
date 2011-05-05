//<editor-fold defaultstate="collapsed" desc="License">
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
//</editor-fold>
package org.geosdi.geoplatform;

import java.util.Iterator;
import org.junit.Test;
import junit.framework.Assert;

import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.UserDTO;
import org.geosdi.geoplatform.responce.collection.UserList;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSUsersTest extends ServiceTest {
    // TODO check:
    //      searchUsers()
    //      updateUser()

    @Test
    public void testUsersDB() {
        UserList userList = geoPlatformService.getUsers();
        logger.info("\n***** Number of Users in the DB: " + userList.getList().size());
        if (userList != null) {
            for (Iterator<UserDTO> it = userList.getList().iterator(); it.hasNext();) {
                logger.info("\nUSER ***** " + it.next());

            }
        }
    }

    @Test
    public void testManageUser() {
        // Number of User
        UserList userList = geoPlatformService.getUsers();
        Assert.assertNotNull(userList);
        Assert.assertTrue("Number of Users stored into database", userList.getList().size() >= 1);

        // Number of User Like
        long numUsersLike = geoPlatformService.getUsersCount(new SearchRequest(username));
        Assert.assertEquals("Number of User Like", numUsersLike, new Long(1).longValue());

        // Get User from Id
        try {
            // Get UserDTO from Id
            UserDTO userDTOFromWS = geoPlatformService.getShortUser(new RequestById(idUser));
            Assert.assertNotNull(userDTOFromWS);
            Assert.assertEquals("Error found User from Id", idUser, userDTOFromWS.getId());
            // Get GPUser from Id
            GPUser userFromWS = geoPlatformService.getUserDetail(new RequestById(idUser));
            Assert.assertNotNull(userFromWS);
            Assert.assertEquals("Error found User from Id", idUser, userFromWS.getId());
        } catch (ResourceNotFoundFault ex) {
            logger.error("Not found User with Id: " + idUser);
            Assert.fail();
        }

        // Get User from Username
        try {
            // Get UserDTO from Username
            UserDTO userDTOFromWS = geoPlatformService.getShortUserByName(new SearchRequest(username));
            Assert.assertNotNull(userDTOFromWS);
            Assert.assertEquals("Error found User from Username", idUser, userDTOFromWS.getId());
            // Get GPUser from Username
            GPUser userFromWS = geoPlatformService.getUserDetailByName(new SearchRequest(username));
            Assert.assertNotNull(userFromWS);
            Assert.assertEquals("Error found User from Username", idUser, userFromWS.getId());
        } catch (ResourceNotFoundFault ex) {
            logger.error("Not found User with Username: " + username);
            Assert.fail();
        }
    }
}
