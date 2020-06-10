package org.geosdi.geoplatform.response.collection;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGenericListEntryType<K, V> extends Serializable {

    /**
     * @return {@link K}
     */
    K getKey();

    /**
     * @param theKey
     */
    void setKey(K theKey);

    /**
     * @return {@link List<V>}
     */
    List<V> getValues();

    /**
     * @param theValues
     */
    void setValues(List<V> theValues);
}