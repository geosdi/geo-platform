package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.request.model.about.version.GPGeoserverAboutVersion;
import org.geosdi.geoplatform.connector.geoserver.request.model.workspace.GPGeoserverEmptyWorkspaces;
import org.geosdi.geoplatform.connector.geoserver.request.model.workspace.GPGeoserverWorkspaces;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverJacksonTest.class);
    //
    private static final JacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_ENABLE,
            INDENT_OUTPUT_ENABLE);
    private static final JacksonSupport emptyJacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE);

    @Test
    public void a_unmarshallGeoserverAboutVersionTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_ABOUT_VERSION : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"about\":{\"resource\":[{\"@name\":\"GeoServer\",\"Build-Timestamp\"" +
                        ":\"21-Nov-2017 22:02\",\"Version\":\"2.12.1\",\"Git-Revision\":" +
                        "\"5927e49e781ddcdbf9213d32a439418347c17480\"},{\"@name\":\"GeoTools\",\"Build-Timestamp\":" +
                        "\"21-Nov-2017 14:18\",\"Version\":18.1,\"Git-Revision\":" +
                        "\"306cf3bdde1bee0110dc1c3ba77819f1e294a45b\"},{\"@name\":\"GeoWebCache\",\"Version\":\"1.12.1\"," +
                        "\"Git-Revision\":\"1.12.x\\/22d18b47c9e80316d563c28d280602cb3dde624c\"}]}}\n"), GPGeoserverAboutVersion.class));
    }

    @Test
    public void b_unmarshallGeoserverWorkspacesTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WOKSPACES : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"workspaces\":{  \n" +
                        "      \"workspace\":[  \n" +
                        "         {  \n" +
                        "            \"name\":\"cite\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/cite.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"tiger\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/tiger.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"nurc\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/nurc.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sde\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/sde.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"it.geosolutions\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/it.geosolutions.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"topp\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/topp.json\"\n" +
                        "         },\n" +
                        "         {  \n" +
                        "            \"name\":\"sf\",\n" +
                        "            \"href\":\"http:\\/\\/150.145.141.92\\/geoserver\\/rest\\/workspaces\\/sf.json\"\n" +
                        "         }\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverWorkspaces.class));
    }

    @Test
    public void c_unmarshallGeoserverEmptyWorkspacesTest() throws Exception {
        logger.info("\n{}\n", emptyJacksonSupport.getDefaultMapper().readValue(new StringReader("{  \n" +
                "   \"workspaces\":\"\"\n" +
                "}"), GPGeoserverEmptyWorkspaces.class));
    }
}