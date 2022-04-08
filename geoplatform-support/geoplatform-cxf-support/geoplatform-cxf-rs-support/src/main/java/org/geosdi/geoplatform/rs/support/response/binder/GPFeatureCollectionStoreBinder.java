package org.geosdi.geoplatform.rs.support.response.binder;

import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.rs.support.response.GPStore;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPFeatureCollectionStoreBinder<E, V, TO extends GPStore<E>, FROM extends List<V>, B extends GPStoreBinder> extends GPStoreBinder<E, TO, FROM, B> {

    /**
     * @param theTotal
     * @return {@link B}
     */
    @Override
    B withTotal(long theTotal);

    /**
     * @param theFrom
     * @return {@link B}
     */
    @Override
    B withFrom(FROM theFrom);

    /**
     * @return {@link FeatureCollection}
     * @throws Exception
     */
    FeatureCollection bindAsFeatureCollection() throws Exception;
}