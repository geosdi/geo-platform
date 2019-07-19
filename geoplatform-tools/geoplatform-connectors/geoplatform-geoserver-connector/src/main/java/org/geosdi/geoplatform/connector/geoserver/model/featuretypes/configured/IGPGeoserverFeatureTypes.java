package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverFeatureTypes.class)
public interface IGPGeoserverFeatureTypes extends Serializable {

    /**
     * @return {@link List< IGPGeoserverFeatureType >}
     */
    List<IGPGeoserverFeatureType> getFeatureTypes();
}