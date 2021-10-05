package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;
import org.geosdi.geoplatform.connector.geoserver.model.file.IGPFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.update.GPParameterUpdate;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverStringQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GeoserverRXQueryParamConsumer;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverUpdateCoverageStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;

import javax.annotation.Nonnull;
import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.create;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
public class GPGeoserverUpdateCoverageStoreWithStoreName extends GPJsonPutConnectorRequest<GPCoverageResponse, GeoserverUpdateCoverageStoreWithStoreNameRequest> implements GeoserverUpdateCoverageStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName = withInitial(() -> null);
    private final ThreadLocal<String> storeName = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverUploadMethod> methodName = withInitial(() -> null);
    private final ThreadLocal<IGPFileExtension> formatName = withInitial(() -> null);
    private final ThreadLocal<File> file = withInitial(() -> null);
    private final ThreadLocal<GPParameterUpdate> update = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverStringQueryParam> configure = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverStringQueryParam> filename = withInitial(() -> null);
    private final ThreadLocal<GPGeoserverStringQueryParam> coverageName = withInitial(() -> null);

    /**
     * @param theServerConnector
     */
    GPGeoserverUpdateCoverageStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.storeName.set(theStore);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withMethod(@Nonnull(when = NEVER) GPGeoserverUploadMethod theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) IGPFileExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theParameterConfigure
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withConfigure(@Nonnull(when = NEVER) GPGeoserverParameterConfigure theParameterConfigure) {
        this.configure.set(new GPGeoserverStringQueryParam("configure", theParameterConfigure.toString()));
        return self();
    }

    /**
     * @param theUpdate
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withUpdate(@Nonnull(when = NEVER) GPParameterUpdate theUpdate) {
        this.update.set(theUpdate);
        return self();
    }

    /**
     * @param theFile
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withFile(@Nonnull(when = NEVER) File theFile) {
        this.file.set(theFile);
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withFileName(@Nonnull(when = NEVER) String theFileName) {
        this.filename.set(new GPGeoserverStringQueryParam("filename", theFileName));
        return self();
    }

    /**
     * @param theCoverageName
     * @return
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withCoverageName(@Nonnull(when = NEVER) String theCoverageName) {
        this.coverageName.set(new GPGeoserverStringQueryParam("coverageName", theCoverageName));
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
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())));
        URIBuilder uriBuilder = new URIBuilder(path);
        Consumer<ThreadLocal> consumer = new GeoserverRXQueryParamConsumer(uriBuilder);
        fromArray(this.update, this.configure, this.filename, this.coverageName)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c-> c.get() != null)
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<GPCoverageResponse>}
     */
    @Override
    protected Class<GPCoverageResponse> forClass() {
        return GPCoverageResponse.class;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        File fileToUpload = this.file.get();
        checkArgument((fileToUpload != null) && (fileToUpload.exists() && !(fileToUpload.isDirectory())), "The Parameter file must not be null, must exist and must not be a directory.");
        FileEntity builder = new FileEntity(fileToUpload, create("image/geotiff"));
        return builder;
    }

    /**
     * @param httpMethod
     */
    @Override
    protected void addHeaderParams(HttpUriRequest httpMethod) {
        httpMethod.addHeader("Content-Type", "image/geotiff");
    }
}