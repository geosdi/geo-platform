/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.exsist;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.geosdi.geoplatform.connector.geoserver.request.exsist.GeoserverExsistRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGeoserverExsistRequest<T, ConnectorRequest extends GPJsonConnectorRequest> extends GPJsonGetConnectorRequest<T, ConnectorRequest> implements GeoserverExsistRequest {

    private final ThreadLocal<Boolean> exist = withInitial(() -> null);
    private final ThreadLocal<T> response = withInitial(() -> null);

    /**
     * @param server
     * @param theJacksonSupport
     */
    protected GPGeoserverExsistRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean exist() throws Exception {
        return (this.exist.get() != null ? this.exist.get() : this.getResponse() != null);
    }

    /**
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T getResponse() throws Exception {
        return (this.response.get() != null ? this.response.get() : super.getResponse());
    }

    /**
     * A {@code 404} status means the resource does not exist: for an "exist" request this is an
     * expected outcome, so it is mapped to {@code exist = FALSE} without attempting to parse the
     * error body. Any other failure (unauthorized, unexpected/invalid payload, 5xx, ...) is a real
     * error and is left to propagate, instead of being silently reported as "resource absent".
     *
     * @param httpResponse
     * @return {@link T}
     */
    @Override
    protected T internalResponseAsEntity(ClassicHttpResponse httpResponse) {
        if (httpResponse.getCode() == 404) {
            this.exist.set(FALSE);
            this.response.set(null);
            return null;
        }
        T value = super.internalResponseAsEntity(httpResponse);
        this.response.set(value);
        this.exist.set((value != null) ? TRUE : FALSE);
        return value;
    }

    /**
     * @return {@link ConnectorRequest}
     */
    @Override
    protected final ConnectorRequest self() {
        this.clearThreadLocals();
        return super.self();
    }

    /**
     * Invalidates the cached result on each (re)configuration of the request. Uses {@link
     * ThreadLocal#remove()} (rather than {@code set(null)}) so the entries are released from the
     * calling thread's {@code ThreadLocalMap}: this keeps the request safe to reuse/share across
     * threads without retaining stale state or leaking references in pooled-thread environments.
     */
    private void clearThreadLocals() {
        this.exist.remove();
        this.response.remove();
    }
}