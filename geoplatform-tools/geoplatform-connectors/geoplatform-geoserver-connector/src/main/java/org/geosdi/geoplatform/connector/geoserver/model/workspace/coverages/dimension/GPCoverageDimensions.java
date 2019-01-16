package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageDimensions implements IGPCoverageDimensions {

    private static final long serialVersionUID = 6832744418663508323L;
    //
    private List<IGPCoverageDimension> coverageDimension;
}
