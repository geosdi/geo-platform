package org.geosdi.geoplatform.connector.geoserver.coveragestores;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPStoreType {

    COVERAGESTORES,
    DATASTORES;

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
