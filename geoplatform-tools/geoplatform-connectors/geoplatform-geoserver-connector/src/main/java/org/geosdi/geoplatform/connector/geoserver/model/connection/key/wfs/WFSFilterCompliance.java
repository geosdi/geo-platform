package org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WFSFilterCompliance {

    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final int level;

    /**
     * @param theLevel
     */
    WFSFilterCompliance(int theLevel) {
        this.level = theLevel;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return String.valueOf(this.level);
    }
}