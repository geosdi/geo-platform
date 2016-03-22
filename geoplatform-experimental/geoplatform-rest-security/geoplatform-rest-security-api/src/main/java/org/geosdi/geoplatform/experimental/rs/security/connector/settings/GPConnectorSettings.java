package org.geosdi.geoplatform.experimental.rs.security.connector.settings;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPConnectorSettings extends GPConnectorPooledSettings {

    /**
     * @return {@link String}
     */
    String getScheme();

    /**
     * @return {@link String}
     */
    String getHost();

    /**
     * @return {@link String}
     */
    String getPath();
}
