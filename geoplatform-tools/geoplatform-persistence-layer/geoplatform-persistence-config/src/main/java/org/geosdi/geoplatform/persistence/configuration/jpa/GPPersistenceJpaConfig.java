/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.persistence.configuration.jpa;

import org.geosdi.geoplatform.persistence.configuration.basic.strategy.PropertiesStrategyManager;
import org.geosdi.geoplatform.persistence.configuration.properties.GPPersistenceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Profile(value = "jpa")
@EnableTransactionManagement
class GPPersistenceJpaConfig {

    @Autowired
    private GPPersistenceConnector gpPersistenceConnector;
    //
    @Resource(name = "persitenceDataSource")
    private DataSource persitenceDataSource;
    //
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;
    //
    @Autowired
    private PropertiesStrategyManager hibPropStrategyManager;

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean gpEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean gpFactoryBean = new LocalContainerEntityManagerFactoryBean();
        gpFactoryBean.setDataSource(this.persitenceDataSource);
        gpFactoryBean.setPackagesToScan(this.gpPersistenceConnector.getPackagesToScan());
        gpFactoryBean.setJpaVendorAdapter(this.jpaVendorAdapter);
        gpFactoryBean.setLoadTimeWeaver(this.gpLoadTimeWeaver());
        gpFactoryBean.setJpaProperties(this.hibPropStrategyManager.getProperties());
        gpFactoryBean.setPersistenceUnitName("geoplatform-persistence-layer");
        return gpFactoryBean;
    }

    @Bean
    public PlatformTransactionManager gpTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.gpEntityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LoadTimeWeaver gpLoadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
}
