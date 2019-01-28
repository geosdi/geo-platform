package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverPurgeParam implements IGPGeoserverPurgeParam {

    NONE("none"),
    METADATA("metadata"),
    ALL("all");

    private final String value;

    GPGeoserverPurgeParam(String theValue) {
        this.value = theValue;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toPurge() {
        return this.value;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.toPurge();
    }
}