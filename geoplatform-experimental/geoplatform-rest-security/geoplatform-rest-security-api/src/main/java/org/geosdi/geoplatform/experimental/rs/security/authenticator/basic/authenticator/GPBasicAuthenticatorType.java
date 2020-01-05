package org.geosdi.geoplatform.experimental.rs.security.authenticator.basic.authenticator;

import org.geosdi.geoplatform.experimental.rs.security.authenticator.GPAuthenticatorType;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPBasicAuthenticatorType implements GPAuthenticatorType {

    GP_BASIC_AUTH("GPBasicAuth");

    private final String authenticatorType;

    /**
     * @param theAuthenticatorType
     */
    GPBasicAuthenticatorType(@Nonnull(when = When.NEVER) String theAuthenticatorType) {
        checkArgument((theAuthenticatorType != null) && !(theAuthenticatorType.trim().isEmpty()), "The Parameter authenticatorType must not be null or an empty string.");
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
