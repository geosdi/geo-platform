package org.geosdi.geoplatform.experimental.openam.support.connector.request.users;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.UPDATE_USER;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "openAMUpdateUserRequest")
public class OpenAMUpdateUserRequest extends OpenAMBaseUsersRequest<OpenAMUpdateUserRequest> {

    private static final long serialVersionUID = 3935736092011356513L;
    //
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
    public OpenAMUpdateUserRequest setExtraPathParam(String... theExtraParamPath) {
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
        return UPDATE_USER;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  path = '" + path + '\'' +
                ", requestType = '" + getRequestType() + '\'' +
                ", extraParamPath = '" + extraParamPath.get() + '\'' +
                '}';
    }
}
