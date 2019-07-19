package org.geosdi.geoplatform.connector.geoserver.model.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@XmlAccessorType(value = XmlAccessType.FIELD)
@ToString
public class GPGeoserverResponseSRS implements GPGeoserverSRS {

    private static final long serialVersionUID = -2552516859276402518L;
    //
    @XmlElement(name = "string")
    private List<String> values;
}
