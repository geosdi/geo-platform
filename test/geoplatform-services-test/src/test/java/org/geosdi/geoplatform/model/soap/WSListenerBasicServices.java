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
package org.geosdi.geoplatform.model.soap;

import org.apache.cxf.jaxws.EndpointImpl;
import org.geosdi.geoplatform.connectors.ws.basic.soap.GPBasicWSClientTestConnector;
import org.geosdi.geoplatform.cxf.bus.GPSpringBusConfigurator;
import org.geosdi.geoplatform.model.BaseGPListenerServices;
import org.geosdi.geoplatform.model.ServiceTest;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.springframework.test.context.TestContext;

import javax.xml.ws.Endpoint;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
class WSListenerBasicServices extends BaseGPListenerServices {

    private GPBasicWSClientTestConnector wsClientConnector;
    private GeoPlatformService gpWSClient;
    private EndpointImpl gpWSClientImpl;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        super.beforeTestClass(testContext);
        wsClientConnector = (GPBasicWSClientTestConnector) appContext.getBean("gpWSClient");
        assertNotNull("geoPlatformWSClient is NULL", wsClientConnector);
        gpWSClient = wsClientConnector.getEndpointService();
        GeoPlatformService geoPlatformService = (GeoPlatformService) appContext.getBean("geoPlatformService");
        assertNotNull("geoPlatformService is NULL", geoPlatformService);
        appContext.getBean(GPSpringBusConfigurator.class).createBus();
        String wsServerAddress = wsClientConnector.getAddress();
        this.gpWSClientImpl = (EndpointImpl) Endpoint.publish(wsServerAddress, geoPlatformService);
        logger.info("\n\t@@@ Server ready... @@@");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        logger.info("\n\t@@@ WSListenerServices.prepareTestInstance @@@");

        ServiceTest testInstance = (ServiceTest) testContext.getTestInstance();
        testInstance.setGeoplatformServiceClient(gpWSClient);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        logger.info("\n\t@@@ WSListenerServices.afterTestClass @@@");
        this.gpWSClientImpl.stop();
    }
}
