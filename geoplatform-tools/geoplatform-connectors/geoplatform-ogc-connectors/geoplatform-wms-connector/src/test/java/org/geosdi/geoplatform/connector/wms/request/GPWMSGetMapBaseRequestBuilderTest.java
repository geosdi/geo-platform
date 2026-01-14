/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
    public void a_a_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://150.145.141.180/geoserver/topp/wms?service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void a_b_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void a_c_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap&layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void a_d_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("&version=1.1.0&  &layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void a_e_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("&version=1.1.0&  &layers=,   ,,  ,   ,   ,,,   &bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void a_f_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers")
                .build());
    }

    @Test
    public void a_g_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://sampleserver1.arcgisonline.com/ArcGIS/services/Specialty/ESRI_StatesCitiesRivers_USA/MapServer/WMSServer?version=1.3.0&request=GetMap&CRS=CRS:84&bbox=-178.217598,18.924782,-66.969271,71.406235&width=760&height=360&layers=0&styles=defaul")
                .build());
    }

    @Test
    public void a_h_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://gisserver.domain.com:6080/arcgis/services/folder/service/ImageServer/WMSServer?REQUEST=GetMap&SERVICE=WMS&VERSION=1.3.0&LAYERS=0&STYLES=&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&CRS=EPSG:4326&bbox=-178.217598,18.924782,-66.969271,71.406235&width=760&height=360&TIME=1980/2010 (time from year 1980 to 2010)")
                .build());
    }

    @Test
    public void a_i_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=States,Cities&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png&SERVICENAME=myService&")
                .build());
    }

    @Test
    public void a_l_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=States,Cities&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png&SERVICENAME=myservice&\n"
                        + "TRANSPARENT=TRUE&BGCOLOR=0xFF0000&EXCEPTIONS=application/vnd.ogc.se_blank&REASPECT=TRUE&")
                .build());
    }

    @Test
    public void a_m_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://150.145.141.92/geoserver/topp/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST" +
                        "=GetFeatureInfo&FORMAT=image%2Fpng&TRANSPARENT=true&QUERY_LAYERS=topp%3Atasmania_cities&STYLES&LAYERS" +
                        "=topp%3Atasmania_cities&exceptions=application%2Fvnd.ogc.se_inimage&INFO_FORMAT=text%2Fhtml&FEATURE_COUNT" +
                        "=50&X=50&Y=50&SRS=EPSG%3A4326&WIDTH=101&HEIGHT=101&BBOX=147.00470186769962%2C-43.117031436413534%2C147.55951143801212%2C" +
                        "-42.562221866101034")
                .build());
    }

    @Test
    public void a_n_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://sit2.regione.campania.it/geoserver/RegioneCampania.Cartografia.Tematica/" +
                        "wms?service=WMS&version=1.1.0&request=GetMap&layers=RegioneCampania.Cartografia.Tematica:sitdbo_curve_livello_25m" +
                        "&styles=&bbox=394273.34375,4426208.0,571791.3125,4601018.0&width=768&height=756" +
                        "&srs=EPSG:3045&format=application/openlayers")
                .build());
    }

    @Test
    public void a_o_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://sit2.regione.campania.it/geoserver/RegioneCampania.Cartografia.Tematica/wms" +
                        "?service=WMS&version=1.1.0&request=GetMap&layers=RegioneCampania.Cartografia.Tematica:sitdbo_retegeodeticaregionale" +
                        "&styles=&bbox=395940.25,4426882.0,568596.4375,4598768.5&width=768&height=764" +
                        "&srs=EPSG:3045&format=application/openlayers")
                .build());
    }

    @Test
    public void a_p_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://sit2.regione.campania.it/geoserver/RegioneCampania.Catalogo/wms" +
                        "?service=WMS&version=1.1.0&request=GetMap&layers=RegioneCampania.Catalogo:sitdbo_bio_italy" +
                        "&styles=&bbox=395405.6875,4425042.0,568193.4375,4596016.0&width=768&height=759" +
                        "&srs=EPSG:3045&format=application/openlayers&test=prova")
                .build());
    }

    @Test
    public void a_q_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://sit2.regione.campania.it/geoserver/RegioneCampania.Catalogo/wms" +
                        "?service=WMS&version=1.1.0&request=GetMap&layers=RegioneCampania.Catalogo:sitdbo_corine_land_cover_90" +
                        "&styles=&bbox=395346.3125,4426030.5,569392.125,4596345.5&width=768&height=751&srs=EPSG:3045" +
                        "&format=application/openlayers")
                .build());
    }

    @Test
    public void a_r_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://mappe.regione.toscana.it/geoserver/extra_rt_webgis/wms" +
                        "?service=WMS&version=1.1.0&request=GetMap&layers=extra_rt_webgis:elem_ridotto_2&styles=" +
                        "&bbox=1557289.25,4679916.5,1771408.5,4924609.5&width=672&height=768" +
                        "&srs=EPSG:3003&format=application/openlayers")
                .build());
    }

    @Test
    public void a_s_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap" +
                        "&layers=Ospedale_maggiore:cessato_punti&styles=&bbox=8.920043852690128,45.323153911514," +
                        "8.934401324999556,45.33530341761082&width=512&height=433" +
                        "&srs=EPSG:4326&format=application/openlayers&test=prova")
                .build());
    }

    @Test
    public void a_t_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("service=WMS&version=1.1.0&request=GetMap" +
                        "&layers=Ospedale_maggiore:punti&styles=&bbox=493440.1733799758,5018480.796821868," +
                        "499690.55666285375,5024741.973964897&width=511&height=512" +
                        "&srs=EPSG:32632&format=application/openlayers")
                .build());
    }

    @Test
    public void a_u_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=Oceans,Countries,Cities" +
                        "&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png&")
                .build());
    }

    @Test
    public void a_v_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("SSERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&LAYERS=States,Cities" +
                        "&STYLES=&SRS=EPSG:4326&BBOX=-124,21,-66,49&WIDTH=600&HEIGHT=400&FORMAT=image/png" +
                        "&SERVICENAME=myservice&TRANSPARENT=TRUE&BGCOLOR=0xFF0000&EXCEPTIONS=application/vnd.ogc.se_blank" +
                        "&REASPECT=TRUE&")
                .build());
    }

    @Test
    public void a_w_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("http://your.server.address/wms/liegenschaftsentwaesserung/abwasser_werkplan" +
                        "?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&LAYERS=Haltungen,Normschacht,Spezialbauwerke" +
                        "&STYLES=&CRS=EPSG%3A21781&BBOX=696136.28844801,245797.12108743,696318.91114315," +
                        "245939.25832905&WIDTH=1042&HEIGHT=811&FORMAT=application/dxf&FORMAT_OPTIONS=MODE:SYMBOLLAYERSYMBOLOGY;" +
                        "SCALE:250&FILE_NAME=werkplan_abwasser.dxf")
                .build());
    }

    @Test
    public void a_z_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://rsdi.regione.basilicata.it/rbgeoserver2016/atc/wms?" +
                        "service=WMS&version=1.1.0&request=GetMap&layers=atc:atc_basilicata&styles=&bbox=528246.125," +
                        "4416604.0,658406.625,4554652.5&width=724&height=768&srs=EPSG:32633&format=application/openlayers")
                .build());
    }

    @Test
    public void b_a_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://webgis.regione.sardegna.it/geoserver/ppr2006/wms?" +
                        "service=WMS&version=1.1.0&request=GetMap&layers=ppr2006%3AALBERIMONUMENTALI" +
                        "&bbox=1451316.0%2C4311236.0%2C1566149.0%2C4562852.0&width=350&height=768" +
                        "&srs=EPSG%3A3003&format=application/openlayers")
                .build());
    }

    @Test
    public void b_b_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://wms.cfr.toscana.it/geoserver/tmp/wms?service=WMS" +
                        "&version=1.1.0&request=GetMap&layers=tmp%3Asitc_asl" +
                        "&bbox=1554750.625%2C4678325.5%2C1771722.875%2C4924792.0&width=676" +
                        "&height=768&srs=EPSG%3A3003&format=application/openlayers&p=fake")
                .build());
    }

    @Test
    public void b_c_wmsGetMapBaseRequestBuilderTest() throws Exception {
        logger.info("#####################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder
                .withKeyValuePair("https://webgis.regione.sardegna.it/geoserver/ras/wms?service=WMS" +
                        "&version=1.1.0&request=GetMap&layers=ras%3AIDT_FV04G_QUADRO_UNIONE" +
                        "&bbox=1426638.0%2C4301311.0%2C1570229.0%2C4573602.5&width=404&height=768" +
                        "&srs=EPSG%3A3003&format=application/openlayers")
                .build());
    }
}