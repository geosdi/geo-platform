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
package org.geosdi.geoplatform.catalogws.beans;

import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.geosdi.geoplatform.services.GPCatalogFinderService;
import org.geosdi.geoplatform.support.cxf.server.ServerInterceptorStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPCatalogEndPointImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private Endpoint endpoint;
    private Bus bus;
    //
    private @Value("configurator{webservice_test_catalogfinder_endpoint_address}")
    String serverAddress;
    //
    @Autowired
    private GPCatalogFinderService gpCatalogFinderService;
    //
    @Autowired
    private ServerInterceptorStrategyFactory serverInterceptorStrategyFactory;

    @PostConstruct
    public void init() {
        SpringBusFactory bf = new SpringBusFactory();
        bus = bf.createBus();

        bus.getInInterceptors().add(new LoggingInInterceptor());
        bus.getOutInterceptors().add(new LoggingOutInterceptor());
//
//        bus.getInInterceptors().add(serverInterceptorStrategyFactory.getSecurityInInterceptor());
//        bus.getOutInterceptors().add(serverInterceptorStrategyFactory.getSecurityOutInterceptor());

        SpringBusFactory.setDefaultBus(bus);
        endpoint = Endpoint.create(gpCatalogFinderService);

        logger.info("\n\t@@@ Catalog Finder End Point Created @@@");
    }

    /**
     * Start GeoPlatform Catalog Finder WS Service
     */
    public void startServer() {
        if (!endpoint.isPublished()) {
            endpoint.publish(serverAddress);
            logger.info("\n\t@@@ Catalog Finder End Point Ready @@@");
        }
    }

    /**
     * Stop Catalog Finder WS Service
     * 
     * @throws Exception 
     */
    public void stopServer() throws Exception {
        logger.info("\n\t@@@ Catalog Finder WS Service Shut Down @@@");

        endpoint.stop();
        bus.shutdown(true);
        // Wait to be sure that the endpoint was shutdown properly
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
    }
}
