package org.geosdi.geoplatform.experimental.el.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface Document extends Serializable {

    /**
     * @return {@link String} ElasticSearch ID
     */
    String getId();

    /**
     * @param theID
     */
    void setId(String theID);

    /**
     * @return {@link Boolean}
     */
    @JsonIgnore
    Boolean isIdSetted();
}
