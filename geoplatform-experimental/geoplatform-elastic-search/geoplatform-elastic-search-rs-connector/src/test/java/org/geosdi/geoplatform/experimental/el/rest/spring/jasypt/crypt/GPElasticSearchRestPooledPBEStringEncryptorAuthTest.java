package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.crypt;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static java.lang.System.clearProperty;
import static java.lang.System.setProperty;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Crypt-Test.xml"})
@FixMethodOrder(NAME_ASCENDING)
public class GPElasticSearchRestPooledPBEStringEncryptorAuthTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "elasticSearchRestPooledPBEStringEncryptor")
    private PooledPBEStringEncryptor elasticSearchRestPooledPBEStringEncryptor;

    @BeforeClass
    public static void beforeClass() throws Exception {
        setProperty("GP_ELASTICSEARCH_REST_PBE_KEY", "1234/??197788");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        clearProperty("GP_ELASTICSEARCH_REST_PBE_KEY");
    }

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.elasticSearchRestPooledPBEStringEncryptor);
    }

    @Test
    public void a_encryptGPElasticSearchRestAuthUserNameTest() throws Exception {
        String userName = "testUser";
        String encryptedUserName = this.elasticSearchRestPooledPBEStringEncryptor.encrypt(userName);
        assertTrue("GPElasticSearchRestAuth / Encrypted UserName doesn't match", this.elasticSearchRestPooledPBEStringEncryptor
                .decrypt(encryptedUserName).equals(userName));
        logger.info("@@@@@@@@@@@@@@GP_ELASTICSEARCH_REST_ENCRYPTED_USERNAME : {}\n\n", encryptedUserName);
    }

    @Test
    public void b_encryptGPElasticSearchRestAuthPasswordTest() throws Exception {
        String password = "0x,peppino,0x?1??";
        String encryptedPassword = this.elasticSearchRestPooledPBEStringEncryptor.encrypt(password);
        assertTrue("GPElasticSearchRestAuth / Encrypted Password doesn't match", this.elasticSearchRestPooledPBEStringEncryptor
                .decrypt(encryptedPassword).equals(password));
        logger.info("@@@@@@@@@@@@@@GP_ELASTICSEARCH_REST_ENCRYPTED_PASSWORD : {}\n\n", encryptedPassword);
    }
}