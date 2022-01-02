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
package org.geosdi.geoplatform.geoserver.extensions.importer;

import it.geosolutions.geoserver.rest.manager.GeoServerRESTImporterManager;
import net.sf.json.JSONObject;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.GPGeoserverCreateImportResponse;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.GPGeoserverLoadImportResponse;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.GeoserverExpandFileImporter;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.body.*;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.task.GPGeoserverTaskImporter;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.task.IGPGeoserverTaskImporter;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorImporterTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorImporterTest.class);
    //
    @Resource(name = "geoServerRestImporterManager")
    private GeoServerRESTImporterManager geoServerRestImporterManager;

    @Test
    public void a_importRunning() throws  Exception {
        logger.info("###########IMPORT RUNNING : {}\n", this.geoserverConnectorStore.isImportsRunning());
    }

    @Test
    @Ignore
    public void b_importerFile() throws Exception {
        GPGeoserverCreateImportBody gpGeoserverCreateImportBody = GPGeoserverCreateImportBody.builder()
                .targetStore(new GPGeoserverTargetStoreBody(new GPGeoserverDataStoreBody("ds_vito")))
                .targetWorkspace(new GPGeoserverTargetWorkspaceBody(new GPGeoserverWorkspaceBody("ws_vito")))
                .data(new GPGeoserverDataBody("file", "/home/geosdi/layer_importer1.kml")).build();
        GPGeoserverCreateImportResponse gpGeoserverCreateImportResponse = this.geoserverConnectorStore.createImportRequest()
                .withExpand(GeoserverExpandFileImporter.ALL)
                .withBody(gpGeoserverCreateImportBody).getResponse();
        logger.info("###############CREATE TASK RESPONSE:{}\n", gpGeoserverCreateImportResponse);

        GPGeoserverLoadImportResponse gpGeoserverLoadImportResponse = this.geoserverConnectorStore.loadImportRequest()
                .withId(gpGeoserverCreateImportResponse.getId()).getResponse();

        logger.info("################LOAD TASK : {}\n", gpGeoserverLoadImportResponse);
        IGPGeoserverTaskImporter createTaskImporter = gpGeoserverLoadImportResponse.getTasks().get(0);
        GPGeoserverTaskImporter gpGeoserverLoadTaskResponse = this.geoserverConnectorStore.loadTaskRequest()
                .withExpand(GeoserverExpandFileImporter.ALL)
                .withImportId(gpGeoserverCreateImportResponse.getId())
                .withTaskId(createTaskImporter.getId()).getResponse();
        logger.info("###########NAME: {}\n", gpGeoserverLoadTaskResponse.getLayer().getName());
        logger.info("###########TITLE: {}\n", gpGeoserverLoadTaskResponse.getLayer().getTitle());
        logger.info("###########STYLE TITLE: {}\n", gpGeoserverLoadTaskResponse.getLayer().getStyle().getName());

        logger.info("##############{}\n",this.geoserverConnectorStore.createImportWithIdRequest()
                        .withId(gpGeoserverCreateImportResponse.getId())
                .withExec(Boolean.TRUE)
                .withAsync(Boolean.TRUE)
                .withBody(gpGeoserverCreateImportBody).getResponse());
    }

    @Test
    @Ignore
    public void c_importFile() throws Exception {
        String jsonData = "{\n" +
                " \"import\": {\n" +
                " \"targetWorkspace\": {\n" +
                " \"workspace\": {\n" +
                " \"name\": \"ws_vito\"\n" +
                " }\n" +
                " },\n" +
                "\"targetStore\": {\n" +
                " \"dataStore\": {\n" +
                " \"name\": \"ds_vito\"\n" +
                " }\n" +
                " },\n" +
                " \"data\": {\n" +
                " \"type\": \"file\",\n" +
                " \"file\": \"/home/geosdi/layer_importer1.kml\"\n" +
                " }\n" +
                " }\n" +
                "}";
         int i = this.geoServerRestImporterManager.postNewImport(jsonData);
                logger.info("###############i:{}\n", i);
                JSONObject importObject = this.geoServerRestImporterManager.getImport(i);
                logger.info("###############RESPONSE:{}\n", importObject);

                JSONObject tasks = importObject.getJSONArray("tasks").getJSONObject(0);
                logger.info("#################{}\n", tasks);
                int t = tasks.getInt("id");
                logger.info("############t:{}\n", t);
                JSONObject layer = this.geoServerRestImporterManager.getLayer(i, t);
                logger.info("#####################LAYER: {}\n\n\n", layer);
                Thread.sleep(1000);
         geoServerRestImporterManager.postImport(i);
    }
}