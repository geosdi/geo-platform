package org.geosdi.geoplatform.connector.geoserver.model.srs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString
public class GPGeoserverRequestSRS implements GPGeoserverSRS {

    private static final long serialVersionUID = 1237878436600718761L;
    //
    @XmlElement(name = "string")
    private List<String> values;
}