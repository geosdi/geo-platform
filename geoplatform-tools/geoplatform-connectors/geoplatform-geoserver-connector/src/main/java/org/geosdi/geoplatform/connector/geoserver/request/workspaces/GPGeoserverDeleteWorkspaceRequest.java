package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonDeleteConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverDeleteWorkspaceRequest extends GPJsonDeleteConnectorRequest<Boolean> implements GeoserverDeleteWorkspaceRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<Boolean> recurse;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverDeleteWorkspaceRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.recurse = withInitial(() -> FALSE);
    }

    /**
     * @param theWorkspaceName
     */
    @Override
    public void setWorkspaceName(String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getWorkspaceName() {
        return this.workspaceName.get();
    }

    /**
     * @param theRecurse
     */
    @Override
    public void setRecurse(Boolean theRecurse) {
        this.recurse.set((theRecurse != null) ? theRecurse : FALSE);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isRecurse() {
        return this.recurse.get();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName mut not be null or an Empty String.");
        String recurse = this.recurse.get().toString();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName).concat("?recurse=").concat(recurse)
                : baseURI.concat("/workspaces/").concat(workspaceName).concat("?recurse=").concat(recurse)));
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    @Override
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 403:
                throw new IllegalStateException("Workspace or related Namespace is not empty (and recurse not true).");
            case 404:
                throw new ResourceNotFoundException("Workspace doesn’t exist");
            case 405:
                throw new IllegalStateException("Can’t delete default workspace");
        }
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
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}