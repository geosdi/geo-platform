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
package org.geosdi.geoplatform.persistence.configuration.basic;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.geosdi.geoplatform.c3p0.config.GPC3P0Config;
import org.geosdi.geoplatform.persistence.configuration.properties.GPPersistenceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class PersistenceDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceDataSourceConfig.class);
    //
    @Autowired
    private GPPersistenceConnector gpPersistenceConnector;
    //
    @Resource(name = "c3p0BasicProperties")
    private GPC3P0Config c3p0BasicProperties;

    /**
     * @return {@link DataSource}
     * @throws Exception
     */
    @Bean
    public DataSource persitenceDataSource() throws Exception {
        checkArgument(this.gpPersistenceConnector != null, "The Parameter gpPersistenceConnector must not be null.");
        checkArgument(this.c3p0BasicProperties != null, "The Parameter c3p0BasicProperties must not be null.");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(this.gpPersistenceConnector.getDriverClassName());
        dataSource.setJdbcUrl(this.gpPersistenceConnector.getUrl());
        dataSource.setUser(this.gpPersistenceConnector.getUsername());
        dataSource.setPassword(this.gpPersistenceConnector.getPassword());
        /**
         * ************************ Poll Settings ****************************
         */
        logger.debug("\n\n################# C3P0 POOL SETTINGS @@@@@@@@@@@@@@@ {}\n\n", c3p0BasicProperties);
        dataSource.setAcquireIncrement(c3p0BasicProperties.getAcquireIncrement());
        dataSource.setAcquireRetryAttempts(c3p0BasicProperties.getAcquireRetryAttempts());
        dataSource.setInitialPoolSize(c3p0BasicProperties.getMinPoolSize());
        dataSource.setMinPoolSize(c3p0BasicProperties.getMinPoolSize());
        dataSource.setMaxPoolSize(c3p0BasicProperties.getMaxPoolSize());
        dataSource.setMaxIdleTime(c3p0BasicProperties.getMaxIdleTime());
        dataSource.setMaxConnectionAge(c3p0BasicProperties.getMaxConnectionAge());
        dataSource.setConnectionCustomizerClassName(c3p0BasicProperties.getConnectionCustomizerClassName());
        return dataSource;
    }
}