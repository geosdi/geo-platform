package org.geosdi.geoplatform.connector.geoserver.model.connection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverConnectionParameters.class)
public interface IGPGeoserverConnectionParameters extends Serializable {

    /**
     * @param <Param>
     * @return {@link List<Param>}
     */
    <Param extends IGPGeoserverConnectionParam> List<Param> getParams();
}
