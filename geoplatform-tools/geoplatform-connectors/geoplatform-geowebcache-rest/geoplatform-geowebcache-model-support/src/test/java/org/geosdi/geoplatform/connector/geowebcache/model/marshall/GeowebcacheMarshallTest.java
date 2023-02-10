package org.geosdi.geoplatform.connector.geowebcache.model.marshall;

import org.geosdi.geoplatform.connector.geowebcache.model.seed.GPGeowebcacheResponse;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAXB;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertFalse;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeowebcacheMarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(GeowebcacheMarshallTest.class);
    //
    public static final GPJacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(JAXB, UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, FAIL_ON_EMPTY_BEANS_DISABLE).configure(NON_NULL);
//    private static final GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();

    @Test
    public void a_marshallGeowebcacheSeedTaskStatusTest() throws Exception {
        GPGeowebcacheResponse seedTaskStatus = GPGeowebcacheResponse.of(of(of(123l, 234l, 4l, 78l, 890l)
                .collect(toList()), of(12l, 23l, 41l, 781l, 8901l).
                collect(toList()))
                .collect(toList()));
        assertFalse(seedTaskStatus.isTerminated());
        logger.info("###################GEOWEBCACHE_SEED_TASK_STATUS_AS_STRING : \n{}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(seedTaskStatus));
    }

    @Test
    public void b_unmarshallGeowebcacheSeedTaskStatusTest() throws Exception {
        GPGeowebcacheResponse seedTaskStatus = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"long-array-array\" : [ [ 123, 234, 4, 78, 890 ], [ 12, 23, 41, 781, 8901 ] ]\n"
                        + "}"), GPGeowebcacheResponse.class);
        assertFalse(seedTaskStatus.isTerminated());
        logger.info("#########################GEOWEBCACHE_SEED_TASK_STATUS_FROM_STRING : {}\n", seedTaskStatus);
    }

    @Test
    public void c_unmarshallGeowebcacheSeedTaskStatusTest() throws Exception {
        GPGeowebcacheResponse seedTaskStatus = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\"long-array-array\":[[126,252,19,851,2],[108,252,3,852,2]," +
                        "[117,252,2,857,2],[117,252,33,858,2]]}"), GPGeowebcacheResponse.class);
        logger.info("{}\n", seedTaskStatus.getStatusTaskValues().size());
    }
}