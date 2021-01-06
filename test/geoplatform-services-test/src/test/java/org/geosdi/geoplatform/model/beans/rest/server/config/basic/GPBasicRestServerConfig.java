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
package org.geosdi.geoplatform.model.beans.rest.server.config.basic;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.*;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.geosdi.geoplatform.configurator.bootstrap.Develop;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.exception.rs.mapper.GPExceptionFaultMapper;
import org.geosdi.geoplatform.services.GeoPlatformService;
import org.geosdi.geoplatform.support.cxf.rs.provider.configurator.GPRestProviderType;
import org.geosdi.geoplatform.support.cxf.rs.provider.factory.GPRestProviderFactory;
import org.geosdi.geoplatform.support.cxf.rs.provider.jettyson.GPJSONProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Develop
class GPBasicRestServerConfig {

    @Bean(name = "gpBasicRestServer")
    @Required
    public static Server gpBasicRestServer(@Qualifier(
            value = "geoPlatformService") GeoPlatformService geoPlatformService,
            @Value("configurator{webservice_rs_test_endpoint_address}") String basicRestAddress,
            @Value("configurator{cxf_rest_provider_type}") GPRestProviderType providerType,
            @Qualifier(value = "serverLoggingInInterceptorBean") LoggingInInterceptor serverLogInInterceptor,
            @Qualifier(value = "serverLoggingOutInterceptorBean") LoggingOutInterceptor serverLogOutInterceptor) {

        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setServiceBean(geoPlatformService);
        factory.setAddress(basicRestAddress);

        factory.setProviders(Arrays.asList(
                new Object[]{createProvider(providerType),
                        new GPExceptionFaultMapper()}));

        Map<Object, Object> extensionMappings = new HashMap<>();
        extensionMappings.put("xml", MediaType.APPLICATION_XML);
        extensionMappings.put("json", MediaType.APPLICATION_JSON);
        factory.setExtensionMappings(extensionMappings);

        if (providerType.equals(GPRestProviderType.FASTINFOSET)) {
            Map<String, Object> properties = new HashMap<>();
            properties.put("org.apache.cxf.endpoint.private", Boolean.TRUE);
            factory.setProperties(properties);
            factory.setInInterceptors(Arrays.<Interceptor<? extends Message>>asList(new FIStaxInInterceptor()));
            factory.setOutInterceptors(Arrays.<Interceptor<? extends Message>>asList(new FIStaxOutInterceptor()));
        } else {
            factory.setInInterceptors(Arrays.<Interceptor<? extends Message>>asList(serverLogInInterceptor));
            factory.setOutInterceptors(Arrays.<Interceptor<? extends Message>>asList(serverLogOutInterceptor));
        }

        return factory.create();
    }

    private static Object createProvider(GPRestProviderType providerType) {
        Object provider = GPRestProviderFactory.createProvider(providerType);
        if (provider instanceof GPJSONProvider) {
            ((GPJSONProvider) provider).setExtraClass(
                    new Class<?>[]{GPAccount.class,
                            GPLayer.class});
        }
        return provider;
    }

}
