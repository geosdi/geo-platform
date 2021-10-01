package org.geosdi.geoplatform.connector.geoserver.datastores;

import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.file.GPGeoserverDataStoreFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.update.GPParameterUpdate;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverStringQueryParam;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverUpdateDataStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.File;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverUpdateDataStoreWithStoreName extends GPJsonPutConnectorRequest<Boolean, GeoserverUpdateDataStoreWithStoreNameRequest> implements GeoserverUpdateDataStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<GPGeoserverUploadMethod> methodName;
    private final ThreadLocal<GPGeoserverDataStoreFileExtension> formatName;
    private final ThreadLocal<File> file;
    private final ThreadLocal<GPGeoserverParameterConfigure> configure;
    private final ThreadLocal<GPGeoserverDataStoreFileExtension> target;
    private final ThreadLocal<GPParameterUpdate> update;
    private final ThreadLocal<GPGeoserverStringQueryParam> charset;
    private final ThreadLocal<GPGeoserverStringQueryParam> filename;


    GPGeoserverUpdateDataStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
        this.file = withInitial(() -> null);
        this.configure = withInitial(() -> null);
        this.target = withInitial(() -> null);
        this.update = withInitial(() -> null);
        this.charset = withInitial(() -> null);
        this.filename = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GPGeoserverUpdateDataStoreWithStoreName}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GPGeoserverUpdateDataStoreWithStoreName}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.storeName.set(theStore);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GPGeoserverUpdateDataStoreWithStoreName}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withMethod(@Nonnull(when = NEVER) GPGeoserverUploadMethod theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GPGeoserverUpdateDataStoreWithStoreName}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) GPGeoserverDataStoreFileExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theParameterConfigure
     * @return {@link GPGeoserverUpdateDataStoreWithStoreName}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withConfigure(@Nonnull(when = NEVER) GPGeoserverParameterConfigure theParameterConfigure) {
        this.configure.set(theParameterConfigure);
        return self();
    }

    /**
     * @param theTarget
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withTarget(@Nonnull(when = NEVER) GPGeoserverDataStoreFileExtension theTarget) {
        this.target.set(theTarget);
        return self();
    }

    /**
     * @param theUpdate
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withUpdate(@Nonnull(when = NEVER) GPParameterUpdate theUpdate) {
        this.update.set(theUpdate);
        return self();
    }

    /**
     * @param theCharset
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withCharset(@Nonnull(when = NEVER) String theCharset) {
        this.charset.set(new GPGeoserverStringQueryParam("charset", theCharset));
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withFileName(@Nonnull(when = NEVER) String theFileName) {
        this.filename.set(new GPGeoserverStringQueryParam("filename", theFileName));
        return self();
    }

    /**
     * @param theFile
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withFile(@Nonnull(when = NEVER) File theFile) {
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
        logger.info("##########{}\n", asList(this.update, this.configure, this.filename, this.target, this.charset));

        fromIterable(asList(this.update, this.configure, this.filename, this.target, this.charset))
                .filter(Objects::nonNull)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .subscribe(c -> c.get().addQueryParam(uriBuilder), ex -> logger.error("###################{}\n", ex.getMessage()));
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
        String contentType = this.methodName.get().getContentType();
        checkArgument(contentType != null && !(contentType.trim().isEmpty()), "The Parameter contentType must not be null.");
        File fileToUpload = this.file.get();
        checkArgument(fileToUpload != null, "The Parameter file must not be null.");
        FileEntity fileEntity = new FileEntity(fileToUpload, ContentType.create(contentType));
        return fileEntity;
    }

    /**
     * @param httpMethod
     */
    @Override
    protected final void addHeaderParams(HttpUriRequest httpMethod) {
        String contentType = this.methodName.get().getContentType();
        checkArgument(contentType != null && !(contentType.trim().isEmpty()), "The Parameter contentType must not be null.");
        httpMethod.addHeader("Content-Type", contentType);
    }
}