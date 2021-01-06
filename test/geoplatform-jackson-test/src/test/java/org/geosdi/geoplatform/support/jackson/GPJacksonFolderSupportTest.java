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
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.request.folder.InsertFolderRequest;
import org.geosdi.geoplatform.request.folder.WSAddFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.folder.WSDDFolderAndTreeModifications;
import org.geosdi.geoplatform.request.folder.WSDeleteFolderAndTreeModifications;
import org.geosdi.geoplatform.response.collection.ChildrenFolderStore;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonFolderSupportTest extends GPBaseJacksonSupportTest {

    @Test
    public void folderDataMapperTest() throws Exception {
        GPFolder folder = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(FOLDER_DATA_JSON), GPFolder.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FOLDER_DATA_MAPPING" + " : {}\n\n", folder);
        super.marshall(folder);
    }

    @Test
    public void insertFolderRequestDataMapperTest() throws Exception {
        InsertFolderRequest insertFolderRequest = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        INSERT_FOLDER_REQUEST_DATA_JSON),
                InsertFolderRequest.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@INSERT_FOLDER_REQUEST_DATA_MAPPING"
                + " : {}\n\n", insertFolderRequest);

        super.marshall(insertFolderRequest);
    }

    @Test
    public void addFolderAndTreeModificationRequestDataMapperTest()
            throws Exception {
        WSAddFolderAndTreeModificationsRequest addFolderRequest = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        ADD_FOLDER_TREE_MODIFICATION_REQUEST_DATA_JSON),
                WSAddFolderAndTreeModificationsRequest.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
                + "ADD_FOLDER_TREE_MODIFICATION_REQUEST_DATA_MAPPING"
                + " : {}\n\n", addFolderRequest);

        super.marshall(addFolderRequest);
    }

    @Test
    public void deleteFolderAndTreeModificationRequestDataMapperTest()
            throws Exception {
        WSDeleteFolderAndTreeModifications deleteFolderRequest = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        DELETE_FOLDER_TREE_MODIFICATION_REQUEST_DATA_JSON),
                WSDeleteFolderAndTreeModifications.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
                + "DELETE_FOLDER_TREE_MODIFICATION_REQUEST_DATA_MAPPING"
                + " : {}\n\n", deleteFolderRequest);

        super.marshall(deleteFolderRequest);
    }

    @Test
    public void getChildrenFolderDataMapperTest() throws Exception {
        ChildrenFolderStore folderStore = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        GET_CHILDREN_FOLDER_DATA_JSON),
                ChildrenFolderStore.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@GET_CHILDREN_FOLDER_DATA_MAPPING"
                + " : {}\n\n", folderStore);

        super.marshall(folderStore);
    }

    @Test
    public void ddFolderAndTreeModificationRequestDataMapperTest()
            throws Exception {
        WSDDFolderAndTreeModifications ddFolderRequest = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        DD_FOLDER_TREE_MODIFICATION_REQUEST_DATA_JSON),
                WSDDFolderAndTreeModifications.class);

        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
                + "DD_FOLDER_TREE_MODIFICATION_REQUEST_DATA_MAPPING"
                + " : {}\n\n", ddFolderRequest);

        super.marshall(ddFolderRequest);
    }

}
