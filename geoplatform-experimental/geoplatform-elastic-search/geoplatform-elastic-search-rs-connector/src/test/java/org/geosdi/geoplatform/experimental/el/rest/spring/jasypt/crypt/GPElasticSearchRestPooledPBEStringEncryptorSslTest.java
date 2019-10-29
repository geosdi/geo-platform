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
public class GPElasticSearchRestPooledPBEStringEncryptorSslTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Resource(name = "elasticSearchRestPooledPBEStringEncryptor")
    private PooledPBEStringEncryptor elasticSearchRestPooledPBEStringEncryptor;

    @BeforeClass
    public static void beforeClass() throws Exception {
        setProperty("GP_ELASTICSEARCH_REST_PBE_KEY", "7756!88??2022?poi");
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
    public void a_encryptGPElasticSearchRestSslKeyStorePathTest() throws Exception {
        String keyStorePath = "test.jks";
        String encryptedKeyStorePassword = this.elasticSearchRestPooledPBEStringEncryptor.encrypt(keyStorePath);
        assertTrue("GPElasticSearchRestSsl / Encrypted KeyStorePath doesn't match", this.elasticSearchRestPooledPBEStringEncryptor
                .decrypt(encryptedKeyStorePassword).equals(keyStorePath));
        logger.info("@@@@@@@@@@@@@@GP_ELASTICSEARCH_REST_ENCRYPTED_KEYSTORE_PATH : {}\n\n", encryptedKeyStorePassword);
    }

    @Test
    public void b_encryptGPElasticSearchRestSslKeyStorePasswordTest() throws Exception {
        String keyStorePassword = "keyStorePasswordTest";
        String encryptedKeyStorePassword = this.elasticSearchRestPooledPBEStringEncryptor.encrypt(keyStorePassword);
        assertTrue("GPElasticSearchRestSsl / Encrypted KeyStorePassword doesn't match", this.elasticSearchRestPooledPBEStringEncryptor
                .decrypt(encryptedKeyStorePassword).equals(keyStorePassword));
        logger.info("@@@@@@@@@@@@@@GP_ELASTICSEARCH_REST_ENCRYPTED_KEYSTORE_PASSWORD : {}\n\n", encryptedKeyStorePassword);
    }
}