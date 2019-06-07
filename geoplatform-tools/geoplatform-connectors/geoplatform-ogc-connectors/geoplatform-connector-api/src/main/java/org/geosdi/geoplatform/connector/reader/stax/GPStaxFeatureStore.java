package org.geosdi.geoplatform.connector.reader.stax;

import org.geojson.Feature;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPStaxFeatureStore<K extends Object> extends Serializable {

    /**
     * @param feature
     * @throws Exception
     */
    void addFeature(@Nonnull(when = When.NEVER) Feature feature) throws Exception;

    /**
     * @return {@link Map<String, List<Feature>}
     */
    Map<K, List<Feature>> getStore();
}