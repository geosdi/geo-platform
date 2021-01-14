/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.wms.unmarshall.request;

import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKeyValuePair;
import org.geosdi.geoplatform.connector.server.request.kvp.WMSRequestKeyValuePair;
import org.geosdi.geoplatform.connector.server.request.kvp.WMSServiceKeyValuePair;
import org.geosdi.geoplatform.connector.server.request.kvp.WMSVersionKeyValuePair;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.wms.WMSGetFeatureInfoReaderFileLoaderTest.JACKSON_SUPPORT;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSGetMapBaseRequestUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetMapBaseRequestUnmarshallerTest.class);

    @Test
    public void a_unmarshallWMSBoundigBoxKeyValuePairTest() throws Exception {
        logger.info("#################WMS_BOUNDING_BOX_FROM_STRING : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"minx\" : -130.0,\n"
                        + "  \"miny\" : 24.0,\n"
                        + "  \"maxx\" : -66.0,\n"
                        + "  \"maxy\" : 50.0\n"
                        + "}"), GPWMSBoundingBox.class));
    }

    @Test
    public void b_unmarshallWMSGetMapKeyValuePairTest() throws Exception {
        logger.info("#################WMS_GET_MAP_FROM_STRING : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"boundingBox\" : {\n"
                        + "    \"minx\" : -130.0,\n"
                        + "    \"miny\" : 24.0,\n"
                        + "    \"maxx\" : -66.0,\n"
                        + "    \"maxy\" : 50.0\n"
                        + "  },\n"
                        + "  \"layers\" : [ \"topp:states\" ],\n"
                        + "  \"srs\" : \"EPSG:4326\",\n"
                        + "  \"width\" : \"550\",\n"
                        + "  \"height\" : \"250\",\n"
                        + "  \"extraParams\" : [ ]\n"
                        + "}"), GPWMSGetMapBaseRequest.class));
    }

    @Test
    public void c_unmarshallWMSGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"boundingBox\" : {\n"
                        + "    \"minx\" : -124.0,\n"
                        + "    \"miny\" : 21.0,\n"
                        + "    \"maxx\" : -66.0,\n"
                        + "    \"maxy\" : 49.0\n"
                        + "  },\n"
                        + "  \"layers\" : [ \"States\", \"Cities\" ],\n"
                        + "  \"srs\" : \"EPSG:4326\",\n"
                        + "  \"width\" : \"600\",\n"
                        + "  \"height\" : \"400\",\n"
                        + "  \"extraParams\" : [ {\n"
                        + "    \"key\" : \"SERVICE\",\n"
                        + "    \"value\" : \"WMS\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"VERSION\",\n"
                        + "    \"value\" : \"1.1.1\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"REQUEST\",\n"
                        + "    \"value\" : \"GetMap\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"FORMAT\",\n"
                        + "    \"value\" : \"image/png\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"SERVICENAME\",\n"
                        + "    \"value\" : \"myservice\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"\\nTRANSPARENT\",\n"
                        + "    \"value\" : \"TRUE\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"BGCOLOR\",\n"
                        + "    \"value\" : \"0xFF0000\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"EXCEPTIONS\",\n"
                        + "    \"value\" : \"application/vnd.ogc.se_blank\"\n"
                        + "  }, {\n"
                        + "    \"key\" : \"REASPECT\",\n"
                        + "    \"value\" : \"TRUE\"\n"
                        + "  } ]\n"
                        + "}"), GPWMSGetMapBaseRequest.class);
        logger.info("#################WMS_GET_MAP_FROM_STRING : {}\n", wmsGetMapBaseRequest);
    }

    @Test
    public void d_unmarshallWMSServiceKeyValuePairTest() throws Exception {
        GPWMSRequestKeyValuePair wmsRequestKeyValuePair = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader(" {\n"
                        + "    \"key\" : \"SERVICE\",\n"
                        + "    \"value\" : \"WMS\"\n"
                        + "  }"), GPWMSRequestKeyValuePair.class);
        assertTrue(wmsRequestKeyValuePair instanceof WMSServiceKeyValuePair);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@WMS_SERVICE_KEY_VALUE_PAIR_FROM_STRING : {}\n", wmsRequestKeyValuePair);
    }

    @Test
    public void e_unmarshallWMSVersionKeyValuePairTest() throws Exception {
        GPWMSRequestKeyValuePair wmsRequestKeyValuePair = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader(" {\n"
                        + "    \"key\" : \"VERSION\",\n"
                        + "    \"value\" : \"1.1.1\"\n"
                        + "  }"), GPWMSRequestKeyValuePair.class);
        assertTrue(wmsRequestKeyValuePair instanceof WMSVersionKeyValuePair);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@WMS_VERSION_KEY_VALUE_PAIR_FROM_STRING : {}\n", wmsRequestKeyValuePair);
    }

    @Test
    public void f_unmarshallWMSRequestKeyValuePairTest() throws Exception {
        GPWMSRequestKeyValuePair wmsRequestKeyValuePair = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new StringReader(" {\n"
                        + "    \"key\" : \"REQUEST\",\n"
                        + "    \"value\" : \"GetMap\"\n"
                        + "  }"), GPWMSRequestKeyValuePair.class);
        assertTrue(wmsRequestKeyValuePair instanceof WMSRequestKeyValuePair);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@WMS_REQUEST_KEY_VALUE_PAIR_FROM_STRING : {}\n", wmsRequestKeyValuePair);
    }
}