package org.geosdi.geoplatform.experimental.rs.security.authenticator.filter;

import org.geosdi.geoplatform.experimental.rs.security.authenticator.GPAuthenticatorType;

import javax.ws.rs.container.ContainerRequestFilter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPAuthenticatorFilter extends ContainerRequestFilter {

    /**
     * @return {@link Authenticator_Type}
     */
    <Authenticator_Type extends GPAuthenticatorType> Authenticator_Type getAuthenticatorType();

    /**
     * @return {@link String}
     */
    String getAuthenticatorName();

    /**
     * @return {@link String}
     */
    String getAuthenticatorVersion();
}
