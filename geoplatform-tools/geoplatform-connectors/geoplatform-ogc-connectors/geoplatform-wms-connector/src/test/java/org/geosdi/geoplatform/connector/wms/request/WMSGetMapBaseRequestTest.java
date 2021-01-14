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
package org.geosdi.geoplatform.connector.wms.request;

import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import static org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequestBuilder.builder;
import static org.geosdi.geoplatform.connector.wms.WMSGetFeatureInfoReaderFileLoaderTest.JACKSON_SUPPORT;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSGetMapBaseRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetMapBaseRequestTest.class);
    //
    private static final GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(-130d, 24d, -66d, 50d);

    @Test
    public void a_wmsBoundigBoxKeyValuePairTest() throws Exception {
        assertTrue(wmsBoundinBox.toWMSKeyValuePair().equalsIgnoreCase("BBOX=-130.0,24.0,-66.0,50.0"));
        logger.info("######################BOUNDING_BOX_KVP : {}\n", wmsBoundinBox.toWMSKeyValuePair());
    }

    @Test
    public void b_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("topp:states", null, "", "topp:states").collect(toList()),
                "EPSG:4326", "550", "250");
        assertTrue(wmsGetMapBaseRequest.toWMSKeyValuePair().equalsIgnoreCase("LAYERS=topp:states&SRS=EPSG:4326&BBOX=-130.0,24.0,-66.0,50.0&WIDTH=550&HEIGHT=250"));
        logger.info("#########################GET_MAP_KVP : {}\n", wmsGetMapBaseRequest.toWMSKeyValuePair());
    }

    @Test
    public void c_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("topp:states", null, "", "topp:states", "siti_protetti:zsc", "retenatura:zsc", "retenatura:zsc").collect(toList()),
                "EPSG:4326", "550", "250");
        assertTrue(wmsGetMapBaseRequest.toWMSKeyValuePair().equalsIgnoreCase("LAYERS=topp:states,siti_protetti:zsc,retenatura:zsc&SRS=EPSG:4326&BBOX=-130.0,24.0,-66.0,50.0&WIDTH=550&HEIGHT=250"));
        logger.info("#########################GET_MAP_KVP : {}\n", wmsGetMapBaseRequest.toWMSKeyValuePair());
    }

    @Test
    public void d_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("test", null, "prova", "", "topp:states", null, "", "topp:states", "siti_protetti:zsc", "retenatura:zsc", "retenatura:zsc").collect(toList()),
                "EPSG:4326", "550", "250");
        assertTrue(wmsGetMapBaseRequest.toWMSKeyValuePair().equalsIgnoreCase("LAYERS=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc&SRS=EPSG:4326&BBOX=-130.0,24.0,-66.0,50.0&WIDTH=550&HEIGHT=250"));
        logger.info("#########################GET_MAP_KVP : {}\n", wmsGetMapBaseRequest.toWMSKeyValuePair());
    }

    @Test
    public void e_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("test","test", null, "prova", "test", "", "topp:states", null, "prova", "topp:states", "siti_protetti:zsc", "retenatura:zsc", "retenatura:zsc").collect(toList()),
                "EPSG:32632", "550", "250");
        assertTrue(wmsGetMapBaseRequest.toWMSKeyValuePair().equalsIgnoreCase("LAYERS=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc&SRS=EPSG:32632&BBOX=-130.0,24.0,-66.0,50.0&WIDTH=550&HEIGHT=250"));
        logger.info("#########################GET_MAP_KVP : {}\n", wmsGetMapBaseRequest.toWMSKeyValuePair());
    }

    @Test(expected = IllegalArgumentException.class)
    public void f_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, of("","", null, "", "", "", "", null, "", "", "", "", "").collect(toList()),
                "EPSG:32632", "550", "250");
    }

    @Test(expected = IllegalArgumentException.class)
    public void g_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest( null, of("","", null, "", "", "", "", null, "", "", "", "", "").collect(toList()),
                "EPSG:32632", "550", "250");
    }

    @Test
    public void h_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = builder().withKeyValuePair(
                "SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=States,Cities&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png&SERVICENAME=myservice&\n" + "TRANSPARENT=TRUE&BGCOLOR=0xFF0000&EXCEPTIONS=application/vnd.ogc.se_blank&REASPECT=TRUE&")
                .build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GET_MAP_JSON_STRING : \n{}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(wmsGetMapBaseRequest));
    }
}
