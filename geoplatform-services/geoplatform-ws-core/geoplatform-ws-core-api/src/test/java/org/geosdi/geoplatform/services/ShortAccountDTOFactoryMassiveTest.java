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
package org.geosdi.geoplatform.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.response.ShortAccountDTO;
import org.geosdi.geoplatform.response.factory.AccountDTOFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ShortAccountDTOFactoryMassiveTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(
            ShortAccountDTOFactoryMassiveTest.class);

    @Test
    public void testShortAccountFactory() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Callable<ShortAccountDTO>> tasks = new ArrayList<Callable<ShortAccountDTO>>(
                1000);

        for (int i = 0; i < 1000; i++) {
            if (i < 500) {
                tasks.add(new CallableEntity(createGPUser(i)));
            } else {
                tasks.add(new CallableEntity(createGPApplication(i)));
            }
        }

        List<Future<ShortAccountDTO>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (flag) {
            for (Future<ShortAccountDTO> future : results) {
                logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", future.get());
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }
    }

    private GPUser createGPUser(int i) {
        GPUser user = new GPUser();
        user.setId(new Long(i));
        user.setName("TEST" + i);
        user.setUsername("TEST" + i);
        user.setEmailAddress("user@test.it");
        user.setSendEmail(Boolean.FALSE);
        user.setOrganization(new GPOrganization("ORGANIZATION_TEST"));
        user.setName("USER_TEST" + i);
        return user;
    }

    private GPApplication createGPApplication(int i) {
        int f = ++i * 20;
        GPApplication application = new GPApplication();
        application.setId(new Long(f));
        application.setOrganization(new GPOrganization("ORGANIZATION_"
                + "APPLICATION_TEST"));
        application.setAppID("APPLICATION_TEST" + f);

        return application;
    }

    class CallableEntity implements Callable<ShortAccountDTO> {

        private final GPAccount account;

        public CallableEntity(GPAccount account) {
            this.account = account;
        }

        @Override
        public ShortAccountDTO call() throws Exception {
            return AccountDTOFactory.buildAccountDTO(account);
        }
    }

}
