package org.geosdi.geoplatform.cxf.rs.support.geocoding;

import org.geosdi.geoplatform.cxf.rs.support.geocoding.services.api.IGPCXFGeocodingRestService;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static java.util.Locale.ITALY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Geocoding-Test.xml"})
public class GPCXFGeocodingRestServiceTest {

    @GeoPlatformLog
    private static Logger logger;
    //
    private static final String GP_GOOGLE_SERVICES_FILE_KEY = "GP_GOOGLE_SERVICES_FILE_PROP";
    //
    @Resource(name = "gpCXFGeocodingRestService")
    private IGPCXFGeocodingRestService gpCXFGeocodingRestService;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty(GP_GOOGLE_SERVICES_FILE_KEY, "gp-googleservices-test.prop");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty(GP_GOOGLE_SERVICES_FILE_KEY);
    }

    @Before
    public void setUp() {
        Assert.assertNotNull(gpCXFGeocodingRestService);
    }

    @Test
    public void searchAddressTest() throws Exception {
        logger.info("################################FOUND : {}\n", this.gpCXFGeocodingRestService
                .searchAddress("via provinciale 169 Marsicovetere", ITALY.toLanguageTag()).getEntity());
    }
}
