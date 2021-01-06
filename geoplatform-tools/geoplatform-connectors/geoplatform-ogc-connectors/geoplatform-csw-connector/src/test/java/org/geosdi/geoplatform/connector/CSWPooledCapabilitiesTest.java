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
package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.pool.builder.GPCSWConnectorBuilderPool;
import org.geosdi.geoplatform.connector.server.request.CatalogGetCapabilitiesRequest;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.xml.csw.v202.CapabilitiesType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
    "classpath:applicationContext-Logger.xml"})
public class CSWPooledCapabilitiesTest {

    @GeoPlatformLog
    private static Logger logger;

    @Ignore(value = "Server is DOWN")
    @Test
    public void cswPooledIspra202GetCapabilitiesTest() throws Exception {
        GPCatalogConnectorStore serverConnector = GPCSWConnectorBuilderPool
                .newConnector()
                .withServerUrl(new URL("http://www.geoportale.isprambiente.it"
                                + "/geoportale/csw?request=GetCapabilities"
                                + "&service=CSW&version=2.0.2")).build();
        CatalogGetCapabilitiesRequest<CapabilitiesType> request = serverConnector.createGetCapabilitiesRequest();
        logger.info("\n\n#######################CSW_GET_CAP_RESPONSE_AS_STRING : {}\n\n", request.getResponseAsString());
    }

    @Test
    public void cswPooledGeoSDI202GetCapabilitiesTest() throws Exception {
        GPCatalogConnectorStore serverConnector = GPCSWConnectorBuilderPool
                .newConnector()
                .withServerUrl(new URL("http://catalog.geosdi.org/geonetwork/"
                                + "srv/eng/csw?request=GetCapabilities&service=CSW"
                                + "&version=2.0.2"))
                .build();
        CatalogGetCapabilitiesRequest<CapabilitiesType> request = serverConnector.createGetCapabilitiesRequest();
        logger.info("\n\n#######################CSW_GET_CAP_RESPONSE_AS_STRING : {}\n\n", request.getResponseAsString());
    }
}