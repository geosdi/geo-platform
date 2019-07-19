package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverFeatureTypeWrapper<V> {

    /**
     * @return {@link V}
     */
    V toFeatureType();

    /**
     * @param theValue
     * @param <V>
     * @return {@link IGPGeoserverFeatureTypeWrapper<V>}
     */
    static <V> GPGeoserverFeatureTypeWrapper<V> of(V theValue) {
        return new GPGeoserverFeatureTypeWrapper<V>(theValue);
    }
}