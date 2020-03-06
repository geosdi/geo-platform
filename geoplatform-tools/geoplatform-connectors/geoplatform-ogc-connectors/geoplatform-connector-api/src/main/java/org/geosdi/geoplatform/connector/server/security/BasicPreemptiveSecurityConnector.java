/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.apache.hc.client5.http.impl.auth.BasicScheme;
import org.apache.hc.core5.http.HttpHost;

import javax.annotation.Nonnull;
import java.net.URI;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BasicPreemptiveSecurityConnector extends PreemptiveSecurityConnector {

    /**
     * @param theUserName
     * @param thePassword
     */
    public BasicPreemptiveSecurityConnector(@Nonnull(when = NEVER) String theUserName, @Nonnull(when = NEVER) String thePassword) {
        super(theUserName, thePassword);
    }

    /**
     * Create an instance for {@link BasicScheme} Class
     *
     * @return BasicScheme
     */
    @Override
    protected BasicScheme createScheme() {
        return new BasicScheme();
    }

    /**
     * @param targetHost
     * @param targetURI
     * @throws Exception
     */
    @Override
    protected void bindCredentials(@Nonnull(when = NEVER) HttpHost targetHost, @Nonnull(when = NEVER) URI targetURI) throws Exception {
        super.bindCredentials(targetHost, targetURI);
        BasicScheme basicAuth = this.createScheme();
        basicAuth.initPreemptive(this.usernamePasswordCredentials);
        localContext.resetAuthExchange(targetHost, basicAuth);
    }
}