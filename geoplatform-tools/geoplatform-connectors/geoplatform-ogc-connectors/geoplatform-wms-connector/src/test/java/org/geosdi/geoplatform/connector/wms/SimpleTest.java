/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wms;

import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKeyValuePair;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKvpReader;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKvpReader.WMSRequestKvpReader;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.WMSVersion.forValue;
import static org.geotools.referencing.CRS.decode;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class SimpleTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void a_simpleTest() throws Exception {
        logger.info("################## {}", decode("EPSG:32632", TRUE));
    }

    @Test
    public void b_simpleTest() throws Exception {
        String value = "http://150.145.141.180/geoserver/topp/wms?service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((key, value1) -> logger.info("##########ENTRY : {} - VALUE : {}\n", key, value1));
    }

    @Test
    public void c_simpleTest() throws Exception {
        logger.info("#####################Version : {}\n", forValue("aa"));
        logger.info("#####################Version : {}\n", forValue("1.3.0"));
    }

    @Test
    public void d_simpleTest() throws Exception {
        String value = "https://www.geoportale.piemonte.it/visregpigo/?url=https:%2F%2Fgeomap.reteunitaria.piemonte.it%2Fws%2Faera%2Frp-01%2Faerawms%2Fwms_aera_limiti%3Fservice%3DWMS%26version%3D1.3%26request%3DgetCapabilities&type=wms&layer=&lang=en&title=&mdUrl=https:%2F%2Fwww.geoportale.piemonte.it%2Fgeonetwork%2Fsrv%2Fita%2Fcatalog.search%23%2Fmetadata%2Fr_piemon:bdc4262a-45b1-47eb-a128-30336d9fa654&mdUuid=r_piemon:bdc4262a-45b1-47eb-a128-30336d9fa654&mdId=18466";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void e_simpleTest() throws Exception {
        String value = "https://www.youtube.com/watch?v=Dnnfy4yOTaw&list=RDMM&start_radio=1&rv=GOB7fS2YK0A";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void f_simpleTest() throws Exception {
        String value = "https://radar.protezionecivile.it/#/pages/dashboard?update=e5799bd5-bebf-c581-d995-62cc00ef6469&zoom=6";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void g_simpleTest() throws Exception {
        String value = "https://geoportale.regione.lazio.it/geoserver/geonode/ows?service=WFS&version=1.0.0" +
                "&request=GetFeature&typeName=geonode:CBLN_Zona_operativa0" +
                "&maxFeatures=1000000&outputFormat=text%2Fxml%3B+subtype%3Dgml%2F3.1.1;k=test;v=p";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void h_simpleTest() throws Exception {
        String value = "https://geoportale.regione.lazio.it/geoserver/geonode/ows?service=WFS&version=1.0.0" +
                "&request=GetFeature&typeName=geonode:CBLN_Zona_operativa0" +
                "&maxFeatures=1000000&outputFormat=text%2Fxml%3B+subtype%3Dgml%2F3.1.1;k=test;v=p&p=val;s=type";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void i_simpleTest() throws Exception {
        String value = "https://geoportale.regione.lazio.it/geoserver/geonode/ows?service=WFS&version=1.0.0" +
                "&request=GetFeature&typeName=geonode:CBLN_Zona_operativa0;val=tot" +
                "&maxFeatures=1000000&outputFormat=text%2Fxml%3B+subtype%3Dgml%2F3.1.1;k=test;v=p&p=val;s=type";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void l_simpleTest() throws Exception {
        String value = "http://it.wikipedia.org/application/new_user/registration_form?nome=Mario&cognome=Rossi&ID_utente=M_Rossi.";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }

    @Test
    public void m_simpleTest() throws Exception {
        String value = "nome=Mario&cognome=Rossi&ID_utente=M_Rossi.";
        GPWMSRequestKvpReader wmsRequestKvpReader = new WMSRequestKvpReader();
        Map<String, GPWMSRequestKeyValuePair> values = wmsRequestKvpReader.read(value);
        values.forEach((k, v) -> logger.info("##########ENTRY : {} - VALUE : {}\n", k, v));
    }
}