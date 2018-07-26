/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.mail.spring.velocity;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.mail.spring.configuration.velocity.GPVelocityParserPollSize;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.velocity.VelocityEngineFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Profile(value = "GPMailVelocitySupport")
class GPSpringVelocityEngineConfig {

    @GeoPlatformLog
    static Logger logger;

    @Bean(name = "gpSpringVelocityEngine")
    @Scope(value = "prototype")
    @Autowired
    public VelocityEngine gpVelocityEngine(@Qualifier(
            value = "gpVelocityParserPollSize") GPVelocityParserPollSize gpVelocityParserPollSize)
            throws VelocityException, IOException {
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@CONFIGURING VELOCITY POOL PARSER "
                + "with : {}\n\n", gpVelocityParserPollSize);

        VelocityEngineFactory factory = new VelocityEngineFactory();
        Properties props = new Properties();
        factory.setOverrideLogging(Boolean.FALSE);
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader."
                + "ClasspathResourceLoader");
        props.put("parser.pool.class",
                "org.apache.velocity.runtime.ParserPoolImpl");
        props.put("parser.pool.size", gpVelocityParserPollSize.getPoolSize());
        factory.setVelocityProperties(props);

        return factory.createVelocityEngine();
    }

}
