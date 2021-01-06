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
package org.geosdi.geoplatform.connector.api;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @param <T>
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPConnectorStore<T extends GPServerConnector> implements GeoPlatformConnector {

    private static final long serialVersionUID = 5909915133353917422L;
    //
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final Date registrationDate = new Date();
    protected final T server;

    /**
     * @param theServer
     */
    protected GPConnectorStore(@Nonnull(when = NEVER) T theServer) {
        checkArgument(theServer != null, "The Parameter server must not be null.");
        this.server = theServer;
    }

    /**
     * @return {@link Date}
     */
    @Override
    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    /**
     * @return {@link GPSecurityConnector}
     */
    @Override
    public GPSecurityConnector getSecurityConnector() {
        return server.getSecurityConnector();
    }

    /**
     * @return {@link URI}
     */
    @Override
    public URI getURI() {
        return server.getURI();
    }

    /**
     * @return {@link URL}
     */
    @Override
    public URL getURL() {
        return server.getURL();
    }

    /**
     * @throws Exception
     */
    @Override
    public void dispose() throws Exception {
        logger.debug("########################## Disposing GeoPlatformConnector [ {} ]", this);
        this.server.dispose();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "registrationDate = " + registrationDate
                + ", server = " + server + '}';
    }
}
