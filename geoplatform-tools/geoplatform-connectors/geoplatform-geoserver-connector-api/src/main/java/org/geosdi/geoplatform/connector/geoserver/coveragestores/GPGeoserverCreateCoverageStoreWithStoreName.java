package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverCreateCoverageStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverCreateCoverageStoreWithStoreName extends GPJsonPostConnectorRequest<Boolean, GeoserverCreateCoverageStoreWithStoreNameRequest> implements GeoserverCreateCoverageStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<GPUploadMethod> methodName;
    private final ThreadLocal<GPCoverateStoreExtension> formatName;
    private final ThreadLocal<TreeMap<String, Object>> queryStringMap;

     GPGeoserverCreateCoverageStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
         this.queryStringMap = withInitial(() -> new TreeMap<>());
     }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        this.queryStringMap.set(new TreeMap<>());
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.storeName.set(theStore);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withMethod(@Nonnull(when = NEVER) GPUploadMethod theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) GPCoverateStoreExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withFileName(@Nonnull(when = NEVER) String theFileName) {
        this.queryStringMap.get().put("filename", theFileName);
        return self();
    }


    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withUpdateBbox(
            @Nonnull(when = NEVER) Boolean theUpdateBbox) {
        this.queryStringMap.get().put("updateBBox", theUpdateBbox);
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
        GPUploadMethod method = this.methodName.get();
        checkArgument((method != null), "The Parameter method must not be null or an empty string.");
        GPCoverateStoreExtension format = this.formatName.get();
        checkArgument((format != null), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())));
        String queryString = this.queryStringMap.get().entrySet().stream().filter(Objects::nonNull)
                .map(e -> e.getKey().concat("=").concat(e.getValue().toString()))
                .map(Object::toString)
                .collect(Collectors.joining("&"));
        return path.concat( queryString != null ? "?".concat(queryString) : "");
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
        GPUploadMethod method = this.methodName.get();
        if(method == GPUploadMethod.FILE){

        }
        return null;
    }
}