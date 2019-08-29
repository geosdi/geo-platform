package org.geosdi.geoplatform.connector.store.settings;

import org.geosdi.geoplatform.connector.geoserver.model.settings.GPGeoserverGlobalSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.IGPGeoserverSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.contact.IGPGeoserverContactSettings;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadContactSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GPGeoserverLoadGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.geoserver.request.settings.GeoserverUpdateGlobalSettingsRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverSettingsConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverGlobalSettingsRequestTest() throws Exception {
        GPGeoserverLoadGlobalSettingsRequest loadGlobalSettingsRequest = geoserverConnectorStore.loadGeoserverGlobalSettingRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_GLOBAL_SETTINGS_RESPONSE : {}\n", loadGlobalSettingsRequest.getResponse());
    }

    @Test
    public void b_updateGeoserverGlobalSettingsRequestTest() throws Exception {
        GPGeoserverGlobalSettings geoserverGlobalSettings = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GeoserverGlobalSettings.json")
                        .collect(joining(separator, "", separator))), GPGeoserverGlobalSettings.class);
        GeoserverUpdateGlobalSettingsRequest updateGlobalSettingsRequest = geoserverConnectorStore.updateGlobalSettingsRequest();
        updateGlobalSettingsRequest.withSettingsBody(geoserverGlobalSettings);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_GLOBAL_SETTINGS_RESPONSE : {}", updateGlobalSettingsRequest.getResponse());
    }

    @Test
    public void c_loadGeoserverGlobalSettingsRequestTest() throws Exception {
        GPGeoserverLoadGlobalSettingsRequest loadGlobalSettingsRequest = geoserverConnectorStore.loadGeoserverGlobalSettingRequest();
        GPGeoserverGlobalSettings geoserverGlobalSettings = loadGlobalSettingsRequest.getResponse();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_GLOBAL_SETTINGS_RESPONSE : {}\n", geoserverGlobalSettings);
        IGPGeoserverSettings geoserverSettings = geoserverGlobalSettings.getSettings();
        IGPGeoserverContactSettings geoserverContactSettings = geoserverSettings.getContact();
        assertTrue(geoserverContactSettings.getContactOrganization().equalsIgnoreCase("geoSDI"));
        assertTrue(geoserverContactSettings.getAddressCity().equalsIgnoreCase("Potenza"));
        assertTrue(geoserverContactSettings.getAddressCountry().equalsIgnoreCase("Italy"));
        assertTrue(geoserverContactSettings.getContactEmail().equalsIgnoreCase("giuseppe.lascaleia@geosdi.org"));
    }

    @Test
    public void d_loadGeoserverContactSettingsRequestTest() throws Exception {
        GPGeoserverLoadContactSettingsRequest contactSettingsRequest = geoserverConnectorStore.loadGeoserverContactSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_CONTACT_SETTINGS_RESPONSE : {}\n", contactSettingsRequest.getResponse());
    }
}