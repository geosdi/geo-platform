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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Resource;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.geosdi.geoplatform.connectors.ws.publish.rest.GPPublisherRestClientTestConnector;
import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayerRequest;
import org.geosdi.geoplatform.request.PublishRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttribute;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.geosdi.geoplatform.services.GPPublisherService;
import org.geosdi.geoplatform.services.GPPublisherServiceImpl;
import org.geosdi.geoplatform.support.cxf.rs.provider.configurator.GPRestProviderType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext-PublisherTest.xml"})
@ActiveProfiles(profiles = {"dev"})
public abstract class PublisherRestTest {

    static final Logger logger = LoggerFactory.getLogger(PublisherRestTest.class);
    //
    private static GPPublisherService publisherService;
    //
    private static final AtomicBoolean flag = new AtomicBoolean(Boolean.FALSE);
    @Value("configurator{webservice_rs_test_publisher_endpoint_address}")
    private String basicRestAddress;
    @Value("configurator{cxf_rest_provider_type}")
    private GPRestProviderType providerType;
    @Resource(name = "serverLoggingInInterceptorBean")
    private LoggingInInterceptor serverLogInInterceptor;
    @Resource(name = "serverLoggingOutInterceptorBean")
    private LoggingOutInterceptor serverLogOutInterceptor;
    @Resource(name = "gpPublisherRestClient")
    private GPPublisherRestClientTestConnector gpPublisherRestClient;

    @BeforeClass
    public static void beforeClass() {
        publisherService = mock(GPPublisherServiceImpl.class);
    }

    @Before
    public void setUp() {
        if (flag.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
            logger.debug(
                    "\n\t@@@@@@@@@@@@@@@@@@@@ SetUp {} @@@@@@@@@@@@@@@@@@@@\n",
                    getClass().getSimpleName());
            PublisherRSServerUtils.gpPublisherClient = gpPublisherRestClient.getEndpointService();
            PublisherRSServerUtils.server = GPPubliserRestServerConfig.gpPublisherRestServer(
                    publisherService, basicRestAddress, providerType,
                    serverLogInInterceptor, serverLogOutInterceptor);
            PublisherRSServerUtils.server.start();
            logger.debug(
                    "\n\n\t@@@@@@@@@@@@@@@@@@@@@ Start GP_PUBLISHER_REST Server"
                    + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        PublisherRSServerUtils.server.stop();
        logger.debug("\n\n\t@@@@@@@@@@@@@@@@@@@@@ Stop GP_PUBLISHER_REST Server"
                + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
    }

    final void mockPublishAllofPreview() throws Exception {
        when(publisherService.publishAllofPreview(any(PublishRequest.class)))
                .thenReturn(Boolean.TRUE);
    }

    final void mockPublish() throws Exception {
        when(publisherService.publish(any(PublishLayerRequest.class))).thenReturn(
                Boolean.TRUE);
    }

    final void mockGetPreviewDataStores() throws Exception {
        when(publisherService.getPreviewDataStores(any(String.class))).thenReturn(
                createInfoPreviewStore(15));
    }

    final void mockAnalyzeTIFInPreview() throws Exception {
        when(publisherService.analyzeTIFInPreview(any(String.class), any(
                File.class),
                any(Boolean.class))).thenReturn(new InfoPreview(
                                "DATA_STORE_MOCK_MOKITO",
                                "MESSAGE_MOCK_MOKITO"));
    }

    final void mockExistsStyle() throws Exception {
        when(publisherService.existsStyle(any(String.class))).thenReturn(
                Boolean.TRUE);
    }

    final void mockPutStyle() throws Exception {
        when(publisherService.putStyle(any(String.class), any(String.class))).thenReturn(
                Boolean.TRUE);
    }

    final void mockPublishStyle() throws Exception {
        when(publisherService.publishStyle(any(String.class))).thenReturn(
                Boolean.TRUE);
    }

    final void mockDescribeFeatureType() throws Exception {
        when(publisherService.describeFeatureType(any(String.class))).thenReturn(
                createLayerAttributeStore(40));
    }

    final void mockLoadStyle() throws Exception {
        when(publisherService.loadStyle(any(String.class), any(String.class))).thenReturn(
                "MOCK_STYLE_TEST_LOADED");
    }

    final void mockProcessEPSGResult() throws Exception {
        when(publisherService.processEPSGResult(any(
                ProcessEPSGResultRequest.class))).thenReturn(
                        createInfoPreviewStore(37));
    }

    final void mockAnalyzeZIPEPSG() throws Exception {
        when(publisherService.analyzeZIPEPSG(any(String.class),
                any(String.class), any(File.class)))
                .thenReturn(createInfoPreviewStore(80));
    }

    final InfoPreviewStore createInfoPreviewStore(int size) {
        List<InfoPreview> previews = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            previews.add(new InfoPreview("MOCK_URL" + i, "WORKSPACE_MOCK" + i,
                    "LAYER_NAME_MOCK" + i, i, i, i, i, "EPSG:MOCK" + i,
                    "STYLE_NAME_MOCK" + i,
                    ((i % 2) == 0) ? Boolean.TRUE : Boolean.FALSE,
                    ((i % 2) == 0) ? Boolean.TRUE : Boolean.FALSE));
        }

        return new InfoPreviewStore(previews);
    }

    final LayerAttributeStore createLayerAttributeStore(int size) {
        List<LayerAttribute> layerAttributes = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            layerAttributes.add(new LayerAttribute("VALUE_MOCK_" + i,
                    "TYPE_MOCK_" + i));
        }

        return new LayerAttributeStore(layerAttributes);
    }
}
