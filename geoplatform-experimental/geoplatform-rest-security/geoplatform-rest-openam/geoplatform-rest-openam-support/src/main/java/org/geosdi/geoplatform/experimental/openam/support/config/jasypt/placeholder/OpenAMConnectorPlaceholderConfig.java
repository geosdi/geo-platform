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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.experimental.openam.support.config.jasypt.placeholder;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class OpenAMConnectorPlaceholderConfig {

    private static final Logger logger = LoggerFactory.getLogger(OpenAMConnectorPlaceholderConfig.class);

    private static final OpenAMConnectorResourcesLoader openAMConnectorResourcesLoader = new OpenAMConnectorResourcesLoader();

    @Bean(name = "openAMConnectorConfigurator")
    public static EncryptablePropertySourcesPlaceholderConfigurer openAMConnectorPropertyConfigurer(
            @Qualifier(value = "openAMPooledPBEStringEncryptor") PooledPBEStringEncryptor openAMPooledPBEStringEncryptor,
            @Value("#{systemProperties['OPENAM_CONNECTOR_DATA_DIR']}") String openAMConnectorConfigDataDir,
            @Value("#{systemProperties['OPENAM_CONNECTOR_FILE_PROP']}") String openAMConnectorFileProp)
            throws MalformedURLException {
        logger.debug("::::::::::::::::::::::::::::::::::::: INITIALIZING OPENAM_CONNECTOR PLACEHOLDER\n");

        EncryptablePropertySourcesPlaceholderConfigurer openAMConnectorPC = new EncryptablePropertySourcesPlaceholderConfigurer(openAMPooledPBEStringEncryptor);
        openAMConnectorPC.setPlaceholderPrefix("openAMConnectorConfigurator{");
        openAMConnectorPC.setPlaceholderSuffix("}");
        openAMConnectorPC.setNullValue("@null");

        openAMConnectorPC.setLocations(openAMConnectorResourcesLoader
                .loadResources(openAMConnectorConfigDataDir, openAMConnectorFileProp));
        openAMConnectorPC.setIgnoreResourceNotFound(Boolean.TRUE);
        openAMConnectorPC.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        return openAMConnectorPC;
    }
}
