/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.persistence.configuration.cache.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@Component(value = "gpEhCacheProperties")
class GPEhCacheProperties implements GPEhCacheHibernateProperties {

    @Value("persistence{db_cacheRegionFactoryClass}")
    @Getter
    @Setter
    private String hibCacheRegionFactoryClass;
    @Value("persistence{db_javax.cache.provider}")
    @Getter
    @Setter
    private String hibCacheProvider;
    @Value("persistence{db_useSecondLevelCache:@null}")
    @Setter
    private Boolean hibUseSecondLevelCache;
    @Value("persistence{db_useQueryCache:@null}")
    @Setter
    private Boolean hibUseQueryCache;
    @Value("persistence{db_ehcacheConfigurationResourceName:@null}")
    @Setter
    @Getter
    private String ehcacheConfResourceName;
    @Value("persistence{db_use_structured_entries:@null}")
    @Setter
    private Boolean useStructuredEntries;

    /**
     * @return {@link Boolean}
     */
    @Override
    public String getHibCacheRegionFactoryClass() {
        return hibCacheRegionFactoryClass;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isHibUseQueryCache() {
        return (hibUseQueryCache == null) ? FALSE : hibUseQueryCache;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public String getEhcacheConfResourceName() {
        return ehcacheConfResourceName;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isUseStructuredEntries() {
        return (useStructuredEntries == null) ? FALSE : useStructuredEntries;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isHibUseSecondLevelCache() {
        return (hibUseSecondLevelCache == null) ? FALSE : hibUseSecondLevelCache;
    }
}