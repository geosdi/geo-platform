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
package org.geosdi.geoplatform.experimental.openam.support.connector.settings.credential;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.openam.api.connector.credential.IOpenAMCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMCredential")
public class OpenAMCredential implements IOpenAMCredential {

    private static final long serialVersionUID = -5475190384852298753L;
    //
    @Value("openAMConnectorConfigurator{openam.connector.username:@null}")
    private String userName;
    @Value("openAMConnectorConfigurator{openam.connector.username_key:@null}")
    private String userNameKey;
    @Value("openAMConnectorConfigurator{openam.connector.password:@null}")
    private String password;
    @Value("openAMConnectorConfigurator{openam.connector.password_key:@null}")
    private String passwordKey;

    /**
     * @return {@link String}
     */
    @Override
    public String getUserName() {
        return this.userName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getUserNameKey() {
        return this.userNameKey = (((this.userNameKey != null) && !(this.userNameKey.isEmpty()))
                ? this.userNameKey : "X-OpenAM-Username");
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPasswordKey() {
        return this.passwordKey = (((this.passwordKey != null) && !(this.passwordKey.isEmpty()))
                ? this.passwordKey : "X-OpenAM-Password");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  userName = '" + userName + '\'' +
                "  userNameKey = '" + getUserNameKey() + '\'' +
                ", password = '" + password + '\'' +
                ", passwordKey = '" + getPasswordKey() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.userName != null)
                && !(this.userName.isEmpty())), "The UserName Parameter must not be "
                + "null or an empty value.");

        Preconditions.checkArgument(((this.password != null)
                && !(this.password.isEmpty())), "The Password Parameter must not be "
                + "null or an empty value.");
    }
}
