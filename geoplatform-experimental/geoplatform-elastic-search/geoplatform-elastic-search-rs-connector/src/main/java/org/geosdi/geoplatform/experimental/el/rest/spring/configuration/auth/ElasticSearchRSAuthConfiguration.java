package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

import static org.apache.http.auth.AuthScope.ANY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Getter
@AllArgsConstructor
@ToString
class ElasticSearchRSAuthConfiguration implements GPElasticSearchRSAuthConfiguration {

    private static final long serialVersionUID = 7474263806645962645L;
    //
    private final String userName;
    private final String password;

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetAuth() {
        return (((this.userName != null) && !(this.userName.trim()
                .isEmpty())) && ((this.password != null) && !(this.password.trim().isEmpty())));
    }

    /**
     * @return {@link CredentialsProvider}
     */
    @Override
    public CredentialsProvider toCredentialProvider() {
        return (isSetAuth() ? new BasicCredentialsProvider() {
            {
                super.setCredentials(ANY, new UsernamePasswordCredentials(userName, password));
            }
        } : null);
    }
}