package org.geosdi.geoplatform.experimental.openam.support.connector.request.authenticate;

import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMAuthenticateRequest extends BaseOpenAMRequest<IOpenAMAuthenticateRequest> {

    /**
     * @return {@link String}
     */
    String getUserName();

    /**
     * @return {@link String}
     */
    String getUserNameKey();

    /**
     * @return {@link String}
     */
    String getPassword();

    /**
     * @return {@link String}
     */
    String getPasswordKey();
}
