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
package org.geosdi.geoplatform.connector.geoserver.request.store;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.store.service.GPGeoserverCreateStoreResponse;
import org.geosdi.geoplatform.connector.geoserver.model.store.service.GPGeoserverServiceStoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.store.service.GeoserverCreateStoreResponse;
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
public abstract class GPGeoserverCreateStoreRequest<Body extends GPGeoserverServiceStoreBody, R extends GeoserverCreateStoreRequest<Body, R>> extends GPJsonPostConnectorRequest<GeoserverCreateStoreResponse, R> implements GeoserverCreateStoreRequest<Body, R> {

    private final ThreadLocal<String> workspace = withInitial(() -> null);
    private final ThreadLocal<Body> body = withInitial(() -> null);
    private final String storeRestPath;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPGeoserverCreateStoreRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport, @Nonnull(when = NEVER) String theStoreRestPath) {
        super(theServerConnector, theJacksonSupport);
        checkArgument((theStoreRestPath != null) && !(theStoreRestPath.trim().isEmpty()), "The Parameter storeRestPath must not be null or an empty string.");
        this.storeRestPath = theStoreRestPath;
    }

    /**
     * @param theWorkspace
     * @return {@link R}
     */
    @Override
    public R withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return self();
    }

    /**
     * @param theBody
     * @return {@link R}
     */
    @Override
    public R withBody(@Nonnull(when = NEVER) Body theBody) {
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
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat(this.storeRestPath)
                : baseURI.concat("/workspaces/").concat(workspace).concat(this.storeRestPath)));
    }

    /**
     * @return {@link Class<GeoserverCreateStoreResponse>}
     */
    @Override
    protected Class<GeoserverCreateStoreResponse> forClass() {
        return GeoserverCreateStoreResponse.class;
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
    protected GPGeoserverCreateStoreResponse readInternal(BufferedReader reader) throws Exception {
        return GPGeoserverCreateStoreResponse.builder()
                .storeName(reader.lines().collect(joining()))
                .build();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        Body storeBody = this.body.get();
        checkArgument( storeBody != null, "The Parameter storeBody must not be null.");
        String wmsStoreBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(storeBody);
        logger.debug("#############################STORE_BODY : \n{}\n", wmsStoreBodyString);
        return new StringEntity(wmsStoreBodyString, APPLICATION_JSON);
    }
}