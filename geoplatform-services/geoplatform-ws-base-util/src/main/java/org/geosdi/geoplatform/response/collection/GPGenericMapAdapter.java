package org.geosdi.geoplatform.response.collection;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGenericMapAdapter<K, V, Entry extends GPGenericEntryType<K, V>, MapType extends GPGenericMapType<K, V, Entry>>
        extends XmlAdapter<MapType, Map<K, V>> {

    /**
     * Convert a value type to a bound type.
     *
     * @param mapType The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public Map<K, V> unmarshal(MapType mapType) throws Exception {
        return mapType.getEntry().stream()
                .collect(toMap(k -> k.getKey(), v -> v.getValue()));
    }
}