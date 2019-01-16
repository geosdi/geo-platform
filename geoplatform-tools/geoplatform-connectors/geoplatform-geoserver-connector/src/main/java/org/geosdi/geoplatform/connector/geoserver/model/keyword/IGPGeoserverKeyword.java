package org.geosdi.geoplatform.connector.geoserver.model.keyword;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverKeyword.class)
public interface IGPGeoserverKeyword extends Serializable {

    /**
     * @return {@link List<String>}
     */
    List<String> getValues();
}