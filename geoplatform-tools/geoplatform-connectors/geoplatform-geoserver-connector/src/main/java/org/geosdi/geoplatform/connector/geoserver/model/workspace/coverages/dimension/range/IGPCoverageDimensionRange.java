package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.range;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageDimensionRange.class)
public interface IGPCoverageDimensionRange extends Serializable {

    /**
     * @return {@link Double}
     */
    Double getMax();

    /**
     * @return {@link Double}
     */
    Double getMin();
}