package org.geosdi.geoplatform.experimental.openam.support.config.mediator;

import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.mediator.IRequestParameterMediator;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.parameter.mediator.RequestParameterMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class RequestParameterMediatorConfig {

    @Autowired
    private List<RequestParameter> requestParameters;

    @Bean
    public IRequestParameterMediator requestParameterMediator() {
        EnumMap<RequestParameter.RequestParameterType, RequestParameter> store = new EnumMap<RequestParameter.RequestParameterType, RequestParameter>(RequestParameter.RequestParameterType.class);
        this.requestParameters.stream().forEach(requestParameter -> store.put(requestParameter.getRequestParameterType(), requestParameter));
        return new RequestParameterMediator(store);
    }
}
