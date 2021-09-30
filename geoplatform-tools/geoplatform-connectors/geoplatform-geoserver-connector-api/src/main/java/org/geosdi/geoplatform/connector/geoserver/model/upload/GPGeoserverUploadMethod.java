package org.geosdi.geoplatform.connector.geoserver.model.upload;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPGeoserverUploadMethod {

    FILE("application/zip"),
    URL("text/plain"),
    EXTERNAL("text/plain");

    private final String contentType;

    GPGeoserverUploadMethod(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return {@link String}
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}