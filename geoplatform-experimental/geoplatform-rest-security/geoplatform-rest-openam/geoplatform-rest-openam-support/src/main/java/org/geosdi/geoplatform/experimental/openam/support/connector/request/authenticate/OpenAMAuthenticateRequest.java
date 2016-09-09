package org.geosdi.geoplatform.experimental.openam.support.connector.request.authenticate;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.openam.api.connector.credential.IOpenAMCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.AUTHENTICATE;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAmAuthenticateRequest")
public class OpenAMAuthenticateRequest implements IOpenAMAuthenticateRequest {

    private static final long serialVersionUID = -9176446727650048712L;
    //
    @Value("openAMConnectorConfigurator{openam.authenticate.request.path:@null}")
    private String path;
    @Resource(name = "openAMCredential")
    private IOpenAMCredential openAMCredential;

    /**
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getUserName() {
        return this.openAMCredential.getUserName();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getUserNameKey() {
        return this.openAMCredential.getUserNameKey();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPassword() {
        return this.openAMCredential.getPassword();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPasswordKey() {
        return this.openAMCredential.getPasswordKey();
    }

    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return AUTHENTICATE;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " path = '" + path + '\'' +
                ", requestType = '" + getRequestType() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
        Preconditions.checkArgument(((this.openAMCredential != null)),
                "The openAMCredential Parameter must not be "
                        + "null or an empty value.");
    }
}
