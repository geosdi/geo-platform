package org.geosdi.geoplatform.experimental.rs.security.connector.settings.credential;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPConnectorCredential extends InitializingBean, Serializable {

    /**
     * @return {@link String}
     */
    String getUserName();

    /**
     * @return {@link String}
     */
    String getPassword();
}
