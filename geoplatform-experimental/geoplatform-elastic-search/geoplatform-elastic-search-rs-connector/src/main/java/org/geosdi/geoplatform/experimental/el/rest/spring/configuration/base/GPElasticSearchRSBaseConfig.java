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
package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.base;

import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth.GPElasticSearchRSAuthConfiguration;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRSBaseConfig {

    /**
     * @param theHttpHost
     * @param theConnectionTimeout
     * @param theSocketTimeout
     * @param theMaxTotalConnections
     * @param theDefaultMaxPerRoute
     * @param theMaxRedirects
     * @param theElasticSearchRestAuthConfiguration
     * @param theElasticSearchRSSslConfiguration
     * @return {@link GPElasticSearchRSBaseConfiguration}
     */
    @Bean
    public GPElasticSearchRSBaseConfiguration elasticSearchRSBaseConfiguration(@Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.hosts:@null}") String theHttpHost,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.connection.timeout:@null}") Integer theConnectionTimeout,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.socket.timeout:@null}") Integer theSocketTimeout,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.max_total_connections:@null}") Integer theMaxTotalConnections,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.default_max_per_route:@null}") Integer theDefaultMaxPerRoute,
            @Value(value = "elasticSearchRestConfigurator{gp.elasticsearch.rest.max_redirects:@null}") Integer theMaxRedirects,
            @Qualifier(value = "elasticSearchRestAuthConfiguration") GPElasticSearchRSAuthConfiguration theElasticSearchRestAuthConfiguration,
            @Qualifier(value = "elasticSearchRSSslConfiguration") GPElasticSearchRSSslConfiguration theElasticSearchRSSslConfiguration) {
        return new ElasticSearchRSBaseConfiguration(theHttpHost, theConnectionTimeout, theSocketTimeout, theMaxTotalConnections, theDefaultMaxPerRoute, theMaxRedirects, theElasticSearchRestAuthConfiguration, theElasticSearchRSSslConfiguration);
    }
}