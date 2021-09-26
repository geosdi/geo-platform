package org.geosdi.geoplatform.connector.geoserver.coveragestores;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPFormatExtension {

    XML,
    JSON,
    HTML,
    SLD,
    SLD_1_1_0;

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}