package org.geosdi.geoplatform.experimental.openam.support.connector.request.parameter.mediator;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.mediator.IRequestParameterMediator;

import java.util.EnumMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class RequestParameterMediator implements IRequestParameterMediator {

    private final EnumMap<RequestParameter.RequestParameterType, RequestParameter> store;

    public RequestParameterMediator(EnumMap<RequestParameter.RequestParameterType, RequestParameter> theStore) {
        this.store = theStore;
    }

    /**
     * @param type
     * @return {@link RequestParameter}
     * @throws Exception
     */
    @Override
    public RequestParameter getRequest(RequestParameter.RequestParameterType type) throws Exception {
        Preconditions.checkNotNull(type, "The Parameter Type must not be null.");
        return this.store.get(type);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " store = " + store +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(this.store, "The OpenAmRequestParameter Store must not be null.");
    }
}
