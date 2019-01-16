package org.geosdi.geoplatform.connector.geoserver.model.format;

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
public class GPGeoserverSupportedFormat implements IGPGeoserverSupportedFormat {

    private static final long serialVersionUID = 4962015018019282648L;
    //
    @XmlElement(name = "string")
    private List<String> formats;
}
