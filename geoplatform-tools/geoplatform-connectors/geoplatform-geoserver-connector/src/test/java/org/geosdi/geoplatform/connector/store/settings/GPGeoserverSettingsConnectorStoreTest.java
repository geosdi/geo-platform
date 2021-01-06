/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
        GPGeoserverLoadGlobalSettingsRequest loadGlobalSettingsRequest = geoserverConnectorStoreV2_18_x.loadGeoserverGlobalSettingRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_GLOBAL_SETTINGS_RESPONSE : {}\n", loadGlobalSettingsRequest.getResponse());
    }

    @Test
    public void b_updateGeoserverGlobalSettingsRequestTest() throws Exception {
        GPGeoserverGlobalSettings geoserverGlobalSettings = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "GeoserverGlobalSettings.json")
                        .collect(joining(separator, "", separator))), GPGeoserverGlobalSettings.class);
        GeoserverUpdateGlobalSettingsRequest updateGlobalSettingsRequest = geoserverConnectorStoreV2_18_x.updateGlobalSettingsRequest();
        updateGlobalSettingsRequest.withSettingsBody(geoserverGlobalSettings);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_GLOBAL_SETTINGS_RESPONSE : {}", updateGlobalSettingsRequest.getResponse());
    }

    @Test
    public void c_loadGeoserverGlobalSettingsRequestTest() throws Exception {
        GPGeoserverLoadGlobalSettingsRequest loadGlobalSettingsRequest = geoserverConnectorStoreV2_18_x.loadGeoserverGlobalSettingRequest();
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
        GPGeoserverLoadContactSettingsRequest contactSettingsRequest = geoserverConnectorStoreV2_18_x.loadGeoserverContactSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_CONTACT_SETTINGS_RESPONSE : {}\n", contactSettingsRequest.getResponse());
    }
}