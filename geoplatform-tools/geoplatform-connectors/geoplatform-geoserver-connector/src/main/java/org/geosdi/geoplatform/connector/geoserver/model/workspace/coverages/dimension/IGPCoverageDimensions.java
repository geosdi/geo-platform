package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageDimensions.class)
public interface IGPCoverageDimensions extends Serializable {

    /**
     * @return {@link List<IGPCoverageDimension>}
     */
    List<IGPCoverageDimension> getCoverageDimension();
}
