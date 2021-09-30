package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.geosdi.geoplatform.connector.geoserver.model.configure.GPGeoserverParameterConfigure;

import org.geosdi.geoplatform.connector.geoserver.model.file.IGPFileExtension;
import org.geosdi.geoplatform.connector.geoserver.model.update.GPParameterUpdate;
import org.geosdi.geoplatform.connector.geoserver.model.upload.GPGeoserverUploadMethod;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverUpdateCoverageStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;

import javax.annotation.Nonnull;
import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
public class GPGeoserverUpdateCoverageStoreWithStoreName extends GPJsonPutConnectorRequest<GPCoverageResponse, GeoserverUpdateCoverageStoreWithStoreNameRequest> implements GeoserverUpdateCoverageStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<GPGeoserverUploadMethod> methodName;
    private final ThreadLocal<IGPFileExtension> formatName;
    private final ThreadLocal<File> file;
    private final ThreadLocal<GPParameterUpdate> update;
    private final ThreadLocal<String> configure;
    private final ThreadLocal<String> filename;
    private final ThreadLocal<String> coverageName;

    /**
     * @param theServerConnector
     */
    GPGeoserverUpdateCoverageStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
        this.file = withInitial(() -> null);
        this.update = withInitial(() -> null);
        this.configure = withInitial(() -> null);
        this.filename = withInitial(() -> null);
        this.coverageName = withInitial(() -> null);
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
        this.configure.set(theParameterConfigure.toString());
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
        this.filename.set(theFileName);
        return self();
    }

    /**
     * @param theCoverageName
     * @return
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withCoverageName(@Nonnull(when = NEVER) String theCoverageName) {
        this.coverageName.set(theCoverageName);
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
        GPParameterUpdate update = this.update.get();
        String configure = this.configure.get();
        String filename = this.filename.get();
        String coverageName = this.coverageName.get();
        if(update != null)
            uriBuilder.addParameter("update", update.toString());
        if(configure != null && !(configure.trim().isEmpty()))
            uriBuilder.addParameter("configure", configure);
        if(filename != null && !(filename.trim().isEmpty()))
            uriBuilder.addParameter("filename", filename);
        if(coverageName != null && !(coverageName.trim().isEmpty()))
            uriBuilder.addParameter("coverageName", coverageName);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<Boolean>}
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
        checkArgument(fileToUpload != null, "The Parameter file must not be null.");
        FileEntity builder = new FileEntity(fileToUpload, ContentType.create("image/geotiff"));
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