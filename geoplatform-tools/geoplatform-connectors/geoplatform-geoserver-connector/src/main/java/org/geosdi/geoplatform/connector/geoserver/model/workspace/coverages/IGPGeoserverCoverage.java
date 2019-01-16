package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCoverage.class)
public interface IGPGeoserverCoverage extends Serializable {

    /**
     * @return {@link String}
     */
    String getCoverageName();

    /**
     * @return {@link String}
     */
    String getCoverageHref();
}