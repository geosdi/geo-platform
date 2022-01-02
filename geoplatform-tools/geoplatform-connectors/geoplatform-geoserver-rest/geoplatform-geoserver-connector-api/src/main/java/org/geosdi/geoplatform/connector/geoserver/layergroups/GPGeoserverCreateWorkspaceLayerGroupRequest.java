/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.layergroups;

import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.layergroups.base.GPGeoserverBaseCreateLayerGroupRequest;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.GPGeoserverLayerGroupBody;
import org.geosdi.geoplatform.connector.geoserver.model.layergroups.response.GeoserverLayerGroupCreationResponse;
import org.geosdi.geoplatform.connector.geoserver.request.layergroups.GeoserverCreateWorkspaceLayerGroupRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;
import static org.geosdi.geoplatform.connector.geoserver.model.layergroups.response.GeoserverLayerGroupCreationResponse.toResponse;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverCreateWorkspaceLayerGroupRequest extends GPGeoserverBaseCreateLayerGroupRequest<GPGeoserverLayerGroupBody, GeoserverCreateWorkspaceLayerGroupRequest> implements GeoserverCreateWorkspaceLayerGroupRequest {

    private final ThreadLocal<String> workspace = withInitial(() -> null);

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverCreateWorkspaceLayerGroupRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverCreateWorkspaceLayerGroupRequest}
     */
    @Override
    public GeoserverCreateWorkspaceLayerGroupRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.workspace.set(theWorkspaceName);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspace.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()), "The Parameter workspaceName must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return (baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/layergroups") : baseURI.concat("/workspaces/").concat(workspaceName).concat("/layergroups"));
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverLayerGroupBody layerGroupBody = this.body.get();
        checkArgument(layerGroupBody != null, "The Parameter layerGroupBody must not be null.");
        String layerGroupBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(layerGroupBody);
        logger.debug("#############################LAYER_GROUP_BODY : \n{}\n", layerGroupBodyString);
        return new StringEntity(layerGroupBodyString, APPLICATION_JSON);
    }

    /**
     * @param reader
     * @return {@link GeoserverLayerGroupCreationResponse}
     * @throws Exception
     */
    @Override
    protected GeoserverLayerGroupCreationResponse readInternal(BufferedReader reader) throws Exception {
        String response = reader.lines().collect(joining());
        return ((response != null) && !(response.trim().isEmpty()) && (response.equalsIgnoreCase(this.body.get().getName()))
                ? toResponse(TRUE, "LayerGroup : " + response + " has been created.") : toResponse(FALSE, response));
    }
}