/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.styles.base;

import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverCreateStyleResponse;
import org.geosdi.geoplatform.connector.geoserver.model.styles.IGPGeoserverCreateStyleResponse;
import org.geosdi.geoplatform.connector.geoserver.request.styles.base.GeoserverBaseCreateStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static java.lang.ThreadLocal.withInitial;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverBaseCreateStyleRequest<StyleBody, R extends GeoserverBaseCreateStyleRequest> extends GPJsonPostConnectorRequest<IGPGeoserverCreateStyleResponse, R> implements GeoserverBaseCreateStyleRequest<StyleBody, R> {

    protected final ThreadLocal<StyleBody> styleBody;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPGeoserverBaseCreateStyleRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.styleBody = withInitial(() -> null);
    }

    /**
     * @param theStyleBody
     * @return {@link R}
     */
    @Override
    public R withStyleBody(@Nonnull(when = NEVER) StyleBody theStyleBody) {
        this.styleBody.set(theStyleBody);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return (baseURI.endsWith("/") ? baseURI.concat("styles") : baseURI.concat("/styles"));
    }

    /**
     * @param reader
     * @return {@link IGPGeoserverCreateStyleResponse}
     * @throws Exception
     */
    @Override
    protected IGPGeoserverCreateStyleResponse readInternal(BufferedReader reader) throws Exception {
        return GPGeoserverCreateStyleResponse.builder()
                .styleName(reader.lines().collect(joining()))
                .build();
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401:
            case 500:
                throw new IllegalStateException("Unable to add Style because it already exists.");
            case 404:
                throw new ResourceNotFoundException();
            case 405:
                throw new IllegalStateException("Method not allowed");
        }
    }

    /**
     * @return {@link Class< IGPGeoserverCreateStyleResponse >}
     */
    @Override
    protected Class<IGPGeoserverCreateStyleResponse> forClass() {
        return IGPGeoserverCreateStyleResponse.class;
    }
}