/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.publisher.rest;

import java.io.File;
import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;

import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPublisherRestTest extends PublisherRestTest {

    @Test
    public void analyzeZIPEPSGTestRest() throws Exception {
        super.mockAnalyzeZIPEPSG();

        InfoPreviewStore previewStore = PublisherRSServerUtils.gpPublisherClient.analyzeZIPEPSG(
                "http://localhost:8282/geoplatform-service/",
                "geoSDI", new File(
                        new File(".").getCanonicalPath() + File.separator
                        + "src/test/resources/logback-test.xml"));

        Assert.assertEquals(80, previewStore.getInfoPreviews().size());

        logger.debug(
                "\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ analyzeZIPEPSG "
                + "{}\n\n", previewStore);
    }

    @Test
    public void processEPSGResultTestRest() throws Exception {
        super.mockProcessEPSGResult();

        InfoPreviewStore previewStore = PublisherRSServerUtils.gpPublisherClient.processEPSGResult(
                new ProcessEPSGResultRequest());

        Assert.assertEquals(37, previewStore.getInfoPreviews().size());

        logger.debug(
                "\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ processEPSGResult "
                + "{}\n\n", previewStore);
    }

    @Test
    public void loadStyleTestRest() throws Exception {
        super.mockLoadStyle();

        String styleLoaded = PublisherRSServerUtils.gpPublisherClient.loadStyle(
                "LAYER_DATA_SOURCE_MOCK", "LAYER_NAME_MOCK");

        Assert.assertEquals("MOCK_STYLE_TEST_LOADED", styleLoaded);
    }

    @Test
    public void describeFeatureTypeTestRest() throws Exception {
        super.mockDescribeFeatureType();

        LayerAttributeStore layerAttributeStore = PublisherRSServerUtils.gpPublisherClient.describeFeatureType(
                "LAYER_NAME_MOCK_TEST");

        Assert.assertEquals(40, layerAttributeStore.getLayerAttributes().size());

        logger.debug(
                "\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ describeFeatureType "
                + "{}\n\n", layerAttributeStore);
    }

    @Test
    public void publishStyleTestRest() throws Exception {
        super.mockPublishStyle();

        Assert.assertTrue(PublisherRSServerUtils.gpPublisherClient.publishStyle(
                "STYLE_MOCK_MOCKITO"));
    }

    @Test
    public void putStyleTestRest() throws Exception {
        super.mockPutStyle();

        Assert.assertTrue(PublisherRSServerUtils.gpPublisherClient.putStyle(
                "STYLE_MOCK_TEST", "STYLE_NAME_MOCK"));
    }
    
    @Test
    public void existsStyleTestRest() throws Exception {
        super.mockExistsStyle();
        
        Assert.assertTrue(PublisherRSServerUtils.gpPublisherClient.existsStyle(
                "STYLE_MOCK_MOCKITO_TEST"));
    }

}
