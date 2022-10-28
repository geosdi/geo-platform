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
package org.geosdi.geoplatform.connector.geowebcache.reloading;

import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.geosdi.geoplatform.connector.geowebcache.request.reloading.GeowebcacheReloadingRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
class GPGeowebcacheReloadingRequest extends GPJsonPostConnectorRequest<String, GeowebcacheReloadingRequest> implements GeowebcacheReloadingRequest {

    private ThreadLocal<String> configurationName;

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeowebcacheReloadingRequest(@Nonnull(when = NEVER) GPServerConnector server,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.configurationName = withInitial(() -> null);
    }

    /**
     * @param configurationName
     * @return {@link  GeowebcacheReloadingRequest}
     */
    @Override
    public GeowebcacheReloadingRequest withConfigurationName(String configurationName) {
        this.configurationName.set(configurationName);
        return self();
    }

    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("/reload") : baseURI.concat("/reload")));
    }

    /**
     * @return {@link  Class<Boolean>}
     */
    @Override
    protected Class<String> forClass() {
        return String.class;
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected String readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return value;
    }

    /**
     * @return {@link  HttpEntity}
     * @throws Exception
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        String configurationName = this.configurationName.get();
        logger.trace("#############################RELOADING_BODY : \n{}\n", configurationName);
        return new StringEntity(configurationName != null && !configurationName.trim().isEmpty() ? configurationName : "reload_configuration=1", APPLICATION_JSON) ;
    }
}