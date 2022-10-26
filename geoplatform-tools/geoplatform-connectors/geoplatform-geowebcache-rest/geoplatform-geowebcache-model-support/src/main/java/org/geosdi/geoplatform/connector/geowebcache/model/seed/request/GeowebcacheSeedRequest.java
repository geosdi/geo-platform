package org.geosdi.geoplatform.connector.geowebcache.model.seed.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.GeowebcacheSeedRequestValue;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.WideGeowebcacheSeedRequestValue;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@XmlAccessorType(value = FIELD)
public class GeowebcacheSeedRequest implements WideGeowebcacheSeedRequest {

    private static final long serialVersionUID = 5451437198829925767L;
    //
    @XmlElement(name = "seedRequest", type = GeowebcacheSeedRequestValue.class)
    private WideGeowebcacheSeedRequestValue seedRequestValue;
}