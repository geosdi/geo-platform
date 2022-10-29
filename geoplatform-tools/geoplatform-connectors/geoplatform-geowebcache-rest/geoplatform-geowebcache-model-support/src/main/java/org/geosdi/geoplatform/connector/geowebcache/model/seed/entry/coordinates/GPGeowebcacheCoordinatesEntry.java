package org.geosdi.geoplatform.connector.geowebcache.model.seed.entry.coordinates;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GeowebcacheCoordinatesEntry.class)
public interface GPGeowebcacheCoordinatesEntry extends Serializable {

    /**
     * @return {@link List<Double>}
     */
    List<Double> getNumbers();

    /**
     * @param theNumbers
     */
    void setNumbers(List<Double> theNumbers);

    /**
     * @param numbers
     */
    void addNumbers(Double... numbers);
}
