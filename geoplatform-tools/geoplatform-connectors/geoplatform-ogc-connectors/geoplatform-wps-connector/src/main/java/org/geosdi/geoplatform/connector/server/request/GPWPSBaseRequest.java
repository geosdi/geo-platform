package org.geosdi.geoplatform.connector.server.request;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWPSBaseRequest<T> extends GPConnectorRequest<T> {

    /**
     * @return {@link String}
     */
    String getLanguage();

    /**
     * @param theLanguage
     */
    void setLanguage(String theLanguage);

    /**
     * @return {@link Boolean}
     */
    Boolean isLanguageSet();
}
