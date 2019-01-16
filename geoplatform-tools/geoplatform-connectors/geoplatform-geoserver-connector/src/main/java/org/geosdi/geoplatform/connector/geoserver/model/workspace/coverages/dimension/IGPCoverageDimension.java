package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.range.IGPCoverageDimensionRange;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageDimension.class)
public interface IGPCoverageDimension extends Serializable {

    /**
     * @return {@link String}
     */
    String getDescription();

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param <Range>
     * @return {@link Range}
     */
    <Range extends IGPCoverageDimensionRange> Range getRange();
}