package org.geosdi.geoplatform.connector.geoserver.request.workspaces;

import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverCreateWorkspaceBody;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.ThreadLocal.withInitial;
import static java.util.stream.Collectors.joining;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverCreateWorkspaceRequest extends GPJsonPostConnectorRequest<String> implements GeoserverCreateWorkspaceRequest {

    private final ThreadLocal<GPGeoserverCreateWorkspaceBody> workspaceBody;
    private final ThreadLocal<Boolean> defaultWorkspace;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverCreateWorkspaceRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector,
            @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.workspaceBody = withInitial(() -> null);
        this.defaultWorkspace = withInitial(() -> FALSE);
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
     * @return {@link Boolean}
     */
    @Override
    public Boolean isDefaultWorkspace() {
        return this.defaultWorkspace.get();
    }

    /**
     * @param theDefaultWorkspace
     */
    @Override
    public void setDefaultWorkspace(Boolean theDefaultWorkspace) {
        this.defaultWorkspace.set(theDefaultWorkspace != null ? theDefaultWorkspace : FALSE);
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401:
                throw new IllegalStateException("Unable to add workspace as it already exists.");
            case 404:
                throw new ResourceNotFoundException();
        }
    }

    /**
     * @param reader
     * @return {@link String}
     * @throws Exception
     */
    @Override
    protected String readInternal(BufferedReader reader) throws Exception {
        return reader.lines().collect(joining());
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
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces?default=").concat(this.defaultWorkspace.get().toString())
                : baseURI.concat("/workspaces?default=").concat(this.defaultWorkspace.get().toString())));
    }

    /**
     * @return {@link Class<String>}
     */
    @Override
    protected Class<String> forClass() {
        return String.class;
    }
}