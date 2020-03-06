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

import org.apache.hc.client5.http.auth.AuthCache;
import org.apache.hc.client5.http.auth.AuthScheme;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.HttpHost;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;
import java.net.URI;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.client5.http.protocol.HttpClientContext.create;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PreemptiveSecurityConnector extends AbstractSecurityConnector {

    private HttpHost httpHost;
    protected AuthCache authCache;
    protected HttpClientContext localContext;

    /**
     * @param theUserName
     * @param thePassword
     */
    PreemptiveSecurityConnector(@Nonnull(when = NEVER) String theUserName, @Nonnull(when = NEVER) String thePassword) {
        super(theUserName, thePassword);
    }

    /**
     * @param connectorRequest
     * @param httpRequest
     * @param <C>
     * @param <H>
     * @return {@link CloseableHttpResponse}
     * @throws Exception
     */
    @Override
    public <C extends GPConnectorRequest, H extends HttpUriRequest> CloseableHttpResponse secure(@Nonnull(when = NEVER) C connectorRequest, @Nonnull(when = NEVER) H httpRequest) throws Exception {
        checkArgument(connectorRequest != null, "The Parameter connectorRequest must not be null.");
        checkArgument(httpRequest != null, "The Parameter httpRequest must not be null.");
        this.localContext = create();
        HttpHost targetHost = this.extractHost(connectorRequest.getURI());
        this.bindCredentials(httpHost, connectorRequest.getURI());
        return connectorRequest.getClientConnection().execute(targetHost, httpRequest, localContext);
    }

    /**
     * @param uri
     * @return
     */
    protected HttpHost extractHost(@Nonnull(when = NEVER) URI uri) {
        checkArgument(uri != null, "The Parameter uri must not be null.");
        if (this.httpHost == null) {
            this.httpHost = new HttpHost(uri.getScheme(), uri.getHost(), this.retrieveNoSetPort(uri));
        }
        return this.httpHost;
    }

    /**
     * Create an instance for {@link Scheme} Class
     *
     * @return AuthScheme
     */
    protected abstract <Scheme extends AuthScheme> Scheme createScheme();

    /**
     * If the URI don't have e port, retrieve the standard port wrt scheme
     * ["http" or "https"].
     */
    private int retrieveNoSetPort(URI uri) {
        int port = uri.getPort();
        if (port > 0) {
            return port;
        }
        String scheme = uri.getScheme();
        switch (scheme) {
            case "https":
                port = 443;
                break;
            case "http":
                port = 80;
                break;
            default:
                throw new IllegalStateException("Scheme doesn't recognize");
        }
        return port;
    }
}