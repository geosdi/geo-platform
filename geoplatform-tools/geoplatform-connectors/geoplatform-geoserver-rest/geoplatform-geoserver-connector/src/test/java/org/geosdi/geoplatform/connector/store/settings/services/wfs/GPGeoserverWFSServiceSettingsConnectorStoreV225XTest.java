/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.store.settings.services.wfs;

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.GPGeoserverWFSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.GPGeoserverWFSWorkspaceServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wfs.GeoserverWFSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.request.settings.services.wfs.*;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV225xTest;
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
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverWFSServiceSettingsJacksonTest.toWFSServiceSettings;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverWFSServiceSettingsConnectorStoreV225XTest extends GPBaseGeoserverConnectorStoreV225xTest {

    @Test
    public void a_loadWFSServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWFSServiceSettingsRequest wfsServiceSettingsRequest = geoserverConnectorStoreV2_25_x.loadWFSServiceSettingsRequest();
        logger.info("########################GEOSERVER_WFS_SERIVCE_SETTINGS_RESPONSE : {}\n", wfsServiceSettingsRequest.getResponse());
    }

    @Test
    public void b_loadWFSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWFSWorkspaceServiceSettingsRequest wfsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_25_x.loadWFSWorkspaceServiceSettingsRequest();
        logger.info("########################GEOSERVER_WFS_WORKSPACE_SERIVCE_SETTINGS_RESPONSE : {}\n", wfsWorkspaceServiceSettingsRequest
                .withWorkspace("topp").getResponse());
    }

    @Test
    public void c_updateWFSServiceSettingsRequestTest() throws Exception {
        GeoserverUpdateWFSServiceSettingsRequest updateWFSServiceSettingsRequest = geoserverConnectorStoreV2_25_x.updateWFSServiceSettingsRequest();
        GPGeoserverWFSServiceSettings wfsServiceSettings = toWFSServiceSettings();
        wfsServiceSettings.setMaintainer("Francesco Izzi");
        wfsServiceSettings.setAbstrct("This is a simple example for Geoserver");
        assertTrue(updateWFSServiceSettingsRequest.withBody(wfsServiceSettings).getResponse());
        GPGeoserverWFSServiceSettings retrieveWFSSettings = geoserverConnectorStoreV2_25_x.loadWFSServiceSettingsRequest().getResponse();
        assertTrue(retrieveWFSSettings.getMaintainer().equals("Francesco Izzi"));
        assertTrue(retrieveWFSSettings.getAbstrct().equals("This is a simple example for Geoserver"));
    }

    @Test
    public void d_updateWFSServiceSettingsRequestTest() throws Exception {
        GeoserverWFSServiceSettings wfsServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WFSServiceSettings")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverWFSServiceSettings.class);
        logger.info("###################WFS_UPDATE_SERVICE_SETTINGS_RESPONSE : {}\n", geoserverConnectorStoreV2_25_x.updateWFSServiceSettingsRequest()
                .withBody(wfsServiceSettings).getResponse());
    }

    @Test
    public void e_updateWFSWorkspaceServiceSettingsRequestTest() throws Exception {
        try (InputStream wfsWorkspaceServiceSettingsStream = currentThread().getContextClassLoader().getResourceAsStream("WFSWorkspaceServiceSettings.xml")) {
            checkArgument(wfsWorkspaceServiceSettingsStream != null, "The Parameter wfsWorkspaceServiceSettingsStream must not be null.");
            GPGeoserverWFSWorkspaceServiceSettings wfsWorkspaceServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                    .readValue(new InputStreamReader(wfsWorkspaceServiceSettingsStream), GPGeoserverWFSWorkspaceServiceSettings.class);
            wfsWorkspaceServiceSettings.getWorkspace().setWorkspaceName("nurc");
            wfsWorkspaceServiceSettings.setMaintainer("Giuseppe La Scaleia");
            wfsWorkspaceServiceSettings.setAbstrct("This is a simple Test.");
            GeoserverUpdateWFSWorkspaceServiceSettingsRequest updateWFSWorkspaceSettingsRequest = geoserverConnectorStoreV2_25_x.updateWFSWorkspaceServiceSettingsRequest();
            logger.info("@@@@@@@@@@@@@@@@@WFS_UPDATE_WORKSPACE_SERVICE_SETTINGS_RESPONSE : {}\n", updateWFSWorkspaceSettingsRequest
                    .withWorkspace("nurc").withBody(wfsWorkspaceServiceSettings).getResponse());
        }
    }

    @Test
    public void f_loadWFSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWFSWorkspaceServiceSettingsRequest wfsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_25_x.loadWFSWorkspaceServiceSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WFS_WORKSPACE_SETTINGS_RESPONSE : {}\n", wfsWorkspaceServiceSettingsRequest
                .withWorkspace("nurc").getResponse());
    }

    @Test
    public void g_deleteWFSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverDeleteWFSWorkspaceServiceSettingsRequest deleteWFSWorkspaceServiceSettingRequest = geoserverConnectorStoreV2_25_x.deleteWFSWorkspaceServiceSettingsRequest();
        logger.info("########################WFS_DELETE_WORKSPACE_SERVICE_SETTINGS : {}\n", deleteWFSWorkspaceServiceSettingRequest
                .withWorkspace("nurc").getResponse());
    }
}