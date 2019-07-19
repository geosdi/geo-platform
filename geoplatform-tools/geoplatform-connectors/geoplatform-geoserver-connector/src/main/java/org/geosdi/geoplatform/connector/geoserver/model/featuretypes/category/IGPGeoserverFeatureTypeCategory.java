package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverFeatureTypeCategory extends Serializable {

    /**
     * @param <T>
     * @return {@link Class<T>}
     */
    <T> Class<T> toModel();

    /**
     * @param <E>
     * @return {@link Class<E>}
     */
    <E> Class<E> toEmptyModel();
}