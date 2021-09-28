package org.geosdi.geoplatform.connector.geoserver.model.file;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPDataStoreFileExtension implements IGPFileExtension {

    SHP,
    PROPERTIES,
    H2,
    SPATIALITE;

    public String toString() {
        return this.name().toLowerCase();
    }
}