package org.geosdi.geoplatform.connector.geoserver.coveragestores;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPUploadMethod {

    FILE,
    /** @deprecated */
    @Deprecated
    file,
    URL,
    /** @deprecated */
    @Deprecated
    url,
    EXTERNAL,
    /** @deprecated */
    @Deprecated
    external;

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}