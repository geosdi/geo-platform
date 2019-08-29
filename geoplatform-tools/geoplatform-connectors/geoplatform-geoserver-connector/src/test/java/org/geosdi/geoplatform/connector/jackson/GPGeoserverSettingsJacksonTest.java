package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.settings.GPGeoserverGlobalSettings;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverSettingsJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverSettingsJacksonTest.class);

    @Test
    public void a_unmarshallGPGeoserverGlobalSettingsTest() throws Exception {
        GPGeoserverGlobalSettings geoserverGlobalSettings = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"global\":{  \n" +
                        "      \"settings\":{  \n" +
                        "         \"id\":\"SettingsInfoImpl-68f6c583:154ca420c47:-8000\",\n" +
                        "         \"contact\":{  \n" +
                        "            \"addressCity\":\"Alexandria\",\n" +
                        "            \"addressCountry\":\"Egypt\",\n" +
                        "            \"addressType\":\"Work\",\n" +
                        "            \"contactEmail\":\"claudius.ptolomaeus@gmail.com\",\n" +
                        "            \"contactOrganization\":\"The Ancient Geographers\",\n" +
                        "            \"contactPerson\":\"Claudius Ptolomaeus\",\n" +
                        "            \"contactPosition\":\"Chief Geographer\"\n" +
                        "         },\n" +
                        "         \"charset\":\"UTF-8\",\n" +
                        "         \"numDecimals\":8,\n" +
                        "         \"onlineResource\":\"http://geoserver.org\",\n" +
                        "         \"verbose\":false,\n" +
                        "         \"verboseExceptions\":false,\n" +
                        "         \"localWorkspaceIncludesPrefix\":false\n" +
                        "      },\n" +
                        "      \"jai\":{  \n" +
                        "         \"allowInterpolation\":false,\n" +
                        "         \"recycling\":false,\n" +
                        "         \"tilePriority\":5,\n" +
                        "         \"tileThreads\":7,\n" +
                        "         \"memoryCapacity\":0.5,\n" +
                        "         \"memoryThreshold\":0.75,\n" +
                        "         \"imageIOCache\":false,\n" +
                        "         \"pngAcceleration\":true,\n" +
                        "         \"jpegAcceleration\":true,\n" +
                        "         \"allowNativeMosaic\":false,\n" +
                        "         \"allowNativeWarp\":false\n" +
                        "      },\n" +
                        "      \"coverageAccess\":{  \n" +
                        "         \"maxPoolSize\":10,\n" +
                        "         \"corePoolSize\":5,\n" +
                        "         \"keepAliveTime\":30000,\n" +
                        "         \"queueType\":\"UNBOUNDED\",\n" +
                        "         \"imageIOCacheThreshold\":10240\n" +
                        "      },\n" +
                        "      \"updateSequence\":180,\n" +
                        "      \"featureTypeCacheSize\":0,\n" +
                        "      \"globalServices\":true,\n" +
                        "      \"xmlPostRequestLogBufferSize\":1024\n" +
                        "   }\n" +
                        "}"), GPGeoserverGlobalSettings.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_GLOBAL_SETTINGS : {}\n", geoserverGlobalSettings);
    }

    @Test
    public void b_unmarshallGeoserverGlobalSettingsFromFileTest() throws Exception {
        GPGeoserverGlobalSettings geoserverGlobalSettings = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GeoserverGlobalSettings.json")
                        .collect(joining(separator, "", separator))), GPGeoserverGlobalSettings.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_GLOBAL_SETTINGS : {}\n", geoserverGlobalSettings);
    }
}