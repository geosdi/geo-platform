package org.geosdi.geoplatform.connector.reader.stax;

import lombok.Getter;
import lombok.ToString;
import org.geojson.Feature;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public abstract class WMSFeatureStore<K extends Object> implements GPStaxFeatureStore<K> {

    private static final long serialVersionUID = 7246174437706791817L;
    //
    private final K key;
    private final Map<K, List<Feature>> store = new HashMap<>();

    /**
     * @param theKey
     */
    protected WMSFeatureStore(@Nonnull(when = NEVER) K theKey) {
        checkArgument(theKey != null, "The Parameter key must not be null.");
        this.key = theKey;
    }

    /**
     * @param feature
     * @throws Exception
     */
    @Override
    public void addFeature(@Nonnull(when = NEVER) Feature feature) throws Exception {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        K featureKey = (K) feature.getProperties().get(this.key);
        if(this.store.containsKey(featureKey)) {
            List<Feature> features = this.store.get(featureKey);
            features.add(feature);
        } else {
            this.store.put(featureKey, of(feature).collect(toList()));
        }
        feature.getProperties().remove(this.key);
    }

    /**
     * @param theKey
     * @return {@link List<Feature>}
     * @throws Exception
     */
    @Override
    public List<Feature> getFeaturesByKey(@Nonnull(when = NEVER) K theKey) throws Exception {
        checkArgument(theKey != null, "The Parameter key must not be null.");
        return this.store.get(theKey);
    }
}