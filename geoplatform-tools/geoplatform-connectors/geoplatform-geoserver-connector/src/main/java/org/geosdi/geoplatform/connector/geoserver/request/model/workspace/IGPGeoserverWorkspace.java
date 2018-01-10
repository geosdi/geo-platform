package org.geosdi.geoplatform.connector.geoserver.request.model.workspace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverWorkspace.class)
public interface IGPGeoserverWorkspace extends Serializable {

    /**
     * @return {@link String}
     */
    String getWorkspaceName();

    /**
     * @return {@link String}
     */
    String getWorkspaceHref();
}
