/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.model.rest;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.model.soap.ServiceWMSTest;
import org.geosdi.geoplatform.request.RequestByID;
import org.geosdi.geoplatform.response.ServerDTO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
        "classpath*:applicationContext.xml"})
@TestExecutionListeners(value = {RSListenerWMSService.class})
@ActiveProfiles(profiles = {"dev"})
public class WMSRestTest extends ServiceWMSTest {

    @Test
    public void testRSWMSClient() {
        Assert.assertNotNull(gpWMSClient);
    }

    @Test
    public void testRestGetCapabilities() throws ParseException, ResourceNotFoundFault {
        ServerDTO serverDTO = gpWMSClient.getShortServer(serverUrlGeoSDI);
        Assert.assertNotNull(serverDTO);

        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@ SERVER_DTO @@@@@@@@@@@@@@@@@@\n{}", serverDTO);

        serverDTO = gpWMSClient.getCapabilities(serverDTO.getServerUrl(),
                new RequestByID(serverDTO.getId()), null, null);
        logger.debug("\n*** NUMBER OF LAYERS FOR geoSDI Server {} ***",
                serverDTO.getLayerList().size());
    }

    @Ignore(value = "Server is DOWN.")
    @Test
    public void testRestExternalGetCapabilities() throws Exception {
        ServerDTO serverDTO = gpWMSClient.getCapabilities("http://sgi1.isprambiente.it/arcgis/services/servizi/" +
                        "geologia500k/MapServer/WMSServer?request=GetCapabilities&service=WMS", new RequestByID(),
                null, null);
        logger.info("###############################FOUND : {}\n", serverDTO);
    }
}