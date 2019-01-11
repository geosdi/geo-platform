package org.geosdi.geoplatform.response.collection;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGenericEntryType<K, V> extends Serializable {

    /**
     * @return {@link K}
     */
    K getKey();

    /**
     * @param theKey
     */
    void setKey(K theKey);

    /**
     * @return {@link V}
     */
    V getValue();

    /**
     * @param theValue
     */
    void setValue(V theValue);
}