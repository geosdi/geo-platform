package org.geosdi.geoplatform.connector.geoserver.model.store;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPStoreType {

    COVERAGES,
    DATASTORES;

    public String toString() {
        return this.name().toLowerCase();
    }
}