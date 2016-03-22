package org.geosdi.geoplatform.experimental.openam.support.connector.request.group;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.UPDATE_GROUP_ADD_USER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "openAMUpdateGroupAddUserRequest")
public class OpenAMUpdateGroupAddUserRequest implements BaseOpenAMRequest<OpenAMUpdateGroupAddUserRequest> {

    private static final long serialVersionUID = -8120334703614296790L;
    //
    @Value("openAMConnectorConfigurator{openam.update_group_adding_user.request.path:@null}")
    private String path;
    private String extraParamPath;

    /**
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return ((this.extraParamPath == null) || (this.extraParamPath.isEmpty()))
                ? this.path : this.path.concat(this.extraParamPath);
    }

    /**
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return UPDATE_GROUP_ADD_USER;
    }

    /**
     * @param extraParamPath
     */
    @Override
    public OpenAMUpdateGroupAddUserRequest setExtraPathParam(String... extraParamPath) {
        if ((extraParamPath != null) && (extraParamPath.length > 0)) {
            this.extraParamPath = Arrays
                    .stream(extraParamPath)
                    .filter(p -> ((p != null) && !(p.isEmpty())))
                    .collect(Collectors.joining("/"));
        }
        return self();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  path = '" + path + '\'' +
                ", requestType = '" + getRequestType() + '\'' +
                ", extraParamPath = '" + extraParamPath + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
    }
}
