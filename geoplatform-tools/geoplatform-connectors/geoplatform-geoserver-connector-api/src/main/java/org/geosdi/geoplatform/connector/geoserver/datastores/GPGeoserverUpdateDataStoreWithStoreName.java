package org.geosdi.geoplatform.connector.geoserver.datastores;

import com.google.common.io.CharStreams;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;

import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.file.GPGeoserverDataStoreFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.file.IGPFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.request.datastores.GeoserverUpdateDataStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
public class GPGeoserverUpdateDataStoreWithStoreName extends GPJsonPutConnectorRequest<Boolean, GeoserverUpdateDataStoreWithStoreNameRequest> implements GeoserverUpdateDataStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName = withInitial(() -> null);
    private final ThreadLocal<String> storeName = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverUploadMethod> methodName = withInitial(() -> null);
    private final ThreadLocal<IGPFileExtension> formatName = withInitial(() -> null);
    private final ThreadLocal<File> file = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverParameterConfigure> configure = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverDataStoreFileExtension> target = withInitial(() -> null);
    private final ThreadLocal<String> update = withInitial(() -> null);
    private final ThreadLocal<String> charset = withInitial(() -> null);
    private final ThreadLocal<String> filename = withInitial(() -> null);

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    GPGeoserverUpdateDataStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
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
    public GeoserverUpdateDataStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) IGPFileExtension theFormat) {
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
    public GeoserverUpdateDataStoreWithStoreNameRequest withUpdate(@Nonnull(when = NEVER) String theUpdate) {
        this.update.set(theUpdate);
        return self();
    }

    /**
     * @param theCharset
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withCharset(@Nonnull(when = NEVER) String theCharset) {
        this.charset.set(theCharset);
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverUpdateDataStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateDataStoreWithStoreNameRequest withFileName(@Nonnull(when = NEVER) String theFileName) {
        this.filename.set(theFileName);
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
        IGPFileExtension format = this.formatName.get();
        checkArgument((format != null), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/datastores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())));
        URIBuilder uriBuilder = new URIBuilder(path);
        GPGeoserverParameterConfigure configure = this.configure.get();
        GPGeoserverDataStoreFileExtension target = this.target.get();
        String update = this.update.get();
        String charset = this.charset.get();
        String filename = this.filename.get();
        if(configure != null)
            uriBuilder.addParameter("configure", configure.toString());
        if(target != null)
            uriBuilder.addParameter("target", target.toString());
        if(update != null && !(update.trim().isEmpty()))
            uriBuilder.addParameter("update", update);
        if(charset != null && !(charset.trim().isEmpty()))
            uriBuilder.addParameter("charset", charset);
        if(filename != null && !(filename.trim().isEmpty()))
            uriBuilder.addParameter("filename", filename);
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
