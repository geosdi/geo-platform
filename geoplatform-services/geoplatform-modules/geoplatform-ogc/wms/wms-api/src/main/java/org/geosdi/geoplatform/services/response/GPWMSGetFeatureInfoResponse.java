package org.geosdi.geoplatform.services.response;

import org.geojson.FeatureCollection;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoResponse extends Serializable {

    /**
     * @return {@link List<FeatureCollection>}
     */
    List<FeatureCollection> getFeatures();

    /**
     * @param theFeatureCollection
     */
    void addFeatureCollection(@Nonnull(when = When.NEVER) FeatureCollection theFeatureCollection);
}