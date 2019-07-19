package org.geosdi.geoplatform.response.collection;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
public abstract class GPGenericMapType<K, V, Entry extends GPGenericEntryType<K, V>> implements Serializable {

    private static final long serialVersionUID = 8201121335219233924L;
    //
    private List<Entry> entry = new ArrayList<>();

    public GPGenericMapType() {
    }

    /**
     * @param map
     */
    public GPGenericMapType(Map<K, V> map) {
        this.entry = map.entrySet()
                .stream()
                .map(entry -> toEntry(entry))
                .collect(toList());
    }

    /**
     * @param entry
     * @return {@link Entry}
     */
    protected abstract Entry toEntry(Map.Entry<K, V> entry);
}