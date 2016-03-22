package org.geosdi.geoplatform.experimental.openam.support.connector.request.serverinfo;

import com.google.common.base.Preconditions;
import org.apache.http.annotation.Immutable;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.SERVER_INFO;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMServerInfoRequest")
public class OpenAMServerInfoRequest implements BaseOpenAMRequest<OpenAMServerInfoRequest> {

    private static final long serialVersionUID = 6708504242365498004L;
    //
    @Value("openAMConnectorConfigurator{openam.server_info.request.path:@null}")
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
        return SERVER_INFO;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " path = '" + path + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
    }
}
