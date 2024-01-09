/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wps;

import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedWriter;
import java.nio.file.Files;

import static java.nio.file.Paths.get;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSGetCapabilitiesTest extends WPSTestConfigurator {

    @Ignore(value = "Server id DOWN")
    @Test
    public void wpsGetCapabilitiesV100Test() throws Exception {
        WPSGetCapabilitiesRequest<WPSCapabilitiesType> request = wpsServerConnector.createGetCapabilitiesRequest();
        String responseAsString = request.formatResponseAsString(2);
        logger.info("WPS_100 GetCapabilities @@@@@@@@@@@@@@@@@@@@@@@ \n{}\n", responseAsString);
        try (BufferedWriter writer = Files.newBufferedWriter(get("target/WPSGetCapabilitiesV100.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Ignore(value = "Server id DOWN")
    @Test
    public void wpsHttpsGetCapabilitiesV100Test() throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        WPSGetCapabilitiesRequest<WPSCapabilitiesType> request = wpsHttpsServerConnector.createGetCapabilitiesRequest();
        String responseAsString = request.formatResponseAsString(2);
        logger.info("WPS_100 Https GetCapabilities @@@@@@@@@@@@@@@@@@@@@@@ \n{}\n", responseAsString);
        try (BufferedWriter writer = Files.newBufferedWriter(get("target/WPSHttpsGetCapabilitiesV100.xml"))) {
            writer.write(responseAsString);
        }
    }
}
