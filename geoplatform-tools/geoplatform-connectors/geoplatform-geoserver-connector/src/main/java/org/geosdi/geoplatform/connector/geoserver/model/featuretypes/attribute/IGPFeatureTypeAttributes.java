package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPFeatureTypeAttributes.class)
public interface IGPFeatureTypeAttributes extends Serializable {

    /**
     * @return {@link List<IGPFeatureTypeAttribute>}
     */
    List<IGPFeatureTypeAttribute> getValues();

    /**
     * @param theValues
     */
    void setValues(List<IGPFeatureTypeAttribute> theValues);
}