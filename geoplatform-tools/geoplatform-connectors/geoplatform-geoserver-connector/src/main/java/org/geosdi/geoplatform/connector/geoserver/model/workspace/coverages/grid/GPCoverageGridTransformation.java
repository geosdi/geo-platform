package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageGridTransformation implements IGPCoverageGridTransformation {

    private static final long serialVersionUID = -5817275271622238146L;
    //
    private Double scaleX;
    private Double scaleY;
    private Double shearX;
    private Double shearY;
    private Double translateX;
    private Double translateY;
}