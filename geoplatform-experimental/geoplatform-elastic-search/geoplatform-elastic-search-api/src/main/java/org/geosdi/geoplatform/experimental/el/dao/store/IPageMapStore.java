package org.geosdi.geoplatform.experimental.el.dao.store;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonSerialize(as = PageMapStore.class)
@JsonDeserialize(as = PageMapStore.class)
public interface IPageMapStore<K, T> extends Serializable {

    /**
     * @return {@link Map<K, List<T>}
     */
    Map<K, List<T>> getValues();

    /**
     * @param theValues
     * @param <K>
     * @param <T>
     * @return {@link IPageMapStore<K, T>}
     */
    static <K, T> IPageMapStore<K, T> of(@Nullable Map<K, List<T>> theValues) {
        return new PageMapStore<K, T>(theValues);
    }
}