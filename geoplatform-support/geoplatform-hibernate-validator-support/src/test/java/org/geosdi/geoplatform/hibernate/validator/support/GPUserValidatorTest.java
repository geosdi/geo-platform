package org.geosdi.geoplatform.hibernate.validator.support;

import org.geosdi.geoplatform.hibernate.validator.GPUserValidator;
import org.geosdi.geoplatform.hibernate.validator.model.GPUser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static GPUser createGPUser() {
        return new GPUser() {

            {
                super.setUserName("Giuseppe La Scaleia");
                super.setPassword("0x,giuseppe,0x");
            }
        };
    }
}
