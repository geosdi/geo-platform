package org.geosdi.geoplatform.experimental.openam.api.connector.secure;

import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.IOpenAMAuthenticate;
import org.geosdi.geoplatform.experimental.openam.api.model.authenticate.IOpenAMLogout;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMAuthorizedConnector extends DisposableBean {

    /**
     * @return {@link IOpenAMAuthenticate}
     * @throws Exception
     */
    IOpenAMAuthenticate authenticate() throws Exception;

    /**
     * @param userName
     * @param password
     * @return {@link IOpenAMAuthenticate}
     * @throws Exception
     */
    IOpenAMAuthenticate authenticate(String userName, String password) throws Exception;

    /**
     * @param tokenId
     * @return {@link IOpenAMLogout}
     * @throws Exception
     */
    IOpenAMLogout logout(String tokenId) throws Exception;
}
