package org.geosdi.geoplatform.experimental.el.api.model.sequence;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPSequence {

    /**
     * @return {@link String}
     */
    String getSequenceId();

    /**
     * @param theID
     */
    void setSequenceID(String theID);

    /**
     * @return {@link Long}
     */
    Long getVersion();

    /**
     * @param theVersion
     */
    void setVersion(Long theVersion);

    /**
     * @return {@link Boolean}
     */
    @JsonIgnore
    Boolean isSetVersion();
}
