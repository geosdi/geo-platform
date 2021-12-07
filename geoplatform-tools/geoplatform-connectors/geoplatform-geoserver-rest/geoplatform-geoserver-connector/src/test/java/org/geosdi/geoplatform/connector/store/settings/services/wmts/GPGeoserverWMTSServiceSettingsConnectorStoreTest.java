/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.store.settings.services.wmts;

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wmts.GPGeoserverWMTSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wmts.GPGeoserverWMTSWorkspaceServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wmts.GeoserverWMTSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.request.settings.services.wmts.*;
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
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverWMTSServiceSettingsJacksonTest.toWMTSServiceSettings;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverWMTSServiceSettingsConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadWMTSServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWMTSServiceSettingsRequest wmtsServiceSettingsRequest = geoserverConnectorStoreV2_19_x.loadWMTSServiceSettingsRequest();
        logger.info("########################GEOSERVER_WMTS_SERIVCE_SETTINGS_RESPONSE : {}\n", wmtsServiceSettingsRequest.getResponse());
    }

    @Test
    public void b_loadWMTSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWMTSWorkspaceServiceSettingsRequest wmtsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_19_x.loadWMTSWorkspaceServiceSettingsRequest();
        logger.info("########################GEOSERVER_WMTS_SERIVCE_SETTINGS_RESPONSE : {}\n", wmtsWorkspaceServiceSettingsRequest
                .withWorkspace("topp").getResponse());
    }

    @Test
    public void c_updateWMTSServiceSettingsRequestTest() throws Exception {
        GeoserverUpdateWMTSServiceSettingsRequest updateWMTSServiceSettingsRequest = geoserverConnectorStoreV2_19_x.updateWMTSServiceSettingsRequest();
        GPGeoserverWMTSServiceSettings wmtsServiceSettings = toWMTSServiceSettings();
        wmtsServiceSettings.setMaintainer("Francesco Izzi");
        wmtsServiceSettings.setAbstrct("This is a simple example for Geoserver");
        assertTrue(updateWMTSServiceSettingsRequest.withBody(wmtsServiceSettings).getResponse());
        GPGeoserverWMTSServiceSettings retrieveWMTSSettings = geoserverConnectorStoreV2_19_x.loadWMTSServiceSettingsRequest().getResponse();
        assertTrue(retrieveWMTSSettings.getMaintainer().equals("Francesco Izzi"));
        assertTrue(retrieveWMTSSettings.getAbstrct().equals("This is a simple example for Geoserver"));
    }

    @Test
    public void d_updateWMTSServiceSettingsRequestTest() throws Exception {
        GeoserverWMTSServiceSettings wmtsServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WMTSServiceSettings-2.19")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverWMTSServiceSettings.class);
        logger.info("###################WMTS_UPDATE_SERVICE_SETTINGS_RESPONSE : {}\n", geoserverConnectorStoreV2_19_x.updateWMTSServiceSettingsRequest()
                .withBody(wmtsServiceSettings).getResponse());
    }

    @Test
    public void e_updateWMTSWorkspaceServiceSettingsRequestTest() throws Exception {
        try (InputStream wmtsWorkspaceServiceSettingsStream = currentThread().getContextClassLoader().getResourceAsStream("WMTSWorkspaceServiceSettings.xml")) {
            checkArgument(wmtsWorkspaceServiceSettingsStream != null, "The Parameter wmtsWorkspaceServiceSettingsStream must not be null.");
            GPGeoserverWMTSWorkspaceServiceSettings wmtsWorkspaceServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                    .readValue(new InputStreamReader(wmtsWorkspaceServiceSettingsStream), GPGeoserverWMTSWorkspaceServiceSettings.class);
            wmtsWorkspaceServiceSettings.getWorkspace().setWorkspaceName("nurc");
            wmtsWorkspaceServiceSettings.setMaintainer("Giuseppe La Scaleia");
            wmtsWorkspaceServiceSettings.setAbstrct("This is a simple Test.");
            GeoserverUpdateWMTSWorkspaceServiceSettingsRequest updateWMTSWorkspaceSettingsRequest = geoserverConnectorStoreV2_19_x.updateWMTSWorkspaceServiceSettingsRequest();
            logger.info("@@@@@@@@@@@@@@@@@WMTS_UPDATE_WORKSPACE_SERVICE_SETTINGS_RESPONSE : {}\n", updateWMTSWorkspaceSettingsRequest
                    .withWorkspace("nurc").withBody(wmtsWorkspaceServiceSettings).getResponse());
        }
    }

    @Test
    public void f_loadWMTSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWMTSWorkspaceServiceSettingsRequest wmtsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_19_x.loadWMTSWorkspaceServiceSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WNTS_WORKSPACE_SETTINGS_RESPONSE : {}\n", wmtsWorkspaceServiceSettingsRequest
                .withWorkspace("nurc").getResponse());
    }

    @Test
    public void g_deleteWMTSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverDeleteWMTSWorkspaceServiceSettingsRequest deleteWMTSWorkspaceServiceSettingRequest = geoserverConnectorStoreV2_19_x.deleteWMTSWorkspaceServiceSettingsRequest();
        logger.info("########################WMTS_DELETE_WORKSPACE_SERVICE_SETTINGS : {}\n", deleteWMTSWorkspaceServiceSettingRequest
                .withWorkspace("nurc").getResponse());
    }
}