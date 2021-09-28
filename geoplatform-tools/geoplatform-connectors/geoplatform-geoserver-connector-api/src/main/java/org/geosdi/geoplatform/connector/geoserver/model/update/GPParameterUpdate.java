package org.geosdi.geoplatform.connector.geoserver.model.update;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPParameterUpdate {

    APPEND,
    OVERWRITE;

    public String toString() {
        return this.name().toLowerCase();
    }
}