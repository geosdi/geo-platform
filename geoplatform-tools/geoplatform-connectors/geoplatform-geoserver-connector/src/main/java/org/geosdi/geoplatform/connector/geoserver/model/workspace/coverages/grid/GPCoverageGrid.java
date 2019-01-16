package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageGrid implements IGPCoverageGrid {

    private static final long serialVersionUID = 3377494987884611704L;
    //
    @XmlElement(name = "@dimension")
    private String dimension;
    private String crs;
    private IGPCoverageGridRange range;
    private IGPCoverageGridTransformation transform;
}
