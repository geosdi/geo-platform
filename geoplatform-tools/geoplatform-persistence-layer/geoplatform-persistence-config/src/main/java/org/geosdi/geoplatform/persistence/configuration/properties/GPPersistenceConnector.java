/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import java.util.StringTokenizer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "gpPersistenceConnector")
public class GPPersistenceConnector implements InitializingBean {

    @Value("${db_driverClassName}")
    private String driverClassName;
    @Value("${db_url}")
    private String url;
    @Value("${db_username}")
    private String username;
    @Value("${db_password}")
    private String password;
    @Value("${db_packageToScan}")
    private String packageToScan;
    private String[] packagesToScan;

    /**
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * @param driverClassName the driverClassName to set
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the packageToScan
     */
    public String getPackageToScan() {
        return packageToScan;
    }

    /**
     * @param packageToScan the packageToScan to set
     */
    public void setPackageToScan(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    /**
     * @return the packagesToScan
     */
    public String[] getPackagesToScan() {
        return (this.packagesToScan != null
                ? this.packagesToScan : definePackagesToScan());
    }

    protected final String[] definePackagesToScan() {
        StringTokenizer st = new StringTokenizer(this.packageToScan, ":");
        int tokenCount = st.countTokens();
        this.packagesToScan = new String[tokenCount];

        for (int i = 0; i < tokenCount; i++) {
            this.packagesToScan[i] = st.nextToken();
        }

        return this.packagesToScan;
    }

    @Override
    public String toString() {
        return "GPPersistenceConnector{ " + "driverClassName = " + driverClassName
                + ", url = " + url + ", username = " + username
                + ", password = " + password
                + ", packagesToScan = " + packageToScan + '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if ((this.packageToScan == null) || (this.packageToScan.equals(""))) {
            throw new IllegalArgumentException("Parameter Package To Scan "
                    + "can not be Null or an Empty String.");
        }
    }
}
