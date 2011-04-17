/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform;

import java.text.ParseException;

import junit.framework.Assert;

import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.responce.ShortServer;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@TestExecutionListeners(value = {WSListenerServices.class})
public class WMSServiceTest extends ServiceTest {

    @Test
    public void testGetCapabilities() throws ParseException,
            ResourceNotFoundFault {

        ShortServer shortServer = geoPlatformService.getServer("http://dpc.geosdi.org/geoserver/wms?service=wms&version=1.1.1&request=GetCapabilities");

        Assert.assertNotNull(shortServer);

        logger.info("NUMBER OF LAYERS FOR DPC ********** "
                + geoPlatformService.getCapabilities(new RequestById(shortServer.getId())).getList().size());


        ShortServer shortServer1 = geoPlatformService.getServer("http://maps.telespazio.it/dpc/dpc-wms");

        Assert.assertNotNull(shortServer1);

        Assert.assertEquals(
                8,
                geoPlatformService.getCapabilities(new RequestById(shortServer1.getId())).getList().size());

        logger.info("NUMBER OF LAYERS FOR TELESPAZIO ********** "
                + geoPlatformService.getCapabilities(new RequestById(shortServer1.getId())).getList().size());

    }
}
