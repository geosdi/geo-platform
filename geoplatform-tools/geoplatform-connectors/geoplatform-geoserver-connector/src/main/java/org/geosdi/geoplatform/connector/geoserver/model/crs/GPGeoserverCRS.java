package org.geosdi.geoplatform.connector.geoserver.model.crs;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPGeoserverCRS implements IGPGeoserverCRS {

    private static final long serialVersionUID = -2174733277323184810L;
    //
    @XmlElement(name = "$")
    private String value;
    @XmlElement(name = "@class")
    private String type;
}