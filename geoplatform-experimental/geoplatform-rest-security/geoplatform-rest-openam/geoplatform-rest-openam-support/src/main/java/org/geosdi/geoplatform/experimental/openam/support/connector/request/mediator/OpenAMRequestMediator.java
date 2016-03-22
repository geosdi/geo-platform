package org.geosdi.geoplatform.experimental.openam.support.connector.request.mediator;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;

import java.util.EnumMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMRequestMediator implements IOpenAMRequestMediator {

    private final EnumMap<BaseOpenAMRequest.OpenAMRequestType, BaseOpenAMRequest> store;

    public OpenAMRequestMediator(EnumMap<BaseOpenAMRequest.OpenAMRequestType, BaseOpenAMRequest> theStore) {
        this.store = theStore;
    }

    /**
     * @param requestType
     * @return {@link BaseOpenAMRequest}
     * @throws Exception
     */
    @Override
    public BaseOpenAMRequest getRequest(BaseOpenAMRequest.OpenAMRequestType requestType) throws Exception {
        Preconditions.checkNotNull(requestType, "The Parameter Type must not be null.");
        return this.store.get(requestType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " store = " + store +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(this.store, "The OpenAmRequest Store must not be null.");
    }
}
