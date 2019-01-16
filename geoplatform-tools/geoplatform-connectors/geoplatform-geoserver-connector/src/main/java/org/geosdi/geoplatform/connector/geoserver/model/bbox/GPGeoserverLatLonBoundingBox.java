package org.geosdi.geoplatform.connector.geoserver.model.bbox;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
public class GPGeoserverLatLonBoundingBox extends GPGeoserverBoundingBox<String> {

    private static final long serialVersionUID = -8306304221548978580L;
    //
    private String crs;
}