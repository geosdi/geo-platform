package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageGridRange.class)
public interface IGPCoverageGridRange extends Serializable {

    /**
     * @return {@link String}
     */
    String getHigh();

    /**
     * @return {@link String}
     */
    String getLow();
}