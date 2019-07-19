package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.avaialable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverFeatureTypesList.class)
public interface IGPGeoserverFeatureTypesList extends Serializable {

    /**
     * @return {@link List<String>}
     */
    List<String> getValues();
}