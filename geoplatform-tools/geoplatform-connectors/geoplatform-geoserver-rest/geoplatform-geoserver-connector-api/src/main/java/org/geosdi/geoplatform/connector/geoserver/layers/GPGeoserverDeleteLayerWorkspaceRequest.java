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
package org.geosdi.geoplatform.connector.geoserver.layers;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverDeleteLayerWorkspaceRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonDeleteConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
public class GPGeoserverDeleteLayerWorkspaceRequest extends GPJsonDeleteConnectorRequest<Boolean, GeoserverDeleteLayerWorkspaceRequest> implements GeoserverDeleteLayerWorkspaceRequest {

    private final ThreadLocal<String> workspaceName = withInitial(() -> null);
    private final ThreadLocal<String> layerName = withInitial(() -> null);
    private final ThreadLocal<Boolean> recurse =  withInitial(() -> FALSE);

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverDeleteLayerWorkspaceRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverDeleteLayerWorkspaceRequest}
     */
    @Override
    public GeoserverDeleteLayerWorkspaceRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
        return self();
    }

    /**
     * @param theWorkspaceName
     */
    @Override
    public GeoserverDeleteLayerWorkspaceRequest withLayerName(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.layerName.set(theWorkspaceName);
        return self();
    }

    /**
     * @param theRecurse
     */
    @Override
    public GeoserverDeleteLayerWorkspaceRequest withRecurse(@Nullable Boolean theRecurse) {
        this.recurse.set((theRecurse != null) ? theRecurse : FALSE);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()), "The Parameter workspaceName mut not be null or an Empty String.");
        String layerName = this.layerName.get();
        checkArgument((layerName != null) && !(layerName.trim().isEmpty()), "The Parameter layerName mut not be null or an Empty String.");
        String recurse = this.recurse.get().toString();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/layers/").concat(layerName).concat("?recurse=").concat(recurse)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/layers/").concat(layerName).concat("?recurse=").concat(recurse)));
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    @Override
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 403:
                throw new IllegalStateException("Workspace or related Namespace is not empty (and recurse not true).");
            case 404:
                throw new ResourceNotFoundException("Workspace doesn’t exist");
            case 405:
                throw new IllegalStateException("Can’t delete default workspace");
        }
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}