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
package org.geosdi.geoplatform.services.rs.config.server;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharingFilter;
import org.geosdi.geoplatform.configurator.bootstrap.cxf.Rest;
import org.geosdi.geoplatform.exception.rs.mapper.GPExceptionFaultMapper;
import org.geosdi.geoplatform.services.GPWFSService;
import org.geosdi.geoplatform.services.rs.config.converter.GPWFSMapParamConverterProvider;
import org.geosdi.geoplatform.support.cxf.rs.provider.configurator.GPRestProviderType;
import org.geosdi.geoplatform.support.cxf.rs.provider.factory.GPRestProviderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;

import static java.util.Arrays.asList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Rest
@Configuration
class GPWFSServiceJsonConfig {

    /**
     * @param wfsService
     * @param gpJsonWFSApplication
     * @param providerType
     * @param serverLogInInterceptor
     * @param serverLogOutInterceptor
     * @return {@link JAXRSServerFactoryBean}
     */
    @Bean(initMethod = "create")
    public static JAXRSServerFactoryBean gpWFSServiceJson(@Qualifier(value = "wfsService") GPWFSService wfsService,
            @Qualifier(value = "gpJsonWFSApplication") Application gpJsonWFSApplication,
            @Value("configurator{cxf_rest_provider_type}") GPRestProviderType providerType,
            @Qualifier(value = "wfsCrossResourceSharingFilter") CrossOriginResourceSharingFilter wfsCrossResourceSharingFilter,
            @Qualifier(value = "serverLoggingInInterceptorBean") LoggingInInterceptor serverLogInInterceptor,
            @Qualifier(value = "serverLoggingOutInterceptorBean") LoggingOutInterceptor serverLogOutInterceptor) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(
                gpJsonWFSApplication, JAXRSServerFactoryBean.class);
        factory.setServiceBean(wfsService);
        factory.setAddress(factory.getAddress());
        factory.setProviders(asList(GPRestProviderFactory.createProvider(providerType), wfsCrossResourceSharingFilter,
                new GPExceptionFaultMapper(), new GPWFSMapParamConverterProvider()));
        factory.setInInterceptors(Arrays.<Interceptor<? extends Message>>asList(serverLogInInterceptor));
        factory.setOutInterceptors(Arrays.<Interceptor<? extends Message>>asList(serverLogOutInterceptor));
        return factory;
    }
}