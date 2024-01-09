/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.extensions.importer;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.GPFileExpandType;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.importer.GPGeoserverLoadImportResponse;
import org.geosdi.geoplatform.connector.geoserver.request.extensions.importer.GeoserverLoadImportRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.connector.uri.GPConnectorRXQueryParamConsumer;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
class GPGeoserverLoadImportRequest extends GPJsonGetConnectorRequest<GPGeoserverLoadImportResponse, GeoserverLoadImportRequest> implements GeoserverLoadImportRequest {

    private final ThreadLocal<Integer> id;
    private final ThreadLocal<GPFileExpandType> expand;

    /**
     *
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverLoadImportRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.id = withInitial(() -> null);
        this.expand = withInitial(() -> null);
    }

    /**
     * @param theId
     * @return {@link GeoserverLoadImportRequest}
     */
    @Override
    public GeoserverLoadImportRequest withId(@Nonnull(when = NEVER) Integer theId) {
        this.id.set(theId);
        return self();
    }

    /**
     * @param theExpand
     * @return {@link GeoserverLoadImportRequest}
     */
    @Override
    public GeoserverLoadImportRequest withExpand(@Nonnull(when = NEVER) GPFileExpandType theExpand) {
        this.expand.set(theExpand);
        return self();
    }

    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        Integer id = this.id.get();
        checkArgument(id != null && id >= 0, "The id must not be null or less than 0");
        String path = (baseURI.endsWith("/") ? baseURI.concat("imports/").concat(id.toString()) : baseURI.concat("/imports/").concat(id.toString()));
        URIBuilder uriBuilder = new URIBuilder(path);
        Consumer<ThreadLocal> consumer = new GPConnectorRXQueryParamConsumer(uriBuilder);
        fromArray(this.expand)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c-> c.get() != null)
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    @Override
    protected Class<GPGeoserverLoadImportResponse> forClass() {
        return GPGeoserverLoadImportResponse.class;
    }
}