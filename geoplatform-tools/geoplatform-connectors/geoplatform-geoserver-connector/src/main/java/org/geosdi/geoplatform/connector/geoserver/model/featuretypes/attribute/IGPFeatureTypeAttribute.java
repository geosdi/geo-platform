package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPFeatureTypeAttribute.class)
public interface IGPFeatureTypeAttribute extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link Integer}
     */
    Integer getMinOccurs();

    /**
     * @param theMinOcccurs
     */
    void setMinOccurs(Integer theMinOcccurs);

    /**
     * @return {@link Integer}
     */
    Integer getMaxOccurs();

    /**
     * @param theMaxOccurs
     */
    void setMaxOccurs(Integer theMaxOccurs);

    /**
     * @return {@link Boolean}
     */
    boolean isNillable();

    /**
     * @param theNillable
     */
    void setNillable(boolean theNillable);

    /**
     * @return {@link String}
     */
    String getBinding();

    /**
     * @param theBinding
     */
    void setBinding(String theBinding);
}