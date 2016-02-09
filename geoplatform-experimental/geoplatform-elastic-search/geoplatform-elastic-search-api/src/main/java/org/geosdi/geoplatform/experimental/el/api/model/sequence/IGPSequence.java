package org.geosdi.geoplatform.experimental.el.api.model.sequence;

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

    void setVersion(Long theVersion);
}
