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
package org.geosdi.geoplatform.wms.request.mapper;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;
import java.util.Arrays;

import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.geosdi.geoplatform.wms.request.validator.GPWMSRequestValidatorTest.createWMSGetFeatureRequest;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSRequestMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSRequestMapperTest.class);
    //
    private static final GPJacksonSupport jacksonSupport = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE)
            .configure(NON_NULL);

    @Test
    public void a_writeGPWMSGetFeatureInfoRequestAsStringTest() throws Exception {
        logger.info("##################################GP_WMS_GET_FEATURE_INFO_REQUEST_AS_STRING : \n{}\n",
                jacksonSupport.getDefaultMapper().writeValueAsString(createWMSGetFeatureRequest()));
    }

    @Test
    public void b_readGPWMSGetFeatureInfoRequestFromStringTest() throws Exception {
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = jacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"lang\" : \"en\",\n" +
                        "  \"crs\" : \"EPSG:4326\",\n" +
                        "  \"width\" : \"256\",\n" +
                        "  \"height\" : \"256\",\n" +
                        "  \"boundingBox\" : {\n" +
                        "    \"minx\" : -82.06792594360869,\n" +
                        "    \"miny\" : 35.02655390844236,\n" +
                        "    \"maxx\" : -82.06781624389134,\n" +
                        "    \"maxy\" : 35.02664373997006\n" +
                        "  },\n" +
                        "  \"point\" : {\n" +
                        "    \"x\" : 98,\n" +
                        "    \"y\" : 34\n" +
                        "  },\n" +
                        "  \"wmsFeatureInfoElements\" : [ {\n" +
                        "    \"wmsServerURL\" : \"http://150.145.141.180/geoserver/wms\",\n" +
                        "    \"layers\" : [ \"topp:states\", \"topp:states\", \"siti_protetti:zsc\", \"retenatura:zsc\", \"retenatura:zsc\" ]\n" +
                        "  }, {\n" +
                        "    \"wmsServerURL\" : \"http://150.145.141.180/geoserver/wms\",\n" +
                        "    \"layers\" : [ \"topp:states\", \"topp:states\", \"siti_protetti:zsc\", \"retenatura:zsc\", \"retenatura:zsc\" ]\n" +
                        "  } ],\n" +
                        "  \"format\" : \"GEOJSON\"\n" +
                        "}"), GPWMSGetFeatureInfoRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WMS_GET_FEATURE_INFO_REQUEST_FROM_STRING : {}\n", wmsGetFeatureInfoRequest);
        fromIterable(wmsGetFeatureInfoRequest.getWmsFeatureInfoElements())
                .doOnComplete(() -> logger.info("##################RX Terminates its task.\n"))
                .subscribe(v -> logger.info("{}\n", Arrays.toString(v.toLayers())), Throwable::printStackTrace);
    }

    @Test
    public void c_writeGPWMSGetFeatureInfoRequestAsFileTest() throws Exception {
        jacksonSupport.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GPWMSGetFeatureInfoRequest.json")
                .collect(joining(separator))), createWMSGetFeatureRequest());
    }

    @Test
    public void d_readGPWMSGetFeatureInfoRequestFromFileTest() throws Exception {
        String filePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "files", "GPWMSGetFeatureInfoRequest.json")
                .collect(joining(separator));
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = jacksonSupport.getDefaultMapper()
                .readValue(new File(filePath), GPWMSGetFeatureInfoRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WMS_GET_FEATURE_INFO_REQUEST_FROM_FILE : {}\n", wmsGetFeatureInfoRequest);
    }

    @Test
    public void e_readGPWMSGetFeatureInfoRequestFromFileTest() throws Exception {
        String filePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "files", "GPWMSGetFeatureInfoRequest1.json")
                .collect(joining(separator));
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = jacksonSupport.getDefaultMapper()
                .readValue(new File(filePath), GPWMSGetFeatureInfoRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WMS_GET_FEATURE_INFO_REQUEST_FROM_FILE : {}\n", wmsGetFeatureInfoRequest);
        fromIterable(wmsGetFeatureInfoRequest.getWmsFeatureInfoElements())
                .doOnComplete(() -> logger.info("##################RX Terminates its task.\n"))
                .subscribe(v -> logger.info("{}\n", Arrays.toString(v.toLayers())), e -> e.printStackTrace());
    }

    @Test
    public void f_readGPWMSGetFeatureInfoRequestFromFileTest() throws Exception {
        String filePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "files", "GPWMSGetFeatureInfoRequest2.json")
                .collect(joining(separator));
        GPWMSGetFeatureInfoRequest wmsGetFeatureInfoRequest = jacksonSupport.getDefaultMapper()
                .readValue(new File(filePath), GPWMSGetFeatureInfoRequest.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@GP_WMS_GET_FEATURE_INFO_REQUEST_FROM_FILE : {}\n", wmsGetFeatureInfoRequest);
        fromIterable(wmsGetFeatureInfoRequest.getWmsFeatureInfoElements())
                .doOnComplete(() -> logger.info("##################RX Terminates its task.\n"))
                .subscribe(v -> logger.info("{}\n", Arrays.toString(v.toLayers())), e -> e.printStackTrace());
    }
}