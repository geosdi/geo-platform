package org.geosdi.geoplatform.experimental.rs.security.authenticator.filter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPAuthenticatorVersionFilter extends GPAuthenticatorFilter {

    /**
     * @return {@link String}
     */
    String getAuthenticatorVersion();
}