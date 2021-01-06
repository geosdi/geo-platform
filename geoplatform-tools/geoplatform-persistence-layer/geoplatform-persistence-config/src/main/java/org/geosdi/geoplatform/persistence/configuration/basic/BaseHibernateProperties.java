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

import org.geosdi.geoplatform.persistence.cache.api.GPHibernateCacheProvider;
import org.geosdi.geoplatform.persistence.configuration.basic.strategy.PersistenceHibernateStrategy;
import org.geosdi.geoplatform.persistence.configuration.properties.GPPersistenceHibProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class BaseHibernateProperties implements PersistenceHibernateStrategy {

    private static final Logger logger = LoggerFactory.getLogger(BaseHibernateProperties.class);
    //
    @Autowired
    private GPPersistenceHibProperties gpHibernateProperties;
    //
    @Autowired(required = false)
    private GPHibernateCacheProvider gpCacheProviderSupport;

    /**
     * @return {@link Properties}
     * @throws Exception
     */
    @Bean
    @Override
    public Properties hibernateProperties() throws Exception {
        checkArgument(gpHibernateProperties != null, "The Persistence Hibernate Properties obj must not be null");
        checkArgument((gpHibernateProperties.getHibDatabasePlatform() != null) && !(gpHibernateProperties.getHibDatabasePlatform().trim().isEmpty()), "Database Dialect must not be null");
        return new Properties() {

            private static final long serialVersionUID = 3109256773218160485L;

            {
                logger.debug("################### Hibernate Properties : {}\n", gpHibernateProperties);
                this.put("hibernate.dialect", gpHibernateProperties.getHibDatabasePlatform());
                this.put("hibernate.hbm2ddl.auto", gpHibernateProperties.getHibHbm2ddlAuto());
                this.put("hibernate.show_sql", gpHibernateProperties.isHibShowSql());
                this.put("hibernate.generate_statistics", gpHibernateProperties.isHibGenerateStatistics());
                this.put("hibernate.temp.use_jdbc_metadata_defaults", FALSE);
                if ((gpHibernateProperties.getHibDefaultSchema() != null) && !(gpHibernateProperties.getHibDefaultSchema().trim().isEmpty())) {
                    this.put("hibernate.default_schema", gpHibernateProperties.getHibDefaultSchema());
                }
                if (gpCacheProviderSupport != null) {
                    this.putAll(gpCacheProviderSupport.getCacheProviderProperties());
                }
            }
        };
    }
}