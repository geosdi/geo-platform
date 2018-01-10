package org.geosdi.geoplatform.connector.geoserver.request.model.workspace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverWorkspaces.class)
public interface IGPGeoserverWorkspaces extends Serializable {

    /**
     * @return {@link List<IGPGeoserverWorkspace>}
     */
    List<IGPGeoserverWorkspace> getWorkspaces();
}