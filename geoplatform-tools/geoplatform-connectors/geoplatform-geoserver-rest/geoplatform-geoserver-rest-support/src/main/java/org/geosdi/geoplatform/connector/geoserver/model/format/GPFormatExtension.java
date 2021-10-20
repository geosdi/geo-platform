package org.geosdi.geoplatform.connector.geoserver.model.format;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPFormatExtension {

    XML("application/xml"),
    JSON("application/json"),
    HTML("application/html"),
    SLD("application/vnd.ogc.sld+xml"),
    SLD_1_1_0("application/vnd.ogc.se+xml");

    private final String contentType;

    GPFormatExtension(String contentType) {
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