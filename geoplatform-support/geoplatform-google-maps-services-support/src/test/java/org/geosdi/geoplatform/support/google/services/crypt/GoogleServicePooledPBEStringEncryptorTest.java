package org.geosdi.geoplatform.support.google.services.crypt;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Crypt-Test.xml"})
public class GoogleServicePooledPBEStringEncryptorTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "googleServicePooledPBEStringEncryptor")
    private PooledPBEStringEncryptor googleServicePooledPBEStringEncryptor;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.googleServicePooledPBEStringEncryptor);
    }

    @Test
    public void encryptedGoogleServiceKeyTest() throws Exception {
        String value = "This is a Test.";
        String encryptedValue = this.googleServicePooledPBEStringEncryptor.encrypt(value);
        assertTrue("Google Service Value / Encrypted Name doesn't match", this.googleServicePooledPBEStringEncryptor.decrypt(encryptedValue).equals(value));
        logger.info("@@@@@@@@@@@@@@GOOGLE_SERVICE_ENCRYPTED_VALUE : {}\n\n", encryptedValue);
    }
}