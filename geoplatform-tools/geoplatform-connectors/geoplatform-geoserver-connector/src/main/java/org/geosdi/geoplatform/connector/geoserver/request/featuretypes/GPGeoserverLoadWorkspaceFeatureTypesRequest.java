package org.geosdi.geoplatform.connector.geoserver.request.featuretypes;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadWorkspaceFeatureTypesRequest extends GPGeoserverLoadFeatureTypesRequest<GeoserverLoadWorkspaceFeatureTypesRequest> implements GeoserverLoadWorkspaceFeatureTypesRequest {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverLoadWorkspaceFeatureTypesRequest(GPServerConnector server, JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspace.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        GPGeoserverFeatureTypeCategory featureTypeCategory = this.featureTypeCategory.get();
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/featuretypes.json?list=").concat(featureTypeCategory.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/featuretypes.json?list=").concat(featureTypeCategory.toString())));
    }
}