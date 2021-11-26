/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.extensions.importer;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.extension.importer.GPFileExpandType;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverBooleanQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GeoserverRXQueryParamConsumer;
import org.geosdi.geoplatform.connector.geoserver.request.extension.importer.GeoserverFileImporterRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
public class GPGeoserverFileImporterRequest extends GPJsonPostConnectorRequest<String, GeoserverFileImporterRequest> implements GeoserverFileImporterRequest {

    private final ThreadLocal<String> body;
    private final ThreadLocal<GPGeoserverBooleanQueryParam> async;
    private final ThreadLocal<GPGeoserverBooleanQueryParam> exec;
    private final ThreadLocal<GPFileExpandType> expand;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPGeoserverFileImporterRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.body = withInitial(null);
        this.async = withInitial(null);
        this.exec = withInitial(null);
        this.expand = withInitial(null);
    }

    /**
     * @param theBody
     */
    @Override
    public GeoserverFileImporterRequest withBody(@Nonnull(when = NEVER) String theBody) {
        this.body.set(theBody);
        return this;
    }

    /**
     * @param theExec
     * @return {@link GeoserverFileImporterRequest}
     */
    @Override
    public GeoserverFileImporterRequest withExec(@Nonnull(when = NEVER) Boolean theExec) {
        this.exec.set(new GPGeoserverBooleanQueryParam("exec", theExec));
        return this;
    }

    /**
     * @param theExpand
     * @return {@link GeoserverFileImporterRequest}
     */
    @Override
    public GeoserverFileImporterRequest withExpand(@Nonnull(when = NEVER) GPFileExpandType theExpand) {
        this.expand.set(theExpand);
        return this;
    }

    /**
     * @param theAsync
     * @return {@link GeoserverFileImporterRequest}
     */
    @Override
    public GeoserverFileImporterRequest withAsync(@Nonnull(when = NEVER) Boolean theAsync) {
        this.exec.set(new GPGeoserverBooleanQueryParam("async", theAsync));
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("imports") : baseURI.concat("/imports")));
        URIBuilder uriBuilder = new URIBuilder(path);
        Consumer<ThreadLocal> consumer = new GeoserverRXQueryParamConsumer(uriBuilder);
        fromArray(this.async, this.exec, this.expand)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c-> c.get() != null)
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    protected Class<String> forClass() {
        return String.class;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        String body = this.body.get();
        checkArgument(body != null, "The importerBody must not be null.");
        String workspaceBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(body);
        logger.debug("#############################IMPORTER_BODY : \n{}\n", workspaceBodyString);
        return new StringEntity(workspaceBodyString, APPLICATION_JSON);
    }
}
