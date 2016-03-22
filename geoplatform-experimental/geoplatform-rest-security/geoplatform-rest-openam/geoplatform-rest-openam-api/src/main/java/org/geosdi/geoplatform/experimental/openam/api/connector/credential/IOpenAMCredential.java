package org.geosdi.geoplatform.experimental.openam.api.connector.credential;

import org.geosdi.geoplatform.experimental.rs.security.connector.settings.credential.GPConnectorCredential;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMCredential extends GPConnectorCredential {

    /**
     * @return {@link String}
     */
    String getUserNameKey();

    /**
     * @return {@link String}
     */
    String getPasswordKey();
}
