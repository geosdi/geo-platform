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

import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequestBuilder;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSKeyValuePairBuilder.GPWMSBaseKeyValuePairBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequestBuilder.builder;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSGetMapBaseRequestBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetMapBaseRequestBuilderTest.class);
    //
    private static final GPWMSBaseKeyValuePairBuilder<GPWMSGetMapBaseRequest, GPWMSGetMapBaseRequestBuilder> builder = builder();

    @Test
    public void a_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://150.145.141.180/geoserver/topp/wms?service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void b_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void c_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap&layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void d_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("&version=1.1.0&  &layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void e_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("&version=1.1.0&  &layers=,   ,,  ,   ,   ,,,   &bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void f_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void g_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://sampleserver1.arcgisonline.com/ArcGIS/services/Specialty/ESRI_StatesCitiesRivers_USA/MapServer/WMSServer?version=1.3.0&request=GetMap&CRS=CRS:84&bbox=-178.217598,18.924782,-66.969271,71.406235&width=760&height=360&layers=0&styles=defaul")
                .build());
    }

    @Test
    public void h_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://gisserver.domain.com:6080/arcgis/services/folder/service/ImageServer/WMSServer?REQUEST=GetMap&SERVICE=WMS&VERSION=1.3.0&LAYERS=0&STYLES=&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&CRS=EPSG:4326&bbox=-178.217598,18.924782,-66.969271,71.406235&width=760&height=360&TIME=1980/2010 (time from year 1980 to 2010)")
                .build());
    }

    @Test
    public void i_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=States,Cities&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png&SERVICENAME=myService&")
                .build());
    }

    @Test
    public void l_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=States,Cities&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png&SERVICENAME=myservice&\n" + "TRANSPARENT=TRUE&BGCOLOR=0xFF0000&EXCEPTIONS=application/vnd.ogc.se_blank&REASPECT=TRUE&")
                .build());
    }
}