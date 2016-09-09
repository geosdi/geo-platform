package org.geosdi.geoplatform.experimental.openam.support.connector.request.users.parameter;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter.RequestParameterType.ACTION_CREATE_USER;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMCreateUserParameter")
public class OpenAMCreateUserParameter implements RequestParameter {

    private static final long serialVersionUID = 4392954755257229864L;
    //
    //
    @Value("openAMConnectorConfigurator{openam.create_user.parameter.key:@null}")
    private String key;
    @Value("openAMConnectorConfigurator{openam.create_user.parameter.value:@null}")
    private String value;

    /**
     * @return {@link String}
     */
    @Override
    public String getkey() {
        return this.key = ((this.key != null) && !(this.key.isEmpty()) ? this.key : "_action");
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValue() {
        return this.value = ((this.value != null) && !(this.value.isEmpty()) ? this.value : "create");
    }

    /**
     * @return {@link RequestParameterType}
     */
    @Override
    public RequestParameterType getRequestParameterType() {
        return ACTION_CREATE_USER;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " key = '" + key + '\'' +
                ", value = '" + value + '\'' +
                ", requestParameterType = '" + getRequestParameterType() + '\'' +
                '}';
    }
}
