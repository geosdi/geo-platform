/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store.settings.services.wcs;

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wcs.GPGeoserverWCSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wcs.GPGeoserverWCSWorkspaceServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.request.settings.services.wcs.*;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.lang.Thread.currentThread;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverWCSServiceSettingsJacksonTest.toWCSServiceSettings;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverWCSServiceSettingsConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadWCSServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWCSServiceSettingsRequest wcsServiceSettingsRequest = geoserverConnectorStoreV2_24_x.loadWCSServiceSettingsRequest();
        logger.info("########################GEOSERVER_WCS_SERIVCE_SETTINGS_RESPONSE : {}\n", wcsServiceSettingsRequest.getResponse());
    }

    @Test
    public void b_loadWCSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWCSWorkspaceServiceSettingsRequest wcsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_24_x.loadWCSWorkspaceServiceSettingsRequest();
        logger.info("########################GEOSERVER_WCS_WORKSPACE_SERIVCE_SETTINGS_RESPONSE : {}\n", wcsWorkspaceServiceSettingsRequest
                .withWorkspace("topp").getResponse());
    }

    @Test
    public void c_updateWCSServiceSettingsRequestTest() throws Exception {
        GeoserverUpdateWCSServiceSettingsRequest updateWCSServiceSettingsRequest = geoserverConnectorStoreV2_24_x.updateWCSServiceSettingsRequest();
        GPGeoserverWCSServiceSettings wcsServiceSettings = toWCSServiceSettings();
        wcsServiceSettings.setMaintainer("Francesco Izzi");
        wcsServiceSettings.setAbstrct("This is a simple example for Geoserver");
        assertTrue(updateWCSServiceSettingsRequest.withBody(wcsServiceSettings).getResponse());
        GPGeoserverWCSServiceSettings retrieveWCSSettings = geoserverConnectorStoreV2_24_x.loadWCSServiceSettingsRequest().getResponse();
        assertTrue(retrieveWCSSettings.getMaintainer().equals("Francesco Izzi"));
        assertTrue(retrieveWCSSettings.getAbstrct().equals("This is a simple example for Geoserver"));
    }

    @Test
    public void d_updateWCSServiceSettingsRequestTest() throws Exception {
        GPGeoserverWCSServiceSettings wcsServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WCSServiceSettings-2.19")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverWCSServiceSettings.class);
        logger.info("###################WCS_UPDATE_SERVICE_SETTINGS_RESPONSE : {}\n", geoserverConnectorStoreV2_24_x.updateWCSServiceSettingsRequest()
                .withBody(wcsServiceSettings).getResponse());
    }

    @Test
    public void e_updateWCSWorkspaceServiceSettingsRequestTest() throws Exception {
        try (InputStream wcsWorkspaceServiceSettingsStream = currentThread().getContextClassLoader().getResourceAsStream("WCSWorkspaceServiceSettings.xml")) {
            checkArgument(wcsWorkspaceServiceSettingsStream != null, "The Parameter wcsWorkspaceServiceSettingsStream must not be null.");
            GPGeoserverWCSWorkspaceServiceSettings wcsWorkspaceServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                    .readValue(new InputStreamReader(wcsWorkspaceServiceSettingsStream), GPGeoserverWCSWorkspaceServiceSettings.class);
            wcsWorkspaceServiceSettings.getWorkspace().setWorkspaceName("nurc");
            wcsWorkspaceServiceSettings.setMaintainer("Giuseppe La Scaleia");
            wcsWorkspaceServiceSettings.setAbstrct("This is a simple Test.");
            GeoserverUpdateWCSWorkspaceServiceSettingsRequest updateWCSWorkspaceSettingsRequest = geoserverConnectorStoreV2_24_x.updateWCSWorkspaceServiceSettingsRequest();
            logger.info("@@@@@@@@@@@@@@@@@WCS_UPDATE_WORKSPACE_SERVICE_SETTINGS_RESPONSE : {}\n", updateWCSWorkspaceSettingsRequest
                    .withWorkspace("nurc").withBody(wcsWorkspaceServiceSettings).getResponse());
        }
    }

    @Test
    public void f_loadWCSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWCSWorkspaceServiceSettingsRequest wcsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_24_x.loadWCSWorkspaceServiceSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WCS_WORKSPACE_SETTINGS_RESPONSE : {}\n", wcsWorkspaceServiceSettingsRequest
                .withWorkspace("nurc").getResponse());
    }

    @Test
    public void g_deleteWCSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverDeleteWCSWorkspaceServiceSettingsRequest deleteWCSWorkspaceServiceSettingRequest = geoserverConnectorStoreV2_24_x.deleteWCSWorkspaceServiceSettingsRequest();
        logger.info("########################WCS_DELETE_WORKSPACE_SERVICE_SETTINGS : {}\n", deleteWCSWorkspaceServiceSettingRequest
                .withWorkspace("nurc").getResponse());
    }
}