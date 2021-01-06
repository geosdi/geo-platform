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
package org.geosdi.geoplatform.support.xmpp.spring.jasypt.placeholder;

import java.net.MalformedURLException;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPXmppPlaceholderConfig {

    private static final PlaceholderXmppResourcesLoader placeholderResourcesLoader = new PlaceholderXmppResourcesLoader();

    @Bean(name = "gpXmppPropertyConfigurer")
    @Required
    public static EncryptablePropertySourcesPlaceholderConfigurer gpXmppPropertyConfigurer(@Qualifier(
            value = "gpXmppPooledPBEStringEncryptor") PooledPBEStringEncryptor gpXmppPooledPBEStringEncryptor,
            @Value("#{systemProperties['GP_DATA_DIR']}") String gpConfigDataDir,
            @Value("#{systemProperties['GP_XMPP_FILE_PROP']}") String gpXmppFileProp)
            throws MalformedURLException {

        EncryptablePropertySourcesPlaceholderConfigurer gpXmppPC = new EncryptablePropertySourcesPlaceholderConfigurer(gpXmppPooledPBEStringEncryptor);
        gpXmppPC.setPlaceholderPrefix("gpXmppConfigurator{");
        gpXmppPC.setPlaceholderSuffix("}");
        gpXmppPC.setNullValue("@null");

        gpXmppPC.setLocations(placeholderResourcesLoader.loadResources(gpConfigDataDir,
                gpXmppFileProp));
        gpXmppPC.setIgnoreResourceNotFound(Boolean.TRUE);
        gpXmppPC.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);

        return gpXmppPC;
    }

}
