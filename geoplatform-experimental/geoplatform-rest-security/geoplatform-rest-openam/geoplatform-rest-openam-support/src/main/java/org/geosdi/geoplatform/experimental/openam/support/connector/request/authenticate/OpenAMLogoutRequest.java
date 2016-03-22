package org.geosdi.geoplatform.experimental.openam.support.connector.request.authenticate;

import com.google.common.base.Preconditions;
import org.apache.http.annotation.Immutable;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.LOGOUT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMLogoutRequest")
public class OpenAMLogoutRequest implements BaseOpenAMRequest<OpenAMLogoutRequest> {

    private static final long serialVersionUID = -1055562460200858864L;
    //
    @Value("openAMConnectorConfigurator{openam.logout.request.path:@null}")
    private String path;

    /**
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return LOGOUT;
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
    }
}
