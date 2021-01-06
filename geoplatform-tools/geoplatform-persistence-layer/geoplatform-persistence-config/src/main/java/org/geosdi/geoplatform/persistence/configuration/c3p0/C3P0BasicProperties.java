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
package org.geosdi.geoplatform.persistence.configuration.c3p0;

import org.geosdi.geoplatform.c3p0.config.GPC3P0Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "c3p0BasicProperties")
class C3P0BasicProperties implements GPC3P0Config {

    @Value("persistence{c3p0_acquireIncrement:@null}")
    private Integer acquireIncrement;
    @Value("persistence{c3p0_acquireRetryAttempts:@null}")
    private Integer acquireRetryAttempts;
    @Value("persistence{c3p0_minPoolSize:@null}")
    private Integer minPoolSize;
    @Value("persistence{c3p0_maxPoolSize:@null}")
    private Integer maxPoolSize;
    @Value("persistence{c3p0_maxIdleTime:@null}")
    private Integer maxIdleTime;
    @Value("persistence{c3p0_maxConnectionAge:@null}")
    private Integer maxConnectionAge;
    @Value("persistence{c3p0_connectionCustomizerClassName:@null}")
    private String connectionCustomizerClassName;

    /**
     * @return the acquireIncrement
     */
    public Integer getAcquireIncrement() {
        return acquireIncrement = ((acquireIncrement == null) ? this.defaultAcquireIncrement() : acquireIncrement);
    }

    /**
     * @return the acquireRetryAttempts
     */
    public Integer getAcquireRetryAttempts() {
        return (acquireRetryAttempts = (acquireRetryAttempts == null) ? this.defaultAcquireRetryAttempts() : acquireRetryAttempts);
    }

    /**
     * @return the minPoolSize
     */
    public Integer getMinPoolSize() {
        return minPoolSize = ((minPoolSize == null) ? this.defaultMinPoolSize() : minPoolSize);
    }

    /**
     * @return the maxPoolSize
     */
    public Integer getMaxPoolSize() {
        return maxPoolSize = ((maxPoolSize == null) ? this.defaultMaxPoolSize() : maxPoolSize);
    }

    /**
     * @return the maxIdleTime
     */
    public Integer getMaxIdleTime() {
        return maxIdleTime = ((maxIdleTime == null) ? this.defaultMaxIdleTime() : maxIdleTime);
    }

    /**
     * @return the maxConnectionAge
     */
    public Integer getMaxConnectionAge() {
        return maxConnectionAge = ((maxConnectionAge == null) ? this.defaultMaxConnectionAge() : maxConnectionAge);
    }

    /**
     * @return the connectionCustomizerClassName
     */
    public String getConnectionCustomizerClassName() {
        return connectionCustomizerClassName = ((connectionCustomizerClassName == null) ? CONNECTION_CUSTOMIZER_CLASS : connectionCustomizerClassName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " + "acquireIncrement = "
                + getAcquireIncrement() + ", acquireRetryAttempts = "
                + getAcquireRetryAttempts() + ", minPoolSize = "
                + getMinPoolSize() + ", maxPoolSize = " + getMaxPoolSize()
                + ", maxIdleTime = " + getMaxIdleTime() + ", maxConnectionAge = "
                + getMaxConnectionAge() + ", connectionCustomizerClassName = "
                + getConnectionCustomizerClassName() + '}';
    }
}