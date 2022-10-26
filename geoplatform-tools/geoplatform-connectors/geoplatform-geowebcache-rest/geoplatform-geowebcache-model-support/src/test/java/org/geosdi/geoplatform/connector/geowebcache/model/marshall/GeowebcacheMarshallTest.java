package org.geosdi.geoplatform.connector.geowebcache.model.marshall;

import org.geosdi.geoplatform.connector.geowebcache.model.entry.GeowebcacheParameterEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.GeowebcacheSeedRequestValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.IGeowebcacheSeedRequestValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.request.GeowebcacheSeedRequest;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.request.WideGeowebcacheSeedRequest;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.status.IGeowebcacheSeedTaskStatus;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.GeowebcacheSrsBean;
import org.geosdi.geoplatform.connector.geowebcache.model.srs.IGeowebcacheSrsBean;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geowebcache.model.seed.operation.GeowebcacheSeedOperationType.RESEED;
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
    public static final GPJacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, FAIL_ON_EMPTY_BEANS_DISABLE)
            .configure(NON_NULL);
//    private static final GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();

    @Test
    public void a_marshallGeowebcacheSeedRequestTest() throws Exception {
        WideGeowebcacheSeedRequest seedRequest = new GeowebcacheSeedRequest();
        IGeowebcacheSeedRequestValue seedRequestValue = new GeowebcacheSeedRequestValue();
        seedRequestValue.setName("name:test");
        IGeowebcacheSrsBean srsBean = new GeowebcacheSrsBean();
        srsBean.setNumber(900913);
        seedRequestValue.setSrs(srsBean);
        seedRequestValue.setZoomStart(6);
        seedRequestValue.setZoomStop(8);
        seedRequestValue.setFormat("image/png");
        seedRequestValue.setType(RESEED);
        GeowebcacheParameterEntry parameterEntry = new GeowebcacheParameterEntry();
        parameterEntry.addValue("TIME");
        parameterEntry.addValue("2018-09-18T04:05:00.000Z");
        GeowebcacheParameterEntry parameterEntry1 = new GeowebcacheParameterEntry();
        parameterEntry1.addValue("VALUE");
        parameterEntry1.addValue("TEST_VALUE");
        seedRequestValue.addParameter(parameterEntry, parameterEntry1);
        seedRequest.setSeedRequestValue(seedRequestValue);
        logger.info("##########################GEOWEBCACHE_SEED_MARSHAL_REQUEST_AS_JSON : \n{}\n",
                JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(seedRequest));
//        StringWriter writer = new StringWriter();
//        jaxbContextBuilder.marshal(seedRequest, writer);
//        logger.info("##########################GEOWEBCACHE_SEED_MARSHAL_REQUEST_AS_XML : {}\n", writer.toString());
    }

    @Test
    public void b_unmarshallGeowebcacheSeedRequestTest() throws Exception {
        logger.info("################################GEOWEBCACHE_SEED_UNMARSHALL_REQUEST : {}\n",
                JACKSON_SUPPORT.getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"seedRequest\": {\n" +
                        "    \"name\": \"topp:states\",\n" +
                        "    \"bounds\": {\n" +
                        "      \"coords\": {\n" +
                        "        \"double\": [\n" +
                        "          \"-2495667.977678598\",\n" +
                        "          \"-2223677.196231552\",\n" +
                        "          \"3291070.6104286816\",\n" +
                        "          \"959189.3312465074\"\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"gridSetId\": \"EPSG:2163\",\n" +
                        "    \"zoomStart\": \"0\",\n" +
                        "    \"zoomStop\": \"2\",\n" +
                        "    \"format\": \"image/png\",\n" +
                        "    \"type\": \"truncate\",\n" +
                        "    \"threadCount\": \"1\",\n" +
                        "    \"parameters\": {\n" +
                        "      \"entry\": [\n" +
                        "        {\n" +
                        "          \"string\": [\n" +
                        "            \"STYLES\",\n" +
                        "            \"pophatch\"\n" +
                        "          ]\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"string\": [\n" +
                        "            \"CQL_FILTER\",\n" +
                        "            \"TOTPOP > 10000\"\n" +
                        "          ]\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"), WideGeowebcacheSeedRequest.class));
    }

    @Test
    public void c_marshallGeowebcacheSeedTaskStatusTest() throws Exception {
        IGeowebcacheSeedTaskStatus seedTaskStatus = IGeowebcacheSeedTaskStatus.of(of(of(123l, 234l, 4l, 78l, 890l)
                .collect(toList()), of(12l, 23l, 41l, 781l, 8901l).
                collect(toList()))
                .collect(toList()));
        assertFalse(seedTaskStatus.isTerminated());
        logger.info("###################GEOWEBCACHE_SEED_TASK_STATUS_AS_STRING : \n{}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(seedTaskStatus));
    }

    @Test
    public void d_unmarshallGeowebcacheSeedTaskStatusTest() throws Exception {
        IGeowebcacheSeedTaskStatus seedTaskStatus = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"long-array-array\" : [ [ 123, 234, 4, 78, 890 ], [ 12, 23, 41, 781, 8901 ] ]\n"
                        + "}"), IGeowebcacheSeedTaskStatus.class);
        assertFalse(seedTaskStatus.isTerminated());
        logger.info("#########################GEOWEBCACHE_SEED_TASK_STATUS_FROM_STRING : {}\n", seedTaskStatus);
    }

    @Test
    public void e_unmarshallGeowebcacheSeedTaskStatusTest() throws Exception {
        IGeowebcacheSeedTaskStatus seedTaskStatus = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\"long-array-array\":[[126,252,19,851,2],[108,252,3,852,2]," +
                        "[117,252,2,857,2],[117,252,33,858,2]]}"), IGeowebcacheSeedTaskStatus.class);
        logger.info("{}\n", seedTaskStatus.getStatusTaskValues().size());
    }
}