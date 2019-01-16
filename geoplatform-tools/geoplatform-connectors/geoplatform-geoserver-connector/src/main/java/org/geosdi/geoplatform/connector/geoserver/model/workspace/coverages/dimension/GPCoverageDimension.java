package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.range.IGPCoverageDimensionRange;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageDimension implements IGPCoverageDimension {

    private static final long serialVersionUID = -7201883013566538628L;
    //
    private String description;
    private String name;
    private IGPCoverageDimensionRange range;
}