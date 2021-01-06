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
package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.placeholder;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.MalformedURLException;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestPlaceholderConfig {

    private static final GPElasticSearchRestResourcesLoader elasticSearchRestResourcesLoader = new GPElasticSearchRestResourcesLoader();

    /**
     * @param elasticSearchRestPooledPBEStringEncryptor
     * @param elasticSearchRestDataDir
     * @param elasticSearchRestFileProp
     * @return {@link PropertySourcesPlaceholderConfigurer}
     * @throws MalformedURLException
     */
    @Bean(name = "elasticSearchRestPropertyConfigurer")
    public static PropertySourcesPlaceholderConfigurer elasticSearchRestPropertyConfigurer(
            @Qualifier(value = "elasticSearchRestPooledPBEStringEncryptor") PooledPBEStringEncryptor elasticSearchRestPooledPBEStringEncryptor,
            @Value("#{systemProperties['GP_ELASTICSEARCH_REST_DATA_DIR']}") String elasticSearchRestDataDir,
            @Value("#{systemProperties['GP_ELASTICSEARCH_REST_FILE_PROP']}") String elasticSearchRestFileProp) throws MalformedURLException {
        EncryptablePropertySourcesPlaceholderConfigurer elasticSearchRestPC = new EncryptablePropertySourcesPlaceholderConfigurer(elasticSearchRestPooledPBEStringEncryptor);
        elasticSearchRestPC.setPlaceholderPrefix("elasticSearchRestConfigurator{");
        elasticSearchRestPC.setPlaceholderSuffix("}");
        elasticSearchRestPC.setNullValue("@null");
        elasticSearchRestPC.setLocations(elasticSearchRestResourcesLoader
                .loadResources(elasticSearchRestDataDir, elasticSearchRestFileProp));
        elasticSearchRestPC.setIgnoreResourceNotFound(TRUE);
        elasticSearchRestPC.setIgnoreUnresolvablePlaceholders(TRUE);
        return elasticSearchRestPC;
    }
}