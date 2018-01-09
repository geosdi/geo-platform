package org.geosdi.geoplatform.connector.geoserver.request.model.about.version;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(value = FIELD)
public class GPGeoserverVersionResource implements IGPGeoserverVersionResource {

    private static final long serialVersionUID = 4766494176330367092L;
    //
    @XmlElement(name = "@name")
    private String name;
    @XmlElement(name = "Build-Timestamp")
    private String buildTimestamp;
    @XmlElement(name = "Version")
    private String version;
    @XmlElement(name = "Git-Revision")
    private String gitRevision;
}
