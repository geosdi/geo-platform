package org.geosdi.geoplatform.connector.geoserver.model.bbox;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.crs.IGPGeoserverCRS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
public class GPGeoserverNativeBoundingBox extends GPGeoserverBoundingBox<IGPGeoserverCRS> {

    private static final long serialVersionUID = 1782373136890658818L;
    //
    private IGPGeoserverCRS crs;
}