package org.geosdi.geoplatform.experimental.openam.api.connector.cookie;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMCookieInfo extends Serializable {

    /**
     * @return {@link String}
     */
    String getOpenAMCookie();

    /**
     * @param theOpenAMCookie
     */
    void setOpenAMCookie(String theOpenAMCookie);
}
