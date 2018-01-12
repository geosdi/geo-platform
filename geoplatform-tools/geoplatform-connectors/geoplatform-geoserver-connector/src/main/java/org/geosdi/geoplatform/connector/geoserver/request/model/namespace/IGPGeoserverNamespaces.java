package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverNamespaces.class)
public interface IGPGeoserverNamespaces extends Serializable {

    /**
     * @return {@link List<  IGPGeoserverNamespace  >}
     */
    List<IGPGeoserverNamespace> getNamespaces();
}
