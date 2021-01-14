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
package org.geosdi.geoplatform.wms;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoElement;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoBoundingBox;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoPoint;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.io.File.separator;
import static java.lang.Thread.currentThread;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.services.builder.WMSGetFeatureInfoResponseBuilder.wmsGetFeatureInfoResponseBuilder;
import static org.geosdi.geoplatform.services.request.WMSGetFeatureInfoResponseFormat.FEATURE_STORE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSGetFeatureInfoResponseBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetFeatureInfoResponseBuilderTest.class);
    //
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    @Test
    public void a_loadWMSGetFeatureInfoResponseBuilderTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = new GPWMSGetFeatureInfoRequest();
        request.setCrs("EPSG:4326");
        request.setWidth("101");
        request.setHeight("101");
        request.setBoundingBox(new WMSGetFeatureInfoBoundingBox() {
            {
                super.setMinx(14.403741359710693);
                super.setMiny(41.891523599624634);
                super.setMaxx(14.681146144866943);
                super.setMaxy(42.168928384780884);
            }
        });
        request.setPoint(new WMSGetFeatureInfoPoint() {
            {
                super.setX(50);
                super.setY(50);
            }
        });
        request.setFormat(FEATURE_STORE);
        GPWMSGetFeatureInfoElement featureInfoElement = new GPWMSGetFeatureInfoElement();
        featureInfoElement.setLayers(asList("PNSRS:ABR_4_5_comuni_aff_COM"));
        featureInfoElement.setWmsServerURL("https://servizi.protezionecivile.it/geoserver/PNSRS/wms");
        request.setWmsFeatureInfoElements(asList(featureInfoElement));
        logger.info("#####################{}\n", wmsGetFeatureInfoResponseBuilder()
                .withRequest(request)
                .build());
    }

    @Test
    public void b_loadWMSGetFeatureInfoResponseBuilderTest() throws Exception {
        InputStream inputStream = currentThread().getContextClassLoader().getResourceAsStream(of("files", "GPWMSGetFeatureInfoRequest1.json")
                .collect(joining(separator)));
        checkArgument(inputStream != null, "The File GPWMSGetFeatureInfoRequest1.json is not present on classpath.");
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(inputStream, GPWMSGetFeatureInfoRequest.class);
        logger.info("########################WMS_GET_FEATURE_INFO_REQUEST : {}\n", wmsGetFeatureInfoRequest);
        fromIterable(wmsGetFeatureInfoRequest.getWmsFeatureInfoElements())
                .doOnComplete(() -> logger.info("##################RX Terminates its task.\n"))
                .subscribe(v -> logger.info("{}\n", Arrays.toString(v.toLayers())), e -> e.printStackTrace());
        logger.info("#####################{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(wmsGetFeatureInfoResponseBuilder()
                .withRequest(wmsGetFeatureInfoRequest)
                .build()));
    }
}