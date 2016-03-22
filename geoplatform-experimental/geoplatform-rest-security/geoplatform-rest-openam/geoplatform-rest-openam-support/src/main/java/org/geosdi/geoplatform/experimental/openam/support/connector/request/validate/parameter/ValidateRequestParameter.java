package org.geosdi.geoplatform.experimental.openam.support.connector.request.validate.parameter;

import org.apache.http.annotation.Immutable;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter.RequestParameterType.ACTION_VALIDATE;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "validateRequestParameter")
public class ValidateRequestParameter implements RequestParameter {

    private static final long serialVersionUID = -6179771246035252073L;
    //
    @Value("openAMConnectorConfigurator{openam.validate.request.parameter.key:@null}")
    private String key;
    @Value("openAMConnectorConfigurator{openam.validate.request.parameter.value:@null}")
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
        return this.value = ((this.value != null) && !(this.value.isEmpty()) ? this.value : "validate");
    }

    /**
     * @return {@link RequestParameterType}
     */
    @Override
    public RequestParameterType getRequestParameterType() {
        return ACTION_VALIDATE;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " key = '" + getkey() + '\'' +
                ", value = '" + getValue() + '\'' +
                ", requestParameterType = '" + getRequestParameterType() + '\'' +
                '}';
    }
}
