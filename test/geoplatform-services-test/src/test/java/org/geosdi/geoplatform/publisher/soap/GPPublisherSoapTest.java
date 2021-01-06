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
package org.geosdi.geoplatform.publisher.soap;

import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayerRequest;
import org.geosdi.geoplatform.request.PublishRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPublisherSoapTest extends PublisherSoapTest {

    @Test
    public void analyzeZIPEPSGTestSoap() throws Exception {
        super.mockAnalyzeZIPEPSG();
        InfoPreviewStore previewStore = PublisherSoapServerUtils.gpPublisherClient.analyzeZIPEPSG(
                "http://localhost:8282/geoplatform-service/",
                "geoSDI", new File(new File(".").getCanonicalPath() + separator
                        + "src/test/resources/logback-test.xml"), null);
        assertEquals(60, previewStore.getInfoPreviews().size());
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ analyzeZIPEPSG {}\n\n", previewStore);
    }

    @Test
    public void processEPSGResultTestSoap() throws Exception {
        super.mockProcessEPSGResult();
        InfoPreviewStore previewStore = PublisherSoapServerUtils.gpPublisherClient.processEPSGResult(new ProcessEPSGResultRequest());
        assertEquals(17, previewStore.getInfoPreviews().size());
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ processEPSGResult SOAP {}\n\n", previewStore);
    }

    @Test
    public void loadStyleTestSoap() throws Exception {
        super.mockLoadStyle();
        String styleLoaded = PublisherSoapServerUtils.gpPublisherClient.loadStyle(
                "LAYER_DATA_SOURCE_MOCK_SOAP", "LAYER_NAME_MOCK_SOAP");
        assertEquals("MOCK_STYLE_TEST_LOADED_SOAP", styleLoaded);
    }

    @Test
    public void describeFeatureTypeTestSoap() throws Exception {
        super.mockDescribeFeatureType();
        LayerAttributeStore layerAttributeStore = PublisherSoapServerUtils.gpPublisherClient.describeFeatureType("LAYER_NAME_MOCK_TEST_SOAP");
        assertEquals(20, layerAttributeStore.getLayerAttributes().size());
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ describeFeatureType SOAP {}\n\n", layerAttributeStore);
    }

    @Test
    public void publishStyleTestSoap() throws Exception {
        super.mockPublishStyle();
        assertFalse(PublisherSoapServerUtils.gpPublisherClient.publishStyle("STYLE_MOCK_MOCKITO_SOAP", "STYLE_MOCK_MOCKITO", Boolean.FALSE));
    }

    @Test
    public void putStyleTestSoap() throws Exception {
        super.mockPutStyle();
        assertFalse(PublisherSoapServerUtils.gpPublisherClient.updateStyle("STYLE_MOCK_TEST_SOAP", "STYLE_NAME_MOCK_SOAP", Boolean.FALSE));
    }

    @Test
    public void existsStyleTestSoap() throws Exception {
        super.mockExistsStyle();
        assertFalse(PublisherSoapServerUtils.gpPublisherClient.existsStyle("STYLE_MOCK_MOCKITO_TEST_SOAP"));
    }

    @Test
    public void analyzeTIFInPreviewTestSoap() throws Exception {
        super.mockAnalyzeTIFInPreview();
        InfoPreview preview = PublisherSoapServerUtils.gpPublisherClient.analyzeTIFInPreview("jdshfjsdfhsjfh",
                new File(new File(".").getCanonicalPath() + separator + "src/test/resources/logback-test.xml"), Boolean.TRUE, null);
        assertNotNull(preview);
        assertEquals("DATA_STORE_MOCK_MOKITO_SOAP", preview.getDataStoreName());
        assertEquals("MESSAGE_MOCK_MOKITO_SOAP", preview.getMessage());
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@ analyzeTIFInPreview SOAP: {}\n\n", preview);
    }

    @Test
    public void getPreviewDataStoresTestSoap() throws Exception {
        super.mockGetPreviewDataStores();
        InfoPreviewStore previewStore = PublisherSoapServerUtils.gpPublisherClient.getPreviewDataStores("USER_MOCK_MOKITO_SOAP");
        assertNotNull(previewStore);
        assertEquals(25, previewStore.getInfoPreviews().size());
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@ getPreviewDataStores SOAP : {}\n\n", previewStore);
    }

    @Test
    public void publishTestSoap() throws Exception {
        super.mockPublish();
        assertFalse(PublisherSoapServerUtils.gpPublisherClient.publish(
                new PublishLayerRequest("7"
                        + "364736sdfdsufhiuf", "WORKSPACE_MOCK_SOAP",
                        "DATA_STORE_MOCK_SOAP", "LAYER_NAME_MOCK_SOAP")));
    }

    @Test
    public void publishAllofPreviewTestSoap() throws Exception {
        super.mockPublishAllofPreview();
        assertFalse(PublisherSoapServerUtils.gpPublisherClient.publishAllofPreview(
                        new PublishRequest("487538hghghewrwr",
                                "WORKSPACE_MOCK_TEST_SOAP",
                                "DATA_STORE_MOCK_TEST_SOAP")));
    }
}