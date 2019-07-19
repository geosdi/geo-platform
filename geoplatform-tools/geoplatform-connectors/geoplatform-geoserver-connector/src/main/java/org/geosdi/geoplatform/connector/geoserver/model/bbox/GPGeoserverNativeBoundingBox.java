package org.geosdi.geoplatform.connector.geoserver.model.bbox;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRSDeserializer;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString(callSuper = true)
public class GPGeoserverNativeBoundingBox extends GPGeoserverBoundingBox<Object> {

    private static final long serialVersionUID = 1782373136890658818L;
    //
    @JsonDeserialize(using = GPGeoserverCRSDeserializer.class)
    private Object crs;

    /**
     * @param theCrs
     */
    public void setCrs(Object theCrs) {
        checkArgument((theCrs instanceof String) || (theCrs instanceof GPGeoserverCRS), "The Parameter crs must be an instance of Stirng or GPGeoserverCRS.");
        this.crs = theCrs;
    }
}