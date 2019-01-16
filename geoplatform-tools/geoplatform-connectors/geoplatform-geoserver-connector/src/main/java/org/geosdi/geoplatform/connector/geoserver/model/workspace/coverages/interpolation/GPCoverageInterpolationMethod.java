package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.interpolation;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPCoverageInterpolationMethod implements IGPCoverageInterpolationMethod {

    private static final long serialVersionUID = 2494048699608472385L;
    //
    @XmlElement(name = "string")
    private List<String> values;
}
