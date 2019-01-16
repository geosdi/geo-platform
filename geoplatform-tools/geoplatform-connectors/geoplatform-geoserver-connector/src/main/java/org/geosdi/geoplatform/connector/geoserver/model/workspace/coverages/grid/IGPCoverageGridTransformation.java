package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPCoverageGridTransformation.class)
public interface IGPCoverageGridTransformation extends Serializable {

    /**
     * @return {@link Double}
     */
    Double getScaleX();

    /**
     * @return {@link Double}
     */
    Double getScaleY();

    /**
     * @return {@link Double}
     */
    Double getShearX();

    /**
     * @return {@link Double}
     */
    Double getShearY();

    /**
     * @return {@link Double}
     */
    Double getTranslateX();

    /**
     * @return {@link Double}
     */
    Double getTranslateY();
}