package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverUpdateWorkspaceRequest extends GPJsonPutConnectorRequest<String> implements GeoserverUpdateWorkspaceRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<GPGeoserverCreateWorkspaceBody> workspaceBody;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverUpdateWorkspaceRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceName = withInitial(() -> null);
        this.workspaceBody = withInitial(() -> null);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getWorkspaceName() {
        return this.workspaceName.get();
    }

    /**
     * @param theWorkspaceName
     */
    @Override
    public void setWorkspaceName(String theWorkspaceName) {
        this.workspaceName.set(theWorkspaceName);
    }

    /**
     * @return {@link GPGeoserverCreateWorkspaceBody}
     */
    @Override
    public GPGeoserverCreateWorkspaceBody getWorkspaceBody() {
        return this.workspaceBody.get();
    }

    /**
     * @param theWorkspaceBody
     */
    @Override
    public void setWorkspaceBody(GPGeoserverCreateWorkspaceBody theWorkspaceBody) {
        this.workspaceBody.set(theWorkspaceBody);
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    @Override
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        super.checkHttpResponseStatus(statusCode);
        switch (statusCode) {
            case 405:
                throw new IllegalStateException("Forbidden to change the name of the workspace");
        }
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverCreateWorkspaceBody workspaceBody = this.workspaceBody.get();
        checkArgument(workspaceBody != null, "The workspaceBody must not be null.");
        String workspaceBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(workspaceBody);
        logger.debug("#############################WORKSPACE_BODY : \n{}\n", workspaceBodyString);
        return new StringEntity(workspaceBodyString, APPLICATION_JSON);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspaceName = this.workspaceName.get();
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspaceName)
                : baseURI.concat("/workspaces/").concat(workspaceName)));
    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    protected Class<String> forClass() {
        return String.class;
    }
}