package org.geosdi.geoplatform.connector.geowebcache.model.seed.bounds;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates.GPGeowebcacheCoordinatesEntry;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheBoundsBean.class)
public interface GPGeowebcacheBoundsBean extends Serializable {

    /**
     * @param <Coordinates>
     */
    <Coordinates extends GPGeowebcacheCoordinatesEntry> Coordinates getCoordinates();

    /**
     * @param coordinates
     * @param <Coordinates>
     */
    <Coordinates extends GPGeowebcacheCoordinatesEntry> void setCoordinates(Coordinates coordinates);

}
