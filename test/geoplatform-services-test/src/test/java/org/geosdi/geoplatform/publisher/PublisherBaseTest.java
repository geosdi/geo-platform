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
package org.geosdi.geoplatform.publisher;

import com.google.common.collect.Lists;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.InfoPreviewStore;
import org.geosdi.geoplatform.responce.LayerAttribute;
import org.geosdi.geoplatform.responce.LayerAttributeStore;
import org.geosdi.geoplatform.services.GPPublisherService;
import org.geosdi.geoplatform.services.GPPublisherServiceImpl;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PublisherBaseTest {

    protected static final Logger logger = LoggerFactory.getLogger(PublisherBaseTest.class);
    //
    protected static GPPublisherService publisherService;
    //
    @Resource(name = "serverLoggingInInterceptorBean")
    protected LoggingInInterceptor serverLogInInterceptor;
    @Resource(name = "serverLoggingOutInterceptorBean")
    protected LoggingOutInterceptor serverLogOutInterceptor;

    @BeforeClass
    public static void beforeClass() {
        publisherService = mock(GPPublisherServiceImpl.class);
    }

    protected abstract void mockPublishAllofPreview() throws Exception;

    protected abstract void mockPublish() throws Exception;

    protected abstract void mockGetPreviewDataStores() throws Exception;

    protected abstract void mockAnalyzeTIFInPreview() throws Exception;

    protected abstract void mockExistsStyle() throws Exception;

    protected abstract void mockPutStyle() throws Exception;

    protected abstract void mockPublishStyle() throws Exception;

    protected abstract void mockDescribeFeatureType() throws Exception;

    protected abstract void mockLoadStyle() throws Exception;

    protected abstract void mockProcessEPSGResult() throws Exception;

    protected abstract void mockAnalyzeZIPEPSG() throws Exception;

    protected final InfoPreviewStore createInfoPreviewStore(int size) {
        List<InfoPreview> previews = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            previews.add(new InfoPreview("MOCK_URL" + i, "WORKSPACE_MOCK" + i,
                    "LAYER_NAME_MOCK" + i, i, i, i, i, "EPSG:MOCK" + i,
                    "STYLE_NAME_MOCK" + i,
                    ((i % 2) == 0) ? Boolean.TRUE : Boolean.FALSE,
                    ((i % 2) == 0) ? Lists.<LayerPublishAction>newArrayList(
                                    LayerPublishAction.APPEND, LayerPublishAction.OVERRIDE,
                                    LayerPublishAction.RENAME) : null));
        }

        return new InfoPreviewStore(previews);
    }

    protected final LayerAttributeStore createLayerAttributeStore(int size) {
        List<LayerAttribute> layerAttributes = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            layerAttributes.add(new LayerAttribute("VALUE_MOCK_" + i,
                    "TYPE_MOCK_" + i));
        }

        return new LayerAttributeStore(layerAttributes);
    }

}
