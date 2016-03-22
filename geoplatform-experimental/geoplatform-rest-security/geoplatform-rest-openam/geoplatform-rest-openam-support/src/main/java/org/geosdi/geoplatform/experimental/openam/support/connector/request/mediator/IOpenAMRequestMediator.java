package org.geosdi.geoplatform.experimental.openam.support.connector.request.mediator;

import org.geosdi.geoplatform.experimental.openam.support.connector.request.BaseOpenAMRequest;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMRequestMediator extends InitializingBean {

    /**
     * @param requestType
     * @return {@link BaseOpenAMRequest}
     * @throws Exception
     */
    <Request extends BaseOpenAMRequest> Request getRequest(BaseOpenAMRequest.OpenAMRequestType requestType) throws Exception;
}
