package org.geosdi.geoplatform.experimental.openam.api.connector;

import org.geosdi.geoplatform.experimental.openam.api.connector.cookie.IOpenAMCookieInfo;
import org.geosdi.geoplatform.experimental.openam.api.connector.search.IOpenAMSearchConnector;
import org.geosdi.geoplatform.experimental.openam.api.model.serverinfo.IOpenAMServerInfo;
import org.geosdi.geoplatform.experimental.openam.api.model.validate.IOpenAMValidateToken;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPOpenAMConnector extends IOpenAMSearchConnector {

    /**
     * @param token
     * @return {@link IOpenAMValidateToken}
     * @throws Exception
     */
    IOpenAMValidateToken validateToken(String token) throws Exception;

    /**
     * @return {@link IOpenAMServerInfo}
     * @throws Exception
     */
    IOpenAMServerInfo serverInfo() throws Exception;

    /**
     * @param openAMCookieInfo
     */
    void registerOpenAMCookieInfo(IOpenAMCookieInfo openAMCookieInfo);
}
