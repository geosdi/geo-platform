package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageGridRange implements IGPCoverageGridRange {

    private static final long serialVersionUID = -1736021968105226373L;
    //
    private String high;
    private String low;
}