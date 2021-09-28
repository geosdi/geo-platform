package org.geosdi.geoplatform.connector.geoserver.model.upload;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPUploadMethod {

    FILE,
    URL,
    EXTERNAL;

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}