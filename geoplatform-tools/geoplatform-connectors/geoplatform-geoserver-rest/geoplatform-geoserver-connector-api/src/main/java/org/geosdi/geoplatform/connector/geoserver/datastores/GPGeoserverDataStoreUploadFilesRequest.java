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
package org.geosdi.geoplatform.connector.geoserver.datastores;

import com.google.common.io.CharStreams;
import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.file.GPGeoserverDataStoreFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.update.GPParameterUpdate;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverDataStoreUploadFilesRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.connector.uri.GPConnectorStringQueryParam;
import org.geosdi.geoplatform.connector.uri.GPConnectorRXQueryParamConsumer;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure.ALL;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 *
 * <p>Uploads files to the data store, creating it if necessary</p>
 */
@ThreadSafe
class GPGeoserverDataStoreUploadFilesRequest extends GPJsonPutConnectorRequest<Boolean, GeoserverDataStoreUploadFilesRequest> implements GeoserverDataStoreUploadFilesRequest {

    private final ThreadLocal<String> workspaceName = withInitial(() -> null);
    private final ThreadLocal<String> storeName = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverUploadMethod> methodName = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverDataStoreFileExtension> formatName = withInitial(() -> null);
    private final ThreadLocal<File> file = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverParameterConfigure> configure = withInitial(() -> ALL);
    private final ThreadLocal<GPGeoserverDataStoreFileExtension> target = withInitial(() -> null);
    private final ThreadLocal<GPParameterUpdate> update = withInitial(() -> null);
    private final ThreadLocal<GPConnectorStringQueryParam> charset = withInitial(() -> null);
    private final ThreadLocal<GPConnectorStringQueryParam> filename = withInitial(() -> null);

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverDataStoreUploadFilesRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @param theWorkspace
     * @return {@link GPGeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GPGeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.storeName.set(theStore);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GPGeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withMethod(@Nonnull(when = NEVER) GPGeoserverUploadMethod theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GPGeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withFormat(@Nonnull(when = NEVER) GPGeoserverDataStoreFileExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theParameterConfigure
     * @return {@link GPGeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withConfigure(@Nonnull(when = NEVER) GPGeoserverParameterConfigure theParameterConfigure) {
        this.configure.set((theParameterConfigure != null) ? theParameterConfigure : ALL);
        return self();
    }

    /**
     * @param theTarget
     * @return {@link GeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withTarget(@Nonnull(when = NEVER) GPGeoserverDataStoreFileExtension theTarget) {
        this.target.set(theTarget);
        return self();
    }

    /**
     * @param theUpdate
     * @return {@link GeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withUpdate(@Nonnull(when = NEVER) GPParameterUpdate theUpdate) {
        this.update.set(theUpdate);
        return self();
    }

    /**
     * @param theCharset
     * @return {@link GeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withCharset(@Nonnull(when = NEVER) String theCharset) {
        this.charset.set(new GPConnectorStringQueryParam("charset", theCharset));
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withFileName(@Nonnull(when = NEVER) String theFileName) {
        this.filename.set(new GPConnectorStringQueryParam("filename", theFileName));
        return self();
    }

    /**
     * @param theFile
     * @return {@link GeoserverDataStoreUploadFilesRequest}
     */
    @Override
    public GeoserverDataStoreUploadFilesRequest withFile(@Nonnull(when = NEVER) File theFile) {
        this.file.set(theFile);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspaceName.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        String store = this.storeName.get();
        checkArgument((store != null) && !(store.trim().isEmpty()), "The Parameter store must not be null or an empty string.");
        GPGeoserverUploadMethod method = this.methodName.get();
        checkArgument((method != null), "The Parameter method must not be null or an empty string.");
        GPGeoserverDataStoreFileExtension format = this.formatName.get();
        checkArgument((format != null), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())));
        URIBuilder uriBuilder = new URIBuilder(path);
        Consumer<ThreadLocal> consumer = new GPConnectorRXQueryParamConsumer(uriBuilder);
        fromArray(this.update, this.configure, this.filename, this.target, this.charset)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c-> c.get() != null)
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        ContentType contentType = this.methodName.get().toContentType();
        checkArgument(contentType != null, "The Parameter contentType must not be null.");
        File fileToUpload = this.file.get();
        checkArgument(fileToUpload != null, "The Parameter file must not be null.");
        return new FileEntity(fileToUpload, contentType);
    }

    /**
     * @param httpMethod
     */
    @Override
    protected final void addHeaderParams(HttpUriRequest httpMethod) {
        ContentType contentType = this.methodName.get().toContentType();
        checkArgument(contentType != null, "The Parameter contentType must not be null.");
        httpMethod.addHeader("Content-Type", contentType.getMimeType());
    }
}
