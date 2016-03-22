package org.geosdi.geoplatform.experimental.openam.support.connector.request.validate;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest.OpenAMRequestType.VALIDATE_TOKEN;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "openAMValidateTokenRequest")
public class ValidateTokenRequest implements BaseOpenAMRequest<ValidateTokenRequest> {

    private static final long serialVersionUID = 335320859108474664L;
    //
    @Value("openAMConnectorConfigurator{openam.validate_token.request.path:@null}")
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
     * @return {@link OpenAMRequestType}
     */
    @Override
    public OpenAMRequestType getRequestType() {
        return VALIDATE_TOKEN;
    }

    /**
     * @param theExtraParamPath
     */
    @Override
    public ValidateTokenRequest setExtraPathParam(String... theExtraParamPath) {
        if ((theExtraParamPath != null) && (theExtraParamPath.length > 0)) {
            this.extraParamPath.set(Arrays
                    .stream(theExtraParamPath)
                    .filter(p -> ((p != null) && !(p.isEmpty())))
                    .collect(Collectors.joining("/")));
        }
        return self();
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
