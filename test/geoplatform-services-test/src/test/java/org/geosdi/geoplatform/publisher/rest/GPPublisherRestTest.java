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
package org.geosdi.geoplatform.publisher.rest;

import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayerRequest;
import org.geosdi.geoplatform.request.PublishRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.publisher.rest.PublisherRSServerUtils.gpPublisherClient;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPublisherRestTest extends PublisherRestTest {

    @Test
    public void analyzeZIPEPSGTestRest() throws Exception {
        super.mockAnalyzeZIPEPSG();
        InfoPreviewStore previewStore = gpPublisherClient.analyzeZIPEPSG("http://localhost:8282/geoplatform-service/",
                "geoSDI", new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "logback-test.xml")
                        .collect(joining(separator))), null);
        assertEquals(80, previewStore.getInfoPreviews().size());
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ analyzeZIPEPSG {}\n\n", previewStore);
    }

    @Test
    public void processEPSGResultTestRest() throws Exception {
        super.mockProcessEPSGResult();
        InfoPreviewStore previewStore = gpPublisherClient.processEPSGResult(new ProcessEPSGResultRequest());
        assertEquals(37, previewStore.getInfoPreviews().size());
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ processEPSGResult {}\n\n", previewStore);
    }

    @Test
    public void loadStyleTestRest() throws Exception {
        super.mockLoadStyle();
        String styleLoaded = gpPublisherClient.loadStyle("LAYER_DATA_SOURCE_MOCK", "LAYER_NAME_MOCK");
        assertEquals("MOCK_STYLE_TEST_LOADED", styleLoaded);
    }

    @Test
    public void describeFeatureTypeTestRest() throws Exception {
        super.mockDescribeFeatureType();
        LayerAttributeStore layerAttributeStore = gpPublisherClient.describeFeatureType("LAYER_NAME_MOCK_TEST");
        assertEquals(40, layerAttributeStore.getLayerAttributes().size());
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ describeFeatureType {}\n\n", layerAttributeStore);
    }

    @Test
    public void publishStyleTestRest() throws Exception {
        super.mockPublishStyle();
        assertTrue(gpPublisherClient.publishStyle("STYLE_MOCK_MOCKITO", "STYLE_MOCK_MOCKITO", Boolean.FALSE));
    }

    @Test
    public void putStyleTestRest() throws Exception {
        super.mockPutStyle();
        assertTrue(gpPublisherClient.updateStyle("STYLE_MOCK_TEST", "STYLE_NAME_MOCK", TRUE));
    }

    @Test
    public void existsStyleTestRest() throws Exception {
        super.mockExistsStyle();
        assertTrue(gpPublisherClient.existsStyle("STYLE_MOCK_MOCKITO_TEST"));
    }

    @Test
    public void analyzeTIFInPreviewTestRest() throws Exception {
        super.mockAnalyzeTIFInPreview();
        InfoPreview preview = gpPublisherClient.analyzeTIFInPreview("jdshfjsdfhsjfh",
                new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "logback-test.xml")
                        .collect(joining(separator))), TRUE, null);
        assertNotNull(preview);
        assertEquals("DATA_STORE_MOCK_MOKITO", preview.getDataStoreName());
        assertEquals("MESSAGE_MOCK_MOKITO", preview.getMessage());
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@ analyzeTIFInPreview : {}\n\n", preview);
    }

    @Test
    public void getPreviewDataStoresTestRest() throws Exception {
        super.mockGetPreviewDataStores();
        InfoPreviewStore previewStore = gpPublisherClient.getPreviewDataStores("USER_MOCK_MOKITO");
        assertNotNull(previewStore);
        assertEquals(15, previewStore.getInfoPreviews().size());
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@ getPreviewDataStores : {}\n\n", previewStore);
    }

    @Test
    public void publishTestRest() throws Exception {
        super.mockPublish();
        assertTrue(gpPublisherClient.publish(new PublishLayerRequest("7364736sdfdsufhiuf", "WORKSPACE_MOCK",
                "DATA_STORE_MOCK", "LAYER_NAME_MOCK")));
    }

    @Test
    public void publishAllofPreviewTestRest() throws Exception {
        super.mockPublishAllofPreview();
        assertTrue(gpPublisherClient.publishAllofPreview(new PublishRequest("487538hghghewrwr",
                "WORKSPACE_MOCK_TEST", "DATA_STORE_MOCK_TEST")));
    }
}