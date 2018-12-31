package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverLoadWorkspace;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadWorkspaceRequest extends GPJsonGetConnectorRequest<GPGeoserverLoadWorkspace> {

    private final ThreadLocal<String> workspaceName;

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadWorkspaceRequest(@Nonnull(when = When.NEVER) GPServerConnector server, @Nonnull(when = When.NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
    }

    /**
     * @return {@link String}
     */
    public String getWorkspaceName() {
        return this.workspaceName.get();
    }

    /**
     * @param theWorkspaceName
     */
    public void setWorkspaceName(String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName mut not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName)
                : baseURI.concat("/workspaces/").concat(workspaceName)));
    }

    /**
     * @return {@link Class<GPGeoserverLoadWorkspace>}
     */
    @Override
    protected Class<GPGeoserverLoadWorkspace> forClass() {
        return GPGeoserverLoadWorkspace.class;
    }
}