package org.geosdi.geoplatform.experimental.openam.api.model.authenticate;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMAuthenticate extends Serializable {

    /**
     * @return {@link String}
     */
    String getTokenId();

    /**
     * @param theTokenId
     */
    void setTokenId(String theTokenId);

    /**
     * @return {@link String}
     */
    String getSuccessUrl();

    /**
     * @param theSuccessUrl
     */
    void setSuccessUrl(String theSuccessUrl);
}
