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
package org.geosdi.geoplatform.connector.geowebcache.seed;

import com.google.common.io.CharStreams;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.GPGeowebcacheSeedBody;
import org.geosdi.geoplatform.connector.geowebcache.request.seed.GeowebcacheSeedWithLayerBodyRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
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
public class GPGeowebcacheSeedWithLayerNameBodyRequest extends GPJsonPostConnectorRequest<Boolean, GeowebcacheSeedWithLayerBodyRequest> implements GeowebcacheSeedWithLayerBodyRequest {

    private final ThreadLocal<GPGeowebcacheSeedBody> body;
    private final ThreadLocal<String> layerName;

    /**
     * @param server
     * @param theJacksonSupport
     */
    protected GPGeowebcacheSeedWithLayerNameBodyRequest(@Nonnull(when = NEVER) GPServerConnector server,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.layerName = withInitial(() -> null);
        this.body = withInitial(() -> null);
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeowebcacheSeedBody body = this.body.get();
        checkArgument(body != null, "The body must not be null.");
        String bodyString = jacksonSupport.getDefaultMapper().writeValueAsString(body);
        logger.debug("#############################SEED_BODY : \n{}\n", bodyString);
        return new StringEntity(bodyString, APPLICATION_JSON);
    }

    /**
     * @param body
     * @return {@link GPGeowebcacheSeedWithLayerNameBodyRequest}
     */
    @Override
    public GeowebcacheSeedWithLayerBodyRequest withBody(GPGeowebcacheSeedBody body) {
        this.body.set(body);
        return self();
    }

    /**
     * @param layerName
     * @return {@link GeowebcacheSeedWithLayerBodyRequest}
     */
    @Override
    public GeowebcacheSeedWithLayerBodyRequest withLayerName(String layerName) {
        this.layerName.set(layerName);
        return self();
    }

    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        String layerName = this.layerName.get();
        checkArgument((layerName != null) && !(layerName.trim().isEmpty()), "The Parameter layerName must not be null or an empty string.");
        return ((baseURI.endsWith("/") ? baseURI.concat("seed/").concat(layerName).concat(".json") : baseURI.concat("/seed/").concat(layerName).concat(".json")));
    }

    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        logger.info("##########RESPONSE: {}\n\n", value);
        return ((value != null) && (!value.trim().isEmpty()) ? FALSE : TRUE);
    }
}
