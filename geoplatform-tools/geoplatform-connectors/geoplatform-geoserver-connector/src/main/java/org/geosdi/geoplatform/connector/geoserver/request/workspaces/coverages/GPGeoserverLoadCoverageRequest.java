package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverageInfo;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadCoverageRequest extends GPJsonGetConnectorRequest<GPGeoserverCoverageInfo> implements GeoserverLoadCoverageRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> coverage;
    private final ThreadLocal<Boolean> quietOnNotFound;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadCoverageRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.coverage = withInitial(() -> null);
        this.quietOnNotFound = withInitial(() -> TRUE);
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoverageRequest}
     */
    @Override
    public GeoserverLoadCoverageRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * @param theCoverage
     * @return
     */
    @Override
    public GeoserverLoadCoverageRequest withCoverage(@Nonnull(when = NEVER) String theCoverage) {
        this.coverage.set(theCoverage);
        return this;
    }

    /**
     * @param theQuietOnNotFound
     * @return {@link GeoserverLoadCoverageRequest}
     */
    @Override
    public GeoserverLoadCoverageRequest withQuietOnNotFound(@Nullable Boolean theQuietOnNotFound) {
        this.quietOnNotFound.set((theQuietOnNotFound != null) ? theQuietOnNotFound : TRUE);
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string");
        String coverage = this.coverage.get();
        checkArgument((coverage != null) && !(coverage.trim().isEmpty()), "The Parameter coverage must not be null or an empty string.");
        coverage = coverage.concat(".json");
        String quietOnNotFound = this.quietOnNotFound.get().toString();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coverages/").concat(coverage).concat("?quietOnNotFound=").concat(quietOnNotFound)
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coverages/").concat(coverage).concat("?quietOnNotFound=").concat(quietOnNotFound)));
    }

    /**
     * @return {@link Class<GPGeoserverCoverageInfo>}
     */
    @Override
    protected Class<GPGeoserverCoverageInfo> forClass() {
        return GPGeoserverCoverageInfo.class;
    }
}