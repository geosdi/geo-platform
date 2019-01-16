package org.geosdi.geoplatform.connector.geoserver.model.keyword;

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
public class GPGeoserverKeyword implements IGPGeoserverKeyword {

    private static final long serialVersionUID = -3202112193147298242L;
    //
    @XmlElement(name = "string")
    private List<String> values;
}
