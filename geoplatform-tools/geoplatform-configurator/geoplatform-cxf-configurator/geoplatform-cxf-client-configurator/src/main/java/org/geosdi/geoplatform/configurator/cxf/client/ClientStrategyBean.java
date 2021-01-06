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
package org.geosdi.geoplatform.configurator.cxf.client;

import org.geosdi.geoplatform.support.cxf.client.GPClientStrategyBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "clientStrategyBean")
public class ClientStrategyBean implements GPClientStrategyBean {

    @Value(value = "configurator{webservice_client_logging}")
    private String loggingStrategy;
    @Value(value = "configurator{webservice_security_strategy}")
    private String securityStrategy;
    @Value(value = "configurator{webservice_username_token_user}")
    private String usernameTokenUser;
    @Value(value = "configurator{webservice_client_keystore_user}")
    private String clientKeystoreUser;
    @Value(value = "configurator{webservice_server_keystore_user}")
    private String serverKeystoreUser;
    @Value(value = "configurator{webservice_client_privatekey_properties_file}")
    private String clientPrivateKeyPropertiesFile;
    @Value(value = "configurator{webservice_server_publickey_properties_file}")
    private String serverPublicKeyPropertiesFile;

    /**
     * @return the loggingStrategy
     */
    @Override
    public String getLoggingStrategy() {
        return loggingStrategy;
    }

    /**
     * @return the securityStrategy
     */
    @Override
    public String getSecurityStrategy() {
        return securityStrategy;
    }

    /**
     * @return the usernameTokenUser
     */
    @Override
    public String getUsernameTokenUser() {
        return usernameTokenUser;
    }

    /**
     * @return the clientKeystoreUser
     */
    @Override
    public String getClientKeystoreUser() {
        return clientKeystoreUser;
    }

    /**
     * @return the serverKeystoreUser
     */
    @Override
    public String getServerKeystoreUser() {
        return serverKeystoreUser;
    }

    /**
     * @return the clientPrivateKeyPropertiesFile
     */
    @Override
    public String getClientPrivateKeyPropertiesFile() {
        return clientPrivateKeyPropertiesFile;
    }

    /**
     * @return the serverPublicKeyPropertiesFile
     */
    @Override
    public String getServerPublicKeyPropertiesFile() {
        return serverPublicKeyPropertiesFile;
    }

    @Override
    public String toString() {
        return "ClientStrategyBean { " + "loggingStrategy = " + loggingStrategy
                + ", securityStrategy = " + securityStrategy
                + ", usernameTokenUser = " + usernameTokenUser
                + ", clientKeystoreUser = " + clientKeystoreUser
                + ", serverKeystoreUser = " + serverKeystoreUser
                + ", clientPrivateKeyPropertiesFile = "
                + clientPrivateKeyPropertiesFile
                + ", serverPublicKeyPropertiesFile = "
                + serverPublicKeyPropertiesFile + '}';
    }

}
