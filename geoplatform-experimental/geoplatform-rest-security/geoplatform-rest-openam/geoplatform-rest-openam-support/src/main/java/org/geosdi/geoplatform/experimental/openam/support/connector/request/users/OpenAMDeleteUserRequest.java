package org.geosdi.geoplatform.experimental.openam.support.connector.request.users;

import com.google.common.base.Preconditions;
import org.apache.http.annotation.Immutable;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.DELETE_USER;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMDeleteUserRequest")
public class OpenAMDeleteUserRequest implements BaseOpenAMRequest<OpenAMDeleteUserRequest> {

    private static final long serialVersionUID = 3606300607189245748L;
    //
    @Value("openAMConnectorConfigurator{openam.users.request.path:@null}")
    private String path;
    private ThreadLocal<String> extraParamPath = new ThreadLocal<>();

    /**
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return ((this.extraParamPath == null) || (this.extraParamPath.get().isEmpty()))
                ? this.path : this.path.concat(this.extraParamPath.get());
    }

    /**
     * @param theExtraParamPath
     */
    @Override
    public OpenAMDeleteUserRequest setExtraPathParam(String... theExtraParamPath) {
        if ((theExtraParamPath != null) && (theExtraParamPath.length > 0)) {
            this.extraParamPath.set(Arrays
                    .stream(theExtraParamPath)
                    .filter(p -> ((p != null) && !(p.isEmpty())))
                    .collect(Collectors.joining("/")));
        }
        return self();
    }

    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return DELETE_USER;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  path = '" + path + '\'' +
                ", requestType = '" + getRequestType() + '\'' +
                ", extraParamPath = '" + extraParamPath.get() + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
    }
}
