package org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates.GPGeowebcacheCoordinatesEntry;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates.GeowebcacheCoordinatesEntry;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@Getter
@Setter
public class GeowebcacheBoundsBean implements GPGeowebcacheBoundsBean {

    private static final long serialVersionUID = -8741112827617099689L;
    //
    @XmlElement(name = "coords", type = GeowebcacheCoordinatesEntry.class)
    private GPGeowebcacheCoordinatesEntry coordinates;
}
