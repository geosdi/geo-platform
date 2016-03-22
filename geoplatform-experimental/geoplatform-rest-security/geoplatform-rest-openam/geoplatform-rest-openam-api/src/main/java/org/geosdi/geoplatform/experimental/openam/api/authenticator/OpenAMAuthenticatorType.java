package org.geosdi.geoplatform.experimental.openam.api.authenticator;

import org.geosdi.geoplatform.experimental.rs.security.authenticator.GPAuthenticatorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum OpenAMAuthenticatorType implements GPAuthenticatorType {
    OPENAM("OpenAM");

    private final String authenticatorType;

    OpenAMAuthenticatorType(String theAuthenticatorType) {
        this.authenticatorType = theAuthenticatorType;
    }


    /**
     * @return {@link String}
     */
    @Override
    public String getAuthenticatorType() {
        return this.authenticatorType;
    }

    @Override
    public String toString() {
        return this.authenticatorType;
    }
}
