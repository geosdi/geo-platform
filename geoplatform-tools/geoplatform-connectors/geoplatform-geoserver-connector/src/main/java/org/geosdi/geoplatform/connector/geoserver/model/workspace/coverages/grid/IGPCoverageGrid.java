package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageGrid.class)
public interface IGPCoverageGrid extends Serializable {

    /**
     * @return {@link String}
     */
    String getDimension();

    /**
     * @return {@link String}
     */
    String getCrs();

    /**
     * @param <Range>
     * @return {@link Range}
     */
    <Range extends IGPCoverageGridRange> Range getRange();

    /**
     * @param <Transform>
     * @return {@link Transform}
     */
    <Transform extends IGPCoverageGridTransformation> Transform getTransform();
}