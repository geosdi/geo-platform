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
package org.geosdi.geoplatform.connector.server.security;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;

import javax.annotation.Nonnull;
import java.net.URI;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractSecurityConnector implements GPSecurityConnector {

    private final String username;
    private final String password;
    private AuthScope authScope;

    /**
     * @param theUserName
     * @param thePassword
     */
    public AbstractSecurityConnector(@Nonnull(when = NEVER) String theUserName, @Nonnull(when = NEVER) String thePassword) {
        checkArgument((theUserName != null) && !(theUserName.trim().isEmpty()), "The Parameter username must not be null or an Empty String.");
        checkArgument((thePassword != null) && !(thePassword.trim().isEmpty()), "The Parameter password must not be null or an Empty String.");
        this.username = theUserName;
        this.password = thePassword;
    }

    /**
     * Bind Credentials for {@link CredentialsProvider} class
     *
     * @param credentialsProvider
     * @param targetURI
     */
    protected void bindCredentials(@Nonnull(when = NEVER) CredentialsProvider credentialsProvider, @Nonnull(when = NEVER) URI targetURI) {
        checkArgument(credentialsProvider != null, "The Parameter credentialsProvider must not be null.");
        checkArgument(targetURI != null, "The Parameter targetURI must not be null.");
        if (this.authScope == null) {
            this.authScope = new AuthScope(targetURI.getHost(), targetURI.getPort());
        }
        credentialsProvider.setCredentials(authScope, new UsernamePasswordCredentials(username, password));
    }
}