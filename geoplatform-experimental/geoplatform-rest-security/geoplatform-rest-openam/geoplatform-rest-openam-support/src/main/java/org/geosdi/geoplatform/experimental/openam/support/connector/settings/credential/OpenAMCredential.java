package org.geosdi.geoplatform.experimental.openam.support.connector.settings.credential;

import com.google.common.base.Preconditions;
import org.apache.http.annotation.Immutable;
import org.geosdi.geoplatform.experimental.openam.api.connector.credential.IOpenAMCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMCredential")
public class OpenAMCredential implements IOpenAMCredential {

    private static final long serialVersionUID = -5475190384852298753L;
    //
    @Value("openAMConnectorConfigurator{openam.connector.username:@null}")
    private String userName;
    @Value("openAMConnectorConfigurator{openam.connector.username_key:@null}")
    private String userNameKey;
    @Value("openAMConnectorConfigurator{openam.connector.password:@null}")
    private String password;
    @Value("openAMConnectorConfigurator{openam.connector.password_key:@null}")
    private String passwordKey;

    /**
     * @return {@link String}
     */
    @Override
    public String getUserName() {
        return this.userName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getUserNameKey() {
        return this.userNameKey = (((this.userNameKey != null) && !(this.userNameKey.isEmpty()))
                ? this.userNameKey : "X-OpenAM-Username");
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPasswordKey() {
        return this.passwordKey = (((this.passwordKey != null) && !(this.passwordKey.isEmpty()))
                ? this.passwordKey : "X-OpenAM-Password");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  userName = '" + userName + '\'' +
                "  userNameKey = '" + getUserNameKey() + '\'' +
                ", password = '" + password + '\'' +
                ", passwordKey = '" + getPasswordKey() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.userName != null)
                && !(this.userName.isEmpty())), "The UserName Parameter must not be "
                + "null or an empty value.");

        Preconditions.checkArgument(((this.password != null)
                && !(this.password.isEmpty())), "The Password Parameter must not be "
                + "null or an empty value.");
    }
}
