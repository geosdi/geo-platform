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
package org.geosdi.geoplatform.ws.wfs;

import org.apache.cxf.jaxws.EndpointImpl;
import org.geosdi.geoplatform.connectors.ws.wfs.GPWFSClientTestConnector;
import org.geosdi.geoplatform.cxf.bus.GPSpringBusConfigurator;
import org.geosdi.geoplatform.services.GPWFSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import javax.xml.ws.Endpoint;

import static org.junit.Assert.assertNotNull;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSListenerServices implements TestExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GPWFSService wfsService;
    private EndpointImpl wfsServiceImpl;
    private String addressDatastore;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        logger.info("\n\t@@@ WFSListenerServices.beforeTestClass @@@");

        ApplicationContext appContext = testContext.getApplicationContext();

        GPWFSClientTestConnector wfsClientConnector = appContext.getBean("wfsClient", GPWFSClientTestConnector.class);
        assertNotNull("wfsClient is NULL", wfsClientConnector);
        wfsService = wfsClientConnector.getEndpointService();
        assertNotNull("wfsService is NULL", wfsService);

        addressDatastore = appContext.getBean("addressDatastore", String.class);
        assertNotNull("addressDatastore is NULL", addressDatastore);

        GPWFSService geoPlatformWFSService = appContext.getBean("wfsService", GPWFSService.class);
        assertNotNull("wfsService is NULL", geoPlatformWFSService);

        appContext.getBean(GPSpringBusConfigurator.class).createBus();

        String wfsServerAddress = wfsClientConnector.getAddress();
        this.wfsServiceImpl = (EndpointImpl) Endpoint.publish(wfsServerAddress, geoPlatformWFSService);

        logger.info("\n\t@@@ Server ready... @@@");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        logger.info("\n\t@@@ WFSListenerServices.prepareTestInstance @@@");

        WFSAbstractTest testInstance = (WFSAbstractTest) testContext.getTestInstance();
        testInstance.setWfsService(wfsService);
        testInstance.setAddressDatastore(addressDatastore);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        logger.info("\n\t@@@ WFSListenerServices.afterTestClass @@@");
        this.wfsServiceImpl.stop();
    }

}
