package org.geosdi.geoplatform.experimental.openam.support.config.mediator;

import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.mediator.IOpenAMRequestMediator;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.mediator.OpenAMRequestMediator;
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
class OpenAMRequestMediatorConfig {

    @Autowired
    private List<BaseOpenAMRequest> requests;

    @Bean
    public IOpenAMRequestMediator openAMRequestMediator() {
        EnumMap<BaseOpenAMRequest.OpenAMRequestType, BaseOpenAMRequest> store = new EnumMap<BaseOpenAMRequest.OpenAMRequestType, BaseOpenAMRequest>(BaseOpenAMRequest.OpenAMRequestType.class);
        this.requests.stream().forEach(request -> store.put(request.getRequestType(), request));
        return new OpenAMRequestMediator(store);
    }
}
