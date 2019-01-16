package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.range;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageDimensionRange implements IGPCoverageDimensionRange {

    private static final long serialVersionUID = 2377457881341333077L;
    //
    private Double max;
    private Double min;
}
