package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base;

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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPElasticSearchRSBaseConfigTest.class}, loader = AnnotationConfigContextLoader.class)
public class GPElasticSearchRSBaseConfigurationTest {

    private static final Logger logger = LoggerFactory.getLogger(GPElasticSearchRSBaseConfigurationTest.class);
    //
    @Resource(name = "elasticSearchRSBaseConfiguration")
    private GPElasticSearchRSBaseConfiguration elasticSearchRSBaseConfiguration;

    @BeforeClass
    public static void beforeClass() throws Exception {
        setProperty("GP_ELASTICSEARCH_REST_DATA_DIR", of(new File(".")
                .getCanonicalPath(), "src", "test", "resources", "base").collect(joining(separator, "", separator)));
        setProperty("GP_ELASTICSEARCH_REST_FILE_PROP", "gp.elastic-search-rest-base.properties");
        setProperty("GP_ELASTICSEARCH_REST_PBE_KEY", "GPElasticSearchRSBase??197788");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        clearProperty("GP_ELASTICSEARCH_REST_DATA_DIR");
        clearProperty("GP_ELASTICSEARCH_REST_FILE_PROP");
        clearProperty("GP_ELASTICSEARCH_REST_PBE_KEY");
    }

    @Before
    public void setUp() throws Exception {
        assertNotNull(this.elasticSearchRSBaseConfiguration);
        assertNotNull(this.elasticSearchRSBaseConfiguration.getAuthConfig());
        assertNotNull(this.elasticSearchRSBaseConfiguration.getSslConfig());
    }

    @Test
    public void printGPElasticSearchRestBaseConfigurationTest() throws Exception {
        assertTrue(this.elasticSearchRSBaseConfiguration.getAuthConfig().isSetAuth());
        assertTrue(this.elasticSearchRSBaseConfiguration.getSslConfig().isSetSecureSocketLayer());
        logger.info("###########################GP_ELASTICSEARCH_REST_BASE_CONFIGURATION : {}\n", this.elasticSearchRSBaseConfiguration);
    }
}