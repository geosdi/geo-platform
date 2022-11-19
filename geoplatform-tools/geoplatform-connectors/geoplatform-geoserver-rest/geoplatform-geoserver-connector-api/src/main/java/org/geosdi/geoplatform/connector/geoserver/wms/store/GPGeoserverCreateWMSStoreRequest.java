/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.geoserver.wms.store;

import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverCreateWMSStoreResponse;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GPGeoserverWMSStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.wms.GeoserverCreateWMSStoreResponse;
import org.geosdi.geoplatform.connector.geoserver.request.wms.store.GeoserverCreateWMSStoreRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverCreateWMSStoreRequest extends GPJsonPostConnectorRequest<GeoserverCreateWMSStoreResponse, GeoserverCreateWMSStoreRequest> implements GeoserverCreateWMSStoreRequest {

    private final ThreadLocal<String> workspace = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverWMSStoreBody> body = withInitial(() -> null);

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverCreateWMSStoreRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateWMSStoreRequest}
     */
    @Override
    public GeoserverCreateWMSStoreRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * @param theBody
     * @return {@link GeoserverCreateWMSStoreRequest}
     */
    @Override
    public GeoserverCreateWMSStoreRequest withBody(@Nonnull(when = NEVER) GPGeoserverWMSStoreBody theBody) {
        this.body.set(theBody);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/wmsstores") : baseURI.concat("/workspaces/").concat(workspace).concat("/wmsstores")));
    }

    /**
     * @return {@link Class<GeoserverCreateWMSStoreResponse>}
     */
    @Override
    protected Class<GeoserverCreateWMSStoreResponse> forClass() {
        return GeoserverCreateWMSStoreResponse.class;
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        super.checkHttpResponseStatus(statusCode);
        switch (statusCode) {
            case 500:
                throw new IllegalStateException("Store '".concat(this.body.get().getName()
                        .concat("' already exist in workspace '".concat(this.workspace.get()).concat("'."))));
        }
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected GeoserverCreateWMSStoreResponse readInternal(BufferedReader reader) throws Exception {
        return GPGeoserverCreateWMSStoreResponse.builder()
                .storeName(reader.lines().collect(joining()))
                .build();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverWMSStoreBody wmsStoreBody = this.body.get();
        checkArgument(wmsStoreBody != null, "The Parameter wmsStoreBody must not be null.");
        String wmsStoreBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(wmsStoreBody);
        logger.debug("#############################WMS_STORE_BODY : \n{}\n", wmsStoreBodyString);
        return new StringEntity(wmsStoreBodyString, APPLICATION_JSON);
    }
}