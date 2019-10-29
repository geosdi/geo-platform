package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.io.File;

import static java.io.File.separator;
import static java.lang.System.clearProperty;
import static java.lang.System.setProperty;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPElasticSearchRestAuthConfigTest.class}, loader = AnnotationConfigContextLoader.class)
public class GPElasticSearchRSAuthConfigurationTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRSAuthConfigurationTest.class);
    //
    @Resource(name = "elasticSearchRestAuthConfiguration")
    private GPElasticSearchRSAuthConfiguration elasticSearchRestAuthConfiguration;

    @BeforeClass
    public static void beforeClass() throws Exception {
        setProperty("GP_ELASTICSEARCH_REST_DATA_DIR", of(new File(".")
                .getCanonicalPath(), "src", "test", "resources", "auth").collect(joining(separator, "", separator)));
        setProperty("GP_ELASTICSEARCH_REST_FILE_PROP", "gp.elastic-search-rest-auth.properties");
        setProperty("GP_ELASTICSEARCH_REST_PBE_KEY", "1234/??197788");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        clearProperty("GP_ELASTICSEARCH_REST_DATA_DIR");
        clearProperty("GP_ELASTICSEARCH_REST_FILE_PROP");
        clearProperty("GP_ELASTICSEARCH_REST_PBE_KEY");
    }

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.elasticSearchRestAuthConfiguration);
    }

    @Test
    public void printGPElasticSearchRestAuthConfigurationTest() throws Exception {
        assertTrue(this.elasticSearchRestAuthConfiguration.getUserName().equalsIgnoreCase("testUser"));
        assertTrue(this.elasticSearchRestAuthConfiguration.getPassword().equalsIgnoreCase("0x,peppino,0x?1??"));
        logger.info("###########################GP_ELASTICSEARCH_REST_AUTH_CONFIGURATION : {}\n", this.elasticSearchRestAuthConfiguration);
    }
}