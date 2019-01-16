package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.interpolation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageInterpolationMethod.class)
public interface IGPCoverageInterpolationMethod extends Serializable {

    /**
     * @return {@link List<String>}
     */
    List<String> getValues();
}