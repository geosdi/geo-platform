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
package org.geosdi.geoplatform.connector.geoserver.layers;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverUpdateLayerRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
class GPGeoserverUpdateLayerRequest extends GPJsonPutConnectorRequest<Boolean, GeoserverUpdateLayerRequest> implements GeoserverUpdateLayerRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> layerName;
    private final ThreadLocal<GeoserverLayer> layerBody;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverUpdateLayerRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.layerName = withInitial(() -> null);
        this.layerBody = withInitial(() -> null);
    }

    /**
     * @param theStoreName
     * @return {@link GeoserverUpdateLayerRequest}
     */
    @Override
    public GeoserverUpdateLayerRequest withLayerName(@Nonnull(when = When.NEVER) String theStoreName) {
        this.layerName.set(theStoreName);
        return self();
    }

    /**
     * @param theWorkspaceName
     * @return {@link GeoserverUpdateLayerRequest}
     */
    @Override
    public GeoserverUpdateLayerRequest withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
        return self();
    }

    /**
     * @param theLayerBody
     * @return {@link GeoserverUpdateLayerRequest}
     */
    @Override
    public GeoserverUpdateLayerRequest withLayerBody(@Nonnull(when = NEVER) GeoserverLayer theLayerBody) {
        this.layerBody.set(theLayerBody);
        return self();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GeoserverLayer layerBody = this.layerBody.get();
        checkArgument(layerBody != null, "The layerBody must not be null.");
        String layerBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(layerBody);
        logger.debug("#############################LAYER_BODY : \n{}\n", layerBodyString);
        return new StringEntity(layerBodyString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()), "The Parameter workspaceName must not be null or an empty string.");
        String layerName = this.layerName.get();
        checkArgument((layerName != null) && !(layerName.trim().isEmpty()), "The Parameter layerName must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("/layers/").concat(layerName)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("/layers/").concat(layerName)));
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        try {
            String value = CharStreams.toString(reader);
            return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
            return FALSE;
        }
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}