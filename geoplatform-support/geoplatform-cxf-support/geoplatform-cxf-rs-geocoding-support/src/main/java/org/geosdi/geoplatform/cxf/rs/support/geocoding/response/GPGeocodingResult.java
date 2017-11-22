package org.geosdi.geoplatform.cxf.rs.support.geocoding.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeocodingResult implements IGPGeocodingResult {

    private static final long serialVersionUID = 6265730582246682432L;
    //
    private final Long total;
    @JsonDeserialize(contentAs = GPGeocodingLocality.class)
    private final List<IGPGeocodingLocality> localities;

    @JsonCreator
    public GPGeocodingResult(@JsonProperty(value = "total") Long theTotal, @JsonProperty(value = "localities") List<IGPGeocodingLocality> theLocalities) {
        this.total = theTotal;
        this.localities = theLocalities;
    }
}
