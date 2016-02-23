package org.geosdi.geoplatform.support.jackson.property;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FunctionalInterface
public interface GPConfigFeature {

    /**
     * @param mapper
     */
    void configureMapper(ObjectMapper mapper);
}
