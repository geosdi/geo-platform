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


package org.geosdi.geoplatform;


import java.text.ParseException;
import java.util.Iterator;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ShortUser;
import org.geosdi.geoplatform.responce.UserList;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSUsersTest extends CXFServiceTest{

    @Test
    public void testUsers() {
        UserList userList = geoPlatformService.getUsers();

        if (userList != null) {
            for (Iterator<ShortUser> it = userList.getList().iterator(); it.hasNext();) {
                logger.info("USER ********************* " + it.next());

            }
        }
    }

    @Test
    public void testEncryptedWS() throws ParseException, ResourceNotFoundFault {
        logger.info("################### Inizio testEncryptedWS");
//        GPUser user = new GPUser();
//        user.setId(-1);
//        user.setUsername("user");
//        user.setPassword("password");
//        user.setEmailAddress("user@geosdi.org");
//        geoPlatformService.insertUser(user);
        
        UserList userList = geoPlatformService.getUsers();
        Assert.assertNotNull(userList);
        Assert.assertTrue("Number of users stored into database", userList.getList().size() >= 1);
//        try {
//            geoPlatformService.deleteUser(new RequestById(-1));
//        } catch (IllegalParameterFault ex) {
//            logger.error("Error while deleting user");
//            Assert.fail();
//        }
        logger.info("################### Fine testEncryptedWS");
    }

}
