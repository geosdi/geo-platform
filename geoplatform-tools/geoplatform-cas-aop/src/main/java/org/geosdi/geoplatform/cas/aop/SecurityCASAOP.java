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
package org.geosdi.geoplatform.cas.aop;

import it.geosolutions.geoserver.rest.cas.CASHTTPUtils;
import it.geosolutions.geoserver.rest.cas.GeoServerCASRESTPublisher;
import it.geosolutions.geoserver.rest.cas.GeoServerCASRESTReader;
import it.geosolutions.geoserver.rest.cas.manager.GeoServerCASRESTStoreManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.geosdi.geoplatform.gui.server.UploadServlet;
import org.geosdi.geoplatform.gui.server.gwt.GeoPlatformServiceImpl;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */

/**
 * @Aspect tells the Spring framework that this class contains advice that
 * should be applied to one or more specified Pointcuts at runtime
 * @Component is necessary to support the spring auto-scan
 */
@Aspect
public class SecurityCASAOP {

    private static final Logger logger = LoggerFactory.getLogger(SecurityCASAOP.class);
    //
    @Value("configurator{geoserver_url}")
    private String geoserverUrl;
    @Value("configurator{geoserver_username}")
    private String geoserverUsername;
    @Value("configurator{geoserver_password}")
    private String geoserverPassword;
    //
    private GeoServerCASRESTReader casRestReader;
    private GeoServerCASRESTPublisher casRestPublisher;
    private GeoServerCASRESTStoreManager casRestStoreManager;

    @Around("execution(* it.geosolutions.geoserver.rest.GeoServerRESTReader.*(..))")
    public Object adviceGeoServerRestReader(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("#########################AOP GeoServerRESTReader.*(..) is running...");
        Assertion receivedAssertion = this.retrieveAssertion();
        Object[] parameters = proceedingJoinPoint.getArgs();
        Class[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object object = parameters[i];
            types[i] = object.getClass();
            logger.info("##########################Parameter type: {}\n", object.getClass());
        }
        casRestReader.setCasAssertion(receivedAssertion);
        try {
            Method method = GeoServerCASRESTReader.class.getMethod(
                    proceedingJoinPoint.getSignature().getName(), types);
            method.setAccessible(Boolean.TRUE);
            return method.invoke(this.casRestReader, proceedingJoinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Around("execution(* it.geosolutions.geoserver.rest.GeoServerRESTPublisher.*(..))")
    public Object adviceGeoServerRESTPublisher(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("AOP GeoServerRESTPublisher.*(..) is running...");
        Assertion receivedAssertion = this.retrieveAssertion();
        CASHTTPUtils.setCasAssertion(receivedAssertion);
        Object[] parameters = proceedingJoinPoint.getArgs();
        Class[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object object = parameters[i];
            types[i] = object.getClass();
            logger.info("Parameter type: " + object.getClass());
        }

        try {
            Method method = GeoServerCASRESTPublisher.class.getMethod(
                    proceedingJoinPoint.getSignature().getName(), types);
            method.setAccessible(Boolean.TRUE);
            return method.invoke(this.casRestPublisher, proceedingJoinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Around("execution(* it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager.*(..))")
    public Object adviceGeoServerRESTStoreManager(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("AOP GeoServerRESTStoreManager.*(..) is running...");
        Assertion receivedAssertion = this.retrieveAssertion();
        CASHTTPUtils.setCasAssertion(receivedAssertion);
        Object[] parameters = proceedingJoinPoint.getArgs();
        Class[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object object = parameters[i];
            types[i] = object.getClass();
            logger.debug("REST Store Parameter type: " + object.getClass());
        }

        try {
            Method method = GeoServerCASRESTStoreManager.class.getMethod(
                    proceedingJoinPoint.getSignature().getName(), types);
            method.setAccessible(Boolean.TRUE);
            logger.info("After REST Store Manager Method");
            return method.invoke(this.casRestStoreManager, proceedingJoinPoint.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    protected void initCASResources() throws MalformedURLException {
        this.casRestReader = new GeoServerCASRESTReader(geoserverUrl, geoserverUsername, geoserverPassword);
        this.casRestPublisher = new GeoServerCASRESTPublisher(geoserverUrl, geoserverUsername, geoserverPassword);
        try {
            this.casRestStoreManager = new GeoServerCASRESTStoreManager(
                    new URL(geoserverUrl), geoserverUsername, geoserverPassword);
        } catch (MalformedURLException ex) {
            logger.error("Error creating casRestStoreManager on AOP Module due to Malformed URL: " + ex);
        }
        logger.info("Executing initCASResources on {}\n", this.getClass().getSimpleName());
    }

    private Assertion retrieveAssertion() {
        HttpServletRequest request = GeoPlatformServiceImpl.getRequest();
        Assertion receivedAssertion;
        if (request != null) {
            logger.info("@@@@@@@@@@@@@@@@ Request : {}\n", request);
            logger.info("@@@@@@@@@@@@@@@@ Session : {}\n", request.getSession());
            receivedAssertion = (AssertionImpl) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        } else {
            //In this case we absume that the request is coming from UploadServlet
            logger.info("@@@@@@@@@@@@@@@@ Request in coming from UploadServlet?!");
            receivedAssertion = UploadServlet.getReceivedAssertion();
        }
        return receivedAssertion;
    }
}
