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
package org.geosdi.geoplatform.connector.store.settings.services.wms;

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wms.GPGeoserverWMSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wms.GPGeoserverWMSWorkspaceServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wms.GeoserverWMSServiceSettings;
import org.geosdi.geoplatform.connector.geoserver.request.settings.services.wms.*;
import org.geosdi.geoplatform.connector.jackson.GPGeoserverWMSServiceSettingsJacksonTest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.lang.Thread.currentThread;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverWMSServiceSettingsConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadWMSServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWMSServiceSettingsRequest wmsServiceSettingsRequest = geoserverConnectorStoreV2_19_x.loadWMSServiceSettingRequest();
        logger.info("########################GEOSERVER_WMS_SERIVCE_SETTINGS_RESPONSE : {}\n", wmsServiceSettingsRequest.getResponse());
    }

    @Test
    public void b_loadWMSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWMSWorkspaceServiceSettingsRequest wmsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_19_x.loadWMSWorkspaceServiceSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WMS_WORKSPACE_SETTINGS_RESPONSE : {}\n", wmsWorkspaceServiceSettingsRequest
                .withWorkspace("topp").getResponse());
    }

    @Test
    public void c_updateWMSServiceSettingsRequestTest() throws Exception {
        GeoserverUpdateWMSServiceSettingsRequest updateWMSServiceSettingsRequest = geoserverConnectorStoreV2_19_x.updateWMSServiceSettingsRequest();
        GPGeoserverWMSServiceSettings wmsServiceSettings = GPGeoserverWMSServiceSettingsJacksonTest.toWMSServiceSettings();
        wmsServiceSettings.setMaintainer("Francesco Izzi");
        wmsServiceSettings.setAbstrct("This is a simple example for Geoserver");
        assertTrue(updateWMSServiceSettingsRequest.withBody(wmsServiceSettings).getResponse());
        GPGeoserverWMSServiceSettings retrieveWMSSettings = geoserverConnectorStoreV2_19_x.loadWMSServiceSettingRequest().getResponse();
        assertTrue(retrieveWMSSettings.getMaintainer().equals("Francesco Izzi"));
        assertTrue(retrieveWMSSettings.getAbstrct().equals("This is a simple example for Geoserver"));
    }

    @Test
    public void d_updateWMSServiceSettingsRequestTest() throws Exception {
        GeoserverWMSServiceSettings wmsServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WMSServiceSettings-2.19")
                        .collect(Collectors.joining(separator, "", ".xml"))), GPGeoserverWMSServiceSettings.class);
        logger.info("###################WMS_UPDATE_SERVICE_SETTINGS_RESPONSE : {}\n", geoserverConnectorStoreV2_19_x.updateWMSServiceSettingsRequest()
                .withBody(wmsServiceSettings).getResponse());
    }

    @Test
    public void e_updateWMSWorkspaceServiceSettingsRequestTest() throws Exception {
        try (InputStream wmsWorkspaceServiceSettingsStream = currentThread().getContextClassLoader().getResourceAsStream("WMSWorkspaceServiceSettings.xml")) {
            checkArgument(wmsWorkspaceServiceSettingsStream != null, "The Parameter wmsWorkspaceServiceSettingsStream must not be null.");
            GPGeoserverWMSWorkspaceServiceSettings wmsWorkspaceServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                    .readValue(new InputStreamReader(wmsWorkspaceServiceSettingsStream), GPGeoserverWMSWorkspaceServiceSettings.class);
            wmsWorkspaceServiceSettings.getWorkspace().setWorkspaceName("nurc");
            wmsWorkspaceServiceSettings.setMaintainer("Giuseppe La Scaleia");
            wmsWorkspaceServiceSettings.setAbstrct("This is a simple Test.");
            GeoserverUpdateWMSWorkspaceSettingsRequest updateWMSWorkspaceSettingsRequest = geoserverConnectorStoreV2_19_x.updateWMSWorkspaceServiceSettingsRequest();
            logger.info("@@@@@@@@@@@@@@@@@WMS_UPDATE_WORKSPACE_SERVICE_SETTINGS_RESPONSE : {}\n", updateWMSWorkspaceSettingsRequest
                    .withWorkspace("nurc").withBody(wmsWorkspaceServiceSettings).getResponse());
        }
    }

    @Test
    public void f_loadWMSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverLoadWMSWorkspaceServiceSettingsRequest wmsWorkspaceServiceSettingsRequest = geoserverConnectorStoreV2_19_x.loadWMSWorkspaceServiceSettingsRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WMS_WORKSPACE_SETTINGS_RESPONSE : {}\n", wmsWorkspaceServiceSettingsRequest
                .withWorkspace("nurc").getResponse());
    }

    @Test
    public void g_deleteWMSWorkspaceServiceSettingsRequestTest() throws Exception {
        GeoserverDeleteWMSWorkspaceServiceSettingRequest deleteWMSWorkspaceServiceSettingRequest = geoserverConnectorStoreV2_19_x.deleteWMSWorkspaceServiceSettingsRequest();
        logger.info("########################WMS_DELETE_WORKSPACE_SERVICE_SETTINGS : {}\n", deleteWMSWorkspaceServiceSettingRequest
                .withWorkspace("nurc").getResponse());
    }
}