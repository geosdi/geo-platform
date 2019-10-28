package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth;

import org.apache.http.client.CredentialsProvider;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRSAuthConfiguration extends Serializable {

    /**
     * @return {@link String}
     */
    String getUserName();

    /**
     * @return {@link String}
     */
    String getPassword();

    /**
     * @return {@link Boolean}
     */
    Boolean isSetAuth();

    /**
     * @return {@link CredentialsProvider}
     */
    CredentialsProvider toCredentialProvider();
}