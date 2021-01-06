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
package org.geosdi.geoplatform.persistence.configuration.properties;

import net.jcip.annotations.Immutable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.StringTokenizer;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.list;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "gpPersistenceConnector")
public class GPPersistenceConnector implements InitializingBean {

    @Value("persistence{db_driverClassName}")
    private String driverClassName;
    @Value("persistence{db_url}")
    private String url;
    @Value("persistence{db_username}")
    private String username;
    @Value("persistence{db_password}")
    private String password;
    @Value("persistence{db_packageToScan}")
    private String packageToScan;
    private String[] packagesToScan;

    /**
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the packageToScan
     */
    public String getPackageToScan() {
        return packageToScan;
    }

    /**
     * @return the packagesToScan
     */
    public String[] getPackagesToScan() {
        return (this.packagesToScan != null ? this.packagesToScan : list(new StringTokenizer(this.packageToScan, ":"))
                .stream()
                .toArray(String[]::new));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", packageToScan = '" + packageToScan + '\'' +
                ", packagesToScan = " + Arrays.toString(packagesToScan) +
                '}';
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument((this.url != null) && !(this.url.trim().isEmpty()), "Parameter DB URL cannot be Null or an Empty String.");
        checkArgument((this.packageToScan != null) && !(this.packageToScan.trim().isEmpty()), "Parameter Package To Scan cannot be Null or an Empty String");
    }
}