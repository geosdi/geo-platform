package org.geosdi.geoplatform.services.response;

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
     * @return {@link List<Object>}
     */
    List<Object> getFeatures();

    /**
     * @param theFeature
     */
    void addFeature(@Nonnull(when = When.NEVER) Object theFeature);
}