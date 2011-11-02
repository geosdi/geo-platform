//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
//</editor-fold>
package org.geosdi.geoplatform;

import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.geosdi.geoplatform.cxf.GeoPlatformWSClient;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.geosdi.geoplatform.services.ServerKeystorePasswordCallback;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class WSListenerServices implements TestExecutionListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GeoPlatformService gpWSClient = null;
    private Endpoint endpoint = null;
    private Bus bus = null;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        logger.trace("\n\t@@@ WSListenerServices.beforeTestClass @@@");

        // Client must be created before the Endpoint is created
        gpWSClient = ((GeoPlatformWSClient) testContext.getApplicationContext().getBean("gpWSClient")).create();

        GeoPlatformService geoPlatformService = (GeoPlatformService) testContext.getApplicationContext().getBean("geoPlatformService");
        Assert.assertNotNull("geoPlatformService is NULL", geoPlatformService);

        Object implementor = geoPlatformService;
        SpringBusFactory bf = new SpringBusFactory();
        bus = bf.createBus();

        bus.getInInterceptors().add(new LoggingInInterceptor());
        bus.getOutInterceptors().add(new LoggingOutInterceptor());
        
        bus.getInInterceptors().add(this.createInInterceptor());
        bus.getOutInterceptors().add(this.createOutInterceptor());

        bf.setDefaultBus(bus);
        String address = "http://localhost:8282/geoplatform-service/soap";
        endpoint = Endpoint.publish(address, implementor);

        logger.debug("\n*** Server ready...");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        logger.trace("\n\t@@@ WSListenerServices.prepareTestInstance @@@");

        ServiceTest testInstance = (ServiceTest) testContext.getTestInstance();
        testInstance.setGeoplatformServiceClient(gpWSClient);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        logger.trace("\n\t@@@ WSListenerServices.afterTestClass @@@");

        endpoint.stop();
        bus.shutdown(true);
        // Wait to be sure that the endpoint was shutdown properly
        Thread.sleep(5 * 1000);
    }

    private WSS4JInInterceptor createInInterceptor() {
        Map<String, Object> inProps = new HashMap<String, Object>();
        
        StringBuilder sb = new StringBuilder();
        sb.append(WSHandlerConstants.USERNAME_TOKEN + " ");
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
        
        // ----------- Only Encryption
////        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
//        sb.append(WSHandlerConstants.ENCRYPT);
//        inProps.put(WSHandlerConstants.ACTION, sb.toString());
//        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "Server_Decrypt.properties");

        // ----------- Only Signature
////        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
//        sb.append(WSHandlerConstants.SIGNATURE);
//        inProps.put(WSHandlerConstants.ACTION, sb.toString());
//        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "Server_SignVerf.properties");

        // ----------- Signature and Encryption
//        tseInProps.put(WSHandlerConstants.ACTION, "Timestamp Signature Encrypt");
        sb.append(WSHandlerConstants.TIMESTAMP + " ");
        sb.append(WSHandlerConstants.SIGNATURE + " ");
        sb.append(WSHandlerConstants.ENCRYPT);
        inProps.put(WSHandlerConstants.ACTION, sb.toString());
        
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, "Server_SignVerf.properties");
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, "Server_Decrypt.properties");      

        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());
        return new WSS4JInInterceptor(inProps);
    }

    private WSS4JOutInterceptor createOutInterceptor() {
        Map<String, Object> outProps = new HashMap<String, Object>();
        
        StringBuilder sb = new StringBuilder();
        
        // ----------- Only Encryption
////        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
//        sb.append(WSHandlerConstants.ENCRYPT);
//        outProps.put(WSHandlerConstants.ACTION, sb.toString());
//        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "Server_SignVerf.properties");
//        outProps.put(WSHandlerConstants.ENCRYPTION_USER, "clientx509v1");

        // ----------- Only Signature
////        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);
//        sb.append(WSHandlerConstants.SIGNATURE);
//        outProps.put(WSHandlerConstants.ACTION, sb.toString());
//        outProps.put(WSHandlerConstants.USER, "serverx509v1");
//        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "Server_Decrypt.properties");

        // ----------- Signature and Encryption
//        outProps.put(WSHandlerConstants.ACTION, "Timestamp Signature Encrypt");
        sb.append(WSHandlerConstants.TIMESTAMP + " ");
        sb.append(WSHandlerConstants.SIGNATURE + " ");
        sb.append(WSHandlerConstants.ENCRYPT);
        outProps.put(WSHandlerConstants.ACTION, sb.toString());
        
        outProps.put(WSHandlerConstants.USER, "serverx509v1");
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, "Server_Decrypt.properties");
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, "Server_SignVerf.properties");
        outProps.put(WSHandlerConstants.ENCRYPTION_USER, "clientx509v1");

        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServerKeystorePasswordCallback.class.getName());
        return new WSS4JOutInterceptor(outProps);
    }
}
