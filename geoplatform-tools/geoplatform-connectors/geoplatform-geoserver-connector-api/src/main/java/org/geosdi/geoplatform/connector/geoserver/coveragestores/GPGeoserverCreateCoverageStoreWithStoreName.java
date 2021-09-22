package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import org.apache.hc.core5.http.HttpEntity;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverCreateCoverageStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverCreateCoverageStoreWithStoreName extends GPJsonPostConnectorRequest<Boolean, GeoserverCreateCoverageStoreWithStoreNameRequest> implements GeoserverCreateCoverageStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<String> methodName;
    private final ThreadLocal<String> formatName;

    public GPGeoserverCreateCoverageStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
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
    public GeoserverCreateCoverageStoreWithStoreNameRequest withMethod(@Nonnull(when = NEVER) String theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GeoserverCreateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverCreateCoverageStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) String theFormat) {
        this.formatName.set(theFormat);
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
        String method = this.methodName.get();
        checkArgument((method != null) && !(method.trim().isEmpty()), "The Parameter method must not be null or an empty string.");
        String format = this.formatName.get();
        checkArgument((format != null) && !(format.trim().isEmpty()), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method).concat(".").concat(format)
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method).concat(".").concat(format)));
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        return null;
    }
}
