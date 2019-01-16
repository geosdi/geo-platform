package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPGeoserverCoverage implements IGPGeoserverCoverage {

    private static final long serialVersionUID = -8031612869655384130L;
    //
    @XmlElement(name = "name")
    private String coverageName;
    @XmlElement(name = "href")
    private String coverageHref;
}