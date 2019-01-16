package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "coverages")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverCoverages implements IGPGeoserverCoverages {

    private static final long serialVersionUID = -4215131739086848119L;
    //
    private List<IGPGeoserverCoverage> coverage;
}