package org.geosdi.geoplatform.cxf.rs.support.geocoding.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.geojson.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeocodingLocality implements IGPGeocodingLocality {

    private static final long serialVersionUID = 5316612556746642706L;
    //
    //
    private final String formattedAddress;
    private final Point location;

    @JsonCreator
    public GPGeocodingLocality(@JsonProperty(value = "formattedAddress") String theFormattedAddress,
            @JsonProperty(value = "location") Point theGeometry) {
        this.formattedAddress = theFormattedAddress;
        this.location = theGeometry;
    }
}
