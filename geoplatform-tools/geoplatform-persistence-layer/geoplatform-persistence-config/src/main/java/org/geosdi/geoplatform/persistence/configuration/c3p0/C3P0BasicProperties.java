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
package org.geosdi.geoplatform.persistence.configuration.c3p0;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "c3p0BasicProperties")
public class C3P0BasicProperties {

    @Value("persistence{c3p0_acquireIncrement}")
    private int acquireIncrement;
    @Value("persistence{c3p0_acquireRetryAttempts}")
    private int acquireRetryAttempts;
    @Value("persistence{c3p0_minPoolSize}")
    private int minPoolSize;
    @Value("persistence{c3p0_maxPoolSize}")
    private int maxPoolSize;
    @Value("persistence{c3p0_maxIdleTime}")
    private int maxIdleTime;
    @Value("persistence{c3p0_maxConnectionAge}")
    private int maxConnectionAge;
    @Value("persistence{c3p0_connectionCustomizerClassName}")
    private String connectionCustomizerClassName;

    /**
     * @return the acquireIncrement
     */
    public int getAcquireIncrement() {
        return acquireIncrement;
    }

    /**
     * @return the acquireRetryAttempts
     */
    public int getAcquireRetryAttempts() {
        return acquireRetryAttempts;
    }

    /**
     * @return the minPoolSize
     */
    public int getMinPoolSize() {
        return minPoolSize;
    }

    /**
     * @return the maxPoolSize
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @return the maxIdleTime
     */
    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    /**
     * @return the maxConnectionAge
     */
    public int getMaxConnectionAge() {
        return maxConnectionAge;
    }

    /**
     * @return the connectionCustomizerClassName
     */
    public String getConnectionCustomizerClassName() {
        return connectionCustomizerClassName;
    }

    @Override
    public String toString() {
        return "C3P0BasicProperties{ " + "acquireIncrement = "
                + acquireIncrement + ", acquireRetryAttempts = "
                + acquireRetryAttempts + ", minPoolSize = "
                + minPoolSize + ", maxPoolSize = " + maxPoolSize
                + ", maxIdleTime = " + maxIdleTime + ", maxConnectionAge = "
                + maxConnectionAge + ", connectionCustomizerClassName = "
                + connectionCustomizerClassName + '}';
    }

}
