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
package org.geosdi.geoplatform.experimental.mongodb.validate;

import javax.validation.ConstraintViolationException;
import org.geosdi.geoplatform.experimental.mongodb.loader.GPMongoConfigLoader;
import org.geosdi.geoplatform.experimental.mongodb.model.Address;
import org.geosdi.geoplatform.experimental.mongodb.repositories.AddressRepository;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPMongoConfigLoader.class},
        loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(value = {"mongo_validate"})
public class GPMongoValidateTest {

    @GeoPlatformLog
    static Logger logger;
    //
    static final String GP_MONGO_KEY = "GP_MONGO_FILE_PROP";
    //
    @Autowired
    AddressRepository addressRepo;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty(GP_MONGO_KEY, "gp-mongo-test.prop");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty(GP_MONGO_KEY);
    }

    @Before
    public void setUp() {
        Assert.assertNotNull("Address Repo must not be NULL",
                addressRepo);
    }

    @Test
    public void nameIsNull() {
        Address a = new Address(null, 34, 28);

        try {
            addressRepo.save(a);
        } catch (ConstraintViolationException cve) {
            assertEquals(1, cve.getConstraintViolations().size());
        }
    }

    @Test
    public void nameIsTooShort() {
        Address a = new Address("abc", 45, 37);

        try {
            addressRepo.save(a);
        } catch (ConstraintViolationException cve) {
            assertEquals(1, cve.getConstraintViolations().size());
            assertEquals("The Field Name must contains almost 4 characters.",
                    cve.getConstraintViolations().iterator().next().getMessage()
            );
        }
    }

}
