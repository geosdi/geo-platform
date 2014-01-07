/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
///*
// *  geo-platform
// *  Rich webgis framework
// *  http://geo-platform.org
// * ====================================================================
// *
// * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
// *
// * This program is free software: you can redistribute it and/or modify it 
// * under the terms of the GNU General Public License as published by 
// * the Free Software Foundation, either version 3 of the License, or 
// * (at your option) any later version. This program is distributed in the 
// * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
// * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
// * A PARTICULAR PURPOSE. See the GNU General Public License 
// * for more details. You should have received a copy of the GNU General 
// * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
// *
// * ====================================================================
// *
// * Linking this library statically or dynamically with other modules is 
// * making a combined work based on this library. Thus, the terms and 
// * conditions of the GNU General Public License cover the whole combination. 
// * 
// * As a special exception, the copyright holders of this library give you permission 
// * to link this library with independent modules to produce an executable, regardless 
// * of the license terms of these independent modules, and to copy and distribute 
// * the resulting executable under terms of your choice, provided that you also meet, 
// * for each linked independent module, the terms and conditions of the license of 
// * that module. An independent module is a module which is not derived from or 
// * based on this library. If you modify this library, you may extend this exception 
// * to your version of the library, but you are not obligated to do so. If you do not 
// * wish to do so, delete this exception statement from your version. 
// *
// */
//package org.geosdi.geoplatform.cas.aop;
//
//import javax.servlet.http.HttpServletRequest;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.geosdi.geoplatform.gui.server.gwt.GeoPlatformServiceImpl;
//import org.jasig.cas.client.util.AbstractCasFilter;
//import org.jasig.cas.client.validation.Assertion;
//import org.jasig.cas.client.validation.AssertionImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StopWatch;
//
///**
// * @author Nazzareno Sileno - CNR IMAA geoSDI Group
// * @email nazzareno.sileno@geosdi.org
// */
//@Aspect
//public class StaticCASAOP {
//
//    private static final Logger logger = LoggerFactory.getLogger(
//            StaticCASAOP.class);
//
//    @Around("execution(* it.geosolutions.geoserver.rest.HTTPUtils.*(..))")
//    public Object adviceHTTPUtils(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        logger.info("AOP HTTPUtils.(..) is running...");
//        HttpServletRequest request = GeoPlatformServiceImpl.getRequest();
//        logger.info("**************** Request: " + request);
//        logger.info("**************** Session: " + request.getSession());
//        Assertion receivedAssertion = (AssertionImpl) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
//
////        casRestReader.setCasAssertion(receivedAssertion);
//
//        StopWatch sw = new StopWatch(getClass().getSimpleName());
//
//        Object[] parameters = proceedingJoinPoint.getArgs();
//        Class[] types = new Class[parameters.length];
//        for (int i = 0; i < parameters.length; i++) {
//            Object object = parameters[i];
//            types[i] = object.getClass();
//            logger.debug("Parameter type: " + object.getClass());
//        }
//        return null;
////        return HTTPUtils.class.getMethod(
////                proceedingJoinPoint.getSignature().getName(), types).
////                invoke(casRestReader, proceedingJoinPoint.getArgs());
//    }
//}
