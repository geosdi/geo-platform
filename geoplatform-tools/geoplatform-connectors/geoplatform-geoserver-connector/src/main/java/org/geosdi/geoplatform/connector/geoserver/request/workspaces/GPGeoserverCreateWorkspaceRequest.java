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
package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceResponse;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverCreareWorkspaceResponse;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.ThreadLocal.withInitial;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverCreateWorkspaceRequest extends GPJsonPostConnectorRequest<IGPGeoserverCreareWorkspaceResponse> implements GeoserverCreateWorkspaceRequest {

    private final ThreadLocal<GPGeoserverCreateWorkspaceBody> workspaceBody;
    private final ThreadLocal<Boolean> defaultWorkspace;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverCreateWorkspaceRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceBody = withInitial(() -> null);
        this.defaultWorkspace = withInitial(() -> FALSE);
    }

    /**
     * @param theWorkspaceBody
     */
    @Override
    public GeoserverCreateWorkspaceRequest withWorkspaceBody(GPGeoserverCreateWorkspaceBody theWorkspaceBody) {
        this.workspaceBody.set(theWorkspaceBody);
        return this;
    }

    /**
     * @param theDefaultWorkspace
     */
    @Override
    public GeoserverCreateWorkspaceRequest withDefaultWorkspace(Boolean theDefaultWorkspace) {
        this.defaultWorkspace.set(theDefaultWorkspace != null ? theDefaultWorkspace : FALSE);
        return this;
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401:
                throw new IllegalStateException("Unable to add workspace as it already exists.");
            case 404:
                throw new ResourceNotFoundException();
        }
    }

    /**
     * @param reader
     * @return {@link IGPGeoserverCreareWorkspaceResponse}
     * @throws Exception
     */
    @Override
    protected IGPGeoserverCreareWorkspaceResponse readInternal(BufferedReader reader) throws Exception {
        return GPGeoserverCreateWorkspaceResponse.builder()
                .workspaceName(reader.lines().collect(joining()))
                .build();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverCreateWorkspaceBody workspaceBody = this.workspaceBody.get();
        checkArgument(workspaceBody != null, "The workspaceBody must not be null.");
        String workspaceBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(workspaceBody);
        logger.debug("#############################WORKSPACE_BODY : \n{}\n", workspaceBodyString);
        return new StringEntity(workspaceBodyString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces?default=").concat(this.defaultWorkspace.get().toString())
                : baseURI.concat("/workspaces?default=").concat(this.defaultWorkspace.get().toString())));
    }

    /**
     * @return {@link Class<IGPGeoserverCreareWorkspaceResponse>}
     */
    @Override
    protected Class<IGPGeoserverCreareWorkspaceResponse> forClass() {
        return IGPGeoserverCreareWorkspaceResponse.class;
    }
}