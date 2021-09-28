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
package org.geosdi.geoplatform.geoserver.datastores;

import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.entity.ContentType;
import org.geosdi.geoplatform.connector.geoserver.model.file.GPDataStoreFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GPGeoserverLoadDatastores;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverLoadDatastoresRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorDatastoresTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorDatastoresTest.class);


    @Test
    public void a_getDataStores() throws Exception {
        RESTDataStoreList store = restReader.getDatastores("tiger");
        GeoserverLoadDatastoresRequest gpGeoserverLoadDatastoresRequest = this.geoserverConnectorStore.loadDatastoresRequest().withWorkspaceName("tiger");
        GPGeoserverLoadDatastores gpGeoserverLoadDatastores = gpGeoserverLoadDatastoresRequest.getResponse();
        Assert.assertTrue("#####################", store.getNames().size() == gpGeoserverLoadDatastores.getDataStores().size());
        logger.info("##########################DATA_STORES: {}\n", gpGeoserverLoadDatastoresRequest.getResponse().getDataStores());
        logger.info("##########################DATA_STORES: {}\n", store.getNames());
        List<String> names = gpGeoserverLoadDatastores.getDataStores().stream()
                .map(c -> c.getName()).collect(Collectors.toList());
        store.getNames().stream().forEach(n -> Assert.assertTrue("##################", names.contains(n)));
    }

    @Test
    public void b_existDatastores() throws Exception {
        Assert.assertTrue("####################", this.restReader.existsDatastore("tiger", "nyc", TRUE) ==
                this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("tiger").withStoreName("nyc").withQuietNotFound(TRUE).exist());
        Assert.assertTrue("####################", this.restReader.existsDatastore("tiger", "nycc", TRUE) ==
                this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("tiger").withStoreName("nycc").withQuietNotFound(TRUE).exist());
    }

    @Ignore(value = "Store store_vito may be not present")
    @Test
    public void c_deleteDatastore() throws Exception {
        Assert.assertTrue("####################", this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("sf").withStoreName("store_vito").withQuietNotFound(TRUE).exist());
        this.geoserverConnectorStore.deleteDatastoreRequest().withDatastoreName("store_vito").withWorkspaceName("sf").withRecurse(TRUE).getResponse();
        Assert.assertFalse("####################", this.geoserverConnectorStore.loadDatastoreRequest().withWorkspaceName("sf").withStoreName("store_vito").withQuietNotFound(TRUE).exist());
    }

    @Ignore
    @Test
    public void d_updateDataStoreWithShape() throws Exception {
        File file = new File(of("src", "test", "resources", "admin_shp_comuni.zip").collect(joining(separator)));
        Assert.assertTrue("#################FILE_EXSIST", file.exists());
        logger.info("##################{}\n", FilenameUtils.getBaseName(file.toURI().toString()));
        logger.info("###############{}\n", this.geoserverConnectorStore.updateDataStoreWithStoreName()
                .withWorkspace("sf")
                .withStore("store_vito")
                .withConfigure(GPParameterConfigure.NONE)
                .withMethod(GPUploadMethod.FILE)
                .withMimeType(ContentType.APPLICATION_OCTET_STREAM)
                .withCharset("UTF-8")
                .withFormat(GPDataStoreFileExtension.SHP)
                .withFile(file).getResponse());
    }


}