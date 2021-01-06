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

import org.geosdi.geoplatform.connectors.ws.publish.soap.GPPublisherClientTestConnector;
import org.geosdi.geoplatform.publisher.PublisherBaseTest;
import org.geosdi.geoplatform.request.ProcessEPSGResultRequest;
import org.geosdi.geoplatform.request.PublishLayerRequest;
import org.geosdi.geoplatform.request.PublishRequest;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext-PublisherSoapTest.xml"})
@ActiveProfiles(profiles = {"dev"})
public abstract class PublisherSoapTest extends PublisherBaseTest {

    private static final AtomicBoolean flag = new AtomicBoolean(FALSE);
    //
    @Value("configurator{webservice_test_publisher_endpoint_address}")
    private String basicSoapAddress;
    @Resource(name = "gpPublisherSOAPClient")
    private GPPublisherClientTestConnector gpPublisherSOAPClient;

    @Before
    public void setUp() {
        if (flag.compareAndSet(FALSE, TRUE)) {
            logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@ SetUp {} @@@@@@@@@@@@@@@@@@@@\n", getClass().getSimpleName());
            PublisherSoapServerUtils.gpPublisherClient = gpPublisherSOAPClient.getEndpointService();
            PublisherSoapServerUtils.server = GPPublisherSoapServerConfig.gpPublisherSoapServer(publisherService, basicSoapAddress,
                    serverLogInInterceptor, serverLogOutInterceptor);
            PublisherSoapServerUtils.server.start();
            logger.debug("\n\n\t@@@@@@@@@@@@@@@@@@@@@ Start GP_PUBLISHER_SOAP Server @@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        PublisherSoapServerUtils.server.stop();
        logger.debug("\n\n\t@@@@@@@@@@@@@@@@@@@@@ Stop GP_PUBLISHER_SOAP Server @@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
    }

    @Override
    protected final void mockPublishAllofPreview() throws Exception {
        when(publisherService.publishAllofPreview(any(PublishRequest.class))).thenReturn(FALSE);
    }

    @Override
    protected final void mockPublish() throws Exception {
        when(publisherService.publish(any(PublishLayerRequest.class))).thenReturn(FALSE);
    }

    @Override
    protected final void mockGetPreviewDataStores() throws Exception {
        when(publisherService.getPreviewDataStores(any(String.class))).thenReturn(createInfoPreviewStore(25));
    }

    @Override
    protected final void mockAnalyzeTIFInPreview() throws Exception {
        when(publisherService.analyzeTIFInPreview(any(), any(),
                any(), any())).thenReturn(new InfoPreview(
                "DATA_STORE_MOCK_MOKITO_SOAP",
                "MESSAGE_MOCK_MOKITO_SOAP"));
    }

    @Override
    protected final void mockExistsStyle() throws Exception {
        when(publisherService.existsStyle(any(String.class))).thenReturn(
                FALSE);
    }

    @Override
    protected final void mockPutStyle() throws Exception {
        when(publisherService.updateStyle(any(String.class), any(String.class),
                any(Boolean.class))).thenReturn(FALSE);
    }

    @Override
    protected final void mockPublishStyle() throws Exception {
        when(publisherService.publishStyle(any(String.class),
                any(String.class), any(Boolean.class))).thenReturn(FALSE);
    }

    @Override
    protected final void mockDescribeFeatureType() throws Exception {
        when(publisherService.describeFeatureType(any(String.class))).thenReturn(
                createLayerAttributeStore(20));
    }

    @Override
    protected final void mockLoadStyle() throws Exception {
        when(publisherService.loadStyle(any(String.class), any(String.class))).thenReturn(
                "MOCK_STYLE_TEST_LOADED_SOAP");
    }

    @Override
    protected final void mockProcessEPSGResult() throws Exception {
        when(publisherService.processEPSGResult(any(
                ProcessEPSGResultRequest.class))).thenReturn(
                createInfoPreviewStore(17));
    }

    @Override
    protected final void mockAnalyzeZIPEPSG() throws Exception {
        when(publisherService.analyzeZIPEPSG(any(), any(), any(), any()))
                .thenReturn(createInfoPreviewStore(60));
    }
}