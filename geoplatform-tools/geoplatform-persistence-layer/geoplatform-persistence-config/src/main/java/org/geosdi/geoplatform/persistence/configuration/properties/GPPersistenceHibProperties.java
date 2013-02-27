/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.persistence.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "gpHibernateProperties")
public class GPPersistenceHibProperties {

    @Value("${db_databasePlatform}")
    private String hibDatabasePlatform;
    @Value("${db_showSql}")
    private boolean hibShowSql;
    @Value("${db_generateDdl}")
    private boolean hibGenerateDdl;
    @Value("${db_hbm2ddlAuto}")
    private String hibHbm2ddlAuto;
    @Value("${db_cacheProviderClass:@null}")
    private String hibCacheProviderClass;
    @Value("${db_cacheRegionFactoryClass}")
    private String hibCacheRegionFactoryClass;
    @Value("${db_useSecondLevelCache:@null}")
    private Boolean hibUseSecondLevelCache;
    @Value("${db_useQueryCache}")
    private boolean hibUseQueryCache;
    @Value("${db_generateStatistics}")
    private boolean hibGenerateStatistics;
    @Value("${db_defaultSchema}")
    private String hibDefaultSchema;
    @Value("${db_ehcacheConfigurationResourceName}")
    private String ehcacheConfResourceName;

    /**
     * @return the hibDatabasePlatform
     */
    public String getHibDatabasePlatform() {
        return hibDatabasePlatform;
    }

    /**
     * @return the hibShowSql
     */
    public boolean isHibShowSql() {
        return hibShowSql;
    }

    /**
     * @return the hibGenerateDdl
     */
    public boolean isHibGenerateDdl() {
        return hibGenerateDdl;
    }

    /**
     * @return the hibHbm2ddlAuto
     */
    public String getHibHbm2ddlAuto() {
        return hibHbm2ddlAuto;
    }

    /**
     * @return the hibCacheProviderClass
     */
    public String getHibCacheProviderClass() {
        return hibCacheProviderClass;
    }

    /**
     * @return the hibCacheRegionFactoryClass
     */
    public String getHibCacheRegionFactoryClass() {
        return hibCacheRegionFactoryClass;
    }

    /**
     * @return the hibUseSecondLevelCache
     */
    public Boolean isHibUseSecondLevelCache() {
        return hibUseSecondLevelCache;
    }

    /**
     * @return the hibUseQueryCache
     */
    public boolean isHibUseQueryCache() {
        return hibUseQueryCache;
    }

    /**
     * @return the hibGenerateStatistics
     */
    public boolean isHibGenerateStatistics() {
        return hibGenerateStatistics;
    }

    /**
     * @return the hibDefaultSchema
     */
    public String getHibDefaultSchema() {
        return hibDefaultSchema;
    }

    /**
     * @return the ehcacheConfResourceName
     */
    public String getEhcacheConfResourceName() {
        return ehcacheConfResourceName;
    }

    @Override
    public String toString() {
        return "GPPersistenceHibProperties{ " + "hibDatabasePlatform = "
                + hibDatabasePlatform + ", hibShowSql = " + hibShowSql
                + ", hibGenerateDdl = " + hibGenerateDdl
                + ", hibHbm2ddlAuto = " + hibHbm2ddlAuto
                + ", hibCacheProviderClass = " + hibCacheProviderClass
                + ", hibCacheRegionFactoryClass = " + hibCacheRegionFactoryClass
                + ", hibUseSecondLevelCache = " + hibUseSecondLevelCache
                + ", hibUseQueryCache = " + hibUseQueryCache
                + ", hibGenerateStatistics = " + hibGenerateStatistics
                + ", hibDefaultSchema = " + hibDefaultSchema
                + ", ehcacheConfResourceName = " + ehcacheConfResourceName + '}';
    }
}
