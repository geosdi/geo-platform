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
package org.geosdi.geoplatform.hibernate.validator.support;

import org.geosdi.geoplatform.hibernate.validator.GPUserValidator;
import org.geosdi.geoplatform.hibernate.validator.model.GPUser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPUserValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(GPUserValidatorTest.class);
    //
    private static GPUserValidator gpUserValidator;

    @BeforeClass
    public static void beforeClass() {
        gpUserValidator = new GPUserValidator();
    }

    @Test
    public void userNameIsNull() {
        GPUser gpUser = createGPUser();
        gpUser.setUserName(null);

        String enMessage = gpUserValidator.validate(gpUser, null);
        Assert.assertNotNull(enMessage);
        logger.info("#########################USER_NAME_IS_NULL_MESSAGE_EN : {}\n", enMessage);

        String itMessage = gpUserValidator.validate(gpUser, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################USER_NAME_IS_NULL_MESSAGE_IT : {}\n", itMessage);
    }

    @Test
    public void userNameIsEmpty() {
        GPUser gpUser = createGPUser();
        gpUser.setUserName("");

        String enMessage = gpUserValidator.validate(gpUser, null);
        Assert.assertNotNull(enMessage);
        logger.info("#########################USER_NAME_IS_EMPTY_MESSAGE_EN : {}\n", enMessage);

        String itMessage = gpUserValidator.validate(gpUser, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################USER_NAME_IS_EMPTY_MESSAGE_IT : {}\n", itMessage);
    }

    @Test
    public void passwordIsNull() {
        GPUser gpUser = createGPUser();
        gpUser.setPassword(null);

        String enMessage = gpUserValidator.validate(gpUser, null);
        Assert.assertNotNull(enMessage);
        logger.info("#########################PASSWORD_IS_NULL_MESSAGE_EN : {}\n", enMessage);

        String itMessage = gpUserValidator.validate(gpUser, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################PASSWORD_IS_NULL_MESSAGE_IT : {}\n", itMessage);
    }

    @Test
    public void passwordIsEmpty() {
        GPUser gpUser = createGPUser();
        gpUser.setPassword("");

        String enMessage = gpUserValidator.validate(gpUser, null);
        Assert.assertNotNull(enMessage);
        logger.info("#########################PASSWORD_IS_EMPTY_MESSAGE_EN : {}\n", enMessage);

        String itMessage = gpUserValidator.validate(gpUser, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################PASSWORD_IS_EMPTY_MESSAGE_IT : {}\n", itMessage);
    }

    @Test
    public void registrationDateIsNull() {
        GPUser gpUser = createGPUser();
        gpUser.setRegistrationDate(null);

        String enMessage = gpUserValidator.validate(gpUser, null);
        Assert.assertNotNull(enMessage);
        logger.info("#########################REGISTRATION_DATE_IS_NULL_MESSAGE_EN : {}\n", enMessage);

        String itMessage = gpUserValidator.validate(gpUser, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################REGISTRATION_DATE_IS_NULL_MESSAGE_IT : {}\n", itMessage);
    }

    @Test
    public void registrationDateIsIncorrect() {
        GPUser gpUser = createGPUser();
        gpUser.setRegistrationDate(LocalDate.of(2021, 9, 2));

        String enMessage = gpUserValidator.validate(gpUser, null);
        Assert.assertNotNull(enMessage);
        logger.info("#########################REGISTRATION_DATE_IS_NULL_MESSAGE_EN : {}\n", enMessage);

        String itMessage = gpUserValidator.validate(gpUser, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################REGISTRATION_DATE_IS_NULL_MESSAGE_IT : {}\n", itMessage);
    }

    public static GPUser createGPUser() {
        return new GPUser() {

            {
                super.setUserName("Giuseppe La Scaleia");
                super.setPassword("0x,giuseppe,0x");
                super.setRegistrationDate(LocalDate.of(2017, 8, 8));
            }
        };
    }
}
