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

import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.request.layer.InsertLayerRequest;
import org.geosdi.geoplatform.request.layer.WSAddLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDDLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.request.layer.WSDeleteLayerAndTreeModificationsRequest;
import org.geosdi.geoplatform.response.GetDataSourceResponse;
import org.geosdi.geoplatform.response.ShortLayerDTO;
import org.geosdi.geoplatform.response.ShortLayerDTOContainer;
import org.geosdi.geoplatform.response.collection.TreeFolderElementsStore;
import org.junit.Test;

import static java.lang.Thread.currentThread;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonLayerSupportTest extends GPBaseJacksonSupportTest {

    @Test
    public void insertLayerRequestDataMapperTest() throws Exception {
        InsertLayerRequest insertLayerRequest = jacksonSupport.getDefaultMapper()
                .readValue(currentThread().getContextClassLoader().getResourceAsStream(INSERT_LAYER_REQUEST_DATA_JSON), InsertLayerRequest.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@INSERT_LAYER_REQUEST_DATA_MAPPING : {}\n\n", insertLayerRequest);
        super.marshall(insertLayerRequest);
    }

    @Test
    public void rasterLayerDataMapperTest() throws Exception {
        GPRasterLayer rasterLayer = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(RASTER_LAYER_DATA_JSON), GPRasterLayer.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@RASTER_LAYER_DATA_MAPPING : {}\n\n", rasterLayer);
        super.marshall(rasterLayer);
    }

    @Test
    public void vectorLayerDataMapperTest() throws Exception {
        GPVectorLayer vectorLayer = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(VECTOR_LAYER_DATA_JSON), GPVectorLayer.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@VECTOR_LAYER_DATA_MAPPING : {}\n\n", vectorLayer);
        super.marshall(vectorLayer);
    }

    @Test
    public void shortLayerDTODataMapperTest() throws Exception {
        ShortLayerDTO shortLayer = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(SHORT_LAYER_DTO_DATA_JSON), ShortLayerDTO.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@SHORT_LAYER_DTO_DATA_MAPPING : {}\n\n", shortLayer);
        super.marshall(shortLayer);
    }

    @Test
    public void shortLayerDTOContainerDataMapperTest() throws Exception {
        ShortLayerDTOContainer shortLayerContainer = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(SHORT_LAYER_DTO_CONTAINER_DATA_JSON), ShortLayerDTOContainer.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@SHORT_LAYER_DTO_CONTAINER_DATA_MAPPING : {}\n\n", shortLayerContainer);
        super.marshall(shortLayerContainer);
    }

    @Test
    public void treeFolderElementsStoreDataMapperTest() throws Exception {
        TreeFolderElementsStore store = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(TREE_FOLDER_ELEMENTS_STORE_DATA_JSON), TreeFolderElementsStore.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ TREE_FOLDER_ELEMENTS_STORE_DATA_MAPPING : {}\n\n", store);
        super.marshall(store);
    }

    @Test
    public void addLayerTreeModificationsDataMapperTest() throws Exception {
        WSAddLayerAndTreeModificationsRequest request = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(ADD_LAYER_TREE_MODIFICATION_REQUEST_DATA_JSON), WSAddLayerAndTreeModificationsRequest.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ADD_LAYER_TREE_MODIFICATION_REQUEST_DATA_MAPPING : {}\n\n", request);
        super.marshall(request);
    }

    @Test
    public void deleteLayerTreeModificationsDataMapperTest() throws Exception {
        WSDeleteLayerAndTreeModificationsRequest request = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(DELETE_LAYER_TREE_MODIFICATION_REQUEST_DATA_JSON), WSDeleteLayerAndTreeModificationsRequest.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@DELETE_LAYER_TREE_MODIFICATION_REQUEST_DATA_MAPPING : {}\n\n", request);
        super.marshall(request);
    }

    @Test
    public void ddLayerTreeModificationsDataMapperTest() throws Exception {
        WSDDLayerAndTreeModificationsRequest request = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(DD_LAYER_TREE_MODIFICATION_REQUEST_DATA_JSON), WSDDLayerAndTreeModificationsRequest.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ DD_LAYER_TREE_MODIFICATION_REQUEST_DATA_MAPPING : {}\n\n", request);
        super.marshall(request);
    }

    @Test
    public void getBBoxDataMapperTest() throws Exception {
        GPBBox bbox = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(GET_BBOX_DATA_JSON), GPBBox.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GET_BBOX_DATA_MAPPING : {}\n\n", bbox);
        super.marshall(bbox);
    }

    @Test
    public void getLayerInfoDataMapperTest() throws Exception {
        GPLayerInfo layerInfo = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(GET_LAYER_INFO_DATA_JSON), GPLayerInfo.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GET_LAYER_INFO_DATA_MAPPING: {}\n\n", layerInfo);
        super.marshall(layerInfo);
    }

    @Test
    public void getLayersDataSourceDataMapperTest() throws Exception {
        GetDataSourceResponse response = jacksonSupport.getDefaultMapper().readValue(currentThread().getContextClassLoader().getResourceAsStream(GET_LAYERS_DATA_SOURCE_DATA_JSON), GetDataSourceResponse.class);
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ GET_LAYERS_DATA_SOURCE_DATA_MAPPING : {}\n\n", response);
        super.marshall(response);
    }
}
