package org.geosdi.geoplatform.experimental.openam.api.authenticator.filter;

import org.geosdi.geoplatform.experimental.rs.security.authenticator.GPAuthenticatorType;
import org.geosdi.geoplatform.experimental.rs.security.authenticator.filter.GPAuthenticatorFilter;

import static org.geosdi.geoplatform.experimental.openam.api.authenticator.OpenAMAuthenticatorType.OPENAM;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class BaseOpenAmAuthenticator implements GPAuthenticatorFilter {

    /**
     * @return {@link String}
     */
    @Override
    public String getAuthenticatorName() {
        return getClass().getSimpleName();
    }

    /**
     * @return {@link GPAuthenticatorType}
     */
    @Override
    public GPAuthenticatorType getAuthenticatorType() {
        return OPENAM;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getAuthenticatorVersion() {
        return "OpenAM-v13";
    }

    /**
     * @param token
     * @throws Exception
     */
    protected abstract void validateToken(String token) throws Exception;
}
