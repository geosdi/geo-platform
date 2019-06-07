package org.geosdi.geoplatform.services.request;

import org.geojson.Feature;
import org.geosdi.geoplatform.connector.reader.stax.WMSFeatureStore;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSFeatureErrorStore extends WMSFeatureStore<String> {

    private static final long serialVersionUID = -1485994741749979395L;

    protected GPWMSFeatureErrorStore() {
        super("WMS_GET_FEATURE_ERROR");
    }

    /**
     * @param feature
     * @throws Exception
     */
    @Override
    public void addFeature(@Nonnull(when = NEVER) Feature feature) throws Exception {
        checkArgument(feature != null, "The Parameter feature must not be null.");
        if (this.getStore().containsKey(this.getKey())) {
            List<Feature> features = this.getStore().get(this.getKey());
            features.add(feature);
        } else {
            this.getStore().put(this.getKey(), of(feature).collect(toList()));
        }
    }
}