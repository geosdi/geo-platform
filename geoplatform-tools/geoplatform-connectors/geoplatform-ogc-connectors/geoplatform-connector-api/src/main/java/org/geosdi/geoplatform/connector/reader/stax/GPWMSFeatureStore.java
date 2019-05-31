package org.geosdi.geoplatform.connector.reader.stax;

import lombok.Getter;
import lombok.ToString;
import org.geojson.Feature;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.reader.stax.GPGetFeatureGeoJsonStaxReader.FEATURE_NAME_KEY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPWMSFeatureStore implements GPStaxFeatureStore {

    private static final long serialVersionUID = 8043547109663983346L;
    //
    private final Map<String, List<Feature>> store = new HashMap<>();

    /**
     * @param feature
     * @throws Exception
     */
    @Override
    public void addFeature(@Nonnull(when = NEVER) Feature feature) throws Exception {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        String featureNameKey = (String) feature.getProperties().get(FEATURE_NAME_KEY);
        if (this.store.containsKey(featureNameKey)) {
            List<Feature> features = this.store.get(featureNameKey);
            features.add(feature);
        } else {
            this.store.put(featureNameKey, Stream.of(feature).collect(toList()));
        }
        feature.getProperties().remove(FEATURE_NAME_KEY);
    }
}