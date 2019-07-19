package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverAllCoverages extends GPGeoserverCoverages {

    private static final long serialVersionUID = 6214773190504774133L;
    //
    @XmlElement(name = "string")
    private List<String> coverages;
}