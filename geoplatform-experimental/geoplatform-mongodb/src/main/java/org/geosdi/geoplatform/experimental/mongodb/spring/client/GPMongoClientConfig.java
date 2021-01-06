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
package org.geosdi.geoplatform.experimental.mongodb.spring.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.geosdi.geoplatform.experimental.mongodb.configuration.properties.MongoProperties;
import org.geosdi.geoplatform.experimental.mongodb.spring.annotation.GPMongoConfig;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@GPMongoConfig
@Configuration
class GPMongoClientConfig {

    @GeoPlatformLog
    static Logger logger;

    @Bean(name = "gpSpringMongoClient")
    public MongoClient gpMongoClient(@Qualifier(value = "gpSpringMongoProp") MongoProperties gpSpringMongoProp)
            throws Exception {
        logger.debug("###################### GeoPlatform Experimental Version ::== Building MongoClient.\n");

        return (gpSpringMongoProp.getMongoAuth().isMongoAuthEnabled()
                ? new MongoClient(new ServerAddress(gpSpringMongoProp.getMongoHost(), gpSpringMongoProp.getMongoPort()),
                Arrays.asList(MongoCredential.createCredential(gpSpringMongoProp.getMongoAuth().getMongoUserName(),
                        gpSpringMongoProp.getMongoDatabaseName(),
                        gpSpringMongoProp.getMongoAuth().getMongoPassword().toCharArray())))
                : new MongoClient(new ServerAddress(gpSpringMongoProp.getMongoHost(), gpSpringMongoProp.getMongoPort())));
    }
}
