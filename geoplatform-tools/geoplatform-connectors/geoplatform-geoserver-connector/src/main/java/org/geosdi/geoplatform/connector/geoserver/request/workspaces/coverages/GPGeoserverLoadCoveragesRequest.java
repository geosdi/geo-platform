package org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverAllCoverages;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverages;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverEmptyCoverages;
import org.geosdi.geoplatform.connector.geoserver.request.GPGeoserverGetConnectorRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadCoveragesRequest extends GPGeoserverGetConnectorRequest<GPGeoserverCoverages, GPGeoserverEmptyCoverages> implements GeoserverLoadCoveragesRequest {

    private final ThreadLocal<String> workspace;
    private final ThreadLocal<String> queryList;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadCoveragesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspace = withInitial(() -> null);
        this.queryList = withInitial(() -> "");
    }

    /**
     * @param theWorkspace
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    @Override
    public GeoserverLoadCoveragesRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspace.set(theWorkspace);
        return this;
    }

    /**
     * <p>If the list parameter value is equal to “all” all the coverages available in the data source
     * (even the non published ones) will be returned. The Class returned is an istance
     * of {@link org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverAllCoverages}  class.
     * </p>
     *
     * @param theQueryList
     * @return {@link GeoserverLoadCoveragesRequest}
     */
    @Override
    public GeoserverLoadCoveragesRequest withQueryList(@Nullable String theQueryList) {
        this.queryList.set(((theQueryList != null) && !(theQueryList.trim().isEmpty())) ? theQueryList : "");
        return this;
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not ne null or an empty string.");
        String queryList = this.queryList.get();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coverages.json").concat("?list=").concat(queryList)
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coverages.json").concat("?list=").concat(queryList)));
    }

    /**
     * @param reader
     * @return {@link org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.GPGeoserverCoverages}
     * @throws Exception
     */
    @Override
    protected GPGeoserverCoverages readInternal(BufferedReader reader) throws Exception {
        String queryList = this.queryList.get();
        return ((queryList != null) && (queryList.equalsIgnoreCase("all"))
                ? this.jacksonSupport.getDefaultMapper().readValue(reader, GPGeoserverAllCoverages.class) : super.readInternal(reader));
    }

    /**
     * @return {@link Class<GPGeoserverEmptyCoverages>}
     */
    @Override
    protected Class<GPGeoserverEmptyCoverages> forEmptyResponse() {
        return GPGeoserverEmptyCoverages.class;
    }

    /**
     * @return {@link Class<GPGeoserverCoverages>}
     */
    @Override
    protected Class<GPGeoserverCoverages> forClass() {
        return GPGeoserverCoverages.class;
    }
}